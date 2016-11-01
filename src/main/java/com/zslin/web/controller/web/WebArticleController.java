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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 钟述林 393156105@qq.com on 2016/11/1 10:02.
 */
@Controller
@RequestMapping(value = "web/article")
public class WebArticleController {

    @Autowired
    private IArticleService articleService;

    @Autowired
    private IAccountService accountService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model, Integer page, HttpServletRequest request) {
        Page<Article> datas = articleService.findAll(new ParamFilterUtil<Article>().buildSearch(model, request),
                PageableUtil.basicPage(page, "createDate"));
        model.addAttribute("datas", datas);

        Specifications newSpes = Specifications.where(new BaseSearch<Article>(new SearchDto("isShow", "eq", 1)));
        Page<Article> newList = articleService.findAll(newSpes, PageableUtil.basicPage(0, 10, new SortDto("desc", "createDate")));
        model.addAttribute("newList", newList);

        Specifications goodSpe = Specifications.where(new BaseSearch<Article>(new SearchDto("isShow", "eq", 1))).
                and(new BaseSearch<>(new SearchDto("isGood", "eq", 1)));
        Page<Article> goodList = articleService.findAll(goodSpe, PageableUtil.basicPage(0, 10, new SortDto("createDate")));
        model.addAttribute("goodList", goodList);

        Page<Account> accountList = accountService.findAll(PageableUtil.basicPage(0, 10));
        model.addAttribute("accountList", accountList);

        return "web/article/index";
    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable Integer id, Model model, HttpServletRequest request) {
        Article art = articleService.findOne(id);
        model.addAttribute("article", art);
        model.addAttribute("account", accountService.findOne(art.getAccountId())); //对应用户

        List<CateDto> cateDtoList = articleService.queryCates(art.getAccountId());
        model.addAttribute("cateDtoList", cateDtoList);

        Specifications newSpes = Specifications.
                where(new BaseSearch<Article>(new SearchDto("accountId", "eq", art.getAccountId()))).
                and(new BaseSearch<>(new SearchDto("isShow", "eq", 1)));
        Page<Article> newList = articleService.findAll(newSpes, PageableUtil.basicPage(0, 10, new SortDto("desc", "createDate")));

        model.addAttribute("newList", newList); //10条新数据

        Specifications goodSpes = Specifications.
                where(new BaseSearch<Article>(new SearchDto("accountId", "eq", art.getAccountId()))).
                and(new BaseSearch<>(new SearchDto("isGood", "eq", 1))).
                and(new BaseSearch<>(new SearchDto("isShow", "eq", 1)));
        Page<Article> goodList = articleService.findAll(goodSpes, PageableUtil.basicPage(0, 10, new SortDto("desc", "createDate")));

        model.addAttribute("goodList", goodList);

        articleService.updateReadCount(id, 1);
        return "web/article/detail";
    }
}
