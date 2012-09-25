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
Set<String> errors = SessionErrors.keySet(renderRequest);
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

<portlet:actionURL name="editForward" var="editForwardURL" >
	<portlet:param name="backURL" value="<%=backURL %>" />
</portlet:actionURL>

<aui:form method="POST" name="fm" id="fm" action="<%=editForwardURL %>">
<aui:input type="hidden" name="uidNumber" value='<%=selAccount.getAttribute("uidNumber") %>' />

<div class="portlet-msg-info"><liferay-ui:message key="email-forward-info" /></div>
<div class="padded-div"><liferay-ui:message key="email-forward-instructions" /></div>

	<c:choose>
		<c:when test="<%=PermissionsChecker.hasPermission(callingAccount, selAccount, PermissionsChecker.PERMISSION_ACCOUNT_FORWARD) %>" >
		<c:if test='<%=Validator.isNotNull(selAccount.getAttribute("esuccMailForward")) %>'>
			<div class="padded-div"><liferay-ui:message key="email-forward-is-currently-set" /></div>
			<aui:fieldset>
				<aui:input type="hidden" name="delete" />
				<c:if test="<%=PermissionsChecker.hasPermission(callingAccount, selAccount, PermissionsChecker.PERMISSION_ACCOUNT_FORWARD) %>" >
					<aui:button name="deleteButton" value="delete" onClick='<%= renderResponse.getNamespace() + "deleteForward()" %>' />
				</c:if>
			</aui:fieldset>
		</c:if>
		<div class="section edit-forward aui-column aui-w25">
			<aui:input name="forward" size="25" value='<%=Validator.isNotNull(selAccount.getAttribute("esuccMailForward")) ? selAccount.getAttribute("esuccMailForward") : StringPool.BLANK %>'>
				<aui:validator name="required" />
				<aui:validator name="email" />
			</aui:input>
			<aui:input name="verify_forward" label="verify" size="25" value='<%=Validator.isNotNull(selAccount.getAttribute("esuccMailForward")) ? selAccount.getAttribute("esuccMailForward") : StringPool.BLANK %>'>
				<aui:validator name="required" />
				<aui:validator name="email" />
				<aui:validator name="equalTo" >
					'#<portlet:namespace />forward'
				</aui:validator>
			</aui:input>
		</div>
	</c:when>
	<c:otherwise>
		<div class="portlet-msg-error"><liferay-ui:message key="you-do-not-have-sufficient-privileges-to-edit-this-account-forward" /></div>
	</c:otherwise>
	</c:choose>
	<aui:button-row cssClass="dialog-footer">
		<div class="buttons-left">	
			<aui:button id="cancel" onClick='<%=backURL %>' value="cancel"/>
			<c:if test="<%=PermissionsChecker.hasPermission(callingAccount, selAccount, PermissionsChecker.PERMISSION_ACCOUNT_FORWARD) %>" >
				<aui:button type="submit" value="submit" />
			</c:if>
		</div>
	</aui:button-row>
</aui:form>

<aui:script>	
	var <portlet:namespace />deleteForward = function() {
		var A = AUI();
		
		A.one('#<portlet:namespace />delete').val("true");
		A.one('#<portlet:namespace />fm').submit();
	}
</aui:script>
