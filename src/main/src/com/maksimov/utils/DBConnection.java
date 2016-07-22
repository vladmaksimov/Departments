package com.maksimov.utils;

import com.maksimov.exceptions.DepartmentException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created on 7/19/2016.
 */
public class DBConnection {

    private static final Logger logger = Logger.getLogger(DBConnection.class);

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/departments";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws DepartmentException {
        Connection connection;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            logger.error("Cant't find class to create connection to BD: " + DRIVER);
            throw new DepartmentException("Cant't find class to create connection to BD");
        } catch (SQLException e) {
            logger.error(String.format("Can't create connection to DB with: url - %s, username - %s, password - %s", URL, USERNAME, PASSWORD));
            throw new DepartmentException("Can't create connection to DB");
        }

        return connection;
    }

    public static void closeConnection(Connection connection) throws DepartmentException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("Can't close a DB connection");
            throw new DepartmentException("Can't close a DB connection");
        }
    }
}
