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
	<portlet:renderURL var="searchViewURL">
		<portlet:param name="jspPage" value="<%=PortletUtil.SEARCH_VIEW_JSP %>" />
		<portlet:param name="redirect" value="<%= redirect %>" />
		<portlet:param name="toolbarItem" value="search" />
	</portlet:renderURL>

	<span class="lfr-toolbar-button view-button <%= toolbarItem.equals("search") ? "current" : StringPool.BLANK %>">
		<a href="<%= searchViewURL %>"><liferay-ui:message key="search" /></a>
	</span>

	<liferay-ui:icon-menu align="left"
			cssClass='<%= "lfr-toolbar-button add-button " + (toolbarItem.equals("add") ? "current" : StringPool.BLANK) %>'
			direction="down"
			extended="<%= false %>"
			icon='<%= themeDisplay.getPathThemeImages() + "/common/add.png" %>'
			message="add"
			showWhenSingleIcon="<%= true %>"
	>

		<c:if test="<%= PermissionsChecker.hasGroupPermission(
				callingAccount, PermissionsChecker.PERMISSION_ACCOUNT_ADD, StringPool.NULL) %>">
			<portlet:renderURL var="addUserURL">
				<portlet:param name="jspPage" value="<%=PortletUtil.ACCT_MGMT_ACCOUNT_EDIT_JSP %>" />
				<portlet:param name="redirect" value="<%= searchViewURL %>" />	
				<portlet:param name="toolbarItem" value="add" />
			</portlet:renderURL>

			<liferay-ui:icon
				image="user_icon"
				message="account"
				url="<%= addUserURL %>"
			/>
		</c:if>
		<c:if test="<%= PermissionsChecker.hasGroupPermission(
				callingAccount, PermissionsChecker.PERMISSION_GROUP_ADD, StringPool.NULL) %>">

			<portlet:renderURL var="addOrganizationURL">
				<portlet:param name="jspPage" value="<%=PortletUtil.ACCT_MGMT_ORGANIZATION_EDIT_JSP %>" />
				<portlet:param name="redirect" value="<%= searchViewURL %>" />
				<portlet:param name="toolbarItem" value="add" />
			</portlet:renderURL>
			<liferay-ui:icon
				image="group"
				message='organization'
				url="<%= addOrganizationURL %>"
			/>

		</c:if>
	</liferay-ui:icon-menu>
	
	<c:if test="<%= PermissionsChecker.hasGroupPermission( 				
			callingAccount, PermissionsChecker.PERMISSION_ACCOUNT_ADD, StringPool.NULL) %>">
		<portlet:renderURL var="importURL">
			<portlet:param name="jspPage" value="<%=PortletUtil.ACCT_MGMT_ACCOUNT_IMPORT_ACCOUNTS_VIEW_JSP %>" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="toolbarItem" value="import" />
		</portlet:renderURL>
		<span class="lfr-toolbar-button import-button <%= toolbarItem.equals("import") ? "current" : StringPool.BLANK %>">
			<a href="<%= importURL %>"><liferay-ui:message key="import-accounts" /></a>
		</span>
	</c:if>
</div>