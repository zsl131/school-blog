$(function() {
    var oldCateId = $("input[name='cateId']").val();
    if(oldCateId>0) {
        $(".single-cate").each(function() {
            var cateId = $(this).attr("cateId");
            if(cateId==oldCateId) {$(this).addClass("btn-info");}
        });
    }

    $(".single-cate").click(function() {
        var cateId = $(this).attr("cateId");
        var cateName = $(this).attr("cateName");

        $(this).parents(".form-control").find("a.btn-info").removeClass("btn-info");
        $(this).addClass("btn-info");

        $("input[name='cateId']").val(cateId);
        $("input[name='cateName']").val(cateName);
    });

    var editor = new wangEditor('content');
    // 上传图片（举例）
    editor.config.uploadImgUrl = '/upload';
    editor.config.uploadImgFileName = 'myFileName';
    // 设置 headers（举例）
    editor.config.uploadHeaders = {
        'Accept' : 'text/x-json',
        'uploadImgFileName' : 'file'
    };
    // 配置自定义参数（举例）
    editor.config.uploadParams = {
        toke: 'abcdefg',
        user: 'wangfupeng1988',
        'uploadImgFileName' : 'file'
    };

    editor.create();

    $(".submit-btn").click(function() {
        var cateId = $("input[name='cateId']").val(); //分类Id
        var title = $("input[name='title']").val(); //标题
        var content = editor.$txt.html();

        if($.trim(title)=='') {showDialog("请输入博文标题");}
        else if(cateId==null|| isNaN(parseInt(cateId)) || parseInt(cateId)<=0) {showDialog("请选择所在分类");}
        else if($.trim(editor.$txt.text())=='') {showDialog("请输入博文内容");}
        else {
            $(this).parents("form").submit();
        }
    });
});