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

Account selAccount = PortletUtil.getAccountFromRequest(renderRequest);
request.setAttribute("account.selAccount", selAccount);
%>

<c:if test="<%= user.isDefaultUser() || PermissionsChecker.hasPermission(callingAccount, selAccount,
			PermissionsChecker.PERMISSION_ACCOUNT_REMOVE) %>">
	<portlet:actionURL name="removeAccount" var="removeAccountURL" >
		<portlet:param name="backURL" value="<%=backURL %>" />
	</portlet:actionURL>
	
	<aui:form method="POST" action="<%=removeAccountURL.toString() %>" name="removeFm" id="removeFm">
		<aui:input type="hidden" name="<%=UserDisplayTerms.CMD %>" value="<%=AccountManagement.EDIT_ACCOUNT_CMD %>" />
		<aui:input type="hidden" name="uidNumber" value='<%=selAccount.getAttribute("uidNumber") %>' />
		<aui:input type="hidden" name="remove" />
	
		<liferay-ui:message key="you-may-also-choose-to-remove-the-account-effective-immediately" />
		
			<aui:button-row>		
					<aui:button name="removeButton"  value="remove-now" onClick='<%= renderResponse.getNamespace() + "removeAccount()" %>' />
			</aui:button-row>
	
	</aui:form>
</c:if>

<aui:script>	
	var <portlet:namespace />removeAccount = function() {
		var A = AUI();
		
		if (confirm("You are about to immediately remove this account. Click OK to continue.\n")) {
			A.one('#<portlet:namespace />remove').val("true");
			A.one('#<portlet:namespace />removeFm').submit();
		}
	}
</aui:script>