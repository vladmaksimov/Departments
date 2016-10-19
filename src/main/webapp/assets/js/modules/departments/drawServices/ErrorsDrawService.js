var ErrorsDrawService = function () {
    var constants = new Constants();

    const ERROR_IMAGE = '/assets/images/error.jpg';

    return {
        drawErrorPage: drawErrorPage
    };

    function drawErrorPage(error) {
        const header = $("<div class='form-header center-block'></div>");
        const image = $("<img src='" + ERROR_IMAGE + "' class='center-block'/>");
        $("<div>" + error.responseText + "</div>").appendTo(header);
        $("<a class='btn btn-default' href='/'>To main page</a>").appendTo(header);

        constants.CONTAINER.empty().append(header).append(image);
    }
};
