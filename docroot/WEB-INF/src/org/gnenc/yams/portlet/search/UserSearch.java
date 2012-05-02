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
package org.gnenc.yams.portlet.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.gnenc.yams.model.Account;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortalPreferences;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.usersadmin.util.UsersAdminUtil;

/**
 * Modeled after com.liferay.portlet.usersadmin.search.UserSearch
 * written by Brian Wing Shun Chan
 * 
 * @author Drew A. Blessing
 */
public class UserSearch extends SearchContainer<Account> {

	static List<String> headerNames = new ArrayList<String>();
	static Map<String, String> orderableHeaders = new HashMap<String, String>();
	
	static {
		headerNames.add("first-name");
		headerNames.add("last-name");
		headerNames.add("email-address");
		headerNames.add("position");
		headerNames.add("group");
		
		orderableHeaders.put("first-name", "first-name");
		orderableHeaders.put("last-name", "last-name");
		orderableHeaders.put("email-address", "email-address");
		orderableHeaders.put("position", "position");
		orderableHeaders.put("group", "group");
	}
	
	public static final String EMPTY_RESULTS_MESSAGE = "no-users-were-found";
	
	public UserSearch(PortletRequest portletRequest, PortletURL iteratorURL) {
		this(portletRequest, DEFAULT_CUR_PARAM, iteratorURL);
	}

	public UserSearch(
		PortletRequest portletRequest, String curParam,
		PortletURL iteratorURL) {

		super(
			portletRequest, new UserDisplayTerms(portletRequest),
			new UserSearchTerms(portletRequest), curParam, DEFAULT_DELTA,
			iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		PortletConfig portletConfig =
			(PortletConfig)portletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_CONFIG);

		UserDisplayTerms displayTerms = (UserDisplayTerms)getDisplayTerms();
		UserSearchTerms searchTerms = (UserSearchTerms)getSearchTerms();

		String portletName = portletConfig.getPortletName();

		if (!portletName.equals(PortletKeys.USERS_ADMIN)) {
			displayTerms.setStatus(WorkflowConstants.STATUS_APPROVED);
			searchTerms.setStatus(WorkflowConstants.STATUS_APPROVED);
		}

		iteratorURL.setParameter(
			UserDisplayTerms.STATUS, String.valueOf(displayTerms.getStatus()));

		iteratorURL.setParameter(
			UserDisplayTerms.EMAIL_ADDRESS, displayTerms.getEmailAddress());
		iteratorURL.setParameter(
			UserDisplayTerms.FIRST_NAME, displayTerms.getFirstName());
		iteratorURL.setParameter(
				UserDisplayTerms.GROUP, displayTerms.getGroup());
		iteratorURL.setParameter(
				UserDisplayTerms.POSITION, displayTerms.getPosition());
		iteratorURL.setParameter(
			UserDisplayTerms.LAST_NAME, displayTerms.getLastName());

		try {
			PortalPreferences preferences =
				PortletPreferencesFactoryUtil.getPortalPreferences(
					portletRequest);

			String orderByCol = ParamUtil.getString(
				portletRequest, "orderByCol");
			String orderByType = ParamUtil.getString(
				portletRequest, "orderByType");

			if (Validator.isNotNull(orderByCol) &&
				Validator.isNotNull(orderByType)) {

				preferences.setValue(
					"users", "users-order-by-col", orderByCol);
				preferences.setValue(
					"users", "users-order-by-type",
					orderByType);
			}
			else {
				orderByCol = preferences.getValue(
					"users", "users-order-by-col", "last-name");
				orderByType = preferences.getValue(
					"users", "users-order-by-type", "asc");
			}

			OrderByComparator orderByComparator =
				UsersAdminUtil.getUserOrderByComparator(
					orderByCol, orderByType);

			setOrderableHeaders(orderableHeaders);
			setOrderByCol(orderByCol);
			setOrderByType(orderByType);
			setOrderByComparator(orderByComparator);
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(UserSearch.class);

	
}
