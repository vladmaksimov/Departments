package com.maksimov.dao.impl;

import com.maksimov.dao.EmployeeDao;
import com.maksimov.data.Pageable;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Employee;
import com.maksimov.utils.HibernateSessionFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static com.maksimov.constants.EmployeeConstants.EMAIL;
import static com.maksimov.constants.EmployeeConstants.NAME;

/**
 * Created on 20.09.16.
 */
public class EmployeeHbmDaoImpl implements EmployeeDao {

    private Class entity = Employee.class;

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> getByDepartmentId(Long id) throws DepartmentException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(entity);
            criteria.add(Restrictions.eq("department.id", id));
            return criteria.list();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> getEmployeesWithPagination(Pageable page, Long id) throws DepartmentException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Criteria criteria = createListCriteria(session, id, page);
            return criteria.list();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> searchEmployees(Pageable page, Long id, String search) throws DepartmentException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Criteria criteria = createListCriteria(session, id, page);
            criteria.add(Restrictions.or(Restrictions.like(NAME, search), Restrictions.like(EMAIL, search)));
            return criteria.list();
        }
    }

    @Override
    public Employee getById(Long id) throws DepartmentException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return (Employee) session.get(entity, id);
        }
    }

    @Override
    public void put(Employee employee) throws DepartmentException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(employee);
            transaction.commit();
        }
    }


    @Override
    public void delete(Long id) throws DepartmentException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Employee employee = getById(id);
            session.delete(employee);
            transaction.commit();
        }
    }

    @Override
    public Employee getByEmail(String email) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(entity);
            criteria.add(Restrictions.eq(EMAIL, email));
            return (Employee) criteria.uniqueResult();
        }
    }

    @Override
    public Integer getCount(Long id, String search) throws DepartmentException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(entity);
            criteria.setProjection(Projections.rowCount());
            return ((Long) criteria.uniqueResult()).intValue();
        }
    }

    private Criteria createListCriteria(Session session, Long id, Pageable page) {
        Criteria criteria = session.createCriteria(entity);
        criteria.add(Restrictions.eq("department.id", id));
        criteria.addOrder(Order.asc(page.getSort()));
        criteria.setFirstResult(page.getFirstResult());
        criteria.setMaxResults(page.getPageSize());
        return criteria;
    }
}
