/**
 * Created by Alexander on 15.02.2015.
 */
$(document).ready(main);

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
            success: function (response) {
                console.log(arguments)
            },
            error: function (data) {
                console.log(arguments);
                console.log(data)
            }
        });
    });
}