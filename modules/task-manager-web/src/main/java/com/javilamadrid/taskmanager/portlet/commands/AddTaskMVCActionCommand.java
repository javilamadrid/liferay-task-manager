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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.workflow.kaleo.definition.Task;

//@formatter:off
@Component(
    immediate = true,
    property = {
        "javax.portlet.name=" + TaskManagerPortletKeys.TASKMANAGERPORTLET,
        "mvc.command.name=" + TaskManagerPortletKeys.TASK_ADD
    },
    service = MVCActionCommand.class
)
//@formatter:on
public class AddTaskMVCActionCommand extends BaseMVCActionCommand {

    private static final Log log = LogFactoryUtil.getLog(AddTaskMVCActionCommand.class);

    @Reference
    private TaskService taskService;

    @Override
    protected void doProcessAction(final ActionRequest actionRequest, final ActionResponse actionResponse)
            throws Exception {

        final ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        final ServiceContext serviceContext = ServiceContextFactory.getInstance(Task.class.getName(), actionRequest);
        final Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(actionRequest, "title");
        final Map<Locale, String> descriptionMap = LocalizationUtil.getLocalizationMap(actionRequest, "description");
        final Date deadline = ParamUtil.getDate(actionRequest, "deadline",
                DateFormatFactoryUtil.getDate(actionRequest.getLocale()));
        final int priority = ParamUtil.getInteger(actionRequest, "priority");

        try {
            this.taskService.addTask(themeDisplay.getScopeGroupId(), titleMap, descriptionMap, deadline, priority,
                    serviceContext);
            this.sendRedirect(actionRequest, actionResponse);
        } catch (final PortalException pe) {
            log.error(pe);
            actionResponse.setRenderParameter("mvcRenderCommandName", TaskManagerPortletKeys.TASK_EDIT);
        }
    }

}
