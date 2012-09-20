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
UserSearch searchContainer = (UserSearch)request.getAttribute("liferay-ui:search:searchContainer");

String redirect = searchContainer.getIteratorURL().toString();

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
Account selAccount = (Account)row.getObject();
//long groupId = themeDisplay.getLayout().getGroupId();
String name = Account.class.getName();
List<String> mail = selAccount.getMail();
String uidStripped = selAccount.getUid().replaceAll("[^a-zA-Z0-9]+","");
%>

<liferay-ui:icon-menu showWhenSingleIcon="<%= true %>">
	<c:if test="<%= user.isDefaultUser() || PermissionsChecker.hasPermission(
				callingAccount, selAccount, PermissionsChecker.PERMISSION_ACCOUNT_EDIT) %>">
		<portlet:renderURL var="editAccountRenderURL">
			<portlet:param name="jspPage" value="<%=PortletUtil.ACCT_MGMT_ACCOUNT_EDIT_JSP %>" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="uidNumber" value='<%=selAccount.getAttribute("uidNumber") %>' />
		</portlet:renderURL>
	
		<span id="edit-account-<%=uidStripped %>">
			<liferay-ui:icon image="edit" message="edit" url="<%=editAccountRenderURL %>" />
		</span>
	</c:if>
	
	<c:if test="<%= user.isDefaultUser() || PermissionsChecker.hasPermission(
  				callingAccount, selAccount, PermissionsChecker.PERMISSION_ACCOUNT_FORWARD) %>">
		<portlet:renderURL var="editAccountForwardRenderURL">
			<portlet:param name="jspPage" value="<%=PortletUtil.ACCT_MGMT_ACCOUNT_EDIT_FORWARD_JSP %>" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="uidNumber" value='<%=selAccount.getAttribute("uidNumber") %>' />
		</portlet:renderURL>
	
		<span id="edit-account-<%=uidStripped %>">
			<liferay-ui:icon src="/html/themes/control_panel/images/mail/forward.png" message="edit-email-forward" url="<%=editAccountForwardRenderURL %>" />
		</span>
	</c:if>
	
	<c:if test="<%= user.isDefaultUser() || PermissionsChecker.hasPermission(callingAccount, selAccount,
				PermissionsChecker.PERMISSION_ACCOUNT_EDIT_PASSWORD) %>">
		<portlet:renderURL var="editPasswordRenderURL">
			<portlet:param name="jspPage" value="<%=PortletUtil.ACCT_MGMT_ACCOUNT_EDIT_PASSWORD_JSP %>" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="uidNumber" value='<%=selAccount.getAttribute("uidNumber") %>' />
		</portlet:renderURL>
		
		<span id="edit-password">
			<liferay-ui:icon cssClass="<%=uidStripped %>" image="key" message="edit-password" 
				url="<%=editPasswordRenderURL %>" />
		</span>
	
		
	</c:if>
	
	<c:if test="<%= user.isDefaultUser() || PermissionsChecker.hasPermission(callingAccount, selAccount,
				PermissionsChecker.PERMISSION_ACCOUNT_PERMISSIONS) %>">
		<portlet:renderURL var="editPermissionsRenderURL">
			<portlet:param name="jspPage" value="<%=PortletUtil.ACCT_MGMT_ACCOUNT_PERMISSIONS_CHOOSE_GROUP_JSP %>" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="uidNumber" value='<%=selAccount.getAttribute("uidNumber") %>' />
		</portlet:renderURL>
	
		<span id="edit-permissions">
			<liferay-ui:icon cssClass="<%=uidStripped %>" image="permissions" message="edit-permissions" 
				url="<%=editPermissionsRenderURL %>" />
		</span>
	</c:if>

</liferay-ui:icon-menu>
