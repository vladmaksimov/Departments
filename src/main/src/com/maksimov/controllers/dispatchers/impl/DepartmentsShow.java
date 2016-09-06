package com.maksimov.controllers.dispatchers.impl;

import com.maksimov.controllers.dispatchers.Dispatcher;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Department;
import com.maksimov.services.DepartmentService;
import com.maksimov.utils.factorys.ServiceBeanFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created on 7/19/2016.
 */
public class DepartmentsShow implements Dispatcher {

    private DepartmentService service = ServiceBeanFactory.getDepartmentService();

    public void doDispatch(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, DepartmentException {
        List<Department> departments = service.getAll();
        req.setAttribute(ATTR_DEPARTMENTS, departments);
        req.getRequestDispatcher(SHOW_DEPARTMENTS).forward(req, res);
    }
}
