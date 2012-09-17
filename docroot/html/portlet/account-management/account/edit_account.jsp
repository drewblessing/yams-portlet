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

<portlet:actionURL name="editAccount" var="editAccountURL" >
	<portlet:param name="backURL" value="<%=backURL %>" />
</portlet:actionURL>

<aui:form method="POST" action="<%=editAccountURL.toString() %>" name="yamsFm" id="yamsFm">
<aui:input type="hidden" name="<%=UserDisplayTerms.CMD %>" value="<%=AccountManagement.EDIT_ACCOUNT_CMD %>" />
<aui:input type="hidden" name="esuccMailPrimaryLocalPart" value='<%=selAccount.getAttribute("esuccMailPrimaryLocalPart") %>' />
<aui:input type="hidden" name="esuccMailPrimaryDomain" value='<%=selAccount.getAttribute("esuccMailPrimaryDomain") %>' />
<aui:input type="hidden" name="uidNumber" value='<%=selAccount.getAttribute("uidNumber") %>' />
	
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
		jspPath="<%= PortletUtil.PORTLET_ACCT_MGMT_ACCOUNT_SECTIONS_DIRECTORY %>"
	/>
</aui:form>

<%!
private static String[] _CATEGORY_NAMES = {"account-information"};
%>