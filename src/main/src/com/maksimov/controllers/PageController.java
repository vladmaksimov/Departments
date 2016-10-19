package com.maksimov.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.maksimov.constants.PageConstants.PAGE_DEPARTMENT_SORT_LIST;
import static com.maksimov.constants.PageConstants.PAGE_EMPLOYEE_SORT_LIST;
import static com.maksimov.constants.PageConstants.PAGE_SIZE_LIST;

/**
 * Created by Vlad on 14.10.2016.
 */
@RestController
@RequestMapping("/page")
public class PageController {

    @RequestMapping("/employee/sort")
    public List<String> getEmployeeSortList() {
        return PAGE_EMPLOYEE_SORT_LIST;
    }

    @RequestMapping("/department/sort")
    public List<String> getDepartmentSortList() {
        return PAGE_DEPARTMENT_SORT_LIST;
    }

    @RequestMapping("/size")
    public List<Integer> getPageSizeList() {
        return PAGE_SIZE_LIST;
    }
}
