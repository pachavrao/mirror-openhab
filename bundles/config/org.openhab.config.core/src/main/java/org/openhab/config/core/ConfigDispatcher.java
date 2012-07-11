/**
 * openHAB, the open Home Automation Bus.
 * Copyright (C) 2010-2012, openHAB.org <admin@openhab.org>
 *
 * See the contributors.txt file in the distribution for a
 * full listing of individual contributors.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses>.
 *
 * Additional permission under GNU GPL version 3 section 7
 *
 * If you modify this Program, or any covered work, by linking or
 * combining it with Eclipse (or a modified version of that library),
 * containing parts covered by the terms of the Eclipse Public License
 * (EPL), the licensors of this Program grant you additional permission
 * to convey the resulting work.
 */
package org.openhab.config.core;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.impl.matchers.GroupMatcher.jobGroupEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.openhab.config.core.internal.ConfigActivator;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.ManagedService;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class provides a mean to read any kind of configuration data from a shared config
 * file and dispatch it to the different bundles using the {@link ConfigurationAdmin} service.
 * 
 * <p>The name of the configuration file can be provided as a program argument "openhab.configfile".
 * If this argument is not set, the default "configurations/openhab.cfg" will be used.
 * In case the configuration file does not exist, a warning will be logged and no action
 * will be performed.</p>
 * 
 * <p>The format of the configuration file is similar to a standard property file, with the
 * exception that the property name must be prefixed by the service pid of the {@link ManagedService}:</p>
 * <p>&lt;service-pid&gt;:&lt;property&gt;=&lt;value&gt;</p>
 * <p>The prefix "org.openhab" can be omitted on the service pid, it is automatically added if
 * the pid does not contain any "."</p> 
 * 
 * <p>A quartz job can be scheduled to reinitialize the Configurations on a regular
 * basis (currently one minute</p>
 * 
 * @author Kai Kreuzer
 * @author Thomas.Eichstaedt-Engelen
 * @since 0.3.0
 */
public class ConfigDispatcher {

	private static final Logger logger = LoggerFactory.getLogger(ConfigDispatcher.class);

	// by default, we use the "configurations" folder in the home directory, but this location
	// might be changed in certain situations (especially when setting a config folder in the
	// openHAB Designer).
	private static String configFolder = ConfigConstants.MAIN_CONFIG_FOLDER;
	
	/** the last refresh timestamp in milliseconds */
	private static long lastRefresh;
	
	/** the name of the scheduler group under which refresh jobs are being registered */
	private static final String SCHEDULER_GROUP = "ConfigDispatcher";

	
	/**
	 * Returns the configuration folder path name. The main config folder 
	 * <code>&lt;openhabhome&gt;/configurations</code> could be overwritten by setting
	 * the System property <code>openhab.configdir</code>.
	 * 
	 * @return the configuration folder path name
	 */
	public static String getConfigFolder() {
		String progArg = System.getProperty(ConfigConstants.CONFIG_DIR_PROG_ARGUMENT);
		if (progArg != null) {
			return progArg;
		} else {
			return configFolder;
		}
	}

	/**
	 * Sets the configuration folder to use. Calling this method will automatically
	 * trigger the loading and dispatching of the contained configuration files.
	 * 
	 * @param configFolder the path name to the new configuration folder
	 */
	public static void setConfigFolder(String configFolder) {
		ConfigDispatcher.configFolder = configFolder;
		initializeBundleConfigurations();
	}

	public static void initializeBundleConfigurations() {
		initializeDefaultConfiguration(-1);			
		initializeMainConfiguration(-1);			
	}

	private static void initializeDefaultConfiguration(long lastRefresh) {
		String defaultConfigFilePath = getDefaultConfigurationFilePath();
		File defaultConfigFile = new File(defaultConfigFilePath);
		
		if (lastRefresh > 0 && defaultConfigFile.lastModified() <= lastRefresh) {
			logger.trace(
				"default configuration file '{}' hasn't been changed since '{}' (lasModified='{}') -> initialization aborted.",
				new Object[] { defaultConfigFile.getAbsolutePath(),	lastRefresh, defaultConfigFile.lastModified() });
			return;
		}
		
		try {
			logger.debug("Processing openHAB default configuration file '{}'.", defaultConfigFile.getAbsolutePath());
			processConfigFile(defaultConfigFile);
		} catch (FileNotFoundException e) {
			// we do not care if we do not have a default file
		} catch (IOException e) {
			logger.error("Default openHAB configuration file '{}' cannot be read.", defaultConfigFilePath, e);
		}
	}

	private static void initializeMainConfiguration(long lastRefresh) {
		String mainConfigFilePath = getMainConfigurationFilePath();
		File mainConfigFile = new File(mainConfigFilePath);

		if (lastRefresh > 0 && mainConfigFile.lastModified() <= lastRefresh) {
			logger.trace(
				"main configuration file '{}' hasn't been changed since '{}' (lasModified='{}') -> initialization aborted.",
				new Object[] { mainConfigFile.getAbsolutePath(),	lastRefresh, mainConfigFile.lastModified() });
			return;
		}
		
		try {
			logger.debug("Processing openHAB main configuration file '{}'.", mainConfigFile.getAbsolutePath());
			processConfigFile(mainConfigFile);
		} catch (FileNotFoundException e) {
			logger.warn("Main openHAB configuration file '{}' does not exist.", mainConfigFilePath);
		} catch (IOException e) {
			logger.error("Main openHAB configuration file '{}' cannot be read.", mainConfigFilePath, e);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void processConfigFile(File configFile) throws IOException, FileNotFoundException {
		ConfigurationAdmin configurationAdmin = (ConfigurationAdmin) ConfigActivator.configurationAdminTracker.getService();
		if(configurationAdmin!=null) {
			// we need to remember which configuration needs to be updated because values have changed.
			Map<Configuration, Dictionary> configsToUpdate = new HashMap<Configuration, Dictionary>();
			
			// also cache the already retrieved configurations for each pid
			Map<Configuration, Dictionary> configMap = new HashMap<Configuration, Dictionary>();
			
			List<String> lines = IOUtils.readLines(new FileInputStream(configFile));
			for(String line : lines) {					
				String[] contents = parseLine(configFile.getPath(), line);
				// no valid configuration line, so continue
				if(contents==null) continue;
				String pid = contents[0];
				String property = contents[1];
				String value = contents[2];
				Configuration configuration = configurationAdmin.getConfiguration(pid, null);
				if(configuration!=null) {
					Dictionary configProperties = configMap.get(configuration);
					if(configProperties==null) {
						configProperties = new Properties();
						configMap.put(configuration, configProperties);
					}
					if(!value.equals(configProperties.get(property))) {
						configProperties.put(property, value);
						configsToUpdate.put(configuration, configProperties);
					}
				}
			}
			for(Entry<Configuration, Dictionary> entry : configsToUpdate.entrySet()) {
				entry.getKey().update(entry.getValue());
			}
		}
	}

	private static String[] parseLine(final String filePath, final String line) {
		String trimmedLine = line.trim();
		if(trimmedLine.startsWith("#") || trimmedLine.isEmpty()) {
			return null;
		}
		if(trimmedLine.substring(1).contains(":")) { 
			String pid = StringUtils.substringBefore(line, ":");
			String rest = line.substring(pid.length() + 1);
			if(!pid.contains(".")) {
				pid = "org.openhab." + pid;
			}
			if(!rest.isEmpty() && rest.substring(1).contains("=")) {
				String property = StringUtils.substringBefore(rest, "=");
				String value = rest.substring(property.length() + 1);
				return new String[] { pid.trim(), property.trim(), value.trim() };
			}
		}
		logger.warn("Cannot parse line '{}' of main configuration file '{}'.", line, filePath);
		return null;
	}

	private static String getDefaultConfigurationFilePath() {
		return configFolder + "/" + ConfigConstants.DEFAULT_CONFIG_FILENAME;
	}

	private static String getMainConfigurationFilePath() {
		String progArg = System.getProperty(ConfigConstants.CONFIG_FILE_PROG_ARGUMENT);
		if(progArg!=null) {
			return progArg;
		} else {
			return getConfigFolder() + "/" + ConfigConstants.MAIN_CONFIG_FILENAME;
		}
	}
	
	
	/**
	 * Schedules a quartz job which is triggered every minute.
	 */
	public static void scheduleRefreshJob() {
		try {
			Scheduler sched = StdSchedulerFactory.getDefaultScheduler();
			JobDetail job = newJob(RefreshJob.class)
			    .withIdentity("Refresh", SCHEDULER_GROUP)
			    .build();

			CronTrigger trigger = newTrigger()
			    .withIdentity("Refresh", "ConfigDispatcher")
			    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?"))
			    .build();

			sched.scheduleJob(job, trigger);
			logger.debug("Created refresh job '{}' in DefaulScheduler", job);
		} catch (SchedulerException e) {
			logger.warn("Could not create refresh job: {}", e.getMessage());
		}
	}
	
	/**
	 * Deletes all quartz refresh jobs containing to group 'ConfigDispatcher'
	 */
	public static void cancelRefreshJob() {
		try {
			Scheduler sched = StdSchedulerFactory.getDefaultScheduler();
			Set<JobKey> jobKeys = sched.getJobKeys(jobGroupEquals(SCHEDULER_GROUP));
			if (jobKeys.size() > 0) {
				sched.deleteJobs(new ArrayList<JobKey>(jobKeys));
				logger.debug("Found {} refresh jobs to delete from DefaulScheduler (keys={})", jobKeys.size(), jobKeys);
			}
		} catch (SchedulerException e) {
			logger.warn("Could not remove refresh job: {}", e.getMessage());
		}		
	}
	
	
	/**
	 * A quartz scheduler job to refresh the Configuration (via {@link ConfigurationAdmin})
	 * when it changed.
	 */
	@DisallowConcurrentExecution
	public static class RefreshJob implements Job {
		
		public void execute(JobExecutionContext context) throws JobExecutionException {
			initializeDefaultConfiguration(lastRefresh);
			initializeMainConfiguration(lastRefresh);
			lastRefresh = System.currentTimeMillis();
		}
		
	}
	
	
}
