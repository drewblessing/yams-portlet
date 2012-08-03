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
Account selAccount = (Account)request.getAttribute("selAccount");
String cancelURL = (String)request.getAttribute("cancelURL");
List<EntityGroup> groups = AccountManagement.getAllGroups();
String group = (String)request.getAttribute("group");
long permissionsId = (Long)request.getAttribute("permissionsId");
TreeMap<String, String> permissions = (TreeMap<String,String>)request.getAttribute("permissions");
%>

<c:if test="<%= portletName.equals(PortletKeys.ACCOUNT_MANAGEMENT) %>">
	<liferay-util:include
			page="<%=PortletUtil.ACCT_MGMT_TOOLBAR_JSP %>"
			servletContext="<%=this.getServletContext() %>">
	</liferay-util:include>
</c:if>

<liferay-ui:header
	backURL="<%= cancelURL %>"
	title='<%= selAccount.getDisplayName() %>'
/>

<portlet:actionURL name="editPermissions" var="editPermissionsURL">
	<portlet:param name="redirect" value="<%=cancelURL %>" />
</portlet:actionURL>

<aui:form method="POST" action="<%=editPermissionsURL %>" name="editPermissionsFm" id="editPermissionsFm">
	<div class="section choose-permissions" >
		<h3><liferay-ui:message key="choose-permissions" /></h3>
		
		<aui:input type="hidden" name="<%=UserDisplayTerms.CMD %>" value='<%=AccountManagement.CHOOSE_GROUP_CMD %>' />
		<aui:input type="hidden" name="uidNumber" value='<%=selAccount.getAttribute("uidNumber") %>' />
		<aui:input type="hidden" name="callingAccount" value='<%=callingAccount.getAttribute("uidNumber") %>' />
		<aui:input type="hidden" name="permissionsId" value='<%=permissionsId %>' />
		<aui:input type="hidden" name="group" value='<%=group %>' />
		
<%-- 		<c:choose> --%>
		<%
		if (permissions.size() > 0) {
		%>
<%-- 		<c:when test="${permissions}" > --%>
			<div class="permissions">
				<aui:column column="<%=true %>" cssClass="aui-w25">
					<aui:fieldset cssClass="account-permissions">
					<h4><liferay-ui:message key="account" /></h4>
					<%
					for (Map.Entry<String, String> permission : permissions.entrySet()) {
						if (permission.getKey().matches("^account.*")){
					%>	
						<aui:input type='checkbox' name='<%=permission.getKey() %>' value='<%= permission.getValue().equals("true") ? "1" : "false" %>' checked='<%= permission.getValue().equals("true") ? true : false %>' />
					<%
						}
					}
					%>
					</aui:fieldset>
				</aui:column>
				<aui:column column="<%=true %>" cssClass="aui-w25">
					<aui:fieldset cssClass="group-permissions">
					<h4><liferay-ui:message key="group" /></h4>
					<%
					for (Map.Entry<String, String> permission : permissions.entrySet()) {
						if (permission.getKey().matches("^group.*")){
					%>	
						<aui:input type='checkbox' name='<%=permission.getKey() %>' value='<%= permission.getValue().equals("true") ? "1" : "false" %>' checked='<%= permission.getValue().equals("true") ? true : false %>' />
					<%
						}
					}
					%>
					</aui:fieldset>
				</aui:column>
			</div>
<%-- 		</c:when> --%>
		<%
		} else {
		%>
<%-- 		<c:otherwise> --%>
			<div class="portlet-msg-error"><liferay-ui:message key="you-do-not-have-sufficient-privileges-to-grant-permissions-to-this-user-for-this-group" /></div>
<%-- 		</c:otherwise> --%>
<%-- 		</c:choose> --%>
		<%
		} 
		%>
	</div>
	<aui:button-row>
		<div class="buttons-left">			
			<aui:button id="cancel" onClick='<%=cancelURL %>' value="cancel"/>
			<%
			if (permissions.size() > 0) {
			%>
<%-- 			<c:if test="${permissions}" > --%>
				<aui:button type="submit" value="submit" />
<%-- 			</c:if> --%>
			<%
			}
			%>
		</div>

		<div class="step" id="<portlet:namespace />step">
			<span>
				<liferay-ui:message arguments="<%= new Integer[] {2, 2}%>" key="step-x-of-x" />
			</span>
		</div>
	</aui:button-row>
</aui:form>