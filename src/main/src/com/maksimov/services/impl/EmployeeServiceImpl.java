package com.maksimov.services.impl;

import com.maksimov.dao.EmployeeDao;
import com.maksimov.exceptions.CustomValidateException;
import com.maksimov.exceptions.DaoException;
import com.maksimov.exceptions.ServiceException;
import com.maksimov.models.Employee;
import com.maksimov.models.Page;
import com.maksimov.services.EmployeeService;
import com.maksimov.utils.Utils;
import com.maksimov.utils.factorys.DaoBeanFactory;
import com.maksimov.utils.factorys.ValidatorBeanFactory;
import com.maksimov.utils.validators.DataValidator;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * Created on 7/19/2016.
 */
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

    private EmployeeDao dao = DaoBeanFactory.getEmployeeDao();
    private DataValidator validator = ValidatorBeanFactory.getValidator();

    @Override
    public List<Employee> getEmployeesWithPagination(Page page, Long id) throws ServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("Get list of employee by id: " + id);
        }

        try {
            return dao.getEmployees(page, id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Employee> searchEmployees(Page page, Long id, String search) throws ServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("Get list of employee by department id: " + id + ", and search value: " + search);
        }

        String searchToMysql = search == null ? null : Utils.createSearchString(search);
        try {
            return dao.searchEmployees(page, id, searchToMysql);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Employee getById(Long id) throws ServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("Get employee by id: " + id);
        }

        try {
            return dao.get(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void put(Employee employee) throws ServiceException, CustomValidateException {
        if (logger.isDebugEnabled()) {
            logger.debug("save employee : " + employee);
        }

        Map<String, List<String>> errors = validator.validate(employee);
        if (errors.isEmpty()) {
            try {
                dao.save(employee);
            } catch (DaoException e) {
                throw new ServiceException(e.getMessage());
            }
        } else {
            logger.error("Can't save employee object. Validation errors: " + errors.size());
            throw new CustomValidateException("Validation error", errors);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            Employee employee = dao.get(id);
            dao.delete(employee);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Integer getEmployeeCount(Long id, String search) throws ServiceException {
        String searchToMysql = search == null ? null : Utils.createSearchString(search);
        try {
            return dao.getCount(id, searchToMysql);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
