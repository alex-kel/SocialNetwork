/**
 * Created by Alexander on 21.02.2015.
 */
var follower = "<div class=\"follower\" style=\"margin-bottom: 50px\">" +
    "<img  style=\"height: 100px; float: left\">" +
    "<h3 style=\"padding-top: 10px; padding-left: 110px\" class=\"name\"></h3>" +
    "<a href=\"#\" style=\"padding-top: 10px; padding-left: 50px\" class='send-msg'>Send message</a>" +
    "</div><hr style=\"margin-top: 50px\">";

var id;

$(document).ready(function () {

    $(".navigation").find("#2").addClass("active");

    $.ajax("/getFollowers", {
        type: "GET",
        contentType: "application/json",
        success: function (data) {
            data = JSON.parse(data);
            for (var i = 0; i < data.length; i++) {
                var newFollower = $('<div/>').html(follower).contents();
                newFollower.addClass(data[i].id.toString());
                newFollower.find(".name").html("<a class='profileRef'>" + data[i].details.fullName.toString() + "</a>");
                newFollower.find(".profileRef").attr("href", "/profile/" + data[i].id);
                newFollower.find("img").attr("src", data[i].details.avatarRef.toString());
                newFollower.find(".profileRef").addClass("no-underline");
                newFollower.find(".send-msg").addClass("no-underline")
                    .click(function () {
                        id = $(this).closest(".follower").attr("class").split(' ').pop();
                        $(".send-message").modal('show');
                    });
                $("#follower").append(newFollower);
            }
        }
    });

    $.ajax("/getFollowings", {
        type: "GET",
        contentType: "application/json",
        success: function (data) {
            data = JSON.parse(data);
            for (var i = 0; i < data.length; i++) {
                var newFollower = $('<div/>').html(follower).contents();
                newFollower.addClass(data[i].id.toString());
                newFollower.find(".name").html("<a class='profileRef'>" + data[i].details.fullName.toString() + "</a>");
                newFollower.find(".profileRef").attr("href", "/profile/" + data[i].id);
                newFollower.find("img").attr("src", data[i].details.avatarRef.toString());
                newFollower.find(".profileRef").addClass("no-underline");
                newFollower.find(".send-msg").addClass("no-underline").click(function () {
                    id = $(this).closest(".follower").attr("class").split(' ').pop();
                    $(".send-message").modal('show');
                });
                $("#following").append(newFollower);
            }
        }
    });

    $.ajax("/getAllUsers", {
        type: "GET",
        contentType: "application/json",
        success: function (data) {
            data = JSON.parse(data);
            for (var i = 0; i < data.length; i++) {
                var newFollower = $('<div/>').html(follower).contents();
                newFollower.addClass(data[i].id.toString());
                newFollower.find(".name").html("<a class='profileRef'>" + data[i].details.fullName.toString() + "</a>");
                newFollower.find(".profileRef").attr("href", "/profile/" + data[i].id);
                newFollower.find("img").attr("src", data[i].details.avatarRef.toString());
                newFollower.find(".profileRef").addClass("no-underline");
                newFollower.find(".send-msg").addClass("no-underline").click(function () {
                    id = $(this).closest(".follower").attr("class").split(' ').pop();
                    $(".send-message").modal('show');
                });
                $("#all").append(newFollower);
            }
        }
    });

    $.ajax("/getFriends", {
        type: "GET",
        contentType: "application/json",
        success: function (data) {
            data = JSON.parse(data);
            for (var i = 0; i < data.length; i++) {
                var newFollower = $('<div/>').html(follower).contents();
                newFollower.addClass(data[i].id.toString());
                newFollower.find(".name").html("<a class='profileRef'>" + data[i].details.fullName.toString() + "</a>");
                newFollower.find(".profileRef").attr("href", "/profile/" + data[i].id);
                newFollower.find("img").attr("src", data[i].details.avatarRef.toString());
                newFollower.find(".profileRef").addClass("no-underline");
                newFollower.find(".send-msg").addClass("no-underline").click(function () {
                    id = $(this).closest(".follower").attr("class").split(' ').pop();
                    $(".send-message").modal('show');
                });
                $("#friends").append(newFollower);
            }
        }
    });

    $(".send").click(function () {
        $.post("/sendMessage", {"id": id, "text": $("#text-message").val()}, function () {
            $(".send-message").modal('hide');
        });
    });
});
