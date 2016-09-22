package com.maksimov.controllers;

import com.maksimov.controllers.dispatchers.Dispatcher;
import com.maksimov.exceptions.DispatcherException;
import com.maksimov.exceptions.ServiceException;
import org.springframework.web.HttpRequestHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created on 7/19/2016.
 */
@org.springframework.stereotype.Controller
public class Controller implements HttpRequestHandler {

    private static final String PROBLEM = "/problem";
    private static final String SHOW_ERROR = "/assets/jsp/error/error.jsp";
    private static final String ATTR_ERROR = "error";
    private static final String ERROR_SYSTEM_MESSAGE = "System error!";

    private Map<String, Dispatcher> dispatcherMap;

    @Override
    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getRequestURI();

        Dispatcher dispatcher = dispatcherMap.get(action);
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

    @Resource(name = "dispatcherMap")
    public void setDispatcherMap(Map<String, Dispatcher> dispatcherMap) {
        this.dispatcherMap = dispatcherMap;
    }
}
