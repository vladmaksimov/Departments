package com.maksimov.services;

import com.maksimov.data.Pageable;
import com.maksimov.exceptions.DepartmentException;

/**
 * Created on 16.09.16.
 */
public interface PageService {

    Integer PAGE_DEFAULT_TOTAL = 1;

    Pageable getDepartmentPageDetails(Pageable pageable) throws DepartmentException;

    Pageable getEmployeePageDetails(Pageable pageable);
}
