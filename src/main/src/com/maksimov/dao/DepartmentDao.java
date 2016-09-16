package com.maksimov.dao;

import com.maksimov.data.Pageable;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Department;

import java.util.List;

/**
 * Created on 7/19/2016.
 */
public interface DepartmentDao {

    String QUERY_GET_ALL = "select * from department order by id";
    String QUERY_GET_WITH_PAGINATION = "select * from department order by %s limit %d, %d";
    String QUERY_GET_BY_ID = "select * from department where id = (?)";
    String QUERY_PUT = "insert into department (name) values (?)";
    String QUERY_UPDATE = "update department set name = (?) where id = (?)";
    String QUERY_DELETE = "delete from department where id = (?)";
    String QUERY_GET_BY_NAME = "select * from department where name = (?)";

    String TABLE = "department";

    /**
     * Return the {@link List} of all {@link Department} objects from database
     *
     * @return {@link List} of all {@link Department} object
     * @throws DepartmentException related with database problems
     */
    List<Department> getAll() throws DepartmentException;

    /**
     * Return the {@link List} of {@link Department} objects from database
     *
     * @param page the {@link Pageable} object, needed to get departments list with pagination
     * @return {@link List} of all {@link Department} object
     * @throws DepartmentException related with database problems
     */
    List<Department> getDepartments(Pageable page) throws DepartmentException;


    /**
     * Receives {@link Long} id of {@link Department} object we want to get.
     *
     * @param id {@link Long} object with id of department we want to get.
     * @return {@link Department} object with current id or null
     * @throws DepartmentException related with database problems
     */
    Department getById(Long id) throws DepartmentException;

    /**
     * Receives the {@link Department} object to put it to database.
     * Object will be updated if it contains the {@link Long} id value from database,
     * or will be added if not.
     *
     * @param department {@link Department} object which needed to save.
     * @throws DepartmentException related with database problems.
     */
    void putDepartment(Department department) throws DepartmentException;

    /**
     * Receives {@link Long} id of {@link Department} object we want to delete.
     * Department with this id will be deleted if database contains this id.
     *
     * @param id {@link Long} object with id of department.
     * @throws DepartmentException related with database problems.
     */
    void delete(Long id) throws DepartmentException;

    /**
     * Receives the {@link String} object with name of the {@link Department} object.
     * Return the department object with received name or null if this name not contains in database.
     *
     * @param name of the department.
     * @return department object with received name or null.
     */
    Department getByName(String name);

    /**
     * Gets the count of all departments rows.
     *
     * @return the {@link Integer} value with count of all departments rows.
     */
    Integer getCount() throws DepartmentException;

}
