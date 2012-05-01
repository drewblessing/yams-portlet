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

import com.liferay.portal.kernel.util.PropsUtil;

/**
 * Property value assignment
 * 
 * @author Drew A. Blessing
 *
 */
public class PropsValues {
	
	public static final String YAMS_LDAP_BASE_PROVIDER_URL = PropsUtil.get(PropsKeys.YAMS_LDAP_BASE_PROVIDER_URL);
	
	public static final String YAMS_LDAP_BASE_DN = PropsUtil.get(PropsKeys.YAMS_LDAP_BASE_DN);
	
	public static final String YAMS_LDAP_SECURITY_PRINCIPAL = PropsUtil.get(PropsKeys.YAMS_LDAP_SECURITY_PRINCIPAL);
	
	public static final String YAMS_LDAP_SECURITY_CREDENTIALS = PropsUtil.get(PropsKeys.YAMS_LDAP_SECURITY_CREDENTIALS);
	
	public static final String YAMS_PORTLET_ACCOUNTS_DEFAULT_VIEW = 
			"/html/portlet/accounts/" + PropsUtil.get(PropsKeys.YAMS_PORTLET_ACCOUNTS_DEFAULT_VIEW) + ".jsp";
	
}
