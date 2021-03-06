<%
/**
 *  Copyright (c) 2012-2013 Educational Service Unit 10.
 *
 *  This file is part of the YAMS portlet.
 *"docroot/html/portlet/account-management/account/admin_actions/edit_password.jsp"
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

Account selAccount = ActionUtil.accountFromUidNumber(ParamUtil.getString(request, "uidNumber"));
String mailProvider = AccountManagement.getEmailProvider(selAccount);
%>

<c:if test="<%= portletName.equals(PortletKeys.ACCOUNT_MANAGEMENT) %>">
	<liferay-util:include
			page="<%=PortletUtil.ACCT_MGMT_TOOLBAR_JSP %>"
			servletContext="<%=this.getServletContext() %>">
		<liferay-util:param name="toolbarItem" value="edit" />
	</liferay-util:include>
</c:if>

<liferay-ui:header
	backURL="<%= backURL %>"
	title='<%= selAccount.getDisplayName() %>'
/> 

<c:choose>
	<c:when test='<%=mailProvider.equals("GNENC") %>'>
		<liferay-util:include
				page="<%=PortletUtil.ACCT_MGMT_ACCOUNT_EDIT_FORWARD_FORM_JSP %>"
				servletContext="<%=this.getServletContext() %>" >
		</liferay-util:include>
	</c:when>
	<c:otherwise>
		<liferay-util:include
				page="<%=PortletUtil.ACCT_MGMT_ACCOUNT_EDIT_FORWARD_OTHER_PROVIDER_JSP %>"
				servletContext="<%=this.getServletContext() %>" >
		</liferay-util:include>	
	</c:otherwise>
</c:choose>

