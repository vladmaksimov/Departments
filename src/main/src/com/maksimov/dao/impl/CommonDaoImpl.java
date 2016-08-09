package com.maksimov.dao.impl;

import com.maksimov.dao.CommonDao;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.utils.DataSourceFactory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created on 21.07.16.
 */
class CommonDaoImpl implements CommonDao {

    private static final Logger logger = Logger.getLogger(CommonDaoImpl.class);

    public void delete(Long id, String query) throws DepartmentException {
        try (Connection connection = DataSourceFactory.getDatasource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete object with id: " + id);
            throw new DepartmentException("Can't delete object");
        }
    }
}
