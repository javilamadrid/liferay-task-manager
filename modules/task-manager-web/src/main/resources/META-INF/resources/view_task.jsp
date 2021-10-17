<%@ include file="/init.jsp"%>

<div>

	<h1>${task.getTitle(locale)}</h1>

	<h2>
		<liferay-ui:message key="task-information" />
	</h2>

	<div>
		<dl>
			<dt>
				<liferay-ui:message key="created" />
			</dt>
			<dd>${createDate}</dd>

			<dt>
				<liferay-ui:message key="created-by" />
			</dt>
			<dd>${task.userName}</dd>
			
			<dt>
				<liferay-ui:message key="priority" />
			</dt>
			<dd>${task.priority}</dd>

			<dt>
				<liferay-ui:message key="deadline" />
			</dt>
			<dd>${deadline}</dd>

			<dt>
				<liferay-ui:message key="description" />
			</dt>
			<dd>${task.getDescription(locale)}</dd>
		</dl>
	</div>
</div>