package com.javilamadrid.taskmanager.portlet.commands;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.javilamadrid.taskmanager.constants.TaskManagerPortletKeys;
import com.javilamadrid.taskmanager.model.Task;
import com.javilamadrid.taskmanager.portlet.TasksManagementToolbarDisplayContext;
import com.javilamadrid.taskmanager.service.TaskService;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

//@formatter:off
@Component(
    immediate = true,
    property = {
        "javax.portlet.name=" + TaskManagerPortletKeys.TASKMANAGERPORTLET,
        "mvc.command.name=/",
        "mvc.command.name=" + TaskManagerPortletKeys.TASKS_VIEW
    },
    service = MVCRenderCommand.class
)
//@formatter:on
public class ViewTasksMVCRenderCommand implements MVCRenderCommand {

    @Reference
    private TaskService taskService;

    @Reference
    private Portal portal;

    @Override
    public String render(final RenderRequest renderRequest, final RenderResponse renderResponse)
            throws PortletException {

        this.addTaskListAttributes(renderRequest);
        this.addManagementToolbarAttributes(renderRequest, renderResponse);

        return "/view.jsp";
    }

    private void addTaskListAttributes(final RenderRequest renderRequest) {

        final ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

        final int currentPage = ParamUtil.getInteger(renderRequest, SearchContainer.DEFAULT_CUR_PARAM,
                SearchContainer.DEFAULT_CUR);

        final int delta = ParamUtil.getInteger(renderRequest, SearchContainer.DEFAULT_DELTA_PARAM,
                SearchContainer.DEFAULT_DELTA);

        final int start = ((currentPage > 0) ? (currentPage - 1) : 0) * delta;
        final int end = start + delta;

        final String orderByCol = ParamUtil.getString(renderRequest, "orderByCol", "title");
        final String orderByType = ParamUtil.getString(renderRequest, "orderByType", "asc");

        final OrderByComparator<Task> comparator = OrderByComparatorFactoryUtil.create("Task", orderByCol,
                !("asc").equals(orderByType));

        final String keywords = ParamUtil.getString(renderRequest, "keywords");

        renderRequest.setAttribute("tasks",
                this.taskService.getTasksByKeywords(themeDisplay.getScopeGroupId(), keywords, start, end, comparator));
        renderRequest.setAttribute("taskCount",
                this.taskService.getTasksCountByKeywords(themeDisplay.getScopeGroupId(), keywords));

    }

    private void addManagementToolbarAttributes(final RenderRequest renderRequest,
            final RenderResponse renderResponse) {

        final LiferayPortletRequest liferayPortletRequest = this.portal.getLiferayPortletRequest(renderRequest);

        final LiferayPortletResponse liferayPortletResponse = this.portal.getLiferayPortletResponse(renderResponse);

        final TasksManagementToolbarDisplayContext taskManagementToolbarDisplayContext = new TasksManagementToolbarDisplayContext(
                liferayPortletRequest, liferayPortletResponse, this.portal.getHttpServletRequest(renderRequest));

        renderRequest.setAttribute("taskManagementToolbarDisplayContext", taskManagementToolbarDisplayContext);

    }
}
