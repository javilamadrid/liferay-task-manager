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
import com.javilamadrid.taskmanager.service.base.TaskLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

/**
 * The implementation of the task local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * <code>com.javilamadrid.taskmanager.service.TaskLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TaskLocalServiceBaseImpl
 */
@Component(property = "model.class.name=com.javilamadrid.taskmanager.model.Task", service = AopService.class)
public class TaskLocalServiceImpl extends TaskLocalServiceBaseImpl {

    /*
     * NOTE FOR DEVELOPERS:
     * Never reference this class directly. Use
     * <code>com.javilamadrid.taskmanager.service.TaskLocalService</code> via
     * injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use
     * <code>com.javilamadrid.taskmanager.service.TaskLocalServiceUtil</code>.
     */
    @Override
    public Task addTask(final long groupId, final Map<Locale, String> titleMap,
            final Map<Locale, String> descriptionMap, final Date deadline, final int priority,
            final ServiceContext serviceContext) throws PortalException {

        final long userId = serviceContext.getUserId();
        final User user = this.userLocalService.getUser(userId);
        final long taskId = this.counterLocalService.increment(Task.class.getName());

        final Task task = this.createTask(taskId);

        task.setCompanyId(serviceContext.getCompanyId());
        task.setCreateDate(new Date());
        task.setDeadline(deadline);
        task.setDescriptionMap(descriptionMap);
        task.setGroupId(groupId);
        task.setModifiedDate(new Date());
        task.setPriority(priority);
        task.setStatus(WorkflowConstants.STATUS_PENDING);
        task.setTitleMap(titleMap);
        task.setUserId(userId);
        task.setUserName(user.getScreenName());

        return super.addTask(task);
    }

    @Override
    public Task updateTask(final long taskId, final Map<Locale, String> titleMap,
            final Map<Locale, String> descriptionMap, final Date deadline, final int priority) throws PortalException {

        final Task task = this.getTask(taskId);

        task.setDeadline(deadline);
        task.setDescriptionMap(descriptionMap);
        task.setModifiedDate(new Date());
        task.setPriority(priority);
        task.setTitleMap(titleMap);

        return super.updateTask(task);
    }

    @Override
    public Task completeTask(final long taskId, final ServiceContext serviceContext) throws PortalException {

        final long userId = serviceContext.getUserId();
        final User user = this.userLocalService.getUser(userId);

        final Task task = this.getTask(taskId);
        task.setStatus(WorkflowConstants.STATUS_APPROVED);
        task.setStatusByUserId(user.getUserId());
        task.setStatusByUserName(user.getFullName());
        task.setStatusDate(new Date());

        return super.updateTask(task);
    }

    @Override
    public List<Task> getTasksByKeywords(final long groupId, final String keywords, final int start, final int end,
            final OrderByComparator<Task> orderByComparator) {

        return this.taskLocalService.dynamicQuery(this.getKeywordSearchDynamicQuery(groupId, keywords), start, end,
                orderByComparator);
    }

    @Override
    public long getTasksCountByKeywords(final long groupId, final String keywords) {

        return this.taskLocalService.dynamicQueryCount(this.getKeywordSearchDynamicQuery(groupId, keywords));
    }

    private DynamicQuery getKeywordSearchDynamicQuery(final long groupId, final String keywords) {

        final DynamicQuery dynamicQuery = this.dynamicQuery().add(RestrictionsFactoryUtil.eq("groupId", groupId));

        if (Validator.isNotNull(keywords)) {

            final Disjunction disjunctionQuery = RestrictionsFactoryUtil.disjunction();
            disjunctionQuery.add(RestrictionsFactoryUtil.like("title", "%" + keywords + "%"));
            disjunctionQuery.add(RestrictionsFactoryUtil.like("description", "%" + keywords + "%"));
            dynamicQuery.add(disjunctionQuery);
        }

        return dynamicQuery;
    }
}
