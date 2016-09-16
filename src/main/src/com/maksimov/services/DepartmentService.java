package com.maksimov.services;

import com.maksimov.data.Pageable;
import com.maksimov.exceptions.CustomValidateException;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Department;

import java.util.List;

/**
 * Created on 7/19/2016.
 */
public interface DepartmentService {

    /**
     * Gets the {@link List} of the {@link Department} objects or empty collection/
     *
     * @return list of the departments or empty collection.
     * @throws DepartmentException related with dao issue.
     */
    List<Department> getAll() throws DepartmentException;

    /**
     * Gets the {@link List} of the {@link Department} objects with pagination or empty collection.
     *
     * @param page the {@link Pageable} object, needed to get departments list with pagination.
     * @return list of the departments or empty collection.
     * @throws DepartmentException related with dao issue.
     */
    List<Department> getDepartments(Pageable page) throws DepartmentException;

    /**
     * Gets the {@link Department} object from database by it {@link Long} id value or null, if row with
     * this id doesn't exist.
     *
     * @param id value of the department we want to get
     * @return department object
     * @throws DepartmentException related with dao issue.
     */
    Department getById(Long id) throws DepartmentException;

    /**
     * Put the {@link Department} object to database. If this object contains {@link Long} id value it will be updated.
     * If this object doesn't contains id value, it will be saved to database with new id.
     * <p>
     * Every department object will be validated by custom data validator
     * {@link com.maksimov.utils.validators.DataValidator#validate(Object)}.
     * If validator will find errors it will produce the {@link CustomValidateException}.
     * Valid object will be saved or updated in database.
     *
     * @param department object we want to validate and put to database.
     * @throws DepartmentException     related with dao issue.
     * @throws CustomValidateException if object have invalid fields.
     */
    void put(Department department) throws DepartmentException, CustomValidateException;

    /**
     * Delete {@link Department} object from database by it {@link Long} id.
     *
     * @param id of the department we want to delete.
     * @throws DepartmentException related with dao issue.
     */
    void delete(Long id) throws DepartmentException;

    /**
     * Gets the {@link Integer} value with departments row count.
     *
     * @return the {@link Integer} value with departments row count.
     */
    Integer getDepartmentCount() throws DepartmentException;
}
