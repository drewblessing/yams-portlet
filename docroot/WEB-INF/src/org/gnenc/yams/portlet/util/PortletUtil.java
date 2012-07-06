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
package org.gnenc.yams.portlet.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.xml.bind.ValidationException;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.model.Group;
import org.gnenc.yams.model.SearchFilter;
import org.gnenc.yams.model.SearchFilter.Filter;
import org.gnenc.yams.model.SubSystem;
import org.gnenc.yams.portlet.Search;
import org.gnenc.yams.portlet.search.UserDisplayTerms;
import org.gnenc.yams.portlet.search.UserSearchTerms;
import org.gnenc.yams.service.AccountManagementService;
import org.gnenc.yams.service.impl.AccountManagementServiceImpl;

import com.liferay.portal.kernel.dao.search.DAOParamUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
public class PortletUtil {
	private static List<SubSystem> checkAccountExists(String mail) 
			throws ValidationException {
		AccountManagementService ams = AccountManagementServiceImpl.getInstance();
		
		return ams.checkAccountExists(mail);
	}
	
	public static HashMap<String, String> editAccount(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		// TODO Auto-generated method stub
		return null;
	}

	public static HashMap<String, String> editPassword(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		String result = "Error";
		String password = DAOParamUtil.getString(resourceRequest, "password");
		String verify = DAOParamUtil.getString(resourceRequest, "verify");
		String uid = DAOParamUtil.getString(resourceRequest, "uid");

//		boolean valid = validatePasswordFields(
//				actionRequest, password, verify, uid);

		return null;
	}

	public static Account getAccountFromPortalUser(RenderRequest request, User user)
			throws Exception {

		List<Account> accounts = null;

		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(Filter.mail,user.getEmailAddress(),false));

		accounts = Search.getAccounts(filters, null, StringPool.BLANK, StringPool.BLANK);

		if (accounts.size() == 0) {
			return null;
		} else if (!(accounts.size() == 1)) {
			//TODO: Create custom exception
			throw new Exception();
		}

		// Set session attribute to save on calls to LDAP later

		request.getPortletSession().setAttribute(
				"callingAccount", accounts.get(0), PortletSession.APPLICATION_SCOPE);

		return accounts.get(0);
	}

	public static Account getAccountFromRequest(RenderRequest request) {
		UserSearchTerms searchTerms = new UserSearchTerms(request);
		Account account = new Account();
		List<Account> accounts = new ArrayList<Account>();

		accounts = Search.getAccounts(
				searchTerms, StringPool.BLANK, StringPool.BLANK);

		if (accounts.size() == 1) {
			account = accounts.get(0);
		} else if (accounts.size() == 0) {
			account = null;
		} else if (accounts.size() > 1) {
			//Exception!
		}

		return account;
	}

	public static List<Group> getGroupsByAccount(Account account) {
		List<Group> groups = null;

		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(
				Filter.member,account.getAttribute("dn"),false));

		groups = Search.getGroups(filters, null, StringPool.BLANK, StringPool.BLANK, false);

		return groups;
	}
	
	public static HashMap<String, String> processAccountName(String firstName,
			String lastName) {				
		HashMap<String,String> responses = new HashMap<String,String>();
		// Make sure to lower case the names.
		// TODO: Issue 49 - Learn how to get primary email domain for the group.
		
		if (Validator.isNotNull(firstName) && Validator.isNotNull(lastName)) {
			responses.put(UserDisplayTerms.EMAIL_ADDRESS, 
					firstName + StringPool.PERIOD + lastName);
			responses.put(UserDisplayTerms.SCREEN_NAME, 
					firstName + StringPool.PERIOD + lastName);
		}
		
		return responses;
	}

	private static boolean validatePasswordFields(
			ActionRequest actionRequest, String password,
			String verify, String uid) {
		boolean result = false;

		while (true) {
			if (Validator.equals(password, verify)) {
				result = true;
			} else {
				SessionErrors.add(actionRequest, "password-fields-must-match");
				break;
			}
			if ((Validator.isNotNull(password)) && (Validator.isNotNull(verify))) {
				result = true;
			} else {
				SessionErrors.add(actionRequest, "please-enter-all-required-fields");
				break;
			}
			if (Validator.isNotNull(uid)) {
				result = true;
			} else {
				SessionErrors.add(actionRequest, "invalid-use-detected");
				break;
			}
		}

		return result;
	}

	// General
	public static final String ACCOUNTS = "accounts";

	public static final String DEFINE_PERMISSIONS = "define-permissions";

	public static final String ORGANIZATIONS = "organizations";

	public static final String PERMISSIONS = "permissions";

	public static final String SEARCH_TABS_NAMES = "accounts,organizations";

	// Directories
	public static final String SEARCH_TABS_JSP_DIRECTORY = "/html/portlet/search/tabs";

	public static final String PORTLET_SEARCH_DIRECTORY = "/html/portlet/search";

	public static final String PORTLET_ACCT_MGMT_DIRECTORY =
			"/html/portlet/account-management";

	// JSPs
	public static final String ACCT_MGMT_TOOLBAR_JSP =
			PORTLET_ACCT_MGMT_DIRECTORY + "/toolbar.jsp";

	public static final String ACCT_MGMT_ACCOUNT_ADMIN_ACTIONS_JSP =
			PORTLET_ACCT_MGMT_DIRECTORY + "/account/admin_actions.jsp";

	public static final String ACCT_MGMT_ACCOUNT_ADD_WIZARD_JSP =
			PORTLET_ACCT_MGMT_DIRECTORY + "/account/add_wizard.jsp";
	
	public static final String ACCT_MGMT_ACCOUNT_ADD_WIZARD_FORM_NAVIGATION_JSP =
			PORTLET_ACCT_MGMT_DIRECTORY + "/account/add_wizard/form_navigation.jsp";

	public static final String ACCT_MGMT_ACCOUNT_CHANGE_PASSWORD_JSP =
			PORTLET_ACCT_MGMT_DIRECTORY + "/account/change_password.jsp";

	public static final String ACCT_MGMT_ACCOUNT_EDIT_JSP =
			PORTLET_ACCT_MGMT_DIRECTORY + "/account/edit.jsp";

	public final static String ACCT_MGMT_ACCOUNT_EDIT_ACCOUNT_JSP =
			PORTLET_ACCT_MGMT_DIRECTORY + "/account/edit_account.jsp";

	public static final String ACCT_MGMT_ORGANIZATION_EDIT_JSP =
			PORTLET_ACCT_MGMT_DIRECTORY + "/organization/edit.jsp";

	public static final String ACCT_MGMT_DEFINE_PERMISSIONS_JSP =
			PORTLET_ACCT_MGMT_DIRECTORY + "/define_permissions.jsp";

	public static final String SEARCH_ACCOUNT_DETAILS_JSP =
			PORTLET_SEARCH_DIRECTORY + "/account/details.jsp";

	public static final String SEARCH_ACCOUNTS_JSP =
			PORTLET_SEARCH_DIRECTORY + "/accounts.jsp";

	public static final String SEARCH_ORGANIZATIONS_JSP =
			PORTLET_SEARCH_DIRECTORY + "/organizations.jsp";

	public static final String SEARCH_TABS_ACCOUNTS_JSP =
			SEARCH_TABS_JSP_DIRECTORY + "/accounts.jsp";

	public static final String SEARCH_TABS_ORGANIZATIONS_JSP =
			SEARCH_TABS_JSP_DIRECTORY + "/organizations.jsp";

	public static final String SEARCH_VIEW_ACCOUNT_JSP =
			PORTLET_SEARCH_DIRECTORY + "/view_account.jsp";

	public static final String SEARCH_VIEW_JSP = PORTLET_SEARCH_DIRECTORY + "/view.jsp";

	public static final String TABS_JSP = PORTLET_SEARCH_DIRECTORY + "/tabs1.jsp";

	// Images
	public static final String STOCK_AVATAR = "/images/user_male_portrait.png";


}