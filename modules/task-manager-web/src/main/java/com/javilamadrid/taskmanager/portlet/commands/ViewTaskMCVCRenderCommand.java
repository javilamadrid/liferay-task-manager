package com.javilamadrid.taskmanager.portlet.commands;

import java.text.DateFormat;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.javilamadrid.taskmanager.constants.TaskManagerPortletKeys;
import com.javilamadrid.taskmanager.model.Task;
import com.javilamadrid.taskmanager.service.TaskService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

//@formatter:off
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + TaskManagerPortletKeys.TASKMANAGERPORTLET,
            "mvc.command.name=" + TaskManagerPortletKeys.TASK_VIEW
        },
        service = MVCRenderCommand.class
)
//@formatter:on
public class ViewTaskMCVCRenderCommand implements MVCRenderCommand {

    @Reference
    private TaskService taskService;

    @Override
    public String render(final RenderRequest renderRequest, final RenderResponse renderResponse)
            throws PortletException {

        final ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

        final long taskId = ParamUtil.getLong(renderRequest, "taskId", 0);

        try {
            final Task task = this.taskService.getTask(taskId);

            final DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("EEEEE, MMMMM dd, yyyy",
                    renderRequest.getLocale());
            renderRequest.setAttribute("task", task);
            renderRequest.setAttribute("deadline", dateFormat.format(task.getDeadline()));
            renderRequest.setAttribute("createDate", dateFormat.format(task.getCreateDate()));

            final PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

            final String redirect = renderRequest.getParameter("redirect");

            portletDisplay.setShowBackIcon(true);
            portletDisplay.setURLBack(redirect);

            return "/view_task.jsp";

        } catch (final PortalException pe) {
            throw new PortletException(pe);
        }
    }

}
