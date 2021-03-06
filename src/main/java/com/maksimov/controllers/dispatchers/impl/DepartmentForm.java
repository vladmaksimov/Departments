package com.maksimov.controllers.dispatchers.impl;

import com.maksimov.controllers.dispatchers.Dispatcher;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.services.DepartmentService;
import com.maksimov.utils.factorys.ServiceBeanFactory;
import com.maksimov.utils.factorys.ModelFactory;
import com.maksimov.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * Created on 7/20/2016.
 */
public class DepartmentForm implements Dispatcher {

    private DepartmentService service = ServiceBeanFactory.getDepartmentService();

    public void doDispatch(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, DepartmentException {
        String idToEdit = req.getParameter(PARAM_ID);
        if (idToEdit != null) {
            Long id = Utils.parseLong(idToEdit);
            if (id == null) {
                throw new DepartmentException("Can't parse department id!");
            }
            req.setAttribute(ATTR_DEPARTMENT, service.getById(id));
        } else {
            req.setAttribute(ATTR_DEPARTMENT, ModelFactory.createDepartment());
        }
        req.setAttribute(ATTR_ERRORS, Collections.emptyMap());
        req.getRequestDispatcher(SHOW_DEPARTMENT_FORM).forward(req, res);
    }
}
