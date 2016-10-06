var DepartmentDrawService = function () {

    this.constants = new Constants;

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
        let tr = $('<tr></tr>').attr('id', item.id);
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

DepartmentDrawService.prototype.drawEditPage = function (data, errors) {
    const _this = this;
    const headerMessage = data.id ? 'Edit department with id: '.concat(data.id) : 'Add new Department';

    const header = $("<div class='form-header'>" + headerMessage + "</div>");

    let form = $("<form class='form-employee'></form>");
    $(`<input type='hidden' name='id' value='${data.id}'>`).appendTo(form);

    let nameDiv = $("<div class='form-group'></div>");
    $("<label for='name'>Department name:</label>").appendTo(nameDiv);

    let input = $("<input type='text' class='form-control' id='name' name='name' placeholder='Email'>");
    input.val(data.name);
    input.appendTo(nameDiv);

    if (errors && errors.name) {
        const errorList = errors.name;
        let errorDiv = $("<div class='error-validation'></div>");
        $.each(errorList, function (i, val) {
            $(`<div>${val}</div>`).appendTo(errorDiv);
        });
        errorDiv.appendTo(nameDiv);
    }

    form.append(nameDiv);
    $("<button id='save' class='btn btn-default'>Save</button>").appendTo(form);
    $("<button id='back' class='btn btn-info'>Back</button>").appendTo(form);

    _this.constants.CONTAINER.empty().append(header).append(form);
};
