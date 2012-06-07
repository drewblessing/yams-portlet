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
String tabs1 = ParamUtil.getString(request, "tabs1", "users");
String jspPage = "";
%>
<c:choose>
	<c:when test="<%= portletName.equals(PortletKeys.ACCOUNT_MANAGEMENT) %>" >
		<% jspPage = "/html/portlet/account-management/account/edit_account.jsp"; %>
	</c:when>
	<c:otherwise>
		<% jspPage = "/html/portlet/search/view_account.jsp"; %>
	</c:otherwise>
</c:choose>

<liferay-ui:search-container searchContainer="<%=new UserSearch(renderRequest, portletURL) %>" >
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
					value="<%=searchTerms.getFirstName()%>" />
	
			<aui:input name="<%=UserDisplayTerms.LAST_NAME%>" size="20" 
					value="<%=searchTerms.getLastName()%>" />
			
			<aui:input name="<%=UserDisplayTerms.EMAIL_ADDRESS%>" size="20" 
					value="<%=searchTerms.getLastName()%>" />
		</aui:fieldset>
	</liferay-ui:search-toggle>
	
	<div class="separator" ><!--  Separator --></div>
	
	<liferay-ui:search-container-results>
		<% 
	 	List<Account> tempResults = Search.getAccounts(
	 			searchTerms,
	 			searchContainer.getOrderByType(), searchContainer.getOrderByCol());
	   
		results = ListUtil.subList(tempResults, searchContainer.getStart(), searchContainer.getEnd());
		total = tempResults.size();
	
		pageContext.setAttribute("results", results);
		pageContext.setAttribute("total", total);
	    %>
	</liferay-ui:search-container-results>
	
	<liferay-ui:search-container-row
			className="org.gnenc.yams.model.Account"
			keyProperty="uid"
			modelVar="yamsAccount"
	>
	<liferay-portlet:renderURL varImpl="rowURL">
			<portlet:param name="backURL" value="<%= searchContainer.getIteratorURL().toString() %>" />
			<portlet:param name="uid" value="<%= yamsAccount.getUid() %>" />
			<portlet:param name="jspPage" value="<%=jspPage %>" />
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
	    
	    <c:if test="<%= portletName.equals(PortletKeys.ACCOUNT_MANAGEMENT) %>" >
	    	<liferay-ui:search-container-column-jsp
        		path="/html/portlet/account-management/account/admin_actions.jsp"
        		align="right"
	        />
	    </c:if>
	
	</liferay-ui:search-container-row>
	
	<liferay-ui:search-iterator />

</liferay-ui:search-container>