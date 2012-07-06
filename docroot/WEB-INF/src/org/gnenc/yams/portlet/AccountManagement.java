/**
 *  Copyright (c) 2012-2013 Educational Service Unit 10.
 *
 *  This file is part of the YAMS portlet.
 *
 *  YAMS portlet is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  YAMS portlet is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with the YAMS portlet.  If not, see <http://www.gnu.org/licenses/>.
 **/
package org.gnenc.yams.portlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.xml.bind.ValidationException;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.model.Group;
import org.gnenc.yams.model.GroupMap;
import org.gnenc.yams.model.SearchFilter;
import org.gnenc.yams.model.SearchFilter.Filter;
import org.gnenc.yams.model.SubSystem;
import org.gnenc.yams.portlet.search.UserDisplayTerms;
import org.gnenc.yams.portlet.search.util.SearchUtil;
import org.gnenc.yams.portlet.util.PortletUtil;
import org.gnenc.yams.service.AccountManagementService;
import org.gnenc.yams.service.impl.AccountManagementServiceImpl;

import com.liferay.portal.kernel.dao.search.DAOParamUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.util.bridges.mvc.MVCPortlet;
public class AccountManagement extends MVCPortlet {
	public void editAccount(
			ActionRequest actionRequest, ActionResponse actionResponse) {
		Account account = ActionUtil.accountFromRequest(actionRequest);
		Account newAccount = null;
		AccountManagementService ams = AccountManagementServiceImpl.getInstance();
		List<SubSystem> subsystems = new ArrayList<SubSystem>();
		
		subsystems.add(SubSystem.LDAP);
		try {
			newAccount = ams.createAccount(account, Collections.EMPTY_LIST, subsystems);
		} catch (ValidationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// That's Ok
		}
	}
	
	public static List<Group> getAllGroups() {
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(
				Filter.name, StringPool.STAR, false));
		
		List<Group> groups = Search.getGroups(filters, null, 
				StringPool.BLANK, StringPool.BLANK, false);
		SearchUtil.sortGroups(groups, "asc", "cn");
		
		return groups;
	}
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, 
			ResourceResponse resourceResponse) throws IOException {
		HashMap<String,String> responses = new HashMap<String,String>();
		switch (DAOParamUtil.getInteger(resourceRequest, UserDisplayTerms.CMD)) {
			case EDIT_PASSWORD_CMD: responses =
					PortletUtil.editPassword(resourceRequest, resourceResponse);
				break;
			case EDIT_ACCOUNT_CMD: responses =
					PortletUtil.editAccount(resourceRequest, resourceResponse);
				break;
			case ADD_ACCOUNT_STEP_1_CMD: 
				String group = DAOParamUtil.getString(
						resourceRequest, UserDisplayTerms.GROUP);
				String firstName = DAOParamUtil.getString(
						resourceRequest, UserDisplayTerms.FIRST_NAME);
				String lastName = DAOParamUtil.getString(
						resourceRequest, UserDisplayTerms.LAST_NAME);
				PortletUtil.processAccountName(firstName, lastName, 
						group, responses);
			default: //nothing
				break;
		}

		resourceResponse.setContentType("text/javascript");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		Iterator<Entry<String, String>> it = responses.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String,String> response = (Map.Entry<String,String>)it.next();
			jsonObject.put(response.getKey(), response.getValue());
		}
		
		PrintWriter writer = resourceResponse.getWriter();
		writer.write(jsonObject.toString());
	}

	public static final int ADD_ACCOUNT_STEP_1_CMD = 1;
	public static final int EDIT_PASSWORD_CMD = 2;
	public static final int EDIT_ACCOUNT_CMD = 3;
}