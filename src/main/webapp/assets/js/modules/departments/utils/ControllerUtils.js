var ControllerUtils = function () {

    return {
        collectPage: collectPage,
        getIdFromTableRow: getIdFromTableRow,
        collectFormData: collectFormData
    };

    function collectPage(event) {
        var element = $(event.target);
        var size = $("#size").val();
        var page = element.is('button') ? element.val() : element.parents('button').val();

        return {page: page, size: size};
    }

    function getIdFromTableRow(event) {
        return $(event.target).parents('tr').attr('id');
    }
    
    function collectFormData() {
        var item = {};
        $("form").find("input").each(function () {
            item[this.name] = $(this).val();
        });
        return item;
    }
};