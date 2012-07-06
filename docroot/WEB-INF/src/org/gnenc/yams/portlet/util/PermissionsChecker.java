package org.gnenc.yams.portlet.util;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.model.Group;
import org.gnenc.yams.service.PermissionsDefinedLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Drew A. Blessing
 *
 * The PermissionsChecker class checks a calling user's account
 * permissions over the account the action is requested for.  In
 * all cases the "calling account" is the currently logged in user's
 * account.
 */
public class PermissionsChecker extends PermissionsUtil {
	/**
	 * Check if an account has permission over a specific group.
	 * <p>
	 * If the permission is PermissionChecker.PERMISSION_ACCOUNT_ADD
	 * and the fqgn is company.dept.staff this will check to see if
	 * the calling account has permission to add an account for
	 * company.dept.staff fqgn.
	 * <p>
	 * This method checks at each fqgn level and permissions are inclusive.
	 * This means that if a user has no permissions at the company.dept.staff
	 * level but has permission at the company.dept level they will be
	 * able to perform the specified action at the company.dept.staff
	 * level as well.
	 * <p>
	 * To see if a user account has a particular permission on any group in the
	 * system, simply send a null value to fqgn (see below for example).  This is
	 * especially helpful for determining whether to display a toolbar item, etc.
	 * where it is not necessary at this point to check permission on a specific group.
	 * <p>
	 * Use: PermissionsChecker.hasGroupPermission(account,
	 * 				PermissionChecker.PERMISSION_ACCOUNT_ADD, "company.dept.staff");
	 * Returns true if the calling account can create an account in the
	 * company.dept.staff group.
	 * <p>
	 * Use: PermissionsChecker.hasGroupPermission(account,
	 * 				PermissionChecker.PERMISSION_ACCOUNT_ADD, null);
	 * Returns true if the calling account can create an account in one or more
	 * groups in the system.
	 *
	 * @param account		The currently logged in user's account
	 * @param permission	The permissions to check - Must be a constant
	 * 						value from the PermissionChecker class (i.e.
	 * 						PermissionChecker.PERMISSION_ACCOUNT_ADD)
	 * @param fqgn			The fully-qualified group name to check permissions
	 * 						over.
	 * @return				true if the account has permission, else false
	 */
	public static boolean hasGroupPermission(Account account, String permission, String fqgn) {
		return hasPermission(account, null, permission, fqgn);
	}
	
	/**
	 * Check if an account has permission over a specific group.
	 * <p>
	 * This method has the same functionality as 
	 * {@link #hasGroupPermission(Account, String, String)} except it accepts
	 * an org.gnenc.yams.model.Group as the third parameter.  The Group is 
	 * converted to a fqgn and then {@link #hasGroupPermission(Account, String, String)}
	 * is called as normal. 
	 * <p>
	 *
	 * @param account		The currently logged in user's account
	 * @param permission	The permissions to check - Must be a constant
	 * 						value from the PermissionChecker class (i.e.
	 * 						PermissionChecker.PERMISSION_ACCOUNT_ADD)
	 * @param group			The group to check permissions over
	 * @return				true if the account has permission, else false
	 */
	public static boolean hasGroupPermission(Account account, String permission, Group group) {
		return hasGroupPermission(account, permission, getFqgnFromDn(group.getAttribute("dn")));
	}

	/**
	 * Check if the calling account has permission over a specific account.
	 * <p>
	 * This method checks at each fqgn and permissions are inclusive
	 * with one exception (see below). This means that if a user has no
	 * permission at the user's lowest level but has permission at any
	 * level above they will be able to perform the specified action. The
	 * fqgn is calculated based on the specific account.
	 * <p>
	 * If the calling account and the specific account are the same,
	 * this will check permissions an account has on itself. Examples
	 * of permissions an account may have over itself include password
	 * changes and email forward changes.
	 * <p>
	 * Use: Where callingAccount and account are not equal -
	 * PermissionsChecker.hasPermission(callingAccount, account,
	 * 				PermissionChecker.PERMISSION_ACCOUNT_EDIT);
	 * Returns true if the calling account can edit the specific account.
	 * <p>
	 * Use: Where callingAccount and account are equal (same account object
	 * passed in both argument positions) -
	 * PermissionsChecker.hasPermission(account, account,
	 * 				PermissionChecker.PERMISSIONS_ACCOUNT_EDIT);
	 * Returns true if the account can edit its own properties.
	 *
	 * @param callingAccount	The currently logged in user's account
	 * @param account			The account to check permissions over
	 * @param permission		The permissions to check - Must be a constant
	 * 							value from the PermissionChecker class (i.e.
	 * 							PermissionChecker.PERMISSION_ACCOUNT_ADD)
	 * @return					true if the account has permission, else false
	 */
	public static boolean hasPermission(Account callingAccount, Account account, String permission) {
		return hasPermission(callingAccount, account, permission, null);
	}

	private static boolean hasPermission(
			Account callingAccount, Account account, String permission, String fqgn) {

		if (Validator.isNull(callingAccount)) {
			return false;
		}

		String binaryPermissions;
		binaryPermissions = getBinaryPermissions(callingAccount, account, fqgn);
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
				return true;
			} else {
				return false;
			}
		} catch (StringIndexOutOfBoundsException e) {
			return false;
		}
	}
}