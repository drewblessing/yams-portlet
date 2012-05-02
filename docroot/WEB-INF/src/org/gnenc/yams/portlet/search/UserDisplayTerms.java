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

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

/**
 * Modeled after com.liferay.portlet.usersadmin.search.AccountDisplayTerms
 * written by Brian Wing Shun Chan
 * 
 * @author Drew A. Blessing
 */
public class UserDisplayTerms extends DisplayTerms {
	
	public static final String EMAIL_ADDRESS = "emailAddress";

	public static final String FIRST_NAME = "firstName";
	
	public static final String GROUP = "group";
	
	public static final String POSITION = "position";

	public static final String LAST_NAME = "lastName";

	public static final String STATUS = "status";

	public UserDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		String statusString = ParamUtil.getString(portletRequest, STATUS);

		if (Validator.isNotNull(statusString)) {
			status = GetterUtil.getInteger(statusString);
		}

		emailAddress = ParamUtil.getString(portletRequest, EMAIL_ADDRESS);
		firstName = ParamUtil.getString(portletRequest, FIRST_NAME);
		group = ParamUtil.getString(portletRequest, GROUP);
		position = ParamUtil.getString(portletRequest, POSITION);
		lastName = ParamUtil.getString(portletRequest, LAST_NAME);
	}
	
	public String getEmailAddress() {
		return emailAddress;
		
	}

	public String getFirstName() {
		return firstName;
		
	}
	
	public String getGroup() {
		return group;
		
	}
	
	public String getPosition() {
		return position;
		
	}

	public String getLastName() {
		return lastName;
		
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
	
	protected String emailAddress;
	protected String firstName;
	protected String group;
	protected String position;
	protected String lastName;
	protected int status;

}
