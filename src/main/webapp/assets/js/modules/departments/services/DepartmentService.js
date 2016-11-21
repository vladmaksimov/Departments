var DepartmentService = function () {

    const URL_DELETE = '/department/delete/{id}/';
    const URL_SAVE = '/department/save/';
    const URL_GET_ONE = '/department/get/{id}/';
    const URL_GET_ALL = '/department/get/all/';

    return {
        getAll: getAll,
        getOne: getOne,
        deleteOne: deleteOne,
        save: save
    };

    function getAll(page) {
        return $.get(URL_GET_ALL, page);
    }

    function getOne(id) {
        return $.get(URL_GET_ONE.replace('{id}', id));
    }

    function deleteOne(id) {
        return $.get(URL_DELETE.replace('{id}', id));
    }

    function save(item) {
        return $.post(URL_SAVE, item);
    }

};
