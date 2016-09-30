(function ($) {

    $(document).ready(
        function () {
            var departmentController = new DepartmentController();
            departmentController.getDepartments();
        }
    );

})(jQuery);
