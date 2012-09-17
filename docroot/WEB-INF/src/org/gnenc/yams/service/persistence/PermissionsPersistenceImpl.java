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

import org.gnenc.yams.NoSuchPermissionsException;
import org.gnenc.yams.model.Permissions;
import org.gnenc.yams.model.impl.PermissionsImpl;
import org.gnenc.yams.model.impl.PermissionsModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the permissions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Drew A. Blessing
 * @see PermissionsPersistence
 * @see PermissionsUtil
 * @generated
 */
public class PermissionsPersistenceImpl extends BasePersistenceImpl<Permissions>
	implements PermissionsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PermissionsUtil} to access the permissions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PermissionsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_EMAILADDRESSANDFQGN =
		new FinderPath(PermissionsModelImpl.ENTITY_CACHE_ENABLED,
			PermissionsModelImpl.FINDER_CACHE_ENABLED, PermissionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByEmailAddressAndFqgn",
			new String[] {
				String.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAILADDRESSANDFQGN =
		new FinderPath(PermissionsModelImpl.ENTITY_CACHE_ENABLED,
			PermissionsModelImpl.FINDER_CACHE_ENABLED, PermissionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByEmailAddressAndFqgn",
			new String[] { String.class.getName(), String.class.getName() },
			PermissionsModelImpl.EMAILADDRESS_COLUMN_BITMASK |
			PermissionsModelImpl.FQGN_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_EMAILADDRESSANDFQGN = new FinderPath(PermissionsModelImpl.ENTITY_CACHE_ENABLED,
			PermissionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByEmailAddressAndFqgn",
			new String[] { String.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_EMAILADDRESSANDFQGNANDGROUPPERMISSION =
		new FinderPath(PermissionsModelImpl.ENTITY_CACHE_ENABLED,
			PermissionsModelImpl.FINDER_CACHE_ENABLED, PermissionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByEmailAddressAndFqgnAndGroupPermission",
			new String[] {
				String.class.getName(), String.class.getName(),
				Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAILADDRESSANDFQGNANDGROUPPERMISSION =
		new FinderPath(PermissionsModelImpl.ENTITY_CACHE_ENABLED,
			PermissionsModelImpl.FINDER_CACHE_ENABLED, PermissionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByEmailAddressAndFqgnAndGroupPermission",
			new String[] {
				String.class.getName(), String.class.getName(),
				Boolean.class.getName()
			},
			PermissionsModelImpl.EMAILADDRESS_COLUMN_BITMASK |
			PermissionsModelImpl.FQGN_COLUMN_BITMASK |
			PermissionsModelImpl.GROUPPERMISSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_EMAILADDRESSANDFQGNANDGROUPPERMISSION =
		new FinderPath(PermissionsModelImpl.ENTITY_CACHE_ENABLED,
			PermissionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByEmailAddressAndFqgnAndGroupPermission",
			new String[] {
				String.class.getName(), String.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FQGNANDGROUPPERMISSION =
		new FinderPath(PermissionsModelImpl.ENTITY_CACHE_ENABLED,
			PermissionsModelImpl.FINDER_CACHE_ENABLED, PermissionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByFqgnAndGroupPermission",
			new String[] {
				String.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FQGNANDGROUPPERMISSION =
		new FinderPath(PermissionsModelImpl.ENTITY_CACHE_ENABLED,
			PermissionsModelImpl.FINDER_CACHE_ENABLED, PermissionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByFqgnAndGroupPermission",
			new String[] { String.class.getName(), Boolean.class.getName() },
			PermissionsModelImpl.FQGN_COLUMN_BITMASK |
			PermissionsModelImpl.GROUPPERMISSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_FQGNANDGROUPPERMISSION = new FinderPath(PermissionsModelImpl.ENTITY_CACHE_ENABLED,
			PermissionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByFqgnAndGroupPermission",
			new String[] { String.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_EMAILADDRESS =
		new FinderPath(PermissionsModelImpl.ENTITY_CACHE_ENABLED,
			PermissionsModelImpl.FINDER_CACHE_ENABLED, PermissionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByEmailAddress",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAILADDRESS =
		new FinderPath(PermissionsModelImpl.ENTITY_CACHE_ENABLED,
			PermissionsModelImpl.FINDER_CACHE_ENABLED, PermissionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByEmailAddress",
			new String[] { String.class.getName() },
			PermissionsModelImpl.EMAILADDRESS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_EMAILADDRESS = new FinderPath(PermissionsModelImpl.ENTITY_CACHE_ENABLED,
			PermissionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEmailAddress",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PermissionsModelImpl.ENTITY_CACHE_ENABLED,
			PermissionsModelImpl.FINDER_CACHE_ENABLED, PermissionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PermissionsModelImpl.ENTITY_CACHE_ENABLED,
			PermissionsModelImpl.FINDER_CACHE_ENABLED, PermissionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PermissionsModelImpl.ENTITY_CACHE_ENABLED,
			PermissionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the permissions in the entity cache if it is enabled.
	 *
	 * @param permissions the permissions
	 */
	public void cacheResult(Permissions permissions) {
		EntityCacheUtil.putResult(PermissionsModelImpl.ENTITY_CACHE_ENABLED,
			PermissionsImpl.class, permissions.getPrimaryKey(), permissions);

		permissions.resetOriginalValues();
	}

	/**
	 * Caches the permissionses in the entity cache if it is enabled.
	 *
	 * @param permissionses the permissionses
	 */
	public void cacheResult(List<Permissions> permissionses) {
		for (Permissions permissions : permissionses) {
			if (EntityCacheUtil.getResult(
						PermissionsModelImpl.ENTITY_CACHE_ENABLED,
						PermissionsImpl.class, permissions.getPrimaryKey()) == null) {
				cacheResult(permissions);
			}
			else {
				permissions.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all permissionses.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(PermissionsImpl.class.getName());
		}

		EntityCacheUtil.clearCache(PermissionsImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the permissions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Permissions permissions) {
		EntityCacheUtil.removeResult(PermissionsModelImpl.ENTITY_CACHE_ENABLED,
			PermissionsImpl.class, permissions.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Permissions> permissionses) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Permissions permissions : permissionses) {
			EntityCacheUtil.removeResult(PermissionsModelImpl.ENTITY_CACHE_ENABLED,
				PermissionsImpl.class, permissions.getPrimaryKey());
		}
	}

	/**
	 * Creates a new permissions with the primary key. Does not add the permissions to the database.
	 *
	 * @param id the primary key for the new permissions
	 * @return the new permissions
	 */
	public Permissions create(long id) {
		Permissions permissions = new PermissionsImpl();

		permissions.setNew(true);
		permissions.setPrimaryKey(id);

		return permissions;
	}

	/**
	 * Removes the permissions with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the permissions
	 * @return the permissions that was removed
	 * @throws org.gnenc.yams.NoSuchPermissionsException if a permissions with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Permissions remove(long id)
		throws NoSuchPermissionsException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the permissions with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the permissions
	 * @return the permissions that was removed
	 * @throws org.gnenc.yams.NoSuchPermissionsException if a permissions with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Permissions remove(Serializable primaryKey)
		throws NoSuchPermissionsException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Permissions permissions = (Permissions)session.get(PermissionsImpl.class,
					primaryKey);

			if (permissions == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPermissionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(permissions);
		}
		catch (NoSuchPermissionsException nsee) {
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
	protected Permissions removeImpl(Permissions permissions)
		throws SystemException {
		permissions = toUnwrappedModel(permissions);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, permissions);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(permissions);

		return permissions;
	}

	@Override
	public Permissions updateImpl(
		org.gnenc.yams.model.Permissions permissions, boolean merge)
		throws SystemException {
		permissions = toUnwrappedModel(permissions);

		boolean isNew = permissions.isNew();

		PermissionsModelImpl permissionsModelImpl = (PermissionsModelImpl)permissions;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, permissions, merge);

			permissions.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !PermissionsModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((permissionsModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAILADDRESSANDFQGN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						permissionsModelImpl.getOriginalEmailAddress(),
						
						permissionsModelImpl.getOriginalFqgn()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EMAILADDRESSANDFQGN,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAILADDRESSANDFQGN,
					args);

				args = new Object[] {
						permissionsModelImpl.getEmailAddress(),
						
						permissionsModelImpl.getFqgn()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EMAILADDRESSANDFQGN,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAILADDRESSANDFQGN,
					args);
			}

			if ((permissionsModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAILADDRESSANDFQGNANDGROUPPERMISSION.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						permissionsModelImpl.getOriginalEmailAddress(),
						
						permissionsModelImpl.getOriginalFqgn(),
						Boolean.valueOf(permissionsModelImpl.getOriginalGroupPermission())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EMAILADDRESSANDFQGNANDGROUPPERMISSION,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAILADDRESSANDFQGNANDGROUPPERMISSION,
					args);

				args = new Object[] {
						permissionsModelImpl.getEmailAddress(),
						
						permissionsModelImpl.getFqgn(),
						Boolean.valueOf(permissionsModelImpl.getGroupPermission())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EMAILADDRESSANDFQGNANDGROUPPERMISSION,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAILADDRESSANDFQGNANDGROUPPERMISSION,
					args);
			}

			if ((permissionsModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FQGNANDGROUPPERMISSION.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						permissionsModelImpl.getOriginalFqgn(),
						Boolean.valueOf(permissionsModelImpl.getOriginalGroupPermission())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FQGNANDGROUPPERMISSION,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FQGNANDGROUPPERMISSION,
					args);

				args = new Object[] {
						permissionsModelImpl.getFqgn(),
						Boolean.valueOf(permissionsModelImpl.getGroupPermission())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FQGNANDGROUPPERMISSION,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FQGNANDGROUPPERMISSION,
					args);
			}

			if ((permissionsModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAILADDRESS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						permissionsModelImpl.getOriginalEmailAddress()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EMAILADDRESS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAILADDRESS,
					args);

				args = new Object[] { permissionsModelImpl.getEmailAddress() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EMAILADDRESS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAILADDRESS,
					args);
			}
		}

		EntityCacheUtil.putResult(PermissionsModelImpl.ENTITY_CACHE_ENABLED,
			PermissionsImpl.class, permissions.getPrimaryKey(), permissions);

		return permissions;
	}

	protected Permissions toUnwrappedModel(Permissions permissions) {
		if (permissions instanceof PermissionsImpl) {
			return permissions;
		}

		PermissionsImpl permissionsImpl = new PermissionsImpl();

		permissionsImpl.setNew(permissions.isNew());
		permissionsImpl.setPrimaryKey(permissions.getPrimaryKey());

		permissionsImpl.setId(permissions.getId());
		permissionsImpl.setCompanyId(permissions.getCompanyId());
		permissionsImpl.setUserId(permissions.getUserId());
		permissionsImpl.setUserName(permissions.getUserName());
		permissionsImpl.setCreateDate(permissions.getCreateDate());
		permissionsImpl.setModifiedDate(permissions.getModifiedDate());
		permissionsImpl.setEmailAddress(permissions.getEmailAddress());
		permissionsImpl.setFqgn(permissions.getFqgn());
		permissionsImpl.setPermissions(permissions.getPermissions());
		permissionsImpl.setPermissionsGrantable(permissions.getPermissionsGrantable());
		permissionsImpl.setGroupPermission(permissions.isGroupPermission());

		return permissionsImpl;
	}

	/**
	 * Returns the permissions with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the permissions
	 * @return the permissions
	 * @throws com.liferay.portal.NoSuchModelException if a permissions with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Permissions findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the permissions with the primary key or throws a {@link org.gnenc.yams.NoSuchPermissionsException} if it could not be found.
	 *
	 * @param id the primary key of the permissions
	 * @return the permissions
	 * @throws org.gnenc.yams.NoSuchPermissionsException if a permissions with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Permissions findByPrimaryKey(long id)
		throws NoSuchPermissionsException, SystemException {
		Permissions permissions = fetchByPrimaryKey(id);

		if (permissions == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchPermissionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return permissions;
	}

	/**
	 * Returns the permissions with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the permissions
	 * @return the permissions, or <code>null</code> if a permissions with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Permissions fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the permissions with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the permissions
	 * @return the permissions, or <code>null</code> if a permissions with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Permissions fetchByPrimaryKey(long id) throws SystemException {
		Permissions permissions = (Permissions)EntityCacheUtil.getResult(PermissionsModelImpl.ENTITY_CACHE_ENABLED,
				PermissionsImpl.class, id);

		if (permissions == _nullPermissions) {
			return null;
		}

		if (permissions == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				permissions = (Permissions)session.get(PermissionsImpl.class,
						Long.valueOf(id));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (permissions != null) {
					cacheResult(permissions);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(PermissionsModelImpl.ENTITY_CACHE_ENABLED,
						PermissionsImpl.class, id, _nullPermissions);
				}

				closeSession(session);
			}
		}

		return permissions;
	}

	/**
	 * Returns all the permissionses where emailAddress = &#63; and fqgn = &#63;.
	 *
	 * @param emailAddress the email address
	 * @param fqgn the fqgn
	 * @return the matching permissionses
	 * @throws SystemException if a system exception occurred
	 */
	public List<Permissions> findByEmailAddressAndFqgn(String emailAddress,
		String fqgn) throws SystemException {
		return findByEmailAddressAndFqgn(emailAddress, fqgn, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the permissionses where emailAddress = &#63; and fqgn = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param emailAddress the email address
	 * @param fqgn the fqgn
	 * @param start the lower bound of the range of permissionses
	 * @param end the upper bound of the range of permissionses (not inclusive)
	 * @return the range of matching permissionses
	 * @throws SystemException if a system exception occurred
	 */
	public List<Permissions> findByEmailAddressAndFqgn(String emailAddress,
		String fqgn, int start, int end) throws SystemException {
		return findByEmailAddressAndFqgn(emailAddress, fqgn, start, end, null);
	}

	/**
	 * Returns an ordered range of all the permissionses where emailAddress = &#63; and fqgn = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param emailAddress the email address
	 * @param fqgn the fqgn
	 * @param start the lower bound of the range of permissionses
	 * @param end the upper bound of the range of permissionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching permissionses
	 * @throws SystemException if a system exception occurred
	 */
	public List<Permissions> findByEmailAddressAndFqgn(String emailAddress,
		String fqgn, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAILADDRESSANDFQGN;
			finderArgs = new Object[] { emailAddress, fqgn };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_EMAILADDRESSANDFQGN;
			finderArgs = new Object[] {
					emailAddress, fqgn,
					
					start, end, orderByComparator
				};
		}

		List<Permissions> list = (List<Permissions>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_PERMISSIONS_WHERE);

			if (emailAddress == null) {
				query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGN_EMAILADDRESS_1);
			}
			else {
				if (emailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGN_EMAILADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGN_EMAILADDRESS_2);
				}
			}

			if (fqgn == null) {
				query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGN_FQGN_1);
			}
			else {
				if (fqgn.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGN_FQGN_3);
				}
				else {
					query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGN_FQGN_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(PermissionsModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (emailAddress != null) {
					qPos.add(emailAddress);
				}

				if (fqgn != null) {
					qPos.add(fqgn);
				}

				list = (List<Permissions>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first permissions in the ordered set where emailAddress = &#63; and fqgn = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param emailAddress the email address
	 * @param fqgn the fqgn
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching permissions
	 * @throws org.gnenc.yams.NoSuchPermissionsException if a matching permissions could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Permissions findByEmailAddressAndFqgn_First(String emailAddress,
		String fqgn, OrderByComparator orderByComparator)
		throws NoSuchPermissionsException, SystemException {
		List<Permissions> list = findByEmailAddressAndFqgn(emailAddress, fqgn,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("emailAddress=");
			msg.append(emailAddress);

			msg.append(", fqgn=");
			msg.append(fqgn);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchPermissionsException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last permissions in the ordered set where emailAddress = &#63; and fqgn = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param emailAddress the email address
	 * @param fqgn the fqgn
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching permissions
	 * @throws org.gnenc.yams.NoSuchPermissionsException if a matching permissions could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Permissions findByEmailAddressAndFqgn_Last(String emailAddress,
		String fqgn, OrderByComparator orderByComparator)
		throws NoSuchPermissionsException, SystemException {
		int count = countByEmailAddressAndFqgn(emailAddress, fqgn);

		List<Permissions> list = findByEmailAddressAndFqgn(emailAddress, fqgn,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("emailAddress=");
			msg.append(emailAddress);

			msg.append(", fqgn=");
			msg.append(fqgn);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchPermissionsException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the permissionses before and after the current permissions in the ordered set where emailAddress = &#63; and fqgn = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param id the primary key of the current permissions
	 * @param emailAddress the email address
	 * @param fqgn the fqgn
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next permissions
	 * @throws org.gnenc.yams.NoSuchPermissionsException if a permissions with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Permissions[] findByEmailAddressAndFqgn_PrevAndNext(long id,
		String emailAddress, String fqgn, OrderByComparator orderByComparator)
		throws NoSuchPermissionsException, SystemException {
		Permissions permissions = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Permissions[] array = new PermissionsImpl[3];

			array[0] = getByEmailAddressAndFqgn_PrevAndNext(session,
					permissions, emailAddress, fqgn, orderByComparator, true);

			array[1] = permissions;

			array[2] = getByEmailAddressAndFqgn_PrevAndNext(session,
					permissions, emailAddress, fqgn, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Permissions getByEmailAddressAndFqgn_PrevAndNext(
		Session session, Permissions permissions, String emailAddress,
		String fqgn, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PERMISSIONS_WHERE);

		if (emailAddress == null) {
			query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGN_EMAILADDRESS_1);
		}
		else {
			if (emailAddress.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGN_EMAILADDRESS_3);
			}
			else {
				query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGN_EMAILADDRESS_2);
			}
		}

		if (fqgn == null) {
			query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGN_FQGN_1);
		}
		else {
			if (fqgn.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGN_FQGN_3);
			}
			else {
				query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGN_FQGN_2);
			}
		}

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
			query.append(PermissionsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (emailAddress != null) {
			qPos.add(emailAddress);
		}

		if (fqgn != null) {
			qPos.add(fqgn);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(permissions);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Permissions> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
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
	public List<Permissions> findByEmailAddressAndFqgnAndGroupPermission(
		String emailAddress, String fqgn, boolean groupPermission)
		throws SystemException {
		return findByEmailAddressAndFqgnAndGroupPermission(emailAddress, fqgn,
			groupPermission, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<Permissions> findByEmailAddressAndFqgnAndGroupPermission(
		String emailAddress, String fqgn, boolean groupPermission, int start,
		int end) throws SystemException {
		return findByEmailAddressAndFqgnAndGroupPermission(emailAddress, fqgn,
			groupPermission, start, end, null);
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
	public List<Permissions> findByEmailAddressAndFqgnAndGroupPermission(
		String emailAddress, String fqgn, boolean groupPermission, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAILADDRESSANDFQGNANDGROUPPERMISSION;
			finderArgs = new Object[] { emailAddress, fqgn, groupPermission };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_EMAILADDRESSANDFQGNANDGROUPPERMISSION;
			finderArgs = new Object[] {
					emailAddress, fqgn, groupPermission,
					
					start, end, orderByComparator
				};
		}

		List<Permissions> list = (List<Permissions>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_PERMISSIONS_WHERE);

			if (emailAddress == null) {
				query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGNANDGROUPPERMISSION_EMAILADDRESS_1);
			}
			else {
				if (emailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGNANDGROUPPERMISSION_EMAILADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGNANDGROUPPERMISSION_EMAILADDRESS_2);
				}
			}

			if (fqgn == null) {
				query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGNANDGROUPPERMISSION_FQGN_1);
			}
			else {
				if (fqgn.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGNANDGROUPPERMISSION_FQGN_3);
				}
				else {
					query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGNANDGROUPPERMISSION_FQGN_2);
				}
			}

			query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGNANDGROUPPERMISSION_GROUPPERMISSION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(PermissionsModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (emailAddress != null) {
					qPos.add(emailAddress);
				}

				if (fqgn != null) {
					qPos.add(fqgn);
				}

				qPos.add(groupPermission);

				list = (List<Permissions>)QueryUtil.list(q, getDialect(),
						start, end);
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
	public Permissions findByEmailAddressAndFqgnAndGroupPermission_First(
		String emailAddress, String fqgn, boolean groupPermission,
		OrderByComparator orderByComparator)
		throws NoSuchPermissionsException, SystemException {
		List<Permissions> list = findByEmailAddressAndFqgnAndGroupPermission(emailAddress,
				fqgn, groupPermission, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("emailAddress=");
			msg.append(emailAddress);

			msg.append(", fqgn=");
			msg.append(fqgn);

			msg.append(", groupPermission=");
			msg.append(groupPermission);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchPermissionsException(msg.toString());
		}
		else {
			return list.get(0);
		}
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
	public Permissions findByEmailAddressAndFqgnAndGroupPermission_Last(
		String emailAddress, String fqgn, boolean groupPermission,
		OrderByComparator orderByComparator)
		throws NoSuchPermissionsException, SystemException {
		int count = countByEmailAddressAndFqgnAndGroupPermission(emailAddress,
				fqgn, groupPermission);

		List<Permissions> list = findByEmailAddressAndFqgnAndGroupPermission(emailAddress,
				fqgn, groupPermission, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("emailAddress=");
			msg.append(emailAddress);

			msg.append(", fqgn=");
			msg.append(fqgn);

			msg.append(", groupPermission=");
			msg.append(groupPermission);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchPermissionsException(msg.toString());
		}
		else {
			return list.get(0);
		}
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
	public Permissions[] findByEmailAddressAndFqgnAndGroupPermission_PrevAndNext(
		long id, String emailAddress, String fqgn, boolean groupPermission,
		OrderByComparator orderByComparator)
		throws NoSuchPermissionsException, SystemException {
		Permissions permissions = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Permissions[] array = new PermissionsImpl[3];

			array[0] = getByEmailAddressAndFqgnAndGroupPermission_PrevAndNext(session,
					permissions, emailAddress, fqgn, groupPermission,
					orderByComparator, true);

			array[1] = permissions;

			array[2] = getByEmailAddressAndFqgnAndGroupPermission_PrevAndNext(session,
					permissions, emailAddress, fqgn, groupPermission,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Permissions getByEmailAddressAndFqgnAndGroupPermission_PrevAndNext(
		Session session, Permissions permissions, String emailAddress,
		String fqgn, boolean groupPermission,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PERMISSIONS_WHERE);

		if (emailAddress == null) {
			query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGNANDGROUPPERMISSION_EMAILADDRESS_1);
		}
		else {
			if (emailAddress.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGNANDGROUPPERMISSION_EMAILADDRESS_3);
			}
			else {
				query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGNANDGROUPPERMISSION_EMAILADDRESS_2);
			}
		}

		if (fqgn == null) {
			query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGNANDGROUPPERMISSION_FQGN_1);
		}
		else {
			if (fqgn.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGNANDGROUPPERMISSION_FQGN_3);
			}
			else {
				query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGNANDGROUPPERMISSION_FQGN_2);
			}
		}

		query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGNANDGROUPPERMISSION_GROUPPERMISSION_2);

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
			query.append(PermissionsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (emailAddress != null) {
			qPos.add(emailAddress);
		}

		if (fqgn != null) {
			qPos.add(fqgn);
		}

		qPos.add(groupPermission);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(permissions);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Permissions> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the permissionses where fqgn = &#63; and groupPermission = &#63;.
	 *
	 * @param fqgn the fqgn
	 * @param groupPermission the group permission
	 * @return the matching permissionses
	 * @throws SystemException if a system exception occurred
	 */
	public List<Permissions> findByFqgnAndGroupPermission(String fqgn,
		boolean groupPermission) throws SystemException {
		return findByFqgnAndGroupPermission(fqgn, groupPermission,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<Permissions> findByFqgnAndGroupPermission(String fqgn,
		boolean groupPermission, int start, int end) throws SystemException {
		return findByFqgnAndGroupPermission(fqgn, groupPermission, start, end,
			null);
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
	public List<Permissions> findByFqgnAndGroupPermission(String fqgn,
		boolean groupPermission, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FQGNANDGROUPPERMISSION;
			finderArgs = new Object[] { fqgn, groupPermission };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_FQGNANDGROUPPERMISSION;
			finderArgs = new Object[] {
					fqgn, groupPermission,
					
					start, end, orderByComparator
				};
		}

		List<Permissions> list = (List<Permissions>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_PERMISSIONS_WHERE);

			if (fqgn == null) {
				query.append(_FINDER_COLUMN_FQGNANDGROUPPERMISSION_FQGN_1);
			}
			else {
				if (fqgn.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_FQGNANDGROUPPERMISSION_FQGN_3);
				}
				else {
					query.append(_FINDER_COLUMN_FQGNANDGROUPPERMISSION_FQGN_2);
				}
			}

			query.append(_FINDER_COLUMN_FQGNANDGROUPPERMISSION_GROUPPERMISSION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(PermissionsModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (fqgn != null) {
					qPos.add(fqgn);
				}

				qPos.add(groupPermission);

				list = (List<Permissions>)QueryUtil.list(q, getDialect(),
						start, end);
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
	public Permissions findByFqgnAndGroupPermission_First(String fqgn,
		boolean groupPermission, OrderByComparator orderByComparator)
		throws NoSuchPermissionsException, SystemException {
		List<Permissions> list = findByFqgnAndGroupPermission(fqgn,
				groupPermission, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("fqgn=");
			msg.append(fqgn);

			msg.append(", groupPermission=");
			msg.append(groupPermission);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchPermissionsException(msg.toString());
		}
		else {
			return list.get(0);
		}
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
	public Permissions findByFqgnAndGroupPermission_Last(String fqgn,
		boolean groupPermission, OrderByComparator orderByComparator)
		throws NoSuchPermissionsException, SystemException {
		int count = countByFqgnAndGroupPermission(fqgn, groupPermission);

		List<Permissions> list = findByFqgnAndGroupPermission(fqgn,
				groupPermission, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("fqgn=");
			msg.append(fqgn);

			msg.append(", groupPermission=");
			msg.append(groupPermission);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchPermissionsException(msg.toString());
		}
		else {
			return list.get(0);
		}
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
	public Permissions[] findByFqgnAndGroupPermission_PrevAndNext(long id,
		String fqgn, boolean groupPermission,
		OrderByComparator orderByComparator)
		throws NoSuchPermissionsException, SystemException {
		Permissions permissions = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Permissions[] array = new PermissionsImpl[3];

			array[0] = getByFqgnAndGroupPermission_PrevAndNext(session,
					permissions, fqgn, groupPermission, orderByComparator, true);

			array[1] = permissions;

			array[2] = getByFqgnAndGroupPermission_PrevAndNext(session,
					permissions, fqgn, groupPermission, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Permissions getByFqgnAndGroupPermission_PrevAndNext(
		Session session, Permissions permissions, String fqgn,
		boolean groupPermission, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PERMISSIONS_WHERE);

		if (fqgn == null) {
			query.append(_FINDER_COLUMN_FQGNANDGROUPPERMISSION_FQGN_1);
		}
		else {
			if (fqgn.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_FQGNANDGROUPPERMISSION_FQGN_3);
			}
			else {
				query.append(_FINDER_COLUMN_FQGNANDGROUPPERMISSION_FQGN_2);
			}
		}

		query.append(_FINDER_COLUMN_FQGNANDGROUPPERMISSION_GROUPPERMISSION_2);

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
			query.append(PermissionsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (fqgn != null) {
			qPos.add(fqgn);
		}

		qPos.add(groupPermission);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(permissions);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Permissions> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the permissionses where emailAddress = &#63;.
	 *
	 * @param emailAddress the email address
	 * @return the matching permissionses
	 * @throws SystemException if a system exception occurred
	 */
	public List<Permissions> findByEmailAddress(String emailAddress)
		throws SystemException {
		return findByEmailAddress(emailAddress, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	public List<Permissions> findByEmailAddress(String emailAddress, int start,
		int end) throws SystemException {
		return findByEmailAddress(emailAddress, start, end, null);
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
	public List<Permissions> findByEmailAddress(String emailAddress, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAILADDRESS;
			finderArgs = new Object[] { emailAddress };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_EMAILADDRESS;
			finderArgs = new Object[] {
					emailAddress,
					
					start, end, orderByComparator
				};
		}

		List<Permissions> list = (List<Permissions>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_PERMISSIONS_WHERE);

			if (emailAddress == null) {
				query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_1);
			}
			else {
				if (emailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(PermissionsModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (emailAddress != null) {
					qPos.add(emailAddress);
				}

				list = (List<Permissions>)QueryUtil.list(q, getDialect(),
						start, end);
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
	public Permissions findByEmailAddress_First(String emailAddress,
		OrderByComparator orderByComparator)
		throws NoSuchPermissionsException, SystemException {
		List<Permissions> list = findByEmailAddress(emailAddress, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("emailAddress=");
			msg.append(emailAddress);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchPermissionsException(msg.toString());
		}
		else {
			return list.get(0);
		}
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
	public Permissions findByEmailAddress_Last(String emailAddress,
		OrderByComparator orderByComparator)
		throws NoSuchPermissionsException, SystemException {
		int count = countByEmailAddress(emailAddress);

		List<Permissions> list = findByEmailAddress(emailAddress, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("emailAddress=");
			msg.append(emailAddress);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchPermissionsException(msg.toString());
		}
		else {
			return list.get(0);
		}
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
	public Permissions[] findByEmailAddress_PrevAndNext(long id,
		String emailAddress, OrderByComparator orderByComparator)
		throws NoSuchPermissionsException, SystemException {
		Permissions permissions = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Permissions[] array = new PermissionsImpl[3];

			array[0] = getByEmailAddress_PrevAndNext(session, permissions,
					emailAddress, orderByComparator, true);

			array[1] = permissions;

			array[2] = getByEmailAddress_PrevAndNext(session, permissions,
					emailAddress, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Permissions getByEmailAddress_PrevAndNext(Session session,
		Permissions permissions, String emailAddress,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PERMISSIONS_WHERE);

		if (emailAddress == null) {
			query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_1);
		}
		else {
			if (emailAddress.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_3);
			}
			else {
				query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_2);
			}
		}

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
			query.append(PermissionsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (emailAddress != null) {
			qPos.add(emailAddress);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(permissions);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Permissions> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the permissionses.
	 *
	 * @return the permissionses
	 * @throws SystemException if a system exception occurred
	 */
	public List<Permissions> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<Permissions> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
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
	public List<Permissions> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Permissions> list = (List<Permissions>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_PERMISSIONS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PERMISSIONS.concat(PermissionsModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Permissions>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Permissions>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the permissionses where emailAddress = &#63; and fqgn = &#63; from the database.
	 *
	 * @param emailAddress the email address
	 * @param fqgn the fqgn
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByEmailAddressAndFqgn(String emailAddress, String fqgn)
		throws SystemException {
		for (Permissions permissions : findByEmailAddressAndFqgn(emailAddress,
				fqgn)) {
			remove(permissions);
		}
	}

	/**
	 * Removes all the permissionses where emailAddress = &#63; and fqgn = &#63; and groupPermission = &#63; from the database.
	 *
	 * @param emailAddress the email address
	 * @param fqgn the fqgn
	 * @param groupPermission the group permission
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByEmailAddressAndFqgnAndGroupPermission(
		String emailAddress, String fqgn, boolean groupPermission)
		throws SystemException {
		for (Permissions permissions : findByEmailAddressAndFqgnAndGroupPermission(
				emailAddress, fqgn, groupPermission)) {
			remove(permissions);
		}
	}

	/**
	 * Removes all the permissionses where fqgn = &#63; and groupPermission = &#63; from the database.
	 *
	 * @param fqgn the fqgn
	 * @param groupPermission the group permission
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByFqgnAndGroupPermission(String fqgn,
		boolean groupPermission) throws SystemException {
		for (Permissions permissions : findByFqgnAndGroupPermission(fqgn,
				groupPermission)) {
			remove(permissions);
		}
	}

	/**
	 * Removes all the permissionses where emailAddress = &#63; from the database.
	 *
	 * @param emailAddress the email address
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByEmailAddress(String emailAddress)
		throws SystemException {
		for (Permissions permissions : findByEmailAddress(emailAddress)) {
			remove(permissions);
		}
	}

	/**
	 * Removes all the permissionses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Permissions permissions : findAll()) {
			remove(permissions);
		}
	}

	/**
	 * Returns the number of permissionses where emailAddress = &#63; and fqgn = &#63;.
	 *
	 * @param emailAddress the email address
	 * @param fqgn the fqgn
	 * @return the number of matching permissionses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByEmailAddressAndFqgn(String emailAddress, String fqgn)
		throws SystemException {
		Object[] finderArgs = new Object[] { emailAddress, fqgn };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_EMAILADDRESSANDFQGN,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PERMISSIONS_WHERE);

			if (emailAddress == null) {
				query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGN_EMAILADDRESS_1);
			}
			else {
				if (emailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGN_EMAILADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGN_EMAILADDRESS_2);
				}
			}

			if (fqgn == null) {
				query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGN_FQGN_1);
			}
			else {
				if (fqgn.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGN_FQGN_3);
				}
				else {
					query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGN_FQGN_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (emailAddress != null) {
					qPos.add(emailAddress);
				}

				if (fqgn != null) {
					qPos.add(fqgn);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_EMAILADDRESSANDFQGN,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
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
	public int countByEmailAddressAndFqgnAndGroupPermission(
		String emailAddress, String fqgn, boolean groupPermission)
		throws SystemException {
		Object[] finderArgs = new Object[] { emailAddress, fqgn, groupPermission };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_EMAILADDRESSANDFQGNANDGROUPPERMISSION,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_PERMISSIONS_WHERE);

			if (emailAddress == null) {
				query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGNANDGROUPPERMISSION_EMAILADDRESS_1);
			}
			else {
				if (emailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGNANDGROUPPERMISSION_EMAILADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGNANDGROUPPERMISSION_EMAILADDRESS_2);
				}
			}

			if (fqgn == null) {
				query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGNANDGROUPPERMISSION_FQGN_1);
			}
			else {
				if (fqgn.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGNANDGROUPPERMISSION_FQGN_3);
				}
				else {
					query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGNANDGROUPPERMISSION_FQGN_2);
				}
			}

			query.append(_FINDER_COLUMN_EMAILADDRESSANDFQGNANDGROUPPERMISSION_GROUPPERMISSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (emailAddress != null) {
					qPos.add(emailAddress);
				}

				if (fqgn != null) {
					qPos.add(fqgn);
				}

				qPos.add(groupPermission);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_EMAILADDRESSANDFQGNANDGROUPPERMISSION,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of permissionses where fqgn = &#63; and groupPermission = &#63;.
	 *
	 * @param fqgn the fqgn
	 * @param groupPermission the group permission
	 * @return the number of matching permissionses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByFqgnAndGroupPermission(String fqgn,
		boolean groupPermission) throws SystemException {
		Object[] finderArgs = new Object[] { fqgn, groupPermission };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_FQGNANDGROUPPERMISSION,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PERMISSIONS_WHERE);

			if (fqgn == null) {
				query.append(_FINDER_COLUMN_FQGNANDGROUPPERMISSION_FQGN_1);
			}
			else {
				if (fqgn.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_FQGNANDGROUPPERMISSION_FQGN_3);
				}
				else {
					query.append(_FINDER_COLUMN_FQGNANDGROUPPERMISSION_FQGN_2);
				}
			}

			query.append(_FINDER_COLUMN_FQGNANDGROUPPERMISSION_GROUPPERMISSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (fqgn != null) {
					qPos.add(fqgn);
				}

				qPos.add(groupPermission);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FQGNANDGROUPPERMISSION,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of permissionses where emailAddress = &#63;.
	 *
	 * @param emailAddress the email address
	 * @return the number of matching permissionses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByEmailAddress(String emailAddress)
		throws SystemException {
		Object[] finderArgs = new Object[] { emailAddress };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_EMAILADDRESS,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PERMISSIONS_WHERE);

			if (emailAddress == null) {
				query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_1);
			}
			else {
				if (emailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (emailAddress != null) {
					qPos.add(emailAddress);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_EMAILADDRESS,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of permissionses.
	 *
	 * @return the number of permissionses
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PERMISSIONS);

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
	 * Initializes the permissions persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.gnenc.yams.model.Permissions")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Permissions>> listenersList = new ArrayList<ModelListener<Permissions>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Permissions>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(PermissionsImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = ActionLogPersistence.class)
	protected ActionLogPersistence actionLogPersistence;
	@BeanReference(type = PermissionsPersistence.class)
	protected PermissionsPersistence permissionsPersistence;
	@BeanReference(type = PermissionsDefinedPersistence.class)
	protected PermissionsDefinedPersistence permissionsDefinedPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_PERMISSIONS = "SELECT permissions FROM Permissions permissions";
	private static final String _SQL_SELECT_PERMISSIONS_WHERE = "SELECT permissions FROM Permissions permissions WHERE ";
	private static final String _SQL_COUNT_PERMISSIONS = "SELECT COUNT(permissions) FROM Permissions permissions";
	private static final String _SQL_COUNT_PERMISSIONS_WHERE = "SELECT COUNT(permissions) FROM Permissions permissions WHERE ";
	private static final String _FINDER_COLUMN_EMAILADDRESSANDFQGN_EMAILADDRESS_1 =
		"permissions.emailAddress IS NULL AND ";
	private static final String _FINDER_COLUMN_EMAILADDRESSANDFQGN_EMAILADDRESS_2 =
		"permissions.emailAddress = ? AND ";
	private static final String _FINDER_COLUMN_EMAILADDRESSANDFQGN_EMAILADDRESS_3 =
		"(permissions.emailAddress IS NULL OR permissions.emailAddress = ?) AND ";
	private static final String _FINDER_COLUMN_EMAILADDRESSANDFQGN_FQGN_1 = "permissions.fqgn IS NULL";
	private static final String _FINDER_COLUMN_EMAILADDRESSANDFQGN_FQGN_2 = "permissions.fqgn = ?";
	private static final String _FINDER_COLUMN_EMAILADDRESSANDFQGN_FQGN_3 = "(permissions.fqgn IS NULL OR permissions.fqgn = ?)";
	private static final String _FINDER_COLUMN_EMAILADDRESSANDFQGNANDGROUPPERMISSION_EMAILADDRESS_1 =
		"permissions.emailAddress IS NULL AND ";
	private static final String _FINDER_COLUMN_EMAILADDRESSANDFQGNANDGROUPPERMISSION_EMAILADDRESS_2 =
		"permissions.emailAddress = ? AND ";
	private static final String _FINDER_COLUMN_EMAILADDRESSANDFQGNANDGROUPPERMISSION_EMAILADDRESS_3 =
		"(permissions.emailAddress IS NULL OR permissions.emailAddress = ?) AND ";
	private static final String _FINDER_COLUMN_EMAILADDRESSANDFQGNANDGROUPPERMISSION_FQGN_1 =
		"permissions.fqgn IS NULL AND ";
	private static final String _FINDER_COLUMN_EMAILADDRESSANDFQGNANDGROUPPERMISSION_FQGN_2 =
		"permissions.fqgn = ? AND ";
	private static final String _FINDER_COLUMN_EMAILADDRESSANDFQGNANDGROUPPERMISSION_FQGN_3 =
		"(permissions.fqgn IS NULL OR permissions.fqgn = ?) AND ";
	private static final String _FINDER_COLUMN_EMAILADDRESSANDFQGNANDGROUPPERMISSION_GROUPPERMISSION_2 =
		"permissions.groupPermission = ?";
	private static final String _FINDER_COLUMN_FQGNANDGROUPPERMISSION_FQGN_1 = "permissions.fqgn IS NULL AND ";
	private static final String _FINDER_COLUMN_FQGNANDGROUPPERMISSION_FQGN_2 = "permissions.fqgn = ? AND ";
	private static final String _FINDER_COLUMN_FQGNANDGROUPPERMISSION_FQGN_3 = "(permissions.fqgn IS NULL OR permissions.fqgn = ?) AND ";
	private static final String _FINDER_COLUMN_FQGNANDGROUPPERMISSION_GROUPPERMISSION_2 =
		"permissions.groupPermission = ?";
	private static final String _FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_1 = "permissions.emailAddress IS NULL";
	private static final String _FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_2 = "permissions.emailAddress = ?";
	private static final String _FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_3 = "(permissions.emailAddress IS NULL OR permissions.emailAddress = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "permissions.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Permissions exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Permissions exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(PermissionsPersistenceImpl.class);
	private static Permissions _nullPermissions = new PermissionsImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Permissions> toCacheModel() {
				return _nullPermissionsCacheModel;
			}
		};

	private static CacheModel<Permissions> _nullPermissionsCacheModel = new CacheModel<Permissions>() {
			public Permissions toEntityModel() {
				return _nullPermissions;
			}
		};
}