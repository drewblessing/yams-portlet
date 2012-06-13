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
 * This class is a wrapper for {@link PermissionsDefinedLocalService}.
 * </p>
 *
 * @author    Drew A. Blessing
 * @see       PermissionsDefinedLocalService
 * @generated
 */
public class PermissionsDefinedLocalServiceWrapper
	implements PermissionsDefinedLocalService,
		ServiceWrapper<PermissionsDefinedLocalService> {
	public PermissionsDefinedLocalServiceWrapper(
		PermissionsDefinedLocalService permissionsDefinedLocalService) {
		_permissionsDefinedLocalService = permissionsDefinedLocalService;
	}

	/**
	* Adds the permissions defined to the database. Also notifies the appropriate model listeners.
	*
	* @param permissionsDefined the permissions defined
	* @return the permissions defined that was added
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.PermissionsDefined addPermissionsDefined(
		org.gnenc.yams.model.PermissionsDefined permissionsDefined)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _permissionsDefinedLocalService.addPermissionsDefined(permissionsDefined);
	}

	/**
	* Creates a new permissions defined with the primary key. Does not add the permissions defined to the database.
	*
	* @param permissionKey the primary key for the new permissions defined
	* @return the new permissions defined
	*/
	public org.gnenc.yams.model.PermissionsDefined createPermissionsDefined(
		java.lang.String permissionKey) {
		return _permissionsDefinedLocalService.createPermissionsDefined(permissionKey);
	}

	/**
	* Deletes the permissions defined with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param permissionKey the primary key of the permissions defined
	* @throws PortalException if a permissions defined with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deletePermissionsDefined(java.lang.String permissionKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_permissionsDefinedLocalService.deletePermissionsDefined(permissionKey);
	}

	/**
	* Deletes the permissions defined from the database. Also notifies the appropriate model listeners.
	*
	* @param permissionsDefined the permissions defined
	* @throws SystemException if a system exception occurred
	*/
	public void deletePermissionsDefined(
		org.gnenc.yams.model.PermissionsDefined permissionsDefined)
		throws com.liferay.portal.kernel.exception.SystemException {
		_permissionsDefinedLocalService.deletePermissionsDefined(permissionsDefined);
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
		return _permissionsDefinedLocalService.dynamicQuery(dynamicQuery);
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
		return _permissionsDefinedLocalService.dynamicQuery(dynamicQuery,
			start, end);
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
		return _permissionsDefinedLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _permissionsDefinedLocalService.dynamicQueryCount(dynamicQuery);
	}

	public org.gnenc.yams.model.PermissionsDefined fetchPermissionsDefined(
		java.lang.String permissionKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _permissionsDefinedLocalService.fetchPermissionsDefined(permissionKey);
	}

	/**
	* Returns the permissions defined with the primary key.
	*
	* @param permissionKey the primary key of the permissions defined
	* @return the permissions defined
	* @throws PortalException if a permissions defined with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.PermissionsDefined getPermissionsDefined(
		java.lang.String permissionKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _permissionsDefinedLocalService.getPermissionsDefined(permissionKey);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _permissionsDefinedLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the permissions defineds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of permissions defineds
	* @param end the upper bound of the range of permissions defineds (not inclusive)
	* @return the range of permissions defineds
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.PermissionsDefined> getPermissionsDefineds(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _permissionsDefinedLocalService.getPermissionsDefineds(start, end);
	}

	/**
	* Returns the number of permissions defineds.
	*
	* @return the number of permissions defineds
	* @throws SystemException if a system exception occurred
	*/
	public int getPermissionsDefinedsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _permissionsDefinedLocalService.getPermissionsDefinedsCount();
	}

	/**
	* Updates the permissions defined in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param permissionsDefined the permissions defined
	* @return the permissions defined that was updated
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.PermissionsDefined updatePermissionsDefined(
		org.gnenc.yams.model.PermissionsDefined permissionsDefined)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _permissionsDefinedLocalService.updatePermissionsDefined(permissionsDefined);
	}

	/**
	* Updates the permissions defined in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param permissionsDefined the permissions defined
	* @param merge whether to merge the permissions defined with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the permissions defined that was updated
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.PermissionsDefined updatePermissionsDefined(
		org.gnenc.yams.model.PermissionsDefined permissionsDefined,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _permissionsDefinedLocalService.updatePermissionsDefined(permissionsDefined,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _permissionsDefinedLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_permissionsDefinedLocalService.setBeanIdentifier(beanIdentifier);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public PermissionsDefinedLocalService getWrappedPermissionsDefinedLocalService() {
		return _permissionsDefinedLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedPermissionsDefinedLocalService(
		PermissionsDefinedLocalService permissionsDefinedLocalService) {
		_permissionsDefinedLocalService = permissionsDefinedLocalService;
	}

	public PermissionsDefinedLocalService getWrappedService() {
		return _permissionsDefinedLocalService;
	}

	public void setWrappedService(
		PermissionsDefinedLocalService permissionsDefinedLocalService) {
		_permissionsDefinedLocalService = permissionsDefinedLocalService;
	}

	private PermissionsDefinedLocalService _permissionsDefinedLocalService;
}