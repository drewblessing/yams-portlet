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
Set<String> errors = SessionErrors.keySet(renderRequest);
%>

<h3><liferay-ui:message key="password" /></h3>

<aui:fieldset column="<%=true %>" cssClass="aui-w100">

	<div class="section edit-password aui-column aui-w50">
			<aui:input type="password" name="password" size="25">
				<aui:validator name="rangeLength">
					[8,64]
				</aui:validator>
			</aui:input>
			<aui:input type="password" name="verify" size="25">
				<aui:validator name="rangeLength">
					[8,64]
				</aui:validator>
			</aui:input>
		</div>
		<div class="section edit-password aui-column aui-w50">
			Passwords must adhere to the following rules:
			<jsp:include page="<%=PortletUtil.ACCT_MGMT_ACCOUNT_PASSWORD_POLICY_TEXT_JSP %>" />
			<liferay-ui:error key="password-fields-must-match" message="password-fields-must-match" />
			<c:if test="<%=errors.size() > 0 %>" >
				<c:forEach var="error" items="<%=errors %>">
					<div class="portlet-msg-error">${error}</div>
				</c:forEach>
			</c:if>
		</div>

</aui:fieldset>