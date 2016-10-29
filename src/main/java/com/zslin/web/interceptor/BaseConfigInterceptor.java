package com.zslin.web.interceptor;

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
public class BaseConfigInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private IBaseConfigService baseConfigService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        //将系统配置文件存入Session中
        BaseConfig baseConfig = (BaseConfig) session.getAttribute("baseConfig");
        if(baseConfigService==null) {
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            baseConfigService = (IBaseConfigService) factory.getBean("baseConfigService");
        }
        if(baseConfig==null) {
            baseConfig = baseConfigService.loadOne();
            session.setAttribute("baseConfig", baseConfig);
        }

        return super.preHandle(request, response, handler);
    }
}