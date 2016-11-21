var DrawUtils = function () {

    return {
        drawInput: drawInput
    };

    function drawInput(data, errors, label, name, placeholder, type) {
        var div = $("<div class='form-group'></div>");
        $("<label for='name'>" + label + "</label>").appendTo(div);

        var input = $("<input type='" + type + "' class='form-control' name='" + name + "' placeholder='" + placeholder + "'>");
        input.val(data[name]);
        input.appendTo(div);

        if (errors && errors[name]) {
            var errorList = errors[name];
            var errorDiv = $("<div class='error-validation'></div>");
            _.each(errorList, function (val) {
                $('<div>' + val + '</div>').appendTo(errorDiv);
            });
            errorDiv.appendTo(div);
        }

        return div;
    }
    
};