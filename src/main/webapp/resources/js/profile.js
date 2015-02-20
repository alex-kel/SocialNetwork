/**
 * Created by Alexander on 15.02.2015.
 */
var isHiden = true;

$(document).ready(function () {

    var currentPhoto;


    $(".photos").on("click", "img", function() {
        $(".bigPhoto").attr("src", $(this).attr("src"));
        if (isHiden) {
            $(".photoModal").modal('show');
            isHiden = false;
        }
        currentPhoto = $(this);



    });

    $(".next").click(function () {
        var next;
        var allNext = currentPhoto.nextAll("img");
        if (allNext.length > 0) {
            next = $(allNext[0]);
            next.click();
        } else {
            var sib = currentPhoto.siblings("img");
            if (sib.length > 0) {
                next = $(sib[0]);
                next.click();
            }
        }
    });

    $(".prev").click(function () {
        var prev;
        var allPrev = currentPhoto.prevAll("img");
        if (allPrev.length > 0) {
            prev = $(allPrev[0]);
            prev.click();
        } else {
            var sib = currentPhoto.siblings("img");
            if (sib.length > 0) {
                prev = $(sib[sib.length - 1]);
                prev.click();
            }
        }
    });

    $(".edit-profile").click(function (event) {
        event.preventDefault();
        $(".profileModal").modal('show');
    });

    $(".change-avatar").click(function (event) {
        event.preventDefault();
        $(".avatarModal").modal('show');
    });

    $(".wall")
        .on("click", ".like-btn", function () {
            var btn = $(this);
            var post = btn.closest(".post");
            var postId = post.attr("class").split(' ').pop();
            $.post("/post/changeLikeState", {"id": postId}, function (data) {
                btn.toggleClass("btn-primary");
                if (data === "liked") {
                    btn.text("Like " + (parseInt(btn.text().split(' ').pop()) + 1));
                } else {
                    btn.text("Like " + (btn.text().split(' ').pop() - 1));
                }
            })
        })
        .on("click", ".del", function() {
            var btn = $(this);
            var post = btn.closest(".post");
            var postId = post.attr("class").split(' ').pop();
            $.post("/post/deletePost", {"id" : postId}, function(data) {
                if ("deleted" != data) {
                    alert(data);
                } else {
                    post.fadeOut(500, function() {
                        post.remove();
                    });
                }
            })
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

function uploadAvatar() {
    $("#avatar-form").submit();
}

function uploadPhoto() {
    $("#photo-form").submit();
}

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        $(".preview").css("display", "block");
        reader.onload = function (e) {
            $('#preview')
                .attr('src', e.target.result)
        };

        reader.readAsDataURL(input.files[0]);
    }
}

function uploadModal(event) {
    $(".uploadPhoto").modal('show');
}

function clearFlag() {
    isHiden = true;
}
