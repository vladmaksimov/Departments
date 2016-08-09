package com.maksimov.dao.impl;

import com.maksimov.dao.CommonDao;
import com.maksimov.dao.EmployeeDao;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Employee;
import com.maksimov.utils.DataSourceFactory;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.maksimov.constants.EmployeeConstants.*;

/**
 * Created on 7/19/2016.
 */
public class EmployeeDaoImpl implements EmployeeDao {

    private static final Logger logger = Logger.getLogger(EmployeeDaoImpl.class);

    private CommonDao common = new CommonDaoImpl();

    @Override
    public List<Employee> getByDepartmentId(Long id) throws DepartmentException {
        List<Employee> result = new ArrayList<>();
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(QUERY_GET_BY_DEPARTMENT_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong(ID));
                employee.setDepartment(resultSet.getLong(DEPARTMENT));
                employee.setName(resultSet.getString(NAME));
                employee.setBirthday(resultSet.getDate(BIRTHDAY));
                employee.setEmail(resultSet.getString(EMAIL));
                result.add(employee);
            }
        } catch (Exception e) {
            logger.error("Can't get employee list from department: " + id);
            throw new DepartmentException("Can't get employee list from department");
        }
        return result;
    }

    @Override
    public Employee getById(Long id) throws DepartmentException {
        Employee employee;
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(QUERY_GET_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            employee = getEmployeeFromResultSet(resultSet);
        } catch (SQLException e) {
            logger.error("Can't get Employee object with id " + id + " from Database!");
            throw new DepartmentException("Error to save employee object");
        }
        return employee;
    }

    @Override
    public void put(Employee employee) throws DepartmentException {
        String query = employee.getId() == null ? QUERY_PUT : QUERY_UPDATE;
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, employee.getName());
            st.setString(2, employee.getEmail());
            st.setDate(3, new Date(employee.getBirthday().getTime()));
            if (QUERY_PUT.equals(query)) {
                st.setLong(4, employee.getDepartment());
            } else if (QUERY_UPDATE.equals(query)) {
                st.setLong(4, employee.getId());
            }
            st.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error to save employee object: " + employee);
            throw new DepartmentException("Error to save employee object");
        }
    }

    @Override
    public void delete(Long id) throws DepartmentException {
        common.delete(id, QUERY_DELETE);
    }

    @Override
    public Employee getByEmail(String email) {
        Employee employee = null;
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(QUERY_GET_BY_EMAIL);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            employee = getEmployeeFromResultSet(resultSet);
        } catch (Exception ignored) {
        }
        return employee;
    }

    private Employee getEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
        Employee employee = null;
        if (resultSet.next()) {
            employee = new Employee();
            employee.setId(resultSet.getLong(ID));
            employee.setDepartment(resultSet.getLong(DEPARTMENT));
            employee.setName(resultSet.getString(NAME));
            employee.setBirthday(resultSet.getDate(BIRTHDAY));
            employee.setEmail(resultSet.getString(EMAIL));
        }
        return employee;
    }
}
