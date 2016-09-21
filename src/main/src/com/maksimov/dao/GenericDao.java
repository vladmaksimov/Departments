package com.maksimov.dao;

import com.maksimov.exceptions.DepartmentException;

import java.util.List;

/**
 * Abstract interface to work with the common database operations
 *
 * @author Vladyslav Maksymov
 */
public interface GenericDao<T> {

    /**
     * Receives the {@link T} object to put it to database.
     * Object will be updated if it contains the {@link Long} id value from the database,
     * or will be added if not.
     *
     * @param object which need to save or update.
     * @throws DepartmentException related with database problems.
     */
    void save(T object) throws DepartmentException;

    /**
     * Receives {@link T} object we want to delete.
     * Department with this id will be deleted if database contains this id.
     *
     * @param object {@link T} object we want to delete.
     * @throws DepartmentException related with database problems.
     */
    void delete(T object) throws DepartmentException;

    /**
     * Return the {@link List} of all {@link T} objects from database
     *
     * @return {@link List} of all {@link T} object
     * @throws DepartmentException related with database problems
     */
    List<T> getAll() throws DepartmentException;

    /**
     * Receives {@link Long} id of {@link T} object we want to get.
     *
     * @param id {@link Long} object we want to get.
     * @return {@link T} object with current id or null
     * @throws DepartmentException related with database problems
     */
    T get(Long id) throws DepartmentException;

}
