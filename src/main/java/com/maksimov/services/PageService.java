package com.maksimov.services;

import com.maksimov.data.Pageable;
import com.maksimov.exceptions.DepartmentException;

/**
 * Abstract interface for pagination information.
 *
 * @author Vlad Maksimov
 * Created on 16.09.16.
 */
public interface PageService {

    Integer PAGE_DEFAULT_TOTAL = 1;

    /**
     * Gets the additional data of the {@link Pageable} object, like total page count of departments having next or
     * previous page, etc.
     *
     * @param pageable instance of the {@link Pageable} object.
     * @param search   {@link String} value needed to correct information for page object
     * @return the {@link Pageable} object with full page information
     * @throws DepartmentException related with database issue.
     */
    Pageable getDepartmentPageDetails(Pageable pageable, String search) throws DepartmentException;

    /**
     * Gets the additional data of the {@link Pageable} object, like total page count of employees, having next or
     * previous page, etc.
     *
     * @param pageable instance of the {@link Pageable} object.
     * @param id       {@link Long} value of the {@link com.maksimov.models.Department} object.
     * @param search   {@link String} value needed to correct information for page object
     * @return the {@link Pageable} object with full page information
     * @throws DepartmentException related with database issue.
     */
    Pageable getEmployeePageDetails(Pageable pageable, Long id, String search) throws DepartmentException;
}
