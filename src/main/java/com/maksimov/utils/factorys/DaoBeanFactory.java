package com.maksimov.utils.factorys;

import com.maksimov.dao.DepartmentDao;
import com.maksimov.dao.EmployeeDao;
import com.maksimov.dao.impl.DepartmentDaoImpl;
import com.maksimov.dao.impl.EmployeeDaoImpl;


public class DaoBeanFactory {

    private static DepartmentDao departmentDao = new DepartmentDaoImpl();

    private static EmployeeDao employeeDao = new EmployeeDaoImpl();

    public static DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public static EmployeeDao getEmployeeDao() {
        return employeeDao;
    }
}
