package com.maksimov.controllers;

import com.maksimov.controllers.dispatchers.Dispatcher;
import com.maksimov.controllers.dispatchers.impl.*;
import com.maksimov.exceptions.DepartmentException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 7/19/2016.
 */
public class Controller extends HttpServlet {

    private static final String DEFAULT_ACTION = "/";
    private static final String SHOW_ERROR = "/assets/jsp/error.jsp";


    private static final Map<String, Dispatcher> ACTIONS = new HashMap<String, Dispatcher>() {{
        put("/", new DepartmentsShow());
        put("/department/put", new DepartmentPut());
        put("/department/form", new DepartmentForm());
        put("/department/delete", new DepartmentDelete());
        put("/department/employees", new EmployeesShow());
        put("/department/employee/form", new EmployeeForm());
        put("/department/employee/put", new EmployeePut());
        put("/department/employee/delete", new EmployeeDelete());
    }};

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getRequestURI();

        if (!ACTIONS.containsKey(action)) {
            action = DEFAULT_ACTION;
        }
        Dispatcher dispatcher = ACTIONS.get(action);
        try {
            dispatcher.doDispatch(req, resp);
        } catch (DepartmentException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher(SHOW_ERROR).forward(req, resp);
        }
    }
}
