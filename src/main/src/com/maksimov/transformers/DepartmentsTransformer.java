package com.maksimov.transformers;

import com.maksimov.models.Department;
import com.mysql.jdbc.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static com.maksimov.controllers.delegators.Processor.PARAM_DEP_NAME;
import static com.maksimov.controllers.delegators.Processor.PARAM_ID;

/**
 * Created on 21.07.16.
 */
public class DepartmentsTransformer {

    public Department transform(HttpServletRequest req) {
        Department department = new Department();
        String id = req.getParameter(PARAM_ID);

        department.setId(id != null && !StringUtils.isEmptyOrWhitespaceOnly(id) ? Long.parseLong(req.getParameter(PARAM_ID)) : null);
        department.setName(req.getParameter(PARAM_DEP_NAME));

        return department;
    }
}
