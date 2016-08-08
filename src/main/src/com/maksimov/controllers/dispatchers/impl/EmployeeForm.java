package com.maksimov.controllers.dispatchers.impl;

import com.maksimov.controllers.dispatchers.Dispatcher;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Employee;
import com.maksimov.services.EmployeeService;
import com.maksimov.services.impl.EmployeeServiceImpl;
import com.maksimov.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.maksimov.constants.EmployeeConstants.ID;

/**
 * Created on 21.07.16.
 */
public class EmployeeForm implements Dispatcher {

    private EmployeeService service = new EmployeeServiceImpl();

    @Override
    public void doDispatch(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, DepartmentException {
        String department = req.getParameter(ATTR_DEPARTMENT);
        String idToEdit = req.getParameter(ID);
        if (idToEdit != null) {
            Long id = Utils.parseLong(idToEdit);
            if (id == null) {
                throw new DepartmentException("Can't parse department id!");
            }
            Employee employee = service.getById(id);
            req.setAttribute(ATTR_EMPLOYEE, employee);
            req.setAttribute(ATTR_DEPARTMENT, employee.getDepartment());
            req.getRequestDispatcher(SHOW_EMPLOYEE_FORM).forward(req, res);
        }
        req.setAttribute(ATTR_DEPARTMENT, department);
        req.getRequestDispatcher(SHOW_EMPLOYEE_FORM).forward(req, res);
    }
}
