$(function() {
    $(".get-code-btn").click(function() {
        var thisObj = $(this);
        var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        var email = $("#email-id").val();
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
        var name = $("input[name='name']").val();
        var email = $("#email-id").val();
        var code = $("input[name='code']").val();

        if($.trim(name)=='') {showDialog("请输入姓名");}
        else if($.trim(code)=='') {showDialog("请输入验证码");}
        else {
            /*$.post("/register", {name:name, email:email, code:code}, function(res) {
                if(res=='1') {alert("注册成功！"); windown.location.href = "/web/login";}
            }, "json");*/
            $(this).parents("form").submit();
        }
    });
});