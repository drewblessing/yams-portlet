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
String backURL = ParamUtil.getString(request, "backURL");
%>

<c:if test="<%= portletName.equals(PortletKeys.ACCOUNT_MANAGEMENT) %>">
	<liferay-util:include
			page="<%=PortletUtil.ACCT_MGMT_TOOLBAR_JSP %>"
			servletContext="<%=this.getServletContext() %>" />
</c:if>

<%
// Account selAccount = PortletUtil.getAccountFromRequest(renderRequest);
Account selAccount = ActionUtil.accountFromUidNumber(ParamUtil.getString(request, "uidNumber"));

request.setAttribute("account.selAccount", selAccount);
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	title='<%= selAccount.getDisplayName() %>'
/>

<div class="account-information">
	<div class="section account-details">
		<jsp:include page="/html/portlet/search/account/details.jsp" />
	</div>
</div>

<aui:button id="back" value="back" onClick="<%=backURL %>" />


