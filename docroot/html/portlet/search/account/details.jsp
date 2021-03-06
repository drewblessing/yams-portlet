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

<div class="details">
	<img alt="<liferay-ui:message key="account-photo" />" src='<%=renderRequest.getContextPath() %>/images/user_male_portrait.png' />

	<dl class="property-list">
		<c:if test="<%=Validator.isNotNull(selAccount.getGivenName()) %>">
			<dt>
				<liferay-ui:message key="first-name" />
			</dt>
			<dd>
				<%=selAccount.getGivenName() %>
			</dd>
		</c:if>
		<c:if test="<%=Validator.isNotNull(selAccount.getSn()) %>">
			<dt>
				<liferay-ui:message key="last-name" />
			</dt>
			<dd>
				<%=selAccount.getSn() %>
			</dd>
		</c:if>
		<c:if test="<%=Validator.isNotNull(selAccount.getMailStringWithDelimiter(
  				Account.DELIMITER_COMMA, true)) %>">
			<dt>
				<liferay-ui:message key="email-address" />
			</dt>
			<dd>
				<%=selAccount.getMailStringWithDelimiter(
	      				Account.DELIMITER_COMMA, true) %>
			</dd>
		</c:if>
	</dl>
</div>
