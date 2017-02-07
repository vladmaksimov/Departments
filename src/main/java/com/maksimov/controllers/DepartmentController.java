package com.maksimov.controllers;

import com.maksimov.exceptions.CustomValidateException;
import com.maksimov.exceptions.ServiceException;
import com.maksimov.models.Department;
import com.maksimov.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vladislav Maksimov
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @RequestMapping("/get/all")
    public Page<Department> showDepartment(@PageableDefault(sort = {"id"}) Pageable page, String search) throws ServiceException {
        return (search == null) ? service.getDepartments(page) : service.searchDepartments(page, search);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(Department department) throws ServiceException, CustomValidateException {
        service.put(department);
    }

    @RequestMapping("/get/{id}")
    public Department getOne(@PathVariable("id") Long id) throws ServiceException {
        return service.getById(id);
    }

    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) throws ServiceException {
        service.delete(id);
    }

}
