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
long requestStep = ParamUtil.getLong(request,"step", 1);
System.out.println(requestStep);
%>

<portlet:renderURL var="formNavigationPrevURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="<%=PortletUtil.ACCT_MGMT_ACCOUNT_ADD_WIZARD_FORM_NAVIGATION_JSP %>" />
	<portlet:param name="step" value="<%=String.valueOf(requestStep - 1) %>" />
</portlet:renderURL>
<portlet:renderURL var="formNavigationNextURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="<%=PortletUtil.ACCT_MGMT_ACCOUNT_ADD_WIZARD_FORM_NAVIGATION_JSP %>" />
	<portlet:param name="step" value="<%=String.valueOf(requestStep + 1) %>" />
</portlet:renderURL>
<portlet:resourceURL var="submissionURL" />

<div class="add-account-instructions-text">
	<c:choose>
		<c:when test="<%=requestStep == 1 %>">
			<liferay-ui:message key="step-1-instructions" />
		</c:when>
		<c:when test="<%=requestStep == 2 %>">
			<liferay-ui:message key="step-2-instructions" />
		</c:when>
		<c:when test="<%=requestStep == 3 %>">
			<liferay-ui:message key="step-3-instructions" />
		</c:when>
	</c:choose>
</div>
<aui:button-row>
	<c:if test='<%=requestStep != 1 %>' >
		<aui:button value="previous" id="prevButton"/>
	</c:if>
	<c:if test='<%=requestStep != 3 %>' >
		<aui:button value="next" id="nextButton"/>
	</c:if>
	<c:if test='<%=requestStep == 3 %>' >
		<aui:button value="submit" id="submitButton"/>
	</c:if>
</aui:button-row>

<aui:script use="aui-core">
	var alloyStep = <%=requestStep %>;
	var nextButton = A.one('#nextButton');
	var prevButton = A.one('#prevButton');
	if (prevButton != null) {
		A.one('#prevButton').on(
			'click',
			function() {
				previousStep(alloyStep);
			}
		);
	}
	if (nextButton != null) {
		nextButton.on(
			'click',
			function() {
				if (alloyStep == 1) {
					processStep1();
				}
				nextStep(alloyStep);
			}
		);
	}
	A.all('.step1-input > .aui-field-content > .aui-field-element > .aui-field-input').on(
		'focus',
		function() {
			if (alloyStep != 1) {
				unplugAndRemoveActive(alloyStep);
				alloyStep = 1;
				<portlet:renderURL var="formNavigationURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
					<portlet:param name="jspPage" value="<%=PortletUtil.ACCT_MGMT_ACCOUNT_ADD_WIZARD_FORM_NAVIGATION_JSP %>" />
					<portlet:param name="step" value="1" />
				</portlet:renderURL>
				plugAndActivate(alloyStep,'<%=formNavigationURL %>');
			}
		}
	);
	A.all('.step2-input > .aui-field-content > .aui-field-element > .aui-field-input').on(
		'focus',
		function() {
			if (alloyStep != 2) {
				unplugAndRemoveActive(alloyStep);
				alloyStep = 2;
				<portlet:renderURL var="formNavigationURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
					<portlet:param name="jspPage" value="<%=PortletUtil.ACCT_MGMT_ACCOUNT_ADD_WIZARD_FORM_NAVIGATION_JSP %>" />
					<portlet:param name="step" value="2" />
				</portlet:renderURL>
				plugAndActivate(alloyStep,'<%=formNavigationURL %>');
			}
		}
	);
	A.all('.step3-input > .aui-field-content > .aui-field-element > .aui-field-input').on(
		'focus',
		function() {
			if (alloyStep != 3) {
				unplugAndRemoveActive(alloyStep);
				alloyStep = 3;
				<portlet:renderURL var="formNavigationURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
					<portlet:param name="jspPage" value="<%=PortletUtil.ACCT_MGMT_ACCOUNT_ADD_WIZARD_FORM_NAVIGATION_JSP %>" />
					<portlet:param name="step" value="3" />
				</portlet:renderURL>
				plugAndActivate(alloyStep,'<%=formNavigationURL %>');
			}
		}
	);
	function previousStep(step) {
		unplugAndRemoveActive(step);
		step--;
		plugAndActivate(step,'<%=formNavigationPrevURL %>');
	}
	function nextStep(step) {
		unplugAndRemoveActive(step);
		step++;
		plugAndActivate(step,'<%=formNavigationNextURL %>');
	}
	function plugAndActivate(step, url) {
		var currentStep = A.one("#step" + step);
		currentStep.addClass('active-step').one('.add-account-instructions').plug(
			A.Plugin.IO, {uri: url}
		);
	}
	function unplugAndRemoveActive(step) {
		A.one("#step" + step).removeClass('active-step').one('.add-account-instructions').unplug(A.Plugin.IO).html('');
	}
	function processStep1() {
		var title = A.one("#<portlet:namespace />title option:selected").text();
		var firstName = A.one("#<portlet:namespace />firstName").val();
		var lastName = A.one("#<portlet:namespace />lastName").val();
		
		var request = A.io.request('<%=submissionURL %>', {
			method: 'POST',
			uri: '<%=submissionURL %>',
			dataType: 'json',
			data: {
				cmd: '<%=AccountManagement.PROCESS_ACCOUNT_NAME %>',
				title: title,
				firstName: firstName,
				lastName: lastName
			},
			on: {
				success: function() {
					var message = this.get('responseData');
					var emailAddressInput = A.one("#<portlet:namespace />emailAddress");
					var screenNameInput = A.one("#<portlet:namespace />screenName");
					if (emailAddressInput.val() == "") {
						emailAddressInput.val(message.emailAddress);
					}
					if (screenNameInput.val() == "") {
						screenNameInput.val(message.screenName);
					}
				},
				failure: function() {
					alert("failure");
				}	
			}
		});
		
<!-- 		var step2 = A.one("#step2"); -->
<!-- 		step2.plug( -->
<!-- 			A.Plugin.IO, { -->
<!-- 				method: 'POST', -->
<%-- 				uri: '<%=submissionURL %>', --%>
<!-- 				dataType: 'json', -->
<!-- 				data: { -->
<%-- 					cmd: '<%=AccountManagement.PROCESS_ACCOUNT_NAME %>', --%>
<!-- 					title: title, -->
<!-- 					firstName: firstName, -->
<!-- 					lastName: lastName -->
<!-- 				}, -->
<!-- 				on: { -->
<!-- 					success: function() { -->
<!-- 						var message = this.get('responseData'); -->
<!-- 					}, -->
<!-- 					failure: function() { -->
<!-- 						alert("failure"); -->
<!-- 					}	 -->
<!-- 				} -->
<!-- 			} -->
<!-- 		); -->
	}
</aui:script>