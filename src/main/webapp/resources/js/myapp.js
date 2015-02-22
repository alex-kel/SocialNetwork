/**
 * Created by Alexander on 13.02.2015.
 */
function main() {
    $(".navigation li").click(function() {
        $(".active").removeClass("active");
        $(this).addClass("active");
    });

    $.ajax("/getNewMessageCount", {
        type: 'GET',
        success: function(data) {
            if (data > 0) {
                $("#3 a").text($("#3 a").text() + " +" + data);
            }
        }
    });
}

$(document).ready(main);