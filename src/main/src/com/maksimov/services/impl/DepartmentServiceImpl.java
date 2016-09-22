package com.maksimov.services.impl;

import com.maksimov.dao.DepartmentDao;
import com.maksimov.exceptions.CustomValidateException;
import com.maksimov.exceptions.DaoException;
import com.maksimov.exceptions.ServiceException;
import com.maksimov.models.Department;
import com.maksimov.models.Page;
import com.maksimov.services.DepartmentService;
import com.maksimov.utils.Utils;
import com.maksimov.utils.validators.DataValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created on 7/19/2016.
 */
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger logger = Logger.getLogger(DepartmentServiceImpl.class);

    @Autowired
    private DepartmentDao dao;
    @Autowired
    private DataValidator validator;

    @Override
    public List<Department> getAll() throws ServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("Trying to get list of all departments.");
        }

        try {
            List<Department> result = dao.getAll();

            if (logger.isDebugEnabled()) {
                logger.debug("Extracted: " + result);
            }

            return result;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Department> getDepartments(Page page) throws ServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("Trying to get department list with pagination. " + page);
        }

        try {
            List<Department> result = dao.getDepartments(page);

            if (logger.isDebugEnabled()) {
                logger.debug("Extracted: " + result);
            }

            return result;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Department> searchDepartments(Page page, String search) throws ServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("Search department list with pagination. " + page + ". Search value: " + search);
        }

        String searchToMysql = Utils.createSearchString(search);
        try {
            List<Department> result = dao.searchDepartments(page, searchToMysql);

            if (logger.isDebugEnabled()) {
                logger.debug("Extracted: " + result);
            }

            return result;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Department getById(Long id) throws ServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("Get department by id: " + id);
        }

        try {
            Department department = dao.get(id);

            if (logger.isDebugEnabled()) {
                logger.debug("Extracted: " + department);
            }

            return department;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void put(Department department) throws CustomValidateException, ServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("Trying to save department: " + department);
        }

        Map<String, List<String>> errors = validator.validate(department);
        if (errors.isEmpty()) {

            if (logger.isDebugEnabled()) {
                logger.debug("Successful department validation: " + department);
            }

            try {
                dao.save(department);
            } catch (DaoException e) {
                throw new ServiceException(e.getMessage());
            }
        } else {
            logger.error("Can't save department object. Validation errors: " + errors.size());
            throw new CustomValidateException("Validation error", errors);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("Trying to delete department by id: " + id);
        }

        try {
            Department department = getById(id);
            dao.delete(department);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Integer getDepartmentCount(String search) throws ServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("Trying to get department count");
        }

        String searchToMysql = search == null ? null : Utils.createSearchString(search);
        try {
            Integer count = dao.getCount(searchToMysql);

            if (logger.isDebugEnabled()) {
                logger.debug("Department count is: " + count);
            }

            return count;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void setDao(DepartmentDao dao) {
        this.dao = dao;
    }

    public void setValidator(DataValidator validator) {
        this.validator = validator;
    }
}
