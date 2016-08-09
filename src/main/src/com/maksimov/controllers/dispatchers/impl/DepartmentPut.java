package com.maksimov.controllers.dispatchers.impl;

import com.maksimov.controllers.dispatchers.Dispatcher;
import com.maksimov.exceptions.CustomValidateException;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Department;
import com.maksimov.services.DepartmentService;
import com.maksimov.transformers.DepartmentRequestTransformerImpl;
import com.maksimov.transformers.RequestTransformer;
import com.maksimov.utils.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 7/20/2016.
 */
public class DepartmentPut implements Dispatcher {

    private DepartmentService service = BeanFactory.getDepartmentService();
    private RequestTransformer transformer = new DepartmentRequestTransformerImpl();

    public void doDispatch(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, DepartmentException {
        Department department = (Department) transformer.transform(req);
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
