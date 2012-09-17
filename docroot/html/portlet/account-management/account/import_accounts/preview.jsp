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
String cancelURL = (String)request.getAttribute("cancelURL");

String filePath = (String)request.getAttribute("filePath");
List<String[]> preview = (List<String[]>)request.getAttribute("preview");
String group = (String)request.getAttribute("group");
%>

<c:if test="<%= portletName.equals(PortletKeys.ACCOUNT_MANAGEMENT) %>">
	<liferay-util:include
			page="<%=PortletUtil.ACCT_MGMT_TOOLBAR_JSP %>"
			servletContext="<%=this.getServletContext() %>">
		<liferay-util:param name="toolbarItem" value="import" />
	</liferay-util:include>
</c:if>

<liferay-ui:header
	backURL="<%= cancelURL %>"
	title='import-preview'
/>

<div class="import-accounts">
	<div class="instructions">
		<liferay-ui:message key="preview-import-instructions" />
	</div>
	
<portlet:actionURL name="importAccountsProcessCSV" var="importAccountsProcessCSVURL" >
	<portlet:param name="doneURL" value="<%=cancelURL %>" />
</portlet:actionURL>
	
	<aui:form method="POST" action="<%=importAccountsProcessCSVURL.toString() %>" name="fm" id="fm">
		<aui:input type="hidden" name="callingAccount" value="<%=callingAccount.getUid() %>" />
		<aui:input type="hidden" name="filePath" value="<%=filePath %>" />
		<aui:input type="hidden" name="group" value="<%=group %>" />
						
		<c:choose>
		<c:when test="<%= PermissionsChecker.hasGroupPermission(callingAccount, PermissionsChecker.PERMISSION_ACCOUNT_ADD, StringPool.NULL) %>">
			<liferay-ui:search-container>
				<liferay-ui:search-container-results>
					<%					
					
					// Add one to start count to eliminate the header names
					
					results = ListUtil.subList(preview, searchContainer.getStart()+1, searchContainer.getEnd());
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
				    		name="<%=preview.get(0)[0] %>"
				    		value="<%=line[0] %>"
				    />
				    
				    <liferay-ui:search-container-column-text
				    		name="<%=preview.get(0)[1] %>"
				    		value="<%=line[1] %>"
				    />
				
				    <liferay-ui:search-container-column-text
				    		name="<%=preview.get(0)[2] %>"
				    		value="<%=line[2] %>"
				    />
				
				    <liferay-ui:search-container-column-text
				    		name="<%=preview.get(0)[3] %>"
				    		value="<%=line[3] %>"
				    />
				    
				    <liferay-ui:search-container-column-text
				    		name="<%=preview.get(0)[4] %>"
				    		value="<%=line[4] %>"
				    />
				
				</liferay-ui:search-container-row>
				
				<liferay-ui:search-iterator paginate="<%=false %>" />
	
			</liferay-ui:search-container>
		</c:when>
		<c:otherwise>
			<div class="portlet-msg-error"><liferay-ui:message key="you-do-not-have-sufficient-privileges-to-import-accounts-for-any-groups" /></div>
		</c:otherwise>
		</c:choose>
		<aui:button-row>
			<div class="buttons-left">			
				<aui:button id="cancel" onClick='<%=cancelURL %>' value="cancel"/>
				<c:if test="<%= PermissionsChecker.hasGroupPermission(callingAccount, PermissionsChecker.PERMISSION_ACCOUNT_ADD, StringPool.NULL) %>">
					<aui:button type="submit" value="submit" />
				</c:if>
			</div>
	
			<div class="step" id="<portlet:namespace />step">
				<span>
					<liferay-ui:message arguments="<%= new Integer[] {2, 3}%>" key="step-x-of-x" />
				</span>
			</div>
		</aui:button-row>
	</aui:form>
</div>