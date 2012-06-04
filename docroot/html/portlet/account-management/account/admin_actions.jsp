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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
Account selAccount = (Account)row.getObject();
//long groupId = themeDisplay.getLayout().getGroupId();
String name = Account.class.getName();
List<String> mail = selAccount.getMail();
String uidStripped = selAccount.getUid().replaceAll("[^a-zA-Z0-9]+","");
%>

<liferay-ui:icon-menu>

<c:if test="<%= permissionChecker.isOmniadmin() %>" >
	<portlet:renderURL var="editAccountRenderURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="jspPage" value="/html/portlet/account-management/account/edit_account.jsp" />
		<portlet:param name="uid" value="<%=selAccount.getUid() %>" />
	</portlet:renderURL>
	
	<span id="edit-account-<%=uidStripped %>">
		<liferay-ui:icon image="edit" message="edit" url="javascript:void(0)" />
	</span>
	<portlet:actionURL 
		var="editAccountActionURL" 
		name="editAccount" 
		windowState="<%= LiferayWindowState.POP_UP.toString()%>" 
	/>
	
	<aui:script use="aui-dialog,aui-overlay-manager,aui-io,io-form">
	A.one('#edit-account-<%=uidStripped %>').on(
		'click',
		function() {
			var editAccountDialog = new A.Dialog({
				buttons: [ 
				{ 
					label: '<liferay-ui:message key="submit" />', 
					handler: function() {
						editAccountDialog.unplug(A.Plugin.IO);
						editAccountDialog.plug(
							A.Plugin.IO, {
								method: 'POST',
								uri: '<%=editAccountActionURL %>',
								form: {
									id: '<portlet:namespace />changePasswordFm'
								},
								after: {
									success: function(event, id, xhr) {
										alert("Password may have been changed! We're not really sure yet :)");
									}
								}
							}
						);
					} 
				}, 
				{ 
					label: '<liferay-ui:message key="cancel" />', 
					handler: function() { 
						this.close(); 
					} 
				}],
				title: '<liferay-ui:message key="edit" />',
				width: 960,
				align: Liferay.Util.Window.ALIGN_CENTER,
				constrain2view: true,
				group: 'default',
				stack: true,
				modal: true,
				destroyOnClose: true
			}).plug(
				A.Plugin.IO, {
					uri: '<%=editAccountRenderURL %>'
				}
			).render();
		}
	);
	</aui:script>
</c:if>

<c:if test="<%=mail.contains(user.getEmailAddress()) || permissionChecker.isOmniadmin() %>" >
	<portlet:renderURL var="changePasswordRenderURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="jspPage" value="/html/portlet/account-management/account/change_password.jsp" />
		<portlet:param name="uid" value="<%=selAccount.getUid() %>" />
	</portlet:renderURL>
	
	<span id="change-password-<%=uidStripped %>">
		<liferay-ui:icon image="key" message="change-password" url="javascript:void(0)" />
	</span>
	<portlet:actionURL 
		var="changePasswordActionURL" 
		name="changePassword" 
		windowState="<%= LiferayWindowState.POP_UP.toString()%>" 
	/>
	
	<aui:script use="aui-dialog,aui-overlay-manager,aui-io,io-form">
	A.one('#change-password-<%=uidStripped %>').on(
		'click',
		function() {
			var changePasswordDialog = new A.Dialog({
				buttons: [ 
				{ 
					label: '<liferay-ui:message key="submit" />', 
					handler: function() {
						changePasswordDialog.unplug(A.Plugin.IO);
						changePasswordDialog.plug(
							A.Plugin.IO, {
								method: 'POST',
								uri: '<%=changePasswordActionURL %>',
								form: {
									id: '<portlet:namespace />changePasswordFm'
								},
								after: {
									success: function(event, id, xhr) {
										alert("Password may have been changed! We're not really sure yet :)");
									}
								}
							}
						);
					} 
				}, 
				{ 
					label: '<liferay-ui:message key="cancel" />', 
					handler: function() { 
						this.close(); 
					} 
				}],
				title: '<liferay-ui:message key="change-password" />',
				height: 225,
				width: 225,
				align: {
					node: '#change-password-<%=uidStripped %>',
					points: [ 'rc','lc' ]
				},
				constrain2view: true,
				group: 'default',
				stack: true,
				modal: true,
				destroyOnClose: true
			}).plug(
				A.Plugin.IO, {
					uri: '<%=changePasswordRenderURL %>'
				}
			).render();
		}
	);
	</aui:script>
</c:if>

</liferay-ui:icon-menu>

