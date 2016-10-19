function DepartmentController() {

    var service = new DepartmentService();
    var pageService = new PageService();
    var drawService = new DepartmentDrawService();
    var errorDrawService = new ErrorsDrawService();

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

    function drawDepartments(data, sort, size) {
        drawService.drawTable(data, sort, size);
        $(".department-edit").on('click', function (event) {
            editDepartment(getIdFromTableRow(event));
        });
        $(".department-delete").on('click', function (event) {
            deleteDepartment(getIdFromTableRow(event));
        });
        $(".department-employee-show").on('click', function (event) {
            console.log(event);
        });
        $(".pgn-button").on('click', function (event) {
            event.preventDefault();
            getDepartments(collectPage(event));
        });
        $("#size").on('change', function (event) {
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
            getDepartments(collectPage(event));
        })
    }

    function collectPage(event) {
        var element = $(event.target);
        var size = $("#size").val();
        var page = element.is('button') ? element.val() : element.parents('button').val();

        return {page: page, size: size};
    }

    function getIdFromTableRow(event) {
        return $(event.target).parents('tr').attr('id');
    }

    function drawErrorPage(error) {
        errorDrawService.drawErrorPage(error);
    }

}
