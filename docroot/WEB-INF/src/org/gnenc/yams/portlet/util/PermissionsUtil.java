package org.gnenc.yams.portlet.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.model.Domain;
import org.gnenc.yams.model.EntityGroup;
import org.gnenc.yams.model.Permissions;
import org.gnenc.yams.model.PermissionsDefined;
import org.gnenc.yams.service.PermissionsDefinedLocalServiceUtil;
import org.gnenc.yams.service.PermissionsLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class PermissionsUtil {
	protected static String getBinaryPermissions(
			Account callingAccount, Account account, String fqgn) {
		long decimal = 0;

		if (Validator.isNull(fqgn) && Validator.isNull(account)) {
			decimal = getDecimalPermissionsByEmailAddress(callingAccount.getMail().get(0));
		} else if (Validator.isNull(fqgn)) {
			List<EntityGroup> groups = PortletUtil.getGroupsByAccount(account);
			for (EntityGroup group : groups) {
				decimal = decimal | getDecimalPermissionsByFqgn(
						callingAccount, account, getFqgnFromDn(group.getAttribute("dn")));
			}
		} else {
			decimal = getDecimalPermissionsByFqgn(callingAccount, account, fqgn);
		}

		String binaryPermissions = new StringBuffer(
				Long.toBinaryString(decimal)).reverse().toString();

		return binaryPermissions;
	}
	
	protected static String getBinaryPermissionsGrantable(
			Account callingAccount, Account account, String fqgn) {
		long decimal = 0;

		if (Validator.isNull(fqgn) && Validator.isNull(account)) {
			decimal = getDecimalPermissionsGrantableByEmailAddress(
					callingAccount.getMail().get(0));
		} else if (Validator.isNull(fqgn)) {
			List<EntityGroup> groups = PortletUtil.getGroupsByAccount(account);
			for (EntityGroup group : groups) {
				decimal = decimal | getDecimalPermissionsGrantableByFqgn(
						callingAccount, account, getFqgnFromDn(group.getAttribute("dn")));
			}
		} else {
			decimal = getDecimalPermissionsGrantableByFqgn(callingAccount, account, fqgn);
		}

		String binaryPermissions = new StringBuffer(
				Long.toBinaryString(decimal)).reverse().toString();

		return binaryPermissions;
	}

	private static long getDecimalPermissionsByEmailAddress(String email) {
		List<Permissions> results = new ArrayList<Permissions>();
		long decimal = 0;
		try {
			results = PermissionsLocalServiceUtil.getByEmailAddress(email);
			for (Permissions permission : results) {
				decimal = decimal | permission.getPermissions();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decimal;
	}
	
	private static long getDecimalPermissionsGrantableByEmailAddress(String email) {
		List<Permissions> results = new ArrayList<Permissions>();
		long decimal = 0;
		try {
			results = PermissionsLocalServiceUtil.getByEmailAddress(email);
			for (Permissions permission : results) {
				decimal = decimal | permission.getPermissionsGrantable();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decimal;
	}

	private static long getDecimalPermissionsByFqgn(
			Account callingAccount, Account account, String fqgn) {
		List<Permissions> results = new ArrayList<Permissions>();
		long decimal = 0;
		boolean selfCheck = false;

		if (callingAccount.equals(account)) {
			selfCheck = true;
		}

		List<String> fqgnLevels = getFqgnLevels(fqgn);

		for (String fqgnLevel : fqgnLevels) {
			try {
				// Check group's self permissions at lowest level only
				if (selfCheck) {
					results = PermissionsLocalServiceUtil.
							getByFqgnAndGroupPermission(fqgnLevel, true);
					if (results.size() == 1) {
						decimal = decimal | results.get(0).getPermissions();
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

		return decimal;
	}
	
	private static long getDecimalPermissionsGrantableByFqgn(
			Account callingAccount, Account account, String fqgn) {
		List<Permissions> results = new ArrayList<Permissions>();
		long decimal = 0;
		boolean selfCheck = false;
		
		if (callingAccount.equals(account)) {
			selfCheck = true;
		}

		List<String> fqgnLevels = getFqgnLevels(fqgn);

		for (String fqgnLevel : fqgnLevels) {
			try {
				// Check group's self permissions at lowest level only
				if (selfCheck) {
					results = PermissionsLocalServiceUtil.
							getByFqgnAndGroupPermission(fqgnLevel, true);
					if (results.size() == 1) {
						decimal = decimal | results.get(0).getPermissionsGrantable();
						selfCheck = false;
					}
				}
				results = PermissionsLocalServiceUtil.
						getByEmailAddressAndFqgnAndGroupPermission(
								callingAccount.getMail().get(0), fqgnLevel, false);

				if (results.size() == 1) {
					decimal = decimal | results.get(0).getPermissionsGrantable();
				}
			} catch (SystemException e) {
				e.printStackTrace();
			}
		}

		return decimal;
	}

	public static String getFqgnFromDn(String dn) {
		String fqgn = "";
		List<String> dnAttributes = new ArrayList<String>();

//		dnAttributes.add("uid=");
//		dnAttributes.add("ou=");
//		dnAttributes.add("o=");
//		dnAttributes.add("cn=");
		
//		int index = -1;
		
		String cn = dn.substring(dn.indexOf("E"), dn.indexOf(",ou"));
		
		// Lookup Group to get entity, provider
		
		EntityGroup entity = PortletUtil.getGroupByCn(cn);
		
		// Build fqgn for permissions
		
		StringBuilder sb = new StringBuilder();
		sb.append(entity.getEsuccSystem().toLowerCase()).append(StringPool.PERIOD);
		sb.append(entity.getEsuccProvider().toLowerCase()).append(StringPool.PERIOD);
		sb.append(entity.getEsuccEntity().toLowerCase());
		
		fqgn = sb.toString();
		
		// Trim spaces
		fqgn = fqgn.replace(StringPool.SPACE, StringPool.BLANK);
		
		// Trim dashes
		fqgn = fqgn.replace(StringPool.DASH, StringPool.BLANK);
		
//		// Strip dc attributes off the end
//		
//		if (dn.indexOf("dc=") != -1) {
//			index = dn.indexOf("dc=");
//			dn = dn.substring(0, index);
//		}
//		
//		// Trim spaces
//		dn = dn.replace(StringPool.SPACE, StringPool.BLANK);
//		
//		// Trim dashes
//		dn = dn.replace(StringPool.DASH, StringPool.BLANK);
//		
//		// Strip DN attributes
//
//		for (String attr : dnAttributes) {
//			dn = dn.replace(attr, StringPool.BLANK);
//		}
//
//		// Change periods to underscores
//
//		dn = dn.replace(StringPool.PERIOD, StringPool.UNDERLINE);
//
//		// Reverse order for fqgn
//		
//		System.out.println("Group dn3: " + dn);
//
//		List<String> tokens = Arrays.asList(dn.split(StringPool.COMMA));
//		Collections.reverse(tokens);
//		for (int i=0; i<tokens.size(); i++) {
//			if (i == tokens.size()-1) {
//				fqgn += tokens.get(i);
//			} else {
//				fqgn += tokens.get(i) + StringPool.PERIOD;
//			}
//		}

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
	
	public static String permissionsToBinary(List<String> permissions) {
		String binaryPermissions = StringPool.BLANK;
		List<PermissionsDefined> permissionsDefineds = null;
		
		try {
			permissionsDefineds = PermissionsDefinedLocalServiceUtil
					.getPermissionsDefineds(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (PermissionsDefined permissionsDefined : permissionsDefineds) {
			boolean matched = false;
			for (String permission : permissions) {
				if (permissionsDefined.getPermissionKey().equals(permission)) {
					binaryPermissions = "1" + binaryPermissions;
					matched = true;
				} 
			}
			if (!matched) {
				binaryPermissions = "0" + binaryPermissions;
			}
		}
		
		return binaryPermissions;
	}
	
	public static long permissionsToDecimal(List<String> permissions) {
		long decimalPermissions = 0;
		
		String binaryPermissions = permissionsToBinary(permissions);
		decimalPermissions = Integer.parseInt(binaryPermissions, 2);
		
		return decimalPermissions;
	}

	// Permission Keys
	public static final String PERMISSION_ACCOUNT_ADD = "account-add";

	public static final String PERMISSION_ACCOUNT_EDIT = "account-edit";

	public static final String PERMISSION_ACCOUNT_EDIT_PASSWORD = "account-edit-password";
	
	public static final String PERMISSION_ACCOUNT_FORWARD = "account-forward";
	
	public static final String PERMISSION_ACCOUNT_MOVE = "account-move";
	
	public static final String PERMISSION_ACCOUNT_PERMISSIONS = "account-permissions";

	public static final String PERMISSION_ACCOUNT_REMOVE = "account-remove";

	public static final String PERMISSION_ACCOUNT_REMOVE_FORCE = "account-remove-force";

	public static final String PERMISSION_GROUP_ADD = "group-add";

	public static final String PERMISSION_GROUP_EDIT = "group-edit";

	public static final String PERMISSION_GROUP_REMOVE = "group-remove";
	
	public static final String PERMISSION_LOG_SEARCH = "log-search";
}