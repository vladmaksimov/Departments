package com.maksimov.dao;

import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Department;

import java.util.List;

/**
 * Created on 7/19/2016.
 */
public interface DepartmentDao {

    String ID = "id";
    String NAME = "name";

    String QUERY_GET_ALL = "select * from department order by id";
    String QUERY_GET_BY_ID = "select * from department where id = (?)";
    String QUERY_PUT = "insert into department (name) values (?)";
    String QUERY_UPDATE = "update department set name = (?) where id = (?)";
    String QUERY_DELETE = "delete from department where id = (?)";

    List<Department> getAll() throws DepartmentException;

    Department getById(Long id) throws DepartmentException;

    void putDepartment(Department department) throws DepartmentException;

    void delete(Long id) throws DepartmentException;

}
