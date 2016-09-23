package com.maksimov.utils.validators;

import com.maksimov.dao.EmployeeDao;
import com.maksimov.models.Employee;
import net.sf.oval.constraint.CheckWithCheck;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 21.07.16.
 */
public class EmployeeEmailCheck implements CheckWithCheck.SimpleCheck {

    @Autowired
    private EmployeeDao dao;

    @Override
    public boolean isSatisfied(Object o, Object o1) {
        Employee employee = (Employee) o;
        Long toTest = dao.getByEmail(o1.toString());
        return toTest == null || toTest.equals(employee.getId());
    }
}
