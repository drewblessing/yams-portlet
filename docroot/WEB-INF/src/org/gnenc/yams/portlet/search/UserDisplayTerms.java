/**
 *  Copyright (c) 2012-2013 Educational Service Unit 10.
 *
 *  This file is part of the YAMS portlet.
 *
 *  YAMS portlet is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  YAMS portlet is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with the YAMS portlet.  If not, see <http://www.gnu.org/licenses/>.
 **/
package org.gnenc.yams.portlet.search;

import javax.portlet.PortletRequest;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

/**
 * Modeled after {@link com.liferay.portlet.usersadmin.search.AccountDisplayTerms}
 * written by Brian Wing Shun Chan
 *
 * @author Drew A. Blessing
 */
public class UserDisplayTerms extends YAMSDisplayTerms {

	public static final String ACCOUNT_TYPE = "accountType";
	
	public static final String CLASS_OF = "classOf";
	
	public static final String DOMAIN = "domain";
	
	public static final String DN = "dn";
	
	public static final String EMAIL_ADDRESS = "emailAddress";
	
	public static final String ESUCC_ACCOUNT_TYPE = "esuccAccountType";
	
	public static final String ESUCC_ENTITY = "esuccEntity";
	
	public static final String ESUCC_PROVIDER = "esuccProvider";
	
	public static final String ESUCC_SYSTEM = "esuccSystem";

	public static final String FIRST_NAME = "firstName";
	
	public static final String GROUP = "group";

	public static final String ORGANIZATION = "organization";

	public static final String LAST_NAME = "lastName";
	
	public static final String PASSWORD = "password";
	
	public static final String PERMISSIONS = "permissions";
	
	public static final String PRIMARY_GROUP = "primaryGroup";
	
	public static final String SCREEN_NAME = "screenName";

	public static final String STATUS = "status";
	
	public static final String TITLE = "title";

	public static final String UID = "uid";
	
	public static final String UID_NUMBER = "uidNumber";
	
	public static final String VERIFY = "verify";

	public UserDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		String statusString = ParamUtil.getString(portletRequest, STATUS);

		if (Validator.isNotNull(statusString)) {
			status = GetterUtil.getInteger(statusString);
		}

		domain = ParamUtil.getString(portletRequest, DOMAIN);
		dn = ParamUtil.getString(portletRequest, DN);
		emailAddress = ParamUtil.getString(portletRequest, EMAIL_ADDRESS);
		esuccAccountType = ParamUtil.getString(portletRequest, ESUCC_ACCOUNT_TYPE);
		esuccEntity = ParamUtil.getString(portletRequest, ESUCC_ENTITY);
		firstName = ParamUtil.getString(portletRequest, FIRST_NAME);
		organization = ParamUtil.getString(portletRequest, ORGANIZATION);
		lastName = ParamUtil.getString(portletRequest, LAST_NAME);
		uid = ParamUtil.getString(portletRequest, UID);
		uidNumber = ParamUtil.getString(portletRequest, UID_NUMBER);
	}
	
	public String getDomain() {
		return domain;
	}
	
	public String getDn() {
		return dn;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
	
	public String getEsuccAccountType() {
		return esuccAccountType;
	}
	
	public String getEsuccEntity() {
		return esuccEntity;
	}

	public String getFirstName() {
		return firstName;

	}

	public String getOrganization() {
		return organization;

	}

	public String getPosition() {
		return position;

	}

	public String getProvider() {
		return provider;

	}

	public String getLastName() {
		return lastName;

	}

	public String getUid() {
		return uid;
	}
	
	public String getUidNumber() {
		return uidNumber;
	}

	public boolean isActive() {
		if (status == WorkflowConstants.STATUS_APPROVED) {
			return true;

		}
		else {
			return false;

		}

	}

	public int getStatus() {
		return status;

	}

	public void setStatus(int status) {
		this.status = status;

	}

	protected String domain;
	protected String dn;
	protected String emailAddress;
	protected String esuccAccountType;
	protected String esuccEntity;
	protected String firstName;
	protected String organization;
	protected String position;
	protected String provider;
	protected String lastName;
	protected int status;
	protected String uid;
	protected String uidNumber;

}