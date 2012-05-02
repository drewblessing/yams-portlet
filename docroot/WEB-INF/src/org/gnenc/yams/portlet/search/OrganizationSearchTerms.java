package org.gnenc.yams.portlet.search;

import javax.portlet.PortletRequest;

import com.liferay.portal.kernel.dao.search.DAOParamUtil;

/**
 * Modeled after com.liferay.portlet.usersadmin.search.AccountSearchTerms
 * written by Brian Wing Shun Chan
 * 
 * @author Drew A. Blessing
 */
public class OrganizationSearchTerms extends OrganizationDisplayTerms {

	public OrganizationSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);

		name = DAOParamUtil.getString(portletRequest, NAME);
		location = DAOParamUtil.getString(portletRequest, LOCATION);
	}

}
