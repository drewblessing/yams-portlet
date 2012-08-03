package org.gnenc.yams.hook.upgrade.v3_0_1;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.util.List;

import org.gnenc.yams.model.PermissionsDefined;
import org.gnenc.yams.portlet.util.PermissionsChecker;
import org.gnenc.yams.service.PermissionsDefinedLocalServiceUtil;

/**
 * @author Drew A. Blessing
 */
public class UpgradePermissions extends UpgradeProcess {

	protected PermissionsDefined addPermissionsDefined(
			String permissionKey, long defaultUserId) throws SystemException {

		return PermissionsDefinedLocalServiceUtil.addPermissionsDefined(
				defaultUserId, permissionKey);
	}

	protected void clearData(long companyId) throws Exception {
		List<PermissionsDefined> permissionsDefined =
				PermissionsDefinedLocalServiceUtil.getPermissionsDefineds(
						QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (PermissionsDefined permission : permissionsDefined) {
			PermissionsDefinedLocalServiceUtil.deletePermissionsDefined(permission);
		}

	}

	@Override
	protected void doUpgrade() throws Exception {
		String name = PrincipalThreadLocal.getName();

		try {
			long companyId = PortalUtil.getDefaultCompanyId();

			long defaultUserId = UserLocalServiceUtil.getDefaultUserId(
				companyId);

			PrincipalThreadLocal.setName(defaultUserId);

			clearData(companyId);
			setupPermissionsDefined(defaultUserId);
		}
		finally {
			PrincipalThreadLocal.setName(name);
		}
	}

	protected void setupPermissionsDefined(long defaultUserId)
			throws Exception {
		addPermissionsDefined(
				PermissionsChecker.PERMISSION_ACCOUNT_ADD, defaultUserId);
		addPermissionsDefined(
				PermissionsChecker.PERMISSION_ACCOUNT_EDIT, defaultUserId);
		addPermissionsDefined(
				PermissionsChecker.PERMISSION_ACCOUNT_EDIT_PASSWORD, defaultUserId);
		addPermissionsDefined(
				PermissionsChecker.PERMISSION_ACCOUNT_FORWARD, defaultUserId);
		addPermissionsDefined(
				PermissionsChecker.PERMISSION_ACCOUNT_PERMISSIONS, defaultUserId);
		addPermissionsDefined(
				PermissionsChecker.PERMISSION_ACCOUNT_MOVE, defaultUserId);
		addPermissionsDefined(
				PermissionsChecker.PERMISSION_ACCOUNT_REMOVE, defaultUserId);
		addPermissionsDefined(
				PermissionsChecker.PERMISSION_ACCOUNT_REMOVE_FORCE, defaultUserId);
		addPermissionsDefined(
				PermissionsChecker.PERMISSION_GROUP_ADD, defaultUserId);
		addPermissionsDefined(
				PermissionsChecker.PERMISSION_GROUP_EDIT, defaultUserId);
		addPermissionsDefined(
				PermissionsChecker.PERMISSION_GROUP_REMOVE, defaultUserId);

		addDefaultUserPermissions();

	}

	private void addDefaultUserPermissions() {
		// TODO Add full permissions for the default user

	}

}