package com.maksimov.services.impl;

import com.maksimov.dao.DepartmentDao;
import com.maksimov.exceptions.CustomValidateException;
import com.maksimov.exceptions.DaoException;
import com.maksimov.exceptions.ServiceException;
import com.maksimov.models.Department;
import com.maksimov.models.Page;
import com.maksimov.services.DepartmentService;
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
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger logger = Logger.getLogger(DepartmentServiceImpl.class);

    private DepartmentDao dao = DaoBeanFactory.getDepartmentDao();
    private DataValidator validator = ValidatorBeanFactory.getValidator();

    @Override
    public List<Department> getAll() throws ServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("Get all department list.");
        }

        try {
            return dao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Department> getDepartments(Page page) throws ServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("Get department list with pagination.");
        }

        try {
            return dao.getDepartments(page);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Department> searchDepartments(Page page, String search) throws ServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("Search department list with pagination. Search value: " + search);
        }

        String searchToMysql = Utils.createSearchString(search);
        try {
            return dao.searchDepartments(page, searchToMysql);
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
            return dao.get(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void put(Department department) throws CustomValidateException, ServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("Save department: " + department);
        }

        Map<String, List<String>> errors = validator.validate(department);
        if (errors.isEmpty()) {
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
            logger.debug("Delete department by id: " + id);
        }

        try {
            Department department = dao.get(id);
            dao.delete(department);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Integer getDepartmentCount(String search) throws ServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("Get department count");
        }

        String searchToMysql = search == null ? null : Utils.createSearchString(search);
        try {
            return dao.getCount(searchToMysql);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
