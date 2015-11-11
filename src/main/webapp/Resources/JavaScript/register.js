$.validator.addMethod("regx", function(value, element, regexpr) {          
    return regexpr.test(value);
}, "Your input is not accepted.");

$(document).ready(function () {

    $('#regButton').click(function() {
        $('#registerForm').valid();
    });

    $('input#genderbox').change(function(event) {
        var checkedBox = event.target;
        $('#genderBoxes input[type=checkbox]').each(function() {
            if($(this).val() != checkedBox.value) {
                $(this).prop("disabled", checkedBox.checked);
                $(this).prop("checked", false);
            }
        });
    });

    $('#registerForm').validate({
        rules: {
            username: {
                required: true,
                minlength: 6,
                maxlength: 30,
                remote: "/available"
            },
            password: {
                required: true,
                minlength: 8
            },
            confirmation: {
                required: true,
                equalTo: "#password"
            },
            email: {
                required: true,
                email: true
            },
            realname: {
                regx: /([A-Za-z]{2}[A-Za-z]*[ ]?[A-Za-z]*)*/
            },
            phone: {
                minlength: 10,
                maxlength: 10,
                regx: /^(([0-9][0-9]){5})?$/
            },
            zcode: {
                minlength: 5,
                maxlength: 5,
                regx: /^((75|77|78|91|92|93|94|95)[0-9]{3})?$/
            }
        },
        messages: {
            username: {
                required: "You need have a username to login.",
                remote: "This username is already taken."
            },
            password: {
                required: "You need a password to protect your account.",
                minlength: "Your password is too short."
            },
            confirmation: {
                required: "You need to confirm your password.",
                equalTo: "The password does not match."
            },
            email: {
                required: "We need an email to contact you.",
                email: "The email must have the form aaa@bbb.ccc"
            },
            realname: {
                regx: "Please enter your real name."
            },
            phone: {
                minlenght: "That phone number is too short.",
                maxlength: "That phone number is too long.",
                regx: "Your phone must be composed of only 10 numbers."
            },
            zcode: {
                minlength: "That zip code is too short.",
                maxlength: "That zip code is too long.",
                regx: "You must live in Île-de-France."
            }
        },
        errorPlacement: function(error, element) {
                            var parent = element.parent();
                            parent.append(error);
                        },
        highlight:  function(element, errorClass) {
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