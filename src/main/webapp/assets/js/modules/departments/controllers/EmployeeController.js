function EmployeeController() {

    var employeeService = new EmployeeService();
    var departmentService = new DepartmentService();
    var drawService = new EmployeeDrawService();
    var pageService = new PageService();
    var errorDrawService = new ErrorsDrawService();
    var utils = new ControllerUtils();
    
    var department;

    return {
        getEmployees: getEmployees,
        deleteEmployee: deleteEmployee,
        editEmployee: editEmployee,
        saveEmployee: saveEmployee
    };

    function getEmployees(id, page) {
        department = id;
        $.when(employeeService.getAll(id, page), departmentService.getOne(id), pageService.employeeSort(), pageService.pageSize()).then(success, drawErrorPage);

        function success(data, department, sort, size) {
            drawEmployees(data[0], department[0], sort[0], size[0]);
        }
    }

    function deleteEmployee(id) {
        if (!id) {
            errorDrawService.drawErrorPage('Incorrect id!');
        }

        employeeService.deleteOne(id).then(
            function () {
                getEmployees(department);
            }, drawErrorPage
        )
    }

    function editEmployee(id) {
        if (!id) {
            errorDrawService.drawErrorPage('Incorrect id!');
        }

        employeeService.getOne(id).then(
            function (data) {
                drawEditPage(data, department, null);
            }, drawErrorPage
        )
    }

    function saveEmployee() {
        var item = utils.collectFormData();
        employeeService.save(item).then(
            function (data) {
                if (data) {
                    drawEditPage(data.object, data['errors']);
                } else {
                    getDepartments(department);
                }
            }, drawErrorPage
        )
    }

    //private functions

    function drawEmployees(data, dep, sort, size) {
        drawService.drawTable(data, dep, sort, size);
        department = dep;
        $(".employee-edit").on('click', function (event) {
            editEmployee(utils.getIdFromTableRow(event));
        });
        $(".employee-delete").on('click', function (event) {
            deleteEmployee(utils.getIdFromTableRow(event));
        });
        $(".pgn-button").on('click', function (event) {
            event.preventDefault();
            getEmployees(department.id, utils.collectPage(event));
        });
        $(".size").on('change', function (event) {
            var size = $(event.target).val();
            getEmployees(department.id, {size: size})
        })
    }

    function drawEditPage(data, department, errors) {
        drawService.drawEditPage(data, department, errors);
        $("#save").on('click', function (event) {
            event.preventDefault();
            saveEmployee();
        });
        $("#back").on('click', function (event) {
            event.preventDefault();
            getEmployees(department, utils.collectPage(event));
        })
    }

    function drawErrorPage(error) {
        errorDrawService.drawErrorPage(error);
    }
}
