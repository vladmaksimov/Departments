package com.maksimov.controllers;

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
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Vladislav Maksimov
 */
@Controller
@RequestMapping("/")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @RequestMapping("/")
    public String main(Model model, @PageableDefault(sort = {"id"}) Pageable page, String search) throws ServiceException {
        return "index";
    }

    @RequestMapping("/departments")
    @ResponseBody
    public Page<Department> showDepartment(@PageableDefault(sort = {"id"}) Pageable page, String search) throws ServiceException {
        return (search == null) ? service.getDepartments(page) : service.searchDepartments(page, search);
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
