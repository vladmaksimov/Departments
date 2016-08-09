package com.maksimov.utils;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.log4j.Logger;

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

    private static MysqlDataSource dataSource = createDataSource();

    private static MysqlDataSource createDataSource() {
        Properties properties = getProperties();
        dataSource = new MysqlDataSource();
        dataSource.setUser(properties.getProperty(USERNAME));
        dataSource.setPassword(properties.getProperty(PASSWORD));
        dataSource.setURL(properties.getProperty(URL));
        return dataSource;
    }

    private static Properties getProperties(){
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream(PROPERTY_FILE)) {
            properties.load(resourceStream);
        } catch (IOException e) {
            logger.error("Can't execute application properties!");
        }
        return properties;
    }

    public static MysqlDataSource getDataSource() {
        return dataSource;
    }
}
