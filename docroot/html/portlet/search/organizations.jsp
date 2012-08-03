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
%>

<liferay-ui:search-container searchContainer="<%=new OrganizationSearch(renderRequest, portletURL)%>">
	<%
		OrganizationDisplayTerms displayTerms = (OrganizationDisplayTerms)searchContainer.getDisplayTerms();
		OrganizationSearchTerms searchTerms = (OrganizationSearchTerms)searchContainer.getSearchTerms();
	%>

<liferay-ui:search-toggle
	buttonLabel="search"
	displayTerms="<%=displayTerms%>"
	id="toggle_id_search_organizations"
>
	<aui:fieldset>
		<aui:input name="<%=OrganizationDisplayTerms.NAME%>" size="20" value="" />
	</aui:fieldset>
</liferay-ui:search-toggle>

<div class="separator"><!-- Separator --></div>

	<liferay-ui:search-container-results>
		<%
			List<EntityGroup> tempResults = Search.getGroups(
			 			searchTerms,
			 			searchContainer.getOrderByType(), searchContainer.getOrderByCol(), true);

				results = ListUtil.subList(tempResults, searchContainer.getStart(), searchContainer.getEnd());
				total = tempResults.size();

				pageContext.setAttribute("results", results);
				pageContext.setAttribute("total", total);
		%>
	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
			className="org.gnenc.yams.model.Group"
			keyProperty="cn"
			modelVar="yamsGroup">

		<liferay-ui:search-container-column-text
	    		name="name"
	    		property="displayName"
	      		orderable="true"
	      		orderableProperty="cn"
	    />

	    <liferay-ui:search-container-column-text
	    		name="description"
	    		property="description"
	      		orderable="false"
	    />


	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />

</liferay-ui:search-container>
<aui:script use="aui-core" >
<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace /><%=OrganizationDisplayTerms.NAME %>);
</c:if>
</aui:script>