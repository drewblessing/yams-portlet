/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.gnenc.yams.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PermissionsLocalService}.
 * </p>
 *
 * @author    Drew A. Blessing
 * @see       PermissionsLocalService
 * @generated
 */
public class PermissionsLocalServiceWrapper implements PermissionsLocalService,
	ServiceWrapper<PermissionsLocalService> {
	public PermissionsLocalServiceWrapper(
		PermissionsLocalService permissionsLocalService) {
		_permissionsLocalService = permissionsLocalService;
	}

	/**
	* Adds the permissions to the database. Also notifies the appropriate model listeners.
	*
	* @param permissions the permissions
	* @return the permissions that was added
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.Permissions addPermissions(
		org.gnenc.yams.model.Permissions permissions)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _permissionsLocalService.addPermissions(permissions);
	}

	/**
	* Creates a new permissions with the primary key. Does not add the permissions to the database.
	*
	* @param id the primary key for the new permissions
	* @return the new permissions
	*/
	public org.gnenc.yams.model.Permissions createPermissions(long id) {
		return _permissionsLocalService.createPermissions(id);
	}

	/**
	* Deletes the permissions with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the permissions
	* @return the permissions that was removed
	* @throws PortalException if a permissions with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.Permissions deletePermissions(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _permissionsLocalService.deletePermissions(id);
	}

	/**
	* Deletes the permissions from the database. Also notifies the appropriate model listeners.
	*
	* @param permissions the permissions
	* @return the permissions that was removed
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.Permissions deletePermissions(
		org.gnenc.yams.model.Permissions permissions)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _permissionsLocalService.deletePermissions(permissions);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _permissionsLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _permissionsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _permissionsLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _permissionsLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _permissionsLocalService.dynamicQueryCount(dynamicQuery);
	}

	public org.gnenc.yams.model.Permissions fetchPermissions(long id)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _permissionsLocalService.fetchPermissions(id);
	}

	/**
	* Returns the permissions with the primary key.
	*
	* @param id the primary key of the permissions
	* @return the permissions
	* @throws PortalException if a permissions with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.Permissions getPermissions(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _permissionsLocalService.getPermissions(id);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _permissionsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the permissionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of permissionses
	* @param end the upper bound of the range of permissionses (not inclusive)
	* @return the range of permissionses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.Permissions> getPermissionses(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _permissionsLocalService.getPermissionses(start, end);
	}

	/**
	* Returns the number of permissionses.
	*
	* @return the number of permissionses
	* @throws SystemException if a system exception occurred
	*/
	public int getPermissionsesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _permissionsLocalService.getPermissionsesCount();
	}

	/**
	* Updates the permissions in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param permissions the permissions
	* @return the permissions that was updated
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.Permissions updatePermissions(
		org.gnenc.yams.model.Permissions permissions)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _permissionsLocalService.updatePermissions(permissions);
	}

	/**
	* Updates the permissions in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param permissions the permissions
	* @param merge whether to merge the permissions with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the permissions that was updated
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.Permissions updatePermissions(
		org.gnenc.yams.model.Permissions permissions, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _permissionsLocalService.updatePermissions(permissions, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _permissionsLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_permissionsLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _permissionsLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this interface directly. Always use {@link org.gnenc.yams.service.PermissionsLocalServiceUtil} to access the permissions local service.
	*
	* @throws SystemException
	*/
	public long getPermissionsIdByEmailAddressAndFqgn(
		java.lang.String emailAddress, java.lang.String fqgn)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _permissionsLocalService.getPermissionsIdByEmailAddressAndFqgn(emailAddress,
			fqgn);
	}

	public java.util.List<org.gnenc.yams.model.Permissions> getByEmailAddressAndFqgnAndGroupPermission(
		java.lang.String email, java.lang.String fqgn, boolean group)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _permissionsLocalService.getByEmailAddressAndFqgnAndGroupPermission(email,
			fqgn, group);
	}

	public java.util.List<org.gnenc.yams.model.Permissions> getByFqgnAndGroupPermission(
		java.lang.String fqgn, boolean group)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _permissionsLocalService.getByFqgnAndGroupPermission(fqgn, group);
	}

	public java.util.List<org.gnenc.yams.model.Permissions> getByEmailAddress(
		java.lang.String email)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _permissionsLocalService.getByEmailAddress(email);
	}

	public org.gnenc.yams.model.Permissions addPermissions(long userId,
		java.lang.String emailAddress, java.lang.String fqgn,
		boolean groupPermission, long permissions, long permissionsGrantable)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _permissionsLocalService.addPermissions(userId, emailAddress,
			fqgn, groupPermission, permissions, permissionsGrantable);
	}

	public org.gnenc.yams.model.Permissions updatePermissions(
		long permissionsId, long userId, long decimalPermissions,
		long permissionsGrantable)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _permissionsLocalService.updatePermissions(permissionsId,
			userId, decimalPermissions, permissionsGrantable);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public PermissionsLocalService getWrappedPermissionsLocalService() {
		return _permissionsLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedPermissionsLocalService(
		PermissionsLocalService permissionsLocalService) {
		_permissionsLocalService = permissionsLocalService;
	}

	public PermissionsLocalService getWrappedService() {
		return _permissionsLocalService;
	}

	public void setWrappedService(
		PermissionsLocalService permissionsLocalService) {
		_permissionsLocalService = permissionsLocalService;
	}

	private PermissionsLocalService _permissionsLocalService;
}