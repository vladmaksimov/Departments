package com.maksimov.controllers.delegators.impl;

import com.maksimov.controllers.delegators.Processor;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Employee;
import com.maksimov.services.EmployeeService;
import com.maksimov.services.impl.EmployeeServiceImpl;
import com.maksimov.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created on 21.07.16.
 */
public class EmployeesShow implements Processor {

    private EmployeeService service = new EmployeeServiceImpl();

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, DepartmentException {
        String id = req.getParameter(PARAM_ID);
        List<Employee> employeeList = service.getByDepartmentId(Utils.parseLong(id));
        req.setAttribute(ATTR_EMPLOYEES, employeeList);
        req.setAttribute(ATTR_DEPARTMENT, id);
        req.getRequestDispatcher(SHOW_EMPLOYEES).forward(req, res);
    }
}
