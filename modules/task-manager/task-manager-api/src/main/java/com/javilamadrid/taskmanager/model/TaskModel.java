/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.javilamadrid.taskmanager.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;
import com.liferay.portal.kernel.model.WorkflowedModel;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the Task service. Represents a row in the &quot;task_Task&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.javilamadrid.taskmanager.model.impl.TaskModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.javilamadrid.taskmanager.model.impl.TaskImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Task
 * @generated
 */
@ProviderType
public interface TaskModel
	extends BaseModel<Task>, GroupedModel, LocalizedModel, ShardedModel,
			StagedAuditedModel, WorkflowedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a task model instance should use the {@link Task} interface instead.
	 */

	/**
	 * Returns the primary key of this task.
	 *
	 * @return the primary key of this task
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this task.
	 *
	 * @param primaryKey the primary key of this task
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this task.
	 *
	 * @return the uuid of this task
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this task.
	 *
	 * @param uuid the uuid of this task
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the task ID of this task.
	 *
	 * @return the task ID of this task
	 */
	public long getTaskId();

	/**
	 * Sets the task ID of this task.
	 *
	 * @param taskId the task ID of this task
	 */
	public void setTaskId(long taskId);

	/**
	 * Returns the group ID of this task.
	 *
	 * @return the group ID of this task
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this task.
	 *
	 * @param groupId the group ID of this task
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this task.
	 *
	 * @return the company ID of this task
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this task.
	 *
	 * @param companyId the company ID of this task
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this task.
	 *
	 * @return the user ID of this task
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this task.
	 *
	 * @param userId the user ID of this task
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this task.
	 *
	 * @return the user uuid of this task
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this task.
	 *
	 * @param userUuid the user uuid of this task
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this task.
	 *
	 * @return the user name of this task
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this task.
	 *
	 * @param userName the user name of this task
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this task.
	 *
	 * @return the create date of this task
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this task.
	 *
	 * @param createDate the create date of this task
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this task.
	 *
	 * @return the modified date of this task
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this task.
	 *
	 * @param modifiedDate the modified date of this task
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the title of this task.
	 *
	 * @return the title of this task
	 */
	public String getTitle();

	/**
	 * Returns the localized title of this task in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized title of this task
	 */
	@AutoEscape
	public String getTitle(Locale locale);

	/**
	 * Returns the localized title of this task in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this task. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getTitle(Locale locale, boolean useDefault);

	/**
	 * Returns the localized title of this task in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized title of this task
	 */
	@AutoEscape
	public String getTitle(String languageId);

	/**
	 * Returns the localized title of this task in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this task
	 */
	@AutoEscape
	public String getTitle(String languageId, boolean useDefault);

	@AutoEscape
	public String getTitleCurrentLanguageId();

	@AutoEscape
	public String getTitleCurrentValue();

	/**
	 * Returns a map of the locales and localized titles of this task.
	 *
	 * @return the locales and localized titles of this task
	 */
	public Map<Locale, String> getTitleMap();

	/**
	 * Sets the title of this task.
	 *
	 * @param title the title of this task
	 */
	public void setTitle(String title);

	/**
	 * Sets the localized title of this task in the language.
	 *
	 * @param title the localized title of this task
	 * @param locale the locale of the language
	 */
	public void setTitle(String title, Locale locale);

	/**
	 * Sets the localized title of this task in the language, and sets the default locale.
	 *
	 * @param title the localized title of this task
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setTitle(String title, Locale locale, Locale defaultLocale);

	public void setTitleCurrentLanguageId(String languageId);

	/**
	 * Sets the localized titles of this task from the map of locales and localized titles.
	 *
	 * @param titleMap the locales and localized titles of this task
	 */
	public void setTitleMap(Map<Locale, String> titleMap);

	/**
	 * Sets the localized titles of this task from the map of locales and localized titles, and sets the default locale.
	 *
	 * @param titleMap the locales and localized titles of this task
	 * @param defaultLocale the default locale
	 */
	public void setTitleMap(Map<Locale, String> titleMap, Locale defaultLocale);

	/**
	 * Returns the description of this task.
	 *
	 * @return the description of this task
	 */
	public String getDescription();

	/**
	 * Returns the localized description of this task in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this task
	 */
	@AutoEscape
	public String getDescription(Locale locale);

	/**
	 * Returns the localized description of this task in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this task. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getDescription(Locale locale, boolean useDefault);

	/**
	 * Returns the localized description of this task in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this task
	 */
	@AutoEscape
	public String getDescription(String languageId);

	/**
	 * Returns the localized description of this task in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this task
	 */
	@AutoEscape
	public String getDescription(String languageId, boolean useDefault);

	@AutoEscape
	public String getDescriptionCurrentLanguageId();

	@AutoEscape
	public String getDescriptionCurrentValue();

	/**
	 * Returns a map of the locales and localized descriptions of this task.
	 *
	 * @return the locales and localized descriptions of this task
	 */
	public Map<Locale, String> getDescriptionMap();

	/**
	 * Sets the description of this task.
	 *
	 * @param description the description of this task
	 */
	public void setDescription(String description);

	/**
	 * Sets the localized description of this task in the language.
	 *
	 * @param description the localized description of this task
	 * @param locale the locale of the language
	 */
	public void setDescription(String description, Locale locale);

	/**
	 * Sets the localized description of this task in the language, and sets the default locale.
	 *
	 * @param description the localized description of this task
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setDescription(
		String description, Locale locale, Locale defaultLocale);

	public void setDescriptionCurrentLanguageId(String languageId);

	/**
	 * Sets the localized descriptions of this task from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this task
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap);

	/**
	 * Sets the localized descriptions of this task from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this task
	 * @param defaultLocale the default locale
	 */
	public void setDescriptionMap(
		Map<Locale, String> descriptionMap, Locale defaultLocale);

	/**
	 * Returns the priority of this task.
	 *
	 * @return the priority of this task
	 */
	public int getPriority();

	/**
	 * Sets the priority of this task.
	 *
	 * @param priority the priority of this task
	 */
	public void setPriority(int priority);

	/**
	 * Returns the deadline of this task.
	 *
	 * @return the deadline of this task
	 */
	public Date getDeadline();

	/**
	 * Sets the deadline of this task.
	 *
	 * @param deadline the deadline of this task
	 */
	public void setDeadline(Date deadline);

	/**
	 * Returns the status of this task.
	 *
	 * @return the status of this task
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this task.
	 *
	 * @param status the status of this task
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this task.
	 *
	 * @return the status by user ID of this task
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this task.
	 *
	 * @param statusByUserId the status by user ID of this task
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this task.
	 *
	 * @return the status by user uuid of this task
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this task.
	 *
	 * @param statusByUserUuid the status by user uuid of this task
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this task.
	 *
	 * @return the status by user name of this task
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this task.
	 *
	 * @param statusByUserName the status by user name of this task
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this task.
	 *
	 * @return the status date of this task
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this task.
	 *
	 * @param statusDate the status date of this task
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns <code>true</code> if this task is approved.
	 *
	 * @return <code>true</code> if this task is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this task is denied.
	 *
	 * @return <code>true</code> if this task is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this task is a draft.
	 *
	 * @return <code>true</code> if this task is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this task is expired.
	 *
	 * @return <code>true</code> if this task is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this task is inactive.
	 *
	 * @return <code>true</code> if this task is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this task is incomplete.
	 *
	 * @return <code>true</code> if this task is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this task is pending.
	 *
	 * @return <code>true</code> if this task is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this task is scheduled.
	 *
	 * @return <code>true</code> if this task is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled();

	@Override
	public String[] getAvailableLanguageIds();

	@Override
	public String getDefaultLanguageId();

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException;

	@Override
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException;

}