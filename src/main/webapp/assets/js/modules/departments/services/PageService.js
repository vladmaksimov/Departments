var PageService = function () {

    var URL_EMPLOYEE_SORT = '/page/employee/sort';
    var URL_DEPARTMENT_SORT = '/page/department/sort';
    var URL_PAGE_SIZE = '/page/size';

    return {
        employeeSort: employeeSort,
        departmentSort: departmentSort,
        pageSize: pageSize
    };

    function employeeSort() {
        return $.get(URL_EMPLOYEE_SORT);
    }

    function departmentSort() {
        return $.get(URL_DEPARTMENT_SORT);
    }

    function pageSize() {
        return $.get(URL_PAGE_SIZE);
    }
};