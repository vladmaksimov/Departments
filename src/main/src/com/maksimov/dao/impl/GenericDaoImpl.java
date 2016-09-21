package com.maksimov.dao.impl;

import com.maksimov.dao.GenericDao;
import com.maksimov.exceptions.DaoException;
import com.maksimov.utils.HibernateSessionFactory;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.util.List;

/**
 * Created on 21.09.16.
 */
abstract class GenericDaoImpl<T> implements GenericDao<T> {

    private static final Logger logger = Logger.getLogger(GenericDaoImpl.class);

    final Class entity;

    GenericDaoImpl(Class entity) {
        this.entity = entity;
    }

    @Override
    public void save(T object) throws DaoException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(object);
            session.getTransaction().commit();
        } catch (Throwable e) {
            logger.error("Can't save object to database!");
            throw new DaoException("Can't save object to database!");
        }
    }

    @Override
    public void delete(T object) throws DaoException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(object);
            session.getTransaction().commit();
        } catch (Throwable e) {
            logger.error("Can't delete object from database!");
            throw new DaoException("Can't delete object from database!");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAll() throws DaoException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return session.createCriteria(entity).list();
        } catch (Throwable e) {
            logger.error("Can't get list of objects from database!");
            throw new DaoException("Can't get list of objects from database!");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(Long id) throws DaoException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return (T) session.get(entity, id);
        } catch (Throwable e) {
            logger.error("Can't get object from database!");
            throw new DaoException("Can't get object from database!");
        }
    }
}
