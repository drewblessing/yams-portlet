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
import com.liferay.portal.kernel.util.ParamUtil;

/**
 * Modeled after {@link com.liferay.portlet.usersadmin.search.AccountDisplayTerms}
 * written by Brian Wing Shun Chan
 * 
 * @author Drew A. Blessing
 */
public class OrganizationDisplayTerms extends DisplayTerms {
	
	public static final String NAME = "name";

	public static final String LOCATION = "location";

	public OrganizationDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		name = ParamUtil.getString(portletRequest, NAME);
		location = ParamUtil.getString(portletRequest, LOCATION);
		
	}
	
	public String getLocation() {
		return name;
		
	}
	
	public String getName() {
		return name;
		
	}
	
	protected String name;
	protected String location;

}
