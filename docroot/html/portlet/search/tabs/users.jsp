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
PortletURL portletURL = renderResponse.createRenderURL();
portletURL.setParameter("jspPage", "/html/portlet/search/tabs/users.jsp");

SearchContainer userSearch = new UserSearch(renderRequest, "users", portletURL);
userSearch.setRowChecker(new RowChecker(renderResponse));
%>

<liferay-ui:search-container searchContainer="<%= userSearch %>" >
	<liferay-ui:search-form 
		page="/html/portlet/search/search_accounts.jsp" 
		servletContext="<%=this.getServletContext() %>" 
	/>
	
	<div class="separator" /><!-- Separator --></div>
	
</liferay-ui:search-container>