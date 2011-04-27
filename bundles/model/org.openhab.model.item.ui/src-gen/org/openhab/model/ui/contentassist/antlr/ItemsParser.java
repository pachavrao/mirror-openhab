/**
 * openHAB, the open Home Automation Bus.
 * Copyright (C) 2011, openHAB.org <admin@openhab.org>
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

/*
* generated by Xtext
*/
package org.openhab.model.ui.contentassist.antlr;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

import com.google.inject.Inject;

import org.openhab.model.services.ItemsGrammarAccess;

public class ItemsParser extends AbstractContentAssistParser {
	
	@Inject
	private ItemsGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected org.openhab.model.ui.contentassist.antlr.internal.InternalItemsParser createParser() {
		org.openhab.model.ui.contentassist.antlr.internal.InternalItemsParser result = new org.openhab.model.ui.contentassist.antlr.internal.InternalItemsParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getModelItemAccess().getAlternatives_0(), "rule__ModelItem__Alternatives_0");
					put(grammarAccess.getModelItemAccess().getIconAlternatives_3_1_0(), "rule__ModelItem__IconAlternatives_3_1_0");
					put(grammarAccess.getModelGroupItemAccess().getArgsAlternatives_2_2_2_1_0(), "rule__ModelGroupItem__ArgsAlternatives_2_2_2_1_0");
					put(grammarAccess.getModelGroupItemAccess().getArgsAlternatives_2_2_2_2_1_0(), "rule__ModelGroupItem__ArgsAlternatives_2_2_2_2_1_0");
					put(grammarAccess.getModelItemTypeAccess().getAlternatives(), "rule__ModelItemType__Alternatives");
					put(grammarAccess.getModelGroupFunctionAccess().getAlternatives(), "rule__ModelGroupFunction__Alternatives");
					put(grammarAccess.getModelItemAccess().getGroup(), "rule__ModelItem__Group__0");
					put(grammarAccess.getModelItemAccess().getGroup_3(), "rule__ModelItem__Group_3__0");
					put(grammarAccess.getModelItemAccess().getGroup_4(), "rule__ModelItem__Group_4__0");
					put(grammarAccess.getModelItemAccess().getGroup_4_2(), "rule__ModelItem__Group_4_2__0");
					put(grammarAccess.getModelItemAccess().getGroup_5(), "rule__ModelItem__Group_5__0");
					put(grammarAccess.getModelItemAccess().getGroup_5_2(), "rule__ModelItem__Group_5_2__0");
					put(grammarAccess.getModelGroupItemAccess().getGroup(), "rule__ModelGroupItem__Group__0");
					put(grammarAccess.getModelGroupItemAccess().getGroup_2(), "rule__ModelGroupItem__Group_2__0");
					put(grammarAccess.getModelGroupItemAccess().getGroup_2_2(), "rule__ModelGroupItem__Group_2_2__0");
					put(grammarAccess.getModelGroupItemAccess().getGroup_2_2_2(), "rule__ModelGroupItem__Group_2_2_2__0");
					put(grammarAccess.getModelGroupItemAccess().getGroup_2_2_2_2(), "rule__ModelGroupItem__Group_2_2_2_2__0");
					put(grammarAccess.getModelBindingAccess().getGroup(), "rule__ModelBinding__Group__0");
					put(grammarAccess.getItemModelAccess().getItemsAssignment(), "rule__ItemModel__ItemsAssignment");
					put(grammarAccess.getModelItemAccess().getNameAssignment_1(), "rule__ModelItem__NameAssignment_1");
					put(grammarAccess.getModelItemAccess().getLabelAssignment_2(), "rule__ModelItem__LabelAssignment_2");
					put(grammarAccess.getModelItemAccess().getIconAssignment_3_1(), "rule__ModelItem__IconAssignment_3_1");
					put(grammarAccess.getModelItemAccess().getGroupsAssignment_4_1(), "rule__ModelItem__GroupsAssignment_4_1");
					put(grammarAccess.getModelItemAccess().getGroupsAssignment_4_2_1(), "rule__ModelItem__GroupsAssignment_4_2_1");
					put(grammarAccess.getModelItemAccess().getBindingsAssignment_5_1(), "rule__ModelItem__BindingsAssignment_5_1");
					put(grammarAccess.getModelItemAccess().getBindingsAssignment_5_2_1(), "rule__ModelItem__BindingsAssignment_5_2_1");
					put(grammarAccess.getModelGroupItemAccess().getTypeAssignment_2_1(), "rule__ModelGroupItem__TypeAssignment_2_1");
					put(grammarAccess.getModelGroupItemAccess().getFunctionAssignment_2_2_1(), "rule__ModelGroupItem__FunctionAssignment_2_2_1");
					put(grammarAccess.getModelGroupItemAccess().getArgsAssignment_2_2_2_1(), "rule__ModelGroupItem__ArgsAssignment_2_2_2_1");
					put(grammarAccess.getModelGroupItemAccess().getArgsAssignment_2_2_2_2_1(), "rule__ModelGroupItem__ArgsAssignment_2_2_2_2_1");
					put(grammarAccess.getModelNormalItemAccess().getTypeAssignment(), "rule__ModelNormalItem__TypeAssignment");
					put(grammarAccess.getModelBindingAccess().getTypeAssignment_0(), "rule__ModelBinding__TypeAssignment_0");
					put(grammarAccess.getModelBindingAccess().getConfigurationAssignment_2(), "rule__ModelBinding__ConfigurationAssignment_2");
				}
			};
		}
		return nameMappings.get(element);
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			org.openhab.model.ui.contentassist.antlr.internal.InternalItemsParser typedParser = (org.openhab.model.ui.contentassist.antlr.internal.InternalItemsParser) parser;
			typedParser.entryRuleItemModel();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}
	
	public ItemsGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(ItemsGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
