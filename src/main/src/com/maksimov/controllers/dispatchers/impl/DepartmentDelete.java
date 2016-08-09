package com.maksimov.controllers.dispatchers.impl;

import com.maksimov.controllers.dispatchers.Dispatcher;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.services.DepartmentService;
import com.maksimov.utils.BeanFactory;
import com.maksimov.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.maksimov.constants.DepartmentConstants.ID;

/**
 * Created on 21.07.16.
 */
public class DepartmentDelete implements Dispatcher {

    private DepartmentService service = BeanFactory.getDepartmentService();

    @Override
    public void doDispatch(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, DepartmentException {
        Long id = Utils.parseLong(req.getParameter(ID));
        if (id == null) {
            throw new DepartmentException("Can't delete department object. Id must be not null!");
        }

        service.delete(id);
        res.sendRedirect(MAIN_URL);
    }
}
