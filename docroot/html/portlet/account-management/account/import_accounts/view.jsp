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

<%@ include file="/html/portlet/account-management/account/import_accounts/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL", redirect);

List<EntityGroup> groups = AccountManagement.getAllGroups();
String uploadProgressId = PwdGenerator.getPassword(PwdGenerator.KEY3, 4);
%>

<liferay-ui:error key="could-not-upload-file-please-try-again" message="could-not-upload-file-please-try-again" />
<liferay-ui:error key="invalid-header-x" message="invalid-header-x" />

<c:if test="<%= portletName.equals(PortletKeys.ACCOUNT_MANAGEMENT) %>">
	<liferay-util:include
			page="<%=PortletUtil.ACCT_MGMT_TOOLBAR_JSP %>"
			servletContext="<%=this.getServletContext() %>">
		<liferay-util:param name="toolbarItem" value="import" />
	</liferay-util:include>
</c:if>

<liferay-ui:header
	backURL="<%= backURL %>"
	title='import-accounts'
/>

<portlet:actionURL name="importAccountsUploadCSV" var="importAccountsUploadCSVURL" >
	<portlet:param name="cancelURL" value="<%=backURL %>" />
</portlet:actionURL>

<div class="import-accounts">
	<div class="instructions">
		<liferay-ui:message key="account-import-instructions" />
	</div>
	<div class="header-values bold">
		<c:forEach var="headerValue" items="<%=AccountManagement.possibleCSVHeaderValues %>" varStatus="status">
			${headerValue}${not status.last ? ',' : ''}<%=StringPool.SPACE %>
		</c:forEach>
	</div>
	
	<aui:form method="POST" action="<%=importAccountsUploadCSVURL.toString() %>"  enctype="multipart/form-data" name="fm" id="fm">
		<aui:input type="hidden" name="callingAccount" value="<%=callingAccount.getUid() %>" />
				
		<c:choose>
		<c:when test="<%= PermissionsChecker.hasGroupPermission(callingAccount, PermissionsChecker.PERMISSION_ACCOUNT_ADD, StringPool.NULL) %>">
		
				<aui:select name="<%=UserDisplayTerms.GROUP %>"
						showEmptyOption="<%= true %>" >
					<%
					// Yes, this should probably be a JSTL <c:forEach> tag but I don't have time right now
					for (EntityGroup group : groups) {
					%>
						<c:if test="<%= PermissionsChecker.hasGroupPermission(callingAccount, PermissionsChecker.PERMISSION_ACCOUNT_ADD, group) %>">
							<aui:option name='<%=group.getAttribute("dn") %>' value='<%=group.getAttribute("dn") %>'>
								<%=group.getDisplayName() %>
							</aui:option>
						</c:if>
					<%
					}
				%>
				</aui:select>
		
			<aui:fieldset>
				<aui:input type="file" name="csvFile" />
				<liferay-ui:upload-progress
				    id="<%= uploadProgressId %>"
				    message="uploading"
				    redirect="<%= backURL %>"
				 />
			 </aui:fieldset>
		
		</c:when>
		<c:otherwise>
			<div class="portlet-msg-error"><liferay-ui:message key="you-do-not-have-sufficient-privileges-to-import-accounts-for-any-groups" /></div>
		</c:otherwise>
		</c:choose>
		<aui:button-row>
			<div class="buttons-left">			
				<aui:button id="cancel" onClick='<%=backURL %>' value="cancel"/>
				<c:if test="<%= PermissionsChecker.hasGroupPermission(callingAccount, PermissionsChecker.PERMISSION_ACCOUNT_ADD, StringPool.NULL) %>">
					<aui:button type="submit" value="next"  onClick='<%= uploadProgressId + ".startProgress(); return true;" %>' />
				</c:if>
			</div>
	
			<div class="step" id="<portlet:namespace />step">
				<span>
					<liferay-ui:message arguments="<%= new Integer[] {1, 3}%>" key="step-x-of-x" />
				</span>
			</div>
		</aui:button-row>
	</aui:form>
</div>