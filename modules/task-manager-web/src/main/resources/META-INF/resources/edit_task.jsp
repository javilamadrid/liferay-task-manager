<%@ include file="/init.jsp"%>

<c:choose>
	<c:when test="${not empty task}">
		<portlet:actionURL var="taskActionURL" name="<%=TaskManagerPortletKeys.TASK_EDIT %>">
			<portlet:param name="redirect" value="${param.redirect}" />
		</portlet:actionURL>

		<c:set var="editTitle" value="edit-task" />
	</c:when>
	<c:otherwise>
		<portlet:actionURL var="taskActionURL" name="<%=TaskManagerPortletKeys.TASK_ADD %>">
			<portlet:param name="redirect" value="${param.redirect}" />
		</portlet:actionURL>

		<c:set var="editTitle" value="add-task" />
	</c:otherwise>
</c:choose>

<div>

	<h1>
		<liferay-ui:message key="${editTitle}" />
	</h1>

	<aui:model-context bean="${task}" model="${taskClass}" />

	<aui:form action="${taskActionURL}" name="fm">

		<aui:input name="taskId" field="taskId" type="hidden" />

		<aui:fieldset-group markupView="lexicon">

			<aui:fieldset>

				<aui:input name="title">
					<aui:validator name="required" />
				</aui:input>

				<aui:input name="description" type="textarea" localized="true">
					<aui:validator name="required" />
				</aui:input>
				
				<aui:input name="priority">
					<aui:validator name="required" />
				</aui:input>

				<aui:input name="deadline">
				</aui:input>
			</aui:fieldset>
		</aui:fieldset-group>

		<aui:button-row>
			<aui:button cssClass="btn btn-primary" type="submit" />
			<aui:button cssClass="btn btn-secondary" onClick="${param.redirect}"
				type="cancel" />
		</aui:button-row>
	</aui:form>
</div>