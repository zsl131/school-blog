package com.zslin.web.interceptor;

import com.zslin.web.model.Account;
import com.zslin.web.model.BaseConfig;
import com.zslin.web.service.IBaseConfigService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Configuration
public class WebAdminInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("login_account");
        if(a==null) {
            response.sendRedirect("/web/login");
            return false;
        }

        return super.preHandle(request, response, handler);
    }
}