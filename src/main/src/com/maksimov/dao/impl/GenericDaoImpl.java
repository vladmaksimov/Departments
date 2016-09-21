package com.maksimov.dao.impl;

import com.maksimov.dao.GenericDao;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.utils.HibernateSessionFactory;
import org.hibernate.Session;

import java.util.List;

/**
 * Created on 21.09.16.
 */
abstract class GenericDaoImpl<T> implements GenericDao<T> {

    final Class entity;

    GenericDaoImpl(Class entity) {
        this.entity = entity;
    }

    @Override
    public void save(T object) throws DepartmentException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(object);
            session.getTransaction().commit();
        } catch (Throwable e) {
            throw new DepartmentException("Can't save object to database!");
        }
    }

    @Override
    public void delete(T object) throws DepartmentException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(object);
            session.getTransaction().commit();
        } catch (Throwable e) {
            throw new DepartmentException("Can't delete object from database!");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAll() throws DepartmentException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return session.createCriteria(entity).list();
        } catch (Throwable e) {
            throw new DepartmentException("Can't get list of objects from database!");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(Long id) throws DepartmentException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return (T) session.get(entity, id);
        } catch (Throwable e) {
            throw new DepartmentException("Can't get object from database!");
        }
    }
}
