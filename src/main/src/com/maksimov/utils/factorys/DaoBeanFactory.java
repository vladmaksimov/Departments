package com.maksimov.utils.factorys;

import com.maksimov.dao.DepartmentDao;
import com.maksimov.dao.EmployeeDao;
import com.maksimov.dao.impl.DepartmentDaoImpl;
import com.maksimov.dao.impl.DepartmentHbmDaoImpl;
import com.maksimov.dao.impl.EmployeeDaoImpl;
import com.maksimov.dao.impl.EmployeeHbmDaoImpl;
import com.maksimov.models.Department;


public class DaoBeanFactory {

    private static DepartmentDao departmentDao = new DepartmentDaoImpl();

    private static DepartmentDao departmentHbmDao = new DepartmentHbmDaoImpl(Department.class);

    private static EmployeeDao employeeDao = new EmployeeDaoImpl();

    private static EmployeeDao employeeHbmDao = new EmployeeHbmDaoImpl();

    public static DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public static DepartmentDao getDepartmentHbmDao() {
        return departmentHbmDao;
    }

    public static EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public static EmployeeDao getEmployeeHbmDao() {
        return employeeHbmDao;
    }
}
