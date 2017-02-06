package com.maksimov.dao.impl;

import com.maksimov.dao.CommonDao;
import com.maksimov.dao.EmployeeDao;
import com.maksimov.data.Pageable;
import com.maksimov.exceptions.DepartmentException;
import com.maksimov.models.Employee;
import com.maksimov.utils.DataSourceFactory;
import com.maksimov.utils.factorys.ModelFactory;
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
                Employee employee = ModelFactory.createEmployee();
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
    public List<Employee> getEmployeesWithPagination(Pageable page, Long id) throws DepartmentException {
        List<Employee> result = new ArrayList<>();
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            String query = String.format(QUERY_GET_WITH_PAGINATION, page.getSort(), page.getFirstResult(), page.getPageSize());
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            result.addAll(getEmployeesFromResultSet(resultSet));
        } catch (Exception e) {
            logger.error("Can't get employee list from department: " + id);
            throw new DepartmentException("Can't get employee list from department");
        }
        return result;
    }

    @Override
    public List<Employee> searchEmployees(Pageable page, Long id, String search) throws DepartmentException {
        List<Employee> result = new ArrayList<>();
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            String query = String.format(QUERY_GET_BY_SEARCH_VALUE, page.getSort(), page.getFirstResult(), page.getPageSize());
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.setString(2, search);
            statement.setString(3, search);
            ResultSet resultSet = statement.executeQuery();
            result.addAll(getEmployeesFromResultSet(resultSet));
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
            st.setDate(3, (Date) employee.getBirthday());
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

    @Override
    public Integer getCount(Long id, String search) throws DepartmentException {
        Integer count = null;
        String query = search == null ? QUERY_GET_COUNT_BY_ID : QUERY_GET_COUNT_BY_SEARCH_VALUE;
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            if (query.equals(QUERY_GET_COUNT_BY_SEARCH_VALUE)) {
                statement.setString(2, search);
                statement.setString(3, search);
            }
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                count = result.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("Can't get row count by search value");
            throw new DepartmentException("Can't get row count by search value");
        }
        return count;
    }

    private List<Employee> getEmployeesFromResultSet(ResultSet resultSet) throws SQLException {
        List<Employee> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(collectData(resultSet));
        }
        return result;
    }

    private Employee getEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
        Employee employee = null;
        if (resultSet.next()) {
            employee = collectData(resultSet);
        }
        return employee;
    }

    private Employee collectData(ResultSet resultSet) throws SQLException {
        Employee employee = ModelFactory.createEmployee();
        employee.setId(resultSet.getLong(ID));
        employee.setDepartment(resultSet.getLong(DEPARTMENT));
        employee.setName(resultSet.getString(NAME));
        employee.setBirthday(resultSet.getDate(BIRTHDAY));
        employee.setEmail(resultSet.getString(EMAIL));
        return employee;
    }
}
