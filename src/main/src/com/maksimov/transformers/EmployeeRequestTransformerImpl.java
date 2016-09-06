package com.maksimov.transformers;

import com.maksimov.models.Employee;
import com.maksimov.utils.factorys.ModelFactory;
import com.maksimov.utils.Utils;
import com.mysql.jdbc.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static com.maksimov.constants.EmployeeConstants.*;

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
        Employee employee = ModelFactory.createEmployee();

        String id = req.getParameter(ID);

        employee.setId(id != null && !StringUtils.isEmptyOrWhitespaceOnly(id) ? Long.parseLong(id) : null);
        employee.setName(req.getParameter(NAME).trim());
        employee.setEmail(req.getParameter(EMAIL).trim());
        employee.setBirthday(Utils.parseDate(req.getParameter(BIRTHDAY)));
        employee.setDepartment(Long.valueOf(req.getParameter(DEPARTMENT)));

        return employee;
    }
}
