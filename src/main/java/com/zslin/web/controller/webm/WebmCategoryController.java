package com.zslin.web.controller.webm;

import com.zslin.basic.utils.BaseSearch;
import com.zslin.basic.utils.PageableUtil;
import com.zslin.basic.utils.ParamFilterUtil;
import com.zslin.basic.utils.SearchDto;
import com.zslin.web.model.Account;
import com.zslin.web.model.Category;
import com.zslin.web.service.ICategoryService;
import com.zslin.web.tools.RequestTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 钟述林 393156105@qq.com on 2016/10/31 9:00.
 */
@Controller
@RequestMapping(value = "webm/category")
public class WebmCategoryController {

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model, Integer page, HttpServletRequest request) {
        Account account = RequestTools.getAccount(request);
        Specifications spes = Specifications.where(new BaseSearch<>(new SearchDto("accountId", "eq", account.getId())));
        Page<Category> datas = categoryService.findAll(new ParamFilterUtil<Category>().buildSearch(model, request, spes),
                PageableUtil.basicPage(page));
        model.addAttribute("datas", datas);
        return "webm/category/list";
    }

    /** 添加 */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public @ResponseBody String add(String name, HttpServletRequest request) {
        Account a = RequestTools.getAccount(request);
        Category cate = new Category();
        cate.setName(name);
        cate.setAccountName(a.getNickname());
        cate.setAccountEmail(a.getEmail());
        cate.setAccountId(a.getId());
        categoryService.save(cate);

        return "1";
    }

    /** 修改名称 */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public @ResponseBody String update(Integer id, String name, HttpServletRequest request) {
        Account account = RequestTools.getAccount(request);
        /*Category cate = categoryService.findByIdAndAccountId(id, account.getId());
        if(cate!=null) {
            cate.setName(name);
            categoryService.save(cate);
        }*/
        categoryService.updateName(id, account.getId(), name);
        return "1";
    }

    /** 删除对象 */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public @ResponseBody String delete(Integer id, HttpServletRequest request) {
        Account account = RequestTools.getAccount(request);
        categoryService.delete(id, account.getId());
        return "1";
    }
}
