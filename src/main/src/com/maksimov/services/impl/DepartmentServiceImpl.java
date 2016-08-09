package com.maksimov.services.impl;

import com.maksimov.dao.DepartmentDao;
import com.maksimov.dao.impl.DepartmentDaoImpl;
import com.maksimov.exceptions.CustomValidateException;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Department;
import com.maksimov.services.DepartmentService;
import com.maksimov.utils.validators.DataValidator;

import java.util.List;
import java.util.Map;

/**
 * Created on 7/19/2016.
 */
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDao dao = new DepartmentDaoImpl();
    private DataValidator validator = new DataValidator();

    @Override
    public List<Department> getAll() throws DepartmentException {
        return dao.getAll();
    }

    @Override
    public Department getById(Long id) throws DepartmentException {
        return dao.getById(id);
    }

    @Override
    public void put(Department department) throws DepartmentException, CustomValidateException {
        Map<String, List<String>> errors = validator.validate(department);
        if (errors.isEmpty()) {
            dao.putDepartment(department);
        } else {
            throw new CustomValidateException("Validation error", errors);
        }
    }

    @Override
    public void delete(Long id) throws DepartmentException {
        dao.delete(id);
    }
}
