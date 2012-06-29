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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.gnenc.yams.portlet.search.UserDisplayTerms;
import org.gnenc.yams.portlet.util.PortletUtil;

import com.liferay.portal.kernel.dao.search.DAOParamUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.util.bridges.mvc.MVCPortlet;
public class AccountManagement extends MVCPortlet {
	@Override
	public void processAction(ActionRequest actionRequest, 
			ActionResponse actionResponse) {
		System.out.println("Yeehaw!");
		actionResponse.setRenderParameter("jspPage", PortletUtil.SEARCH_ACCOUNTS_JSP);
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
			case PROCESS_ACCOUNT_NAME: 
				String firstName = DAOParamUtil.getString(
						resourceRequest, UserDisplayTerms.FIRST_NAME);
				String lastName = DAOParamUtil.getString(
						resourceRequest, UserDisplayTerms.LAST_NAME);
				responses = PortletUtil.processAccountName(firstName, lastName);
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

	public static final int ADD_ACCOUNT_CMD = 1;
	public static final int EDIT_PASSWORD_CMD = 2;
	public static final int EDIT_ACCOUNT_CMD = 3;
	public static final int PROCESS_ACCOUNT_NAME = 4;
}