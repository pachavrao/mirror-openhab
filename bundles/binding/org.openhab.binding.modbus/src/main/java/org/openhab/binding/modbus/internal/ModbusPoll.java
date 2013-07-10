/**
 * openHAB, the open Home Automation Bus.
 * Copyright (C) 2010-2013, openHAB.org <admin@openhab.org>
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
package org.openhab.binding.modbus.internal;

import java.util.Collection;
import java.util.HashSet;

import org.openhab.core.service.AbstractActiveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Modbus polling service that reads information from modbus slave devices
 *
 * @author Dmitry Krasnov
 * @since 1.1.0
 */
public class ModbusPoll extends AbstractActiveService {

	static final Logger logger = LoggerFactory.getLogger(ModbusPoll.class);

	/**
	 * ModbusBinding that stores information about items to be updated
	 */
	ModbusBinding binding = null;

	public void setModbusBinding(ModbusBinding binding) {
		this.binding = binding;
		if (!ModbusConfiguration.getAllSlaves().isEmpty()) {
			setProperlyConfiguredAndStart();
		}
	}

	public void unsetModbusBinding(ModbusBinding binding) {
		this.binding = null;
	}

	@Override
	protected long getRefreshInterval() {
		return 	ModbusConfiguration.poll;
	}

	@Override
	protected String getName() {
		return "Modbus Polling Service";
	}

	@Override
	/**
	 * updates all slaves from the modbusSlaves
	 */
	protected void execute() {
		Collection<ModbusSlave> slaves = new HashSet<ModbusSlave>();
		synchronized (slaves) {
			slaves.addAll(ModbusConfiguration.getAllSlaves());
		}
		for (ModbusSlave slave : slaves) {
			slave.update(binding);
		}
	}


}
