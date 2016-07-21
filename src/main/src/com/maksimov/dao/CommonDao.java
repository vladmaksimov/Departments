package com.maksimov.dao;

import com.maksimov.exceptions.DepartmentException;

/**
 * Created on 21.07.16.
 */
public interface CommonDao {

    void delete(Long id, String query) throws DepartmentException;

}
