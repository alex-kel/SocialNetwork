/**
 * Created by Alexander on 22.02.2015.
 */
var msg = "<div class=\"msg\">" +
    "<img class=\"ava\"> " +
    "<span  class=\"author\"></span>" + "<div class=\"right\">" +
    "<a class='reply'>Reply</a> <br><br> <a class='profile'>Profile</a> </div> " +
    "<div class=\"text\"> </div></div>" +
    "<hr style=\"margin-top: 50px\">";

var outmsg = "<div class=\"msg\">" +
    "<img class=\"ava\"> " +
    "<span  class=\"author\"></span>" + "<div class=\"right\">" +
    "<div class=\"text\"> </div></div>" +
    "<hr style=\"margin-top: 50px\">";

var id;

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
                if (!data[i].isRead) {
                    newMsg.find(".text").css({'background-color' : 'aliceblue'});
                }
                newMsg.find(".reply").click(function() {
                    id = $(this).closest(".msg").attr("id");
                    $(".send-message").modal('show');
                    $.post("/markAsRead", {"id" : messageId});
                });
                newMsg.click(function() {
                    id = $(this).attr("id");
                    $(".msg-text").text($(this).find(".text").text());
                    $(".send-message").modal('show');
                    var messageId = $(this).attr("class").split(' ').pop();
                    $.post("/markAsRead", {"id" : messageId});
                    $(this).find(".text").css({'background-color' : ''});
                });
                $("#inbox").prepend(newMsg);
            }
        }
    });

    $.ajax("/getOutcomingMessages", {
        type: "GET",
        contentType: "application/json; charset=utf-8",
        success: function(data) {
            data = JSON.parse(data);
            for(var i = 0; i < data.length; i++) {
                var newMsg = $("<div/>").html(outmsg).contents();
                newMsg.addClass(data[i].id.toString());
                newMsg.attr("id", data[i].author.id);
                newMsg.find(".ava").attr("src", data[i].author.details.avatarRef.toString());
                newMsg.find(".author").text(data[i].author.details.fullName.toString());
                newMsg.find(".text").text(data[i].text);
                newMsg.find(".profile").attr("href", "/profile/" + data[i].author.id);
                $("#outbox").prepend(newMsg);
            }
        }
    });
});


function send() {
    $.post("/sendMessage", {"id" : id, "text" : $("#text-message").val()}, function() {
        $(".send-message").modal('hide');
        //$(".wasSend").stop().fadeIn(400).delay(3000).fadeOut(400);
    });
}