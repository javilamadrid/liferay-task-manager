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

package com.javilamadrid.taskmanager.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import com.javilamadrid.taskmanager.model.Task;
import com.javilamadrid.taskmanager.service.base.TaskServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * The implementation of the task remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * <code>com.javilamadrid.taskmanager.service.TaskService</code> interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have
 * security checks based on the propagated JAAS credentials because this service
 * can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TaskServiceBaseImpl
 */
@Component(property = { "json.web.service.context.name=task",
        "json.web.service.context.path=Task" }, service = AopService.class)
public class TaskServiceImpl extends TaskServiceBaseImpl {

    /*
     * NOTE FOR DEVELOPERS:
     * Never reference this class directly. Always use
     * <code>com.javilamadrid.taskmanager.service.TaskServiceUtil</code> to access
     * the task remote service.
     */
    @Override
    public Task addTask(final long groupId, final Map<Locale, String> titleMap,
            final Map<Locale, String> descriptionMap, final Date deadline, final int priority,
            final ServiceContext serviceContext) throws PortalException {

        return this.taskLocalService.addTask(groupId, titleMap, descriptionMap, deadline, priority, serviceContext);
    }

    @Override
    public Task updateTask(final long taskId, final Map<Locale, String> titleMap,
            final Map<Locale, String> descriptionMap, final Date deadline, final int priority) throws PortalException {

        return this.taskLocalService.updateTask(taskId, titleMap, descriptionMap, deadline, priority);
    }

    @Override
    public Task completeTask(final long taskId, final ServiceContext serviceContext) throws PortalException {

        return this.taskLocalService.completeTask(taskId, serviceContext);
    }

    @Override
    public Task deleteTask(final long taskId) throws PortalException {

        return this.taskLocalService.deleteTask(taskId);
    }

    @Override
    public Task getTask(final long taskId) throws PortalException {

        return this.taskLocalService.getTask(taskId);
    }

    @Override
    public List<Task> getTasksByKeywords(final long groupId, final String keywords, final int start, final int end,
            final OrderByComparator<Task> orderByComparator) {

        return this.taskLocalService.getTasksByKeywords(groupId, keywords, start, end, orderByComparator);
    }

    @Override
    public long getTasksCountByKeywords(final long groupId, final String keywords) {

        return this.taskLocalService.getTasksCountByKeywords(groupId, keywords);
    }
}
