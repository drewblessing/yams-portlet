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
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import org.gnenc.yams.NoSuchActionLogException;
import org.gnenc.yams.model.ActionLog;
import org.gnenc.yams.model.impl.ActionLogImpl;
import org.gnenc.yams.model.impl.ActionLogModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The persistence implementation for the action log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Drew A. Blessing
 * @see ActionLogPersistence
 * @see ActionLogUtil
 * @generated
 */
public class ActionLogPersistenceImpl extends BasePersistenceImpl<ActionLog>
	implements ActionLogPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ActionLogUtil} to access the action log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ActionLogImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USEREMAILADDRESS =
		new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, ActionLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByuserEmailAddress",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USEREMAILADDRESS =
		new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, ActionLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByuserEmailAddress", new String[] { String.class.getName() },
			ActionLogModelImpl.USEREMAILADDRESS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USEREMAILADDRESS = new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByuserEmailAddress", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MODIFIEDDATE =
		new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, ActionLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBymodifiedDate",
			new String[] {
				Date.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDDATE =
		new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, ActionLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBymodifiedDate",
			new String[] { Date.class.getName() },
			ActionLogModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MODIFIEDDATE = new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBymodifiedDate",
			new String[] { Date.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MODIFIEDDESCRIPTION =
		new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, ActionLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBymodifiedDescription",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDDESCRIPTION =
		new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, ActionLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBymodifiedDescription",
			new String[] { String.class.getName() },
			ActionLogModelImpl.MODIFIEDDESCRIPTION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MODIFIEDDESCRIPTION = new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBymodifiedDescription",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MODIFIEDFQGN =
		new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, ActionLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBymodifiedFqgn",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDFQGN =
		new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, ActionLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBymodifiedFqgn",
			new String[] { String.class.getName() },
			ActionLogModelImpl.MODIFIEDFQGN_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MODIFIEDFQGN = new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBymodifiedFqgn",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MODIFIEDUSEREMAILADDRESS =
		new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, ActionLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBymodifiedUserEmailAddress",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDUSEREMAILADDRESS =
		new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, ActionLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBymodifiedUserEmailAddress",
			new String[] { String.class.getName() },
			ActionLogModelImpl.MODIFIEDUSEREMAILADDRESS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MODIFIEDUSEREMAILADDRESS =
		new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBymodifiedUserEmailAddress",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MODIFIEDFQGNANDMODIFIEDDATE =
		new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, ActionLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBymodifiedFqgnAndModifiedDate",
			new String[] {
				String.class.getName(), Date.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDFQGNANDMODIFIEDDATE =
		new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, ActionLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBymodifiedFqgnAndModifiedDate",
			new String[] { String.class.getName(), Date.class.getName() },
			ActionLogModelImpl.MODIFIEDFQGN_COLUMN_BITMASK |
			ActionLogModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MODIFIEDFQGNANDMODIFIEDDATE =
		new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBymodifiedFqgnAndModifiedDate",
			new String[] { String.class.getName(), Date.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS =
		new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, ActionLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByuserEmailAddressAndModifiedUserEmailAddress",
			new String[] {
				String.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS =
		new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, ActionLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByuserEmailAddressAndModifiedUserEmailAddress",
			new String[] { String.class.getName(), String.class.getName() },
			ActionLogModelImpl.USEREMAILADDRESS_COLUMN_BITMASK |
			ActionLogModelImpl.MODIFIEDUSEREMAILADDRESS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS =
		new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByuserEmailAddressAndModifiedUserEmailAddress",
			new String[] { String.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS =
		new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, ActionLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress",
			new String[] {
				Date.class.getName(), String.class.getName(),
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS =
		new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, ActionLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress",
			new String[] {
				Date.class.getName(), String.class.getName(),
				String.class.getName()
			},
			ActionLogModelImpl.MODIFIEDDATE_COLUMN_BITMASK |
			ActionLogModelImpl.USEREMAILADDRESS_COLUMN_BITMASK |
			ActionLogModelImpl.MODIFIEDUSEREMAILADDRESS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS =
		new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress",
			new String[] {
				Date.class.getName(), String.class.getName(),
				String.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS =
		new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, ActionLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress",
			new String[] {
				String.class.getName(), String.class.getName(),
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS =
		new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, ActionLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress",
			new String[] {
				String.class.getName(), String.class.getName(),
				String.class.getName()
			},
			ActionLogModelImpl.MODIFIEDDESCRIPTION_COLUMN_BITMASK |
			ActionLogModelImpl.USEREMAILADDRESS_COLUMN_BITMASK |
			ActionLogModelImpl.MODIFIEDUSEREMAILADDRESS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS =
		new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress",
			new String[] {
				String.class.getName(), String.class.getName(),
				String.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, ActionLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, ActionLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the action log in the entity cache if it is enabled.
	 *
	 * @param actionLog the action log
	 */
	public void cacheResult(ActionLog actionLog) {
		EntityCacheUtil.putResult(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogImpl.class, actionLog.getPrimaryKey(), actionLog);

		actionLog.resetOriginalValues();
	}

	/**
	 * Caches the action logs in the entity cache if it is enabled.
	 *
	 * @param actionLogs the action logs
	 */
	public void cacheResult(List<ActionLog> actionLogs) {
		for (ActionLog actionLog : actionLogs) {
			if (EntityCacheUtil.getResult(
						ActionLogModelImpl.ENTITY_CACHE_ENABLED,
						ActionLogImpl.class, actionLog.getPrimaryKey()) == null) {
				cacheResult(actionLog);
			}
			else {
				actionLog.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all action logs.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ActionLogImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ActionLogImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the action log.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ActionLog actionLog) {
		EntityCacheUtil.removeResult(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogImpl.class, actionLog.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ActionLog> actionLogs) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ActionLog actionLog : actionLogs) {
			EntityCacheUtil.removeResult(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
				ActionLogImpl.class, actionLog.getPrimaryKey());
		}
	}

	/**
	 * Creates a new action log with the primary key. Does not add the action log to the database.
	 *
	 * @param id the primary key for the new action log
	 * @return the new action log
	 */
	public ActionLog create(long id) {
		ActionLog actionLog = new ActionLogImpl();

		actionLog.setNew(true);
		actionLog.setPrimaryKey(id);

		return actionLog;
	}

	/**
	 * Removes the action log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the action log
	 * @return the action log that was removed
	 * @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog remove(long id)
		throws NoSuchActionLogException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the action log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the action log
	 * @return the action log that was removed
	 * @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ActionLog remove(Serializable primaryKey)
		throws NoSuchActionLogException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ActionLog actionLog = (ActionLog)session.get(ActionLogImpl.class,
					primaryKey);

			if (actionLog == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchActionLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(actionLog);
		}
		catch (NoSuchActionLogException nsee) {
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
	protected ActionLog removeImpl(ActionLog actionLog)
		throws SystemException {
		actionLog = toUnwrappedModel(actionLog);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, actionLog);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(actionLog);

		return actionLog;
	}

	@Override
	public ActionLog updateImpl(org.gnenc.yams.model.ActionLog actionLog,
		boolean merge) throws SystemException {
		actionLog = toUnwrappedModel(actionLog);

		boolean isNew = actionLog.isNew();

		ActionLogModelImpl actionLogModelImpl = (ActionLogModelImpl)actionLog;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, actionLog, merge);

			actionLog.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ActionLogModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((actionLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USEREMAILADDRESS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						actionLogModelImpl.getOriginalUserEmailAddress()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USEREMAILADDRESS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USEREMAILADDRESS,
					args);

				args = new Object[] { actionLogModelImpl.getUserEmailAddress() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USEREMAILADDRESS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USEREMAILADDRESS,
					args);
			}

			if ((actionLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDDATE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						actionLogModelImpl.getOriginalModifiedDate()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODIFIEDDATE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDDATE,
					args);

				args = new Object[] { actionLogModelImpl.getModifiedDate() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODIFIEDDATE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDDATE,
					args);
			}

			if ((actionLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDDESCRIPTION.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						actionLogModelImpl.getOriginalModifiedDescription()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODIFIEDDESCRIPTION,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDDESCRIPTION,
					args);

				args = new Object[] { actionLogModelImpl.getModifiedDescription() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODIFIEDDESCRIPTION,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDDESCRIPTION,
					args);
			}

			if ((actionLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDFQGN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						actionLogModelImpl.getOriginalModifiedFqgn()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODIFIEDFQGN,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDFQGN,
					args);

				args = new Object[] { actionLogModelImpl.getModifiedFqgn() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODIFIEDFQGN,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDFQGN,
					args);
			}

			if ((actionLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDUSEREMAILADDRESS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						actionLogModelImpl.getOriginalModifiedUserEmailAddress()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODIFIEDUSEREMAILADDRESS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDUSEREMAILADDRESS,
					args);

				args = new Object[] {
						actionLogModelImpl.getModifiedUserEmailAddress()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODIFIEDUSEREMAILADDRESS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDUSEREMAILADDRESS,
					args);
			}

			if ((actionLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDFQGNANDMODIFIEDDATE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						actionLogModelImpl.getOriginalModifiedFqgn(),
						
						actionLogModelImpl.getOriginalModifiedDate()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODIFIEDFQGNANDMODIFIEDDATE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDFQGNANDMODIFIEDDATE,
					args);

				args = new Object[] {
						actionLogModelImpl.getModifiedFqgn(),
						
						actionLogModelImpl.getModifiedDate()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODIFIEDFQGNANDMODIFIEDDATE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDFQGNANDMODIFIEDDATE,
					args);
			}

			if ((actionLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						actionLogModelImpl.getOriginalUserEmailAddress(),
						
						actionLogModelImpl.getOriginalModifiedUserEmailAddress()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS,
					args);

				args = new Object[] {
						actionLogModelImpl.getUserEmailAddress(),
						
						actionLogModelImpl.getModifiedUserEmailAddress()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS,
					args);
			}

			if ((actionLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						actionLogModelImpl.getOriginalModifiedDate(),
						
						actionLogModelImpl.getOriginalUserEmailAddress(),
						
						actionLogModelImpl.getOriginalModifiedUserEmailAddress()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS,
					args);

				args = new Object[] {
						actionLogModelImpl.getModifiedDate(),
						
						actionLogModelImpl.getUserEmailAddress(),
						
						actionLogModelImpl.getModifiedUserEmailAddress()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS,
					args);
			}

			if ((actionLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						actionLogModelImpl.getOriginalModifiedDescription(),
						
						actionLogModelImpl.getOriginalUserEmailAddress(),
						
						actionLogModelImpl.getOriginalModifiedUserEmailAddress()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS,
					args);

				args = new Object[] {
						actionLogModelImpl.getModifiedDescription(),
						
						actionLogModelImpl.getUserEmailAddress(),
						
						actionLogModelImpl.getModifiedUserEmailAddress()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS,
					args);
			}
		}

		EntityCacheUtil.putResult(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
			ActionLogImpl.class, actionLog.getPrimaryKey(), actionLog);

		return actionLog;
	}

	protected ActionLog toUnwrappedModel(ActionLog actionLog) {
		if (actionLog instanceof ActionLogImpl) {
			return actionLog;
		}

		ActionLogImpl actionLogImpl = new ActionLogImpl();

		actionLogImpl.setNew(actionLog.isNew());
		actionLogImpl.setPrimaryKey(actionLog.getPrimaryKey());

		actionLogImpl.setId(actionLog.getId());
		actionLogImpl.setCompanyId(actionLog.getCompanyId());
		actionLogImpl.setUserId(actionLog.getUserId());
		actionLogImpl.setUserName(actionLog.getUserName());
		actionLogImpl.setModifiedDate(actionLog.getModifiedDate());
		actionLogImpl.setUserEmailAddress(actionLog.getUserEmailAddress());
		actionLogImpl.setModifiedUserId(actionLog.getModifiedUserId());
		actionLogImpl.setModifiedUserName(actionLog.getModifiedUserName());
		actionLogImpl.setModifiedUserEmailAddress(actionLog.getModifiedUserEmailAddress());
		actionLogImpl.setModifiedDescription(actionLog.getModifiedDescription());
		actionLogImpl.setModifiedFqgn(actionLog.getModifiedFqgn());

		return actionLogImpl;
	}

	/**
	 * Returns the action log with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the action log
	 * @return the action log
	 * @throws com.liferay.portal.NoSuchModelException if a action log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ActionLog findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the action log with the primary key or throws a {@link org.gnenc.yams.NoSuchActionLogException} if it could not be found.
	 *
	 * @param id the primary key of the action log
	 * @return the action log
	 * @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog findByPrimaryKey(long id)
		throws NoSuchActionLogException, SystemException {
		ActionLog actionLog = fetchByPrimaryKey(id);

		if (actionLog == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchActionLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return actionLog;
	}

	/**
	 * Returns the action log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the action log
	 * @return the action log, or <code>null</code> if a action log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ActionLog fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the action log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the action log
	 * @return the action log, or <code>null</code> if a action log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog fetchByPrimaryKey(long id) throws SystemException {
		ActionLog actionLog = (ActionLog)EntityCacheUtil.getResult(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
				ActionLogImpl.class, id);

		if (actionLog == _nullActionLog) {
			return null;
		}

		if (actionLog == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				actionLog = (ActionLog)session.get(ActionLogImpl.class,
						Long.valueOf(id));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (actionLog != null) {
					cacheResult(actionLog);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(ActionLogModelImpl.ENTITY_CACHE_ENABLED,
						ActionLogImpl.class, id, _nullActionLog);
				}

				closeSession(session);
			}
		}

		return actionLog;
	}

	/**
	 * Returns all the action logs where userEmailAddress = &#63;.
	 *
	 * @param userEmailAddress the user email address
	 * @return the matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findByuserEmailAddress(String userEmailAddress)
		throws SystemException {
		return findByuserEmailAddress(userEmailAddress, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the action logs where userEmailAddress = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userEmailAddress the user email address
	 * @param start the lower bound of the range of action logs
	 * @param end the upper bound of the range of action logs (not inclusive)
	 * @return the range of matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findByuserEmailAddress(String userEmailAddress,
		int start, int end) throws SystemException {
		return findByuserEmailAddress(userEmailAddress, start, end, null);
	}

	/**
	 * Returns an ordered range of all the action logs where userEmailAddress = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userEmailAddress the user email address
	 * @param start the lower bound of the range of action logs
	 * @param end the upper bound of the range of action logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findByuserEmailAddress(String userEmailAddress,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USEREMAILADDRESS;
			finderArgs = new Object[] { userEmailAddress };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USEREMAILADDRESS;
			finderArgs = new Object[] {
					userEmailAddress,
					
					start, end, orderByComparator
				};
		}

		List<ActionLog> list = (List<ActionLog>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ActionLog actionLog : list) {
				if (!Validator.equals(userEmailAddress,
							actionLog.getUserEmailAddress())) {
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

			query.append(_SQL_SELECT_ACTIONLOG_WHERE);

			if (userEmailAddress == null) {
				query.append(_FINDER_COLUMN_USEREMAILADDRESS_USEREMAILADDRESS_1);
			}
			else {
				if (userEmailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_USEREMAILADDRESS_USEREMAILADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_USEREMAILADDRESS_USEREMAILADDRESS_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ActionLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (userEmailAddress != null) {
					qPos.add(userEmailAddress);
				}

				list = (List<ActionLog>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first action log in the ordered set where userEmailAddress = &#63;.
	 *
	 * @param userEmailAddress the user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching action log
	 * @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog findByuserEmailAddress_First(String userEmailAddress,
		OrderByComparator orderByComparator)
		throws NoSuchActionLogException, SystemException {
		ActionLog actionLog = fetchByuserEmailAddress_First(userEmailAddress,
				orderByComparator);

		if (actionLog != null) {
			return actionLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userEmailAddress=");
		msg.append(userEmailAddress);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActionLogException(msg.toString());
	}

	/**
	 * Returns the first action log in the ordered set where userEmailAddress = &#63;.
	 *
	 * @param userEmailAddress the user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching action log, or <code>null</code> if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog fetchByuserEmailAddress_First(String userEmailAddress,
		OrderByComparator orderByComparator) throws SystemException {
		List<ActionLog> list = findByuserEmailAddress(userEmailAddress, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last action log in the ordered set where userEmailAddress = &#63;.
	 *
	 * @param userEmailAddress the user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching action log
	 * @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog findByuserEmailAddress_Last(String userEmailAddress,
		OrderByComparator orderByComparator)
		throws NoSuchActionLogException, SystemException {
		ActionLog actionLog = fetchByuserEmailAddress_Last(userEmailAddress,
				orderByComparator);

		if (actionLog != null) {
			return actionLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userEmailAddress=");
		msg.append(userEmailAddress);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActionLogException(msg.toString());
	}

	/**
	 * Returns the last action log in the ordered set where userEmailAddress = &#63;.
	 *
	 * @param userEmailAddress the user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching action log, or <code>null</code> if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog fetchByuserEmailAddress_Last(String userEmailAddress,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByuserEmailAddress(userEmailAddress);

		List<ActionLog> list = findByuserEmailAddress(userEmailAddress,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the action logs before and after the current action log in the ordered set where userEmailAddress = &#63;.
	 *
	 * @param id the primary key of the current action log
	 * @param userEmailAddress the user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next action log
	 * @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog[] findByuserEmailAddress_PrevAndNext(long id,
		String userEmailAddress, OrderByComparator orderByComparator)
		throws NoSuchActionLogException, SystemException {
		ActionLog actionLog = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			ActionLog[] array = new ActionLogImpl[3];

			array[0] = getByuserEmailAddress_PrevAndNext(session, actionLog,
					userEmailAddress, orderByComparator, true);

			array[1] = actionLog;

			array[2] = getByuserEmailAddress_PrevAndNext(session, actionLog,
					userEmailAddress, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ActionLog getByuserEmailAddress_PrevAndNext(Session session,
		ActionLog actionLog, String userEmailAddress,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACTIONLOG_WHERE);

		if (userEmailAddress == null) {
			query.append(_FINDER_COLUMN_USEREMAILADDRESS_USEREMAILADDRESS_1);
		}
		else {
			if (userEmailAddress.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_USEREMAILADDRESS_USEREMAILADDRESS_3);
			}
			else {
				query.append(_FINDER_COLUMN_USEREMAILADDRESS_USEREMAILADDRESS_2);
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
			query.append(ActionLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (userEmailAddress != null) {
			qPos.add(userEmailAddress);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(actionLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActionLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the action logs where modifiedDate = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @return the matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findBymodifiedDate(Date modifiedDate)
		throws SystemException {
		return findBymodifiedDate(modifiedDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the action logs where modifiedDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of action logs
	 * @param end the upper bound of the range of action logs (not inclusive)
	 * @return the range of matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findBymodifiedDate(Date modifiedDate, int start,
		int end) throws SystemException {
		return findBymodifiedDate(modifiedDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the action logs where modifiedDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of action logs
	 * @param end the upper bound of the range of action logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findBymodifiedDate(Date modifiedDate, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDDATE;
			finderArgs = new Object[] { modifiedDate };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MODIFIEDDATE;
			finderArgs = new Object[] {
					modifiedDate,
					
					start, end, orderByComparator
				};
		}

		List<ActionLog> list = (List<ActionLog>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ActionLog actionLog : list) {
				if (!Validator.equals(modifiedDate, actionLog.getModifiedDate())) {
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

			query.append(_SQL_SELECT_ACTIONLOG_WHERE);

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_MODIFIEDDATE_MODIFIEDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_MODIFIEDDATE_MODIFIEDDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ActionLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				list = (List<ActionLog>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first action log in the ordered set where modifiedDate = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching action log
	 * @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog findBymodifiedDate_First(Date modifiedDate,
		OrderByComparator orderByComparator)
		throws NoSuchActionLogException, SystemException {
		ActionLog actionLog = fetchBymodifiedDate_First(modifiedDate,
				orderByComparator);

		if (actionLog != null) {
			return actionLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActionLogException(msg.toString());
	}

	/**
	 * Returns the first action log in the ordered set where modifiedDate = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching action log, or <code>null</code> if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog fetchBymodifiedDate_First(Date modifiedDate,
		OrderByComparator orderByComparator) throws SystemException {
		List<ActionLog> list = findBymodifiedDate(modifiedDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last action log in the ordered set where modifiedDate = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching action log
	 * @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog findBymodifiedDate_Last(Date modifiedDate,
		OrderByComparator orderByComparator)
		throws NoSuchActionLogException, SystemException {
		ActionLog actionLog = fetchBymodifiedDate_Last(modifiedDate,
				orderByComparator);

		if (actionLog != null) {
			return actionLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActionLogException(msg.toString());
	}

	/**
	 * Returns the last action log in the ordered set where modifiedDate = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching action log, or <code>null</code> if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog fetchBymodifiedDate_Last(Date modifiedDate,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBymodifiedDate(modifiedDate);

		List<ActionLog> list = findBymodifiedDate(modifiedDate, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the action logs before and after the current action log in the ordered set where modifiedDate = &#63;.
	 *
	 * @param id the primary key of the current action log
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next action log
	 * @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog[] findBymodifiedDate_PrevAndNext(long id,
		Date modifiedDate, OrderByComparator orderByComparator)
		throws NoSuchActionLogException, SystemException {
		ActionLog actionLog = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			ActionLog[] array = new ActionLogImpl[3];

			array[0] = getBymodifiedDate_PrevAndNext(session, actionLog,
					modifiedDate, orderByComparator, true);

			array[1] = actionLog;

			array[2] = getBymodifiedDate_PrevAndNext(session, actionLog,
					modifiedDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ActionLog getBymodifiedDate_PrevAndNext(Session session,
		ActionLog actionLog, Date modifiedDate,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACTIONLOG_WHERE);

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_MODIFIEDDATE_MODIFIEDDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_MODIFIEDDATE_MODIFIEDDATE_2);
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
			query.append(ActionLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (modifiedDate != null) {
			qPos.add(CalendarUtil.getTimestamp(modifiedDate));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(actionLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActionLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the action logs where modifiedDescription = &#63;.
	 *
	 * @param modifiedDescription the modified description
	 * @return the matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findBymodifiedDescription(String modifiedDescription)
		throws SystemException {
		return findBymodifiedDescription(modifiedDescription,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the action logs where modifiedDescription = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDescription the modified description
	 * @param start the lower bound of the range of action logs
	 * @param end the upper bound of the range of action logs (not inclusive)
	 * @return the range of matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findBymodifiedDescription(
		String modifiedDescription, int start, int end)
		throws SystemException {
		return findBymodifiedDescription(modifiedDescription, start, end, null);
	}

	/**
	 * Returns an ordered range of all the action logs where modifiedDescription = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDescription the modified description
	 * @param start the lower bound of the range of action logs
	 * @param end the upper bound of the range of action logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findBymodifiedDescription(
		String modifiedDescription, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDDESCRIPTION;
			finderArgs = new Object[] { modifiedDescription };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MODIFIEDDESCRIPTION;
			finderArgs = new Object[] {
					modifiedDescription,
					
					start, end, orderByComparator
				};
		}

		List<ActionLog> list = (List<ActionLog>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ActionLog actionLog : list) {
				if (!Validator.equals(modifiedDescription,
							actionLog.getModifiedDescription())) {
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

			query.append(_SQL_SELECT_ACTIONLOG_WHERE);

			if (modifiedDescription == null) {
				query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTION_MODIFIEDDESCRIPTION_1);
			}
			else {
				if (modifiedDescription.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTION_MODIFIEDDESCRIPTION_3);
				}
				else {
					query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTION_MODIFIEDDESCRIPTION_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ActionLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedDescription != null) {
					qPos.add(modifiedDescription);
				}

				list = (List<ActionLog>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first action log in the ordered set where modifiedDescription = &#63;.
	 *
	 * @param modifiedDescription the modified description
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching action log
	 * @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog findBymodifiedDescription_First(
		String modifiedDescription, OrderByComparator orderByComparator)
		throws NoSuchActionLogException, SystemException {
		ActionLog actionLog = fetchBymodifiedDescription_First(modifiedDescription,
				orderByComparator);

		if (actionLog != null) {
			return actionLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDescription=");
		msg.append(modifiedDescription);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActionLogException(msg.toString());
	}

	/**
	 * Returns the first action log in the ordered set where modifiedDescription = &#63;.
	 *
	 * @param modifiedDescription the modified description
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching action log, or <code>null</code> if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog fetchBymodifiedDescription_First(
		String modifiedDescription, OrderByComparator orderByComparator)
		throws SystemException {
		List<ActionLog> list = findBymodifiedDescription(modifiedDescription,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last action log in the ordered set where modifiedDescription = &#63;.
	 *
	 * @param modifiedDescription the modified description
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching action log
	 * @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog findBymodifiedDescription_Last(
		String modifiedDescription, OrderByComparator orderByComparator)
		throws NoSuchActionLogException, SystemException {
		ActionLog actionLog = fetchBymodifiedDescription_Last(modifiedDescription,
				orderByComparator);

		if (actionLog != null) {
			return actionLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDescription=");
		msg.append(modifiedDescription);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActionLogException(msg.toString());
	}

	/**
	 * Returns the last action log in the ordered set where modifiedDescription = &#63;.
	 *
	 * @param modifiedDescription the modified description
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching action log, or <code>null</code> if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog fetchBymodifiedDescription_Last(
		String modifiedDescription, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countBymodifiedDescription(modifiedDescription);

		List<ActionLog> list = findBymodifiedDescription(modifiedDescription,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the action logs before and after the current action log in the ordered set where modifiedDescription = &#63;.
	 *
	 * @param id the primary key of the current action log
	 * @param modifiedDescription the modified description
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next action log
	 * @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog[] findBymodifiedDescription_PrevAndNext(long id,
		String modifiedDescription, OrderByComparator orderByComparator)
		throws NoSuchActionLogException, SystemException {
		ActionLog actionLog = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			ActionLog[] array = new ActionLogImpl[3];

			array[0] = getBymodifiedDescription_PrevAndNext(session, actionLog,
					modifiedDescription, orderByComparator, true);

			array[1] = actionLog;

			array[2] = getBymodifiedDescription_PrevAndNext(session, actionLog,
					modifiedDescription, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ActionLog getBymodifiedDescription_PrevAndNext(Session session,
		ActionLog actionLog, String modifiedDescription,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACTIONLOG_WHERE);

		if (modifiedDescription == null) {
			query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTION_MODIFIEDDESCRIPTION_1);
		}
		else {
			if (modifiedDescription.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTION_MODIFIEDDESCRIPTION_3);
			}
			else {
				query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTION_MODIFIEDDESCRIPTION_2);
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
			query.append(ActionLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (modifiedDescription != null) {
			qPos.add(modifiedDescription);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(actionLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActionLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the action logs where modifiedFqgn = &#63;.
	 *
	 * @param modifiedFqgn the modified fqgn
	 * @return the matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findBymodifiedFqgn(String modifiedFqgn)
		throws SystemException {
		return findBymodifiedFqgn(modifiedFqgn, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the action logs where modifiedFqgn = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedFqgn the modified fqgn
	 * @param start the lower bound of the range of action logs
	 * @param end the upper bound of the range of action logs (not inclusive)
	 * @return the range of matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findBymodifiedFqgn(String modifiedFqgn, int start,
		int end) throws SystemException {
		return findBymodifiedFqgn(modifiedFqgn, start, end, null);
	}

	/**
	 * Returns an ordered range of all the action logs where modifiedFqgn = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedFqgn the modified fqgn
	 * @param start the lower bound of the range of action logs
	 * @param end the upper bound of the range of action logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findBymodifiedFqgn(String modifiedFqgn, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDFQGN;
			finderArgs = new Object[] { modifiedFqgn };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MODIFIEDFQGN;
			finderArgs = new Object[] {
					modifiedFqgn,
					
					start, end, orderByComparator
				};
		}

		List<ActionLog> list = (List<ActionLog>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ActionLog actionLog : list) {
				if (!Validator.equals(modifiedFqgn, actionLog.getModifiedFqgn())) {
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

			query.append(_SQL_SELECT_ACTIONLOG_WHERE);

			if (modifiedFqgn == null) {
				query.append(_FINDER_COLUMN_MODIFIEDFQGN_MODIFIEDFQGN_1);
			}
			else {
				if (modifiedFqgn.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MODIFIEDFQGN_MODIFIEDFQGN_3);
				}
				else {
					query.append(_FINDER_COLUMN_MODIFIEDFQGN_MODIFIEDFQGN_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ActionLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedFqgn != null) {
					qPos.add(modifiedFqgn);
				}

				list = (List<ActionLog>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first action log in the ordered set where modifiedFqgn = &#63;.
	 *
	 * @param modifiedFqgn the modified fqgn
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching action log
	 * @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog findBymodifiedFqgn_First(String modifiedFqgn,
		OrderByComparator orderByComparator)
		throws NoSuchActionLogException, SystemException {
		ActionLog actionLog = fetchBymodifiedFqgn_First(modifiedFqgn,
				orderByComparator);

		if (actionLog != null) {
			return actionLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedFqgn=");
		msg.append(modifiedFqgn);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActionLogException(msg.toString());
	}

	/**
	 * Returns the first action log in the ordered set where modifiedFqgn = &#63;.
	 *
	 * @param modifiedFqgn the modified fqgn
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching action log, or <code>null</code> if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog fetchBymodifiedFqgn_First(String modifiedFqgn,
		OrderByComparator orderByComparator) throws SystemException {
		List<ActionLog> list = findBymodifiedFqgn(modifiedFqgn, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last action log in the ordered set where modifiedFqgn = &#63;.
	 *
	 * @param modifiedFqgn the modified fqgn
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching action log
	 * @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog findBymodifiedFqgn_Last(String modifiedFqgn,
		OrderByComparator orderByComparator)
		throws NoSuchActionLogException, SystemException {
		ActionLog actionLog = fetchBymodifiedFqgn_Last(modifiedFqgn,
				orderByComparator);

		if (actionLog != null) {
			return actionLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedFqgn=");
		msg.append(modifiedFqgn);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActionLogException(msg.toString());
	}

	/**
	 * Returns the last action log in the ordered set where modifiedFqgn = &#63;.
	 *
	 * @param modifiedFqgn the modified fqgn
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching action log, or <code>null</code> if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog fetchBymodifiedFqgn_Last(String modifiedFqgn,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBymodifiedFqgn(modifiedFqgn);

		List<ActionLog> list = findBymodifiedFqgn(modifiedFqgn, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the action logs before and after the current action log in the ordered set where modifiedFqgn = &#63;.
	 *
	 * @param id the primary key of the current action log
	 * @param modifiedFqgn the modified fqgn
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next action log
	 * @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog[] findBymodifiedFqgn_PrevAndNext(long id,
		String modifiedFqgn, OrderByComparator orderByComparator)
		throws NoSuchActionLogException, SystemException {
		ActionLog actionLog = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			ActionLog[] array = new ActionLogImpl[3];

			array[0] = getBymodifiedFqgn_PrevAndNext(session, actionLog,
					modifiedFqgn, orderByComparator, true);

			array[1] = actionLog;

			array[2] = getBymodifiedFqgn_PrevAndNext(session, actionLog,
					modifiedFqgn, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ActionLog getBymodifiedFqgn_PrevAndNext(Session session,
		ActionLog actionLog, String modifiedFqgn,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACTIONLOG_WHERE);

		if (modifiedFqgn == null) {
			query.append(_FINDER_COLUMN_MODIFIEDFQGN_MODIFIEDFQGN_1);
		}
		else {
			if (modifiedFqgn.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MODIFIEDFQGN_MODIFIEDFQGN_3);
			}
			else {
				query.append(_FINDER_COLUMN_MODIFIEDFQGN_MODIFIEDFQGN_2);
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
			query.append(ActionLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (modifiedFqgn != null) {
			qPos.add(modifiedFqgn);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(actionLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActionLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the action logs where modifiedUserEmailAddress = &#63;.
	 *
	 * @param modifiedUserEmailAddress the modified user email address
	 * @return the matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findBymodifiedUserEmailAddress(
		String modifiedUserEmailAddress) throws SystemException {
		return findBymodifiedUserEmailAddress(modifiedUserEmailAddress,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the action logs where modifiedUserEmailAddress = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedUserEmailAddress the modified user email address
	 * @param start the lower bound of the range of action logs
	 * @param end the upper bound of the range of action logs (not inclusive)
	 * @return the range of matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findBymodifiedUserEmailAddress(
		String modifiedUserEmailAddress, int start, int end)
		throws SystemException {
		return findBymodifiedUserEmailAddress(modifiedUserEmailAddress, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the action logs where modifiedUserEmailAddress = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedUserEmailAddress the modified user email address
	 * @param start the lower bound of the range of action logs
	 * @param end the upper bound of the range of action logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findBymodifiedUserEmailAddress(
		String modifiedUserEmailAddress, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDUSEREMAILADDRESS;
			finderArgs = new Object[] { modifiedUserEmailAddress };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MODIFIEDUSEREMAILADDRESS;
			finderArgs = new Object[] {
					modifiedUserEmailAddress,
					
					start, end, orderByComparator
				};
		}

		List<ActionLog> list = (List<ActionLog>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ActionLog actionLog : list) {
				if (!Validator.equals(modifiedUserEmailAddress,
							actionLog.getModifiedUserEmailAddress())) {
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

			query.append(_SQL_SELECT_ACTIONLOG_WHERE);

			if (modifiedUserEmailAddress == null) {
				query.append(_FINDER_COLUMN_MODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_1);
			}
			else {
				if (modifiedUserEmailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_MODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ActionLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedUserEmailAddress != null) {
					qPos.add(modifiedUserEmailAddress);
				}

				list = (List<ActionLog>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first action log in the ordered set where modifiedUserEmailAddress = &#63;.
	 *
	 * @param modifiedUserEmailAddress the modified user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching action log
	 * @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog findBymodifiedUserEmailAddress_First(
		String modifiedUserEmailAddress, OrderByComparator orderByComparator)
		throws NoSuchActionLogException, SystemException {
		ActionLog actionLog = fetchBymodifiedUserEmailAddress_First(modifiedUserEmailAddress,
				orderByComparator);

		if (actionLog != null) {
			return actionLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedUserEmailAddress=");
		msg.append(modifiedUserEmailAddress);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActionLogException(msg.toString());
	}

	/**
	 * Returns the first action log in the ordered set where modifiedUserEmailAddress = &#63;.
	 *
	 * @param modifiedUserEmailAddress the modified user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching action log, or <code>null</code> if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog fetchBymodifiedUserEmailAddress_First(
		String modifiedUserEmailAddress, OrderByComparator orderByComparator)
		throws SystemException {
		List<ActionLog> list = findBymodifiedUserEmailAddress(modifiedUserEmailAddress,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last action log in the ordered set where modifiedUserEmailAddress = &#63;.
	 *
	 * @param modifiedUserEmailAddress the modified user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching action log
	 * @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog findBymodifiedUserEmailAddress_Last(
		String modifiedUserEmailAddress, OrderByComparator orderByComparator)
		throws NoSuchActionLogException, SystemException {
		ActionLog actionLog = fetchBymodifiedUserEmailAddress_Last(modifiedUserEmailAddress,
				orderByComparator);

		if (actionLog != null) {
			return actionLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedUserEmailAddress=");
		msg.append(modifiedUserEmailAddress);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActionLogException(msg.toString());
	}

	/**
	 * Returns the last action log in the ordered set where modifiedUserEmailAddress = &#63;.
	 *
	 * @param modifiedUserEmailAddress the modified user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching action log, or <code>null</code> if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog fetchBymodifiedUserEmailAddress_Last(
		String modifiedUserEmailAddress, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countBymodifiedUserEmailAddress(modifiedUserEmailAddress);

		List<ActionLog> list = findBymodifiedUserEmailAddress(modifiedUserEmailAddress,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the action logs before and after the current action log in the ordered set where modifiedUserEmailAddress = &#63;.
	 *
	 * @param id the primary key of the current action log
	 * @param modifiedUserEmailAddress the modified user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next action log
	 * @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog[] findBymodifiedUserEmailAddress_PrevAndNext(long id,
		String modifiedUserEmailAddress, OrderByComparator orderByComparator)
		throws NoSuchActionLogException, SystemException {
		ActionLog actionLog = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			ActionLog[] array = new ActionLogImpl[3];

			array[0] = getBymodifiedUserEmailAddress_PrevAndNext(session,
					actionLog, modifiedUserEmailAddress, orderByComparator, true);

			array[1] = actionLog;

			array[2] = getBymodifiedUserEmailAddress_PrevAndNext(session,
					actionLog, modifiedUserEmailAddress, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ActionLog getBymodifiedUserEmailAddress_PrevAndNext(
		Session session, ActionLog actionLog, String modifiedUserEmailAddress,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACTIONLOG_WHERE);

		if (modifiedUserEmailAddress == null) {
			query.append(_FINDER_COLUMN_MODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_1);
		}
		else {
			if (modifiedUserEmailAddress.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_3);
			}
			else {
				query.append(_FINDER_COLUMN_MODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_2);
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
			query.append(ActionLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (modifiedUserEmailAddress != null) {
			qPos.add(modifiedUserEmailAddress);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(actionLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActionLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the action logs where modifiedFqgn = &#63; and modifiedDate = &#63;.
	 *
	 * @param modifiedFqgn the modified fqgn
	 * @param modifiedDate the modified date
	 * @return the matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findBymodifiedFqgnAndModifiedDate(
		String modifiedFqgn, Date modifiedDate) throws SystemException {
		return findBymodifiedFqgnAndModifiedDate(modifiedFqgn, modifiedDate,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the action logs where modifiedFqgn = &#63; and modifiedDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedFqgn the modified fqgn
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of action logs
	 * @param end the upper bound of the range of action logs (not inclusive)
	 * @return the range of matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findBymodifiedFqgnAndModifiedDate(
		String modifiedFqgn, Date modifiedDate, int start, int end)
		throws SystemException {
		return findBymodifiedFqgnAndModifiedDate(modifiedFqgn, modifiedDate,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the action logs where modifiedFqgn = &#63; and modifiedDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedFqgn the modified fqgn
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of action logs
	 * @param end the upper bound of the range of action logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findBymodifiedFqgnAndModifiedDate(
		String modifiedFqgn, Date modifiedDate, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDFQGNANDMODIFIEDDATE;
			finderArgs = new Object[] { modifiedFqgn, modifiedDate };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MODIFIEDFQGNANDMODIFIEDDATE;
			finderArgs = new Object[] {
					modifiedFqgn, modifiedDate,
					
					start, end, orderByComparator
				};
		}

		List<ActionLog> list = (List<ActionLog>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ActionLog actionLog : list) {
				if (!Validator.equals(modifiedFqgn, actionLog.getModifiedFqgn()) ||
						!Validator.equals(modifiedDate,
							actionLog.getModifiedDate())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ACTIONLOG_WHERE);

			if (modifiedFqgn == null) {
				query.append(_FINDER_COLUMN_MODIFIEDFQGNANDMODIFIEDDATE_MODIFIEDFQGN_1);
			}
			else {
				if (modifiedFqgn.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MODIFIEDFQGNANDMODIFIEDDATE_MODIFIEDFQGN_3);
				}
				else {
					query.append(_FINDER_COLUMN_MODIFIEDFQGNANDMODIFIEDDATE_MODIFIEDFQGN_2);
				}
			}

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_MODIFIEDFQGNANDMODIFIEDDATE_MODIFIEDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_MODIFIEDFQGNANDMODIFIEDDATE_MODIFIEDDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ActionLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedFqgn != null) {
					qPos.add(modifiedFqgn);
				}

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				list = (List<ActionLog>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first action log in the ordered set where modifiedFqgn = &#63; and modifiedDate = &#63;.
	 *
	 * @param modifiedFqgn the modified fqgn
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching action log
	 * @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog findBymodifiedFqgnAndModifiedDate_First(
		String modifiedFqgn, Date modifiedDate,
		OrderByComparator orderByComparator)
		throws NoSuchActionLogException, SystemException {
		ActionLog actionLog = fetchBymodifiedFqgnAndModifiedDate_First(modifiedFqgn,
				modifiedDate, orderByComparator);

		if (actionLog != null) {
			return actionLog;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedFqgn=");
		msg.append(modifiedFqgn);

		msg.append(", modifiedDate=");
		msg.append(modifiedDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActionLogException(msg.toString());
	}

	/**
	 * Returns the first action log in the ordered set where modifiedFqgn = &#63; and modifiedDate = &#63;.
	 *
	 * @param modifiedFqgn the modified fqgn
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching action log, or <code>null</code> if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog fetchBymodifiedFqgnAndModifiedDate_First(
		String modifiedFqgn, Date modifiedDate,
		OrderByComparator orderByComparator) throws SystemException {
		List<ActionLog> list = findBymodifiedFqgnAndModifiedDate(modifiedFqgn,
				modifiedDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last action log in the ordered set where modifiedFqgn = &#63; and modifiedDate = &#63;.
	 *
	 * @param modifiedFqgn the modified fqgn
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching action log
	 * @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog findBymodifiedFqgnAndModifiedDate_Last(
		String modifiedFqgn, Date modifiedDate,
		OrderByComparator orderByComparator)
		throws NoSuchActionLogException, SystemException {
		ActionLog actionLog = fetchBymodifiedFqgnAndModifiedDate_Last(modifiedFqgn,
				modifiedDate, orderByComparator);

		if (actionLog != null) {
			return actionLog;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedFqgn=");
		msg.append(modifiedFqgn);

		msg.append(", modifiedDate=");
		msg.append(modifiedDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActionLogException(msg.toString());
	}

	/**
	 * Returns the last action log in the ordered set where modifiedFqgn = &#63; and modifiedDate = &#63;.
	 *
	 * @param modifiedFqgn the modified fqgn
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching action log, or <code>null</code> if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog fetchBymodifiedFqgnAndModifiedDate_Last(
		String modifiedFqgn, Date modifiedDate,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBymodifiedFqgnAndModifiedDate(modifiedFqgn,
				modifiedDate);

		List<ActionLog> list = findBymodifiedFqgnAndModifiedDate(modifiedFqgn,
				modifiedDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the action logs before and after the current action log in the ordered set where modifiedFqgn = &#63; and modifiedDate = &#63;.
	 *
	 * @param id the primary key of the current action log
	 * @param modifiedFqgn the modified fqgn
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next action log
	 * @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog[] findBymodifiedFqgnAndModifiedDate_PrevAndNext(long id,
		String modifiedFqgn, Date modifiedDate,
		OrderByComparator orderByComparator)
		throws NoSuchActionLogException, SystemException {
		ActionLog actionLog = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			ActionLog[] array = new ActionLogImpl[3];

			array[0] = getBymodifiedFqgnAndModifiedDate_PrevAndNext(session,
					actionLog, modifiedFqgn, modifiedDate, orderByComparator,
					true);

			array[1] = actionLog;

			array[2] = getBymodifiedFqgnAndModifiedDate_PrevAndNext(session,
					actionLog, modifiedFqgn, modifiedDate, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ActionLog getBymodifiedFqgnAndModifiedDate_PrevAndNext(
		Session session, ActionLog actionLog, String modifiedFqgn,
		Date modifiedDate, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACTIONLOG_WHERE);

		if (modifiedFqgn == null) {
			query.append(_FINDER_COLUMN_MODIFIEDFQGNANDMODIFIEDDATE_MODIFIEDFQGN_1);
		}
		else {
			if (modifiedFqgn.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MODIFIEDFQGNANDMODIFIEDDATE_MODIFIEDFQGN_3);
			}
			else {
				query.append(_FINDER_COLUMN_MODIFIEDFQGNANDMODIFIEDDATE_MODIFIEDFQGN_2);
			}
		}

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_MODIFIEDFQGNANDMODIFIEDDATE_MODIFIEDDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_MODIFIEDFQGNANDMODIFIEDDATE_MODIFIEDDATE_2);
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
			query.append(ActionLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (modifiedFqgn != null) {
			qPos.add(modifiedFqgn);
		}

		if (modifiedDate != null) {
			qPos.add(CalendarUtil.getTimestamp(modifiedDate));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(actionLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActionLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the action logs where userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	 *
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @return the matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findByuserEmailAddressAndModifiedUserEmailAddress(
		String userEmailAddress, String modifiedUserEmailAddress)
		throws SystemException {
		return findByuserEmailAddressAndModifiedUserEmailAddress(userEmailAddress,
			modifiedUserEmailAddress, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the action logs where userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @param start the lower bound of the range of action logs
	 * @param end the upper bound of the range of action logs (not inclusive)
	 * @return the range of matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findByuserEmailAddressAndModifiedUserEmailAddress(
		String userEmailAddress, String modifiedUserEmailAddress, int start,
		int end) throws SystemException {
		return findByuserEmailAddressAndModifiedUserEmailAddress(userEmailAddress,
			modifiedUserEmailAddress, start, end, null);
	}

	/**
	 * Returns an ordered range of all the action logs where userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @param start the lower bound of the range of action logs
	 * @param end the upper bound of the range of action logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findByuserEmailAddressAndModifiedUserEmailAddress(
		String userEmailAddress, String modifiedUserEmailAddress, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS;
			finderArgs = new Object[] { userEmailAddress, modifiedUserEmailAddress };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS;
			finderArgs = new Object[] {
					userEmailAddress, modifiedUserEmailAddress,
					
					start, end, orderByComparator
				};
		}

		List<ActionLog> list = (List<ActionLog>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ActionLog actionLog : list) {
				if (!Validator.equals(userEmailAddress,
							actionLog.getUserEmailAddress()) ||
						!Validator.equals(modifiedUserEmailAddress,
							actionLog.getModifiedUserEmailAddress())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ACTIONLOG_WHERE);

			if (userEmailAddress == null) {
				query.append(_FINDER_COLUMN_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_1);
			}
			else {
				if (userEmailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_2);
				}
			}

			if (modifiedUserEmailAddress == null) {
				query.append(_FINDER_COLUMN_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_1);
			}
			else {
				if (modifiedUserEmailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ActionLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (userEmailAddress != null) {
					qPos.add(userEmailAddress);
				}

				if (modifiedUserEmailAddress != null) {
					qPos.add(modifiedUserEmailAddress);
				}

				list = (List<ActionLog>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first action log in the ordered set where userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	 *
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching action log
	 * @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog findByuserEmailAddressAndModifiedUserEmailAddress_First(
		String userEmailAddress, String modifiedUserEmailAddress,
		OrderByComparator orderByComparator)
		throws NoSuchActionLogException, SystemException {
		ActionLog actionLog = fetchByuserEmailAddressAndModifiedUserEmailAddress_First(userEmailAddress,
				modifiedUserEmailAddress, orderByComparator);

		if (actionLog != null) {
			return actionLog;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userEmailAddress=");
		msg.append(userEmailAddress);

		msg.append(", modifiedUserEmailAddress=");
		msg.append(modifiedUserEmailAddress);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActionLogException(msg.toString());
	}

	/**
	 * Returns the first action log in the ordered set where userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	 *
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching action log, or <code>null</code> if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog fetchByuserEmailAddressAndModifiedUserEmailAddress_First(
		String userEmailAddress, String modifiedUserEmailAddress,
		OrderByComparator orderByComparator) throws SystemException {
		List<ActionLog> list = findByuserEmailAddressAndModifiedUserEmailAddress(userEmailAddress,
				modifiedUserEmailAddress, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last action log in the ordered set where userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	 *
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching action log
	 * @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog findByuserEmailAddressAndModifiedUserEmailAddress_Last(
		String userEmailAddress, String modifiedUserEmailAddress,
		OrderByComparator orderByComparator)
		throws NoSuchActionLogException, SystemException {
		ActionLog actionLog = fetchByuserEmailAddressAndModifiedUserEmailAddress_Last(userEmailAddress,
				modifiedUserEmailAddress, orderByComparator);

		if (actionLog != null) {
			return actionLog;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userEmailAddress=");
		msg.append(userEmailAddress);

		msg.append(", modifiedUserEmailAddress=");
		msg.append(modifiedUserEmailAddress);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActionLogException(msg.toString());
	}

	/**
	 * Returns the last action log in the ordered set where userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	 *
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching action log, or <code>null</code> if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog fetchByuserEmailAddressAndModifiedUserEmailAddress_Last(
		String userEmailAddress, String modifiedUserEmailAddress,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByuserEmailAddressAndModifiedUserEmailAddress(userEmailAddress,
				modifiedUserEmailAddress);

		List<ActionLog> list = findByuserEmailAddressAndModifiedUserEmailAddress(userEmailAddress,
				modifiedUserEmailAddress, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the action logs before and after the current action log in the ordered set where userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	 *
	 * @param id the primary key of the current action log
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next action log
	 * @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog[] findByuserEmailAddressAndModifiedUserEmailAddress_PrevAndNext(
		long id, String userEmailAddress, String modifiedUserEmailAddress,
		OrderByComparator orderByComparator)
		throws NoSuchActionLogException, SystemException {
		ActionLog actionLog = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			ActionLog[] array = new ActionLogImpl[3];

			array[0] = getByuserEmailAddressAndModifiedUserEmailAddress_PrevAndNext(session,
					actionLog, userEmailAddress, modifiedUserEmailAddress,
					orderByComparator, true);

			array[1] = actionLog;

			array[2] = getByuserEmailAddressAndModifiedUserEmailAddress_PrevAndNext(session,
					actionLog, userEmailAddress, modifiedUserEmailAddress,
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

	protected ActionLog getByuserEmailAddressAndModifiedUserEmailAddress_PrevAndNext(
		Session session, ActionLog actionLog, String userEmailAddress,
		String modifiedUserEmailAddress, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACTIONLOG_WHERE);

		if (userEmailAddress == null) {
			query.append(_FINDER_COLUMN_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_1);
		}
		else {
			if (userEmailAddress.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_3);
			}
			else {
				query.append(_FINDER_COLUMN_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_2);
			}
		}

		if (modifiedUserEmailAddress == null) {
			query.append(_FINDER_COLUMN_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_1);
		}
		else {
			if (modifiedUserEmailAddress.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_3);
			}
			else {
				query.append(_FINDER_COLUMN_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_2);
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
			query.append(ActionLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (userEmailAddress != null) {
			qPos.add(userEmailAddress);
		}

		if (modifiedUserEmailAddress != null) {
			qPos.add(modifiedUserEmailAddress);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(actionLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActionLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the action logs where modifiedDate = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @return the matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress(
		Date modifiedDate, String userEmailAddress,
		String modifiedUserEmailAddress) throws SystemException {
		return findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress(modifiedDate,
			userEmailAddress, modifiedUserEmailAddress, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the action logs where modifiedDate = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @param start the lower bound of the range of action logs
	 * @param end the upper bound of the range of action logs (not inclusive)
	 * @return the range of matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress(
		Date modifiedDate, String userEmailAddress,
		String modifiedUserEmailAddress, int start, int end)
		throws SystemException {
		return findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress(modifiedDate,
			userEmailAddress, modifiedUserEmailAddress, start, end, null);
	}

	/**
	 * Returns an ordered range of all the action logs where modifiedDate = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @param start the lower bound of the range of action logs
	 * @param end the upper bound of the range of action logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress(
		Date modifiedDate, String userEmailAddress,
		String modifiedUserEmailAddress, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS;
			finderArgs = new Object[] {
					modifiedDate, userEmailAddress, modifiedUserEmailAddress
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS;
			finderArgs = new Object[] {
					modifiedDate, userEmailAddress, modifiedUserEmailAddress,
					
					start, end, orderByComparator
				};
		}

		List<ActionLog> list = (List<ActionLog>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ActionLog actionLog : list) {
				if (!Validator.equals(modifiedDate, actionLog.getModifiedDate()) ||
						!Validator.equals(userEmailAddress,
							actionLog.getUserEmailAddress()) ||
						!Validator.equals(modifiedUserEmailAddress,
							actionLog.getModifiedUserEmailAddress())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ACTIONLOG_WHERE);

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDDATE_2);
			}

			if (userEmailAddress == null) {
				query.append(_FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_1);
			}
			else {
				if (userEmailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_2);
				}
			}

			if (modifiedUserEmailAddress == null) {
				query.append(_FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_1);
			}
			else {
				if (modifiedUserEmailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ActionLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				if (userEmailAddress != null) {
					qPos.add(userEmailAddress);
				}

				if (modifiedUserEmailAddress != null) {
					qPos.add(modifiedUserEmailAddress);
				}

				list = (List<ActionLog>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first action log in the ordered set where modifiedDate = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching action log
	 * @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress_First(
		Date modifiedDate, String userEmailAddress,
		String modifiedUserEmailAddress, OrderByComparator orderByComparator)
		throws NoSuchActionLogException, SystemException {
		ActionLog actionLog = fetchBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress_First(modifiedDate,
				userEmailAddress, modifiedUserEmailAddress, orderByComparator);

		if (actionLog != null) {
			return actionLog;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", userEmailAddress=");
		msg.append(userEmailAddress);

		msg.append(", modifiedUserEmailAddress=");
		msg.append(modifiedUserEmailAddress);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActionLogException(msg.toString());
	}

	/**
	 * Returns the first action log in the ordered set where modifiedDate = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching action log, or <code>null</code> if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog fetchBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress_First(
		Date modifiedDate, String userEmailAddress,
		String modifiedUserEmailAddress, OrderByComparator orderByComparator)
		throws SystemException {
		List<ActionLog> list = findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress(modifiedDate,
				userEmailAddress, modifiedUserEmailAddress, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last action log in the ordered set where modifiedDate = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching action log
	 * @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress_Last(
		Date modifiedDate, String userEmailAddress,
		String modifiedUserEmailAddress, OrderByComparator orderByComparator)
		throws NoSuchActionLogException, SystemException {
		ActionLog actionLog = fetchBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress_Last(modifiedDate,
				userEmailAddress, modifiedUserEmailAddress, orderByComparator);

		if (actionLog != null) {
			return actionLog;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", userEmailAddress=");
		msg.append(userEmailAddress);

		msg.append(", modifiedUserEmailAddress=");
		msg.append(modifiedUserEmailAddress);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActionLogException(msg.toString());
	}

	/**
	 * Returns the last action log in the ordered set where modifiedDate = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching action log, or <code>null</code> if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog fetchBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress_Last(
		Date modifiedDate, String userEmailAddress,
		String modifiedUserEmailAddress, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress(modifiedDate,
				userEmailAddress, modifiedUserEmailAddress);

		List<ActionLog> list = findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress(modifiedDate,
				userEmailAddress, modifiedUserEmailAddress, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the action logs before and after the current action log in the ordered set where modifiedDate = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	 *
	 * @param id the primary key of the current action log
	 * @param modifiedDate the modified date
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next action log
	 * @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog[] findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress_PrevAndNext(
		long id, Date modifiedDate, String userEmailAddress,
		String modifiedUserEmailAddress, OrderByComparator orderByComparator)
		throws NoSuchActionLogException, SystemException {
		ActionLog actionLog = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			ActionLog[] array = new ActionLogImpl[3];

			array[0] = getBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress_PrevAndNext(session,
					actionLog, modifiedDate, userEmailAddress,
					modifiedUserEmailAddress, orderByComparator, true);

			array[1] = actionLog;

			array[2] = getBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress_PrevAndNext(session,
					actionLog, modifiedDate, userEmailAddress,
					modifiedUserEmailAddress, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ActionLog getBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress_PrevAndNext(
		Session session, ActionLog actionLog, Date modifiedDate,
		String userEmailAddress, String modifiedUserEmailAddress,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACTIONLOG_WHERE);

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDDATE_2);
		}

		if (userEmailAddress == null) {
			query.append(_FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_1);
		}
		else {
			if (userEmailAddress.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_3);
			}
			else {
				query.append(_FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_2);
			}
		}

		if (modifiedUserEmailAddress == null) {
			query.append(_FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_1);
		}
		else {
			if (modifiedUserEmailAddress.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_3);
			}
			else {
				query.append(_FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_2);
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
			query.append(ActionLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (modifiedDate != null) {
			qPos.add(CalendarUtil.getTimestamp(modifiedDate));
		}

		if (userEmailAddress != null) {
			qPos.add(userEmailAddress);
		}

		if (modifiedUserEmailAddress != null) {
			qPos.add(modifiedUserEmailAddress);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(actionLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActionLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the action logs where modifiedDescription = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	 *
	 * @param modifiedDescription the modified description
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @return the matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress(
		String modifiedDescription, String userEmailAddress,
		String modifiedUserEmailAddress) throws SystemException {
		return findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress(modifiedDescription,
			userEmailAddress, modifiedUserEmailAddress, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the action logs where modifiedDescription = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDescription the modified description
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @param start the lower bound of the range of action logs
	 * @param end the upper bound of the range of action logs (not inclusive)
	 * @return the range of matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress(
		String modifiedDescription, String userEmailAddress,
		String modifiedUserEmailAddress, int start, int end)
		throws SystemException {
		return findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress(modifiedDescription,
			userEmailAddress, modifiedUserEmailAddress, start, end, null);
	}

	/**
	 * Returns an ordered range of all the action logs where modifiedDescription = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDescription the modified description
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @param start the lower bound of the range of action logs
	 * @param end the upper bound of the range of action logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress(
		String modifiedDescription, String userEmailAddress,
		String modifiedUserEmailAddress, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS;
			finderArgs = new Object[] {
					modifiedDescription, userEmailAddress,
					modifiedUserEmailAddress
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS;
			finderArgs = new Object[] {
					modifiedDescription, userEmailAddress,
					modifiedUserEmailAddress,
					
					start, end, orderByComparator
				};
		}

		List<ActionLog> list = (List<ActionLog>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ActionLog actionLog : list) {
				if (!Validator.equals(modifiedDescription,
							actionLog.getModifiedDescription()) ||
						!Validator.equals(userEmailAddress,
							actionLog.getUserEmailAddress()) ||
						!Validator.equals(modifiedUserEmailAddress,
							actionLog.getModifiedUserEmailAddress())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ACTIONLOG_WHERE);

			if (modifiedDescription == null) {
				query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDDESCRIPTION_1);
			}
			else {
				if (modifiedDescription.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDDESCRIPTION_3);
				}
				else {
					query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDDESCRIPTION_2);
				}
			}

			if (userEmailAddress == null) {
				query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_1);
			}
			else {
				if (userEmailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_2);
				}
			}

			if (modifiedUserEmailAddress == null) {
				query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_1);
			}
			else {
				if (modifiedUserEmailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ActionLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedDescription != null) {
					qPos.add(modifiedDescription);
				}

				if (userEmailAddress != null) {
					qPos.add(userEmailAddress);
				}

				if (modifiedUserEmailAddress != null) {
					qPos.add(modifiedUserEmailAddress);
				}

				list = (List<ActionLog>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first action log in the ordered set where modifiedDescription = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	 *
	 * @param modifiedDescription the modified description
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching action log
	 * @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress_First(
		String modifiedDescription, String userEmailAddress,
		String modifiedUserEmailAddress, OrderByComparator orderByComparator)
		throws NoSuchActionLogException, SystemException {
		ActionLog actionLog = fetchBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress_First(modifiedDescription,
				userEmailAddress, modifiedUserEmailAddress, orderByComparator);

		if (actionLog != null) {
			return actionLog;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDescription=");
		msg.append(modifiedDescription);

		msg.append(", userEmailAddress=");
		msg.append(userEmailAddress);

		msg.append(", modifiedUserEmailAddress=");
		msg.append(modifiedUserEmailAddress);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActionLogException(msg.toString());
	}

	/**
	 * Returns the first action log in the ordered set where modifiedDescription = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	 *
	 * @param modifiedDescription the modified description
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching action log, or <code>null</code> if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog fetchBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress_First(
		String modifiedDescription, String userEmailAddress,
		String modifiedUserEmailAddress, OrderByComparator orderByComparator)
		throws SystemException {
		List<ActionLog> list = findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress(modifiedDescription,
				userEmailAddress, modifiedUserEmailAddress, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last action log in the ordered set where modifiedDescription = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	 *
	 * @param modifiedDescription the modified description
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching action log
	 * @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress_Last(
		String modifiedDescription, String userEmailAddress,
		String modifiedUserEmailAddress, OrderByComparator orderByComparator)
		throws NoSuchActionLogException, SystemException {
		ActionLog actionLog = fetchBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress_Last(modifiedDescription,
				userEmailAddress, modifiedUserEmailAddress, orderByComparator);

		if (actionLog != null) {
			return actionLog;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDescription=");
		msg.append(modifiedDescription);

		msg.append(", userEmailAddress=");
		msg.append(userEmailAddress);

		msg.append(", modifiedUserEmailAddress=");
		msg.append(modifiedUserEmailAddress);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActionLogException(msg.toString());
	}

	/**
	 * Returns the last action log in the ordered set where modifiedDescription = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	 *
	 * @param modifiedDescription the modified description
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching action log, or <code>null</code> if a matching action log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog fetchBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress_Last(
		String modifiedDescription, String userEmailAddress,
		String modifiedUserEmailAddress, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress(modifiedDescription,
				userEmailAddress, modifiedUserEmailAddress);

		List<ActionLog> list = findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress(modifiedDescription,
				userEmailAddress, modifiedUserEmailAddress, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the action logs before and after the current action log in the ordered set where modifiedDescription = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	 *
	 * @param id the primary key of the current action log
	 * @param modifiedDescription the modified description
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next action log
	 * @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ActionLog[] findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress_PrevAndNext(
		long id, String modifiedDescription, String userEmailAddress,
		String modifiedUserEmailAddress, OrderByComparator orderByComparator)
		throws NoSuchActionLogException, SystemException {
		ActionLog actionLog = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			ActionLog[] array = new ActionLogImpl[3];

			array[0] = getBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress_PrevAndNext(session,
					actionLog, modifiedDescription, userEmailAddress,
					modifiedUserEmailAddress, orderByComparator, true);

			array[1] = actionLog;

			array[2] = getBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress_PrevAndNext(session,
					actionLog, modifiedDescription, userEmailAddress,
					modifiedUserEmailAddress, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ActionLog getBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress_PrevAndNext(
		Session session, ActionLog actionLog, String modifiedDescription,
		String userEmailAddress, String modifiedUserEmailAddress,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACTIONLOG_WHERE);

		if (modifiedDescription == null) {
			query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDDESCRIPTION_1);
		}
		else {
			if (modifiedDescription.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDDESCRIPTION_3);
			}
			else {
				query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDDESCRIPTION_2);
			}
		}

		if (userEmailAddress == null) {
			query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_1);
		}
		else {
			if (userEmailAddress.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_3);
			}
			else {
				query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_2);
			}
		}

		if (modifiedUserEmailAddress == null) {
			query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_1);
		}
		else {
			if (modifiedUserEmailAddress.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_3);
			}
			else {
				query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_2);
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
			query.append(ActionLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (modifiedDescription != null) {
			qPos.add(modifiedDescription);
		}

		if (userEmailAddress != null) {
			qPos.add(userEmailAddress);
		}

		if (modifiedUserEmailAddress != null) {
			qPos.add(modifiedUserEmailAddress);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(actionLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActionLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the action logs.
	 *
	 * @return the action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the action logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of action logs
	 * @param end the upper bound of the range of action logs (not inclusive)
	 * @return the range of action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the action logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of action logs
	 * @param end the upper bound of the range of action logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of action logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ActionLog> findAll(int start, int end,
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

		List<ActionLog> list = (List<ActionLog>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ACTIONLOG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACTIONLOG.concat(ActionLogModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<ActionLog>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<ActionLog>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the action logs where userEmailAddress = &#63; from the database.
	 *
	 * @param userEmailAddress the user email address
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByuserEmailAddress(String userEmailAddress)
		throws SystemException {
		for (ActionLog actionLog : findByuserEmailAddress(userEmailAddress)) {
			remove(actionLog);
		}
	}

	/**
	 * Removes all the action logs where modifiedDate = &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBymodifiedDate(Date modifiedDate)
		throws SystemException {
		for (ActionLog actionLog : findBymodifiedDate(modifiedDate)) {
			remove(actionLog);
		}
	}

	/**
	 * Removes all the action logs where modifiedDescription = &#63; from the database.
	 *
	 * @param modifiedDescription the modified description
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBymodifiedDescription(String modifiedDescription)
		throws SystemException {
		for (ActionLog actionLog : findBymodifiedDescription(
				modifiedDescription)) {
			remove(actionLog);
		}
	}

	/**
	 * Removes all the action logs where modifiedFqgn = &#63; from the database.
	 *
	 * @param modifiedFqgn the modified fqgn
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBymodifiedFqgn(String modifiedFqgn)
		throws SystemException {
		for (ActionLog actionLog : findBymodifiedFqgn(modifiedFqgn)) {
			remove(actionLog);
		}
	}

	/**
	 * Removes all the action logs where modifiedUserEmailAddress = &#63; from the database.
	 *
	 * @param modifiedUserEmailAddress the modified user email address
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBymodifiedUserEmailAddress(
		String modifiedUserEmailAddress) throws SystemException {
		for (ActionLog actionLog : findBymodifiedUserEmailAddress(
				modifiedUserEmailAddress)) {
			remove(actionLog);
		}
	}

	/**
	 * Removes all the action logs where modifiedFqgn = &#63; and modifiedDate = &#63; from the database.
	 *
	 * @param modifiedFqgn the modified fqgn
	 * @param modifiedDate the modified date
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBymodifiedFqgnAndModifiedDate(String modifiedFqgn,
		Date modifiedDate) throws SystemException {
		for (ActionLog actionLog : findBymodifiedFqgnAndModifiedDate(
				modifiedFqgn, modifiedDate)) {
			remove(actionLog);
		}
	}

	/**
	 * Removes all the action logs where userEmailAddress = &#63; and modifiedUserEmailAddress = &#63; from the database.
	 *
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByuserEmailAddressAndModifiedUserEmailAddress(
		String userEmailAddress, String modifiedUserEmailAddress)
		throws SystemException {
		for (ActionLog actionLog : findByuserEmailAddressAndModifiedUserEmailAddress(
				userEmailAddress, modifiedUserEmailAddress)) {
			remove(actionLog);
		}
	}

	/**
	 * Removes all the action logs where modifiedDate = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress(
		Date modifiedDate, String userEmailAddress,
		String modifiedUserEmailAddress) throws SystemException {
		for (ActionLog actionLog : findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress(
				modifiedDate, userEmailAddress, modifiedUserEmailAddress)) {
			remove(actionLog);
		}
	}

	/**
	 * Removes all the action logs where modifiedDescription = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63; from the database.
	 *
	 * @param modifiedDescription the modified description
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress(
		String modifiedDescription, String userEmailAddress,
		String modifiedUserEmailAddress) throws SystemException {
		for (ActionLog actionLog : findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress(
				modifiedDescription, userEmailAddress, modifiedUserEmailAddress)) {
			remove(actionLog);
		}
	}

	/**
	 * Removes all the action logs from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (ActionLog actionLog : findAll()) {
			remove(actionLog);
		}
	}

	/**
	 * Returns the number of action logs where userEmailAddress = &#63;.
	 *
	 * @param userEmailAddress the user email address
	 * @return the number of matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByuserEmailAddress(String userEmailAddress)
		throws SystemException {
		Object[] finderArgs = new Object[] { userEmailAddress };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USEREMAILADDRESS,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACTIONLOG_WHERE);

			if (userEmailAddress == null) {
				query.append(_FINDER_COLUMN_USEREMAILADDRESS_USEREMAILADDRESS_1);
			}
			else {
				if (userEmailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_USEREMAILADDRESS_USEREMAILADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_USEREMAILADDRESS_USEREMAILADDRESS_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (userEmailAddress != null) {
					qPos.add(userEmailAddress);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USEREMAILADDRESS,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of action logs where modifiedDate = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @return the number of matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countBymodifiedDate(Date modifiedDate) throws SystemException {
		Object[] finderArgs = new Object[] { modifiedDate };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MODIFIEDDATE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACTIONLOG_WHERE);

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_MODIFIEDDATE_MODIFIEDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_MODIFIEDDATE_MODIFIEDDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MODIFIEDDATE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of action logs where modifiedDescription = &#63;.
	 *
	 * @param modifiedDescription the modified description
	 * @return the number of matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countBymodifiedDescription(String modifiedDescription)
		throws SystemException {
		Object[] finderArgs = new Object[] { modifiedDescription };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MODIFIEDDESCRIPTION,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACTIONLOG_WHERE);

			if (modifiedDescription == null) {
				query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTION_MODIFIEDDESCRIPTION_1);
			}
			else {
				if (modifiedDescription.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTION_MODIFIEDDESCRIPTION_3);
				}
				else {
					query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTION_MODIFIEDDESCRIPTION_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedDescription != null) {
					qPos.add(modifiedDescription);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MODIFIEDDESCRIPTION,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of action logs where modifiedFqgn = &#63;.
	 *
	 * @param modifiedFqgn the modified fqgn
	 * @return the number of matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countBymodifiedFqgn(String modifiedFqgn)
		throws SystemException {
		Object[] finderArgs = new Object[] { modifiedFqgn };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MODIFIEDFQGN,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACTIONLOG_WHERE);

			if (modifiedFqgn == null) {
				query.append(_FINDER_COLUMN_MODIFIEDFQGN_MODIFIEDFQGN_1);
			}
			else {
				if (modifiedFqgn.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MODIFIEDFQGN_MODIFIEDFQGN_3);
				}
				else {
					query.append(_FINDER_COLUMN_MODIFIEDFQGN_MODIFIEDFQGN_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedFqgn != null) {
					qPos.add(modifiedFqgn);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MODIFIEDFQGN,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of action logs where modifiedUserEmailAddress = &#63;.
	 *
	 * @param modifiedUserEmailAddress the modified user email address
	 * @return the number of matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countBymodifiedUserEmailAddress(String modifiedUserEmailAddress)
		throws SystemException {
		Object[] finderArgs = new Object[] { modifiedUserEmailAddress };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MODIFIEDUSEREMAILADDRESS,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACTIONLOG_WHERE);

			if (modifiedUserEmailAddress == null) {
				query.append(_FINDER_COLUMN_MODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_1);
			}
			else {
				if (modifiedUserEmailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_MODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedUserEmailAddress != null) {
					qPos.add(modifiedUserEmailAddress);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MODIFIEDUSEREMAILADDRESS,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of action logs where modifiedFqgn = &#63; and modifiedDate = &#63;.
	 *
	 * @param modifiedFqgn the modified fqgn
	 * @param modifiedDate the modified date
	 * @return the number of matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countBymodifiedFqgnAndModifiedDate(String modifiedFqgn,
		Date modifiedDate) throws SystemException {
		Object[] finderArgs = new Object[] { modifiedFqgn, modifiedDate };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MODIFIEDFQGNANDMODIFIEDDATE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACTIONLOG_WHERE);

			if (modifiedFqgn == null) {
				query.append(_FINDER_COLUMN_MODIFIEDFQGNANDMODIFIEDDATE_MODIFIEDFQGN_1);
			}
			else {
				if (modifiedFqgn.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MODIFIEDFQGNANDMODIFIEDDATE_MODIFIEDFQGN_3);
				}
				else {
					query.append(_FINDER_COLUMN_MODIFIEDFQGNANDMODIFIEDDATE_MODIFIEDFQGN_2);
				}
			}

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_MODIFIEDFQGNANDMODIFIEDDATE_MODIFIEDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_MODIFIEDFQGNANDMODIFIEDDATE_MODIFIEDDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedFqgn != null) {
					qPos.add(modifiedFqgn);
				}

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MODIFIEDFQGNANDMODIFIEDDATE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of action logs where userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	 *
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @return the number of matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByuserEmailAddressAndModifiedUserEmailAddress(
		String userEmailAddress, String modifiedUserEmailAddress)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				userEmailAddress, modifiedUserEmailAddress
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACTIONLOG_WHERE);

			if (userEmailAddress == null) {
				query.append(_FINDER_COLUMN_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_1);
			}
			else {
				if (userEmailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_2);
				}
			}

			if (modifiedUserEmailAddress == null) {
				query.append(_FINDER_COLUMN_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_1);
			}
			else {
				if (modifiedUserEmailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (userEmailAddress != null) {
					qPos.add(userEmailAddress);
				}

				if (modifiedUserEmailAddress != null) {
					qPos.add(modifiedUserEmailAddress);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of action logs where modifiedDate = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @return the number of matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress(
		Date modifiedDate, String userEmailAddress,
		String modifiedUserEmailAddress) throws SystemException {
		Object[] finderArgs = new Object[] {
				modifiedDate, userEmailAddress, modifiedUserEmailAddress
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ACTIONLOG_WHERE);

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDDATE_2);
			}

			if (userEmailAddress == null) {
				query.append(_FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_1);
			}
			else {
				if (userEmailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_2);
				}
			}

			if (modifiedUserEmailAddress == null) {
				query.append(_FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_1);
			}
			else {
				if (modifiedUserEmailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				if (userEmailAddress != null) {
					qPos.add(userEmailAddress);
				}

				if (modifiedUserEmailAddress != null) {
					qPos.add(modifiedUserEmailAddress);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of action logs where modifiedDescription = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	 *
	 * @param modifiedDescription the modified description
	 * @param userEmailAddress the user email address
	 * @param modifiedUserEmailAddress the modified user email address
	 * @return the number of matching action logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress(
		String modifiedDescription, String userEmailAddress,
		String modifiedUserEmailAddress) throws SystemException {
		Object[] finderArgs = new Object[] {
				modifiedDescription, userEmailAddress, modifiedUserEmailAddress
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ACTIONLOG_WHERE);

			if (modifiedDescription == null) {
				query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDDESCRIPTION_1);
			}
			else {
				if (modifiedDescription.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDDESCRIPTION_3);
				}
				else {
					query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDDESCRIPTION_2);
				}
			}

			if (userEmailAddress == null) {
				query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_1);
			}
			else {
				if (userEmailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_2);
				}
			}

			if (modifiedUserEmailAddress == null) {
				query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_1);
			}
			else {
				if (modifiedUserEmailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedDescription != null) {
					qPos.add(modifiedDescription);
				}

				if (userEmailAddress != null) {
					qPos.add(userEmailAddress);
				}

				if (modifiedUserEmailAddress != null) {
					qPos.add(modifiedUserEmailAddress);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of action logs.
	 *
	 * @return the number of action logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACTIONLOG);

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
	 * Initializes the action log persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.gnenc.yams.model.ActionLog")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ActionLog>> listenersList = new ArrayList<ModelListener<ActionLog>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ActionLog>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ActionLogImpl.class.getName());
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
	private static final String _SQL_SELECT_ACTIONLOG = "SELECT actionLog FROM ActionLog actionLog";
	private static final String _SQL_SELECT_ACTIONLOG_WHERE = "SELECT actionLog FROM ActionLog actionLog WHERE ";
	private static final String _SQL_COUNT_ACTIONLOG = "SELECT COUNT(actionLog) FROM ActionLog actionLog";
	private static final String _SQL_COUNT_ACTIONLOG_WHERE = "SELECT COUNT(actionLog) FROM ActionLog actionLog WHERE ";
	private static final String _FINDER_COLUMN_USEREMAILADDRESS_USEREMAILADDRESS_1 =
		"actionLog.userEmailAddress IS NULL";
	private static final String _FINDER_COLUMN_USEREMAILADDRESS_USEREMAILADDRESS_2 =
		"actionLog.userEmailAddress = ?";
	private static final String _FINDER_COLUMN_USEREMAILADDRESS_USEREMAILADDRESS_3 =
		"(actionLog.userEmailAddress IS NULL OR actionLog.userEmailAddress = ?)";
	private static final String _FINDER_COLUMN_MODIFIEDDATE_MODIFIEDDATE_1 = "actionLog.modifiedDate IS NULL";
	private static final String _FINDER_COLUMN_MODIFIEDDATE_MODIFIEDDATE_2 = "actionLog.modifiedDate = ?";
	private static final String _FINDER_COLUMN_MODIFIEDDESCRIPTION_MODIFIEDDESCRIPTION_1 =
		"actionLog.modifiedDescription IS NULL";
	private static final String _FINDER_COLUMN_MODIFIEDDESCRIPTION_MODIFIEDDESCRIPTION_2 =
		"actionLog.modifiedDescription = ?";
	private static final String _FINDER_COLUMN_MODIFIEDDESCRIPTION_MODIFIEDDESCRIPTION_3 =
		"(actionLog.modifiedDescription IS NULL OR actionLog.modifiedDescription = ?)";
	private static final String _FINDER_COLUMN_MODIFIEDFQGN_MODIFIEDFQGN_1 = "actionLog.modifiedFqgn IS NULL";
	private static final String _FINDER_COLUMN_MODIFIEDFQGN_MODIFIEDFQGN_2 = "actionLog.modifiedFqgn = ?";
	private static final String _FINDER_COLUMN_MODIFIEDFQGN_MODIFIEDFQGN_3 = "(actionLog.modifiedFqgn IS NULL OR actionLog.modifiedFqgn = ?)";
	private static final String _FINDER_COLUMN_MODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_1 =
		"actionLog.modifiedUserEmailAddress IS NULL";
	private static final String _FINDER_COLUMN_MODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_2 =
		"actionLog.modifiedUserEmailAddress = ?";
	private static final String _FINDER_COLUMN_MODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_3 =
		"(actionLog.modifiedUserEmailAddress IS NULL OR actionLog.modifiedUserEmailAddress = ?)";
	private static final String _FINDER_COLUMN_MODIFIEDFQGNANDMODIFIEDDATE_MODIFIEDFQGN_1 =
		"actionLog.modifiedFqgn IS NULL AND ";
	private static final String _FINDER_COLUMN_MODIFIEDFQGNANDMODIFIEDDATE_MODIFIEDFQGN_2 =
		"actionLog.modifiedFqgn = ? AND ";
	private static final String _FINDER_COLUMN_MODIFIEDFQGNANDMODIFIEDDATE_MODIFIEDFQGN_3 =
		"(actionLog.modifiedFqgn IS NULL OR actionLog.modifiedFqgn = ?) AND ";
	private static final String _FINDER_COLUMN_MODIFIEDFQGNANDMODIFIEDDATE_MODIFIEDDATE_1 =
		"actionLog.modifiedDate IS NULL";
	private static final String _FINDER_COLUMN_MODIFIEDFQGNANDMODIFIEDDATE_MODIFIEDDATE_2 =
		"actionLog.modifiedDate = ?";
	private static final String _FINDER_COLUMN_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_1 =
		"actionLog.userEmailAddress IS NULL AND ";
	private static final String _FINDER_COLUMN_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_2 =
		"actionLog.userEmailAddress = ? AND ";
	private static final String _FINDER_COLUMN_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_3 =
		"(actionLog.userEmailAddress IS NULL OR actionLog.userEmailAddress = ?) AND ";
	private static final String _FINDER_COLUMN_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_1 =
		"actionLog.modifiedUserEmailAddress IS NULL";
	private static final String _FINDER_COLUMN_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_2 =
		"actionLog.modifiedUserEmailAddress = ?";
	private static final String _FINDER_COLUMN_USEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_3 =
		"(actionLog.modifiedUserEmailAddress IS NULL OR actionLog.modifiedUserEmailAddress = ?)";
	private static final String _FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDDATE_1 =
		"actionLog.modifiedDate IS NULL AND ";
	private static final String _FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDDATE_2 =
		"actionLog.modifiedDate = ? AND ";
	private static final String _FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_1 =
		"actionLog.userEmailAddress IS NULL AND ";
	private static final String _FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_2 =
		"actionLog.userEmailAddress = ? AND ";
	private static final String _FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_3 =
		"(actionLog.userEmailAddress IS NULL OR actionLog.userEmailAddress = ?) AND ";
	private static final String _FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_1 =
		"actionLog.modifiedUserEmailAddress IS NULL";
	private static final String _FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_2 =
		"actionLog.modifiedUserEmailAddress = ?";
	private static final String _FINDER_COLUMN_MODIFIEDDATEANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_3 =
		"(actionLog.modifiedUserEmailAddress IS NULL OR actionLog.modifiedUserEmailAddress = ?)";
	private static final String _FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDDESCRIPTION_1 =
		"actionLog.modifiedDescription IS NULL AND ";
	private static final String _FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDDESCRIPTION_2 =
		"actionLog.modifiedDescription = ? AND ";
	private static final String _FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDDESCRIPTION_3 =
		"(actionLog.modifiedDescription IS NULL OR actionLog.modifiedDescription = ?) AND ";
	private static final String _FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_1 =
		"actionLog.userEmailAddress IS NULL AND ";
	private static final String _FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_2 =
		"actionLog.userEmailAddress = ? AND ";
	private static final String _FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_USEREMAILADDRESS_3 =
		"(actionLog.userEmailAddress IS NULL OR actionLog.userEmailAddress = ?) AND ";
	private static final String _FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_1 =
		"actionLog.modifiedUserEmailAddress IS NULL";
	private static final String _FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_2 =
		"actionLog.modifiedUserEmailAddress = ?";
	private static final String _FINDER_COLUMN_MODIFIEDDESCRIPTIONANDUSEREMAILADDRESSANDMODIFIEDUSEREMAILADDRESS_MODIFIEDUSEREMAILADDRESS_3 =
		"(actionLog.modifiedUserEmailAddress IS NULL OR actionLog.modifiedUserEmailAddress = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "actionLog.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ActionLog exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ActionLog exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ActionLogPersistenceImpl.class);
	private static ActionLog _nullActionLog = new ActionLogImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ActionLog> toCacheModel() {
				return _nullActionLogCacheModel;
			}
		};

	private static CacheModel<ActionLog> _nullActionLogCacheModel = new CacheModel<ActionLog>() {
			public ActionLog toEntityModel() {
				return _nullActionLog;
			}
		};
}