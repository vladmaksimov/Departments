var DepartmentDrawService = function () {

    this.constants = new Constants;

    // return {
    //     drawTable: drawTable
    // };
    //
    // function drawTable(data) {
    //     const items = data.content;
    //     const header = $('<div class="form-header"><span>Department List:</span></div>');
    //
    //     var table = $("<table class='table table-striped table-department'></table>");
    //
    //
    //     const tableHeader = $("<thead><tr>" +
    //         "<th class='column-id'>id</th>" +
    //         "<th class='column-name'>name</th>" +
    //         "<th class='column-action' colspan='3'>Actions</th>" +
    //         "</tr></thead>");
    //
    //    
    //     let tableBody = $('<tbody></tbody>');
    //
    //     $.each(items, function (i, item) {
    //         let tr = $('<tr></tr>');
    //         $(`<td>${item.id}</td>`).appendTo(tr);
    //         $(`<td>${item.name}</td>`).appendTo(tr);
    //         $('<td><button class="btn btn-default edit-button">Edit</button></td>').appendTo(tr);
    //         $('<td><button class="btn btn-default edit-delete">Delete</button></td>').appendTo(tr);
    //         $('<td><button class="btn btn-default edit-show">Show employees</button></td>').appendTo(tr);
    //         tr.appendTo(tableBody);
    //     });
    //
    //     table.append(tableHeader);
    //     table.append(tableBody);
    //
    //     constants.CONTAINER.empty().append(table);
    // }
};

DepartmentDrawService.prototype.drawTable = function drawTable(data) {
    const _this = this;
    const items = data.content;
    const header = $('<div class="form-header"><span>Department List:</span></div>');

    var table = $("<table class='table table-striped table-department'></table>");


    const tableHeader = $("<thead><tr>" +
        "<th class='column-id'>id</th>" +
        "<th class='column-name'>name</th>" +
        "<th class='column-action' colspan='3'>Actions</th>" +
        "</tr></thead>");


    let tableBody = $('<tbody></tbody>');

    $.each(items, function (i, item) {
        let tr = $('<tr></tr>');
        $(`<td>${item.id}</td>`).appendTo(tr);
        $(`<td>${item.name}</td>`).appendTo(tr);
        $('<td><button class="btn btn-default department-edit">Edit</button></td>').appendTo(tr);
        $('<td><button class="btn btn-default department-delete">Delete</button></td>').appendTo(tr);
        $('<td><button class="btn btn-default department-employee-show">Show employees</button></td>').appendTo(tr);
        tr.appendTo(tableBody);
    });

    table.append(tableHeader);
    table.append(tableBody);

    _this.constants.CONTAINER.empty().append(table);
};
