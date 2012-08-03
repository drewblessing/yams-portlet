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
	PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");
String tabs1 = ParamUtil.getString(request, "tabs1", PortletUtil.ACCOUNTS);
List<EntityGroup> groups = AccountManagement.getAllGroups();
List<Domain> domains = AccountManagement.getAllDomains();
String jspPage = "";
%>

<liferay-ui:search-container searchContainer="<%=new UserSearch(renderRequest, portletURL) %>">
	<%
	UserDisplayTerms displayTerms = (UserDisplayTerms)searchContainer.getDisplayTerms();
	UserSearchTerms searchTerms = (UserSearchTerms)searchContainer.getSearchTerms();
	%>

	<liferay-ui:search-toggle
		buttonLabel="search"
		displayTerms="<%=displayTerms%>"
		id="toggle_id_search_users"
	>
		<aui:fieldset>
			<aui:input name="<%=UserDisplayTerms.FIRST_NAME%>" size="20"
					value="<%=searchTerms.getFirstName() %>" />

			<aui:input name="<%=UserDisplayTerms.LAST_NAME%>" size="20"
					value="<%=searchTerms.getLastName() %>" />

			<aui:input name="<%=UserDisplayTerms.EMAIL_ADDRESS%>" size="20"
					value="<%=searchTerms.getLastName() %>" />
		</aui:fieldset>
		<aui:fieldset>
			<aui:select name="<%=UserDisplayTerms.ESUCC_ENTITY %>" label="entity" showEmptyOption="<%= true %>">
				<% for (EntityGroup group : groups) { %>
<%-- 				<c:forEach var="groupItem" items="<%=groups %>"> --%>
					<aui:option value="<%=group.getEsuccEntity() %>" selected="<%=searchTerms.getEsuccEntity() != null ? searchTerms.getEsuccEntity().equals(group.getEsuccEntity()) : false %>">
						<%=group.getEsuccEntity() %>
					</aui:option>
<%-- 				</c:forEach> --%>
				<% } %>
			</aui:select>
			<aui:select name="<%=UserDisplayTerms.DOMAIN %>" label="domain" showEmptyOption="<%=true %>" >
			<% for (Domain domain : domains) { %>
<%-- 				<c:forEach var="domainItem" items="<%=domains %>"> --%>
					<aui:option value="<%=domain.getOrganization() %>" selected="<%=searchTerms.getDomain() != null ? searchTerms.getDomain().equals(domain.getOrganization()) : false %>">
						<%=domain.getOrganization() %>
					</aui:option>
<%-- 				</c:forEach> --%>
			<% } %>
			</aui:select>
			<aui:select name="<%=UserDisplayTerms.ESUCC_ACCOUNT_TYPE %>" label="account-type" >
				<aui:option value="<%=AccountType.ALL %>" selected="<%=searchTerms.getEsuccAccountType() != null ? searchTerms.getEsuccAccountType().equals(AccountType.ALL ) : false %>" ><liferay-ui:message key="all" /></aui:option>
				<aui:option value="<%=AccountType.ESUCC_STAFF %>" selected="<%=searchTerms.getEsuccAccountType() != null ? searchTerms.getEsuccAccountType().equals(AccountType.ESUCC_STAFF ) : false %>" ><liferay-ui:message key="staff" /></aui:option>
				<aui:option value="<%=AccountType.ESUCC_STUDENT %>" selected="<%=searchTerms.getEsuccAccountType() != null ? searchTerms.getEsuccAccountType().equals(AccountType.ESUCC_STUDENT ) : false %>" ><liferay-ui:message key="student" /></aui:option>
			</aui:select>
		</aui:fieldset>
	</liferay-ui:search-toggle>

	<div class="separator"><!-- Separator --></div>

	<liferay-ui:search-container-results>
		<%
	 	List<Account> tempResults = Search.getAccounts(
	 			searchTerms, searchContainer.getOrderByType(), 
	 			searchContainer.getOrderByCol(), true);

		results = ListUtil.subList(tempResults, searchContainer.getStart(), searchContainer.getEnd());
		total = tempResults.size();

		pageContext.setAttribute("results", results);
		pageContext.setAttribute("total", total);
	    %>
	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
			className="org.gnenc.yams.model.Account"
			keyProperty="dn"
			modelVar="yamsAccount"
	>
	
		<liferay-portlet:renderURL varImpl="rowURL">
				<portlet:param name="backURL" value="<%= searchContainer.getIteratorURL().toString() %>" />
				<portlet:param name="uidNumber" value='<%= yamsAccount.getAttribute("uidNumber") %>' />
				<portlet:param name="jspPage" value="<%=PortletUtil.SEARCH_VIEW_ACCOUNT_JSP %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container-column-text
				href="<%=rowURL %>"
	    		name="lastName"
	    		property="sn"
	      		orderable="true"
	      		orderableProperty="sn"
	    />

	  	<liferay-ui:search-container-column-text
				href="<%=rowURL %>"
	      		name="firstName"
	      		property="givenName"
	      		orderable="true"
				orderableProperty="givenName"
	    />

		<liferay-ui:search-container-column-text
	    		name="emailAddress"
	      		value="<%=yamsAccount.getMailStringWithDelimiter(
	      				Account.DELIMITER_COMMA, true) %>"
	    />

	    <c:if test="<%= portletName.equals(PortletKeys.ACCOUNT_MANAGEMENT) %>">
	    	<liferay-ui:search-container-column-jsp
        		path="<%=PortletUtil.ACCT_MGMT_ACCOUNT_ADMIN_ACTIONS_JSP %>"
        		align="right"
	        />
	    </c:if>

	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />

</liferay-ui:search-container>
<aui:script use="aui-core" >
<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace /><%=UserDisplayTerms.FIRST_NAME %>);
</c:if>
</aui:script>