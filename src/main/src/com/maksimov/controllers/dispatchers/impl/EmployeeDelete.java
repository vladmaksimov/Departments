package com.maksimov.controllers.dispatchers.impl;

import com.maksimov.controllers.dispatchers.Dispatcher;
import com.maksimov.exceptions.DispatcherException;
import com.maksimov.exceptions.ServiceException;
import com.maksimov.services.EmployeeService;
import com.maksimov.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.maksimov.constants.EmployeeConstants.ID;

/**
 * Created on 21.07.16.
 */
@Component
public class EmployeeDelete implements Dispatcher {

    @Autowired
    private EmployeeService service;

    @Override
    public void doDispatch(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, ServiceException, DispatcherException {
        Long id = Utils.parseLong(req.getParameter(ID));
        if (id == null) {
            throw new DispatcherException("Can't delete department object. Id must be not null!");
        }

        service.delete(id);
        res.sendRedirect(MAIN_EMPLOYEE_URL.replace("{id}", req.getParameter(ATTR_DEPARTMENT)));
    }

    public void setService(EmployeeService service) {
        this.service = service;
    }
}
