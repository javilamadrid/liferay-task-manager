package com.javilamadrid.taskmanager.portlet.commands;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.javilamadrid.taskmanager.constants.TaskManagerPortletKeys;
import com.javilamadrid.taskmanager.service.TaskService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;

//@formatter:off
@Component(
    immediate = true,
    property = {
        "javax.portlet.name=" + TaskManagerPortletKeys.TASKMANAGERPORTLET,
        "mvc.command.name=" + TaskManagerPortletKeys.TASK_EDIT
    },
    service = MVCActionCommand.class
)
//@formatter:on
public class EditTaskMVCActionCommand extends BaseMVCActionCommand {

    private static final Log log = LogFactoryUtil.getLog(EditTaskMVCActionCommand.class);
    
    @Reference
    protected TaskService taskService;

    @Override
    protected void doProcessAction(final ActionRequest actionRequest, final ActionResponse actionResponse)
            throws Exception {

        final long taskId = ParamUtil.getLong(actionRequest, "taskId");
        final Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(actionRequest, "title");
        final Map<Locale, String> descriptionMap = LocalizationUtil.getLocalizationMap(actionRequest, "description");
        final Date dueDate = ParamUtil.getDate(actionRequest, "dueDate",
                DateFormatFactoryUtil.getDate(actionRequest.getLocale()));
        final int priority = ParamUtil.getInteger(actionRequest, "priority");

        try {
            this.taskService.updateTask(taskId, titleMap, descriptionMap, dueDate, priority);
            this.sendRedirect(actionRequest, actionResponse);
        } catch (final PortalException pe) {
            log.error(pe);
            actionResponse.setRenderParameter(TaskManagerPortletKeys.MVC_RENDER_COMMAND_NAME, TaskManagerPortletKeys.TASK_EDIT);
        }
    }

}
