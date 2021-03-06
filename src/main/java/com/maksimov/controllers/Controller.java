package com.maksimov.controllers;

import com.maksimov.controllers.dispatchers.Dispatcher;
import com.maksimov.controllers.dispatchers.impl.*;
import com.maksimov.exceptions.DepartmentException;
import org.apache.log4j.Logger;

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

    private static final String PROBLEM = "/problem";
    private static final String SHOW_ERROR = "/assets/jsp/error/error.jsp";

    private static final Logger logger = Logger.getLogger(Controller.class);

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
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getRequestURI();

        if (logger.isDebugEnabled()) {
            logger.debug("URL to dispatch: " + action);
        }

        Dispatcher dispatcher = ACTIONS.get(action);
        if (dispatcher != null) {
            try {
                dispatcher.doDispatch(req, resp);
            } catch (DepartmentException e) {
                logger.error(e.getMessage());
                req.setAttribute("error", e.getMessage());
                req.getRequestDispatcher(SHOW_ERROR).forward(req, resp);
            }
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("Can't find dispatcher to URL: " + action);
            }
            resp.sendRedirect(PROBLEM);
        }
    }
}
