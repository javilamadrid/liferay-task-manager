package com.javilamadrid.taskmanager.portlet;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;

import com.javilamadrid.taskmanager.constants.TaskManagerPortletKeys;
import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.BaseManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItemList;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

public class TasksManagementToolbarDisplayContext extends BaseManagementToolbarDisplayContext {

    private static final String TITLE = "title";
    private static final String ORDER_BY_TYPE = "orderByType";
    private static final String ORDER_BY_COL = "orderByCol";
    
    private final PortalPreferences portalPreferences;

    public TasksManagementToolbarDisplayContext(final LiferayPortletRequest liferayPortletRequest,
            final LiferayPortletResponse liferayPortletResponse, final HttpServletRequest httpServletRequest) {

        super(httpServletRequest, liferayPortletRequest, liferayPortletResponse);

        this.portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(liferayPortletRequest);
    }

    @Override
    public CreationMenu getCreationMenu() {

        return new CreationMenu() {
            private static final long serialVersionUID = 7264196690617604511L;
            {
                this.addDropdownItem(dropdownItem -> {
                    dropdownItem.setHref(
                            TasksManagementToolbarDisplayContext.this.liferayPortletResponse.createRenderURL(),
                            TaskManagerPortletKeys.MVC_RENDER_COMMAND_NAME, TaskManagerPortletKeys.TASK_EDIT,
                            "redirect", TasksManagementToolbarDisplayContext.this.currentURLObj.toString());
                    dropdownItem.setLabel(
                            LanguageUtil.get(TasksManagementToolbarDisplayContext.this.httpServletRequest, "add-task"));
                });
            }
        };
    }

    @Override
    public String getClearResultsURL() {
        return this.getSearchActionURL();
    }

    @Override
    public String getDisplayStyle() {

        String displayStyle = ParamUtil.getString(this.httpServletRequest, "displayStyle");

        if (Validator.isNull(displayStyle)) {
            displayStyle = this.portalPreferences.getValue(TaskManagerPortletKeys.TASKMANAGERPORTLET,
                    "tasks-display-style", "descriptive");
        } else {
            this.portalPreferences.setValue(TaskManagerPortletKeys.TASKMANAGERPORTLET, "tasks-display-style",
                    displayStyle);

            this.httpServletRequest.setAttribute(WebKeys.SINGLE_PAGE_APPLICATION_CLEAR_CACHE, Boolean.TRUE);
        }

        return displayStyle;
    }

    @Override
    public String getOrderByCol() {

        return ParamUtil.getString(this.httpServletRequest, ORDER_BY_COL, TITLE);
    }

    @Override
    public String getOrderByType() {

        return ParamUtil.getString(this.httpServletRequest, ORDER_BY_TYPE, "asc");
    }

    @Override
    public String getSearchActionURL() {

        final PortletURL searchURL = this.liferayPortletResponse.createRenderURL();

        searchURL.setProperty(TaskManagerPortletKeys.MVC_RENDER_COMMAND_NAME, TaskManagerPortletKeys.TASKS_VIEW);

        final String navigation = ParamUtil.getString(this.httpServletRequest, "navigation", "entries");
        searchURL.setParameter("navigation", navigation);
        searchURL.setParameter(ORDER_BY_COL, this.getOrderByCol());
        searchURL.setParameter(ORDER_BY_TYPE, this.getOrderByType());

        return searchURL.toString();
    }

    @Override
    public List<ViewTypeItem> getViewTypeItems() {
        final PortletURL portletURL = this.liferayPortletResponse.createRenderURL();

        portletURL.setParameter(TaskManagerPortletKeys.MVC_RENDER_COMMAND_NAME, TaskManagerPortletKeys.TASKS_VIEW);

        final int delta = ParamUtil.getInteger(this.httpServletRequest, SearchContainer.DEFAULT_DELTA_PARAM);

        if (delta > 0) {
            portletURL.setParameter("delta", String.valueOf(delta));
        }

        final String orderByCol = ParamUtil.getString(this.httpServletRequest, ORDER_BY_COL, TITLE);
        final String orderByType = ParamUtil.getString(this.httpServletRequest, ORDER_BY_TYPE, "asc");

        portletURL.setParameter(ORDER_BY_COL, orderByCol);
        portletURL.setParameter(ORDER_BY_TYPE, orderByType);

        final int cur = ParamUtil.getInteger(this.httpServletRequest, SearchContainer.DEFAULT_CUR_PARAM);

        if (cur > 0) {
            portletURL.setParameter("cur", String.valueOf(cur));
        }

        return new ViewTypeItemList(portletURL, this.getDisplayStyle()) {
            private static final long serialVersionUID = -7968731532723799521L;

            {
                this.addCardViewTypeItem();
                this.addListViewTypeItem();
                this.addTableViewTypeItem();
            }
        };
    }

    @Override
    protected List<DropdownItem> getOrderByDropdownItems() {
        return new DropdownItemList() {
            private static final long serialVersionUID = -1980786483248182667L;

            {
                this.add(dropdownItem -> {
                    dropdownItem.setActive(TITLE.equals(TasksManagementToolbarDisplayContext.this.getOrderByCol()));
                    dropdownItem.setHref(TasksManagementToolbarDisplayContext.this.getCurrentSortingURL(), ORDER_BY_COL,
                            TITLE);
                    dropdownItem.setLabel(
                            LanguageUtil.get(TasksManagementToolbarDisplayContext.this.httpServletRequest, TITLE));
                });

                this.add(dropdownItem -> {
                    dropdownItem
                            .setActive("createDate".equals(TasksManagementToolbarDisplayContext.this.getOrderByCol()));
                    dropdownItem.setHref(TasksManagementToolbarDisplayContext.this.getCurrentSortingURL(), ORDER_BY_COL,
                            "createDate");
                    dropdownItem.setLabel(LanguageUtil.get(TasksManagementToolbarDisplayContext.this.httpServletRequest,
                            "create-date"));
                });
            }
        };
    }

    private PortletURL getCurrentSortingURL() throws PortletException {

        final PortletURL sortingURL = PortletURLUtil.clone(this.currentURLObj, this.liferayPortletResponse);
        sortingURL.setParameter(TaskManagerPortletKeys.MVC_RENDER_COMMAND_NAME, TaskManagerPortletKeys.TASKS_VIEW);
        sortingURL.setParameter(SearchContainer.DEFAULT_CUR_PARAM, "0");

        final String keywords = ParamUtil.getString(this.httpServletRequest, "keywords");

        if (Validator.isNotNull(keywords)) {
            sortingURL.setParameter("keywords", keywords);
        }

        return sortingURL;
    }

}
