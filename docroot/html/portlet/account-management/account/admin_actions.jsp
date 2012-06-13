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
UserSearch searchContainer = (UserSearch)request.getAttribute("liferay-ui:search:searchContainer");

String redirect = searchContainer.getIteratorURL().toString();

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
Account selAccount = (Account)row.getObject();
//long groupId = themeDisplay.getLayout().getGroupId();
String name = Account.class.getName();
List<String> mail = selAccount.getMail();
String uidStripped = selAccount.getUid().replaceAll("[^a-zA-Z0-9]+","");
%>

<liferay-ui:icon-menu>

<c:if test="<%= permissionChecker.isOmniadmin() %>" >
	<portlet:renderURL var="editAccountRenderURL" >
		<portlet:param name="jspPage" value="<%=PortletUtil.ACCT_MGMT_ACCOUNT_EDIT_JSP %>" />
		<portlet:param name="redirect" value="<%= redirect %>" />
		<portlet:param name="uid" value="<%=selAccount.getUid() %>" />
	</portlet:renderURL>
	
	<span id="edit-account-<%=uidStripped %>">
		<liferay-ui:icon image="edit" message="edit" url="<%=editAccountRenderURL %>" />
	</span>	
</c:if>

<c:if test="<%=mail.contains(user.getEmailAddress()) || permissionChecker.isOmniadmin() %>" >
	<portlet:renderURL var="changePasswordRenderURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="jspPage" value="/<%=PortletUtil.ACCT_MGMT_ACCOUNT_CHANGE_PASSWORD_JSP %>" />
		<portlet:param name="uid" value="<%=selAccount.getUid() %>" />
	</portlet:renderURL>
	
	<span id="change-password-<%=uidStripped %>">
		<liferay-ui:icon image="key" message="change-password" url="javascript:;" />
	</span>
	<portlet:resourceURL var="changePasswordResourceURL" />
	
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
								uri: '<%=changePasswordResourceURL %>',
								dataType: 'json',
								form: {
									id: '<portlet:namespace />changePasswordFm'
								},
								on: {
									success: function() {
										var message = this.get('responseData');
										
										if (message.response.toLowerCase() == 'success') {
											changePasswordDialog.close();
										} else {
											alert(message.response);
										}
									},
									failure: function() {
										alert("failure");
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

