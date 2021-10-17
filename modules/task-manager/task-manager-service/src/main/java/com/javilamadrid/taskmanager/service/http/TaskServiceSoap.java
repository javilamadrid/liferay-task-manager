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

package com.javilamadrid.taskmanager.service.http;

import com.javilamadrid.taskmanager.service.TaskServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;

import java.rmi.RemoteException;

import java.util.Locale;
import java.util.Map;

/**
 * Provides the SOAP utility for the
 * <code>TaskServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.javilamadrid.taskmanager.model.TaskSoap</code>. If the method in the
 * service utility returns a
 * <code>com.javilamadrid.taskmanager.model.Task</code>, that is translated to a
 * <code>com.javilamadrid.taskmanager.model.TaskSoap</code>. Methods that SOAP
 * cannot safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TaskServiceHttp
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class TaskServiceSoap {

	public static com.javilamadrid.taskmanager.model.TaskSoap addTask(
			long groupId, String[] titleMapLanguageIds, String[] titleMapValues,
			String[] descriptionMapLanguageIds, String[] descriptionMapValues,
			java.util.Date deadline, int priority,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(
				titleMapLanguageIds, titleMapValues);
			Map<Locale, String> descriptionMap =
				LocalizationUtil.getLocalizationMap(
					descriptionMapLanguageIds, descriptionMapValues);

			com.javilamadrid.taskmanager.model.Task returnValue =
				TaskServiceUtil.addTask(
					groupId, titleMap, descriptionMap, deadline, priority,
					serviceContext);

			return com.javilamadrid.taskmanager.model.TaskSoap.toSoapModel(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.javilamadrid.taskmanager.model.TaskSoap updateTask(
			long taskId, String[] titleMapLanguageIds, String[] titleMapValues,
			String[] descriptionMapLanguageIds, String[] descriptionMapValues,
			java.util.Date deadline, int priority)
		throws RemoteException {

		try {
			Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(
				titleMapLanguageIds, titleMapValues);
			Map<Locale, String> descriptionMap =
				LocalizationUtil.getLocalizationMap(
					descriptionMapLanguageIds, descriptionMapValues);

			com.javilamadrid.taskmanager.model.Task returnValue =
				TaskServiceUtil.updateTask(
					taskId, titleMap, descriptionMap, deadline, priority);

			return com.javilamadrid.taskmanager.model.TaskSoap.toSoapModel(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.javilamadrid.taskmanager.model.TaskSoap completeTask(
			long taskId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.javilamadrid.taskmanager.model.Task returnValue =
				TaskServiceUtil.completeTask(taskId, serviceContext);

			return com.javilamadrid.taskmanager.model.TaskSoap.toSoapModel(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.javilamadrid.taskmanager.model.TaskSoap deleteTask(
			long taskId)
		throws RemoteException {

		try {
			com.javilamadrid.taskmanager.model.Task returnValue =
				TaskServiceUtil.deleteTask(taskId);

			return com.javilamadrid.taskmanager.model.TaskSoap.toSoapModel(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.javilamadrid.taskmanager.model.TaskSoap getTask(
			long taskId)
		throws RemoteException {

		try {
			com.javilamadrid.taskmanager.model.Task returnValue =
				TaskServiceUtil.getTask(taskId);

			return com.javilamadrid.taskmanager.model.TaskSoap.toSoapModel(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.javilamadrid.taskmanager.model.TaskSoap[]
			getTasksByKeywords(
				long groupId, String keywords, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.javilamadrid.taskmanager.model.Task> orderByComparator)
		throws RemoteException {

		try {
			java.util.List<com.javilamadrid.taskmanager.model.Task>
				returnValue = TaskServiceUtil.getTasksByKeywords(
					groupId, keywords, start, end, orderByComparator);

			return com.javilamadrid.taskmanager.model.TaskSoap.toSoapModels(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static long getTasksCountByKeywords(long groupId, String keywords)
		throws RemoteException {

		try {
			long returnValue = TaskServiceUtil.getTasksCountByKeywords(
				groupId, keywords);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(TaskServiceSoap.class);

}