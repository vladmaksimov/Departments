package com.maksimov.services;

import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Department;

import java.util.List;

/**
 * Created on 7/19/2016.
 */
public interface DepartmentService {

    List<Department> getAll() throws DepartmentException;

    Department getById(Long id) throws DepartmentException;

    void put(Department department) throws DepartmentException;

    void delete(Long id) throws DepartmentException;
}
