package com.maksimov.dao.impl;

import com.maksimov.dao.DepartmentDao;
import com.maksimov.exceptions.DaoException;
import com.maksimov.models.Department;
import com.maksimov.models.Page;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.maksimov.constants.DepartmentConstants.NAME;

/**
 * Created on 20.09.16.
 */
@Transactional
public class DepartmentDaoImpl extends GenericDaoImpl<Department> implements DepartmentDao {

    private static final Logger logger = Logger.getLogger(DepartmentDaoImpl.class);

    public DepartmentDaoImpl(Class entity) {
        super(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Department> getDepartments(Page page) throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            Criteria criteria = getListCriteria(session, page);
            return criteria.list();
        } catch (Exception e) {
            logger.error("Can't get department list");
            throw new DaoException("Can't get department list");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Department> searchDepartments(Page page, String search) throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            Criteria criteria = getListCriteria(session, page);
            criteria.add(Restrictions.like(NAME, search));
            return criteria.list();
        } catch (Exception e) {
            logger.error("Can't get employees from DataBase");
            throw new DaoException("Can't get employees from DataBase");
        }
    }

    @Override
    public Long getByName(String name) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entity);
        criteria.setProjection(Projections.id());
        criteria.add(Restrictions.eq(NAME, name));
        return (Long) criteria.uniqueResult();
    }

    @Override
    public Integer getCount(String search) throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            Criteria criteria = session.createCriteria(entity);
            criteria.setProjection(Projections.rowCount());
            if (search != null) {
                criteria.add(Restrictions.like(NAME, search));
            }
            return ((Long) criteria.uniqueResult()).intValue();
        } catch (Exception e) {
            logger.error("Can't get employees from DataBase");
            throw new DaoException("Can't get employees from DataBase");
        }
    }

    private Criteria getListCriteria(Session session, Page page) {
        Criteria criteria = session.createCriteria(entity);
        criteria.addOrder(Order.asc(page.getSort()));
        criteria.setFirstResult(page.getFirstResult());
        criteria.setMaxResults(page.getPageSize());
        return criteria;
    }
}
