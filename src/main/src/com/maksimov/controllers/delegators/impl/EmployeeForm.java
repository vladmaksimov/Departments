package com.maksimov.controllers.delegators.impl;

import com.maksimov.controllers.delegators.Processor;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Employee;
import com.maksimov.services.EmployeeService;
import com.maksimov.services.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 21.07.16.
 */
public class EmployeeForm implements Processor {

    private EmployeeService service = new EmployeeServiceImpl();

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, DepartmentException {
        String department = req.getParameter(ATTR_DEPARTMENT);
        String idToEdit = req.getParameter(PARAM_ID);
        if (idToEdit != null) {
            Long id = Long.parseLong(idToEdit);
            Employee employee = service.getById(id);
            req.setAttribute(ATTR_EMPLOYEE, employee);
            req.setAttribute(ATTR_DEPARTMENT, employee.getDepartment());
            req.getRequestDispatcher(SHOW_EMPLOYEE_FORM).forward(req, res);
        }
        req.setAttribute(ATTR_DEPARTMENT, department);
        req.getRequestDispatcher(SHOW_EMPLOYEE_FORM).forward(req, res);
    }
}
