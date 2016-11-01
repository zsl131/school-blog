package com.zslin.web.controller.web;

import com.zslin.basic.utils.*;
import com.zslin.web.dto.CateDto;
import com.zslin.web.model.Account;
import com.zslin.web.model.Article;
import com.zslin.web.service.IAccountService;
import com.zslin.web.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 钟述林 393156105@qq.com on 2016/11/1 10:05.
 */
@Controller
@RequestMapping(value = "web/account")
public class WebAccountController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IArticleService articleService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model, Integer page, HttpServletRequest request) {
        Page<Account> datas = accountService.findAll(new ParamFilterUtil<Account>().buildSearch(model, request),
                PageableUtil.basicPage(page, "id"));
        model.addAttribute("datas", datas);
        return "web/account/index";
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer id, Integer page, HttpServletRequest request) {
        Account account = accountService.findOne(id);
        model.addAttribute("account", account);

        Specifications spes = Specifications.where(new BaseSearch<Article>(new SearchDto("accountId", "eq", account.getId()))).
                and(new BaseSearch<>(new SearchDto("isShow", "eq", 1)));
        Page<Article> datas = articleService.findAll(spes, PageableUtil.basicPage(page, new SortDto("desc", "isGood"), new SortDto("createDate")));
        model.addAttribute("datas", datas);

        List<CateDto> cateDtoList = articleService.queryCates(id);
        model.addAttribute("cateDtoList", cateDtoList);

        return "web/account/detail";
    }
}
