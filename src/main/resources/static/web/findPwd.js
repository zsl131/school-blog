$(function() {
    $(".get-code-btn").click(function() {
        var thisObj = $(this);
        var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        var email = $("#email").val();
        if(!myreg.test(email)) {
            showDialog("请输入正确的邮箱地址！");
        } else {
            $("input[name='email']").val(email); //设置
            $.post("/sendCode", {email:email}, function(res) {
                var timer = 60;
                var myInt = setInterval(function() {
                    if(timer>=1) {
                        timer --;
                        $(thisObj).html(timer+" 秒");
                    } else {
                        clearInterval(myInt);
                    }
                }, "1000");
            });
        }
    });

    $(".submit-btn").click(function() {
        var code = $("input[name='code']").val();
        if($.trim(code)=='') {showDialog("请输入验证码");}
        else {
            $(this).parents("form").submit();
        }
    });
});