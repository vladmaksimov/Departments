var DepartmentService = function () {

    const URL_DELETE = '';
    const URL_SAVE = '';
    const URL_GET_ONE = '';
    const URL_GET_ALL = '/departments';

    return {
        getAll: getAll,
        getOne: getOne,
        deleteOne: deleteOne,
        save: save
    };

    function getAll() {
        return $.ajax({
            url: URL_GET_ALL,
            type: "get"
        });
    }

    function getOne() {

    }

    function deleteOne() {

    }

    function save() {

    }

};
