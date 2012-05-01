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
SearchContainer searchContainer = (SearchContainer)request.getAttribute("liferay-ui:search:searchContainer");

AccountDisplayTerms displayTerms = (AccountDisplayTerms)searchContainer.getDisplayTerms();
%>

<liferay-ui:search-toggle
	buttonLabel="search"
	displayTerms="<%= displayTerms %>"
	id="toggle_id_accounts_admin_account_search"
>
	<aui:fieldset>
		<aui:input name="<%= AccountDisplayTerms.FIRST_NAME %>" size="20" value="" />

		<aui:input name="<%= AccountDisplayTerms.LAST_NAME %>" size="20" value="" />
		
		<aui:input name="<%= AccountDisplayTerms.EMAIL_ADDRESS %>" size="20" value="" />

		<aui:input name="<%= AccountDisplayTerms.JOB_TITLE %>" size="20" value="" />

		<aui:input name="<%= AccountDisplayTerms.GROUP %>" size="20" value="" />
	</aui:fieldset>
</liferay-ui:search-toggle>