package com.maksimov.controllers.delegators.impl;

import com.maksimov.controllers.delegators.Processor;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.services.EmployeeService;
import com.maksimov.services.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 21.07.16.
 */
public class EmployeeDelete implements Processor {

    private EmployeeService service = new EmployeeServiceImpl();

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, DepartmentException {
        service.delete(Long.parseLong(req.getParameter(PARAM_ID)));
        res.sendRedirect(MAIN_EMPLOYEE_URL.replace("{id}", req.getParameter(ATTR_DEPARTMENT)));
    }
}
