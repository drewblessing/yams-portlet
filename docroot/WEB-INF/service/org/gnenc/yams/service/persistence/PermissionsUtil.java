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

import org.gnenc.yams.model.Permissions;

import java.util.List;

/**
 * The persistence utility for the permissions service. This utility wraps {@link PermissionsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Drew A. Blessing
 * @see PermissionsPersistence
 * @see PermissionsPersistenceImpl
 * @generated
 */
public class PermissionsUtil {
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
	public static void clearCache(Permissions permissions) {
		getPersistence().clearCache(permissions);
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
	public static List<Permissions> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Permissions> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Permissions> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static Permissions update(Permissions permissions, boolean merge)
		throws SystemException {
		return getPersistence().update(permissions, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static Permissions update(Permissions permissions, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(permissions, merge, serviceContext);
	}

	/**
	* Caches the permissions in the entity cache if it is enabled.
	*
	* @param permissions the permissions
	*/
	public static void cacheResult(org.gnenc.yams.model.Permissions permissions) {
		getPersistence().cacheResult(permissions);
	}

	/**
	* Caches the permissionses in the entity cache if it is enabled.
	*
	* @param permissionses the permissionses
	*/
	public static void cacheResult(
		java.util.List<org.gnenc.yams.model.Permissions> permissionses) {
		getPersistence().cacheResult(permissionses);
	}

	/**
	* Creates a new permissions with the primary key. Does not add the permissions to the database.
	*
	* @param id the primary key for the new permissions
	* @return the new permissions
	*/
	public static org.gnenc.yams.model.Permissions create(long id) {
		return getPersistence().create(id);
	}

	/**
	* Removes the permissions with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the permissions
	* @return the permissions that was removed
	* @throws org.gnenc.yams.NoSuchPermissionsException if a permissions with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.Permissions remove(long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchPermissionsException {
		return getPersistence().remove(id);
	}

	public static org.gnenc.yams.model.Permissions updateImpl(
		org.gnenc.yams.model.Permissions permissions, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(permissions, merge);
	}

	/**
	* Returns the permissions with the primary key or throws a {@link org.gnenc.yams.NoSuchPermissionsException} if it could not be found.
	*
	* @param id the primary key of the permissions
	* @return the permissions
	* @throws org.gnenc.yams.NoSuchPermissionsException if a permissions with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.Permissions findByPrimaryKey(long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchPermissionsException {
		return getPersistence().findByPrimaryKey(id);
	}

	/**
	* Returns the permissions with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the permissions
	* @return the permissions, or <code>null</code> if a permissions with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.Permissions fetchByPrimaryKey(long id)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(id);
	}

	/**
	* Returns all the permissionses where emailAddress = &#63; and fqgn = &#63; and groupPermission = &#63;.
	*
	* @param emailAddress the email address
	* @param fqgn the fqgn
	* @param groupPermission the group permission
	* @return the matching permissionses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.Permissions> findByEmailAddressAndFqgnAndGroupPermission(
		java.lang.String emailAddress, java.lang.String fqgn,
		boolean groupPermission)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEmailAddressAndFqgnAndGroupPermission(emailAddress,
			fqgn, groupPermission);
	}

	/**
	* Returns a range of all the permissionses where emailAddress = &#63; and fqgn = &#63; and groupPermission = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param emailAddress the email address
	* @param fqgn the fqgn
	* @param groupPermission the group permission
	* @param start the lower bound of the range of permissionses
	* @param end the upper bound of the range of permissionses (not inclusive)
	* @return the range of matching permissionses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.Permissions> findByEmailAddressAndFqgnAndGroupPermission(
		java.lang.String emailAddress, java.lang.String fqgn,
		boolean groupPermission, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEmailAddressAndFqgnAndGroupPermission(emailAddress,
			fqgn, groupPermission, start, end);
	}

	/**
	* Returns an ordered range of all the permissionses where emailAddress = &#63; and fqgn = &#63; and groupPermission = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param emailAddress the email address
	* @param fqgn the fqgn
	* @param groupPermission the group permission
	* @param start the lower bound of the range of permissionses
	* @param end the upper bound of the range of permissionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching permissionses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.Permissions> findByEmailAddressAndFqgnAndGroupPermission(
		java.lang.String emailAddress, java.lang.String fqgn,
		boolean groupPermission, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEmailAddressAndFqgnAndGroupPermission(emailAddress,
			fqgn, groupPermission, start, end, orderByComparator);
	}

	/**
	* Returns the first permissions in the ordered set where emailAddress = &#63; and fqgn = &#63; and groupPermission = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param emailAddress the email address
	* @param fqgn the fqgn
	* @param groupPermission the group permission
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching permissions
	* @throws org.gnenc.yams.NoSuchPermissionsException if a matching permissions could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.Permissions findByEmailAddressAndFqgnAndGroupPermission_First(
		java.lang.String emailAddress, java.lang.String fqgn,
		boolean groupPermission,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchPermissionsException {
		return getPersistence()
				   .findByEmailAddressAndFqgnAndGroupPermission_First(emailAddress,
			fqgn, groupPermission, orderByComparator);
	}

	/**
	* Returns the last permissions in the ordered set where emailAddress = &#63; and fqgn = &#63; and groupPermission = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param emailAddress the email address
	* @param fqgn the fqgn
	* @param groupPermission the group permission
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching permissions
	* @throws org.gnenc.yams.NoSuchPermissionsException if a matching permissions could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.Permissions findByEmailAddressAndFqgnAndGroupPermission_Last(
		java.lang.String emailAddress, java.lang.String fqgn,
		boolean groupPermission,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchPermissionsException {
		return getPersistence()
				   .findByEmailAddressAndFqgnAndGroupPermission_Last(emailAddress,
			fqgn, groupPermission, orderByComparator);
	}

	/**
	* Returns the permissionses before and after the current permissions in the ordered set where emailAddress = &#63; and fqgn = &#63; and groupPermission = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param id the primary key of the current permissions
	* @param emailAddress the email address
	* @param fqgn the fqgn
	* @param groupPermission the group permission
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next permissions
	* @throws org.gnenc.yams.NoSuchPermissionsException if a permissions with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.Permissions[] findByEmailAddressAndFqgnAndGroupPermission_PrevAndNext(
		long id, java.lang.String emailAddress, java.lang.String fqgn,
		boolean groupPermission,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchPermissionsException {
		return getPersistence()
				   .findByEmailAddressAndFqgnAndGroupPermission_PrevAndNext(id,
			emailAddress, fqgn, groupPermission, orderByComparator);
	}

	/**
	* Returns all the permissionses where fqgn = &#63; and groupPermission = &#63;.
	*
	* @param fqgn the fqgn
	* @param groupPermission the group permission
	* @return the matching permissionses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.Permissions> findByFqgnAndGroupPermission(
		java.lang.String fqgn, boolean groupPermission)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByFqgnAndGroupPermission(fqgn, groupPermission);
	}

	/**
	* Returns a range of all the permissionses where fqgn = &#63; and groupPermission = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param fqgn the fqgn
	* @param groupPermission the group permission
	* @param start the lower bound of the range of permissionses
	* @param end the upper bound of the range of permissionses (not inclusive)
	* @return the range of matching permissionses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.Permissions> findByFqgnAndGroupPermission(
		java.lang.String fqgn, boolean groupPermission, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByFqgnAndGroupPermission(fqgn, groupPermission, start,
			end);
	}

	/**
	* Returns an ordered range of all the permissionses where fqgn = &#63; and groupPermission = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param fqgn the fqgn
	* @param groupPermission the group permission
	* @param start the lower bound of the range of permissionses
	* @param end the upper bound of the range of permissionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching permissionses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.Permissions> findByFqgnAndGroupPermission(
		java.lang.String fqgn, boolean groupPermission, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByFqgnAndGroupPermission(fqgn, groupPermission, start,
			end, orderByComparator);
	}

	/**
	* Returns the first permissions in the ordered set where fqgn = &#63; and groupPermission = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param fqgn the fqgn
	* @param groupPermission the group permission
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching permissions
	* @throws org.gnenc.yams.NoSuchPermissionsException if a matching permissions could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.Permissions findByFqgnAndGroupPermission_First(
		java.lang.String fqgn, boolean groupPermission,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchPermissionsException {
		return getPersistence()
				   .findByFqgnAndGroupPermission_First(fqgn, groupPermission,
			orderByComparator);
	}

	/**
	* Returns the last permissions in the ordered set where fqgn = &#63; and groupPermission = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param fqgn the fqgn
	* @param groupPermission the group permission
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching permissions
	* @throws org.gnenc.yams.NoSuchPermissionsException if a matching permissions could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.Permissions findByFqgnAndGroupPermission_Last(
		java.lang.String fqgn, boolean groupPermission,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchPermissionsException {
		return getPersistence()
				   .findByFqgnAndGroupPermission_Last(fqgn, groupPermission,
			orderByComparator);
	}

	/**
	* Returns the permissionses before and after the current permissions in the ordered set where fqgn = &#63; and groupPermission = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param id the primary key of the current permissions
	* @param fqgn the fqgn
	* @param groupPermission the group permission
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next permissions
	* @throws org.gnenc.yams.NoSuchPermissionsException if a permissions with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.Permissions[] findByFqgnAndGroupPermission_PrevAndNext(
		long id, java.lang.String fqgn, boolean groupPermission,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchPermissionsException {
		return getPersistence()
				   .findByFqgnAndGroupPermission_PrevAndNext(id, fqgn,
			groupPermission, orderByComparator);
	}

	/**
	* Returns all the permissionses where emailAddress = &#63;.
	*
	* @param emailAddress the email address
	* @return the matching permissionses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.Permissions> findByEmailAddress(
		java.lang.String emailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEmailAddress(emailAddress);
	}

	/**
	* Returns a range of all the permissionses where emailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param emailAddress the email address
	* @param start the lower bound of the range of permissionses
	* @param end the upper bound of the range of permissionses (not inclusive)
	* @return the range of matching permissionses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.Permissions> findByEmailAddress(
		java.lang.String emailAddress, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEmailAddress(emailAddress, start, end);
	}

	/**
	* Returns an ordered range of all the permissionses where emailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param emailAddress the email address
	* @param start the lower bound of the range of permissionses
	* @param end the upper bound of the range of permissionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching permissionses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.Permissions> findByEmailAddress(
		java.lang.String emailAddress, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEmailAddress(emailAddress, start, end,
			orderByComparator);
	}

	/**
	* Returns the first permissions in the ordered set where emailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param emailAddress the email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching permissions
	* @throws org.gnenc.yams.NoSuchPermissionsException if a matching permissions could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.Permissions findByEmailAddress_First(
		java.lang.String emailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchPermissionsException {
		return getPersistence()
				   .findByEmailAddress_First(emailAddress, orderByComparator);
	}

	/**
	* Returns the last permissions in the ordered set where emailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param emailAddress the email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching permissions
	* @throws org.gnenc.yams.NoSuchPermissionsException if a matching permissions could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.Permissions findByEmailAddress_Last(
		java.lang.String emailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchPermissionsException {
		return getPersistence()
				   .findByEmailAddress_Last(emailAddress, orderByComparator);
	}

	/**
	* Returns the permissionses before and after the current permissions in the ordered set where emailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param id the primary key of the current permissions
	* @param emailAddress the email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next permissions
	* @throws org.gnenc.yams.NoSuchPermissionsException if a permissions with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.Permissions[] findByEmailAddress_PrevAndNext(
		long id, java.lang.String emailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchPermissionsException {
		return getPersistence()
				   .findByEmailAddress_PrevAndNext(id, emailAddress,
			orderByComparator);
	}

	/**
	* Returns all the permissionses.
	*
	* @return the permissionses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.Permissions> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<org.gnenc.yams.model.Permissions> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<org.gnenc.yams.model.Permissions> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the permissionses where emailAddress = &#63; and fqgn = &#63; and groupPermission = &#63; from the database.
	*
	* @param emailAddress the email address
	* @param fqgn the fqgn
	* @param groupPermission the group permission
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByEmailAddressAndFqgnAndGroupPermission(
		java.lang.String emailAddress, java.lang.String fqgn,
		boolean groupPermission)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByEmailAddressAndFqgnAndGroupPermission(emailAddress, fqgn,
			groupPermission);
	}

	/**
	* Removes all the permissionses where fqgn = &#63; and groupPermission = &#63; from the database.
	*
	* @param fqgn the fqgn
	* @param groupPermission the group permission
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByFqgnAndGroupPermission(java.lang.String fqgn,
		boolean groupPermission)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByFqgnAndGroupPermission(fqgn, groupPermission);
	}

	/**
	* Removes all the permissionses where emailAddress = &#63; from the database.
	*
	* @param emailAddress the email address
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByEmailAddress(java.lang.String emailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByEmailAddress(emailAddress);
	}

	/**
	* Removes all the permissionses from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of permissionses where emailAddress = &#63; and fqgn = &#63; and groupPermission = &#63;.
	*
	* @param emailAddress the email address
	* @param fqgn the fqgn
	* @param groupPermission the group permission
	* @return the number of matching permissionses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByEmailAddressAndFqgnAndGroupPermission(
		java.lang.String emailAddress, java.lang.String fqgn,
		boolean groupPermission)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByEmailAddressAndFqgnAndGroupPermission(emailAddress,
			fqgn, groupPermission);
	}

	/**
	* Returns the number of permissionses where fqgn = &#63; and groupPermission = &#63;.
	*
	* @param fqgn the fqgn
	* @param groupPermission the group permission
	* @return the number of matching permissionses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByFqgnAndGroupPermission(java.lang.String fqgn,
		boolean groupPermission)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByFqgnAndGroupPermission(fqgn, groupPermission);
	}

	/**
	* Returns the number of permissionses where emailAddress = &#63;.
	*
	* @param emailAddress the email address
	* @return the number of matching permissionses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByEmailAddress(java.lang.String emailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByEmailAddress(emailAddress);
	}

	/**
	* Returns the number of permissionses.
	*
	* @return the number of permissionses
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static PermissionsPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (PermissionsPersistence)PortletBeanLocatorUtil.locate(org.gnenc.yams.service.ClpSerializer.getServletContextName(),
					PermissionsPersistence.class.getName());

			ReferenceRegistry.registerReference(PermissionsUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(PermissionsPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(PermissionsUtil.class,
			"_persistence");
	}

	private static PermissionsPersistence _persistence;
}