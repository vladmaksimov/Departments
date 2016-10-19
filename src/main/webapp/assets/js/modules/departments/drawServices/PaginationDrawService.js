var PaginationDrawService = function () {

    return {
        draw: draw
    };

    function draw(data, sortList, sizeList) {
        var mainDiv = $("<div></div>");
        var mainPaginationDiv = $("<div class='text-center pagination-tab'>");

        var sort = drawSort(sortList, data);
        var pagination = drawPagination(data);
        var size = drawSize(sizeList, data);

        mainPaginationDiv.append(sort).append(pagination).append(size).appendTo(mainDiv);

        return mainDiv;
    }

    //private functions

    function drawSort(sortList, data) {
        var form = $("<form class='navbar-form navbar-left'></form>");
        var div = $("<div class='pagination-left form-group'></div>");
        $("<span>Sort by:</span>").appendTo(div);

        var select = $("<select class='form-control sort-selected' name='sort' title='Select sort field'></select>");
        _.each(sortList, function (item) {
            var option = $("<option>" + item + "</option>");
            if (option === data.sort[0]['property']) {
                option.attr('selected', true);
            }
            option.appendTo(select);
        });
        select.appendTo(div);
        div.appendTo(form);

        return form;
    }

    function drawPagination(data) {
        var form = $("<form class='pagination-center navbar-form'></form>");
        var div = $("<div class='form-group'></div>");

        var prevButton = $("<button class='btn btn-default pgn-button' value='" + (data['number'] - 1) + "'></button>");
        if (data.first) {
            prevButton.prop('disabled', true);
        }
        $("<span class='glyphicon glyphicon-menu-left' aria-hidden='true'></span>").appendTo(prevButton);

        var pageCount = $("<span>" + (data['number'] + 1) + "/" + data['totalPages'] + "</span>");

        var nextButton = $("<button class='btn btn-default pgn-button' value='" + (data['number'] + 1) + "'></button>");
        if (data.last) {
            nextButton.prop('disabled', true);
        }
        $("<span class='glyphicon glyphicon-menu-right' aria-hidden='true'></span>").appendTo(nextButton);

        div.append(prevButton).append(pageCount).append(nextButton).appendTo(form);

        return form;
    }

    function drawSize(sizeList, data) {
        var form = $("<form class='navbar-form navbar-right'></form>");
        var div = $("<div class='form-group'></div>");
        $("<span>On page:</span>").appendTo(div);

        var select = $("<select class='form-control' id='size' title='Select page size'></select>");
        _.each(sizeList, function (item) {
            var option = $("<option>" + item + "</option>");
            if (item === data.size) {
                option.attr('selected', true);
            }
            option.appendTo(select);
        });
        select.appendTo(div);
        div.appendTo(form);

        return form;
    }

};
