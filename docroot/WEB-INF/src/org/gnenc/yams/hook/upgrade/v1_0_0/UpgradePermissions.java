package org.gnenc.yams.hook.upgrade.v1_0_0;

import java.util.List;

import org.gnenc.yams.model.PermissionsDefined;
import org.gnenc.yams.service.PermissionsDefinedLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
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
		addPermissionsDefined("account_add", defaultUserId);
		addPermissionsDefined("account_edit", defaultUserId);
		addPermissionsDefined("account_remove", defaultUserId);
		addPermissionsDefined("account_remove_force", defaultUserId);
		addPermissionsDefined("group_add", defaultUserId);
		addPermissionsDefined("group_edit", defaultUserId);
		addPermissionsDefined("group_remove", defaultUserId);
		
	}
	
}
