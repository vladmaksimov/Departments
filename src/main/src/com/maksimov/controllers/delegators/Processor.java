package com.maksimov.controllers.delegators;

import com.maksimov.exceptions.DepartmentException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 7/19/2016.
 */
public interface Processor {

    String PARAM_ID = "id";

    String PARAM_DEP_NAME = "name";

    String PARAM_EMP_NAME = "name";
    String PARAM_EMP_EMAIL = "email";
    String PARAM_EMP_BIRTHDAY = "birthday";
    String PARAM_EMP_DEPARTMENT = "department";

    String MAIN_URL = "/";
    String MAIN_EMPLOYEE_URL = "/department/employees?id={id}";
    String SHOW_DEPARTMENTS = "/assets/jsp/table.departments.jsp";
    String SHOW_DEPARTMENT_FORM = "/assets/jsp/form.department.jsp";
    String SHOW_EMPLOYEES = "/assets/jsp/table.employees.jsp";
    String SHOW_EMPLOYEE_FORM = "/assets/jsp/form.employee.jsp";

    String ATTR_DEPARTMENT = "department";
    String ATTR_DEPARTMENTS = "departments";
    String ATTR_EMPLOYEES = "employees";
    String ATTR_EMPLOYEE = "employee";
    String ATTR_ERRORS = "errors";

    void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, DepartmentException;

}
