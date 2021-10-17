<%@ include file="/init.jsp"%>

<div>
	<h1>
		<liferay-ui:message key="tasks" />
	</h1>

	<clay:management-toolbar disabled="${taskCount eq 0}"
		displayContext="${taskManagementToolbarDisplayContext}"
		itemsTotal="${taskCount}" searchContainerId="taskEntries"
		selectable="false" />

	<liferay-ui:search-container emptyResultsMessage="no-tasks"
		id="taskEntries" iteratorURL="${portletURL}" total="${taskCount}">

		<liferay-ui:search-container-results results="${tasks}" />

		<liferay-ui:search-container-row className="com.javilamadrid.taskmanager.model.Task" modelVar="entry">

			<portlet:renderURL var="viewTaskURL">
				<portlet:param name="mvcRenderCommandName" value="<%= TaskManagerPortletKeys.TASK_VIEW%>" />
				<portlet:param name="redirect" value="${currentURL}" />
				<portlet:param name="taskId" value="${entry.taskId}" />
			</portlet:renderURL>
			
			<c:choose>
				<c:when
					test='${taskManagementToolbarDisplayContext.getDisplayStyle().equals("descriptive")}'>

					<liferay-ui:search-container-column-user showDetails="<%=false%>" userId="<%=entry.getUserId()%>" />
					<liferay-ui:search-container-column-text colspan="<%=2%>">
						<%
						String modifiedDateDescription = LanguageUtil.getTimeDescription(request,
						        System.currentTimeMillis() - entry.getModifiedDate().getTime(), true);
						%>

						<h5 class="text-default">
							<liferay-ui:message arguments="<%=new String[] { entry.getUserName(), modifiedDateDescription }%>" key="x-modified-x-ago" />
						</h5>

						<h4>
							<aui:a href="${viewTaskURL}">${entry.getTitle(locale)}</aui:a>
						</h4>
					</liferay-ui:search-container-column-text>
					
					<liferay-ui:search-container-column-status property="status" />

					<liferay-ui:search-container-column-jsp path="/entry_actions.jsp" />
				</c:when>

				<c:when
					test='${taskManagementToolbarDisplayContext.getDisplayStyle().equals("icon")}'>

					<%
					row.setCssClass("lfr-asset-item");
					%>

					<liferay-ui:search-container-column-text>

						<%-- Vertical card. --%>

						<liferay-frontend:icon-vertical-card
							actionJsp="/entry_actions.jsp"
							actionJspServletContext="<%= application %>" icon="cards2"
							resultRow="${row}" title="${entry.getTitle(locale)}"
							url="${viewTaskURL}">

							<liferay-frontend:vertical-card-sticker-bottom>

								<liferay-ui:user-portrait cssClass="sticker sticker-bottom"
									userId="${entry.userId}" />
							</liferay-frontend:vertical-card-sticker-bottom>

							<liferay-frontend:vertical-card-footer>

								<div class="truncate-text">

									<%-- Strip HTML --%>

									<%=HtmlUtil.stripHtml(entry.getDescription(locale))%>
								</div>
							</liferay-frontend:vertical-card-footer>
						</liferay-frontend:icon-vertical-card>
					</liferay-ui:search-container-column-text>
				</c:when>

				<c:otherwise>

					<liferay-ui:search-container-column-text href="${viewTaskURL}"
						name="title" value="<%= entry.getTitle(locale) %>" />

					<liferay-ui:search-container-column-user name="author"
						userId="${entry.userId}" />

					<liferay-ui:search-container-column-date name="create-date"
						property="createDate" />

					<liferay-ui:search-container-column-jsp name="actions"
						path="/entry_actions.jsp" />
				</c:otherwise>
			</c:choose>

		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="${taskManagementToolbarDisplayContext.getDisplayStyle()}"
			markupView="lexicon" />
	</liferay-ui:search-container>
</div>