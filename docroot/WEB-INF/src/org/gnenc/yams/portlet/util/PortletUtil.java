package org.gnenc.yams.portlet.util;

import java.util.List;

import javax.portlet.RenderRequest;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.portlet.Search;
import org.gnenc.yams.portlet.search.UserSearchTerms;

import com.liferay.portal.kernel.util.StringPool;

public class PortletUtil {
	public static Account getAccountFromRequest(RenderRequest request) {
		UserSearchTerms searchTerms = new UserSearchTerms(request);
		
		List<Account> accounts = Search.getAccounts(
				searchTerms, StringPool.BLANK, StringPool.BLANK);
		if (accounts.size() > 1) {
			//Exception!
		} else if (accounts.size() == 0) {
			//Exception!
		}
		
		return accounts.get(0);
	}
	
	public static String STOCK_AVATAR = "/images/user_male_portrait.png";
}
