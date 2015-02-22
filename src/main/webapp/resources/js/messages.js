/**
 * Created by Alexander on 22.02.2015.
 */
var msg = "<div class=\"msg\">" +
    "<img class=\"ava\"> " +
    "<span  class=\"author\"></span>" + "<div class=\"right\">" +
    "<a>Reply</a> <br><br> <a class='profile'>Profile</a> </div> " +
    "<div class=\"text\"> </div></div>" +
    "<hr style=\"margin-top: 50px\">";

$(document).ready(function() {
    $.ajax("/getIncomingMessages", {
        type: "GET",
        success: function(data) {
            data = JSON.parse(data);
            for(var i = 0; i < data.length; i++) {
                var newMsg = $("<div/>").html(msg).contents();
                newMsg.addClass(data[i].id.toString());
                newMsg.attr("id", data[i].author.id);
                newMsg.find(".ava").attr("src", data[i].author.details.avatarRef.toString());
                newMsg.find(".author").text(data[i].author.details.fullName.toString());
                newMsg.find(".text").text(data[i].text);
                newMsg.find(".profile").attr("href", "/profile/" + data[i].author.id);
                $("#inbox").prepend(newMsg);
            }
        }
    });
});
