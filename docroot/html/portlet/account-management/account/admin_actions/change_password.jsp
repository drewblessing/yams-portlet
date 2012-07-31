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
<liferay-ui:error key="password-fields-must-match" message="password-fields-must-match" />

<aui:form method="POST" name="changePasswordFm" id="changePasswordFm">
	<aui:input type="hidden" name="<%=UserDisplayTerms.CMD %>" value="<%=AccountManagement.EDIT_PASSWORD_CMD %>" />
	<aui:input type="hidden" name="date" value="<%=new Date() %>" />
	<aui:input type="hidden" name="uid" value='<%=ParamUtil.getString(renderRequest, "uid") %>' />
	<aui:input type="password" name="password" size="25">
		<aui:validator name="required" />
		<aui:validator name="rangeLength">
			[8,50]
		</aui:validator>
	</aui:input>
	<aui:input type="password" name="verify" size="25">
		<aui:validator name="required" />
		<aui:validator name="rangeLength">
			[8,50]
		</aui:validator>
	</aui:input>
</aui:form>