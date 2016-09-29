package com.maksimov.controllers;

import com.maksimov.constants.PageConstants;
import com.maksimov.exceptions.CustomValidateException;
import com.maksimov.exceptions.ServiceException;
import com.maksimov.models.Department;
import com.maksimov.models.Employee;
import com.maksimov.services.DepartmentService;
import com.maksimov.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

/**
 * @author Vladislav Maksimov
 */
@Controller
@RequestMapping("/department")
public class EmployeeController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/{id}/employees")
    public String showEmployee(@PathVariable("id") Long id,
                               @PageableDefault(sort = {"id"}) Pageable pageable,
                               Model model,
                               String search) throws ServiceException {
        Page<Employee> page = (search == null) ? employeeService.getEmployees(pageable, id) : employeeService.searchEmployees(pageable, id, search);
        Department department = departmentService.getById(id);

        model.addAttribute("page", page);
        model.addAttribute("department", department);
        model.addAttribute("search", search);
        model.addAttribute("sortList", PageConstants.PAGE_EMPLOYEE_SORT_LIST);
        model.addAttribute("sizeList", PageConstants.PAGE_SIZE_LIST);
        return "employee/table.employees";
    }

    @RequestMapping("{department}/employee/form")
    public String form(@PathVariable("department") Long department, Model model) throws ServiceException {
        Department d = departmentService.getById(department);
        model.addAttribute("department", d);
        return "employee/form.employee";
    }

    @RequestMapping("{department}/employee/edit/{id}")
    public String edit(@PathVariable("department") Long department,
                       @PathVariable("id") Long id,
                       Model model) throws ServiceException {

        Employee employee = employeeService.getById(id);
        Department d = departmentService.getById(department);

        model.addAttribute("employee", employee);
        model.addAttribute("department", d);
        return "employee/form.employee";
    }

    @RequestMapping("/employee/form/clear")
    public String clearForm(Model model) throws ServiceException {
        List<Department> departments = departmentService.getAll();
        model.addAttribute("departments", departments);
        return "employee/form.employee";
    }

    @RequestMapping(value = "/employee/put", method = RequestMethod.POST)
    public String save(Employee employee, Model model) throws ServiceException {
        try {
            employeeService.put(employee);
        } catch (CustomValidateException e) {
            Department department = departmentService.getById(employee.getDepartment().getId());
            model.addAttribute("employee", employee);
            model.addAttribute("department", department);
            model.addAttribute("errors", e.getErrors());
            return "employee/form.employee";
        }

        return String.format("redirect:/department/%d/employees/", employee.getDepartment().getId());
    }

    @RequestMapping("{department}/employee/delete/{id}")
    public String delete(@PathVariable("department") Long department, @PathVariable("id") Long id) throws ServiceException {
        employeeService.delete(id);
        return String.format("redirect:/department/%d/employees/", department);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
