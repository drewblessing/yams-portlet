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
%>

<div class="aui-layout">
	<div id='step1' class="aui-layout-content">
		<div class="aui-column aui-w50 aui-column-first">
			<h3>Step 1</h3>
			<aui:fieldset>
				<!-- Use the title values from the portal for ease -->
				<aui:select name="title"  
						listType="<%= ListTypeConstants.CONTACT_PREFIX %>" 
						showEmptyOption="<%= true %>" cssClass="step1-input"/>
				<aui:input name="firstName" cssClass="step1-input" >
					<aui:validator name="required" />
				</aui:input>
				<aui:input name="lastName" cssClass="step1-input"  >
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
			<aui:input name="emailAddress" cssClass="step2-input" >
				<aui:validator name="required" />
			</aui:input>
			<aui:input name="screenName" cssClass="step2-input" >
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
			<aui:input type="password" name="password" cssClass="step3-input" >
				<aui:validator name="required" />
			</aui:input>
			<aui:input type="password" name="verify" cssClass="step3-input" >
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