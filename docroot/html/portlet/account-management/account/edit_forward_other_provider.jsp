<%
/**
 *  Copyright (c) 2012-2013 Educational Service Unit 10.
 *
 *  This file is part of the YAMS portlet.
 *"docroot/html/portlet/account-management/account/admin_actions/edit_password.jsp"
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
String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL", redirect);
%>

<div class='portlet-msg-info'><liferay-ui:message key="account-not-hosted-by-gnenc" /></div>

<liferay-ui:message key="email-forward-other-provider" /><a href="http://support.google.com/mail/bin/answer.py?hl=en&answer=10957" target="_blank">http://support.google.com/mail/bin/answer.py?hl=en&answer=10957</a>

<aui:button-row cssClass="dialog-footer">
	<aui:button value="Back" onClick="<%=backURL %>"></aui:button>
</aui:button-row>