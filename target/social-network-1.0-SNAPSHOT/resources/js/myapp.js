/**
 * Created by Alexander on 13.02.2015.
 */
function main() {
    $(".navigation li").click(function() {
        $(".active").removeClass("active");
        $(this).addClass("active");
    });
}

$(document).ready(main);