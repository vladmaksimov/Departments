package com.maksimov.dao;

import com.maksimov.exceptions.DepartmentException;

/**
 * Created on 21.07.16.
 */
public interface CommonDao {

    /**
     * Receives {@link Long} id object and mysql {@link String} query to delete row with this id from database by query.
     *
     * @param id    of the object which we need to delete
     * @param query to mysql, which needed to delete row
     * @throws DepartmentException
     */
    void delete(Long id, String query) throws DepartmentException;

}
