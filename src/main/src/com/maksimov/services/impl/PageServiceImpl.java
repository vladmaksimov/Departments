package com.maksimov.services.impl;

import com.maksimov.data.Pageable;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.services.DepartmentService;
import com.maksimov.services.EmployeeService;
import com.maksimov.services.PageService;
import com.maksimov.utils.factorys.ServiceBeanFactory;

/**
 * Created on 16.09.16.
 */
public class PageServiceImpl implements PageService {

    private DepartmentService departmentService = ServiceBeanFactory.getDepartmentService();
    private EmployeeService employeeService = ServiceBeanFactory.getEmployeeService();

    @Override
    public Pageable getDepartmentPageDetails(Pageable page) throws DepartmentException {
        Integer count = departmentService.getDepartmentCount();
        Integer totalPages = getTotalPageCount(count, page.getPageSize());
        page.setTotalPages(totalPages);
        page.setNext(getNextButtonState(page.getPageNumber(), totalPages));

        return page;
    }

    @Override
    public Pageable getEmployeePageDetails(Pageable page) {
        return null;
    }

    private Integer getTotalPageCount(Integer count, Integer size) {
        if (count.equals(size)) {
            return PAGE_DEFAULT_TOTAL;
        }

        return count % size != 0 ? (count / size) + 1 : count / size;
    }

    private Boolean getNextButtonState(Integer page, Integer total) {
        return page < total;
    }
}
