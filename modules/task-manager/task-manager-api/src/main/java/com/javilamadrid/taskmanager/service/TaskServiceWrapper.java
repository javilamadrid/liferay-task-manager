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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TaskService}.
 *
 * @author Brian Wing Shun Chan
 * @see TaskService
 * @generated
 */
public class TaskServiceWrapper
	implements ServiceWrapper<TaskService>, TaskService {

	public TaskServiceWrapper(TaskService taskService) {
		_taskService = taskService;
	}

	@Override
	public com.javilamadrid.taskmanager.model.Task addTask(
			long groupId, java.util.Map<java.util.Locale, String> titleMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			java.util.Date deadline, int priority,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _taskService.addTask(
			groupId, titleMap, descriptionMap, deadline, priority,
			serviceContext);
	}

	@Override
	public com.javilamadrid.taskmanager.model.Task completeTask(
			long taskId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _taskService.completeTask(taskId, serviceContext);
	}

	@Override
	public com.javilamadrid.taskmanager.model.Task deleteTask(long taskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _taskService.deleteTask(taskId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _taskService.getOSGiServiceIdentifier();
	}

	@Override
	public com.javilamadrid.taskmanager.model.Task getTask(long taskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _taskService.getTask(taskId);
	}

	@Override
	public java.util.List<com.javilamadrid.taskmanager.model.Task>
		getTasksByKeywords(
			long groupId, String keywords, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.javilamadrid.taskmanager.model.Task> orderByComparator) {

		return _taskService.getTasksByKeywords(
			groupId, keywords, start, end, orderByComparator);
	}

	@Override
	public long getTasksCountByKeywords(long groupId, String keywords) {
		return _taskService.getTasksCountByKeywords(groupId, keywords);
	}

	@Override
	public com.javilamadrid.taskmanager.model.Task updateTask(
			long taskId, java.util.Map<java.util.Locale, String> titleMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			java.util.Date deadline, int priority)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _taskService.updateTask(
			taskId, titleMap, descriptionMap, deadline, priority);
	}

	@Override
	public TaskService getWrappedService() {
		return _taskService;
	}

	@Override
	public void setWrappedService(TaskService taskService) {
		_taskService = taskService;
	}

	private TaskService _taskService;

}