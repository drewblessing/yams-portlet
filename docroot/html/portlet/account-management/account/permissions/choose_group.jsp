<%/**
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
 **/%>

<%@ include file="/html/portlet/init.jsp" %>
<%
	String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL", redirect);

Account selAccount = PortletUtil.getAccountFromRequest(renderRequest);
List<EntityGroup> groups = AccountManagement.getAllGroups();


System.out.println("Group dn: " + groups.get(0).getAttribute("dn"));

List<PermissionsDefined> permissionsDefined = PortletUtil.getPermissionsDefined();
%>

<c:if test="<%=portletName.equals(PortletKeys.ACCOUNT_MANAGEMENT)%>">
	<liferay-util:include
			page="<%=PortletUtil.ACCT_MGMT_TOOLBAR_JSP%>"
			servletContext="<%=this.getServletContext()%>">
	</liferay-util:include>
</c:if>

<liferay-ui:header
	backURL="<%=backURL%>"
	title='<%=selAccount.getDisplayName()%>'
/>

<h3><liferay-ui:message key="edit-permissions" /></h3>

<portlet:actionURL name="editPermissionsChooseGroup" var="editPermissionsChooseGroupURL" >
	<portlet:param name="cancelURL" value="<%=backURL%>" />
</portlet:actionURL>

<aui:form method="POST" action="<%=editPermissionsChooseGroupURL%>" name="fm" id="fm">
	<c:choose>
	<c:when test="<%=PermissionsChecker.hasGroupPermissionGrantable(callingAccount, null, StringPool.BLANK)%>" >
		<div class="section choose-group">
			<liferay-ui:message key="choose-an-organization-to-grant-permissions-for" />
			
			<aui:input type="hidden" name="<%=UserDisplayTerms.CMD%>" value="<%=AccountManagement.CHOOSE_GROUP_CMD%>" />
			<aui:input type="hidden" name="uidNumber" value='<%=selAccount.getAttribute("uidNumber") %>' />
			<aui:input type="hidden" name="callingAccount" value='<%=callingAccount.getAttribute("uidNumber") %>' />
			
			<aui:select name="<%=UserDisplayTerms.GROUP%>"
					showEmptyOption="<%=true%>" >
				<%
					// Yes, this should probably be a JSTL <c:forEach> tag but I don't have time right now
						for (EntityGroup group : groups) {
				%>
					<c:if test="<%=PermissionsChecker.hasGroupPermissionGrantable(
							callingAccount, null, group) %>">
						<aui:option name='<%=group.getAttribute("dn") %>' value='<%=group.getAttribute("dn") %>'>
							<%=group.getDisplayName() %>
						</aui:option>
					</c:if>
				<%
				}
			%>
			</aui:select>
		</div>
	</c:when>
	<c:otherwise>
		<div class="portlet-msg-error"><liferay-ui:message key="you-do-not-have-sufficient-privileges-to-grant-permissions-for-any-groups" /></div>
	</c:otherwise>
	</c:choose>
	<aui:button-row cssClass="dialog-footer">
		<div class="buttons-left">			
			<aui:button id="cancel" onClick='<%=backURL %>' value="cancel"/>
			<c:if test="<%=PermissionsChecker.hasGroupPermissionGrantable(callingAccount, null, StringPool.BLANK) %>" >
				<aui:button id="next" type="submit" value="next" />
			</c:if>
		</div>

		<div class="step" id="<portlet:namespace />step">
			<span>
				<liferay-ui:message arguments="<%= new Integer[] {1, 2}%>" key="step-x-of-x" />
			</span>
		</div>
	</aui:button-row>
</aui:form>