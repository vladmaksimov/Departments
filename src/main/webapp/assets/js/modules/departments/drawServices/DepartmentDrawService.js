var DepartmentDrawService = function () {

    this.constants = new Constants;
    this.pagination = new PaginationDrawService();
    this.drawUtils = new DrawUtils();

};

DepartmentDrawService.prototype.drawTable = function drawTable(data, sort, size) {
    var _this = this;
    var items = data.content;
    var header = $('<div class="form-header"><span>Department List:</span></div>');
    var pagination = _this.pagination.draw(data, sort, size);

    var table = $("<table class='table table-striped table-department'></table>");

    var tableHeader = $("<thead><tr>" +
        "<th class='column-id'>id</th>" +
        "<th class='column-name'>name</th>" +
        "<th class='column-action' colspan='3'>Actions</th>" +
        "</tr></thead>");

    var tableBody = $('<tbody></tbody>');

    _.each(items, function (item) {
        var tr = $('<tr></tr>').attr('id', item.id);
        $('<td>' + item.id + '</td>').appendTo(tr);
        $('<td>' + item.name + '</td>').appendTo(tr);
        $('<td><button class="btn btn-default department-edit">Edit</button></td>').appendTo(tr);
        $('<td><button class="btn btn-default department-delete">Delete</button></td>').appendTo(tr);
        $('<td><button class="btn btn-default department-employee-show">Show employees</button></td>').appendTo(tr);
        tr.appendTo(tableBody);
    });

    table.append(tableHeader);
    table.append(tableBody);

    _this.constants.CONTAINER.empty().append(header).append(pagination.clone()).append(table).append(pagination);
};

DepartmentDrawService.prototype.drawEditPage = function (data, errors) {
    var _this = this;
    var headerMessage = data.id ? 'Edit department with id: '.concat(data.id) : 'Add new Department';

    var header = $("<div class='form-header'>" + headerMessage + "</div>");

    var form = $("<form class='form-employee'></form>");
    $("<input type='hidden' name='id' value='" + data.id + "'>").appendTo(form);

    var nameDiv = _this.drawUtils.drawInput(data, errors, 'Name', 'name', 'Enter name', 'text');

    form.append(nameDiv);
    $("<button id='save' class='btn btn-default'>Save</button>").appendTo(form);
    $("<button id='back' class='btn btn-info'>Back</button>").appendTo(form);

    _this.constants.CONTAINER.empty().append(header).append(form);
};
