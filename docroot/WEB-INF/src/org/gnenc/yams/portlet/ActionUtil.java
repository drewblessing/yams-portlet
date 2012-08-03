package org.gnenc.yams.portlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.portlet.ActionRequest;
import javax.xml.bind.ValidationException;

import org.apache.commons.lang.mutable.MutableInt;
import org.gnenc.yams.model.Account;
import org.gnenc.yams.model.AccountType;
import org.gnenc.yams.model.Permissions;
import org.gnenc.yams.model.PermissionsDefined;
import org.gnenc.yams.model.SearchFilter;
import org.gnenc.yams.model.SubSystem;
import org.gnenc.yams.model.SearchFilter.Filter;
import org.gnenc.yams.portlet.search.UserDisplayTerms;
import org.gnenc.yams.portlet.util.PermissionsChecker;
import org.gnenc.yams.portlet.util.PermissionsUtil;
import org.gnenc.yams.portlet.util.UnknownImportAccountsHeaderException;
import org.gnenc.yams.service.AccountManagementService;
import org.gnenc.yams.service.PermissionsDefinedLocalServiceUtil;
import org.gnenc.yams.service.PermissionsLocalServiceUtil;
import org.gnenc.yams.service.impl.AccountManagementServiceImpl;

import au.com.bytecode.opencsv.CSVReader;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

/**
 * 
 * @author Drew A. Blessing
 *
 */
public class ActionUtil {
	
	private static Account accountFromCSVLine(String[] line) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static Account accountFromRequest(ActionRequest request) {
		Account account = new Account();
		
		switch (ParamUtil.getInteger(request, UserDisplayTerms.CMD)) {
			case AccountManagement.ADD_ACCOUNT_CMD:
				account.setAttribute("esuccAccountType", 
						ParamUtil.getString(request, UserDisplayTerms.ACCOUNT_TYPE));
				account.setCn(cnFromRequest(request));
				account.setDisplayName(account.getCn().get(0));
				account.setGivenName(
						ParamUtil.getString(request, UserDisplayTerms.FIRST_NAME));
				account.setSn(ParamUtil.getString(request, UserDisplayTerms.LAST_NAME));
				account.getMail().add(ParamUtil.getString(request, UserDisplayTerms.EMAIL_ADDRESS) + 
						StringPool.AT + ParamUtil.getString(request, UserDisplayTerms.DOMAIN));
				account.setAttribute("esuccEntity", 
						ParamUtil.getString(request, UserDisplayTerms.PRIMARY_GROUP));
				account.setAttribute("esuccMailPrimaryLocalPart", 
						ParamUtil.getString(request, UserDisplayTerms.EMAIL_ADDRESS));
				account.setAttribute("esuccMailPrimaryDomain", 
						ParamUtil.getString(request, UserDisplayTerms.DOMAIN));
				account.setAttribute(UserDisplayTerms.TITLE, 
						ParamUtil.getString(request, UserDisplayTerms.TITLE));
				account.setUid(ParamUtil.getString(request, UserDisplayTerms.EMAIL_ADDRESS));
				account.setAttribute(UserDisplayTerms.SCREEN_NAME, 
						ParamUtil.getString(request, UserDisplayTerms.SCREEN_NAME));
				
				break;
			case AccountManagement.EDIT_ACCOUNT_CMD:
				account.setCn(cnFromRequest(request));
				account.setDisplayName(account.getCn().get(0));
				account.setGivenName(
						ParamUtil.getString(request, UserDisplayTerms.FIRST_NAME));
				account.setSn(ParamUtil.getString(request, UserDisplayTerms.LAST_NAME));
				account.setAttribute("esuccEntity", 
						ParamUtil.getString(request, UserDisplayTerms.ESUCC_ENTITY));
				account.setAttribute(UserDisplayTerms.TITLE, 
						ParamUtil.getString(request, UserDisplayTerms.TITLE));
				account.setAttribute(UserDisplayTerms.SCREEN_NAME, 
						ParamUtil.getString(request, UserDisplayTerms.SCREEN_NAME));
				account.setAttribute("uidNumber", ParamUtil.getString(request, "uidNumber"));
				
				break;
		}
		
		return account;		
	}

	public static Account accountFromUidNumber(String uidNumber) {
		Account account = new Account();
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(
				Filter.uidNumber, uidNumber, false));
		
		List<Account> accounts = Search.getAccounts(
				filters, null, StringPool.BLANK, StringPool.BLANK, false, AccountType.ALL);
		
		if (accounts.size() == 1) {
			account = accounts.get(0);
		} else {
			account = null;
			// Bad news
		}

		return account;
	}
	
	public static Account accountFromEmailAddress(String emailAddress) {
		Account account = new Account();
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(
				Filter.mail, emailAddress, false));
		
		List<Account> accounts = Search.getAccounts(
				filters, null, StringPool.BLANK, StringPool.BLANK, false, AccountType.ALL);
		
		if (accounts.size() == 1) {
			account = accounts.get(0);
		} else {
			account = null;
			// Bad news
		}

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

	public static List<String> permissionsFromRequest(
			ActionRequest actionRequest) {
		List<PermissionsDefined> permissionsDefineds = null;
		try {
			permissionsDefineds = PermissionsDefinedLocalServiceUtil.getPermissionsDefineds(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> permissions = new ArrayList<String>();
		
		for (PermissionsDefined permissionsDefined : permissionsDefineds) {
			
			String permissionValue = ParamUtil.getString(actionRequest, permissionsDefined.getPermissionKey());
			
			System.out.println("permission value: " + permissionValue);
			
			if (Validator.isNotNull(permissionValue) && !permissionValue.equals("false")) {
				permissions.add(permissionsDefined.getPermissionKey());
			}
		}
		
		return permissions;
	}
	
	public static TreeMap<String, String> groupPermissionsGrantableAndAccountPermissionsToMap(
			Account callingAccount, Account account, String group) {
		
		TreeMap<String, String> map = new TreeMap<String, String>();
		List<Permissions> permissions = new ArrayList<Permissions>();
		String fqgn = PermissionsUtil.getFqgnFromDn(group);
		
		System.out.println("Calling account: " + callingAccount.getDisplayName());
		System.out.println("!!!Group: " + group);
		
		List<String> permissionsGrantable = 
				PermissionsChecker.getGroupPermissionsGrantable(callingAccount, group);
		long callingAccountDecimalPermissionsGrantable = 
				PermissionsUtil.permissionsToDecimal(permissionsGrantable);

		try {
			permissions = PermissionsLocalServiceUtil
					.getByEmailAddressAndFqgnAndGroupPermission(
							account.getMail().get(0), fqgn, false);
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long accountDecimalPermissions = permissions.size() == 1 ? permissions.get(0).getPermissions() : 0;
		
		if (callingAccountDecimalPermissionsGrantable >= accountDecimalPermissions) {
			
			for (String permissionGrantable : permissionsGrantable) {
				boolean hasPermission = 
						PermissionsChecker.hasGroupPermission(account, permissionGrantable, fqgn);
				if (hasPermission) {
					map.put(permissionGrantable, "true");
					System.out.println("True story" + permissionGrantable);
				} else {
					map.put(permissionGrantable, "false");
					System.out.println("Not a true story" + permissionGrantable);
				}
			}
		}
						
		return map;
		
	}
	
	public static List<String[]> parseCSVForPreview(File file) throws FileNotFoundException,
			UnknownImportAccountsHeaderException {
		CSVReader csvReader = new CSVReader(new FileReader(file));
		List<String[]> preview = new ArrayList<String[]>();
		String[] line;
		TreeMap<Integer, String> headers = new TreeMap<Integer, String>();
		String[] possibleHeaderValues = AccountManagement.possibleCSVHeaderValues;
		
		try {
			int i = 0;
			while((line = csvReader.readNext()) != null && i<=11) {

				// Get the header values
				
				if (i == 0) {					
					int j = 0;
					for (String headerValue : line) {
						boolean matched = false;
						for (String possibleHeaderValue : possibleHeaderValues) {
							
							if (headerValue.equalsIgnoreCase(possibleHeaderValue)) {
								headers.put(j, possibleHeaderValue);
								matched = true;
							}
						}
						if (!matched) { 
							throw new UnknownImportAccountsHeaderException(headerValue);
						}
						j++;
					}
				}
				preview.add(line);
				i++; 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return preview;
	}

	public static List<String[]> parseCSV(File file, MutableInt count) throws FileNotFoundException, 
			UnknownImportAccountsHeaderException {
		CSVReader csvReader = new CSVReader(new FileReader(file));
		List<String[]> failedImports = new ArrayList<String[]>();
		String[] line;
		TreeMap<Integer, String> headers = new TreeMap<Integer, String>();
		String[] possibleHeaderValues = AccountManagement.possibleCSVHeaderValues;
		
		try {
			count.setValue(0);
			while((line = csvReader.readNext()) != null) {

				// Get the header values
				
				if (count.equals(0)) {					
					int i = 0;
					for (String headerValue : line) {
						boolean matched = false;
						for (String possibleHeaderValue : possibleHeaderValues) {
							
							if (headerValue.equalsIgnoreCase(possibleHeaderValue)) {
								headers.put(i, possibleHeaderValue);
								matched = true;
							}
						}
						if (!matched) { 
							throw new UnknownImportAccountsHeaderException(headerValue);
						}
						i++;
					}
				}
				
				// Get the actual data now
				
//				Account account = ActionUtil.accountFromCSVLine(line);
//				Account newAccount = null;
//				AccountManagementService ams = AccountManagementServiceImpl.getInstance();
//				List<SubSystem> subsystems = new ArrayList<SubSystem>();
//				
//				subsystems.add(SubSystem.LDAP);
//				try {
//					newAccount = ams.createAccount(account, subsystems);
//				} catch (ValidationException e) {
//					e.printStackTrace();
//				} catch (IllegalArgumentException e) {
//					// That's Ok
//				}
		
				count.increment(); 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return failedImports;
	}	
}
