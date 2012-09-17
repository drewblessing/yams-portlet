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
String filePath = (String)request.getAttribute("filePath");

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
			<liferay-ui:message key="failed-x" arguments="<%=failedImports.size() != 0 ? failedImports.size()-1 : 0 %>" />
			
			<c:if test="<%=false %>">
<%-- 			<c:if test="<%=failedImports.size() > 0 %>" > --%>
				<aui:button id="download-csv" value="download-csv" onClick='<%=filePath %>' />
			</c:if>
		</aui:column>
	</div>
	<c:if test="<%=failedImports.size() > 1 %>">
		<liferay-ui:search-container>
			<liferay-ui:search-container-results>
				<%					
				
				// Add one to start count to eliminate the header names
				
				results = ListUtil.subList(failedImports, searchContainer.getStart()+1, searchContainer.getEnd());
				total = results.size();
		
				pageContext.setAttribute("results", results);
				pageContext.setAttribute("total", total);
				%>
			</liferay-ui:search-container-results>
			<liferay-ui:search-container-row
					className="java.lang.String[]"
					modelVar="line"
			>
			
				<liferay-ui:search-container-column-text
			    		name="<%=failedImports.get(0)[0] %>"
			    		value="<%=line[0] %>"
			    />
			    
			    <liferay-ui:search-container-column-text
			    		name="<%=failedImports.get(0)[1] %>"
			    		value="<%=line[1] %>"
			    />
			
			    <liferay-ui:search-container-column-text
			    		name="<%=failedImports.get(0)[2] %>"
			    		value="<%=line[2] %>"
			    />
			
			    <liferay-ui:search-container-column-text
			    		name="<%=failedImports.get(0)[3] %>"
			    		value="<%=line[3] %>"
			    />
			    
			    <liferay-ui:search-container-column-text
			    		name="<%=failedImports.get(0)[4] %>"
			    		value="<%=line[4] %>"
			    />
			
			</liferay-ui:search-container-row>
			
			<liferay-ui:search-iterator paginate="<%=false %>" />

		</liferay-ui:search-container>
	</c:if>
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