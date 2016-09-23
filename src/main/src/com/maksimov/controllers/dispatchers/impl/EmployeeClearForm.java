package com.maksimov.controllers.dispatchers.impl;

import com.maksimov.controllers.dispatchers.Dispatcher;
import com.maksimov.exceptions.ServiceException;
import com.maksimov.models.Department;
import com.maksimov.services.DepartmentService;
import com.maksimov.utils.factorys.ModelFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created on 8/15/2016.
 */
@Component("/department/employee/form/clear")
public class EmployeeClearForm implements Dispatcher {

    @Autowired
    private DepartmentService service;

    @Override
    public void doDispatch(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, ServiceException {
        List<Department> departmentList = service.getAll();
        req.setAttribute(ATTR_DEPARTMENTS, departmentList);
        req.setAttribute(ATTR_EMPLOYEE, ModelFactory.createEmployee());
        req.setAttribute(ATTR_ERRORS, Collections.emptyMap());
        req.getRequestDispatcher(SHOW_EMPLOYEE_FORM).forward(req, res);
    }
}
