function DepartmentController() {

    var service = new DepartmentService();
    var pageService = new PageService();
    var drawService = new DepartmentDrawService();
    var errorDrawService = new ErrorsDrawService();
    var employeeController = new EmployeeController();
    var utils = new ControllerUtils();

    return {
        getDepartments: getDepartments,
        deleteDepartment: deleteDepartment,
        editDepartment: editDepartment,
        saveDepartment: saveDepartment
    };

    function getDepartments(page) {
        $.when(service.getAll(page), pageService.departmentSort(), pageService.pageSize()).then(success, drawErrorPage);

        function success(data, sort, size) {
            drawDepartments(data[0], sort[0], size[0]);
        }
    }

    function deleteDepartment(id) {
        if (!id) {
            errorDrawService.drawErrorPage('Incorrect id!');
        }

        service.deleteOne(id).then(
            function () {
                getDepartments();
            }, drawErrorPage
        )
    }

    function editDepartment(id) {
        if (!id) {
            errorDrawService.drawErrorPage('Incorrect id!');
        }

        service.getOne(id).then(
            function (data) {
                drawEditPage(data, null);
            }, drawErrorPage
        )
    }

    function saveDepartment() {
        var item = utils.collectFormData();
        service.save(item).then(
            function (data) {
                if (data) {
                    drawEditPage(data.object, data['errors']);
                } else {
                    getDepartments();
                }
            }, drawErrorPage
        )
    }

    //private functions

    function drawDepartments(data, sort, size) {
        drawService.drawTable(data, sort, size);
        $(".department-edit").on('click', function (event) {
            editDepartment(utils.getIdFromTableRow(event));
        });
        $(".department-delete").on('click', function (event) {
            deleteDepartment(utils.getIdFromTableRow(event));
        });
        $(".department-employee-show").on('click', function (event) {
            employeeController.getEmployees(utils.getIdFromTableRow(event));
        });
        $(".pgn-button").on('click', function (event) {
            event.preventDefault();
            getDepartments(utils.collectPage(event));
        });
        $(".size").on('change', function (event) {
            var size = $(event.target).val();
            getDepartments({size: size})
        })
    }

    function drawEditPage(data, errors) {
        drawService.drawEditPage(data, errors);
        $("#save").on('click', function (event) {
            event.preventDefault();
            saveDepartment();
        });
        $("#back").on('click', function (event) {
            event.preventDefault();
            getDepartments(utils.collectPage(event));
        })
    }

    function drawErrorPage(error) {
        errorDrawService.drawErrorPage(error);
    }

}
