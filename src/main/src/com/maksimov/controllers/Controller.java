package com.maksimov.controllers;

import com.maksimov.controllers.dispatchers.Dispatcher;
import com.maksimov.controllers.dispatchers.impl.*;
import com.maksimov.exceptions.DispatcherException;
import com.maksimov.exceptions.ServiceException;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 7/19/2016.
 */
public class Controller implements HttpRequestHandler {

    private static final String PROBLEM = "/problem";
    private static final String SHOW_ERROR = "/assets/jsp/error/error.jsp";
    private static final String ATTR_ERROR = "error";
    private static final String ERROR_SYSTEM_MESSAGE = "System error!";

    private static final Map<String, Dispatcher> ACTIONS = new HashMap<String, Dispatcher>() {{
        put("/", new DepartmentsShow());
        put("/department/put", new DepartmentPut());
        put("/department/form", new DepartmentForm());
        put("/department/delete", new DepartmentDelete());
        put("/department/employees", new EmployeesShow());
        put("/department/employee/form", new EmployeeForm());
        put("/department/employee/form/clear", new EmployeeClearForm());
        put("/department/employee/put", new EmployeePut());
        put("/department/employee/delete", new EmployeeDelete());
        put("/problem", new ProblemPage());
    }};

    @Override
    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getRequestURI();

        Dispatcher dispatcher = ACTIONS.get(action);
        if (dispatcher != null) {
            try {
                dispatcher.doDispatch(req, resp);
            } catch (ServiceException | DispatcherException e) {
                req.setAttribute(ATTR_ERROR, e.getMessage());
                req.getRequestDispatcher(SHOW_ERROR).forward(req, resp);
            } catch (Error e) {
                req.setAttribute(ATTR_ERROR, ERROR_SYSTEM_MESSAGE);
                req.getRequestDispatcher(SHOW_ERROR).forward(req, resp);
            }
        } else {
            resp.sendRedirect(PROBLEM);
        }
    }
}
