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

package com.javilamadrid.taskmanager.service.persistence.test;

import com.javilamadrid.taskmanager.exception.NoSuchTaskException;
import com.javilamadrid.taskmanager.model.Task;
import com.javilamadrid.taskmanager.service.TaskLocalServiceUtil;
import com.javilamadrid.taskmanager.service.persistence.TaskPersistence;
import com.javilamadrid.taskmanager.service.persistence.TaskUtil;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class TaskPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.javilamadrid.taskmanager.service"));

	@Before
	public void setUp() {
		_persistence = TaskUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Task> iterator = _tasks.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Task task = _persistence.create(pk);

		Assert.assertNotNull(task);

		Assert.assertEquals(task.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Task newTask = addTask();

		_persistence.remove(newTask);

		Task existingTask = _persistence.fetchByPrimaryKey(
			newTask.getPrimaryKey());

		Assert.assertNull(existingTask);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addTask();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Task newTask = _persistence.create(pk);

		newTask.setUuid(RandomTestUtil.randomString());

		newTask.setGroupId(RandomTestUtil.nextLong());

		newTask.setCompanyId(RandomTestUtil.nextLong());

		newTask.setUserId(RandomTestUtil.nextLong());

		newTask.setUserName(RandomTestUtil.randomString());

		newTask.setCreateDate(RandomTestUtil.nextDate());

		newTask.setModifiedDate(RandomTestUtil.nextDate());

		newTask.setTitle(RandomTestUtil.randomString());

		newTask.setDescription(RandomTestUtil.randomString());

		newTask.setPriority(RandomTestUtil.nextInt());

		newTask.setDeadline(RandomTestUtil.nextDate());

		newTask.setStatus(RandomTestUtil.nextInt());

		newTask.setStatusByUserId(RandomTestUtil.nextLong());

		newTask.setStatusByUserName(RandomTestUtil.randomString());

		newTask.setStatusDate(RandomTestUtil.nextDate());

		_tasks.add(_persistence.update(newTask));

		Task existingTask = _persistence.findByPrimaryKey(
			newTask.getPrimaryKey());

		Assert.assertEquals(existingTask.getUuid(), newTask.getUuid());
		Assert.assertEquals(existingTask.getTaskId(), newTask.getTaskId());
		Assert.assertEquals(existingTask.getGroupId(), newTask.getGroupId());
		Assert.assertEquals(
			existingTask.getCompanyId(), newTask.getCompanyId());
		Assert.assertEquals(existingTask.getUserId(), newTask.getUserId());
		Assert.assertEquals(existingTask.getUserName(), newTask.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingTask.getCreateDate()),
			Time.getShortTimestamp(newTask.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingTask.getModifiedDate()),
			Time.getShortTimestamp(newTask.getModifiedDate()));
		Assert.assertEquals(existingTask.getTitle(), newTask.getTitle());
		Assert.assertEquals(
			existingTask.getDescription(), newTask.getDescription());
		Assert.assertEquals(existingTask.getPriority(), newTask.getPriority());
		Assert.assertEquals(
			Time.getShortTimestamp(existingTask.getDeadline()),
			Time.getShortTimestamp(newTask.getDeadline()));
		Assert.assertEquals(existingTask.getStatus(), newTask.getStatus());
		Assert.assertEquals(
			existingTask.getStatusByUserId(), newTask.getStatusByUserId());
		Assert.assertEquals(
			existingTask.getStatusByUserName(), newTask.getStatusByUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingTask.getStatusDate()),
			Time.getShortTimestamp(newTask.getStatusDate()));
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid("");

		_persistence.countByUuid("null");

		_persistence.countByUuid((String)null);
	}

	@Test
	public void testCountByUUID_G() throws Exception {
		_persistence.countByUUID_G("", RandomTestUtil.nextLong());

		_persistence.countByUUID_G("null", 0L);

		_persistence.countByUUID_G((String)null, 0L);
	}

	@Test
	public void testCountByUuid_C() throws Exception {
		_persistence.countByUuid_C("", RandomTestUtil.nextLong());

		_persistence.countByUuid_C("null", 0L);

		_persistence.countByUuid_C((String)null, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Task newTask = addTask();

		Task existingTask = _persistence.findByPrimaryKey(
			newTask.getPrimaryKey());

		Assert.assertEquals(existingTask, newTask);
	}

	@Test(expected = NoSuchTaskException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<Task> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"task_Task", "uuid", true, "taskId", true, "groupId", true,
			"companyId", true, "userId", true, "userName", true, "createDate",
			true, "modifiedDate", true, "title", true, "description", true,
			"priority", true, "deadline", true, "status", true,
			"statusByUserId", true, "statusByUserName", true, "statusDate",
			true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Task newTask = addTask();

		Task existingTask = _persistence.fetchByPrimaryKey(
			newTask.getPrimaryKey());

		Assert.assertEquals(existingTask, newTask);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Task missingTask = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingTask);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		Task newTask1 = addTask();
		Task newTask2 = addTask();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newTask1.getPrimaryKey());
		primaryKeys.add(newTask2.getPrimaryKey());

		Map<Serializable, Task> tasks = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(2, tasks.size());
		Assert.assertEquals(newTask1, tasks.get(newTask1.getPrimaryKey()));
		Assert.assertEquals(newTask2, tasks.get(newTask2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Task> tasks = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(tasks.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		Task newTask = addTask();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newTask.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Task> tasks = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, tasks.size());
		Assert.assertEquals(newTask, tasks.get(newTask.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Task> tasks = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(tasks.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		Task newTask = addTask();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newTask.getPrimaryKey());

		Map<Serializable, Task> tasks = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, tasks.size());
		Assert.assertEquals(newTask, tasks.get(newTask.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			TaskLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Task>() {

				@Override
				public void performAction(Task task) {
					Assert.assertNotNull(task);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		Task newTask = addTask();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Task.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("taskId", newTask.getTaskId()));

		List<Task> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Task existingTask = result.get(0);

		Assert.assertEquals(existingTask, newTask);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Task.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("taskId", RandomTestUtil.nextLong()));

		List<Task> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		Task newTask = addTask();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Task.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("taskId"));

		Object newTaskId = newTask.getTaskId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in("taskId", new Object[] {newTaskId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingTaskId = result.get(0);

		Assert.assertEquals(existingTaskId, newTaskId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Task.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("taskId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"taskId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		Task newTask = addTask();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(newTask.getPrimaryKey()));
	}

	@Test
	public void testResetOriginalValuesWithDynamicQueryLoadFromDatabase()
		throws Exception {

		_testResetOriginalValuesWithDynamicQuery(true);
	}

	@Test
	public void testResetOriginalValuesWithDynamicQueryLoadFromSession()
		throws Exception {

		_testResetOriginalValuesWithDynamicQuery(false);
	}

	private void _testResetOriginalValuesWithDynamicQuery(boolean clearSession)
		throws Exception {

		Task newTask = addTask();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Task.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("taskId", newTask.getTaskId()));

		List<Task> result = _persistence.findWithDynamicQuery(dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(Task task) {
		Assert.assertEquals(
			task.getUuid(),
			ReflectionTestUtil.invoke(
				task, "getColumnOriginalValue", new Class<?>[] {String.class},
				"uuid_"));
		Assert.assertEquals(
			Long.valueOf(task.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				task, "getColumnOriginalValue", new Class<?>[] {String.class},
				"groupId"));
	}

	protected Task addTask() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Task task = _persistence.create(pk);

		task.setUuid(RandomTestUtil.randomString());

		task.setGroupId(RandomTestUtil.nextLong());

		task.setCompanyId(RandomTestUtil.nextLong());

		task.setUserId(RandomTestUtil.nextLong());

		task.setUserName(RandomTestUtil.randomString());

		task.setCreateDate(RandomTestUtil.nextDate());

		task.setModifiedDate(RandomTestUtil.nextDate());

		task.setTitle(RandomTestUtil.randomString());

		task.setDescription(RandomTestUtil.randomString());

		task.setPriority(RandomTestUtil.nextInt());

		task.setDeadline(RandomTestUtil.nextDate());

		task.setStatus(RandomTestUtil.nextInt());

		task.setStatusByUserId(RandomTestUtil.nextLong());

		task.setStatusByUserName(RandomTestUtil.randomString());

		task.setStatusDate(RandomTestUtil.nextDate());

		_tasks.add(_persistence.update(task));

		return task;
	}

	private List<Task> _tasks = new ArrayList<Task>();
	private TaskPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}