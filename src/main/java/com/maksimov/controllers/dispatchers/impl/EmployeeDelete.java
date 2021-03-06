package com.maksimov.controllers.dispatchers.impl;

import com.maksimov.controllers.dispatchers.Dispatcher;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.services.EmployeeService;
import com.maksimov.utils.factorys.ServiceBeanFactory;
import com.maksimov.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.maksimov.constants.EmployeeConstants.ID;

/**
 * Created on 21.07.16.
 */
public class EmployeeDelete implements Dispatcher {

    private EmployeeService service = ServiceBeanFactory.getEmployeeService();

    @Override
    public void doDispatch(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, DepartmentException {
        Long id = Utils.parseLong(req.getParameter(ID));
        if (id == null) {
            throw new DepartmentException("Can't delete department object. Id must be not null!");
        }

        service.delete(id);
        res.sendRedirect(MAIN_EMPLOYEE_URL.replace("{id}", req.getParameter(ATTR_DEPARTMENT)));
    }
}
