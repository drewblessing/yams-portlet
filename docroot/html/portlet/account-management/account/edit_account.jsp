<%
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
%>

<%@ include file="/html/portlet/init.jsp" %>

<%
Account selAccount = PortletUtil.getAccountFromRequest(renderRequest);

request.setAttribute("account.selAccount", selAccount);
%> 
<%= PermissionsChecker.hasGroupPermission(selAccount,PermissionsChecker.PERMISSION_ACCOUNT_ADD,"gnenc.esu10.esu10_org") %>

Account Add <%= PermissionsChecker.hasPermission(selAccount,selAccount,PermissionsChecker.PERMISSION_ACCOUNT_ADD) %>
Account Edit <%= PermissionsChecker.hasPermission(selAccount,selAccount,PermissionsChecker.PERMISSION_ACCOUNT_EDIT) %>
Account Remove <%= PermissionsChecker.hasPermission(selAccount,selAccount,PermissionsChecker.PERMISSION_ACCOUNT_REMOVE) %>
Account Remove Force <%= PermissionsChecker.hasPermission(selAccount,selAccount,PermissionsChecker.PERMISSION_ACCOUNT_REMOVE_FORCE) %>
Group Add <%= PermissionsChecker.hasPermission(selAccount,selAccount,PermissionsChecker.PERMISSION_GROUP_ADD) %>
Group Edit <%= PermissionsChecker.hasPermission(selAccount,selAccount,PermissionsChecker.PERMISSION_GROUP_EDIT) %>
Group Remove <%= PermissionsChecker.hasPermission(selAccount,selAccount,PermissionsChecker.PERMISSION_GROUP_REMOVE) %>