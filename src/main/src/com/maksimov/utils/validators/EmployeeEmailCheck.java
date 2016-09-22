package com.maksimov.utils.validators;

import com.maksimov.dao.EmployeeDao;
import com.maksimov.models.Employee;
import com.maksimov.utils.SpringUtils;
import net.sf.oval.constraint.CheckWithCheck;

/**
 * Created on 21.07.16.
 */
public class EmployeeEmailCheck implements CheckWithCheck.SimpleCheck {

    private EmployeeDao dao = SpringUtils.getBean(EmployeeDao.class);

    @Override
    public boolean isSatisfied(Object o, Object o1) {
        Employee employee = (Employee) o;
        Employee toTest = dao.getByEmail(o1.toString());
        return toTest == null || toTest.getId().equals(employee.getId());
    }
}
