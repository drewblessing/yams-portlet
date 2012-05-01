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
String redirect = PortalUtil.getCurrentURL(renderRequest);
String toolbarItem = ParamUtil.getString(request, "toolbarItem", "search");
%>

<div class="lfr-portlet-toolbar">
	<portlet:renderURL var="searchUsersURL" >
		<portlet:param name="jspPage" value="/html/portlet/accounts/user_search.jsp" />
		<portlet:param name="redirect" value="<%= redirect %>" />
		<portlet:param name="toolbarItem" value="search" />
	</portlet:renderURL>
	
	<span class="lfr-toolbar-button search-button <%= toolbarItem.equals("search") ? "current" : StringPool.BLANK %>">
		<a href="<%= searchUsersURL %>"><liferay-ui:message key="search" /></a>
	</span>
	
	<portlet:renderURL var="viewUsersURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="jspPage" value="/html/portlet/accounts/view_accounts.jsp" />
		<portlet:param name="redirect" value="<%= redirect %>" />
		<portlet:param name="toolbarItem" value="view-all" />
	</portlet:renderURL>
	
	<span class="lfr-toolbar-button view-button <%= toolbarItem.equals("view-all") ? "current" : StringPool.BLANK %>">
		<a href="<%= viewUsersURL %>"><liferay-ui:message key="view-all" /></a>
	</span>
	
</div>