package com.maksimov.dao.impl;

import com.maksimov.dao.CommonDao;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.utils.DataSourceFactory;
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * Created on 21.07.16.
 */
class CommonDaoImpl implements CommonDao {

    private static final Logger logger = Logger.getLogger(CommonDaoImpl.class);

    public void delete(Long id, String query) throws DepartmentException {
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete object with id: " + id);
            throw new DepartmentException("Can't delete object");
        }
    }

    @Override
    public Integer getCount(String table) throws DepartmentException {
        Integer count = null;
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            String query = String.format(QUERY_GET_COUNT, table);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                count = result.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("Can't get row count from table: " + table);
            throw new DepartmentException("Can't get row count from table: " + table);
        }
        return count;
    }
}
