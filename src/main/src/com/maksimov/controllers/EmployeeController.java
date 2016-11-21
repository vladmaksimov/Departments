package com.maksimov.controllers;

import com.maksimov.exceptions.CustomValidateException;
import com.maksimov.exceptions.ServiceException;
import com.maksimov.models.Employee;
import com.maksimov.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * @author Vladislav Maksimov
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/get/all/{id}")
    public Page<Employee> showEmployee(@PathVariable("id") Long id,
                                       @PageableDefault(sort = {"id"}) Pageable pageable,
                                       String search) throws ServiceException {
        return (search == null) ? employeeService.getEmployees(pageable, id) : employeeService.searchEmployees(pageable, id, search);
    }

    @RequestMapping("/get/{id}")
    public Employee getOne(@PathVariable("id") Long id) throws ServiceException {
        return employeeService.getById(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(Employee employee) throws ServiceException, CustomValidateException {
        employeeService.put(employee);
    }

    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) throws ServiceException {
        employeeService.delete(id);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
