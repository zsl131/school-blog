package com.zslin.web.controller.admin;

import com.zslin.basic.annotations.AdminAuth;
import com.zslin.basic.utils.PageableUtil;
import com.zslin.basic.utils.ParamFilterUtil;
import com.zslin.web.model.Account;
import com.zslin.web.model.Article;
import com.zslin.web.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 钟述林 393156105@qq.com on 2016/11/1 9:37.
 */
@Controller
@RequestMapping(value = "admin/account")
@AdminAuth(name="用户管理", orderNum=2, psn="系统应用", pentity=0, porderNum=20)
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @AdminAuth(name="用户管理", orderNum=1, icon="fa fa-list-ul", type="1")
    @RequestMapping(value="list", method= RequestMethod.GET)
    public String list(Model model, Integer page, HttpServletRequest request) {
        Page<Account> datas = accountService.findAll(new ParamFilterUtil<Account>().buildSearch(model, request),
                PageableUtil.basicPage(page));
        model.addAttribute("datas", datas);
        return "admin/account/list";
    }
}
