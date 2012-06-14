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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.naming.Name;
import javax.portlet.ActionRequest;
import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.model.Permissions;
import org.gnenc.yams.portlet.Search;
import org.gnenc.yams.portlet.search.UserSearchTerms;
import org.gnenc.yams.service.PermissionsDefinedLocalServiceUtil;
import org.gnenc.yams.service.PermissionsLocalServiceUtil;

import com.liferay.portal.kernel.dao.search.DAOParamUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;

public class PortletUtil {	
	public static String editAccount(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static String editPassword(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		String result = "Error";
		String password = DAOParamUtil.getString(resourceRequest, "password");
		String verify = DAOParamUtil.getString(resourceRequest, "verify");
		String uid = DAOParamUtil.getString(resourceRequest, "uid");
		
//		boolean valid = validatePasswordFields(
//				actionRequest, password, verify, uid);
		
		return result;
	}
	
	public static Account getAccountFromPortalUser(RenderRequest request, User user) 
			throws Exception {
		List<Account> accounts = null;
		
		request.setAttribute("emailAddress", user.getEmailAddress());
		UserSearchTerms searchTerms = new UserSearchTerms(request);
		
		accounts = Search.getAccounts(searchTerms, StringPool.BLANK, StringPool.BLANK);
		
		if (!(accounts.size() == 1)) {
			//TODO: Create custom exception
			throw new Exception();
		}
		
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
	
	private static String getBinaryPermissions(
			Account callingAccount, Account account, String fqgn, boolean group) {
		long decimal = 0;
		boolean selfCheck = false;
		if ((Validator.isNull(fqgn)) && (callingAccount.equals(account))) {
			selfCheck = true;
		} 
		if (Validator.isNull(fqgn)) {
			fqgn = getFqgnFromDn(account.getAttribute("dn"));
		} 
		List<String> fqgnLevels = getFqgnLevels(fqgn);
		List<Permissions> results = new ArrayList<Permissions>();
		
		for (String fqgnLevel : fqgnLevels) {
			try {
				// Check group's self permissions at lowest level only
				if (selfCheck) {
					results = PermissionsLocalServiceUtil.
							getByFqgnAndGroupPermission(
									fqgnLevel, true);
					if (results.size() == 1) {
						decimal = results.get(0).getPermissions();
						selfCheck = false;
					}
				} 
				results = PermissionsLocalServiceUtil.
						getByEmailAddressAndFqgnAndGroupPermission(
								callingAccount.getMail().get(0), fqgnLevel, false);
						
				if (results.size() == 1) {
					decimal = decimal | results.get(0).getPermissions();
				}
			} catch (SystemException e) {
				e.printStackTrace();
			}
		}
		System.out.println(decimal);
		String binaryPermissions = new StringBuffer(
				Long.toBinaryString(decimal)).reverse().toString();
		System.out.println(binaryPermissions);
		return binaryPermissions;
	}
	
	private static String getFqgnFromDn(String dn) {
		String fqgn = "";
		List<String> dnAttributes = new ArrayList<String>();
		
		dnAttributes.add("uid=");
		dnAttributes.add("ou=");
		dnAttributes.add("o=");
		dnAttributes.add("dc=");
		dnAttributes.add("cn=");
		
		// Strip past first comma
		
		int index = dn.indexOf(StringPool.COMMA);
		dn = dn.substring(index+1);
		
		// Strip DN attributes
		
		for (String attr : dnAttributes) {
			dn = dn.replace(attr, StringPool.BLANK);
		}
		
		// Change periods to underscores
		
		dn = dn.replace(StringPool.PERIOD, StringPool.UNDERLINE);
		
		// Reverse order for fqgn
		
		List<String> tokens = Arrays.asList(dn.split(StringPool.COMMA));
		Collections.reverse(tokens);
		for (int i=0; i<tokens.size(); i++) {
			if (i == tokens.size()-1) {
				fqgn += tokens.get(i);
			} else {
				fqgn += tokens.get(i) + StringPool.PERIOD;
			}
		}
		
		return fqgn;
	}
	
	private static List<String> getFqgnLevels(String fqgn) {
		List<String> fqgnList = new ArrayList<String>();
		String[] levels = fqgn.split("\\.");
		
		
		for (int i=0; i<levels.length; i++) {
			if (i == 0) {
				fqgnList.add(levels[i]);
			} else {
				fqgnList.add(fqgnList.get(i-1) + "." + levels[i]);
			}
		}
		Collections.reverse(fqgnList);
		
		return fqgnList;
	}
	
	public static boolean hasGroupPermission(Account account, String permission, String fqgn) {
		return hasPermission(account, null, permission, fqgn);
	}
	
	public static boolean hasPermission(Account callingAccount, Account account, String permission) {
		return hasPermission(callingAccount, account, permission, null);
	}
	
	private static boolean hasPermission(
			Account callingAccount, Account account, String permission, String fqgn) {
		String binaryPermissions;
		if (permission.contains("self")) {
			binaryPermissions = getBinaryPermissions(callingAccount, account, null, true);
		} else {
			binaryPermissions = getBinaryPermissions(callingAccount, account, fqgn, false);
		}
		int permissionBit = 0;
		
		try {
			permissionBit = PermissionsDefinedLocalServiceUtil.
					getPermissionsDefined(permission).getBitLocation();
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if (binaryPermissions.charAt(permissionBit) == '1') {
				System.out.println(permissionBit);
				return true;
			} else {
				return false;
			}
		} catch (StringIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	private static boolean validatePasswordFields(
			ActionRequest actionRequest, String password,
			String verify, String uid) {
		boolean result = false;
		
		while (true) {
			if (Validator.equals(password,verify)) {
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
	public final static String ACCOUNTS = "accounts";
	
	public final static String DEFINE_PERMISSIONS = "define-permissions";

	public final static String ORGANIZATIONS = "organizations";
	
	public final static String PERMISSIONS = "permissions";
	
	public final static String SEARCH_TABS_NAMES = "accounts,organizations";
	
	// Directories	
	public final static String SEARCH_TABS_JSP_DIRECTORY = "/html/portlet/search/tabs";
	
	public final static String PORTLET_SEARCH_DIRECTORY = "/html/portlet/search";
	
	public final static String PORTLET_ACCT_MGMT_DIRECTORY = 
			"/html/portlet/account-management";
	
	// JSPs
	public final static String ACCT_MGMT_TOOLBAR_JSP = 
			PORTLET_ACCT_MGMT_DIRECTORY + "/toolbar.jsp";

	public final static String ACCT_MGMT_ACCOUNT_ADMIN_ACTIONS_JSP = 
			PORTLET_ACCT_MGMT_DIRECTORY + "/account/admin_actions.jsp";
	
	public final static String ACCT_MGMT_ACCOUNT_ADD_WIZARD_JSP = 
			PORTLET_ACCT_MGMT_DIRECTORY + "/account/add_wizard.jsp";
	
	public final static String ACCT_MGMT_ACCOUNT_CHANGE_PASSWORD_JSP = 
			PORTLET_ACCT_MGMT_DIRECTORY + "/account/change_password.jsp";
	
	public final static String ACCT_MGMT_ACCOUNT_EDIT_JSP = 
			PORTLET_ACCT_MGMT_DIRECTORY + "/account/edit.jsp";
	
	public final static String ACCT_MGMT_ACCOUNT_EDIT_ACCOUNT_JSP = 
			PORTLET_ACCT_MGMT_DIRECTORY + "/account/edit_account.jsp";
	
	public final static String ACCT_MGMT_ORGANIZATION_EDIT_JSP = 
			PORTLET_ACCT_MGMT_DIRECTORY + "/organization/edit.jsp";
	
	public final static String ACCT_MGMT_DEFINE_PERMISSIONS_JSP =
			PORTLET_ACCT_MGMT_DIRECTORY + "/define_permissions.jsp";
	
	public final static String SEARCH_ACCOUNT_DETAILS_JSP = 
			PORTLET_SEARCH_DIRECTORY + "/account/details.jsp";
	
	public final static String SEARCH_ACCOUNTS_JSP = 
			PORTLET_SEARCH_DIRECTORY + "/accounts.jsp";
	
	public final static String SEARCH_ORGANIZATIONS_JSP = 
			PORTLET_SEARCH_DIRECTORY + "/organizations.jsp";
	
	public final static String SEARCH_TABS_ACCOUNTS_JSP = 
			SEARCH_TABS_JSP_DIRECTORY + "/accounts.jsp";
	
	public final static String SEARCH_TABS_ORGANIZATIONS_JSP = 
			SEARCH_TABS_JSP_DIRECTORY + "/organizations.jsp";
	
	public final static String SEARCH_VIEW_ACCOUNT_JSP = 
			PORTLET_SEARCH_DIRECTORY + "/view_account.jsp";
	
	public final static String SEARCH_VIEW_JSP = PORTLET_SEARCH_DIRECTORY + "/view.jsp";
	
	public final static String TABS_JSP = PORTLET_SEARCH_DIRECTORY + "/tabs1.jsp";
	
	// Images	
	public final static String STOCK_AVATAR = "/images/user_male_portrait.png";
	
	// Permission Keys
	public final static String PERMISSION_ACCOUNT_ADD = "account_add";	
	
	public final static String PERMISSION_ACCOUNT_EDIT = "account_edit";
	
	public final static String PERMISSION_ACCOUNT_REMOVE = "account_remove";
	
	public final static String PERMISSION_ACCOUNT_REMOVE_FORCE = "account_remove_force";
	
	public final static String PERMISSION_GROUP_ADD = "group_add";
	
	public final static String PERMISSION_GROUP_EDIT = "group_edit";
	
	public final static String PERMISSION_GROUP_REMOVE = "group_remove";
	
	public final static String PERMISSION_SELF_EDIT_PASSWORD = "self_edit_password";	
	
}
