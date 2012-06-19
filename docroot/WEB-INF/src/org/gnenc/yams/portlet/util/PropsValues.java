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
package org.gnenc.yams.portlet.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * Property value assignment
 *
 * @author Drew A. Blessing
 *
 */
public class PropsValues {

	public static final String LDAP_PROVIDER_URL = PortletProps.get(PropsKeys.LDAP_PROVIDER_URL);

	public static final String LDAP_BASE_DN = PortletProps.get(PropsKeys.LDAP_BASE_DN);

	public static final String LDAP_DATE_FORMAT = PortletProps.get(PropsKeys.LDAP_DATE_FORMAT);

	public static final String LDAP_USER_CONTAINER_MODE =
			PortletProps.get(PropsKeys.LDAP_USER_CONTAINER_MODE);

	public static final String LDAP_USER_CONTAINER_DN =
			PortletProps.get(PropsKeys.LDAP_USER_CONTAINER_DN);
	
	public static final boolean PORTLETS_ACTIVE_ACCOUNTMANAGEMENT = GetterUtil.getBoolean(
			PortletProps.get(PropsKeys.PORTLETS_ACTIVE_ACCOUNTMANAGEMENT));

	public static final boolean ACCOUNT_CREATE_WITH_WIZARD = GetterUtil.getBoolean(
			PortletProps.get(PropsKeys.ACCOUNT_CREATE_WITH_WIZARD));
}