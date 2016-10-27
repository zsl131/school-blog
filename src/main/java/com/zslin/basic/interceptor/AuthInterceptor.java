package com.zslin.basic.interceptor;

import com.zslin.basic.dto.AuthToken;
import com.zslin.basic.exception.SystemException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        AuthToken at = (AuthToken)session.getAttribute(AuthToken.SESSION_NAME);
        HandlerMethod hm = (HandlerMethod)handler;
        if(at==null||at.getUser()==null) {
            response.sendRedirect(request.getContextPath()+"/login");
            return false;
        } else {
            boolean isAdmin = false;
            try {
                isAdmin = at.getUser().getIsAdmin()==1; //是否为超级管理员
            } catch (Exception e) {
            }
//			System.out.println(isAdmin);
            if(!isAdmin) {
                //不是超级管理人员，就需要判断是否有权限访问某些功能
                String className = hm.getBean().getClass().getName();
                String methodName = hm.getMethod().getName();
                String curUrl = className.substring(className.lastIndexOf(".")+1, className.length())+"."+methodName;
                List<String> authList = at.getAuthList();
                if(!authList.contains(curUrl)) {throw new SystemException("无权限访问，请与管理员联系！");}
            }
        }
        return super.preHandle(request, response, handler);
    }
}