package com.maksimov.services;

import com.maksimov.exceptions.CustomValidateException;
import com.maksimov.exceptions.ServiceException;
import com.maksimov.models.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created on 7/19/2016.
 */
public interface DepartmentService {

    /**
     * Gets the {@link List} of the {@link Department} objects or empty collection/
     *
     * @return list of the departments or empty collection.
     * @throws ServiceException related with dao issue.
     */
    List<Department> getAll() throws ServiceException;

    /**
     * Gets the {@link List} of the {@link Department} objects with pagination or empty collection.
     *
     * @param page the {@link Page} object, needed to get departments list with pagination.
     * @return list of the departments or empty collection.
     * @throws ServiceException related with dao issue.
     */
    Page<Department> getDepartments(Pageable page) throws ServiceException;

    /**
     * Gets the {@link List} of the {@link Department} objects with pagination by search value or empty collection.
     *
     * @param page   the {@link Page} object, needed to get departments list with pagination.
     * @param search the {@link String} value to search departments
     * @return list of the departments or empty collection.
     * @throws ServiceException related with dao issue.
     */
    Page<Department> searchDepartments(Pageable page, String search) throws ServiceException;

    /**
     * Gets the {@link Department} object from database by it {@link Long} id value or null, if row with
     * this id doesn't exist.
     *
     * @param id value of the department we want to get
     * @return department object
     * @throws ServiceException related with dao issue.
     */
    Department getById(Long id) throws ServiceException;

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
     * @throws ServiceException        related with dao issue.
     * @throws CustomValidateException if object have invalid fields.
     */
    void put(Department department) throws CustomValidateException, ServiceException;

    /**
     * Delete {@link Department} object from database by it {@link Long} id.
     *
     * @param id of the department we want to delete.
     * @throws ServiceException related with dao issue.
     */
    void delete(Long id) throws ServiceException;

    /**
     * Gets the {@link Integer} value with departments row count depends of search value.
     *
     * @param search the {@link String} value to search departments.
     * @return the {@link Integer} value with departments row count.
     */
    Long getDepartmentCount(String search) throws ServiceException;
}
