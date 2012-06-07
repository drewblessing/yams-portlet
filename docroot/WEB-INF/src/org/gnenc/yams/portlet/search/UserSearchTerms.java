package org.gnenc.yams.portlet.search;

import javax.portlet.PortletRequest;

import com.liferay.portal.kernel.dao.search.DAOParamUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

/**
 * Modeled after {@link com.liferay.portlet.usersadmin.search.AccountSearchTerms}
 * written by Brian Wing Shun Chan
 * 
 * @author Drew A. Blessing
 */
public class UserSearchTerms extends UserDisplayTerms {

	public UserSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);

		emailAddress = DAOParamUtil.getString(portletRequest, EMAIL_ADDRESS);
		firstName = DAOParamUtil.getString(portletRequest, FIRST_NAME);
		organization = DAOParamUtil.getString(portletRequest, ORGANIZATION);
		lastName = DAOParamUtil.getString(portletRequest, LAST_NAME);
		status = ParamUtil.getInteger(
			portletRequest, STATUS, WorkflowConstants.STATUS_APPROVED);
		uid = DAOParamUtil.getString(portletRequest, UID);
	}

}
