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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.gnenc.yams.model.PermissionsDefined;

import java.util.List;

/**
 * The persistence utility for the permissions defined service. This utility wraps {@link PermissionsDefinedPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Drew A. Blessing
 * @see PermissionsDefinedPersistence
 * @see PermissionsDefinedPersistenceImpl
 * @generated
 */
public class PermissionsDefinedUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(PermissionsDefined permissionsDefined) {
		getPersistence().clearCache(permissionsDefined);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<PermissionsDefined> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PermissionsDefined> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PermissionsDefined> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static PermissionsDefined update(
		PermissionsDefined permissionsDefined, boolean merge)
		throws SystemException {
		return getPersistence().update(permissionsDefined, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static PermissionsDefined update(
		PermissionsDefined permissionsDefined, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(permissionsDefined, merge, serviceContext);
	}

	/**
	* Caches the permissions defined in the entity cache if it is enabled.
	*
	* @param permissionsDefined the permissions defined
	*/
	public static void cacheResult(
		org.gnenc.yams.model.PermissionsDefined permissionsDefined) {
		getPersistence().cacheResult(permissionsDefined);
	}

	/**
	* Caches the permissions defineds in the entity cache if it is enabled.
	*
	* @param permissionsDefineds the permissions defineds
	*/
	public static void cacheResult(
		java.util.List<org.gnenc.yams.model.PermissionsDefined> permissionsDefineds) {
		getPersistence().cacheResult(permissionsDefineds);
	}

	/**
	* Creates a new permissions defined with the primary key. Does not add the permissions defined to the database.
	*
	* @param permissionKey the primary key for the new permissions defined
	* @return the new permissions defined
	*/
	public static org.gnenc.yams.model.PermissionsDefined create(
		java.lang.String permissionKey) {
		return getPersistence().create(permissionKey);
	}

	/**
	* Removes the permissions defined with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param permissionKey the primary key of the permissions defined
	* @return the permissions defined that was removed
	* @throws org.gnenc.yams.NoSuchPermissionsDefinedException if a permissions defined with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.PermissionsDefined remove(
		java.lang.String permissionKey)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchPermissionsDefinedException {
		return getPersistence().remove(permissionKey);
	}

	public static org.gnenc.yams.model.PermissionsDefined updateImpl(
		org.gnenc.yams.model.PermissionsDefined permissionsDefined,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(permissionsDefined, merge);
	}

	/**
	* Returns the permissions defined with the primary key or throws a {@link org.gnenc.yams.NoSuchPermissionsDefinedException} if it could not be found.
	*
	* @param permissionKey the primary key of the permissions defined
	* @return the permissions defined
	* @throws org.gnenc.yams.NoSuchPermissionsDefinedException if a permissions defined with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.PermissionsDefined findByPrimaryKey(
		java.lang.String permissionKey)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchPermissionsDefinedException {
		return getPersistence().findByPrimaryKey(permissionKey);
	}

	/**
	* Returns the permissions defined with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param permissionKey the primary key of the permissions defined
	* @return the permissions defined, or <code>null</code> if a permissions defined with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.PermissionsDefined fetchByPrimaryKey(
		java.lang.String permissionKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(permissionKey);
	}

	/**
	* Returns all the permissions defineds where bitLocation = &#63;.
	*
	* @param bitLocation the bit location
	* @return the matching permissions defineds
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.PermissionsDefined> findByBitLocation(
		int bitLocation)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByBitLocation(bitLocation);
	}

	/**
	* Returns a range of all the permissions defineds where bitLocation = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param bitLocation the bit location
	* @param start the lower bound of the range of permissions defineds
	* @param end the upper bound of the range of permissions defineds (not inclusive)
	* @return the range of matching permissions defineds
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.PermissionsDefined> findByBitLocation(
		int bitLocation, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByBitLocation(bitLocation, start, end);
	}

	/**
	* Returns an ordered range of all the permissions defineds where bitLocation = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param bitLocation the bit location
	* @param start the lower bound of the range of permissions defineds
	* @param end the upper bound of the range of permissions defineds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching permissions defineds
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.PermissionsDefined> findByBitLocation(
		int bitLocation, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByBitLocation(bitLocation, start, end, orderByComparator);
	}

	/**
	* Returns the first permissions defined in the ordered set where bitLocation = &#63;.
	*
	* @param bitLocation the bit location
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching permissions defined
	* @throws org.gnenc.yams.NoSuchPermissionsDefinedException if a matching permissions defined could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.PermissionsDefined findByBitLocation_First(
		int bitLocation,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchPermissionsDefinedException {
		return getPersistence()
				   .findByBitLocation_First(bitLocation, orderByComparator);
	}

	/**
	* Returns the first permissions defined in the ordered set where bitLocation = &#63;.
	*
	* @param bitLocation the bit location
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching permissions defined, or <code>null</code> if a matching permissions defined could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.PermissionsDefined fetchByBitLocation_First(
		int bitLocation,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByBitLocation_First(bitLocation, orderByComparator);
	}

	/**
	* Returns the last permissions defined in the ordered set where bitLocation = &#63;.
	*
	* @param bitLocation the bit location
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching permissions defined
	* @throws org.gnenc.yams.NoSuchPermissionsDefinedException if a matching permissions defined could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.PermissionsDefined findByBitLocation_Last(
		int bitLocation,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchPermissionsDefinedException {
		return getPersistence()
				   .findByBitLocation_Last(bitLocation, orderByComparator);
	}

	/**
	* Returns the last permissions defined in the ordered set where bitLocation = &#63;.
	*
	* @param bitLocation the bit location
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching permissions defined, or <code>null</code> if a matching permissions defined could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.PermissionsDefined fetchByBitLocation_Last(
		int bitLocation,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByBitLocation_Last(bitLocation, orderByComparator);
	}

	/**
	* Returns the permissions defineds before and after the current permissions defined in the ordered set where bitLocation = &#63;.
	*
	* @param permissionKey the primary key of the current permissions defined
	* @param bitLocation the bit location
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next permissions defined
	* @throws org.gnenc.yams.NoSuchPermissionsDefinedException if a permissions defined with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.PermissionsDefined[] findByBitLocation_PrevAndNext(
		java.lang.String permissionKey, int bitLocation,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchPermissionsDefinedException {
		return getPersistence()
				   .findByBitLocation_PrevAndNext(permissionKey, bitLocation,
			orderByComparator);
	}

	/**
	* Returns all the permissions defineds.
	*
	* @return the permissions defineds
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.PermissionsDefined> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<org.gnenc.yams.model.PermissionsDefined> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<org.gnenc.yams.model.PermissionsDefined> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the permissions defineds where bitLocation = &#63; from the database.
	*
	* @param bitLocation the bit location
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByBitLocation(int bitLocation)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByBitLocation(bitLocation);
	}

	/**
	* Removes all the permissions defineds from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of permissions defineds where bitLocation = &#63;.
	*
	* @param bitLocation the bit location
	* @return the number of matching permissions defineds
	* @throws SystemException if a system exception occurred
	*/
	public static int countByBitLocation(int bitLocation)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByBitLocation(bitLocation);
	}

	/**
	* Returns the number of permissions defineds.
	*
	* @return the number of permissions defineds
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static PermissionsDefinedPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (PermissionsDefinedPersistence)PortletBeanLocatorUtil.locate(org.gnenc.yams.service.ClpSerializer.getServletContextName(),
					PermissionsDefinedPersistence.class.getName());

			ReferenceRegistry.registerReference(PermissionsDefinedUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(PermissionsDefinedPersistence persistence) {
	}

	private static PermissionsDefinedPersistence _persistence;
}