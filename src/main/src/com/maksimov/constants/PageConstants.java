package com.maksimov.constants;

import java.util.ArrayList;
import java.util.List;

import static com.maksimov.constants.EmployeeConstants.*;

/**
 * Created on 16.09.16.
 */
public class PageConstants {

    public static final List<Integer> PAGE_SIZE_LIST = new ArrayList<>();
    public static final List<String> PAGE_DEPARTMENT_SORT_LIST = new ArrayList<>();
    public static final List<String> PAGE_EMPLOYEE_SORT_LIST = new ArrayList<>();

    static {
        PAGE_SIZE_LIST.add(10);
        PAGE_SIZE_LIST.add(20);
        PAGE_SIZE_LIST.add(30);
        PAGE_SIZE_LIST.add(40);
        PAGE_SIZE_LIST.add(50);

        PAGE_DEPARTMENT_SORT_LIST.add(ID);
        PAGE_DEPARTMENT_SORT_LIST.add(NAME);

        PAGE_EMPLOYEE_SORT_LIST.add(ID);
        PAGE_EMPLOYEE_SORT_LIST.add(NAME);
        PAGE_EMPLOYEE_SORT_LIST.add(EMAIL);
    }

}
