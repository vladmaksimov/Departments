package com.maksimov.utils.factorys;

import com.maksimov.data.Page;
import com.maksimov.models.Department;
import com.maksimov.models.Employee;

import java.sql.Date;

/**
 * Created on 11.08.16.
 */
public class ModelFactory {

    public static Department createDepartment() {
        return new Department();
    }

    public static Employee createEmployee() {
        return new Employee();
    }

    public static Date createSqlDate(Long date) {
        return new Date(date);
    }

    public static Page createPage(Integer page, Integer size, String sort) {
        return new Page(page, size, sort);
    }

}
