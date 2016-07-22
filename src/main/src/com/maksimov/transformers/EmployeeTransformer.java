package com.maksimov.transformers;

import com.maksimov.models.Employee;
import com.maksimov.utils.Utils;
import com.mysql.jdbc.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static com.maksimov.controllers.delegators.Processor.*;

/**
 * Created on 21.07.16.
 */
public class EmployeeTransformer {

    public Employee transform(HttpServletRequest req) {
        Employee employee = new Employee();

        String id = req.getParameter(PARAM_ID);

        employee.setId(id != null && !StringUtils.isEmptyOrWhitespaceOnly(id) ? Long.parseLong(req.getParameter(PARAM_ID)) : null);
        employee.setName(req.getParameter(PARAM_EMP_NAME));
        employee.setEmail(req.getParameter(PARAM_EMP_EMAIL));
        employee.setBirthday(Utils.parseDate(req.getParameter(PARAM_EMP_BIRTHDAY)));
        employee.setDepartment(Long.valueOf(req.getParameter(PARAM_EMP_DEPARTMENT)));

        return employee;
    }
}
