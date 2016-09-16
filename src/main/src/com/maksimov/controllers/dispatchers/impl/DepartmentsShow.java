package com.maksimov.controllers.dispatchers.impl;

import com.maksimov.controllers.dispatchers.Dispatcher;
import com.maksimov.data.Pageable;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Department;
import com.maksimov.services.DepartmentService;
import com.maksimov.services.PageService;
import com.maksimov.transformers.PageRequestTransformer;
import com.maksimov.utils.factorys.PageServiceFactory;
import com.maksimov.utils.factorys.ServiceBeanFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.maksimov.constants.PageConstants.PAGE_DEPARTMENT_SORT_LIST;
import static com.maksimov.constants.PageConstants.PAGE_SIZE_LIST;

/**
 * Created on 7/19/2016.
 */
public class DepartmentsShow implements Dispatcher {

    private DepartmentService service = ServiceBeanFactory.getDepartmentService();
    private PageService pageService = PageServiceFactory.getPageService();
    private PageRequestTransformer transformer = new PageRequestTransformer();

    public void doDispatch(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, DepartmentException {
        Pageable page = transformer.transform(req);
        List<Department> departments = service.getDepartments(page);
        page = pageService.getDepartmentPageDetails(page);

        req.setAttribute(ATTR_DEPARTMENTS, departments);
        req.setAttribute(ATTR_PAGE, page);
        req.setAttribute(ATTR_SIZE_LIST, PAGE_SIZE_LIST);
        req.setAttribute(ATTR_SORT_LIST, PAGE_DEPARTMENT_SORT_LIST);
        req.getRequestDispatcher(SHOW_DEPARTMENTS).forward(req, res);
    }
}
