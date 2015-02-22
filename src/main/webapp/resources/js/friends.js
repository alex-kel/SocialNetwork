/**
 * Created by Alexander on 21.02.2015.
 */
var follower = "<div class=\"follower\" style=\"margin-bottom: 50px\">" +
    "<img  style=\"height: 100px; float: left\">" +
    "<h3 style=\"padding-top: 10px; padding-left: 110px\" class=\"name\"></h3>" +
    "<a href=\"#\" style=\"padding-top: 10px; padding-left: 34px\">Send message</a>" +
    "</div><hr style=\"margin-top: 50px\">";

$(document).ready(function () {

    $.ajax("/getFollowers", {
        type : "GET",
        contentType: "application/json",
        success: function(data) {
            data = JSON.parse(data);
            for (var i = 0; i < data.length; i++) {
                var newFollower = $('<div/>').html(follower).contents();
                newFollower.addClass(data[i].id.toString());
                newFollower.find(".name").text(data[i].details.fullName.toString());
                newFollower.find("img").attr("src", data[i].details.avatarRef.toString());
                $(".followers").append(newFollower);
            }
        }
    });

    $.ajax("/getFollowings", {
        type : "GET",
        contentType: "application/json",
        success: function(data) {
            data = JSON.parse(data);
            for (var i = 0; i < data.length; i++) {
                var newFollower = $('<div/>').html(follower).contents();
                newFollower.addClass(data[i].id.toString());
                newFollower.find(".name").text(data[i].details.fullName.toString());
                newFollower.find("img").attr("src", data[i].details.avatarRef.toString());
                $(".followings").append(newFollower);
            }
        }
    });


});
