package com.maksimov.services.impl;

import com.maksimov.exceptions.CustomValidateException;
import com.maksimov.exceptions.ServiceException;
import com.maksimov.models.Department;
import com.maksimov.models.ValidateError;
import com.maksimov.persistence.DepartmentPersistence;
import com.maksimov.services.DepartmentService;
import com.maksimov.utils.Utils;
import com.maksimov.utils.validators.DataValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created on 7/19/2016.
 */
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger logger = Logger.getLogger(DepartmentServiceImpl.class);

    @Autowired
    private DepartmentPersistence persistence;

    @Autowired
    private DataValidator validator;

    @Override
    public List<Department> getAll() throws ServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("Trying to get list of all departments.");
        }

        try {
            List<Department> result = persistence.findAll();

            if (logger.isDebugEnabled()) {
                logger.debug("Extracted: " + result);
            }

            return result;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Page<Department> getDepartments(Pageable page) throws ServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("Trying to get department list with pagination. " + page);
        }

        try {
            Page<Department> result = persistence.findAll(page);

            if (logger.isDebugEnabled()) {
                logger.debug("Extracted: " + result);
            }

            return result;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Page<Department> searchDepartments(Pageable page, String search) throws ServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("Search department list with pagination. " + page + ". Search value: " + search);
        }

        String searchToMysql = Utils.createSearchString(search);
        try {
            Page<Department> result = persistence.searchDepartments(searchToMysql, page);

            if (logger.isDebugEnabled()) {
                logger.debug("Extracted: " + result);
            }

            return result;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Department getById(Long id) throws ServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("Trying to get department by id: " + id);
        }

        try {
            Department department = persistence.findOne(id);

            if (logger.isDebugEnabled()) {
                logger.debug("Extracted: " + department);
            }

            return department;
        } catch (Exception e) {
            throw new ServiceException("Unable to find department with id " + id);
        }
    }

    @Override
    public void put(Department department) throws CustomValidateException, ServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("Trying to save department: " + department);
        }

        ValidateError error = validator.validate(department);
        if (error.getErrors().isEmpty()) {

            if (logger.isDebugEnabled()) {
                logger.debug("Successful department validation: " + department);
            }

            try {
                persistence.save(department);

                if (logger.isDebugEnabled()) {
                    logger.debug("Department successfully saved");
                }
            } catch (Exception e) {
                logger.error("Can't save department object. Database error.");
                throw new ServiceException("Can't save department object. Database error.");
            }
        } else {
            logger.error("Can't save department object. Validation errors: " + error.getErrors());
            throw new CustomValidateException("Validation error", error);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("Trying to delete department by id: " + id);
        }

        try {
            persistence.delete(id);

            if (logger.isDebugEnabled()) {
                logger.debug("Department successfully deleted.");
            }
        } catch (Exception e) {
            logger.error(String.format("Can't delete department with id: %d. Reason: %s", id, e.getMessage()));
            throw new ServiceException("Can't delete department with id: " + id);
        }
    }

}
