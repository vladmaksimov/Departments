package com.maksimov.utils.factorys;

import com.maksimov.services.DepartmentService;
import com.maksimov.services.EmployeeService;
import com.maksimov.services.impl.DepartmentServiceImpl;
import com.maksimov.services.impl.EmployeeServiceImpl;

/**
 * Created on 09.08.16.
 */
public class ServiceBeanFactory {

    private static DepartmentService departmentService = new DepartmentServiceImpl();

    private static EmployeeService employeeService = new EmployeeServiceImpl();

    public static DepartmentService getDepartmentService() {
        return departmentService;
    }

    public static EmployeeService getEmployeeService() {
        return employeeService;
    }

}