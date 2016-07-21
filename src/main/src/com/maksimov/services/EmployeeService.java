package com.maksimov.services;

import com.maksimov.exceptions.CustomValidateException;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Employee;

import java.util.List;

/**
 * Created on 7/19/2016.
 */
public interface EmployeeService {

    List<Employee> getByDepartmentId(Long id) throws DepartmentException;

    Employee getById(Long id) throws DepartmentException;

    void put(Employee employee) throws DepartmentException, CustomValidateException;

    void delete(Long id) throws DepartmentException;
}
