<%@ include file="/init.jsp"%>

<c:set var="task" value="${SEARCH_CONTAINER_RESULT_ROW.object}" />

<liferay-ui:icon-menu markupView="lexicon">

	<portlet:renderURL var="viewTaskURL">
		<portlet:param name="mvcRenderCommandName" value="<%=TaskManagerPortletKeys.TASK_VIEW %>" />
		<portlet:param name="redirect" value="${currentURL}" />
		<portlet:param name="taskId" value="${task.taskId}" />
	</portlet:renderURL>
	<liferay-ui:icon message="view" url="${viewTaskURL}" />
	
	<c:if test="${task.status == 1}">
		<portlet:renderURL var="editTaskURL">
			<portlet:param name="mvcRenderCommandName" value="<%=TaskManagerPortletKeys.TASK_EDIT %>" />
			<portlet:param name="redirect" value="${currentURL}" />
			<portlet:param name="taskId" value="${task.taskId}" />
		</portlet:renderURL>
		<liferay-ui:icon message="edit" url="${editTaskURL}" />
		
		<portlet:actionURL var="completeTaskURL" name="<%=TaskManagerPortletKeys.TASK_COMPLETE %>">
			<portlet:param name="redirect" value="${currentURL}" />
			<portlet:param name="taskId" value="${task.taskId}" />
		</portlet:actionURL>
		<liferay-ui:icon message="complete" url="${completeTaskURL}" />
	</c:if>

	<portlet:actionURL var="deleteTaskURL" name="<%=TaskManagerPortletKeys.TASK_DELETE %>">
		<portlet:param name="redirect" value="${currentURL}" />
		<portlet:param name="taskId" value="${task.taskId}" />
	</portlet:actionURL>
	<liferay-ui:icon-delete url="${deleteTaskURL}" />
</liferay-ui:icon-menu>