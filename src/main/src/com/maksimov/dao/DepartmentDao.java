package com.maksimov.dao;

import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Department;
import com.maksimov.models.Page;

import java.util.List;

/**
 * @author Vladyslav Maksymov
 */
public interface DepartmentDao extends GenericDao<Department> {

    /**
     * Return the {@link List} of {@link Department} objects from database
     *
     * @param page the {@link Page} object, needed to get departments list with pagination
     * @return {@link List} of all {@link Department} object
     * @throws DepartmentException related with database problems
     */
    List<Department> getDepartments(Page page) throws DepartmentException;

    /**
     * Return the {@link List} of {@link Department} objects from database, filtered by search value.
     *
     * @param page   the {@link Page} object, needed to get departments list with pagination.
     * @param search the {@link String} value to search departments.
     * @return {@link List} of {@link Department} objects, filtered by search value.
     * @throws DepartmentException
     */
    List<Department> searchDepartments(Page page, String search) throws DepartmentException;

    /**
     * Receives the {@link String} object with name of the {@link Department} object.
     * Return the department object with received name or null if this name not contains in database.
     *
     * @param name of the department.
     * @return department object with received name or null.
     */
    Department getByName(String name);

    /**
     * Gets the count of all departments rows depends of search value.
     *
     * @param search the {@link String} value to get count of departments by this parameter.
     * @return the {@link Integer} value with count of all departments rows.
     */
    Integer getCount(String search) throws DepartmentException;

}
