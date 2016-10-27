package com.zslin.basic.controller;

import com.zslin.basic.dto.AuthToken;
import com.zslin.basic.service.IMenuService;
import com.zslin.basic.service.IUserService;
import com.zslin.basic.model.User;
import com.zslin.basic.service.MenuServiceImpl;
import com.zslin.basic.tools.SecurityUtil;
import com.zslin.basic.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

/**
 * Created by zsl-pc on 2016/9/7.
 */
@Controller
public class LoginController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IMenuService menuService;

    @Autowired
    private MenuServiceImpl menuServiceImpl;

    /** 登陆 */
    @RequestMapping(value="login")
    public String login(Model model, HttpServletRequest request) {
        String method = request.getMethod(); //获取请求方式，GET或POST
        try {
            if("get".equalsIgnoreCase(method)) {
                return "admin/basic/login";
            } else if("post".equalsIgnoreCase(method)) {
                String errMsg = null;
                String username = request.getParameter("username"); //用户名
                String password = request.getParameter("password"); //密码
                User u = userService.findByUsername(username);
                if(u==null || u.getStatus()==null || u.getStatus()!=1) {errMsg = username+"不存在或已停用";}
                else if(!u.getPassword().equals(SecurityUtil.md5(username, password))) {errMsg = "密码输入不正确";}
                else {
                    AuthToken at = new AuthToken();
                    at.setLogin_ip(request.getRemoteAddr());
                    at.setLogin_time(new Date());
                    at.setUser(u);
                    at.setAuthMenu(menuServiceImpl.queryMenuDtoNew(u.getId()));
                    List<String> authList = menuService.listAuthByUser(u.getId());
                    authList.add("AdminController.index");
                    authList.add("AdminController.updatePwd");
                    at.setAuthList(authList);
                    request.getSession().setAttribute(AuthToken.SESSION_NAME, at);
//					request.getSession().setAttribute("login_user", u);
                }
                if(errMsg!=null && !"".equals(errMsg)) {
                    model.addAttribute("errMsg", errMsg);
                    return "admin/basic/login";
                } else {
                    return "redirect:/admin";
                }
            }
        } catch (NoSuchAlgorithmException e) {
            throw new SystemException("登陆异常！");
        }
        return "admin/login";
    }

    /** 设置当前的菜单Id */
    @RequestMapping(value="setCurrentMenuId", method= RequestMethod.POST)
    public @ResponseBody
    String setCurrentMenuId(Integer pmId, Integer mid, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("pmId", pmId);
        session.setAttribute("mid", mid);
        return "1";
    }

    /** 退出 */
    @RequestMapping(value="logout", method=RequestMethod.POST)
    public @ResponseBody String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(AuthToken.SESSION_NAME);
        return "1";
    }
}
