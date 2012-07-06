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
long step = ParamUtil.getLong(request, "step", 1);
List<Group> groups = AccountManagement.getAllGroups();
%>

<div class="aui-layout">
	<div id='step1' class="aui-layout-content">
		<div class="aui-column aui-w50 aui-column-first">
			<h3>Step 1</h3>
			<aui:fieldset>
				<aui:select name="group" label="primary-group" 
						showEmptyOption="<%= true %>" cssClass="step1-input" >
					<%
					for (Group group : groups) {
					%>
						<c:if test="<%=PermissionsChecker.hasGroupPermission(
								callingAccount, PermissionsChecker.PERMISSION_ACCOUNT_ADD, group) %>">
							<aui:option name="" value='<%=group.getAttribute("dn") %>'>
								<%=group.getDisplayName() %>
							</aui:option>
						</c:if>
					<%
					}
					%>
				</aui:select>
				<!-- Use the title values from the portal for ease -->
				<aui:select name="title"  
						listType="<%= ListTypeConstants.CONTACT_PREFIX %>" 
						showEmptyOption="<%= true %>" cssClass="step1-input"/>
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
		<div class="aui-column aui-w50 aui-column-first" >
			<h3>Step 2</h3>
			<aui:fieldset>
			<aui:input name="<%=UserDisplayTerms.EMAIL_ADDRESS %>" cssClass="step2-input" 
					inlineField="<%=true %>"  >
				<aui:validator name="required" />
			</aui:input>
			<c:choose>
				<c:when test="<%=PropsValues.ACCOUNT_EMAIL_ADDRESS_DOMAIN_OVERRIDE_ENABLED %>" >
					<span class="domain"><%=StringPool.AT %></span>
					<aui:input 
						name="domain" 
						label="<%=StringPool.BLANK %>" 
						cssClass="step2-input domain" 
						value="<%=PropsValues.ACCOUNT_EMAIL_ADDRESS_DOMAIN_DEFAULT %>" 
						inlineField="<%=true %>" 
					>
						<aui:validator name="required" />
					</aui:input>
				</c:when>
				<c:otherwise>
					<span class="step2-input domain">
						<%=StringPool.AT %><%=PropsValues.ACCOUNT_EMAIL_ADDRESS_DOMAIN_DEFAULT %>
					</span>
				</c:otherwise>
			</c:choose>
				<aui:input name="<%=UserDisplayTerms.SCREEN_NAME %>" cssClass="step2-input" >
					<aui:validator name="required" />
				</aui:input>
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

<aui:script use="aui-core">
	var stepOnLoad = A.one('#step<%=step %>');
	stepOnLoad.addClass('active-step').one(
			'.add-account-instructions').plug(
					A.Plugin.IO, {uri: '<%=formNavigationURL %>'});
</aui:script>