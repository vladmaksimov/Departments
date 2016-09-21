package com.maksimov.dao;

import com.maksimov.data.Pageable;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Employee;

import java.util.List;

/**
 * @author Vladyslav Maksymov
 */
public interface EmployeeDao extends GenericDao<Employee> {

    /**
     * Gets the {@link List} of the {@link Employee} objects from the database by received {@link Long} id
     * of the {@link com.maksimov.models.Department} object or empty list if the rows with this id doesn't exist.
     *
     * @param id value of the department.
     * @return list of the employees from department with received id.
     * @throws DepartmentException related with database problems.
     */
    List<Employee> getByDepartmentId(Long id) throws DepartmentException;

    /**
     * Gets the {@link List} of the {@link Employee} objects from the database by received {@link Long} id
     * of the {@link com.maksimov.models.Department} object with pagination
     * or empty list if the rows with this id doesn't exist.
     *
     * @param page the {@link Pageable} object, needed to get employees list with pagination.
     * @param id   value of the department.
     * @return list of the employees with pagination from department with received id.
     * @throws DepartmentException related with database problems.
     */
    List<Employee> getEmployees(Pageable page, Long id) throws DepartmentException;

    /**
     * @param page   the {@link Pageable} object, needed to get employees list with pagination.
     * @param id     value of the department.
     * @param search the {@link String} value to search employees.
     * @return list of the employees with pagination from department with received id.
     * @throws DepartmentException related with database problems.
     */
    List<Employee> searchEmployees(Pageable page, Long id, String search) throws DepartmentException;

    /**
     * Gets the {@link Employee} object from database by it email or null if row with this email is not exist.
     *
     * @param email of the employee we want to get.
     * @return employee object or null.
     */
    Employee getByEmail(String email);

    /**
     * Gets the count of the {@link Employee} objects from the database by received {@link Long} id
     * of the {@link com.maksimov.models.Department} object and search value.
     *
     * @param id     value of the department.
     * @param search the {@link String} value to get count of departments by this parameter.
     * @return the {@link Integer} value with count of all departments rows.
     */
    Integer getCount(Long id, String search) throws DepartmentException;

}
