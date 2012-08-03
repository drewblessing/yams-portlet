package org.gnenc.yams.portlet.search;

import com.liferay.portal.kernel.dao.search.DAOParamUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import javax.portlet.PortletRequest;

/**
 * Modeled after {@link com.liferay.portlet.usersadmin.search.AccountSearchTerms}
 * written by Brian Wing Shun Chan
 *
 * @author Drew A. Blessing
 */
public class UserSearchTerms extends UserDisplayTerms {

	public UserSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);

		domain = DAOParamUtil.getString(portletRequest, DOMAIN);
		emailAddress = DAOParamUtil.getString(portletRequest, EMAIL_ADDRESS);
		esuccEntity = DAOParamUtil.getString(portletRequest, ESUCC_ENTITY);
		esuccAccountType = DAOParamUtil.getString(portletRequest, ESUCC_ACCOUNT_TYPE);
		firstName = DAOParamUtil.getString(portletRequest, FIRST_NAME);
		lastName = DAOParamUtil.getString(portletRequest, LAST_NAME);
		uidNumber = DAOParamUtil.getString(portletRequest, UID_NUMBER);
	}
}