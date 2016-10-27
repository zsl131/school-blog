$(function() {
    var date = new Date();
    var year = date.getFullYear();
    $(".show-year").html(year+"-"+(year+1));

    //设置高度
    var leftHeight = $(".login-box div.col-md-6:first-child").height();
    $(".login-box div.col-md-6:last-child").height(leftHeight);
});