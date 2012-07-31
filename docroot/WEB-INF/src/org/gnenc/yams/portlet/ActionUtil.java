package org.gnenc.yams.portlet;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ResourceRequest;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.portlet.search.UserDisplayTerms;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.ListTypeServiceUtil;

/**
 * 
 * @author Drew A. Blessing
 *
 */
public class ActionUtil {
	
	public static Account accountFromRequest(ActionRequest request) {
		Account account = new Account();
		
		account.setCn(cnFromRequest(request));
		account.setDescription(null);
		account.setDisplayName(cnFromRequest(request).get(0));
		account.setEmployeeNumber(null);
		account.setGivenName(
				ParamUtil.getString(request, UserDisplayTerms.FIRST_NAME));
		account.setPassword(passwordFromRequest(request));
		account.setSn(ParamUtil.getString(request, UserDisplayTerms.LAST_NAME));
		account.setUid(ParamUtil.getString(request, UserDisplayTerms.SCREEN_NAME));
		
		// This won't work for existing accounts
		account.getMail().add(ParamUtil.getString(request, UserDisplayTerms.EMAIL_ADDRESS) + 
				StringPool.AT + ParamUtil.getString(request, UserDisplayTerms.DOMAIN));
		
		System.out.println("Domain: " + ParamUtil.getString(request, UserDisplayTerms.DOMAIN));
		System.out.println("Uid: " + ParamUtil.getString(request, UserDisplayTerms.SCREEN_NAME));
		
		account.setAttribute(UserDisplayTerms.TITLE, ParamUtil.getString(request, UserDisplayTerms.TITLE));
		account.setAttribute(UserDisplayTerms.PRIMARY_GROUP, ParamUtil.getString(request, UserDisplayTerms.PRIMARY_GROUP));
		
		return account;
	}

	public static List<String> cnFromRequest(ActionRequest request) {
		String firstName = ParamUtil.getString(request, UserDisplayTerms.FIRST_NAME);
		String lastName = ParamUtil.getString(request, UserDisplayTerms.LAST_NAME);
		List<String> cnList = new ArrayList<String>();
		
		cnList.add(firstName + StringPool.SPACE + lastName);
		
		return cnList;
	}
	
	private static String passwordFromRequest(ActionRequest request) {
		String password = ParamUtil.getString(request, UserDisplayTerms.PASSWORD);
		String verify = ParamUtil.getString(request, UserDisplayTerms.VERIFY);
		
		if (password.equals(verify)) {
			return password;
		} else {
			return null;
		}
	}
	
}
