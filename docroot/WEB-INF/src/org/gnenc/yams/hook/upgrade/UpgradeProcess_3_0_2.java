package org.gnenc.yams.hook.upgrade;

import org.gnenc.yams.hook.upgrade.v3_0_2.UpgradePermissions;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Drew A. Blessing
 */
public class UpgradeProcess_3_0_2 extends UpgradeProcess {
	@Override
	public int getThreshold() {
		return 302;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgrade(UpgradePermissions.class);
	}
}