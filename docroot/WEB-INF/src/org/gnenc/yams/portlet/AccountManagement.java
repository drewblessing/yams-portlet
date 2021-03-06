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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.xml.bind.ValidationException;

import org.apache.commons.lang.mutable.MutableInt;
import org.gnenc.yams.model.Account;
import org.gnenc.yams.model.Domain;
import org.gnenc.yams.model.EntityGroup;
import org.gnenc.yams.model.SearchFilter;
import org.gnenc.yams.model.SearchFilter.Filter;
import org.gnenc.yams.model.SearchFilter.Operand;
import org.gnenc.yams.model.SubSystem;
import org.gnenc.yams.portlet.search.UserDisplayTerms;
import org.gnenc.yams.portlet.search.util.SearchUtil;
import org.gnenc.yams.portlet.util.PermissionsChecker;
import org.gnenc.yams.portlet.util.PermissionsUtil;
import org.gnenc.yams.portlet.util.PortletUtil;
import org.gnenc.yams.portlet.util.PropsValues;
import org.gnenc.yams.portlet.util.UnknownImportAccountsHeaderException;
import org.gnenc.yams.service.AccountManagementService;
import org.gnenc.yams.service.ActionLogLocalServiceUtil;
import org.gnenc.yams.service.JobQueueLocalServiceUtil;
import org.gnenc.yams.service.PermissionsLocalServiceUtil;
import org.gnenc.yams.service.impl.AccountManagementServiceImpl;

import com.liferay.portal.kernel.dao.search.DAOParamUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.liferay.util.mail.MailEngine;
public class AccountManagement extends MVCPortlet {
	public void addAccount(
				ActionRequest actionRequest, ActionResponse actionResponse) {
		Account account = ActionUtil.accountFromRequest(actionRequest);
	    String backURL = ParamUtil.getString(actionRequest, "backURL");
		Account newAccount = null;
		String jspPage = null;
				
		AccountManagementService ams = AccountManagementServiceImpl.getInstance();
		List<SubSystem> subsystems = new ArrayList<SubSystem>();	
		
		List<String> responses = new ArrayList<String>(); 
		String password = DAOParamUtil.getString(actionRequest, "password");
		String verify = DAOParamUtil.getString(actionRequest, "verify");
		String emailAddress = DAOParamUtil.getString(actionRequest, "emailAddress") + 
				StringPool.AT + DAOParamUtil.getString(actionRequest, "domain");
		
		actionRequest.setAttribute("selAccount", account);
		actionResponse.setRenderParameter("backURL", backURL);
		
		boolean valid = PortletUtil.validatePasswordFields(password, verify, 
				account.getGivenName(), account.getSn(), responses);
		
		if (valid) {
			valid = PortletUtil.validateEmailAddressField(emailAddress, responses);
		}
		
		if (!valid && responses.size() > 0) {
			for(String response : responses) {
				SessionErrors.add(actionRequest, response);
			}
			
			jspPage = PortletUtil.ACCT_MGMT_ACCOUNT_ADD_WIZARD_JSP;
		} else {	
			account.setPassword(password);
			subsystems.add(SubSystem.LDAP);
				try {
					if (ams.checkAccountExists(account.getMail().get(0)).isEmpty()) {
						newAccount = ams.createAccount(account, subsystems);
						jspPage = PortletUtil.SEARCH_VIEW_JSP;
					} else {
						SessionErrors.add(actionRequest, "This account already exists");
						jspPage = PortletUtil.ACCT_MGMT_ACCOUNT_ADD_WIZARD_JSP;
					}
				} catch (ValidationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		writeActionLog(actionRequest, account.getMail().get(0), 
				account.getDisplayName(), account.getAttribute("esuccEntity"), 
				"Add account");
		actionResponse.setRenderParameter("jspPage", jspPage);
	}
	
	public void editAccount(
			ActionRequest actionRequest, ActionResponse actionResponse) {
		Account account = ActionUtil.accountFromRequest(actionRequest);
	    String backURL = ParamUtil.getString(actionRequest, "backURL");
		Account newAccount = null;
		String jspPage = null;
		
		AccountManagementService ams = AccountManagementServiceImpl.getInstance();
		List<SubSystem> subsystems = new ArrayList<SubSystem>();	
		
		List<String> responses = new ArrayList<String>(); 
		String password = DAOParamUtil.getString(actionRequest, "password_field");
		String verify = DAOParamUtil.getString(actionRequest, "verify");
		boolean valid = false;
		
		// If the password fields aren't populated, don't change the user's password
		if (Validator.isNotNull(password) && Validator.isNotNull(verify)) {
			valid = PortletUtil.validatePasswordFields(password, verify, 
					account.getGivenName(), account.getSn(), responses);
			account.setPassword(password);
		} else {
			valid = true;
		}
		
		actionResponse.setRenderParameter("uidNumber", account.getAttribute("uidNumber"));
		actionResponse.setRenderParameter("backURL", backURL);
		
		if (!valid && responses.size() > 0) {
			for(String response : responses) {
				SessionErrors.add(actionRequest, response);
			}
			
			jspPage = PortletUtil.ACCT_MGMT_ACCOUNT_EDIT_ACCOUNT_JSP;
		} else {	
			subsystems.add(SubSystem.LDAP);
			try {
				newAccount = ams.modifyAccount(account, subsystems);
			} catch (ValidationException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// That's Ok
			}
			jspPage = PortletUtil.SEARCH_VIEW_JSP;
		}
		
		writeActionLog(actionRequest, account.getMail().get(0), 
				account.getDisplayName(), account.getAttribute("esuccEntity"), 
				"Edit account");
		actionResponse.setRenderParameter("jspPage", jspPage);
	}
	
	public static void editForward(
			ActionRequest actionRequest, ActionResponse actionResponse) {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
	            WebKeys.THEME_DISPLAY);
		Account callingAccount =  null;
		try {
			callingAccount = ActionUtil.accountFromEmailAddress(themeDisplay.getUser().getEmailAddress());
		} catch (Exception e1) {
			e1.printStackTrace();	
		}
		AccountManagementService ams = AccountManagementServiceImpl.getInstance();
		String jspPage = null;
		boolean valid = false; 
	    String backURL = DAOParamUtil.getString(actionRequest, "backURL");
	    boolean delete = DAOParamUtil.getBoolean(actionRequest, "delete");
		String forward = DAOParamUtil.getString(actionRequest, "forward");
		String verify = DAOParamUtil.getString(actionRequest, "verify_forward");
		Account account = ActionUtil.accountFromUidNumber(
				DAOParamUtil.getString(actionRequest, "uidNumber"));
		
		actionResponse.setRenderParameter("uidNumber", account.getAttribute("uidNumber"));
		actionResponse.setRenderParameter("backURL", backURL);
		
		if (PermissionsChecker.hasPermission(
				callingAccount, account, PermissionsChecker.PERMISSION_ACCOUNT_FORWARD)) {
			try {
				ams.modifyEmailForward(account, forward, delete);
			} catch (ValidationException e) {
				SessionErrors.add(actionRequest, "password-change-failed");
				
				jspPage = PortletUtil.ACCT_MGMT_ACCOUNT_EDIT_FORWARD_JSP;
			}

			jspPage = PortletUtil.SEARCH_VIEW_JSP;
		} else {
			SessionErrors.add(actionRequest, "insufficient-privileges");
			
			jspPage = PortletUtil.ACCT_MGMT_ACCOUNT_EDIT_FORWARD_JSP;
		}
			
		writeActionLog(actionRequest, account.getMail().get(0), 
				account.getDisplayName(), account.getAttribute("esuccEntity"), 
				"Edit forward");
		actionResponse.setRenderParameter("jspPage", jspPage);
	}
	
	public static void editPassword(
			ActionRequest actionRequest, ActionResponse actionResponse) {
		AccountManagementService ams = AccountManagementServiceImpl.getInstance();
		String jspPage = null;
		List<String> responses = new ArrayList<String>(); 
	    String backURL = DAOParamUtil.getString(actionRequest, "backURL");
		String password = DAOParamUtil.getString(actionRequest, "password");
		String verify = DAOParamUtil.getString(actionRequest, "verify");
		Account account = ActionUtil.accountFromUidNumber(
				DAOParamUtil.getString(actionRequest, "uidNumber"));
		
		boolean valid = PortletUtil.validatePasswordFields(password, verify, 
				account.getGivenName(), account.getSn(), responses);
		
		actionResponse.setRenderParameter("uidNumber", account.getAttribute("uidNumber"));
		actionResponse.setRenderParameter("backURL", backURL);
		
		if (!valid && responses.size() > 0) {
			for(String response : responses) {
				SessionErrors.add(actionRequest, response);
			}
			
			jspPage = PortletUtil.ACCT_MGMT_ACCOUNT_EDIT_PASSWORD_JSP;
		} else {
			try {
				ams.changePassword(account, password);
			} catch (ValidationException e) {
				SessionErrors.add(actionRequest, "password-change-failed");
				
				jspPage = PortletUtil.ACCT_MGMT_ACCOUNT_EDIT_PASSWORD_JSP;
			}
			
			jspPage = PortletUtil.SEARCH_VIEW_JSP;
		}
		
		writeActionLog(actionRequest, account.getMail().get(0), 
				account.getDisplayName(), account.getAttribute("esuccEntity"), 
				"Edit password");
		actionResponse.setRenderParameter("jspPage", jspPage);
	}
	
	public void editPermissions(
				ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortalException, SystemException {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
				WebKeys.THEME_DISPLAY);
		
		List<String> permissions = ActionUtil
				.permissionsFromRequest(actionRequest);
		List<String> permissionsGrantable = new ArrayList<String>();
		long decimalPermissions = PermissionsUtil
				.permissionsToDecimal(permissions);
		long decimalPermissionsGrantable = PermissionsUtil
				.permissionsToDecimal(permissionsGrantable);
		
		long permissionsId = ParamUtil.getLong(
				actionRequest, "permissionsId");
		Account account = ActionUtil.accountFromUidNumber(
					DAOParamUtil.getString(
							actionRequest, "uidNumber"));
		
		String emailAddress = account.getMail().get(0);
		String fqgn = PermissionsUtil.getFqgnFromDn(ParamUtil.getString(
				actionRequest, "group"));
		boolean groupPermission = false;
		
		if (permissionsId > 0) {
			PermissionsLocalServiceUtil.updatePermissions(
					permissionsId, themeDisplay.getUserId(), 
					decimalPermissions, decimalPermissionsGrantable);
		} else {
			PermissionsLocalServiceUtil.addPermissions(
					themeDisplay.getUserId(), emailAddress, 
					fqgn, groupPermission, decimalPermissions, 
					decimalPermissionsGrantable);
		}
		
		writeActionLog(actionRequest, account.getMail().get(0), 
				account.getDisplayName(), account.getAttribute("esuccEntity"), 
				"Edit permissions");
	}
	
	public void editPermissionsChooseGroup(
			ActionRequest actionRequest, ActionResponse actionResponse) {		
		Account account = ActionUtil.accountFromUidNumber(
					DAOParamUtil.getString(actionRequest, "uidNumber"));
		
		String cancelURL = ParamUtil.getString(actionRequest, "cancelURL");
		
		Account callingAccount = ActionUtil.accountFromUidNumber(
				DAOParamUtil.getString(
						actionRequest, "callingAccount"));
		
		String group = DAOParamUtil.getString(
				actionRequest, UserDisplayTerms.GROUP);
		
		TreeMap<String,String> permissions = ActionUtil
				.groupPermissionsGrantableAndAccountPermissionsToMap(
						callingAccount, account, group);
		
		long permissionsId;
		
		try {
			permissionsId = PermissionsLocalServiceUtil
					.getPermissionsIdByEmailAddressAndFqgn(
							account.getMail().get(0), 
							PermissionsUtil.getFqgnFromDn(group));
		} catch (SystemException e) {
			permissionsId = 0;
		}
		
		actionRequest.setAttribute("cancelURL", cancelURL);
		actionRequest.setAttribute("group", group);
		actionRequest.setAttribute("permissions", permissions);
		actionRequest.setAttribute("permissionsId", permissionsId);
		actionRequest.setAttribute("selAccount", account);
		
		actionResponse.setRenderParameter("jspPage", 
				PortletUtil.ACCT_MGMT_ACCOUNT_PERMISSIONS_CHOOSE_PERMISSIONS_JSP);
		
	}
	
	public static String getEmailProvider(Account account) {
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(Filter.o,
				account.getAttribute("esuccMailPrimaryDomain"),false));
		
		List<Domain> domains = Search.getDomains(filters, null, 
				StringPool.BLANK, StringPool.BLANK, false);
		
		String result = StringPool.BLANK;
		if (domains.size() == 1) {
			result = domains.get(0).getEsuccMailProvider();
		}
		return result;
	}
	
	public static boolean importAccount(ActionRequest actionRequest, Account account, 
			List<String> responses) {
		String emailAddress = account.getMail().get(0);
		Account newAccount = null;
		
		AccountManagementService ams = AccountManagementServiceImpl.getInstance();
		List<SubSystem> subsystems = new ArrayList<SubSystem>();	
		
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(Filter.esuccEntity,
				account.getAttribute("esuccEntity"),false));
		
		List<Domain> domains = Search.getDomains(filters, null, 
				StringPool.BLANK, StringPool.BLANK, false);
		
		boolean valid = PortletUtil.validatePasswordFields(account.getPassword(), 
				account.getPassword(), account.getGivenName(), 
				account.getSn(), responses);
		
		if (valid) {
			valid = PortletUtil.validateEmailAddressField(emailAddress, responses);
		}
		
		if (valid) {
			boolean matched = false;
			for (Domain domain : domains) {
				
				if (domain.getOrganization().equals(account.getAttribute("esuccMailPrimaryDomain"))) {
					matched = true;
					break;
				}
			}
			if (!matched) {
				valid = false;
				responses.add("Invalid domain");
			}
		}
		
		if (!valid && responses.size() > 0) {
			return false;
		} else {
			subsystems.add(SubSystem.LDAP);
				try {
					if (ams.checkAccountExists(account.getMail().get(0)).isEmpty()) {
						newAccount = ams.createAccount(account, subsystems);
					} else {
						return false;
					}
				} catch (ValidationException e) {
					e.printStackTrace();
					return false;
				}
		}
		
		writeActionLog(actionRequest, account.getMail().get(0), 
				account.getDisplayName(), account.getAttribute("esuccEntity"), 
				"Add account (Import)");
		
		return true;
	}
	
	public void importAccountsUploadCSV(
			ActionRequest actionRequest, ActionResponse actionResponse) {
		 List<String[]> preview = new ArrayList<String[]>();
	     String filePath = null;
	     String jspPage = null;
	     UploadPortletRequest uploadRequest = null;
	     
	     try {
	    	 uploadRequest = PortalUtil
	    			 .getUploadPortletRequest(actionRequest);
	    	 
	    	 File file = uploadRequest.getFile("csvFile");
	    	 if (!file.exists()) {
	    		 SessionErrors.add(actionRequest, "could-not-upload-file-please-try-again");
	    	 }
	    	    	 
	    	 preview = ActionUtil.parseCSVForPreview(file); 
	    	 filePath = file.getAbsolutePath();
		     
		     String group = uploadRequest.getParameter(UserDisplayTerms.GROUP);
		     String cancelURL = uploadRequest.getParameter("cancelURL");

		     actionRequest.setAttribute("preview", preview);
		     actionRequest.setAttribute("filePath", filePath);
		     actionRequest.setAttribute("group", group);
		     actionRequest.setAttribute("cancelURL", cancelURL);
		     
    		 jspPage = PortletUtil.ACCT_MGMT_ACCOUNT_IMPORT_ACCOUNTS_PREVIEW_JSP;
	    	 
	     } catch (FileNotFoundException e) {
	    	 SessionErrors.add(actionRequest, "could-not-upload-file-please-try-again");

	    	 jspPage = PortletUtil.ACCT_MGMT_ACCOUNT_IMPORT_ACCOUNTS_VIEW_JSP;
		     
	     } catch (UnknownImportAccountsHeaderException e) {
	    	 SessionErrors.add(actionRequest, "invalid-header");

	    	 jspPage = PortletUtil.ACCT_MGMT_ACCOUNT_IMPORT_ACCOUNTS_VIEW_JSP;
	     }
	     
	     actionResponse.setRenderParameter("jspPage", jspPage);	     
	}
	
	public void importAccountsProcessCSV(
			ActionRequest actionRequest, ActionResponse actionResponse) {
		 List<String[]> failedImports = new ArrayList<String[]>();
	     String filePath = ParamUtil.getString(actionRequest, "filePath");
	     String doneURL = ParamUtil.getString(actionRequest, "doneURL");
	     String group = ParamUtil.getString(actionRequest, "group");
	     MutableInt importCount = new MutableInt();
	     
	     try {	    	 
	    	 File file = new File(filePath);
	    	 if (!file.exists()) {
	    		 SessionErrors.add(actionRequest, "could-not-find-file-please-try-again");
	    	 }
	    	    	 
	    	 failedImports = ActionUtil.parseCSV(actionRequest, file, importCount); 
	    	 filePath = file.getAbsolutePath();
	    	 
	     } catch (FileNotFoundException e) {
	    	 SessionErrors.add(actionRequest, "could-not-find-file-please-try-again");

		     actionResponse.setRenderParameter("jspPage", 
		    		 PortletUtil.ACCT_MGMT_ACCOUNT_IMPORT_ACCOUNTS_VIEW_JSP);
		     
	     } catch (UnknownImportAccountsHeaderException e) {
	    	 SessionErrors.add(actionRequest, "invalid-header");

		     actionResponse.setRenderParameter("jspPage", 
		    		 PortletUtil.ACCT_MGMT_ACCOUNT_IMPORT_ACCOUNTS_VIEW_JSP);
	     }
	     
	     actionRequest.setAttribute("failedImports", failedImports);
	     actionRequest.setAttribute("importCount", importCount.toInteger());
	     actionRequest.setAttribute("group", group);
	     actionRequest.setAttribute("doneURL", doneURL);
	     
//	     if (failedImports.size() > 1) {
//	    	 String outputFilePath = ActionUtil.writeCSV(failedImports);
//		     actionRequest.setAttribute("filePath", outputFilePath);
//	     }
	     
	     actionResponse.setRenderParameter("jspPage", 
	    		 PortletUtil.ACCT_MGMT_ACCOUNT_IMPORT_ACCOUNTS_SUMMARY_JSP);	     
	}
	
	public static void removeAccount(
			ActionRequest actionRequest, ActionResponse actionResponse) {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
		            WebKeys.THEME_DISPLAY);
		Account callingAccount =  null;
		try {
			callingAccount = ActionUtil.accountFromEmailAddress(themeDisplay.getUser().getEmailAddress());
		} catch (Exception e1) {
			e1.printStackTrace();	
		}
		AccountManagementService ams = AccountManagementServiceImpl.getInstance();
		String jspPage = null;
		List<SubSystem> subsystems = new ArrayList<SubSystem>();
	    String backURL = DAOParamUtil.getString(actionRequest, "backURL");
	    boolean remove = DAOParamUtil.getBoolean(actionRequest, "remove");
		Account account = ActionUtil.accountFromUidNumber(
				DAOParamUtil.getString(actionRequest, "uidNumber"));
		
		actionResponse.setRenderParameter("uidNumber", account.getAttribute("uidNumber"));
		actionResponse.setRenderParameter("backURL", backURL);
		
		if (PermissionsChecker.hasPermission(
				callingAccount, account, PermissionsChecker.PERMISSION_ACCOUNT_REMOVE_FORCE)) {	
			subsystems.add(SubSystem.LDAP);
			if (remove) {
				try {
					account.setAttribute("removeAccount", "TRUE");
					ams.modifyAccount(account, subsystems);
				} catch (ValidationException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// That's Ok
				}
			}
			jspPage = PortletUtil.SEARCH_VIEW_JSP;
		} else {
			SessionErrors.add(actionRequest, "insufficient-privileges");

			jspPage = PortletUtil.ACCT_MGMT_ACCOUNT_REMOVE_JSP;
		}
			
		writeActionLog(actionRequest, account.getMail().get(0), 
				account.getDisplayName(), account.getAttribute("esuccEntity"), 
				"Immediate account removal");
		actionResponse.setRenderParameter("jspPage", jspPage);
	}
	
	public void removeAccountOnSchedule(ActionRequest actionRequest, 
			ActionResponse actionResponse) {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
		            WebKeys.THEME_DISPLAY);
		Account callingAccount =  null;
		try {
			callingAccount = ActionUtil.accountFromEmailAddress(themeDisplay.getUser().getEmailAddress());
		} catch (Exception e1) {
			e1.printStackTrace();	
		}
		String jspPage = null;
	    String backURL = DAOParamUtil.getString(actionRequest, "backURL");
		String dateString = DAOParamUtil.getString(actionRequest, "remove-date");
		Date date = null;
		Date noticeDate = null;
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		try {
			date = new SimpleDateFormat("MM/dd/yy").parse(dateString);
			Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.DATE, -6);
	        noticeDate = cal.getTime();
		} catch (ParseException e1) {
			SessionErrors.add(actionRequest, "remove-account-failed");
			
			jspPage = PortletUtil.ACCT_MGMT_ACCOUNT_REMOVE_JSP;
		}
		Account account = ActionUtil.accountFromUidNumber(
				DAOParamUtil.getString(actionRequest, "uidNumber"));
		
		actionResponse.setRenderParameter("uidNumber", account.getAttribute("uidNumber"));
		actionResponse.setRenderParameter("backURL", backURL);
		
		if (PermissionsChecker.hasPermission(
				callingAccount, account, PermissionsChecker.PERMISSION_ACCOUNT_REMOVE)) {
			try {
				// Send email 7 days before removal
				JobQueueLocalServiceUtil.addJob(themeDisplay.getUserId(), account.getMail().get(0), 
						account.getDisplayName(), "Scheduled removal of " + account.getMail().get(0), 
						PortletUtil.JOB_ACTION_REMOVE_EMAIL_NOTICE, noticeDate);
				// Remove the account on the date
				JobQueueLocalServiceUtil.addJob(themeDisplay.getUserId(), account.getMail().get(0), 
						account.getDisplayName(), "Scheduled removal of " + account.getMail().get(0), 
						PortletUtil.JOB_ACTION_REMOVE, date);
				// Send an email immediately
				
				MailEngine.send(PropsValues.JOB_FROM_EMAIL_ADDRESS, 
						account.getMail().get(0), 
						PropsValues.JOB_REMOVE_NOTICE_EMAIL_SUBJECT, 
						PropsValues.JOB_REMOVE_NOTICE_EMAIL_BODY
						+ "\n\nRemoval date: " + df.format(date));
			} catch (Exception e) {
				SessionErrors.add(actionRequest, "remove-account-failed");
				e.printStackTrace();
				jspPage = PortletUtil.ACCT_MGMT_ACCOUNT_REMOVE_JSP;
			}

			jspPage = PortletUtil.SEARCH_VIEW_JSP;
		} else {
			SessionErrors.add(actionRequest, "insufficient-privileges");
			
			jspPage = PortletUtil.ACCT_MGMT_ACCOUNT_REMOVE_JSP;
		}
			
		writeActionLog(actionRequest, account.getMail().get(0), 
				account.getDisplayName(), account.getAttribute("esuccEntity"), 
				"Scheduled account removal for " + df.format(date));
		actionResponse.setRenderParameter("jspPage", jspPage);
	}
	
	public void deleteScheduledRemovalJob(ActionRequest actionRequest,
			ActionResponse actionResponse) {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
	            WebKeys.THEME_DISPLAY);
		Account callingAccount =  null;
		try {
			callingAccount = ActionUtil.accountFromEmailAddress(themeDisplay.getUser().getEmailAddress());
		} catch (Exception e1) {
			e1.printStackTrace();	
		}
		String jspPage = null;
		Account account = ActionUtil.accountFromUidNumber(
				DAOParamUtil.getString(actionRequest, "uidNumber"));
		String backURL = DAOParamUtil.getString(actionRequest, "backURL");
	    boolean delete = DAOParamUtil.getBoolean(actionRequest, "delete");
	    
	    actionResponse.setRenderParameter("uidNumber", account.getAttribute("uidNumber"));
		actionResponse.setRenderParameter("backURL", backURL);
	    
	    if (PermissionsChecker.hasPermission(
				callingAccount, account, PermissionsChecker.PERMISSION_ACCOUNT_REMOVE_FORCE)) {
	    	if (delete) {
	    		try {
					JobQueueLocalServiceUtil.deleteJobByEmailAddressAndJobAction(
							account.getMail().get(0), PortletUtil.JOB_ACTION_REMOVE);
		    		JobQueueLocalServiceUtil.deleteJobByEmailAddressAndJobAction(
		    				account.getMail().get(0), PortletUtil.JOB_ACTION_REMOVE_EMAIL_NOTICE);
				} catch (SystemException e) {
					SessionErrors.add(actionRequest, "delete-removal-job-failed");
					
					jspPage = PortletUtil.ACCT_MGMT_ACCOUNT_REMOVE_JSP;
				}
	    	}
	    	jspPage = PortletUtil.SEARCH_VIEW_JSP;
	    } else {
	    	SessionErrors.add(actionRequest, "insufficient-privileges");
			
			jspPage = PortletUtil.ACCT_MGMT_ACCOUNT_REMOVE_JSP;
	    }
	    
	    writeActionLog(actionRequest, account.getMail().get(0), 
				account.getDisplayName(), account.getAttribute("esuccEntity"), 
				"Deleted scheduled removal jobs");
		actionResponse.setRenderParameter("jspPage", jspPage);
	}
	
	public static List<EntityGroup> getAllGroups() {
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(
				Filter.name, StringPool.STAR, false));
		
		List<EntityGroup> groups = Search.getGroups(filters, null, 
				StringPool.BLANK, StringPool.BLANK, false);
		SearchUtil.sortGroups(groups, "asc", "cn");
		
		return groups;
	}
	
	public static List<Domain> getAllDomains() {
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(
				Filter.o, StringPool.STAR, false));
		
		List<Domain> domains = Search.getDomains(filters, null, 
				StringPool.BLANK, StringPool.BLANK, false);
		SearchUtil.sortDomains(domains, "asc", "o");
		
		return domains;
	}
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, 
			ResourceResponse resourceResponse) throws IOException {
		TreeMap<String,String> responses = new TreeMap<String,String>();
		String group = StringPool.NULL;
		
		switch (DAOParamUtil.getInteger(resourceRequest, UserDisplayTerms.CMD)) {
			case ADD_ACCOUNT_STEP_1_CMD: 
				group = DAOParamUtil.getString(
						resourceRequest, UserDisplayTerms.PRIMARY_GROUP);
				String accountType = DAOParamUtil.getString(
						resourceRequest, UserDisplayTerms.ACCOUNT_TYPE);
				String firstName = DAOParamUtil.getString(
						resourceRequest, UserDisplayTerms.FIRST_NAME);
				String lastName = DAOParamUtil.getString(
						resourceRequest, UserDisplayTerms.LAST_NAME);
				PortletUtil.processAccountName(firstName, lastName, 
						group, accountType, responses);
				break;
			case CHOOSE_GROUP_CMD:
				group = DAOParamUtil.getString(
						resourceRequest, UserDisplayTerms.GROUP);
				Account callingAccount = ActionUtil.accountFromUidNumber(
						DAOParamUtil.getString(
								resourceRequest, "callingAccount"));
				Account account = ActionUtil
						.accountFromEmailAddress(
								DAOParamUtil.getString(
										resourceRequest, "mail"));
				
				responses = ActionUtil
						.groupPermissionsGrantableAndAccountPermissionsToMap(
								callingAccount, account, group);
				
				try {
					responses.put("permissionsId", String.valueOf(
							PermissionsLocalServiceUtil
								.getPermissionsIdByEmailAddressAndFqgn(
										account.getMail().get(0), 
										PermissionsUtil.getFqgnFromDn(group))));
				} catch (SystemException e) {
					responses.put("permissionsId", "0");
				}
				break;
			case GET_ENTITY_ACCOUNT_TYPES:
				group = DAOParamUtil.getString(
						resourceRequest, UserDisplayTerms.PRIMARY_GROUP);
				PortletUtil.getEntityAccountTypes(group, responses);
			default: 
				//nothing
				break;
		}
		
		resourceResponse.setContentType("text/javascript");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		
		for (Map.Entry<String, String> response : responses.entrySet()) {
			
			jsonObject.put(response.getKey(), response.getValue());
		}
		
		PrintWriter writer = resourceResponse.getWriter();
		writer.write(jsonObject.toString());
	}
	
	private static void writeActionLog(
			PortletRequest request, String emailAddress, String fullName, 
			String entity, String description) {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
	            WebKeys.THEME_DISPLAY);
		
		long userId = 0;
		long modifiedUserId = 0;
		try {
			userId = UserLocalServiceUtil.getUserIdByEmailAddress(
					themeDisplay.getCompanyId(), 
					themeDisplay.getUser().getEmailAddress());
//			modifiedUserId = UserLocalServiceUtil.getUserIdByEmailAddress(
//					themeDisplay.getCompanyId(), account.getMail().get(0));
		} catch (PortalException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SystemException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(
				Filter.esuccEntity, entity, false));
		
		List<EntityGroup> group = Search.getGroups(
				filters, Operand.AND, StringPool.BLANK, StringPool.BLANK, false);
		String fqgn = null;
		
		if (group.size() == 1) {
			fqgn = PermissionsUtil.getFqgnFromDn(group.get(0).getAttribute("dn"));
		} else {
			fqgn = "UNKNOWN";
		}
		
		try {
			ActionLogLocalServiceUtil.addAction(
					userId, emailAddress, fullName, fqgn, description);
		} catch (SystemException e) {
			// It's just the log, life goes on
			System.out.println("Unabled to write to YAMS Action Log");
		}
	}

	public static final int ADD_ACCOUNT_CMD = 1;
	public static final int ADD_ACCOUNT_STEP_1_CMD = 2;
	public static final int EDIT_ACCOUNT_CMD = 3;
	public static final int EDIT_PASSWORD_CMD = 4;
	public static final int CHOOSE_PERMISSIONS_CMD = 5;
	public static final int CHOOSE_GROUP_CMD = 6;
	public static final int UPLOAD_CSV_CMD = 7;
	public static final int GET_ENTITY_ACCOUNT_TYPES = 8;
	
	public static String[] possibleCSVHeaderValues = new String[] {"First Name","Last Name", 
		"Email Address", "Password", "Screen Name", "Account Type" };
}