package com.maksimov.services.impl;

import com.maksimov.exceptions.ServiceException;
import com.maksimov.models.Page;
import com.maksimov.services.DepartmentService;
import com.maksimov.services.EmployeeService;
import com.maksimov.services.PageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created on 16.09.16.
 */
@Service
@Transactional
public class PageServiceImpl implements PageService {

    private static final Logger logger = Logger.getLogger(PageServiceImpl.class);

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeeService employeeService;

    @Override
    public Page getDepartmentPageDetails(Page page, String search) throws ServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("Trying to get full department page information to " + page);
        }

        Integer count = departmentService.getDepartmentCount(search);
        Integer totalPages = getTotalPageCount(count, page.getPageSize());
        page.setTotalPages(totalPages);
        page.setNext(getNextButtonState(page.getPageNumber(), totalPages));

        return page;
    }

    @Override
    public Page getEmployeePageDetails(Page page, Long id, String search) throws ServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("Trying to get full employee page information to " + page);
        }

        Integer count = employeeService.getEmployeeCount(id, search);
        Integer totalPages = getTotalPageCount(count, page.getPageSize());
        page.setTotalPages(totalPages);
        page.setNext(getNextButtonState(page.getPageNumber(), totalPages));

        return page;
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
