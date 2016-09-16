package com.maksimov.dao.impl;

import com.maksimov.dao.CommonDao;
import com.maksimov.dao.DepartmentDao;
import com.maksimov.data.Pageable;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Department;
import com.maksimov.utils.DataSourceFactory;
import com.maksimov.utils.factorys.ModelFactory;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.maksimov.constants.DepartmentConstants.ID;
import static com.maksimov.constants.DepartmentConstants.NAME;

/**
 * Created on 7/19/2016.
 */
public class DepartmentDaoImpl implements DepartmentDao {

    private static final Logger logger = Logger.getLogger(DepartmentDaoImpl.class);

    private CommonDao common = new CommonDaoImpl();

    public List<Department> getAll() throws DepartmentException {
        List<Department> departments = new ArrayList<>();
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(QUERY_GET_ALL);
            departments.addAll(getDepartmentsFromResultSet(result));
        } catch (SQLException e) {
            logger.error("Can't get department list");
            throw new DepartmentException("Can't get department list");
        }
        return departments;
    }

    @Override
    public List<Department> getDepartments(Pageable page) throws DepartmentException {
        List<Department> departments = new ArrayList<>();
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            String query = String.format(QUERY_GET_WITH_PAGINATION, page.getSort(), page.getFirstResult(), page.getPageSize());
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            departments.addAll(getDepartmentsFromResultSet(result));
        } catch (SQLException e) {
            logger.error("Can't get department list");
            throw new DepartmentException("Can't get department list");
        }
        return departments;
    }

    public Department getById(Long id) throws DepartmentException {
        Department department = null;
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(QUERY_GET_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                department = ModelFactory.createDepartment();
                department.setId(resultSet.getLong(ID));
                department.setName(resultSet.getString(NAME));
            }
        } catch (SQLException e) {
            logger.error("Can't get department with id: " + id);
            throw new DepartmentException("Can't get department");
        }
        return department;
    }

    public void putDepartment(Department department) throws DepartmentException {
        String query = department.getId() == null ? QUERY_PUT : QUERY_UPDATE;
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, department.getName());
            if (QUERY_UPDATE.equals(query)) {
                st.setLong(2, department.getId());
            }
            st.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error to save department object");
            throw new DepartmentException("Error to save department object");
        }
    }

    public void delete(Long id) throws DepartmentException {
        common.delete(id, QUERY_DELETE);
    }

    @Override
    public Department getByName(String name) {
        Department department = null;
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(QUERY_GET_BY_NAME);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                department = ModelFactory.createDepartment();
                department.setId(resultSet.getLong(ID));
                department.setName(resultSet.getString(NAME));
            }
        } catch (Exception ignored) {
        }
        return department;
    }

    @Override
    public Integer getCount() throws DepartmentException {
        return common.getCount(TABLE);
    }

    private List<Department> getDepartmentsFromResultSet(ResultSet resultSet) throws SQLException {
        List<Department> result = new ArrayList<>();
        while (resultSet.next()) {
            Department department = ModelFactory.createDepartment();
            department.setId(resultSet.getLong(ID));
            department.setName(resultSet.getString(NAME));
            result.add(department);
        }
        return result;
    }
}
