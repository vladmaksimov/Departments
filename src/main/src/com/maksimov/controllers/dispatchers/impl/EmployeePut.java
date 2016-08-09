package com.maksimov.controllers.dispatchers.impl;

import com.maksimov.controllers.dispatchers.Dispatcher;
import com.maksimov.exceptions.CustomValidateException;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Employee;
import com.maksimov.services.EmployeeService;
import com.maksimov.transformers.EmployeeRequestTransformerImpl;
import com.maksimov.transformers.RequestTransformer;
import com.maksimov.utils.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 21.07.16.
 */
public class EmployeePut implements Dispatcher {

    private EmployeeService service = BeanFactory.getEmployeeService();
    private RequestTransformer transformer = new EmployeeRequestTransformerImpl();

    @Override
    public void doDispatch(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, DepartmentException {
        Employee employee = (Employee) transformer.transform(req);
        try {
            service.put(employee);
        } catch (CustomValidateException e) {
            req.setAttribute(ATTR_EMPLOYEE, employee);
            req.setAttribute(ATTR_DEPARTMENT, employee.getDepartment());
            req.setAttribute(ATTR_ERRORS, e.getErrors());
            req.getRequestDispatcher(SHOW_EMPLOYEE_FORM).forward(req, res);
        }
        res.sendRedirect(MAIN_EMPLOYEE_URL.replace("{id}", employee.getDepartment().toString()));
    }
}
