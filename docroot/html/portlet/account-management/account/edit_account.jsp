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

Account selAccount = PortletUtil.getAccountFromRequest(renderRequest);
request.setAttribute("account.selAccount", selAccount);

String[] mainSections = {"basic","password"};

String[][] categorySections = {mainSections};

%>

<liferay-util:buffer var="htmlTop">
	<c:if test="<%= selAccount != null %>">
		<div class="user-info">
			<div class="float-container">
<%-- 					<img alt="<%= HtmlUtil.escape(selAccount.getDisplayName()) %>" class="user-logo" src="<%= selUser.getPortraitURL(themeDisplay) %>" /> --%>

<%-- 					<span class="user-name"><%= HtmlUtil.escape(selAccount.getDisplayName()) %></span> --%>
			</div>
		</div>
	</c:if>
</liferay-util:buffer>

<liferay-util:buffer var="htmlBottom">
	<c:if test="<%= (selAccount != null) %>">
	</c:if>
</liferay-util:buffer>

	<liferay-ui:form-navigator
		backURL="<%= backURL %>"
		categoryNames="<%= _CATEGORY_NAMES %>"
		categorySections="<%= categorySections %>"
		htmlBottom="<%= htmlBottom %>"
		htmlTop="<%= htmlTop %>"
		jspPath="<%= PortletUtil.ACCT_MGMT_ACCOUNT_SECTIONS_DIRECTORY %>"
	/>

<%!
private static String[] _CATEGORY_NAMES = {"account-information"};
%>