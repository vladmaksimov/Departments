package com.maksimov.controllers.dispatchers.impl;

import com.maksimov.controllers.dispatchers.Dispatcher;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Department;
import com.maksimov.services.DepartmentService;
import com.maksimov.utils.factorys.ServiceBeanFactory;
import com.maksimov.utils.factorys.ModelFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created on 8/15/2016.
 */
public class EmployeeClearForm implements Dispatcher {

    private DepartmentService service = ServiceBeanFactory.getDepartmentService();

    @Override
    public void doDispatch(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, DepartmentException {
        List<Department> departmentList = service.getAll();
        req.setAttribute(ATTR_DEPARTMENTS, departmentList);
        req.setAttribute(ATTR_EMPLOYEE, ModelFactory.createEmployee());
        req.setAttribute(ATTR_ERRORS, Collections.emptyMap());
        req.getRequestDispatcher(SHOW_EMPLOYEE_FORM).forward(req, res);
    }
}
