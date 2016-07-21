package com.maksimov.dao;

import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Employee;

import java.util.List;

/**
 * Created on 7/19/2016.
 */
public interface EmployeeDao {

    String ID = "id";
    String DEPARTMENT = "department";
    String NAME = "name";
    String BIRTHDAY = "birthday";
    String EMAIL = "email";

    String QUERY_GET_BY_DEPARTMENT_ID = "select * from employee where department = (?)";
    String QUERY_GET_BY_ID = "select * from employee where id = (?)";
    String QUERY_PUT = "insert into employee (name, email, birthday, department) values (?, ?, ?, ?)";
    String QUERY_UPDATE = "update employee set name = (?), email = (?), birthday = (?) where id = (?)";
    String QUERY_DELETE = "delete from employee where id = (?)";
    String QUERY_GET_BY_EMAIL = "select * from employee where email = (?)";

    List<Employee> getByDepartmentId(Long id) throws DepartmentException;

    Employee getById(Long id) throws DepartmentException;

    void put(Employee employee) throws DepartmentException;

    void delete(Long id) throws DepartmentException;

    Employee getByEmail(String email);

}
