package com.maksimov.services;

import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Page;

/**
 * Abstract interface for pagination information.
 *
 * @author Vlad Maksimov
 */
public interface PageService {

    Integer PAGE_DEFAULT_TOTAL = 1;

    /**
     * Gets the additional data of the {@link Page} object, like total page count of departments having next or
     * previous page, etc.
     *
     * @param pageable instance of the {@link Page} object.
     * @param search   {@link String} value needed to correct information for page object
     * @return the {@link Page} object with full page information
     * @throws DepartmentException related with database issue.
     */
    Page getDepartmentPageDetails(Page pageable, String search) throws DepartmentException;

    /**
     * Gets the additional data of the {@link Page} object, like total page count of employees, having next or
     * previous page, etc.
     *
     * @param pageable instance of the {@link Page} object.
     * @param id       {@link Long} value of the {@link com.maksimov.models.Department} object.
     * @param search   {@link String} value needed to correct information for page object
     * @return the {@link Page} object with full page information
     * @throws DepartmentException related with database issue.
     */
    Page getEmployeePageDetails(Page pageable, Long id, String search) throws DepartmentException;
}
