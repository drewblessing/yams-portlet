<liferay-ui:icon-menu align="left"
			cssClass='<%= "lfr-toolbar-button add-button " + (toolbarItem.equals("add") ? "current" : StringPool.BLANK) %>'
			direction="down"
			extended="<%= false %>"
			icon='<%= themeDisplay.getPathThemeImages() + "/common/add.png" %>'
			message="add"
			showWhenSingleIcon="<%= true %>"
	>

		<c:if test="<%= PermissionsChecker.hasGroupPermission(
				callingAccount, PermissionsChecker.PERMISSION_ACCOUNT_ADD, StringPool.NULL) %>">
			<portlet:renderURL var="addUserURL">
				<portlet:param name="jspPage" value="<%=PortletUtil.ACCT_MGMT_ACCOUNT_EDIT_JSP %>" />
				<portlet:param name="redirect" value="<%= searchViewURL %>" />	
				<portlet:param name="toolbarItem" value="add" />
			</portlet:renderURL>

			<liferay-ui:icon
				image="user_icon"
				message="account"
				url="<%= addUserURL %>"
			/>
		</c:if>
		<c:if test="<%= PermissionsChecker.hasGroupPermission(
				callingAccount, PermissionsChecker.PERMISSION_GROUP_ADD, StringPool.NULL) %>">

			<portlet:renderURL var="addOrganizationURL">
				<portlet:param name="jspPage" value="<%=PortletUtil.ACCT_MGMT_ORGANIZATION_EDIT_JSP %>" />
				<portlet:param name="redirect" value="<%= searchViewURL %>" />
				<portlet:param name="toolbarItem" value="add" />
			</portlet:renderURL>
			<liferay-ui:icon
				image="group"
				message='organization'
				url="<%= addOrganizationURL %>"
			/>

		</c:if>
	</liferay-ui:icon-menu>