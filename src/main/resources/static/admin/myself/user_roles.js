$(function() {
    $(".single-role-box").click(function() {
        var objId = $(this).attr("id"); //角色Id
        objId = objId.split("_")[1];
        var userId = $("#curUserId").val(); //用户Id

        $.post("/admin/user/addOrDelUserRole", {userId : userId, roleId : objId}, function(datas) {
            if(datas=='1') {
                alert("设置成功！");
            }
        }, "json");
    });
    var curAuth_array = $("#curRoles").val().split(",");
    for(var i=0; i<curAuth_array.length; i++) {
        $(("#role_"+curAuth_array[i])).attr("checked", true);
    }
});