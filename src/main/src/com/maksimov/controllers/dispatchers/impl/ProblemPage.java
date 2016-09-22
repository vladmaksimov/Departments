package com.maksimov.controllers.dispatchers.impl;

import com.maksimov.controllers.dispatchers.Dispatcher;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 11.08.16.
 */
@Component
public class ProblemPage implements Dispatcher {

    @Override
    public void doDispatch(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.getRequestDispatcher(PROBLEM).forward(req, res);
    }
}
