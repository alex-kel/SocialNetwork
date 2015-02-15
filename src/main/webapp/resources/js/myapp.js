/**
 * Created by Alexander on 13.02.2015.
 */
function main() {

    $(".navigation li").click(function() {
        $(".active").removeClass("active");
        $(this).addClass("active");
    });

    $(".edit-profile").click(function(event) {
        event.preventDefault();
        $(".modal").modal('show');
    });
    //update user-info
    $(".save").click(function() {
        var email = $("#email").val();
        var city = $("#city").val();
        var birthDate = $("#birthDate").val();
        var phoneNumber = $("#phoneNumber").val();
        var about = $("#about").val();
        var fullName = $("#fullName").val();
        $.post("/secure/profile/edit", {email:email, city:city, birthDate:birthDate, phoneNumber:phoneNumber,
                                        about:about, fullName:fullName});
        window.location.reload();
    });
    //new post
    $(".post").click(function() {
        var post = $(".wall-post").val();
        var url = window.location.href + "/post";
        $.post(url, {post:post});

    });
}

$(document).ready(main);