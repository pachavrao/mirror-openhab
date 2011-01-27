/**
 * openHAB, the open Home Automation Bus.
 * Copyright (C) 2010, openHAB.org <admin@openhab.org>
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

package org.openhab.ui.internal.items;

import org.openhab.model.core.ModelRepository;
import org.openhab.model.items.Item;
import org.openhab.model.items.ItemModel;
import org.openhab.model.sitemap.Widget;
import org.openhab.ui.items.ItemUIProvider;

public class GenericItemUIProvider implements ItemUIProvider {

	private ModelRepository modelRepository = null;

	public void setModelRepository(ModelRepository modelRepository) {
		this.modelRepository = modelRepository;
	}

	public void unsetModelRepository(ModelRepository modelRepository) {
		this.modelRepository = null;
	}

	@Override
	public String getIcon(String itemName) {
		Item item = getItem(itemName);
		return item != null ? item.getIcon() : null;
	}

	@Override
	public String getLabel(String itemName) {
		Item item = getItem(itemName);
		return item != null ? item.getLabel() : null;
	}

	@Override
	public Widget getWidget(String itemName) {
		return null;
	}

	@Override
	public Widget getDefaultWidget(Class<? extends org.openhab.core.items.Item> itemType, String itemName) {
		return null;
	}

	public Item getItem(String itemName) {
		if (itemName != null && modelRepository != null) {
			for (String modelName : modelRepository
					.getAllModelNamesOfType("items")) {
				ItemModel model = (ItemModel) modelRepository.getModel(modelName);
				for (Item item : model.getItems()) {
					if (item.getName().equals(itemName))
						return item;
				}
			}
		}
		return null;
	}

}
