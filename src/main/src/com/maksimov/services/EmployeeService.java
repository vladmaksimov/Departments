package com.maksimov.services;

import com.maksimov.exceptions.CustomValidateException;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Employee;
import com.maksimov.models.Page;

import java.util.List;

/**
 * Created on 7/19/2016.
 */
public interface EmployeeService {

    /**
     * Gets the {@link List} of the {@link Employee} objects by {@link Long} id value of the
     * {@link com.maksimov.models.Department}.
     *
     * @param id of the department which employees we want to get.
     * @return the list of the employees by department id.
     * @throws DepartmentException related with dao issue.
     */
    List<Employee> getByDepartmentId(Long id) throws DepartmentException;

    /**
     * Gets the {@link List} of the {@link Employee} objects by {@link Long} id value of the
     * {@link com.maksimov.models.Department} with pagination.
     *
     * @param page the {@link Page} object, needed to get employees list with pagination.
     * @param id   of the department which employees we want to get.
     * @return the list of the employees by department id with pagination.
     * @throws DepartmentException
     */
    List<Employee> getEmployeesWithPagination(Page page, Long id) throws DepartmentException;

    /**
     * Gets the {@link List} of the {@link Employee} objects with pagination by department id
     * and search value or empty collection.
     *
     * @param page   the {@link Page} object, needed to get departments list with pagination.
     * @param id     of the department which employees we want to get.
     * @param search the {@link String} value to search employees.
     * @return the {@link List} of the {@link Employee} by department id with pagination.
     * @throws DepartmentException
     */
    List<Employee> searchEmployees(Page page, Long id, String search) throws DepartmentException;

    /**
     * Gets the {@link Employee} object from database by it {@link Long} id value or null, if row with
     * this id doesn't exist.
     *
     * @param id of the employee we want to get.
     * @return employee object with received id.
     * @throws DepartmentException related with dao issue.
     */
    Employee getById(Long id) throws DepartmentException;

    /**
     * Put the {@link Employee} object to database. If this object contains {@link Long} id value it will be updated.
     * If this object doesn't contains id value, it will be saved to database with new id.
     * <p>
     * Every employee object will be validated by custom data validator
     * {@link com.maksimov.utils.validators.DataValidator#validate(Object)}.
     * If validator will find errors it will produce the {@link CustomValidateException}.
     * Valid object will be saved or updated in database.
     *
     * @param employee object we want to validate and put to database.
     * @throws DepartmentException     related with dao issue.
     * @throws CustomValidateException if object have invalid fields.
     */
    void put(Employee employee) throws DepartmentException, CustomValidateException;

    /**
     * Delete {@link Employee} object from database by it {@link Long} id.
     *
     * @param id of the department we want to delete.
     * @throws DepartmentException related with dao issue.
     */
    void delete(Long id) throws DepartmentException;

    /**
     * Gets the {@link Integer} value with employees row count depends of search value and department id.
     *
     * @param id     of the department which employees we want to get.
     * @param search the {@link String} value to search departments.
     * @return the {@link Integer} value with departments row count.
     */
    Integer getEmployeeCount(Long id, String search) throws DepartmentException;
}
