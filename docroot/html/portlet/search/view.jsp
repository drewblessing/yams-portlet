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

<%-- <jsp:include page="<%=PropsValues.YAMS_PORTLET_SEARCH_DEFAULT_VIEW %>" /> --%>

<portlet:renderURL var="portletURL" />

<%
String tabValue = ParamUtil.getString(request, "tab", "users");
String tabsURL = "/html/portlet/search/tabs/" + tabValue.trim() + ".jsp";
String tabNames = "users,organizations";
%>

<liferay-ui:tabs 
	names="users,organizations"
	param="tab" 
	value="<%=tabValue %>"
	url="<%=portletURL %>" />
	
<jsp:include page="<%=tabsURL %>" />