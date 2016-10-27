package com.zslin.basic.interceptor;

import com.zslin.basic.service.IAppConfigService;
import com.zslin.basic.model.AppConfig;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Configuration
public class SystemInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private IAppConfigService appConfigService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        //将系统配置文件存入Session中
        AppConfig appConfig = (AppConfig) session.getAttribute("appConfig");
        if(appConfigService==null) {
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            appConfigService = (IAppConfigService) factory.getBean("appConfigService");
        }
        if(appConfig==null) {
            appConfig = appConfigService.loadOne();
            session.setAttribute("appConfig", appConfig);
        }

        return super.preHandle(request, response, handler);
    }
}