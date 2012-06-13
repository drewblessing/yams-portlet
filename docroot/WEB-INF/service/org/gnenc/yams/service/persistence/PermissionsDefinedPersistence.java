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

package org.gnenc.yams.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import org.gnenc.yams.model.PermissionsDefined;

/**
 * The persistence interface for the permissions defined service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Drew A. Blessing
 * @see PermissionsDefinedPersistenceImpl
 * @see PermissionsDefinedUtil
 * @generated
 */
public interface PermissionsDefinedPersistence extends BasePersistence<PermissionsDefined> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PermissionsDefinedUtil} to access the permissions defined persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the permissions defined in the entity cache if it is enabled.
	*
	* @param permissionsDefined the permissions defined
	*/
	public void cacheResult(
		org.gnenc.yams.model.PermissionsDefined permissionsDefined);

	/**
	* Caches the permissions defineds in the entity cache if it is enabled.
	*
	* @param permissionsDefineds the permissions defineds
	*/
	public void cacheResult(
		java.util.List<org.gnenc.yams.model.PermissionsDefined> permissionsDefineds);

	/**
	* Creates a new permissions defined with the primary key. Does not add the permissions defined to the database.
	*
	* @param permissionKey the primary key for the new permissions defined
	* @return the new permissions defined
	*/
	public org.gnenc.yams.model.PermissionsDefined create(
		java.lang.String permissionKey);

	/**
	* Removes the permissions defined with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param permissionKey the primary key of the permissions defined
	* @return the permissions defined that was removed
	* @throws org.gnenc.yams.NoSuchPermissionsDefinedException if a permissions defined with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.PermissionsDefined remove(
		java.lang.String permissionKey)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchPermissionsDefinedException;

	public org.gnenc.yams.model.PermissionsDefined updateImpl(
		org.gnenc.yams.model.PermissionsDefined permissionsDefined,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the permissions defined with the primary key or throws a {@link org.gnenc.yams.NoSuchPermissionsDefinedException} if it could not be found.
	*
	* @param permissionKey the primary key of the permissions defined
	* @return the permissions defined
	* @throws org.gnenc.yams.NoSuchPermissionsDefinedException if a permissions defined with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.PermissionsDefined findByPrimaryKey(
		java.lang.String permissionKey)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchPermissionsDefinedException;

	/**
	* Returns the permissions defined with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param permissionKey the primary key of the permissions defined
	* @return the permissions defined, or <code>null</code> if a permissions defined with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.PermissionsDefined fetchByPrimaryKey(
		java.lang.String permissionKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the permissions defineds.
	*
	* @return the permissions defineds
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.PermissionsDefined> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.gnenc.yams.model.PermissionsDefined> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the permissions defineds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of permissions defineds
	* @param end the upper bound of the range of permissions defineds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of permissions defineds
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.PermissionsDefined> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the permissions defineds from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of permissions defineds.
	*
	* @return the number of permissions defineds
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}