$(document).ready(function () {

    $.validator.addMethod("regx", function(value, element, regexpr) {
        return regexpr.test(value);
    }, "Your input is not accepted.");

    $('.signin').click(function() {
        $('#loginForm').valid();
    });

    $('#loginForm').validate({
        rules: {
            email: {
                required: true,
                email:true
            },
            password: {
                required: true,
                minlength: 8
            }
        },
        messages: {
            username: {
                required: "You need have a username to login."
            },
            password: {
                required: "You need a password to protect your account.",
                minlength: "Your password is too short."
            }
        },
        errorPlacement: function(error, element) {
            var parent = element.parent();
            parent.append(error);
        },
        highlight: 	function(element, errorClass) {
            element.parentElement.parentElement.className = "form-group has-error has-feedback";
            element.setAttribute("aria-describedby", "inputError2Status");

            var inputStatus = element.nextElementSibling;
            var parent = element.parentElement;
            while(inputStatus != null) {
                parent.removeChild(element.nextElementSibling);
                inputStatus = element.nextElementSibling;
            }

            var status = document.createElement("span");
            status.id = "inputError2Status";
            status.className = "sr-only";
            status.appendChild(document.createTextNode("error"));
            parent.appendChild(status);

            var icon = document.createElement("span");
            icon.className = "glyphicon glyphicon-remove form-control-feedback";
            icon.setAttribute("aria-hidden", "true");
            parent.appendChild(icon);

        },
        unhighlight: function(element, errorClass) {
            element.parentElement.parentElement.className = "form-group has-success has-feedback";
            element.setAttribute("aria-describedby", "inputSuccess2Status");

            var inputStatus = element.nextElementSibling;
            var parent = element.parentElement;
            while(inputStatus != null) {
                parent.removeChild(element.nextElementSibling);
                inputStatus = element.nextElementSibling;
            }

            var status = document.createElement("span");
            status.id = "inputSuccess2Status";
            status.className = "sr-only";
            status.appendChild(document.createTextNode("success"));
            parent.appendChild(status);

            var icon = document.createElement("span");
            icon.className = "glyphicon glyphicon-ok form-control-feedback";
            icon.setAttribute("aria-hidden", "true");
            parent.appendChild(icon);
        }
    });
});
