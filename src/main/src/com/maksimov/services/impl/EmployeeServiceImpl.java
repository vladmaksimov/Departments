package com.maksimov.services.impl;

import com.maksimov.exceptions.CustomValidateException;
import com.maksimov.exceptions.ServiceException;
import com.maksimov.models.Employee;
import com.maksimov.persistence.EmployeePersistence;
import com.maksimov.services.EmployeeService;
import com.maksimov.utils.Utils;
import com.maksimov.utils.validators.DataValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created on 7/19/2016.
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private DataValidator validator;
    @Autowired
    private EmployeePersistence persistence;

    @Override
    public Page<Employee> getEmployees(Pageable page, Long id) throws ServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("Trying to get list of employee by department id: " + id);
        }

        try {
            Page<Employee> result = persistence.getEmployees(id, page);

            if (logger.isDebugEnabled()) {
                logger.debug("Extracted: " + result);
            }

            return result;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Page<Employee> searchEmployees(Pageable page, Long id, String search) throws ServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("Trying to get list of employee by department id: " + id + ", " + page + " and search value: " + search);
        }

        String searchToMysql = search == null ? null : Utils.createSearchString(search);
        try {
            Page<Employee> result = persistence.findEmployees(id, searchToMysql, page);

            if (logger.isDebugEnabled()) {
                logger.debug("Extracted: " + result);
            }

            return result;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Employee getById(Long id) throws ServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("Get employee by id: " + id);
        }

        try {
            Employee employee = persistence.getOne(id);

            if (logger.isDebugEnabled()) {
                logger.debug("Extracted: " + employee);
            }

            return employee;
        } catch (Exception e) {
            logger.error("Unable to find employee with id " + id);
            throw new ServiceException("Unable to find employee with id " + id);
        }
    }

    @Override
    public void put(Employee employee) throws ServiceException, CustomValidateException {
        if (logger.isDebugEnabled()) {
            logger.debug("Trying to save employee : " + employee);
        }

        Map<String, List<String>> errors = validator.validate(employee);
        if (errors.isEmpty()) {

            if (logger.isDebugEnabled()) {
                logger.debug("Successful employee validation: " + employee);
            }

            try {
                persistence.save(employee);

                if (logger.isDebugEnabled()) {
                    logger.debug("Employee successfully saved");
                }
            } catch (Exception e) {
                logger.error("Can't save employee object. Database error.");
                throw new ServiceException(e.getMessage());
            }
        } else {
            logger.error("Can't save employee object. Validation errors: " + errors);
            throw new CustomValidateException("Validation error", errors);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("Trying to employee by department id: " + id);
        }

        try {
            persistence.delete(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

}
