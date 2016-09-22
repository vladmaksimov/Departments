package com.maksimov.controllers.dispatchers.impl;

import com.maksimov.controllers.dispatchers.Dispatcher;
import com.maksimov.exceptions.CustomValidateException;
import com.maksimov.exceptions.ServiceException;
import com.maksimov.models.Department;
import com.maksimov.services.DepartmentService;
import com.maksimov.transformers.RequestTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 7/20/2016.
 */
@Component
public class DepartmentPut implements Dispatcher {

    @Autowired
    private DepartmentService service;
    @Autowired
    private RequestTransformer<Department> transformer;

    public void doDispatch(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, ServiceException {
        Department department = transformer.transform(req);
        try {
            service.put(department);
        } catch (CustomValidateException e) {
            req.setAttribute(ATTR_DEPARTMENT, department);
            req.setAttribute(ATTR_ERRORS, e.getErrors());
            req.getRequestDispatcher(SHOW_DEPARTMENT_FORM).forward(req, res);
        }
        res.sendRedirect(MAIN_URL);
    }

    public void setService(DepartmentService service) {
        this.service = service;
    }

    public void setTransformer(RequestTransformer<Department> transformer) {
        this.transformer = transformer;
    }
}
