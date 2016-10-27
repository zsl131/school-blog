$(function() {
    setSize();
    $(window).resize(function() {setSize();});

    $(".show-copy").each(function() {
        var myDate = new Date();
        var year = myDate.getFullYear();
        $(this).html('&copy; '+year);
    });
});

function setSize() {
    var winHeight = $(window).height();

    var winWidth = $(window).width();

    $(".main-left").css({"height":(winHeight-80)+"px"});
    $("#navigation-div").css({"height":(winHeight-80-50)+"px"});
    $(".main-right").css({"height":(winHeight-80)+"px", "width":(winWidth-242)+"px"});
}