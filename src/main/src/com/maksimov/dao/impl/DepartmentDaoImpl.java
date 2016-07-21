package com.maksimov.dao.impl;

import com.maksimov.dao.CommonDao;
import com.maksimov.dao.DepartmentDao;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Department;
import com.maksimov.utils.DBConnection;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 7/19/2016.
 */
public class DepartmentDaoImpl implements DepartmentDao {

    private static final Logger logger = Logger.getLogger(DepartmentDaoImpl.class);

    private CommonDao common = new CommonDaoImpl();

    public List<Department> getAll() throws DepartmentException {
        List<Department> departments = new ArrayList<Department>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(QUERY_GET_ALL);
            while (result.next()) {
                Department department = new Department();
                department.setId(result.getLong(ID));
                department.setName(result.getString(NAME));
                departments.add(department);
            }
            DBConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Can't get department list");
            throw new DepartmentException("Can't get department list");
        }
        return departments;
    }

    public Department getById(Long id) throws DepartmentException {
        Department department = null;
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(QUERY_GET_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                department = new Department();
                department.setId(resultSet.getLong(ID));
                department.setName(resultSet.getString(NAME));
            }
            DBConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Can't get department with id: " + id);
            throw new DepartmentException("Can't get department");
        }
        return department;
    }

    public void putDepartment(Department department) throws DepartmentException {
        String query = department.getId() == null ? QUERY_PUT : QUERY_UPDATE;
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, department.getName());
            if (QUERY_UPDATE.equals(query)) {
                st.setLong(2, department.getId());
            }
            st.executeUpdate();
            DBConnection.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Error to save department object");
            throw new DepartmentException("Error to save department object");
        }
    }

    public void delete(Long id) throws DepartmentException {
        common.delete(id, QUERY_DELETE);
    }
}
