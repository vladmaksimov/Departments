package com.maksimov.controllers;

import com.maksimov.constants.PageConstants;
import com.maksimov.exceptions.CustomValidateException;
import com.maksimov.exceptions.ServiceException;
import com.maksimov.models.Department;
import com.maksimov.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created on 23.09.16.
 */
@Controller
@RequestMapping("/")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @RequestMapping("/")
    public String showDepartment(Model model, @PageableDefault(sort = {"id"}) Pageable page, String search) throws ServiceException {
        Page<Department> result = (search == null) ? service.getDepartments(page) : service.searchDepartments(page, search);

        model.addAttribute("page", result);
        model.addAttribute("search", search);
        model.addAttribute("sortList", PageConstants.PAGE_DEPARTMENT_SORT_LIST);
        model.addAttribute("sizeList", PageConstants.PAGE_SIZE_LIST);
        return "department/table.departments";
    }

    @RequestMapping(value = "/department/put", method = RequestMethod.POST)
    public String save(Department department, Model model) throws ServiceException {
        try {
            service.put(department);
        } catch (CustomValidateException e) {
            model.addAttribute("department", department);
            model.addAttribute("errors", e.getErrors());
            return "department/form.department";
        }
        return "redirect:/";
    }

    @RequestMapping("/department/form")
    public String form() throws ServiceException {
        return "department/form.department";
    }

    @RequestMapping("/department/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) throws ServiceException {
        Department department = service.getById(id);
        model.addAttribute("department", department);
        return "department/form.department";
    }

    @RequestMapping("/department/delete/{id}")
    public String delete(@PathVariable("id") Long id) throws ServiceException {
        service.delete(id);
        return "redirect:/";
    }

}
