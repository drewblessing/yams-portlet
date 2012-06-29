package org.gnenc.yams.portlet.search;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.dao.search.DisplayTerms;

public class YAMSDisplayTerms extends DisplayTerms {

	public YAMSDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);
	}

	public static final String CMD = "cmd";
}
