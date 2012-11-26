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

Account selAccount = PortletUtil.getAccountFromRequest(renderRequest);
request.setAttribute("account.selAccount", selAccount);
%>

<c:if test="<%= user.isDefaultUser() || PermissionsChecker.hasPermission(callingAccount, selAccount,
			PermissionsChecker.PERMISSION_ACCOUNT_REMOVE) %>">
	<portlet:actionURL name="removeAccountOnSchedule" var="removeOnScheduleURL" >
		<portlet:param name="backURL" value="<%=backURL %>" />
	</portlet:actionURL>
	
	<p><liferay-ui:message key="choose-a-date-to-remove-this-account-dates-less-than-one-week-from-today-may-be-restricted-based-on-your-privileges" /></p>
	
	<aui:form method="POST" action="<%=removeOnScheduleURL.toString() %>" name="yamsFm" id="yamsFm">
		<aui:input type="hidden" name="<%=UserDisplayTerms.CMD %>" value="<%=AccountManagement.EDIT_ACCOUNT_CMD %>" />
		<aui:input type="hidden" name="esuccMailPrimaryLocalPart" value='<%=selAccount.getAttribute("esuccMailPrimaryLocalPart") %>' />
		<aui:input type="hidden" name="esuccMailPrimaryDomain" value='<%=selAccount.getAttribute("esuccMailPrimaryDomain") %>' />
		<aui:input type="hidden" name="uidNumber" value='<%=selAccount.getAttribute("uidNumber") %>' />
	
		<aui:input type="text" name="remove-date" />
		<div id='<portlet:namespace />date'></div>
	
			<aui:button-row >		
					<aui:button id="cancel" onClick='<%=backURL %>' value="cancel"/>
					<aui:button type="submit" value="submit" />
			</aui:button-row>
	</aui:form>
	<aui:script use="aui-core,aui-calendar,aui-datepicker">
		var selectedDate=new Date();
		selectedDate.setDate(selectedDate.getDate()+7);
		var minDate=new Date();
		<!-- If user is privileged let them set a date sooner than one week from today -->
		if (<%=PermissionsChecker.hasPermission(callingAccount, selAccount,
				PermissionsChecker.PERMISSION_ACCOUNT_REMOVE_FORCE) %>) {
			minDate.setDate(minDate.getDate());
		} else {
			minDate.setDate(minDate.getDate()+6);
		}
		var maxDate=new Date();
		maxDate.setDate(maxDate.getDate()+372);
		var datepicker = new A.DatePicker({
	        trigger: '#<portlet:namespace/>remove-date',
	        calendar: {
	            allowNone: false, 
	            dateFormat: '%m/%d/%y', 
	            dates: [ selectedDate ], 
	            maxDate: maxDate, 
	            minDate: minDate, 
	            selectMultipleDates: false, 
	            showToday: false
	        },
	        setValue: true
    	}).render();
		
	</aui:script>
</c:if>