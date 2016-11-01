package com.zslin.web.controller.admin;

import com.zslin.basic.annotations.AdminAuth;
import com.zslin.basic.utils.PageableUtil;
import com.zslin.basic.utils.ParamFilterUtil;
import com.zslin.web.model.Article;
import com.zslin.web.service.IArticleService;
import com.zslin.web.tools.EmailTools;
import com.zslin.web.tools.RequestTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 钟述林 393156105@qq.com on 2016/11/1 8:44.
 */
@Controller
@RequestMapping(value = "admin/article")
@AdminAuth(name="博文管理", orderNum=1, psn="系统应用", pentity=0, porderNum=20)
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    @Autowired
    private EmailTools emailTools;

    @AdminAuth(name="博文管理", orderNum=1, icon="fa fa-list-ul", type="1")
    @RequestMapping(value="list", method= RequestMethod.GET)
    public String list(Model model, Integer page, HttpServletRequest request) {
        Page<Article> datas = articleService.findAll(new ParamFilterUtil<Article>().buildSearch(model, request),
                PageableUtil.basicPage(page));
        model.addAttribute("datas", datas);
        return "admin/article/list";
    }

    @RequestMapping(value = "updateField", method = RequestMethod.POST)
    @AdminAuth(name="设置博文属性", orderNum=2, icon="fa fa-pencil")
    public @ResponseBody String updateField(Integer id, String field, Integer val, HttpServletRequest request) {
        if("isGood".equalsIgnoreCase(field)) {
            articleService.updateIsGood(id, val);
        } else {
            articleService.updateIsShow(id, val);
        }
        Article art = articleService.findOne(id);
        if(art!=null) {
            emailTools.sendOnUpdateArticle(field, val, art.getAccountEmail(), art.getTitle(),
                    RequestTools.getCurUrl(request)+"/web/article/detail/"+art.getId());
        }
        return "1";
    }
}
