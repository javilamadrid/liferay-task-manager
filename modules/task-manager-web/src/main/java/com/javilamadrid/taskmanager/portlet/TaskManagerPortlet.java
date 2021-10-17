package com.javilamadrid.taskmanager.portlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

import com.javilamadrid.taskmanager.constants.TaskManagerPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

/**
 * @author javilamadrid
 */
//@formatter:off
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.header-portlet-css=/css/main.css",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=TaskManagerPortlet",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + TaskManagerPortletKeys.TASKMANAGERPORTLET,
                "javax.portlet.resource-bundle=content.Language",
            "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
//@formatter:on
public class TaskManagerPortlet extends MVCPortlet {

}
