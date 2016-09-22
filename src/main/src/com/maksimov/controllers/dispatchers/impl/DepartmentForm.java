package com.maksimov.controllers.dispatchers.impl;

import com.maksimov.controllers.dispatchers.Dispatcher;
import com.maksimov.exceptions.DispatcherException;
import com.maksimov.exceptions.ServiceException;
import com.maksimov.services.DepartmentService;
import com.maksimov.utils.Utils;
import com.maksimov.utils.factorys.ModelFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * Created on 7/20/2016.
 */
public class DepartmentForm implements Dispatcher {

    private DepartmentService service;

    public void doDispatch(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, DispatcherException, ServiceException {
        String idToEdit = req.getParameter(PARAM_ID);
        if (idToEdit != null) {
            Long id = Utils.parseLong(idToEdit);
            if (id == null) {
                throw new DispatcherException("Can't parse department id!");
            }
            req.setAttribute(ATTR_DEPARTMENT, service.getById(id));
        } else {
            req.setAttribute(ATTR_DEPARTMENT, ModelFactory.createDepartment());
        }
        req.setAttribute(ATTR_ERRORS, Collections.emptyMap());
        req.getRequestDispatcher(SHOW_DEPARTMENT_FORM).forward(req, res);
    }

    public void setService(DepartmentService service) {
        this.service = service;
    }
}
