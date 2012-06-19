package org.gnenc.yams.portlet.util;

import com.liferay.portal.model.Portlet;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.BaseControlPanelEntry;

public class YAMSControlPanelEntry extends BaseControlPanelEntry {

	@Override
	public boolean isVisible(PermissionChecker permissionChecker, Portlet portlet)
			throws Exception {
		if (portlet.getPortletId().equals(PortletKeys.ACCOUNT_MANAGEMENT_PORTLET_ID) &&
				(!PropsValues.PORTLETS_ACTIVE_ACCOUNTMANAGEMENT)) {
				return false;
		}
		return true;
	}

	@Override
	public boolean isVisible(Portlet portlet, String category, ThemeDisplay themeDisplay)
			throws Exception {
		if (portlet.getPortletId().equals(PortletKeys.ACCOUNT_MANAGEMENT_PORTLET_ID) &&
				(!PropsValues.PORTLETS_ACTIVE_ACCOUNTMANAGEMENT)) {
				return false;
		}
		
		return true; 
	}

}