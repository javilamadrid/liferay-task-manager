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

package com.javilamadrid.taskmanager.service;

import com.javilamadrid.taskmanager.model.Task;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;
import java.util.Map;

/**
 * Provides the remote service utility for Task. This utility wraps
 * <code>com.javilamadrid.taskmanager.service.impl.TaskServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see TaskService
 * @generated
 */
public class TaskServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.javilamadrid.taskmanager.service.impl.TaskServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static Task addTask(
			long groupId, Map<java.util.Locale, String> titleMap,
			Map<java.util.Locale, String> descriptionMap,
			java.util.Date deadline, int priority,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addTask(
			groupId, titleMap, descriptionMap, deadline, priority,
			serviceContext);
	}

	public static Task completeTask(
			long taskId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().completeTask(taskId, serviceContext);
	}

	public static Task deleteTask(long taskId) throws PortalException {
		return getService().deleteTask(taskId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static Task getTask(long taskId) throws PortalException {
		return getService().getTask(taskId);
	}

	public static List<Task> getTasksByKeywords(
		long groupId, String keywords, int start, int end,
		OrderByComparator<Task> orderByComparator) {

		return getService().getTasksByKeywords(
			groupId, keywords, start, end, orderByComparator);
	}

	public static long getTasksCountByKeywords(long groupId, String keywords) {
		return getService().getTasksCountByKeywords(groupId, keywords);
	}

	public static Task updateTask(
			long taskId, Map<java.util.Locale, String> titleMap,
			Map<java.util.Locale, String> descriptionMap,
			java.util.Date deadline, int priority)
		throws PortalException {

		return getService().updateTask(
			taskId, titleMap, descriptionMap, deadline, priority);
	}

	public static TaskService getService() {
		return _service;
	}

	private static volatile TaskService _service;

}