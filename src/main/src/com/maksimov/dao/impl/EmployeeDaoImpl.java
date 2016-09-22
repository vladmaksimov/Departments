package com.maksimov.dao.impl;

import com.maksimov.dao.EmployeeDao;
import com.maksimov.exceptions.DaoException;
import com.maksimov.models.Employee;
import com.maksimov.models.Page;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static com.maksimov.constants.EmployeeConstants.EMAIL;
import static com.maksimov.constants.EmployeeConstants.NAME;

/**
 * Created on 20.09.16.
 */
public class EmployeeDaoImpl extends GenericDaoImpl<Employee> implements EmployeeDao {

    private static final Logger logger = Logger.getLogger(EmployeeDaoImpl.class);

    public EmployeeDaoImpl(Class entity) {
        super(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> getByDepartmentId(Long id) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(entity);
            criteria.add(Restrictions.eq("department.id", id));
            return criteria.list();
        } catch (Exception e) {
            logger.error("Can't get employees from DataBase");
            throw new DaoException("Can't get employees from DataBase");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> getEmployees(Page page, Long id) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = createListCriteria(session, id, page);
            return criteria.list();
        } catch (Exception e) {
            logger.error("Can't get employees from DataBase");
            throw new DaoException("Can't get employees from DataBase");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> searchEmployees(Page page, Long id, String search) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = createListCriteria(session, id, page);
            criteria.add(Restrictions.or(Restrictions.like(NAME, search), Restrictions.like(EMAIL, search)));
            return criteria.list();
        } catch (Exception e) {
            logger.error("Can't get employees from DataBase");
            throw new DaoException("Can't get employees from DataBase");
        }
    }

    @Override
    public Employee getByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(entity);
            criteria.add(Restrictions.eq(EMAIL, email));
            return (Employee) criteria.uniqueResult();
        }
    }

    @Override
    public Integer getCount(Long id, String search) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(entity);
            criteria.setProjection(Projections.rowCount());
            return ((Long) criteria.uniqueResult()).intValue();
        } catch (Exception e) {
            logger.error("Can't get employees from DataBase");
            throw new DaoException("Can't get employees from DataBase");
        }
    }

    private Criteria createListCriteria(Session session, Long id, Page page) {
        Criteria criteria = session.createCriteria(entity);
        criteria.add(Restrictions.eq("department.id", id));
        criteria.addOrder(Order.asc(page.getSort()));
        criteria.setFirstResult(page.getFirstResult());
        criteria.setMaxResults(page.getPageSize());
        return criteria;
    }
}
