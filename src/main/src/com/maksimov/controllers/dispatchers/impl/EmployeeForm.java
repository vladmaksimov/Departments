package com.maksimov.controllers.dispatchers.impl;

import com.maksimov.controllers.dispatchers.Dispatcher;
import com.maksimov.exceptions.DispatcherException;
import com.maksimov.exceptions.ServiceException;
import com.maksimov.models.Department;
import com.maksimov.models.Employee;
import com.maksimov.services.DepartmentService;
import com.maksimov.services.EmployeeService;
import com.maksimov.utils.Utils;
import com.maksimov.utils.factorys.ModelFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.maksimov.constants.EmployeeConstants.ID;

/**
 * Created on 21.07.16.
 */
public class EmployeeForm implements Dispatcher {

    private EmployeeService service;
    private DepartmentService departmentService;

    @Override
    public void doDispatch(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, ServiceException, DispatcherException {
        Long department = Utils.parseLong(req.getParameter(ATTR_DEPARTMENT));
        if (department == null) {
            throw new DispatcherException("Can't parse department id!");
        }

        String idToEdit = req.getParameter(ID);
        if (idToEdit != null) {
            Long id = Utils.parseLong(idToEdit);
            if (id == null) {
                throw new DispatcherException("Can't parse employee id!");
            }
            Employee employee = service.getById(id);
            req.setAttribute(ATTR_EMPLOYEE, employee);
        } else {
            req.setAttribute(ATTR_EMPLOYEE, ModelFactory.createEmployee());
        }

        req.setAttribute(ATTR_ERRORS, Collections.emptyMap());

        List<Department> departments = new ArrayList<>();
        departments.add(departmentService.getById(department));

        req.setAttribute(ATTR_DEPARTMENTS, departments);
        req.getRequestDispatcher(SHOW_EMPLOYEE_FORM).forward(req, res);
    }

    public void setService(EmployeeService service) {
        this.service = service;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
}
