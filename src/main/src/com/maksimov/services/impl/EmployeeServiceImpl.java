package com.maksimov.services.impl;

import com.maksimov.dao.EmployeeDao;
import com.maksimov.dao.impl.EmployeeDaoImpl;
import com.maksimov.exceptions.CustomValidateException;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Employee;
import com.maksimov.services.EmployeeService;
import com.maksimov.utils.validators.DataValidator;

import java.util.List;
import java.util.Map;

/**
 * Created on 7/19/2016.
 */
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao dao = new EmployeeDaoImpl();
    private DataValidator validator = new DataValidator();

    @Override
    public List<Employee> getByDepartmentId(Long id) throws DepartmentException {
        return dao.getByDepartmentId(id);
    }

    @Override
    public Employee getById(Long id) throws DepartmentException {
        return dao.getById(id);
    }

    @Override
    public void put(Employee employee) throws DepartmentException, CustomValidateException {
        Map<String, List<String>> errors = validator.validate(employee);
        if (errors.isEmpty()) {
            dao.put(employee);
        } else {
            throw new CustomValidateException("Validation error", errors);
        }
    }

    @Override
    public void delete(Long id) throws DepartmentException {
        dao.delete(id);
    }
}
