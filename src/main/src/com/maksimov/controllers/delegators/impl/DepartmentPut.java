package com.maksimov.controllers.delegators.impl;

import com.maksimov.controllers.delegators.Processor;
import com.maksimov.exceptions.CustomValidateException;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Department;
import com.maksimov.services.DepartmentService;
import com.maksimov.services.impl.DepartmentServiceImpl;
import com.maksimov.transformers.DepartmentRequestTransformerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 7/20/2016.
 */
public class DepartmentPut implements Processor {

    private DepartmentService service = new DepartmentServiceImpl();
    private DepartmentRequestTransformerImpl transformer = new DepartmentRequestTransformerImpl();

    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, DepartmentException {
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
}
