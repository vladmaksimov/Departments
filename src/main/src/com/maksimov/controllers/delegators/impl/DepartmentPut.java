package com.maksimov.controllers.delegators.impl;

import com.maksimov.controllers.delegators.Processor;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Department;
import com.maksimov.services.DepartmentService;
import com.maksimov.services.impl.DepartmentServiceImpl;
import com.maksimov.transformers.DepartmentsTransformer;
import com.mysql.jdbc.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 7/20/2016.
 */
public class DepartmentPut implements Processor {

    private DepartmentService service = new DepartmentServiceImpl();
    private DepartmentsTransformer transformer = new DepartmentsTransformer();

    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, DepartmentException {
        Department department = transformer.transform(req);
        service.put(department);
        res.sendRedirect(MAIN_URL);
    }
}
