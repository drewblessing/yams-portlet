package org.gnenc.yams.hook.upgrade;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.util.List;

import org.gnenc.yams.hook.upgrade.v0_0_2.UpgradePermissions;
import org.gnenc.yams.model.PermissionsDefined;
import org.gnenc.yams.service.PermissionsDefinedLocalServiceUtil;

/**
 * @author Drew A. Blessing
 */
public class UpgradeProcess_0_0_2 extends UpgradeProcess {
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