var EmployeeService = function () {

    const URL_DELETE = '/employee/delete/{id}/';
    const URL_SAVE = '/employee/save/';
    const URL_GET_ONE = '/employee/get/{id}/';
    const URL_GET_ALL = '/employee/get/all/{id}/';

    return {
        getAll: getAll,
        getOne: getOne,
        deleteOne: deleteOne,
        save: save
    };

    function getAll(id, page) {
        return $.get(URL_GET_ALL.replace('{id}', id), page);
    }

    function getOne(id) {
        return $.get(URL_GET_ONE.replace('{id}', id));
    }

    function deleteOne(id) {
        return $.get(URL_DELETE.replace('{id}', id));
    }

    function save(employee) {
        $.post(URL_SAVE, employee);
    }
};
