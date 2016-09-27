package com.maksimov.utils.validators;

import com.maksimov.models.Department;
import com.maksimov.persistence.DepartmentPersistence;
import net.sf.oval.constraint.CheckWithCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 21.07.16.
 */
@Component
public class DepartmentNameCheck implements CheckWithCheck.SimpleCheck {

    @Autowired
    private DepartmentPersistence persistence;

    @Override
    public boolean isSatisfied(Object o, Object o1) {
        Department department = (Department) o;
        Long toTest = persistence.getIdByName(o1.toString());
        return toTest == null || toTest.equals(department.getId());
    }
}
