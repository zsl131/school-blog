$(function() {
    $("#beginFilter").click(function() {
        var location = window.location.href;
        location = $.getBaseHref(location);
        var params = "?";
        $(".filter_element").each(function(){
            var val = $(this).val();
            var n = $(this).attr("name");
            var opt = $(this).attr("opt");
            if(val!=""&&val!=-1) {
                params+=n+"="+opt+"-"+val+"&";
            }
        });
        window.location.href=location+params;
    });

    $(".filter_element").each(function() {
        var thisObj = $(this);
        var id = $(thisObj).attr("name");
        var val = $("input[targetId="+id+"]").val();
        if (typeof(val) != "undefined") {
            $(thisObj).val(val);
        }
    });
});