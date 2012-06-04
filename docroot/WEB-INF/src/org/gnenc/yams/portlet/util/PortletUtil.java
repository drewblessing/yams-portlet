package org.gnenc.yams.portlet.util;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.portlet.Search;
import org.gnenc.yams.portlet.search.UserSearchTerms;

import com.liferay.portal.kernel.dao.search.DAOParamUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class PortletUtil {
	public static void changePassword(
			ActionRequest actionRequest, ActionResponse actionResponse) {
		String password = DAOParamUtil.getString(actionRequest, "password");
		String verify = DAOParamUtil.getString(actionRequest, "verify");
		String uid = DAOParamUtil.getString(actionRequest, "uid");
		
		boolean valid = validatePasswordFields(
				actionRequest, password, verify, uid);
		
		if (valid) {
			// TODO: Change password
		} else {
			actionResponse.setRenderParameter("jspPage", CHANGE_PASSWORD_JSP);
		} 
		
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
