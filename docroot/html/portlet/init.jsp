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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.portal.model.ListTypeConstants" %>

<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.dao.search.RowChecker" %><%@
page import="com.liferay.portal.kernel.dao.search.SearchContainer" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.servlet.SessionErrors" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ListUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.util.PortalUtil" %>

<%@ page import="com.liferay.portlet.PortletURLUtil" %>

<%@ page import="java.text.DateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.TreeMap" %>

<%@ page import="javax.portlet.PortletMode" %><%@
page import="javax.portlet.PortletURL" %><%@
page import="javax.portlet.WindowState" %>

<%@ page import="org.gnenc.yams.model.Account" %><%@
page import="org.gnenc.yams.model.AccountType" %><%@
page import="org.gnenc.yams.model.Domain" %><%@
page import="org.gnenc.yams.model.EntityGroup" %><%@
page import="org.gnenc.yams.model.JobQueue" %><%@
page import="org.gnenc.yams.model.PermissionsDefined" %><%@
page import="org.gnenc.yams.portlet.AccountManagement" %><%@
page import="org.gnenc.yams.portlet.ActionUtil" %><%@
page import="org.gnenc.yams.portlet.Search" %><%@
page import="org.gnenc.yams.portlet.search.OrganizationDisplayTerms" %><%@
page import="org.gnenc.yams.portlet.search.OrganizationSearch" %><%@
page import="org.gnenc.yams.portlet.search.OrganizationSearchTerms" %><%@
page import="org.gnenc.yams.portlet.search.UserDisplayTerms" %><%@
page import="org.gnenc.yams.portlet.search.UserSearch" %><%@
page import="org.gnenc.yams.portlet.search.UserSearchTerms" %><%@
page import="org.gnenc.yams.portlet.util.PermissionsChecker" %><%@
page import="org.gnenc.yams.portlet.util.PortletKeys" %><%@
page import="org.gnenc.yams.portlet.util.PortletUtil" %><%@
page import="org.gnenc.yams.portlet.util.PropsValues" %>

<liferay-theme:defineObjects />
<portlet:defineObjects />

<%
Account callingAccount = null;
PortletMode portletMode = null;
PortletURL currentURLObj = null;
WindowState windowState = null;

if (Validator.isNull(request.getSession().getAttribute("callingAccount"))) {
	callingAccount = PortletUtil.getAccountFromPortalUser(renderRequest, user);
} else {
	callingAccount = (Account)request.getSession().getAttribute("callingAccount");
}

if (renderRequest != null) {
	windowState = renderRequest.getWindowState();
	portletMode = renderRequest.getPortletMode();

	currentURLObj = PortletURLUtil.getCurrent(renderRequest, renderResponse);
}
else if (resourceRequest != null) {
	windowState = resourceRequest.getWindowState();
	portletMode = resourceRequest.getPortletMode();

	currentURLObj = PortletURLUtil.getCurrent(resourceRequest, resourceResponse);
}

String currentURL = currentURLObj.toString();
%>