package com.maksimov.controllers.dispatchers.impl;

import com.maksimov.controllers.dispatchers.Dispatcher;
import com.maksimov.exceptions.CustomValidateException;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Department;
import com.maksimov.models.Employee;
import com.maksimov.services.DepartmentService;
import com.maksimov.services.EmployeeService;
import com.maksimov.transformers.EmployeeRequestTransformerImpl;
import com.maksimov.transformers.RequestTransformer;
import com.maksimov.utils.factorys.ServiceBeanFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 21.07.16.
 */
public class EmployeePut implements Dispatcher {

    private EmployeeService service = ServiceBeanFactory.getEmployeeService();
    private DepartmentService departmentService = ServiceBeanFactory.getDepartmentService();
    private RequestTransformer<Employee> transformer = new EmployeeRequestTransformerImpl();

    @Override
    public void doDispatch(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, DepartmentException {
        Employee employee = transformer.transform(req);
        try {
            service.put(employee);
        } catch (CustomValidateException e) {
            req.setAttribute(ATTR_EMPLOYEE, employee);
            List<Department> departments = new ArrayList<>();
            departments.add(departmentService.getById(employee.getDepartment()));
            req.setAttribute(ATTR_DEPARTMENTS, departments);
            req.setAttribute(ATTR_ERRORS, e.getErrors());
            req.getRequestDispatcher(SHOW_EMPLOYEE_FORM).forward(req, res);
        }
        res.sendRedirect(MAIN_EMPLOYEE_URL.replace("{id}", employee.getDepartment().toString()));
    }
}
