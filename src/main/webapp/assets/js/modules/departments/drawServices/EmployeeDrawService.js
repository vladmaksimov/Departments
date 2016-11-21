var EmployeeDrawService = function () {

    this.constants = new Constants;
    this.pagination = new PaginationDrawService();
    this.drawUtils = new DrawUtils();
    
};

EmployeeDrawService.prototype.drawTable = function (data, department, sort, size) {
    var _this = this;
    var items = data.content;
    var header = $("<div class='form-header'><span>Employees of:" + department.name + "</span></div>");
    var pagination = _this.pagination.draw(data, sort, size);

    var table = $("<table class='table table-striped table-employee'></table>");

    var tableHeader = $("<thead><tr>" +
        "<th class='column-id'>Name</th>" +
        "<th class='column-name'>Email</th>" +
        "<th class='column-birthday'>Birthday</th>" +
        "<th class='column-action' colspan='2'>Actions</th>" +
        "</tr></thead>");

    var tableBody = $('<tbody></tbody>');

    _.each(items, function (item) {
        var tr = $('<tr></tr>').attr('id', item.id);
        $('<td>' + item.name + '</td>').appendTo(tr);
        $('<td>' + item['email'] + '</td>').appendTo(tr);
        $('<td>' + item['birthday'] + '</td>').appendTo(tr);
        $('<td><button class="btn btn-default employee-edit">Edit</button></td>').appendTo(tr);
        $('<td><button class="btn btn-default employee-delete">Delete</button></td>').appendTo(tr);
        tr.appendTo(tableBody);
    });

    table.append(tableHeader);
    table.append(tableBody);

    _this.constants.CONTAINER.empty().append(header).append(pagination.clone()).append(table).append(pagination);
};

EmployeeDrawService.prototype.drawEditPage = function (data, department, errors) {
    var _this = this;
    var headerMessage = data.id ? 'Edit Employee' : 'Add new Employee';

    var header = $("<div class='form-header'>" + headerMessage + "</div>");

    var form = $("<form class='form-employee'></form>");
    $("<input type='hidden' name='id' value='" + data.id + "'>").appendTo(form);
    $("<input type='hidden' name='department.id' value='" + department.id + "'>").appendTo(form);
    $("<input type='hidden' name='department.name' value='" + department.name + "'>").appendTo(form);

    var nameDiv = _this.drawUtils.drawInput(data, errors, 'Name', 'name', 'Enter name', 'text');
    var emailDiv = _this.drawUtils.drawInput(data, errors, 'Email', 'email', 'Enter email', 'text');
    var birthdayDiv = _this.drawUtils.drawInput(data, errors, 'Birthday', 'birthday', 'Enter birthday', 'date');

    form.append(nameDiv);
    form.append(emailDiv);
    form.append(birthdayDiv);
    $("<button id='save' class='btn btn-default'>Save</button>").appendTo(form);
    $("<button id='back' class='btn btn-info'>Back</button>").appendTo(form);

    _this.constants.CONTAINER.empty().append(header).append(form);
};