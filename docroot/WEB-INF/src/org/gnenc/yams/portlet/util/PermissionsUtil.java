package org.gnenc.yams.portlet.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.model.Permissions;
import org.gnenc.yams.service.PermissionsLocalServiceUtil;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class PermissionsUtil {
	protected static String getBinaryPermissions(
			Account callingAccount, Account account, String fqgn) {
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
		
		String binaryPermissions = new StringBuffer(
				Long.toBinaryString(decimal)).reverse().toString();
		
		return binaryPermissions;
	}
	
	protected static String getFqgnFromDn(String dn) {
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
	
	protected static List<String> getFqgnLevels(String fqgn) {
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
	
	// Permission Keys
	public final static String PERMISSION_ACCOUNT_ADD = "account_add";	
	
	public final static String PERMISSION_ACCOUNT_EDIT = "account_edit";
	
	public final static String PERMISSION_ACCOUNT_REMOVE = "account_remove";
	
	public final static String PERMISSION_ACCOUNT_REMOVE_FORCE = "account_remove_force";
	
	public final static String PERMISSION_GROUP_ADD = "group_add";
	
	public final static String PERMISSION_GROUP_EDIT = "group_edit";
	
	public final static String PERMISSION_GROUP_REMOVE = "group_remove";	
}
