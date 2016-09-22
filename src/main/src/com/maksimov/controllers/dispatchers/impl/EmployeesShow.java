package com.maksimov.controllers.dispatchers.impl;

import com.maksimov.controllers.dispatchers.Dispatcher;
import com.maksimov.exceptions.DispatcherException;
import com.maksimov.exceptions.ServiceException;
import com.maksimov.models.Employee;
import com.maksimov.models.Page;
import com.maksimov.services.DepartmentService;
import com.maksimov.services.EmployeeService;
import com.maksimov.services.PageService;
import com.maksimov.transformers.RequestTransformer;
import com.maksimov.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.maksimov.constants.PageConstants.PAGE_EMPLOYEE_SORT_LIST;
import static com.maksimov.constants.PageConstants.PAGE_SIZE_LIST;

/**
 * Created on 21.07.16.
 */
public class EmployeesShow implements Dispatcher {

    private EmployeeService service;
    private DepartmentService departmentService;
    private PageService pageService;
    private RequestTransformer<Page> transformer;

    @Override
    public void doDispatch(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, ServiceException, DispatcherException {
        String id = req.getParameter(PARAM_ID);
        String search = req.getParameter(ATTR_SEARCH);

        Long departmentId = Utils.parseLong(id);
        if (departmentId == null) {
            throw new DispatcherException("Can't parse department id!");
        }

        Page page = transformer.transform(req);
        List<Employee> employeeList = search == null ? service.getEmployeesWithPagination(page, departmentId) : service.searchEmployees(page, departmentId, search);
        page = pageService.getEmployeePageDetails(page, departmentId, search);

        req.setAttribute(ATTR_EMPLOYEES, employeeList);
        req.setAttribute(ATTR_PAGE, page);
        req.setAttribute(ATTR_SEARCH, search);
        req.setAttribute(ATTR_SIZE_LIST, PAGE_SIZE_LIST);
        req.setAttribute(ATTR_SORT_LIST, PAGE_EMPLOYEE_SORT_LIST);
        req.setAttribute(ATTR_DEPARTMENT, departmentService.getById(departmentId));
        req.getRequestDispatcher(SHOW_EMPLOYEES).forward(req, res);
    }

    public void setService(EmployeeService service) {
        this.service = service;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public void setPageService(PageService pageService) {
        this.pageService = pageService;
    }

    public void setTransformer(RequestTransformer<Page> transformer) {
        this.transformer = transformer;
    }
}
