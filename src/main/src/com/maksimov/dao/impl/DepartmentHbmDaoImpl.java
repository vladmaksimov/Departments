package com.maksimov.dao.impl;

import com.maksimov.dao.DepartmentDao;
import com.maksimov.data.Pageable;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Department;
import com.maksimov.utils.HibernateSessionFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static com.maksimov.constants.DepartmentConstants.NAME;

/**
 * Created on 20.09.16.
 */
public class DepartmentHbmDaoImpl implements DepartmentDao {

    private Class entity;

    public DepartmentHbmDaoImpl(Class entity) {
        this.entity = entity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Department> getAll() throws DepartmentException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            return session.createCriteria(entity).list();
        } catch (Exception e) {
            System.out.println("Can't get department list");
            throw new DepartmentException("Can't get department list");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Department> getDepartments(Pageable page) throws DepartmentException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Criteria criteria = getListCriteria(session, page);
            return criteria.list();
        } catch (Exception | NoClassDefFoundError e) {
            System.out.println("Can't get department list");
            throw new DepartmentException("Can't get department list");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Department> searchDepartments(Pageable page, String search) throws DepartmentException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Criteria criteria = getListCriteria(session, page);
            criteria.add(Restrictions.like(NAME, search));
            return criteria.list();
        }
    }

    @Override
    public Department getById(Long id) throws DepartmentException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return (Department) session.get(entity, id);
        }
    }

    @Override
    public void putDepartment(Department department) throws DepartmentException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(department);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Long id) throws DepartmentException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Department d = (Department) session.get(entity, id);
            session.delete(d);
        }
    }

    @Override
    public Department getByName(String name) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(entity);
            criteria.add(Restrictions.eq(NAME, name));
            return (Department) criteria.uniqueResult();
        }
    }

    @Override
    public Integer getCount(String search) throws DepartmentException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(entity);
            criteria.setProjection(Projections.rowCount());
            if (search != null) {
                criteria.add(Restrictions.like(NAME, search));
            }
            return ((Long) criteria.uniqueResult()).intValue();
        }
    }

    private Criteria getListCriteria(Session session, Pageable page) {
        Criteria criteria = session.createCriteria(Department.class);
        criteria.addOrder(Order.asc(page.getSort()));
        criteria.setFirstResult(page.getFirstResult());
        criteria.setMaxResults(page.getPageSize());
        return criteria;
    }
}
