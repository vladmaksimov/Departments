package com.maksimov.utils;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.spi.ServiceException;

/**
 * Created on 20.09.16.
 */
public class HibernateSessionFactory {

    private static final Logger logger = Logger.getLogger(HibernateSessionFactory.class);

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            return new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (ServiceException e) {
            logger.error("Initial HibernateSessionFactory creation failed." + e);
            throw new ExceptionInInitializerError("Failed to access the database");
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
