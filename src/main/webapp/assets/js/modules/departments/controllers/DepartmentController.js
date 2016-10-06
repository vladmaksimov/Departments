function DepartmentController() {

    const service = new DepartmentService();
    const drawService = new DepartmentDrawService();
    const errorDrawService = new ErrorsDrawService();

    return {
        getDepartments: getDepartments,
        deleteDepartment: deleteDepartment,
        editDepartment: editDepartment,
        saveDepartment: saveDepartment
    };

    function getDepartments() {
        service.getAll().then(
            function (data) {
                drawDepartments(data);
            }, drawErrorPage
        );
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
        var item = {};
        $("form").find("input").each(function () {
            item[this.name] = $(this).val();
        });
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

    function drawDepartments(data) {
        drawService.drawTable(data);
        $(".department-edit").on('click', function (event) {
            editDepartment(getIdFromTableRow(event));
        });
        $(".department-delete").on('click', function (event) {
            deleteDepartment(getIdFromTableRow(event));
        });
        $(".department-employee-show").on('click', function (event) {
            console.log(event);
        });
    }

    function drawEditPage(data, errors) {
        drawService.drawEditPage(data, errors);
        $("#save").on('click', function (event) {
            event.preventDefault();
            saveDepartment();
        });
        $("#back").on('click', function (event) {
            event.preventDefault();
            getDepartments();
        })
    }

    function getIdFromTableRow(event) {
        return $(event.target).parents('tr').attr('id');
    }

    function drawErrorPage(error) {
        errorDrawService.drawErrorPage(error);
    }

}
