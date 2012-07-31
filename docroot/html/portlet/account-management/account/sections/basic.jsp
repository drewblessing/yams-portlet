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
Account selAccount = (Account)request.getAttribute("account.selAccount");
%>

<h3><liferay-ui:message key="basic" /></h3>

<aui:fieldset column="<%=true %>" cssClass="aui-w50">

	<aui:select name="title" showEmptyOption="<%= true %>" first="<%=true %>" >
		<aui:option selected='<%= selAccount.getAttribute("title").equals("Dr.") %>' value="Dr.">
			<liferay-ui:message key="dr." />
		</aui:option>
		<aui:option selected='<%= selAccount.getAttribute("title").equals("Mr.") %>' value="Mr.">
			<liferay-ui:message key="mr." />
		</aui:option>
		<aui:option selected='<%= selAccount.getAttribute("title").equals("Mrs.") %>' value="Mrs.">
			<liferay-ui:message key="mrs." />
		</aui:option>
		<aui:option selected='<%= selAccount.getAttribute("title").equals("Ms.") %>' value="Ms.">
			<liferay-ui:message key="ms." />
		</aui:option>
	</aui:select>
	
	<aui:input name="<%=UserDisplayTerms.FIRST_NAME %>"	value="<%= selAccount.getGivenName() %>">
		<aui:validator name="required" />
	</aui:input>
	<aui:input name="<%=UserDisplayTerms.LAST_NAME %>" value="<%= selAccount.getSn() %>">
		<aui:validator name="required" />
	</aui:input>

</aui:fieldset>

<aui:fieldset column="<%=true %>" cssClass="aui-w50">

	<aui:field-wrapper name="<%=UserDisplayTerms.SCREEN_NAME %>">
		<%= selAccount.getUid() %>

		<aui:input name="<%=UserDisplayTerms.SCREEN_NAME %>" type="hidden" value="<%= selAccount.getUid() %>" />
	</aui:field-wrapper>

	<aui:field-wrapper name="<%=UserDisplayTerms.EMAIL_ADDRESS %>">
		<%= selAccount.getMailStringWithDelimiter(',', true) %>

		<aui:input name="<%=UserDisplayTerms.EMAIL_ADDRESS %>" type="hidden" value="<%= selAccount.getMail() %>" />
	</aui:field-wrapper>

</aui:fieldset>