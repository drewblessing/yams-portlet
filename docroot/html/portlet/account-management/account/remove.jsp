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
List<JobQueue> removalJobs = PortletUtil.getRemovalJobsByEmailAddress(selAccount);
// Check Quartz scheduler to see if the user is scheduled for removal
boolean isEnabled = selAccount.getAttribute("esuccAccountEnabled").equalsIgnoreCase("true");
boolean isScheduledForRemoval = false;
boolean hasRemovePermission = PermissionsChecker.hasPermission(callingAccount, selAccount,
		PermissionsChecker.PERMISSION_ACCOUNT_REMOVE);
boolean hasRemoveForcePermission = PermissionsChecker.hasPermission(callingAccount, selAccount,
		PermissionsChecker.PERMISSION_ACCOUNT_REMOVE_FORCE);
String removalDate = StringPool.BLANK;

for (JobQueue job : removalJobs) {
	isScheduledForRemoval = true;
	DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
	removalDate = df.format(job.getJobDate());
}
%>
<liferay-ui:error key="insufficient-privileges" message="insufficient-privileges" />
<liferay-ui:error key="remove-account-failed" message="remove-account-failed" />

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
	<c:when test='<%=user.isDefaultUser() || hasRemovePermission || hasRemoveForcePermission %>'>

	<c:choose>
		<c:when test='<%=!isEnabled %>'>
			<div class="portlet-msg-error"><liferay-ui:message key="this-account-has-been-removed" /></div>
		</c:when>
		<c:when test="<%=isScheduledForRemoval %>">
			<div class="portlet-msg-alert"><liferay-ui:message key="this-account-is-scheduled-for-removal-on-x" 
					arguments="<%=new String[]{removalDate}%>"/> </div>
			<c:if test='<%=user.isDefaultUser() || hasRemoveForcePermission %>'>
				<portlet:actionURL name="deleteScheduledRemovalJob" var="deleteScheduledRemovalJobURL" >
					<portlet:param name="backURL" value="<%=backURL %>" />
				</portlet:actionURL>
				
				<liferay-ui:message key="you-may-delete-the-scheduled-removal-job-by-clicking-the-delete-button" />
			
				<aui:form method="POST" name="fm" id="fm" action="<%=deleteScheduledRemovalJobURL.toString() %>">
					<aui:input type="hidden" name="uidNumber" value='<%=selAccount.getAttribute("uidNumber") %>' />
					<aui:input type="hidden" name="delete" />
					<aui:button name="deleteButton" value="delete" onClick='<%= renderResponse.getNamespace() + "deleteJob()" %>' />
					</aui:form>
			</c:if>
		</c:when>
		<c:otherwise>
				<c:if test='<%=user.isDefaultUser() || hasRemovePermission %>'> 
						<liferay-util:include
								page="<%=PortletUtil.ACCT_MGMT_ACCOUNT_REMOVE_SCHEDULED_JSP %>"
								servletContext="<%=this.getServletContext() %>">
							<liferay-util:param name="uidNumber" value='<%=selAccount.getAttribute("uidNumber") %>' />
							<liferay-util:param name="backURL" value="<%= backURL %>" />
						</liferay-util:include>
				</c:if>
				<c:if test='<%=user.isDefaultUser() || hasRemoveForcePermission %>'>
						<liferay-util:include
								page="<%=PortletUtil.ACCT_MGMT_ACCOUNT_REMOVE_FORCE_JSP %>"
								servletContext="<%=this.getServletContext() %>">
							<liferay-util:param name="uidNumber" value='<%=selAccount.getAttribute("uidNumber") %>' />
							<liferay-util:param name="backURL" value="<%= backURL %>" />
						</liferay-util:include>
				</c:if>
		</c:otherwise>
	</c:choose>

	</c:when>
	<c:otherwise>
		<div class="portlet-msg-error"><liferay-ui:message key="you-do-not-have-sufficient-privileges-to-remove-this-account" /></div>
	</c:otherwise>
</c:choose>

<aui:script>	
	var <portlet:namespace />deleteJob = function() {
		var A = AUI();
		
		if (confirm("You are about to stop the scheduled removal of this account. Click OK to continue.\n")) {
			A.one('#<portlet:namespace />delete').val("true");
			A.one('#<portlet:namespace />fm').submit();
		}
	}
</aui:script>


	