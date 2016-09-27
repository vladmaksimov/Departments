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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created on 23.09.16.
 */
@Controller
@RequestMapping("/department")
public class EmployeeController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("employees/{id}")
    public String showEmployee(@PathVariable("id") Long id, Model model, Pageable pageable, String search) throws ServiceException {
        Page<Employee> page = (search == null) ? employeeService.getEmployees(pageable, id) : employeeService.searchEmployees(pageable, id, search);
        Department department = departmentService.getById(id);

        model.addAttribute("page", page);
        model.addAttribute("department", department);
        model.addAttribute("search", search);
        model.addAttribute("sortList", PageConstants.PAGE_EMPLOYEE_SORT_LIST);
        model.addAttribute("sizeList", PageConstants.PAGE_SIZE_LIST);
        return "employee/table.employees";
    }

    @RequestMapping("/employee/form")
    public String form(Model model, Long department, Long id) throws ServiceException {
        if (id != null) {
            Employee employee = employeeService.getById(id);
            model.addAttribute("employee", employee);
        }
        List<Department> departments = new ArrayList<>();

        Department d = departmentService.getById(department);
        departments.add(d);

        model.addAttribute("departments", departments);
        return "employee/form.employee";
    }

    @RequestMapping("/employee/form/clear")
    public String clearForm(Model model) throws ServiceException {
        List<Department> departments = departmentService.getAll();
        model.addAttribute("departments", departments);
        return "employee/form.employee";
    }

    @RequestMapping(value = "/employee/put", method = RequestMethod.POST)
    public String save(Employee employee) throws ServiceException {
        try {
            employeeService.put(employee);
        } catch (CustomValidateException e) {
            e.printStackTrace();
        }

        return "redirect:/department/employees/" + employee.getDepartment().getId();
    }

    @RequestMapping("/employee/delete")
    public String delete(Long id, Long department) throws ServiceException {
        employeeService.delete(id);
        return "redirect:/department/employees/" + department;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
