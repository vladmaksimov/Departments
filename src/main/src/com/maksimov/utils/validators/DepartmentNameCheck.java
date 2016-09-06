package com.maksimov.utils.validators;

import com.maksimov.dao.DepartmentDao;
import com.maksimov.models.Department;
import com.maksimov.utils.factorys.DaoBeanFactory;
import net.sf.oval.constraint.CheckWithCheck;

/**
 * Created on 21.07.16.
 */
public class DepartmentNameCheck implements CheckWithCheck.SimpleCheck {

    private DepartmentDao dao = DaoBeanFactory.getDepartmentDao();

    @Override
    public boolean isSatisfied(Object o, Object o1) {
        Department department = (Department) o;
        Department toTest = dao.getByName(o1.toString());
        return toTest == null || toTest.getId().equals(department.getId());
    }
}
