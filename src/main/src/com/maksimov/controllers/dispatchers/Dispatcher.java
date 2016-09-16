package com.maksimov.controllers.dispatchers;

import com.maksimov.exceptions.DepartmentException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 7/19/2016.
 */
public interface Dispatcher {

    String PARAM_ID = "id";

    String MAIN_URL = "/";
    String MAIN_EMPLOYEE_URL = "/department/employees?id={id}";
    String SHOW_DEPARTMENTS = "/assets/jsp/table.departments.jsp";
    String SHOW_DEPARTMENT_FORM = "/assets/jsp/form.department.jsp";
    String SHOW_EMPLOYEES = "/assets/jsp/table.employees.jsp";
    String SHOW_EMPLOYEE_FORM = "/assets/jsp/form.employee.jsp";
    String PROBLEM = "/assets/jsp/problem.jsp";

    String ATTR_DEPARTMENT = "department";
    String ATTR_DEPARTMENTS = "departments";
    String ATTR_EMPLOYEES = "employees";
    String ATTR_EMPLOYEE = "employee";
    String ATTR_ERRORS = "errors";

    String ATTR_PAGE = "requestPage";
    String ATTR_SIZE_LIST = "sizeList";
    String ATTR_SORT_LIST = "sortList";

    /**
     * Receives standard HTTP requests from the public controller and delegate it to different implementation.
     *
     * @param req the {@link HttpServletRequest} object that contains the request the client made of the servlet
     * @param res the {@link HttpServletResponse} object that contains the response the servlet returns to the client
     *
     * @throws IOException
     * @throws ServletException
     * @throws DepartmentException related with operating with database
     */
    void doDispatch(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, DepartmentException;

}
