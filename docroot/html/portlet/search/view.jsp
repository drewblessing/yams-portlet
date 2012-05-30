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
request.setAttribute("view.jsp-portletURL", portletURL);

String tabs1 = ParamUtil.getString(request, "tabs1", "users");
String tabsURL = "/html/portlet/search/tabs/" + tabs1.trim() + ".jsp";
String tabNames = "users,organizations";

portletURL.setParameter("tabs1", tabs1);
pageContext.setAttribute("portletURL",portletURL);
String portletURLString = portletURL.toString();
%>

<aui:form method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="portletURL" />
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="tabs1" type="hidden" value="<%= tabs1 %>" />
	<aui:input name="redirect" type="hidden" value="<%= portletURLString %>" />
	
	<liferay-ui:tabs 
		names="<%=tabNames %>"
		value="<%=tabs1 %>"
		url="<%=portletURLString %>" />
		
	<jsp:include page="<%=tabsURL %>" />
</aui:form>