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

import org.gnenc.yams.model.Permissions;

/**
 * The persistence interface for the permissions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Drew A. Blessing
 * @see PermissionsPersistenceImpl
 * @see PermissionsUtil
 * @generated
 */
public interface PermissionsPersistence extends BasePersistence<Permissions> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PermissionsUtil} to access the permissions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the permissions in the entity cache if it is enabled.
	*
	* @param permissions the permissions
	*/
	public void cacheResult(org.gnenc.yams.model.Permissions permissions);

	/**
	* Caches the permissionses in the entity cache if it is enabled.
	*
	* @param permissionses the permissionses
	*/
	public void cacheResult(
		java.util.List<org.gnenc.yams.model.Permissions> permissionses);

	/**
	* Creates a new permissions with the primary key. Does not add the permissions to the database.
	*
	* @param id the primary key for the new permissions
	* @return the new permissions
	*/
	public org.gnenc.yams.model.Permissions create(long id);

	/**
	* Removes the permissions with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the permissions
	* @return the permissions that was removed
	* @throws org.gnenc.yams.NoSuchPermissionsException if a permissions with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.Permissions remove(long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchPermissionsException;

	public org.gnenc.yams.model.Permissions updateImpl(
		org.gnenc.yams.model.Permissions permissions, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the permissions with the primary key or throws a {@link org.gnenc.yams.NoSuchPermissionsException} if it could not be found.
	*
	* @param id the primary key of the permissions
	* @return the permissions
	* @throws org.gnenc.yams.NoSuchPermissionsException if a permissions with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.Permissions findByPrimaryKey(long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchPermissionsException;

	/**
	* Returns the permissions with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the permissions
	* @return the permissions, or <code>null</code> if a permissions with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.Permissions fetchByPrimaryKey(long id)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the permissionses.
	*
	* @return the permissionses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.Permissions> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.gnenc.yams.model.Permissions> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the permissionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of permissionses
	* @param end the upper bound of the range of permissionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of permissionses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.Permissions> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the permissionses from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of permissionses.
	*
	* @return the number of permissionses
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}