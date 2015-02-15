/**
 * Created by Alexander on 15.02.2015.
 */
$(document).ready(main);


function successFunction(response, textStatus, xhr) {

    if (xhr.status == '203') {
        var errors = JSON.parse(response)
        $(".error").remove();
        $(".has-error").removeClass("has-error");
        errors = errors.errors;
        if (errors.password != "") {
            $('input[name=password]').parent()
                .addClass('has-error')
                .prepend("<span class=\"error\">" + errors.password + "</span>");
        }
        if (errors.confirmPassword != "") {
            $('input[name=confirmPassword]').parent()
                .addClass('has-error')
                .prepend("<span class=\"error\">" + ((errors.confirmPassword != undefined) ? errors.confirmPassword : errors.password) + "</span>");

        }
        if (errors.login != "") {
            $('input[name=login]').parent()
                .addClass('has-error')
                .prepend("<span class=\"error\">" + errors.login + "</span>");
        }
        if (errors.fullName != "") {
            $('input[name=fullName]').parent()
                .addClass('has-error')
                .prepend("<span class=\"error\">" + errors.fullName + "</span>");
        }
    } else {
        window.location.replace("/login");
    }

}

function main() {
    $('form').on('submit', function (event) {
        event.preventDefault();
        var login = $('input[name = login]').val();
        var password = $('input[name = password]').val();
        var confirmPassword = $('input[name = confirmPassword]').val();
        var fullName = $('input[name = fullName]').val();
        $.ajax("/register", {
            type: 'POST',
            contentType: "application/json",
            data: JSON.stringify({
                "login": login,
                "password": password,
                "confirmPassword": confirmPassword,
                "fullName": fullName
            }),
            success: successFunction
        });
    });
}

