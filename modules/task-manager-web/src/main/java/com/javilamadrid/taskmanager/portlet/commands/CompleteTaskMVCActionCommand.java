package com.javilamadrid.taskmanager.portlet.commands;

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
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.workflow.kaleo.definition.Task;

//@formatter:off
@Component(
  immediate = true,
  property = {
      "javax.portlet.name=" + TaskManagerPortletKeys.TASKMANAGERPORTLET,
      "mvc.command.name=" + TaskManagerPortletKeys.TASK_COMPLETE
  },
  service = MVCActionCommand.class
)
//@formatter:on
public class CompleteTaskMVCActionCommand extends BaseMVCActionCommand {

    private static final Log log = LogFactoryUtil.getLog(CompleteTaskMVCActionCommand.class);

    @Reference
    private TaskService taskService;

    @Override
    protected void doProcessAction(final ActionRequest actionRequest, final ActionResponse actionResponse)
            throws Exception {

        final ServiceContext serviceContext = ServiceContextFactory.getInstance(Task.class.getName(), actionRequest);
        final long taskId = ParamUtil.getLong(actionRequest, "taskId");

        try {
            this.taskService.completeTask(taskId, serviceContext);
        } catch (final PortalException pe) {
            log.error(pe);
        }
    }
}
