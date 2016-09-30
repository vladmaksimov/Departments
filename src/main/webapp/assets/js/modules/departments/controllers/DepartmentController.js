function DepartmentController() {

    const service = new DepartmentService();
    const drawService = new DepartmentDrawService();
    const errorDrawService = new ErrorsDrawService();

    return {
        getDepartments: getDepartments
    };

    function getDepartments() {
        service.getAll().then(
            function (data) {
                drawDepartments(data);
            },
            function (error) {
                errorDrawService.drawErrorPage(error);
            }
        );
    }

    //private functions

    function drawDepartments(data) {
        drawService.drawTable(data);
        $(".department-edit").on('click', function (event) {
            console.log(event);
        });
        $(".department-delete").on('click', function (event) {
            console.log(event);
        });
        $(".department-employee-show").on('click', function (event) {
            console.log(event);
        });
    }

}
    
