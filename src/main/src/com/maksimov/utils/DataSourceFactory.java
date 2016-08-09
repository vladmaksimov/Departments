package com.maksimov.utils;

import com.maksimov.exceptions.DepartmentException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created on 7/19/2016.
 */
public class DataSourceFactory {

    private static final Logger logger = Logger.getLogger(DataSourceFactory.class);

    private static final String PROPERTY_FILE = "application.properties";
    private static final String URL = "jdbc.url";
    private static final String USERNAME = "jdbc.username";
    private static final String PASSWORD = "jdbc.password";


    public static DataSource getDatasource() throws DepartmentException {
        Properties properties = getProperties();
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(properties.getProperty(USERNAME));
        dataSource.setPassword(properties.getProperty(PASSWORD));
        dataSource.setURL(properties.getProperty(URL));
        return dataSource;
    }

    private static Properties getProperties() throws DepartmentException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream(PROPERTY_FILE)) {
            properties.load(resourceStream);
        } catch (IOException e) {
            logger.error("Can't execute application properties!");
            throw new DepartmentException("Can't create connection");
        }
        return properties;
    }
}
