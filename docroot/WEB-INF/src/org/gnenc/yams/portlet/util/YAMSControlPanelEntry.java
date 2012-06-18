package org.gnenc.yams.portlet.util;

import com.liferay.portal.model.Portlet;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.ControlPanelEntry;
public class YAMSControlPanelEntry implements ControlPanelEntry {

	@Override
	public boolean isVisible(PermissionChecker arg0, Portlet arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isVisible(Portlet arg0, String arg1, ThemeDisplay arg2)
			throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

}