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

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import org.gnenc.yams.NoSuchPermissionsDefinedException;
import org.gnenc.yams.model.PermissionsDefined;
import org.gnenc.yams.model.impl.PermissionsDefinedImpl;
import org.gnenc.yams.model.impl.PermissionsDefinedModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the permissions defined service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Drew A. Blessing
 * @see PermissionsDefinedPersistence
 * @see PermissionsDefinedUtil
 * @generated
 */
public class PermissionsDefinedPersistenceImpl extends BasePersistenceImpl<PermissionsDefined>
	implements PermissionsDefinedPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PermissionsDefinedUtil} to access the permissions defined persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PermissionsDefinedImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_BITLOCATION =
		new FinderPath(PermissionsDefinedModelImpl.ENTITY_CACHE_ENABLED,
			PermissionsDefinedModelImpl.FINDER_CACHE_ENABLED,
			PermissionsDefinedImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByBitLocation",
			new String[] {
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BITLOCATION =
		new FinderPath(PermissionsDefinedModelImpl.ENTITY_CACHE_ENABLED,
			PermissionsDefinedModelImpl.FINDER_CACHE_ENABLED,
			PermissionsDefinedImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByBitLocation",
			new String[] { Integer.class.getName() },
			PermissionsDefinedModelImpl.BITLOCATION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_BITLOCATION = new FinderPath(PermissionsDefinedModelImpl.ENTITY_CACHE_ENABLED,
			PermissionsDefinedModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByBitLocation",
			new String[] { Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PermissionsDefinedModelImpl.ENTITY_CACHE_ENABLED,
			PermissionsDefinedModelImpl.FINDER_CACHE_ENABLED,
			PermissionsDefinedImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PermissionsDefinedModelImpl.ENTITY_CACHE_ENABLED,
			PermissionsDefinedModelImpl.FINDER_CACHE_ENABLED,
			PermissionsDefinedImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PermissionsDefinedModelImpl.ENTITY_CACHE_ENABLED,
			PermissionsDefinedModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the permissions defined in the entity cache if it is enabled.
	 *
	 * @param permissionsDefined the permissions defined
	 */
	public void cacheResult(PermissionsDefined permissionsDefined) {
		EntityCacheUtil.putResult(PermissionsDefinedModelImpl.ENTITY_CACHE_ENABLED,
			PermissionsDefinedImpl.class, permissionsDefined.getPrimaryKey(),
			permissionsDefined);

		permissionsDefined.resetOriginalValues();
	}

	/**
	 * Caches the permissions defineds in the entity cache if it is enabled.
	 *
	 * @param permissionsDefineds the permissions defineds
	 */
	public void cacheResult(List<PermissionsDefined> permissionsDefineds) {
		for (PermissionsDefined permissionsDefined : permissionsDefineds) {
			if (EntityCacheUtil.getResult(
						PermissionsDefinedModelImpl.ENTITY_CACHE_ENABLED,
						PermissionsDefinedImpl.class,
						permissionsDefined.getPrimaryKey()) == null) {
				cacheResult(permissionsDefined);
			}
			else {
				permissionsDefined.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all permissions defineds.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(PermissionsDefinedImpl.class.getName());
		}

		EntityCacheUtil.clearCache(PermissionsDefinedImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the permissions defined.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PermissionsDefined permissionsDefined) {
		EntityCacheUtil.removeResult(PermissionsDefinedModelImpl.ENTITY_CACHE_ENABLED,
			PermissionsDefinedImpl.class, permissionsDefined.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<PermissionsDefined> permissionsDefineds) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PermissionsDefined permissionsDefined : permissionsDefineds) {
			EntityCacheUtil.removeResult(PermissionsDefinedModelImpl.ENTITY_CACHE_ENABLED,
				PermissionsDefinedImpl.class, permissionsDefined.getPrimaryKey());
		}
	}

	/**
	 * Creates a new permissions defined with the primary key. Does not add the permissions defined to the database.
	 *
	 * @param permissionKey the primary key for the new permissions defined
	 * @return the new permissions defined
	 */
	public PermissionsDefined create(String permissionKey) {
		PermissionsDefined permissionsDefined = new PermissionsDefinedImpl();

		permissionsDefined.setNew(true);
		permissionsDefined.setPrimaryKey(permissionKey);

		return permissionsDefined;
	}

	/**
	 * Removes the permissions defined with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param permissionKey the primary key of the permissions defined
	 * @return the permissions defined that was removed
	 * @throws org.gnenc.yams.NoSuchPermissionsDefinedException if a permissions defined with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PermissionsDefined remove(String permissionKey)
		throws NoSuchPermissionsDefinedException, SystemException {
		return remove((Serializable)permissionKey);
	}

	/**
	 * Removes the permissions defined with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the permissions defined
	 * @return the permissions defined that was removed
	 * @throws org.gnenc.yams.NoSuchPermissionsDefinedException if a permissions defined with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PermissionsDefined remove(Serializable primaryKey)
		throws NoSuchPermissionsDefinedException, SystemException {
		Session session = null;

		try {
			session = openSession();

			PermissionsDefined permissionsDefined = (PermissionsDefined)session.get(PermissionsDefinedImpl.class,
					primaryKey);

			if (permissionsDefined == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPermissionsDefinedException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(permissionsDefined);
		}
		catch (NoSuchPermissionsDefinedException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected PermissionsDefined removeImpl(
		PermissionsDefined permissionsDefined) throws SystemException {
		permissionsDefined = toUnwrappedModel(permissionsDefined);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, permissionsDefined);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(permissionsDefined);

		return permissionsDefined;
	}

	@Override
	public PermissionsDefined updateImpl(
		org.gnenc.yams.model.PermissionsDefined permissionsDefined,
		boolean merge) throws SystemException {
		permissionsDefined = toUnwrappedModel(permissionsDefined);

		boolean isNew = permissionsDefined.isNew();

		PermissionsDefinedModelImpl permissionsDefinedModelImpl = (PermissionsDefinedModelImpl)permissionsDefined;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, permissionsDefined, merge);

			permissionsDefined.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !PermissionsDefinedModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((permissionsDefinedModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BITLOCATION.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Integer.valueOf(permissionsDefinedModelImpl.getOriginalBitLocation())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BITLOCATION,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BITLOCATION,
					args);

				args = new Object[] {
						Integer.valueOf(permissionsDefinedModelImpl.getBitLocation())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BITLOCATION,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BITLOCATION,
					args);
			}
		}

		EntityCacheUtil.putResult(PermissionsDefinedModelImpl.ENTITY_CACHE_ENABLED,
			PermissionsDefinedImpl.class, permissionsDefined.getPrimaryKey(),
			permissionsDefined);

		return permissionsDefined;
	}

	protected PermissionsDefined toUnwrappedModel(
		PermissionsDefined permissionsDefined) {
		if (permissionsDefined instanceof PermissionsDefinedImpl) {
			return permissionsDefined;
		}

		PermissionsDefinedImpl permissionsDefinedImpl = new PermissionsDefinedImpl();

		permissionsDefinedImpl.setNew(permissionsDefined.isNew());
		permissionsDefinedImpl.setPrimaryKey(permissionsDefined.getPrimaryKey());

		permissionsDefinedImpl.setPermissionKey(permissionsDefined.getPermissionKey());
		permissionsDefinedImpl.setCompanyId(permissionsDefined.getCompanyId());
		permissionsDefinedImpl.setUserId(permissionsDefined.getUserId());
		permissionsDefinedImpl.setUserName(permissionsDefined.getUserName());
		permissionsDefinedImpl.setCreateDate(permissionsDefined.getCreateDate());
		permissionsDefinedImpl.setModifiedDate(permissionsDefined.getModifiedDate());
		permissionsDefinedImpl.setBitLocation(permissionsDefined.getBitLocation());

		return permissionsDefinedImpl;
	}

	/**
	 * Returns the permissions defined with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the permissions defined
	 * @return the permissions defined
	 * @throws com.liferay.portal.NoSuchModelException if a permissions defined with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PermissionsDefined findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey((String)primaryKey);
	}

	/**
	 * Returns the permissions defined with the primary key or throws a {@link org.gnenc.yams.NoSuchPermissionsDefinedException} if it could not be found.
	 *
	 * @param permissionKey the primary key of the permissions defined
	 * @return the permissions defined
	 * @throws org.gnenc.yams.NoSuchPermissionsDefinedException if a permissions defined with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PermissionsDefined findByPrimaryKey(String permissionKey)
		throws NoSuchPermissionsDefinedException, SystemException {
		PermissionsDefined permissionsDefined = fetchByPrimaryKey(permissionKey);

		if (permissionsDefined == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + permissionKey);
			}

			throw new NoSuchPermissionsDefinedException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				permissionKey);
		}

		return permissionsDefined;
	}

	/**
	 * Returns the permissions defined with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the permissions defined
	 * @return the permissions defined, or <code>null</code> if a permissions defined with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PermissionsDefined fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey((String)primaryKey);
	}

	/**
	 * Returns the permissions defined with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param permissionKey the primary key of the permissions defined
	 * @return the permissions defined, or <code>null</code> if a permissions defined with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PermissionsDefined fetchByPrimaryKey(String permissionKey)
		throws SystemException {
		PermissionsDefined permissionsDefined = (PermissionsDefined)EntityCacheUtil.getResult(PermissionsDefinedModelImpl.ENTITY_CACHE_ENABLED,
				PermissionsDefinedImpl.class, permissionKey);

		if (permissionsDefined == _nullPermissionsDefined) {
			return null;
		}

		if (permissionsDefined == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				permissionsDefined = (PermissionsDefined)session.get(PermissionsDefinedImpl.class,
						permissionKey);
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (permissionsDefined != null) {
					cacheResult(permissionsDefined);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(PermissionsDefinedModelImpl.ENTITY_CACHE_ENABLED,
						PermissionsDefinedImpl.class, permissionKey,
						_nullPermissionsDefined);
				}

				closeSession(session);
			}
		}

		return permissionsDefined;
	}

	/**
	 * Returns all the permissions defineds where bitLocation = &#63;.
	 *
	 * @param bitLocation the bit location
	 * @return the matching permissions defineds
	 * @throws SystemException if a system exception occurred
	 */
	public List<PermissionsDefined> findByBitLocation(int bitLocation)
		throws SystemException {
		return findByBitLocation(bitLocation, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	public List<PermissionsDefined> findByBitLocation(int bitLocation,
		int start, int end) throws SystemException {
		return findByBitLocation(bitLocation, start, end, null);
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
	public List<PermissionsDefined> findByBitLocation(int bitLocation,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BITLOCATION;
			finderArgs = new Object[] { bitLocation };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_BITLOCATION;
			finderArgs = new Object[] { bitLocation, start, end, orderByComparator };
		}

		List<PermissionsDefined> list = (List<PermissionsDefined>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PermissionsDefined permissionsDefined : list) {
				if ((bitLocation != permissionsDefined.getBitLocation())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_PERMISSIONSDEFINED_WHERE);

			query.append(_FINDER_COLUMN_BITLOCATION_BITLOCATION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(PermissionsDefinedModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(bitLocation);

				list = (List<PermissionsDefined>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
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
	public PermissionsDefined findByBitLocation_First(int bitLocation,
		OrderByComparator orderByComparator)
		throws NoSuchPermissionsDefinedException, SystemException {
		PermissionsDefined permissionsDefined = fetchByBitLocation_First(bitLocation,
				orderByComparator);

		if (permissionsDefined != null) {
			return permissionsDefined;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("bitLocation=");
		msg.append(bitLocation);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPermissionsDefinedException(msg.toString());
	}

	/**
	 * Returns the first permissions defined in the ordered set where bitLocation = &#63;.
	 *
	 * @param bitLocation the bit location
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching permissions defined, or <code>null</code> if a matching permissions defined could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PermissionsDefined fetchByBitLocation_First(int bitLocation,
		OrderByComparator orderByComparator) throws SystemException {
		List<PermissionsDefined> list = findByBitLocation(bitLocation, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public PermissionsDefined findByBitLocation_Last(int bitLocation,
		OrderByComparator orderByComparator)
		throws NoSuchPermissionsDefinedException, SystemException {
		PermissionsDefined permissionsDefined = fetchByBitLocation_Last(bitLocation,
				orderByComparator);

		if (permissionsDefined != null) {
			return permissionsDefined;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("bitLocation=");
		msg.append(bitLocation);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPermissionsDefinedException(msg.toString());
	}

	/**
	 * Returns the last permissions defined in the ordered set where bitLocation = &#63;.
	 *
	 * @param bitLocation the bit location
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching permissions defined, or <code>null</code> if a matching permissions defined could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PermissionsDefined fetchByBitLocation_Last(int bitLocation,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByBitLocation(bitLocation);

		List<PermissionsDefined> list = findByBitLocation(bitLocation,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public PermissionsDefined[] findByBitLocation_PrevAndNext(
		String permissionKey, int bitLocation,
		OrderByComparator orderByComparator)
		throws NoSuchPermissionsDefinedException, SystemException {
		PermissionsDefined permissionsDefined = findByPrimaryKey(permissionKey);

		Session session = null;

		try {
			session = openSession();

			PermissionsDefined[] array = new PermissionsDefinedImpl[3];

			array[0] = getByBitLocation_PrevAndNext(session,
					permissionsDefined, bitLocation, orderByComparator, true);

			array[1] = permissionsDefined;

			array[2] = getByBitLocation_PrevAndNext(session,
					permissionsDefined, bitLocation, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PermissionsDefined getByBitLocation_PrevAndNext(Session session,
		PermissionsDefined permissionsDefined, int bitLocation,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PERMISSIONSDEFINED_WHERE);

		query.append(_FINDER_COLUMN_BITLOCATION_BITLOCATION_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(PermissionsDefinedModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(bitLocation);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(permissionsDefined);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PermissionsDefined> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the permissions defineds.
	 *
	 * @return the permissions defineds
	 * @throws SystemException if a system exception occurred
	 */
	public List<PermissionsDefined> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<PermissionsDefined> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
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
	public List<PermissionsDefined> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<PermissionsDefined> list = (List<PermissionsDefined>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_PERMISSIONSDEFINED);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PERMISSIONSDEFINED.concat(PermissionsDefinedModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<PermissionsDefined>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<PermissionsDefined>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the permissions defineds where bitLocation = &#63; from the database.
	 *
	 * @param bitLocation the bit location
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByBitLocation(int bitLocation) throws SystemException {
		for (PermissionsDefined permissionsDefined : findByBitLocation(
				bitLocation)) {
			remove(permissionsDefined);
		}
	}

	/**
	 * Removes all the permissions defineds from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (PermissionsDefined permissionsDefined : findAll()) {
			remove(permissionsDefined);
		}
	}

	/**
	 * Returns the number of permissions defineds where bitLocation = &#63;.
	 *
	 * @param bitLocation the bit location
	 * @return the number of matching permissions defineds
	 * @throws SystemException if a system exception occurred
	 */
	public int countByBitLocation(int bitLocation) throws SystemException {
		Object[] finderArgs = new Object[] { bitLocation };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_BITLOCATION,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PERMISSIONSDEFINED_WHERE);

			query.append(_FINDER_COLUMN_BITLOCATION_BITLOCATION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(bitLocation);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_BITLOCATION,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of permissions defineds.
	 *
	 * @return the number of permissions defineds
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PERMISSIONSDEFINED);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the permissions defined persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.gnenc.yams.model.PermissionsDefined")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<PermissionsDefined>> listenersList = new ArrayList<ModelListener<PermissionsDefined>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<PermissionsDefined>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(PermissionsDefinedImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = ActionLogPersistence.class)
	protected ActionLogPersistence actionLogPersistence;
	@BeanReference(type = JobQueuePersistence.class)
	protected JobQueuePersistence jobQueuePersistence;
	@BeanReference(type = PermissionsPersistence.class)
	protected PermissionsPersistence permissionsPersistence;
	@BeanReference(type = PermissionsDefinedPersistence.class)
	protected PermissionsDefinedPersistence permissionsDefinedPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_PERMISSIONSDEFINED = "SELECT permissionsDefined FROM PermissionsDefined permissionsDefined";
	private static final String _SQL_SELECT_PERMISSIONSDEFINED_WHERE = "SELECT permissionsDefined FROM PermissionsDefined permissionsDefined WHERE ";
	private static final String _SQL_COUNT_PERMISSIONSDEFINED = "SELECT COUNT(permissionsDefined) FROM PermissionsDefined permissionsDefined";
	private static final String _SQL_COUNT_PERMISSIONSDEFINED_WHERE = "SELECT COUNT(permissionsDefined) FROM PermissionsDefined permissionsDefined WHERE ";
	private static final String _FINDER_COLUMN_BITLOCATION_BITLOCATION_2 = "permissionsDefined.bitLocation = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "permissionsDefined.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PermissionsDefined exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PermissionsDefined exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(PermissionsDefinedPersistenceImpl.class);
	private static PermissionsDefined _nullPermissionsDefined = new PermissionsDefinedImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<PermissionsDefined> toCacheModel() {
				return _nullPermissionsDefinedCacheModel;
			}
		};

	private static CacheModel<PermissionsDefined> _nullPermissionsDefinedCacheModel =
		new CacheModel<PermissionsDefined>() {
			public PermissionsDefined toEntityModel() {
				return _nullPermissionsDefined;
			}
		};
}