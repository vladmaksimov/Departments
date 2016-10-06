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

    function getAll() {
        return $.ajax(options(URL_GET_ALL));
    }

    function getOne(id) {
        const url = URL_GET_ONE.replace('{id}', id);
        return $.ajax(options(url))
    }

    function deleteOne(id) {
        const url = URL_DELETE.replace('{id}', id);
        return $.ajax(options(url))
    }

    function save(item) {
        return $.ajax({url: URL_SAVE, data: item, type: "post"})
    }

    //private function

    function options(url) {
        return {
            url: url,
            type: "get"
        }
    }

};
