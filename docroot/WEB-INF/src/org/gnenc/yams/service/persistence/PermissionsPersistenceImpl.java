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

	@BeanReference(type = PermissionsPersistence.class)
	protected PermissionsPersistence permissionsPersistence;
	@BeanReference(type = PermissionsDefinedPersistence.class)
	protected PermissionsDefinedPersistence permissionsDefinedPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_PERMISSIONS = "SELECT permissions FROM Permissions permissions";
	private static final String _SQL_COUNT_PERMISSIONS = "SELECT COUNT(permissions) FROM Permissions permissions";
	private static final String _ORDER_BY_ENTITY_ALIAS = "permissions.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Permissions exists with the primary key ";
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