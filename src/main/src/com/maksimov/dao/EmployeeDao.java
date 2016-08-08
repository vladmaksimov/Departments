package com.maksimov.dao;

import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Employee;

import java.util.List;

/**
 * Created on 7/19/2016.
 */
public interface EmployeeDao {

    String QUERY_GET_BY_DEPARTMENT_ID = "select * from employee where department = (?)";
    String QUERY_GET_BY_ID = "select * from employee where id = (?)";
    String QUERY_PUT = "insert into employee (name, email, birthday, department) values (?, ?, ?, ?)";
    String QUERY_UPDATE = "update employee set name = (?), email = (?), birthday = (?) where id = (?)";
    String QUERY_DELETE = "delete from employee where id = (?)";
    String QUERY_GET_BY_EMAIL = "select * from employee where email = (?)";

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
     * Gets the {@link Employee} object from the database by received {@link Long} id or null if the row
     * with this id doesn't exist.
     *
     * @param id of the employee we want to get.
     * @return {@link Employee} object or null.
     * @throws DepartmentException related with database problems.
     */
    Employee getById(Long id) throws DepartmentException;

    /**
     * Receives the {@link Employee} object to put it to database.
     * Object will be updated if it contains the {@link Long} id value from the database,
     * or will be added if not.
     *
     * @param employee object which need to save/
     * @throws DepartmentException related with database problems.
     */
    void put(Employee employee) throws DepartmentException;

    /**
     * Receives {@link Long} id of the {@link Employee} object we want to delete.
     * Employee with this id will be deleted if database contains the row this id.
     *
     * @param id of the employee we want to delete.
     * @throws DepartmentException
     */
    void delete(Long id) throws DepartmentException;

    /**
     * Gets the {@link Employee} object from database by it email or null if row with this email is not exist.
     *
     * @param email of the employee we want to get.
     * @return employee object or null.
     */
    Employee getByEmail(String email);

}
