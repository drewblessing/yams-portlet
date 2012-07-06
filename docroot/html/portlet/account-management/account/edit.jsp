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
String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL", redirect);

Account selectedAccount = PortletUtil.getAccountFromRequest(renderRequest);
%>

<c:if test="<%= portletName.equals(PortletKeys.ACCOUNT_MANAGEMENT) %>">
	<liferay-util:include
			page="<%=PortletUtil.ACCT_MGMT_TOOLBAR_JSP %>"
			servletContext="<%=this.getServletContext() %>">
		<liferay-util:param name="toolbarItem" value='<%= (selectedAccount == null) ? "add" : "view" %>' />
	</liferay-util:include>
</c:if>

<liferay-ui:header
	backURL="<%= backURL %>"
	title='<%= (selectedAccount == null) ? "new-user" : selectedAccount.getDisplayName() %>'
/>

<portlet:actionURL name="editAccount" var="editAccountURL" />

<aui:form method="POST" action="<%=editAccountURL.toString() %>" name="yamsFm" id="yamsFm">
<%-- 	<aui:input type="hidden" name="cmd" value='<%=(selectedAccount == null) ? AccountManagement.ADD_ACCOUNT_CMD : AccountManagement.EDIT_ACCOUNT_CMD %>' /> --%>
<c:choose>
	<c:when test="<%=PropsValues.ACCOUNT_CREATE_WITH_WIZARD && selectedAccount == null %>">
		<liferay-util:include
				page="<%=PortletUtil.ACCT_MGMT_ACCOUNT_ADD_WIZARD_JSP %>"
				servletContext="<%=this.getServletContext() %>" >
			<liferay-util:param name="step" value="1" />		
		</liferay-util:include>
	</c:when>
	<c:otherwise>
		<liferay-util:include
				page="<%=PortletUtil.ACCT_MGMT_ACCOUNT_EDIT_ACCOUNT_JSP %>"
				servletContext="<%=this.getServletContext() %>">
			<liferay-util:param name="uid" value="<%=selectedAccount.getUid() %>" />
			<liferay-util:param name="backURL" value="<%= backURL %>" />
		</liferay-util:include>
	</c:otherwise>
</c:choose>
</aui:form>