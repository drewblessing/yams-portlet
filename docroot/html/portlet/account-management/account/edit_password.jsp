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

<portlet:actionURL name="editPassword" var="editPasswordURL" >
	<portlet:param name="backURL" value="<%=backURL %>" />
</portlet:actionURL>

<aui:form method="POST" name="editPasswordFm" id="editPasswordFm" action="<%=editPasswordURL %>">
<aui:input type="hidden" name="uidNumber" value='<%=selAccount.getAttribute("uidNumber") %>' />

	<c:choose>
		<c:when test="<%=PermissionsChecker.hasPermission(callingAccount, selAccount, PermissionsChecker.PERMISSION_ACCOUNT_EDIT_PASSWORD) %>" >
		<div class="section edit-password aui-column aui-w25">
			<aui:input type="hidden" name="uid" value='<%=selAccount.getUid() %>' />
			<aui:input type="password" name="password" size="25">
				<aui:validator name="required" />
				<aui:validator name="rangeLength">
					[8,64]
				</aui:validator>
			</aui:input>
			<aui:input type="password" name="verify" size="25">
				<aui:validator name="required" />
				<aui:validator name="rangeLength">
					[8,64]
				</aui:validator>
				<aui:validator name="equalTo" >
					'#<portlet:namespace />password'
				</aui:validator>
			</aui:input>
		</div>
		<div class="section edit-password aui-column aui-w25">
			Passwords must adhere to the following rules:
			<jsp:include page="<%=PortletUtil.ACCT_MGMT_ACCOUNT_PASSWORD_POLICY_TEXT_JSP %>" />
			<liferay-ui:error key="password-fields-must-match" message="password-fields-must-match" />
			<c:if test="<%=errors.size() > 0 %>" >
				<c:forEach var="error" items="<%=errors %>">
					<div class="portlet-msg-error">${error}</div>
				</c:forEach>
			</c:if>
		</div>
	</c:when>
	<c:otherwise>
		<div class="portlet-msg-error"><liferay-ui:message key="you-do-not-have-sufficient-privileges-to-edit-this-account-password" /></div>
	</c:otherwise>
	</c:choose>
	<aui:button-row cssClass="dialog-footer">
		<div class="buttons-left">			
			<aui:button id="cancel" onClick='<%=backURL %>' value="cancel"/>
			<c:if test="<%=PermissionsChecker.hasPermission(callingAccount, selAccount, PermissionsChecker.PERMISSION_ACCOUNT_EDIT_PASSWORD) %>" >
				<aui:button type="submit" value="submit" />
			</c:if>
		</div>

		<div class="step" id="<portlet:namespace />step">
			<span>
				<liferay-ui:message arguments="<%= new Integer[] {1, 1}%>" key="step-x-of-x" />
			</span>
		</div>
	</aui:button-row>
</aui:form>