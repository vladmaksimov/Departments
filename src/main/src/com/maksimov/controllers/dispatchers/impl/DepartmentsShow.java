package com.maksimov.controllers.dispatchers.impl;

import com.maksimov.controllers.dispatchers.Dispatcher;
import com.maksimov.exceptions.ServiceException;
import com.maksimov.models.Department;
import com.maksimov.models.Page;
import com.maksimov.services.DepartmentService;
import com.maksimov.services.PageService;
import com.maksimov.transformers.RequestTransformer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.maksimov.constants.PageConstants.PAGE_DEPARTMENT_SORT_LIST;
import static com.maksimov.constants.PageConstants.PAGE_SIZE_LIST;

/**
 * Created on 7/19/2016.
 */
public class DepartmentsShow implements Dispatcher {

    private DepartmentService service;
    private PageService pageService;
    private RequestTransformer<Page> transformer;

    public void doDispatch(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, ServiceException {
        List<Department> departments;
        String search = req.getParameter(ATTR_SEARCH);

        Page page = transformer.transform(req);
        departments = search == null ? service.getDepartments(page) : service.searchDepartments(page, search);
        page = pageService.getDepartmentPageDetails(page, search);

        req.setAttribute(ATTR_DEPARTMENTS, departments);
        req.setAttribute(ATTR_PAGE, page);
        req.setAttribute(ATTR_SEARCH, search);
        req.setAttribute(ATTR_SIZE_LIST, PAGE_SIZE_LIST);
        req.setAttribute(ATTR_SORT_LIST, PAGE_DEPARTMENT_SORT_LIST);
        req.getRequestDispatcher(SHOW_DEPARTMENTS).forward(req, res);
    }

    public void setService(DepartmentService service) {
        this.service = service;
    }

    public void setPageService(PageService pageService) {
        this.pageService = pageService;
    }

    public void setTransformer(RequestTransformer<Page> transformer) {
        this.transformer = transformer;
    }
}
