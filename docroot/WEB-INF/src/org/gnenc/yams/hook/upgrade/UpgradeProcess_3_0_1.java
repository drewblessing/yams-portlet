package org.gnenc.yams.hook.upgrade;

import java.util.List;

import org.gnenc.yams.hook.upgrade.v3_0_1.UpgradePermissions;
import org.gnenc.yams.model.PermissionsDefined;
import org.gnenc.yams.service.PermissionsDefinedLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Drew A. Blessing
 */
public class UpgradeProcess_3_0_1 extends UpgradeProcess {
	@Override
	public int getThreshold() {
		return 100;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!isFirstRun()) {
			return;
		}

		upgrade(UpgradePermissions.class);
	}

	protected boolean isFirstRun() throws Exception {
		List<PermissionsDefined> permissionsDefined =
				PermissionsDefinedLocalServiceUtil.getPermissionsDefineds(
						QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		if (permissionsDefined.size() > 1) {
			return false;
		}

		return true;
	}
}