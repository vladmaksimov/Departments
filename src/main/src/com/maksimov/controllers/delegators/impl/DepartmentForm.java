package com.maksimov.controllers.delegators.impl;

import com.maksimov.controllers.delegators.Processor;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.services.DepartmentService;
import com.maksimov.services.impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 7/20/2016.
 */
public class DepartmentForm implements Processor {

    private DepartmentService service = new DepartmentServiceImpl();

    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, DepartmentException {
        String idToEdit = req.getParameter(PARAM_ID);
        if (idToEdit != null) {
            Long id = Long.parseLong(idToEdit);
            req.setAttribute(ATTR_DEPARTMENT, service.getById(id));
            req.getRequestDispatcher(SHOW_DEPARTMENT_FORM).forward(req, res);
        }
        res.sendRedirect(SHOW_DEPARTMENT_FORM);
    }
}
