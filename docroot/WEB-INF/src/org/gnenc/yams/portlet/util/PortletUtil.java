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
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.portlet.Search;
import org.gnenc.yams.portlet.search.UserSearchTerms;

import com.liferay.portal.kernel.dao.search.DAOParamUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

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
	
	public static String STOCK_AVATAR = "/images/user_male_portrait.png";
	private static String CHANGE_PASSWORD_JSP = 
			"/html/portlet/account-management/account/change_password.jsp";
}
