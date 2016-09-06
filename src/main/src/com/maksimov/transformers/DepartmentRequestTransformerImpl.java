package com.maksimov.transformers;

import com.maksimov.models.Department;
import com.maksimov.utils.factorys.ModelFactory;
import com.mysql.jdbc.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static com.maksimov.constants.DepartmentConstants.*;

/**
 * This class consists method which operates with the {@link HttpServletRequest} object
 * and creates the {@link Department} object from request parameters.
 * <p>
 * Created on 21.07.16.
 *
 * @author Vladislav Maksimov
 * @see com.maksimov.controllers.Controller
 * @see Department
 */
public class DepartmentRequestTransformerImpl implements RequestTransformer {

    public Department transform(HttpServletRequest req) {
        Department department = ModelFactory.createDepartment();
        String id = req.getParameter(ID);

        department.setId(id != null && !StringUtils.isEmptyOrWhitespaceOnly(id) ? Long.parseLong(req.getParameter(ID)) : null);
        department.setName(req.getParameter(NAME));

        return department;
    }
}
