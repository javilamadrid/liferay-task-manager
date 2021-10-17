package com.javilamadrid.taskmanager.portlet.commands;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.javilamadrid.taskmanager.constants.TaskManagerPortletKeys;
import com.javilamadrid.taskmanager.exception.NoSuchTaskException;
import com.javilamadrid.taskmanager.model.Task;
import com.javilamadrid.taskmanager.service.TaskService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

//@formatter:off
@Component(
    immediate = true,
    property = {
        "javax.portlet.name=" + TaskManagerPortletKeys.TASKMANAGERPORTLET,
        "mvc.command.name=" + TaskManagerPortletKeys.TASK_EDIT
    },
    service = MVCRenderCommand.class
)
//@formatter:on
public class EditTaskMVCRenderCommand implements MVCRenderCommand {

    private static final Log log = LogFactoryUtil.getLog(EditTaskMVCRenderCommand.class);
    
    @Reference
    private TaskService taskService;

    @Override
    public String render(final RenderRequest renderRequest, final RenderResponse renderResponse)
            throws PortletException {

        Task task = null;

        final long taskId = ParamUtil.getLong(renderRequest, "taskId", 0);

        if (taskId > 0) {
            try {

                task = this.taskService.getTask(taskId);
            } catch (final NoSuchTaskException nsae) {
                log.error(nsae);
            } catch (final PortalException pe) {
                log.error(pe);
            }
        }

        final ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
        final PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
        portletDisplay.setShowBackIcon(true);
        final String redirect = renderRequest.getParameter("redirect");
        portletDisplay.setURLBack(redirect);
        renderRequest.setAttribute("task", task);
        renderRequest.setAttribute("taskClass", Task.class);

        return "/edit_task.jsp";
    }

}
