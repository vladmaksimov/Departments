package com.maksimov.utils.validators;

import com.maksimov.models.Employee;
import com.maksimov.persistence.EmployeePersistence;
import net.sf.oval.constraint.CheckWithCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 21.07.16.
 */
@Component
public class EmployeeEmailCheck implements CheckWithCheck.SimpleCheck {

    @Autowired
    private EmployeePersistence persistence;

    @Override
    public boolean isSatisfied(Object o, Object o1) {
        Employee employee = (Employee) o;
        Long toTest = persistence.getIdByEmail(o1.toString());
        return toTest == null || toTest.equals(employee.getId());
    }
}
