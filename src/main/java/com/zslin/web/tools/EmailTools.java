package com.zslin.web.tools;

import com.zslin.basic.tools.NormalTools;
import com.zslin.web.model.BaseConfig;
import com.zslin.web.service.IBaseConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 发送邮件的工具类
 * Created by 钟述林 393156105@qq.com on 2016/10/30 11:23.
 */
@Component
public class EmailTools {

    @Autowired
    private IBaseConfigService baseConfigService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String username;

    //获取配置，主要是获取驾校名称作为落款
    private BaseConfig getConfig() {
        return baseConfigService.loadOne();
    }

    /**
     * 修改博文属性通知
     * @param field 被修改的属性
     * @param val 修改后的值
     * @param email 接收邮箱地址（发文人的）
     * @param title 博文标题
     * @param url 博文链接地址
     */
    public void sendOnUpdateArticle(String field, Integer val, String email, String title, String url) {
        String fieldName = "isGood".equalsIgnoreCase(field)?"是否推荐":"是否显示";
        String valName = val==1?"是":"否";
        StringBuffer sb = new StringBuffer();
        sb.append("<h1>您的博文属性被管理员修改！</h1>")
                .append("<p>修改博文：<a href='").append(url).append("'>").append(title).append("</a></p>")
                .append("<p>设置属性：<b style='color:#00F;'>").append(fieldName)
                .append("</b>&nbsp;为&nbsp;<b style='color:#F00'>").append(valName).append("</b></p>");

        new Thread(new Runnable() {
            @Override
            public void run() {
                sendNormalEmail("博文属性修改通知", true, sb.toString(), true, email);
            }
        }).start();
    }

    /**
     * 博文变更通过
     * @param email 接收方邮箱地址（管理员的）
     * @param title 博文标题
     * @param content 博文内容
     * @param url 链接地址
     */
    public void sendOnAddArticle(String email, String title, String content, String url) {
        StringBuffer sb = new StringBuffer();
        sb.append("<a href='").append(url).append("' target='_blank'><h1>博文标题：").append(title).append("</h1></a>");
        sb.append("<h2>博文内容如下：</h2>").append(content);
        new Thread(new Runnable() {
            @Override
            public void run() {
                sendNormalEmail("新博文通知", true, sb.toString(), true, email);
            }
        }).start();
    }

    /**
     * 发送注册时的验证码
     * @param email 接收的邮箱地址
     * @param code 验证码
     */
    public void sendRegisterCode(String email, String code) {
        StringBuffer sb = new StringBuffer();
        sb.append("<h1>欢迎使用！</h1>")
                .append("<p style='color:#F00'>此次注册的验证码是："+code+"</p>");
//        sendNormalEmail("注册验证码", true, sb.toString(), true, email);
        new Thread(new Runnable() {
            @Override
            public void run() {
                sendNormalEmail("验证码", true, sb.toString(), true, email);
            }
        }).start();
    }

    /**
     * 注册成功时的提示邮件
     * @param email 接收的邮箱地址
     * @param pwd 初始密码
     * @param url 登陆地址
     */
    public void sendRegisterSuc(String email, String pwd, String url) {
        StringBuffer sb = new StringBuffer();
        sb.append("<h3>恭喜您，注册成功！</h3>")
                .append("<h2>初始化密码是：<b style='color:#F00'>").append(pwd).append("</b>，请不要告诉任何人！</h2>")
                .append("请及时<a href='").append(url).append("'>登陆网站</a>修改密码。");

//        sendNormalEmail("注册成功", true, sb.toString(), true, email);
        new Thread(new Runnable() {
            @Override
            public void run() {
                sendNormalEmail("注册成功", true, sb.toString(), true, email);
            }
        }).start();
    }

    /**
     * 注册成功时的提示邮件
     * @param email 接收的邮箱地址
     * @param pwd 初始密码
     * @param url 登陆地址
     */
    public void sendFindPwdSuc(String email, String pwd, String url) {
        StringBuffer sb = new StringBuffer();
        sb.append("<h3>恭喜您，密码找回成功！</h3>")
                .append("<h2>系统随机密码是：<b style='color:#F00'>").append(pwd).append("</b>，请不要告诉任何人！</h2>")
                .append("请及时<a href='").append(url).append("'>登陆网站</a>修改密码。");

        new Thread(new Runnable() {
            @Override
            public void run() {
                sendNormalEmail("成功找回密码", true, sb.toString(), true, email);
            }
        }).start();
    }

    /**
     * 新用户注册通过
     * @param email 接收邮箱地址（管理员的）
     * @param nickname 注册人姓名
     * @param regEmail 注册人邮箱
     * @param url 地址
     */
    public void sendOnRegister(String email, String nickname, String regEmail, String url) {
        StringBuffer sb = new StringBuffer();
        sb.append("<a href='").append(url).append("'><h1>姓名：").append(nickname).append("</h1></a>");
        sb.append("<h3>注册邮箱：").append(regEmail).append("</h3>");

        new Thread(new Runnable() {
            @Override
            public void run() {
                sendNormalEmail("新用户注册通知", true, sb.toString(), true, email);
            }
        }).start();
    }

    /**
     * 通过邮件
     * @param title 标题
     * @param titleWithName 是否在标题后加上落款
     * @param content 邮件内容
     * @param contentWithName 邮件内容后是否加上落款
     * @param email 接收的邮箱地址
     */
    private void sendNormalEmail(String title, boolean titleWithName, String content, boolean contentWithName, String email) {
        BaseConfig config = getConfig();
        String dName = "校园博客";
        dName = config==null?dName:config.getName()==null||"".equals(config.getName())?dName:config.getName();
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(new InternetAddress(username, dName, "UTF-8"));
            helper.setTo(email);
            title = titleWithName?title + " - "+dName:title;
            helper.setSubject(title);

            if(contentWithName) {
                content += "<p style='text-align:right'>" +dName+ "</p>";
                content += "<p style='text-align:right'>" + NormalTools.curDate("yyyy-MM-dd HH:mm:ss")+ "</p>";
            }

            helper.setText(content, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        javaMailSender.send(message);
    }
}
