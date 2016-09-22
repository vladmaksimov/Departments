package com.maksimov.dao.impl;

import com.maksimov.dao.GenericDao;
import com.maksimov.exceptions.DaoException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created on 21.09.16.
 */
public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    private static final Logger logger = Logger.getLogger(GenericDaoImpl.class);

    @Autowired
    SessionFactory sessionFactory;

    final Class entity;

    GenericDaoImpl(Class entity) {
        this.entity = entity;
    }

    @Override
    public void save(T object) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(object);
        } catch (Exception e) {
            logger.error("Can't save object to database!");
            throw new DaoException("Can't save object to database!");
        }
    }

    @Override
    public void delete(T object) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            session.delete(object);
        } catch (Exception e) {
            logger.error("Can't delete object from database!");
            throw new DaoException("Can't delete object from database!");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAll() throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            return session.createCriteria(entity).list();
        } catch (Exception e) {
            logger.error("Can't get list of objects from database!");
            throw new DaoException("Can't get list of objects from database!");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(Long id) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            return (T) session.get(entity, id);
        } catch (Exception e) {
            logger.error("Can't get object from database!");
            throw new DaoException("Can't get object from database!");
        }
    }

}
