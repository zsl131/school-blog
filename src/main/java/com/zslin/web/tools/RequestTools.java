package com.zslin.web.tools;

import com.zslin.basic.model.AppConfig;
import com.zslin.web.model.Account;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 钟述林 393156105@qq.com on 2016/10/30 16:15.
 */
public class RequestTools {

    /** 获取登陆的用户 */
    public static Account getAccount(HttpServletRequest request) {
        return (Account) request.getSession().getAttribute("login_account");
    }

    /** 获取管理配置 */
    public static AppConfig getAppConfig(HttpServletRequest request) {
        return (AppConfig) request.getSession().getAttribute("appConfig");
    }

    /** 获取管理员邮箱地址 */
    public static String getAdminEmail(HttpServletRequest request) {
        AppConfig config = getAppConfig(request);
        if(config!=null) {
            return config.getAdminEmail()==null?"393156105@qq.com":config.getAdminEmail();
        } else {
            return "393156105@qq.com";
        }
    }

    /** 获取url地址 */
    public static String getCurUrl(HttpServletRequest request) {
        String url = request.getScheme()+"://"+
                request.getServerName()+":"+request.getServerPort();
        return url;
    }
}
