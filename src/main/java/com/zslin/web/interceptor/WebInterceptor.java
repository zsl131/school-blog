package com.zslin.web.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by 钟述林 393156105@qq.com on 2016/10/29 22:32.
 */
@Configuration
public class WebInterceptor extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new BaseConfigInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new WebAdminInterceptor()).addPathPatterns("/webm/**");
        super.addInterceptors(registry);
    }
}
