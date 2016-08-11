package com.maksimov.controllers.dispatchers.impl;

import com.maksimov.controllers.dispatchers.Dispatcher;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Employee;
import com.maksimov.services.EmployeeService;
import com.maksimov.utils.BeanFactory;
import com.maksimov.utils.ModelFactory;
import com.maksimov.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static com.maksimov.constants.EmployeeConstants.ID;

/**
 * Created on 21.07.16.
 */
public class EmployeeForm implements Dispatcher {

    private EmployeeService service = BeanFactory.getEmployeeService();

    @Override
    public void doDispatch(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, DepartmentException {
        Long department = Utils.parseLong(req.getParameter(ATTR_DEPARTMENT));
        if (department == null) {
            throw new DepartmentException("Can't parse department id!");
        }

        String idToEdit = req.getParameter(ID);
        if (idToEdit != null) {
            Long id = Utils.parseLong(idToEdit);
            if (id == null) {
                throw new DepartmentException("Can't parse employee id!");
            }
            Employee employee = service.getById(id);
            req.setAttribute(ATTR_EMPLOYEE, employee);
        } else {
            req.setAttribute(ATTR_EMPLOYEE, ModelFactory.createEmployee());
        }

        req.setAttribute(ATTR_ERRORS, Collections.emptyMap());
        req.setAttribute(ATTR_DEPARTMENT, department);
        req.getRequestDispatcher(SHOW_EMPLOYEE_FORM).forward(req, res);
    }
}
