package com.maksimov.services.impl;

import com.maksimov.dao.EmployeeDao;
import com.maksimov.data.Pageable;
import com.maksimov.exceptions.CustomValidateException;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Employee;
import com.maksimov.services.EmployeeService;
import com.maksimov.utils.Utils;
import com.maksimov.utils.factorys.DaoBeanFactory;
import com.maksimov.utils.factorys.ValidatorBeanFactory;
import com.maksimov.utils.validators.DataValidator;

import java.util.List;
import java.util.Map;

/**
 * Created on 7/19/2016.
 */
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao dao = DaoBeanFactory.getEmployeeDao();
    private DataValidator validator = ValidatorBeanFactory.getValidator();

    @Override
    public List<Employee> getByDepartmentId(Long id) throws DepartmentException {
        return dao.getByDepartmentId(id);
    }

    @Override
    public List<Employee> getEmployeesWithPagination(Pageable page, Long id) throws DepartmentException {
        return dao.getEmployeesWithPagination(page, id);
    }

    @Override
    public List<Employee> searchEmployees(Pageable page, Long id, String search) throws DepartmentException {
        String searchToMysql = search == null ? null : Utils.createSearchString(search);
        return dao.searchEmployees(page, id, searchToMysql);
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

    @Override
    public Integer getEmployeeCount(Long id, String search) throws DepartmentException {
        String searchToMysql = search == null ? null : Utils.createSearchString(search);
        return dao.getCount(id, searchToMysql);
    }
}
