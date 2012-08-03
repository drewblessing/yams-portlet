package org.gnenc.yams.hook.upgrade.v3_0_2;

import org.gnenc.yams.model.PermissionsDefined;
import org.gnenc.yams.portlet.util.PermissionsChecker;
import org.gnenc.yams.service.PermissionsDefinedLocalServiceUtil;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

/**
 * @author Drew A. Blessing
 */
public class UpgradePermissions extends UpgradeProcess {

	protected PermissionsDefined addPermissionsDefined(
			String permissionKey, long defaultUserId) throws SystemException {

		return PermissionsDefinedLocalServiceUtil.addPermissionsDefined(
				defaultUserId, permissionKey);
	}

	@Override
	protected void doUpgrade() throws Exception {
		String name = PrincipalThreadLocal.getName();

		try {
			long companyId = PortalUtil.getDefaultCompanyId();

			long defaultUserId = UserLocalServiceUtil.getDefaultUserId(
				companyId);

			PrincipalThreadLocal.setName(defaultUserId);
			setupPermissionsDefined(defaultUserId);
		}
		finally {
			PrincipalThreadLocal.setName(name);
		}
	}

	protected void setupPermissionsDefined(long defaultUserId)
			throws Exception {
		addPermissionsDefined(
				PermissionsChecker.PERMISSION_LOG_SEARCH, defaultUserId);

	}
}