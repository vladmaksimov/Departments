package com.maksimov.transformers;

import com.maksimov.models.Employee;
import com.maksimov.utils.Utils;
import com.mysql.jdbc.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static com.maksimov.controllers.delegators.Processor.*;

/**
 * This class consists method which operates with the {@link HttpServletRequest} object
 * and creates the {@link Employee} object from request parameters.
 * <p>
 * Created on 21.07.16.
 *
 * @author Vladislav Maksimov
 * @see com.maksimov.controllers.Controller
 * @see Employee
 */
public class EmployeeRequestTransformerImpl implements RequestTransformer {

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
