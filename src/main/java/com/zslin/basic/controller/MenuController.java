package com.zslin.basic.controller;

import com.zslin.basic.annotations.AdminAuth;
import com.zslin.basic.annotations.Token;
import com.zslin.basic.model.Menu;
import com.zslin.basic.service.IMenuService;
import com.zslin.basic.service.MenuServiceImpl;
import com.zslin.basic.tools.AuthTools;
import com.zslin.basic.tools.TokenTools;
import com.zslin.basic.utils.BaseSearch;
import com.zslin.basic.utils.PageableUtil;
import com.zslin.basic.utils.SearchDto;
import com.zslin.basic.utils.SortDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 菜单管理Controller
 * @author zsl-pc 20160511
 *
 */
@Controller
@RequestMapping(value="admin/menu")
@AdminAuth(name = "菜单管理", psn="权限管理", orderNum = 1, pentity=0, porderNum=2)
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @Autowired
    private MenuServiceImpl menuServiceImpl;

    @Autowired
    private AuthTools authTools;

    /** 列表 */
    @AdminAuth(name = "菜单管理", orderNum = 1, icon="icon-list", type="1")
    @RequestMapping(value="list", method= RequestMethod.GET)
    public String list(Model model, Integer pid, Integer page, HttpServletRequest request) {
        String treeJson = menuServiceImpl.queryTreeJson("1");
        Page<Menu> datas ;
        if(pid==null || pid<=0) {
            BaseSearch<Menu> spec = new BaseSearch<>(new SearchDto("pid", "isnull", ""));
            datas = menuService.findAll(Specifications.where(spec).and(new BaseSearch<>(new SearchDto("type", "eq", "1"))), PageableUtil.basicPage(page, 15, new SortDto("asc", "orderNum")));
        } else {
            BaseSearch<Menu> spec = new BaseSearch<>(new SearchDto("pid", "eq", pid));
            datas = menuService.findAll(Specifications.where(spec).and(new BaseSearch<>(new SearchDto("type", "eq", "1"))), PageableUtil.basicPage(page, 15, new SortDto("asc", "orderNum")));
        }
        model.addAttribute("treeJson", treeJson);
        model.addAttribute("datas", datas);
        return "admin/basic/menu/list";
    }

    @AdminAuth(name="重构菜单", orderNum=3)
    @RequestMapping(value="rebuildMenus", method=RequestMethod.POST)
    public @ResponseBody String rebuildMenus(Model model, HttpServletRequest request) {
        authTools.buildSystemMenu();
        return "1";
    }

    @Token(flag=Token.READY)
    @AdminAuth(name="修改菜单", orderNum=3)
    @RequestMapping(value="update/{id}", method=RequestMethod.GET)
    public String update(Model model, @PathVariable Integer id, HttpServletRequest request) {
        Menu m = menuService.findOne(id);
        model.addAttribute("menu", m);
        return "admin/basic/menu/update";
    }

    @Token(flag=Token.CHECK)
    @RequestMapping(value="update/{id}", method=RequestMethod.POST)
    public String update(Model model, @PathVariable Integer id, Menu menu, HttpServletRequest request) {
        if(TokenTools.isNoRepeat(request)) {
            Menu m = menuService.findOne(id);
            m.setIcon(menu.getIcon());
            menuService.save(m);
        }
        return "redirect:/admin/menu/list";
    }

    @RequestMapping("updateSort")
    @AdminAuth(name="菜单排序", orderNum=4)
    public @ResponseBody String updateSort(Integer[] ids) {
        try {
            menuServiceImpl.updateSort(ids);
        } catch (Exception e) {
            return "0";
        }
        return "1";
    }
}