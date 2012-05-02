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

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="util" %>

<%@ page import="org.gnenc.yams.model.Account" %>
<%@ page import="org.gnenc.yams.portlet.Search" %>
<%@ page import="org.gnenc.yams.portlet.search.UserDisplayTerms" %>
<%@ page import="org.gnenc.yams.portlet.search.UserSearch" %>
<%@ page import="org.gnenc.yams.portlet.util.PropsValues" %>

<%@ page import="javax.portlet.PortletMode" %>
<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="javax.portlet.WindowState" %>

<%@ page import="com.liferay.portal.util.PortalUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.search.RowChecker" %>
<%@ page import="com.liferay.portal.kernel.dao.search.SearchContainer" %> 
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>

<%@ page import="com.liferay.portlet.PortletURLUtil" %>

<liferay-theme:defineObjects />
<portlet:defineObjects />