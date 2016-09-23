package com.maksimov.utils.validators;

import com.maksimov.dao.DepartmentDao;
import com.maksimov.models.Department;
import net.sf.oval.constraint.CheckWithCheck;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 21.07.16.
 */
public class DepartmentNameCheck implements CheckWithCheck.SimpleCheck {

    @Autowired
    private DepartmentDao dao;

    @Override
    public boolean isSatisfied(Object o, Object o1) {
        Department department = (Department) o;
        Long toTest = dao.getByName(o1.toString());
        return toTest == null || toTest.equals(department.getId());
    }
}
