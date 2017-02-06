package com.maksimov.dao;

import com.maksimov.exceptions.DepartmentException;

/**
 * Created on 21.07.16.
 */
public interface CommonDao {

    String QUERY_GET_COUNT = "select count(*) from %s";

    /**
     * Receives {@link Long} id object and mysql {@link String} query to delete row with this id from database by query.
     *
     * @param id    of the object which we need to delete
     * @param query to mysql, which needed to delete row
     * @throws DepartmentException
     */
    void delete(Long id, String query) throws DepartmentException;


    /**
     * Receives {@link String} value with table name to get all rows count of this table.
     * Get the count of all row from receives table.
     *
     * @param table the {@link String} value with table name.
     * @return the {@link Integer} object with departments row count
     * @throws DepartmentException related with database problems.
     */
    Integer getCount(String table) throws DepartmentException;

}
