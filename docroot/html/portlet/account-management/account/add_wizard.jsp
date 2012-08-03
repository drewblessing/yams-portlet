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
	long step = ParamUtil.getLong(request, "step", 1);
List<EntityGroup> groups = AccountManagement.getAllGroups();
%>
<portlet:actionURL name="addAccount" var="addAccountURL" />

<aui:form method="POST" action="<%=addAccountURL.toString()%>" name="yamsFm" id="yamsFm">
<aui:input type="hidden" name="<%=UserDisplayTerms.CMD %>" value="<%=AccountManagement.ADD_ACCOUNT_CMD %>" />

	<div class="aui-layout">
		<div id='step1' class="aui-layout-content">
			<div class="aui-column aui-w50 aui-column-first">
				<h3>Step 1</h3>
				<aui:fieldset>
					<aui:select name="<%=UserDisplayTerms.PRIMARY_GROUP%>" label="entity" 
							showEmptyOption="<%=true%>" cssClass="step1-input" >
						<%
							for (EntityGroup group : groups) {
						%>
							<c:if test="<%=PermissionsChecker.hasGroupPermission(
									callingAccount, PermissionsChecker.PERMISSION_ACCOUNT_ADD, group) %>">
								<aui:option name='<%=group.getEsuccEntity() %>' value='<%=group.getEsuccEntity()%>'>
									<%=group.getEsuccEntity() %>
								</aui:option>
							</c:if>
						<%
						}
						%>
					</aui:select>
					<aui:select name="<%=UserDisplayTerms.ACCOUNT_TYPE %>" label="account-type" 
							cssClass="step1-input" >
						<aui:option value="select-an-entity-first">
							<liferay-ui:message key="select-an-entity-first" />
						</aui:option>
					</aui:select>
					<aui:select name="title" showEmptyOption="<%= true %>" cssClass="step1-input" first="<%=true %>" >
						<aui:option value="Dr.">
							<liferay-ui:message key="dr." />
						</aui:option>
						<aui:option value="Mr.">
							<liferay-ui:message key="mr." />
						</aui:option>
						<aui:option value="Mrs.">
							<liferay-ui:message key="mrs." />
						</aui:option>
						<aui:option value="Ms.">
							<liferay-ui:message key="ms." />
						</aui:option>
					</aui:select>
					<aui:input name="<%=UserDisplayTerms.FIRST_NAME %>" cssClass="step1-input" >
						<aui:validator name="required" />
					</aui:input>
					<aui:input name="<%=UserDisplayTerms.LAST_NAME %>" cssClass="step1-input"  >
						<aui:validator name="required" />
					</aui:input>
				</aui:fieldset>
			</div>
			
			<div class="add-account-instructions aui-column aui-w50 aui-column-last">
			<!-- Placeholder -->
			</div>
		</div>
		
		<div class="separator" ></div>
		
		<div id="step2" class="aui-layout-content">
			<div id="step2content" class="aui-column aui-w50 aui-column-first" >
				<h3>Step 2</h3>
				<aui:fieldset>
					<aui:input name="<%=UserDisplayTerms.EMAIL_ADDRESS %>" cssClass="step2-input" 
							inlineField="<%=true %>"  >
						<aui:validator name="required" />
					</aui:input>
					<span class="domain"><%=StringPool.AT %></span>
					<aui:select name="<%=UserDisplayTerms.DOMAIN %>" label="<%=StringPool.BLANK %>" 
							cssClass="step2-input domain" inlineField="<%=true %>">
					</aui:select>
<%-- 					<aui:input name="<%=UserDisplayTerms.SCREEN_NAME %>" cssClass="step2-input" > --%>
<%-- 						<aui:validator name="required" /> --%>
<%-- 					</aui:input> --%>
				</aui:fieldset>
			</div>
			
			<div class="add-account-instructions aui-column aui-w50 aui-column-last">
			<!-- Placeholder -->
			</div>
		</div>
		
		<div class="separator"></div>
		
		<div id="step3" class="aui-layout-content">
			<div class="aui-column aui-w50 aui-column-first">
				<h3>Step 3</h3>
				<aui:fieldset>
					<aui:input type="password" name="<%=UserDisplayTerms.PASSWORD %>" cssClass="step3-input" >
						<aui:validator name="required" />
					</aui:input>
					<aui:input type="password" name="<%=UserDisplayTerms.VERIFY %>" cssClass="step3-input" >
						<aui:validator name="required" />
					</aui:input>
				</aui:fieldset>
				</div>
				<div class="add-account-instructions aui-column aui-w50 aui-column-last">
				<!-- Placeholder -->
			</div>
		</div>
	</div>
	
	<portlet:renderURL var="formNavigationURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
		<portlet:param name="jspPage" value="<%=PortletUtil.ACCT_MGMT_ACCOUNT_ADD_WIZARD_FORM_NAVIGATION_JSP %>" />
	</portlet:renderURL>
	
	<portlet:resourceURL var="resourceURL" />
	
	<aui:script use="aui-core">
		var stepOnLoad = A.one('#step<%=step %>');
		stepOnLoad.addClass('active-step').one(
				'.add-account-instructions').plug(
						A.Plugin.IO, {uri: '<%=formNavigationURL %>'});
		
		A.one('#<portlet:namespace /><%=UserDisplayTerms.PRIMARY_GROUP %>').on(
			'click',
			function() {
				var group = A.one('#<portlet:namespace /><%=UserDisplayTerms.PRIMARY_GROUP %> option:selected');
				var accountType = A.one('#<portlet:namespace /><%=UserDisplayTerms.ACCOUNT_TYPE %>');
			
				var request = A.io.request('<%=resourceURL %>', {
					method: 'POST',
					uri: '<%=resourceURL %>',
					dataType: 'json',
					data: {
						'<%=UserDisplayTerms.CMD %>': '<%=AccountManagement.GET_ENTITY_ACCOUNT_TYPES %>',
						'<%=UserDisplayTerms.PRIMARY_GROUP %>': group.val(),
					},
					on: {
						success: function() {
							var message = this.get('responseData');
							accountType.html("<option value></option>");
							if (message['Staff']) {
									accountType.append("<option name='" + message['staff'] + "' value='Staff'><liferay-ui:message key='Staff' /></option>");
							}
							if (message['Student']) {
									accountType.append("<option name='" + message['student'] + "' value='Student'><liferay-ui:message key='Student' /></option>");
							}
						},
						failure: function() {
							alert("failure");
						}	
					}
				});
			}
		);
	</aui:script>
</aui:form>