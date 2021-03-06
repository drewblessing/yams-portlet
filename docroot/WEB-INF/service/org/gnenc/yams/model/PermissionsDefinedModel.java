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

package org.gnenc.yams.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.AuditedModel;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the PermissionsDefined service. Represents a row in the &quot;yams_PermissionsDefined&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link org.gnenc.yams.model.impl.PermissionsDefinedModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link org.gnenc.yams.model.impl.PermissionsDefinedImpl}.
 * </p>
 *
 * @author Drew A. Blessing
 * @see PermissionsDefined
 * @see org.gnenc.yams.model.impl.PermissionsDefinedImpl
 * @see org.gnenc.yams.model.impl.PermissionsDefinedModelImpl
 * @generated
 */
public interface PermissionsDefinedModel extends AuditedModel,
	BaseModel<PermissionsDefined> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a permissions defined model instance should use the {@link PermissionsDefined} interface instead.
	 */

	/**
	 * Returns the primary key of this permissions defined.
	 *
	 * @return the primary key of this permissions defined
	 */
	public String getPrimaryKey();

	/**
	 * Sets the primary key of this permissions defined.
	 *
	 * @param primaryKey the primary key of this permissions defined
	 */
	public void setPrimaryKey(String primaryKey);

	/**
	 * Returns the permission key of this permissions defined.
	 *
	 * @return the permission key of this permissions defined
	 */
	@AutoEscape
	public String getPermissionKey();

	/**
	 * Sets the permission key of this permissions defined.
	 *
	 * @param permissionKey the permission key of this permissions defined
	 */
	public void setPermissionKey(String permissionKey);

	/**
	 * Returns the company ID of this permissions defined.
	 *
	 * @return the company ID of this permissions defined
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this permissions defined.
	 *
	 * @param companyId the company ID of this permissions defined
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this permissions defined.
	 *
	 * @return the user ID of this permissions defined
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this permissions defined.
	 *
	 * @param userId the user ID of this permissions defined
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this permissions defined.
	 *
	 * @return the user uuid of this permissions defined
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this permissions defined.
	 *
	 * @param userUuid the user uuid of this permissions defined
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this permissions defined.
	 *
	 * @return the user name of this permissions defined
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this permissions defined.
	 *
	 * @param userName the user name of this permissions defined
	 */
	public void setUserName(String userName);

	/**
	 * Returns the create date of this permissions defined.
	 *
	 * @return the create date of this permissions defined
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this permissions defined.
	 *
	 * @param createDate the create date of this permissions defined
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this permissions defined.
	 *
	 * @return the modified date of this permissions defined
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this permissions defined.
	 *
	 * @param modifiedDate the modified date of this permissions defined
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the bit location of this permissions defined.
	 *
	 * @return the bit location of this permissions defined
	 */
	public int getBitLocation();

	/**
	 * Sets the bit location of this permissions defined.
	 *
	 * @param bitLocation the bit location of this permissions defined
	 */
	public void setBitLocation(int bitLocation);

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public Serializable getPrimaryKeyObj();

	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(PermissionsDefined permissionsDefined);

	public int hashCode();

	public CacheModel<PermissionsDefined> toCacheModel();

	public PermissionsDefined toEscapedModel();

	public String toString();

	public String toXmlString();
}