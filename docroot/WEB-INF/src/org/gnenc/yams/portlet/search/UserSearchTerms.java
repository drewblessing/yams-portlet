package org.gnenc.yams.portlet.search;

import javax.portlet.PortletRequest;

import com.liferay.portal.kernel.dao.search.DAOParamUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

/**
 * Modeled after com.liferay.portlet.usersadmin.search.AccountSearchTerms
 * written by Brian Wing Shun Chan
 * 
 * @author Drew A. Blessing
 */
public class UserSearchTerms extends UserDisplayTerms {

	public UserSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);

		emailAddress = DAOParamUtil.getString(portletRequest, EMAIL_ADDRESS);
		firstName = DAOParamUtil.getString(portletRequest, FIRST_NAME);
		group = DAOParamUtil.getString(portletRequest, GROUP);
		position = DAOParamUtil.getString(portletRequest, POSITION);
		lastName = DAOParamUtil.getString(portletRequest, LAST_NAME);
		status = ParamUtil.getInteger(
			portletRequest, STATUS, WorkflowConstants.STATUS_APPROVED);
	}

}