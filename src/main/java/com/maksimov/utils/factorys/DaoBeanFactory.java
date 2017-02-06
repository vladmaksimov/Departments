package com.maksimov.utils.factorys;

import com.maksimov.dao.DepartmentDao;
import com.maksimov.dao.EmployeeDao;
import com.maksimov.dao.impl.DepartmentDaoImpl;
import com.maksimov.dao.impl.EmployeeDaoImpl;
import com.maksimov.models.Department;
import com.maksimov.models.Employee;


public class DaoBeanFactory {

    private static DepartmentDao departmentDao = new DepartmentDaoImpl(Department.class);

    private static EmployeeDao employeeDao = new EmployeeDaoImpl(Employee.class);

    public static DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public static EmployeeDao getEmployeeDao() {
        return employeeDao;
    }
}
