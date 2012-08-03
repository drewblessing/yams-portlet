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

<%@ include file="/html/portlet/account-management/account/import_accounts/init.jsp" %>

<%
String doneURL = (String)request.getAttribute("doneURL");
List<String[]> failedImports = (List<String[]>)request.getAttribute("failedImports");
Integer importCount = (Integer)request.getAttribute("importCount");

%>

<c:if test="<%= portletName.equals(PortletKeys.ACCOUNT_MANAGEMENT) %>">
	<liferay-util:include
			page="<%=PortletUtil.ACCT_MGMT_TOOLBAR_JSP %>"
			servletContext="<%=this.getServletContext() %>">
		<liferay-util:param name="toolbarItem" value="import" />
	</liferay-util:include>
</c:if>

<liferay-ui:header
	backURL="<%= doneURL %>"
	title="import-summary"
/>

<div class="import-accounts">
	<liferay-ui:message key="import-summary-instructions" />
	<div class="statistics">
		<aui:column column="<%=true %>" cssClass="aui-w10" >
			<liferay-ui:message key="successful-x" arguments="<%=importCount - failedImports.size() %>" />
		</aui:column>
		<aui:column column="<%=true %>" cssClass="aui-w10" >
			<liferay-ui:message key="failed-x" arguments="<%=failedImports.size() %>" />
			
			<c:if test="<%=failedImports.size() > 0 %>" >
				<aui:button id="download-csv" value="download-csv"/>
			</c:if>
		</aui:column>
	</div>
	<aui:button-row>
		<div class="buttons-left">			
			<aui:button id="done" onClick='<%=doneURL %>' value="done"/>
		</div>

		<div class="step" id="<portlet:namespace />step">
			<span>
				<liferay-ui:message arguments="<%= new Integer[] {3, 3}%>" key="step-x-of-x" />
			</span>
		</div>
	</aui:button-row>
</div>