package com.maksimov.utils.validators;

import com.maksimov.dao.EmployeeDao;
import com.maksimov.models.Employee;
import net.sf.oval.constraint.CheckWithCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 21.07.16.
 */
@Component
public class EmployeeEmailCheck implements CheckWithCheck.SimpleCheck {

    @Autowired
    private EmployeeDao dao;

    @Override
    public boolean isSatisfied(Object o, Object o1) {
        Employee employee = (Employee) o;
        Employee toTest = dao.getByEmail(o1.toString());
        return toTest == null || toTest.getId().equals(employee.getId());
    }
}
