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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;

import javax.portlet.ActionRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.xml.bind.ValidationException;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.model.AccountType;
import org.gnenc.yams.model.EntityGroup;
import org.gnenc.yams.model.Group;
import org.gnenc.yams.model.PermissionsDefined;
import org.gnenc.yams.model.SearchFilter;
import org.gnenc.yams.model.SearchFilter.Filter;
import org.gnenc.yams.model.SubSystem;
import org.gnenc.yams.portlet.Search;
import org.gnenc.yams.portlet.search.UserDisplayTerms;
import org.gnenc.yams.portlet.search.UserSearchTerms;
import org.gnenc.yams.service.AccountManagementService;
import org.gnenc.yams.service.PermissionsDefinedLocalServiceUtil;
import org.gnenc.yams.service.impl.AccountManagementServiceImpl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;

import edu.vt.middleware.dictionary.ArrayWordList;
import edu.vt.middleware.dictionary.WordListDictionary;
import edu.vt.middleware.dictionary.WordLists;
import edu.vt.middleware.dictionary.sort.ArraysSort;
import edu.vt.middleware.password.AlphabeticalSequenceRule;
import edu.vt.middleware.password.CharacterCharacteristicsRule;
import edu.vt.middleware.password.DictionarySubstringRule;
import edu.vt.middleware.password.DigitCharacterRule;
import edu.vt.middleware.password.LengthRule;
import edu.vt.middleware.password.LowercaseCharacterRule;
import edu.vt.middleware.password.MessageResolver;
import edu.vt.middleware.password.NonAlphanumericCharacterRule;
import edu.vt.middleware.password.NumericalSequenceRule;
import edu.vt.middleware.password.Password;
import edu.vt.middleware.password.PasswordData;
import edu.vt.middleware.password.PasswordValidator;
import edu.vt.middleware.password.QwertySequenceRule;
import edu.vt.middleware.password.Rule;
import edu.vt.middleware.password.RuleResult;
import edu.vt.middleware.password.UppercaseCharacterRule;
import edu.vt.middleware.password.UsernameRule;
import edu.vt.middleware.password.WhitespaceRule;
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

	public static Account getAccountFromPortalUser(RenderRequest request, User user)
			throws Exception {

		List<Account> accounts = null;

		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(Filter.mail,user.getEmailAddress(),false));

		accounts = Search.getAccounts(filters, null, StringPool.BLANK, 
				StringPool.BLANK, false, AccountType.ALL);

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
	
	public static Account getAccountFromRequest(ActionRequest actionRequest) {
		UserSearchTerms searchTerms = new UserSearchTerms(actionRequest);
		
		return getAccountFromUserSearchTerms(searchTerms);
	}

	public static Account getAccountFromRequest(RenderRequest request) {
		UserSearchTerms searchTerms = new UserSearchTerms(request);
		
		return getAccountFromUserSearchTerms(searchTerms);
	}
	
	private static Account getAccountFromUserSearchTerms(UserSearchTerms searchTerms) {
		Account account = new Account();
		List<Account> accounts = new ArrayList<Account>();

		accounts = Search.getAccounts(
				searchTerms, StringPool.BLANK, StringPool.BLANK, false);

		if (accounts.size() == 1) {
			account = accounts.get(0);
		} else if (accounts.size() == 0) {
			account = null;

			System.out.println("Crap1");
		} else if (accounts.size() > 1) {
			//Exception!
			System.out.println("Crap2");
		}

		return account;
		
	}

	public static List<EntityGroup> getGroupsByAccount(Account account) {
		List<EntityGroup> groups = null;

		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(
				Filter.esuccEntity, account.getAttribute("esuccEntity"), false));

		groups = Search.getGroups(filters, null, StringPool.BLANK, StringPool.BLANK, false);

		return groups;
	}
	
	public static EntityGroup getGroupByCn(String groupCn) {
		List<EntityGroup> groups = new ArrayList<EntityGroup>();

		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(
				Filter.cn,groupCn,false));

		groups = Search.getGroups(filters, null, StringPool.BLANK, StringPool.BLANK, false);

		if (groups.size() > 1) {
			return null;
		} else if (groups.size() == 0) {
			return null;
		} 
				
		return groups.get(0);
	}
	
	public static List<PermissionsDefined> getPermissionsDefined() {
		try {
			return PermissionsDefinedLocalServiceUtil.getPermissionsDefineds(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	public static void processAccountName(String firstName,
			String lastName, String entity, String accountType, 
			TreeMap<String, String> responses) {
		
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(
				Filter.esuccEntity, entity, false));
		
		List<Group> groups = Search.getGroupsForAccountType(filters, null, 
				StringPool.BLANK, StringPool.BLANK, false);
		
		System.out.println("Groups: " + groups.size());
		
		filters.clear();
		
		StringBuilder domains = new StringBuilder();
		
		for (Group group : groups) {
			if (group.getAttribute("esuccGroupType").equals(accountType)) {
				List<String> seeAlsos = group.getSeeAlso();
			
				for (String seeAlso : seeAlsos) {
					String domain = seeAlso.substring(seeAlso.indexOf("o="));
					domain = domain.substring(2, domain.indexOf(","));
					
					domains.append(domain).append(StringPool.COMMA);
				}
			}
		}
		domains.delete(domains.length()-1, domains.length()); // Delete trailing comma
		
		responses.put("domains", domains.toString());
		
		
		if (Validator.isNotNull(firstName) && Validator.isNotNull(lastName)) {
			responses.put(UserDisplayTerms.EMAIL_ADDRESS, 
					firstName.toLowerCase() + 
						StringPool.PERIOD + lastName.toLowerCase());
			responses.put(UserDisplayTerms.SCREEN_NAME, 
					firstName.toLowerCase() + 
						StringPool.PERIOD + lastName.toLowerCase());
		}
		
	}

	public static void getEntityAccountTypes(String entity,
			TreeMap<String, String> responses) {
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(
				Filter.esuccEntity, entity, false));
		
		List<Group> groups = Search.getGroupsForAccountType(filters, null, 
				"asc", "name", false);
		
		System.out.println("Groups: " + groups.size());
		
		for (Group group : groups) {
			System.out.println("Group type: " + group.getAttribute("esuccGroupType"));
			responses.put(group.getAttribute("esuccGroupType"), 
					group.getAttribute("esuccGroupType"));
		}
	}
	
	public static boolean validateEmailAddressField(
			String emailAddress, List<String> responses) {
		boolean result = false;
		
		System.out.println("Email: " + emailAddress);
		
		if (Validator.isEmailAddress(emailAddress)) {
			result = true;
		} else {
			responses.add("Invalid email address");
		}
			
		return result;
	}

	public static boolean validatePasswordFields(
			String password, String verify, 
			String firstName, String lastName, 
			List<String> responses) {
		boolean result = false;
		
		while (true) {
			if (Validator.equals(password, verify)) {
				result = true;
			} else {
				responses.add("password-fields-must-match");
				break;
			}
			if ((Validator.isNotNull(password)) && (Validator.isNotNull(verify))) {
				result = true;
			} else {
				responses.add("please-enter-all-required-fields");
				break;
			}	
			break;
		}
		
		// Virginia Tech Password checking library - http://code.google.com/p/vt-middleware/
		
		LengthRule lengthRule = new LengthRule(8,64);
		WhitespaceRule whitespaceRule = new WhitespaceRule();
		
		// Restrict numerical sequences - like 1234.  False means do not wrap sequences - 8901 is OK
		NumericalSequenceRule numRule = new NumericalSequenceRule(4, false);
		AlphabeticalSequenceRule alphaRule = new AlphabeticalSequenceRule(4, false);
		QwertySequenceRule qwertyRule = new QwertySequenceRule(4, false);
		
		// Restrict usernames - true and true say match backwards and ignore case
		UsernameRule usernameRule = new UsernameRule(true, true);
		
		// Add minimum requirements for number of digits and lower case chars
		CharacterCharacteristicsRule charRule = new CharacterCharacteristicsRule();
		charRule.getRules().add(new DigitCharacterRule(1));
		charRule.getRules().add(new LowercaseCharacterRule(1));
		charRule.getRules().add(new UppercaseCharacterRule(1));
		charRule.getRules().add(new NonAlphanumericCharacterRule(1));
		charRule.setNumberOfCharacteristics(2);
		
		WordListDictionary dict1 = null;
		WordListDictionary dict2 = null;
		
		MessageResolver resolver = null;
		try {
			ArrayWordList awl1 = WordLists.createFromReader(
					new FileReader[] {
							new FileReader(PropsValues.JVM_DIR + "/webapps/yams-portlet/WEB-INF/classes/dict-webster2")
					}, 
					false, new ArraysSort());
			dict1 = new WordListDictionary(awl1);
			
			ArrayWordList awl2 = WordLists.createFromReader(
					new FileReader[] {
							new FileReader(PropsValues.JVM_DIR + "/webapps/yams-portlet/WEB-INF/classes/dict-propernames")
					}, 
					false, new ArraysSort());
			dict2 = new WordListDictionary(awl2);
			
			Properties props = new Properties();
			props.load(new FileInputStream(PropsValues.JVM_DIR + "/webapps/yams-portlet/WEB-INF/classes/vtpassword-messages.properties"));
			resolver = new MessageResolver(props);
		} catch (FileNotFoundException e) {
			responses.add("could-not-find-dictionary");
			result = false;
			e.printStackTrace();
		} catch (IOException e) {
			responses.add("could-not-find-dictionary");
			result = false;
			e.printStackTrace();
		}
		
		DictionarySubstringRule dictRule1 = new DictionarySubstringRule(dict1);
		dictRule1.setWordLength(5);
		dictRule1.setMatchBackwards(true);
		
		DictionarySubstringRule dictRule2 = new DictionarySubstringRule(dict2);
		dictRule2.setWordLength(5);
		dictRule2.setMatchBackwards(false);
		
		List<Rule> ruleList1 = new ArrayList<Rule>();
		ruleList1.add(lengthRule);
		ruleList1.add(whitespaceRule);
		ruleList1.add(alphaRule);
		ruleList1.add(numRule);
		ruleList1.add(qwertyRule);
		ruleList1.add(usernameRule);
		ruleList1.add(charRule);
		ruleList1.add(dictRule1);
		
		PasswordValidator validator = new PasswordValidator(resolver, ruleList1);
		PasswordData passwordData = new PasswordData(new Password(password));
		passwordData.setUsername(firstName);
		
		RuleResult rs1 = validator.validate(passwordData);
		if (!rs1.isValid()) {
			for (String msg : validator.getMessages(rs1)) {
				responses.add(msg);
				System.out.println(msg);
			}
			result = false;
		} else {
			result = true;
		}
		
		List<Rule> ruleList2 = new ArrayList<Rule>();
		ruleList2.add(usernameRule);
		
		passwordData.setUsername(lastName);
		
		RuleResult rs2 = validator.validate(passwordData);
		if (!rs2.isValid()) {
			for (String msg : validator.getMessages(rs2)) {
				//Only put the username failure messages on the stack if it's not there already
				if (!responses.contains(msg)){
					responses.add(msg);
					System.out.println(msg);
				}
			}
			result = false;
		} else {
			result = true;
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
	
	public final static String PORTLET_ACCT_MGMT_ACCOUNT_SECTIONS_DIRECTORY =
			PORTLET_ACCT_MGMT_DIRECTORY + "/account/sections/";
	
	public final static String PORTLET_ACCT_MGMT_PERMISSIONS_DIRECTORY = 
			PORTLET_ACCT_MGMT_DIRECTORY + "/account/permissions";

	// JSPs
	public static final String ACCT_MGMT_TOOLBAR_JSP =
			PORTLET_ACCT_MGMT_DIRECTORY + "/toolbar.jsp";

	public static final String ACCT_MGMT_ACCOUNT_ADMIN_ACTIONS_JSP =
			PORTLET_ACCT_MGMT_DIRECTORY + "/account/admin_actions.jsp";

	public static final String ACCT_MGMT_ACCOUNT_ADD_WIZARD_JSP =
			PORTLET_ACCT_MGMT_DIRECTORY + "/account/add_wizard.jsp";
	
	public static final String ACCT_MGMT_ACCOUNT_ADD_WIZARD_FORM_NAVIGATION_JSP =
			PORTLET_ACCT_MGMT_DIRECTORY + "/account/add_wizard/form_navigation.jsp";

	public static final String ACCT_MGMT_ACCOUNT_EDIT_JSP =
			PORTLET_ACCT_MGMT_DIRECTORY + "/account/edit.jsp";

	public final static String ACCT_MGMT_ACCOUNT_EDIT_ACCOUNT_JSP =
			PORTLET_ACCT_MGMT_DIRECTORY + "/account/edit_account.jsp";
	
	public final static String ACCT_MGMT_ACCOUNT_EDIT_FORWARD_JSP =
			PORTLET_ACCT_MGMT_DIRECTORY + "/account/edit_forward.jsp";
	
	public final static String ACCT_MGMT_ACCOUNT_EDIT_FORWARD_FORM_JSP =
			PORTLET_ACCT_MGMT_DIRECTORY + "/account/edit_forward_form.jsp";

	public final static String ACCT_MGMT_ACCOUNT_EDIT_FORWARD_OTHER_PROVIDER_JSP =
			PORTLET_ACCT_MGMT_DIRECTORY + "/account/edit_forward_other_provider.jsp";
	
	public static final String ACCT_MGMT_ACCOUNT_EDIT_PASSWORD_JSP =
			PORTLET_ACCT_MGMT_DIRECTORY + "/account/edit_password.jsp";
	
	public static final String ACCT_MGMT_ACCOUNT_IMPORT_ACCOUNTS_VIEW_JSP = 
			PORTLET_ACCT_MGMT_DIRECTORY + "/account/import_accounts/view.jsp";
	
	public static final String ACCT_MGMT_ACCOUNT_IMPORT_ACCOUNTS_PREVIEW_JSP = 
			PORTLET_ACCT_MGMT_DIRECTORY + "/account/import_accounts/preview.jsp";
	
	public static final String ACCT_MGMT_ACCOUNT_IMPORT_ACCOUNTS_SUMMARY_JSP = 
			PORTLET_ACCT_MGMT_DIRECTORY + "/account/import_accounts/summary.jsp";
	
	public final static String ACCT_MGMT_ACCOUNT_PASSWORD_POLICY_TEXT_JSP =
			PORTLET_ACCT_MGMT_DIRECTORY + "/account/password_policy_text.jsp";
	
	public final static String ACCT_MGMT_ACCOUNT_PERMISSIONS_CHOOSE_GROUP_JSP =
			PORTLET_ACCT_MGMT_PERMISSIONS_DIRECTORY + "/choose_group.jsp";
	
	public final static String ACCT_MGMT_ACCOUNT_PERMISSIONS_CHOOSE_PERMISSIONS_JSP =
			PORTLET_ACCT_MGMT_PERMISSIONS_DIRECTORY + "/choose_permissions.jsp";

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