/**
 * Created by Alexander on 15.02.2015.
 */
$(document).ready(function () {

    $(".edit-profile").click(function (event) {
        event.preventDefault();
        $(".profileModal").modal('show');
    });

    $(".change-avatar").click(function(event) {
        event.preventDefault();
        $(".avatarModal").modal('show');
    });

    $(".save").click(function () {
        var fullName = $("#fullName").val();
        var city = $("#city").val();
        var phoneNumber = $("#phoneNumber").val();
        var email = $("#email").val();
        var about = $("#about").val();
        var birthDate = $("#birthDate").val();
        $.ajax("/edit", {
            type: 'POST',
            contentType: "application/json",
            data: JSON.stringify({
                "fullName": fullName,
                "city": city,
                "phoneNumber": phoneNumber,
                "email": email,
                "about": about,
                "birthDate": birthDate
            }),
            complete: function () {
                location.reload(true);
                location.reload(true);
            }
        });
    });
});

function setActive() {
    $(".navigation").find("#1").addClass("active");
}