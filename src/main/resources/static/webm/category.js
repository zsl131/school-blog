$(function() {
    $(".add-category-btn").click(function() {
        var html = '<input class="form-control" name="name" placeholder="输入分类名称"/>';
        var addCateDialog = confirmDialog(html, "添加分类", function() {
            var name = $(addCateDialog).find("input[name='name']").val();
            if($.trim(name)=='') {
                showDialog("请输入分类名称");
            } else {
                $.post("/webm/category/add", {name:name}, function(res) {
                    if(res=='1') {showDialog("添加分类成功！"); $(addCateDialog).modal("hide");}
                }, "json");
            }
        });
    });

    $(".update-cate-btn").click(function() {
        var objId = $(this).attr("objId");
        var objName = $(this).attr("objName");
        var html = '<input class="form-control" name="name" value="'+objName+'" placeholder="输入分类名称"/>';
        var updateCateDialog = confirmDialog(html, "添加分类", function() {
            var name = $(updateCateDialog).find("input[name='name']").val();
            if($.trim(name)=='') {
                showDialog("请输入分类名称");
            } else {
                $.post("/webm/category/update", {id:objId, name:name}, function(res) {
                    if(res=='1') {showDialog("修改分类成功！"); $(updateCateDialog).modal("hide");}
                }, "json");
            }
        });
    });
});