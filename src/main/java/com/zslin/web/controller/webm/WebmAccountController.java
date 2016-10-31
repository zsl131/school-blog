package com.zslin.web.controller.webm;

import com.zslin.basic.tools.MyBeanUtils;
import com.zslin.basic.tools.SecurityUtil;
import com.zslin.web.model.Account;
import com.zslin.web.service.IAccountService;
import com.zslin.web.tools.RequestTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 钟述林 393156105@qq.com on 2016/10/30 16:11.
 */
@Controller
@RequestMapping(value = "webm/account")
public class WebmAccountController {

    @Autowired
    private IAccountService accountService;

    /** 用户首页 */
    @RequestMapping(value = "index")
    public String index(Model model, Account account, HttpServletRequest request) {
        String method = request.getMethod();
        if("get".equalsIgnoreCase(method)) {
            model.addAttribute("account", RequestTools.getAccount(request));
        } else {
            Account a = RequestTools.getAccount(request);
            MyBeanUtils.copyProperties(account, a, new String[]{"id", "email"});

            String oldPwd = request.getParameter("oldPwd");
            String newPwd = request.getParameter("newPwd");

            if(oldPwd!=null && newPwd!=null && newPwd.length()>=6) { //修改密码
                try {
                    String pwd = SecurityUtil.md5(a.getEmail(), oldPwd);
                    if(pwd.equals(a.getPassword())) { //如果原始密码匹配
                        a.setPassword(SecurityUtil.md5(a.getEmail(), newPwd));
                    }
                } catch (NoSuchAlgorithmException e) {
                }
            }

            accountService.save(a);
            request.getSession().setAttribute("login_account", a);

            return "redirect:/webm/account/index?ok=true";
        }
        return "webm/account/index";
    }
}
