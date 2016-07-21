package com.maksimov.services.impl;

import com.maksimov.dao.DepartmentDao;
import com.maksimov.dao.impl.DepartmentDaoImpl;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Department;
import com.maksimov.services.DepartmentService;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created on 7/19/2016.
 */
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDao dao = new DepartmentDaoImpl();

    @Override
    public List<Department> getAll() throws DepartmentException {
        return dao.getAll();
    }

    @Override
    public Department getById(Long id) throws DepartmentException {
        return dao.getById(id);
    }

    @Override
    public void put(Department department) throws DepartmentException {
        dao.putDepartment(department);
    }

    @Override
    public void delete(Long id) throws DepartmentException {
        dao.delete(id);
    }
}
