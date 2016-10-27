package com.zslin.basic.controller;

import com.zslin.basic.annotations.AdminAuth;
import com.zslin.basic.annotations.Token;
import com.zslin.basic.exception.SystemException;
import com.zslin.basic.model.Role;
import com.zslin.basic.model.User;
import com.zslin.basic.service.IRoleService;
import com.zslin.basic.service.IUserService;
import com.zslin.basic.service.UserRoleServiceImpl;
import com.zslin.basic.tools.TokenTools;
import com.zslin.basic.utils.PageableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zsl-pc on 2016/9/6.
 */
@Controller
@RequestMapping(value="admin/user")
@AdminAuth(name = "用户管理", psn="权限管理", orderNum = 3, pentity=0, porderNum=2)
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private UserRoleServiceImpl userRoleServiceImpl;

    /** 列表 */
    @AdminAuth(name = "用户管理", orderNum = 1, icon="icon-list", type="1")
    @RequestMapping(value="list", method=RequestMethod.GET)
    public String list(Model model, Integer page, HttpServletRequest request) {
        Page<User> datas = userService.findAll(PageableUtil.basicPage(page));
        model.addAttribute("datas", datas);
        return "admin/basic/user/list";
    }

    @AdminAuth(name="用户授权", orderNum=5)
    @RequestMapping(value="roles/{id}", method=RequestMethod.GET)
    public String roles(Model model, @PathVariable Integer id, HttpServletRequest request) {
        model.addAttribute("user", userService.findOne(id)); //获取当前用户
        List<Integer> userRoleIds = userService.listUserRoleIds(id);
        StringBuffer sb = new StringBuffer();
        for(Integer rid : userRoleIds) {sb.append(rid).append(",");}
        sb.append("0");
        List<Role> roleList = roleService.findAll(); //获取所有角色
        model.addAttribute("roleList", roleList);
        model.addAttribute("roleIds", sb.toString()); //获取当前用户所拥有的角色Id
        return "admin/basic/user/roles";
    }

    @Token(flag=Token.READY)
    @AdminAuth(name = "添加用户", orderNum = 2, icon="icon-plus")
    @RequestMapping(value="add", method=RequestMethod.GET)
    public String add(Model model, HttpServletRequest request) {
        User user = new User();
        user.setStatus(1);
        model.addAttribute("user", user);
        return "admin/basic/user/add";
    }

    /** 添加POST */
    @Token(flag=Token.CHECK)
    @RequestMapping(value="add", method=RequestMethod.POST)
    public String add(Model model, User user, HttpServletRequest request) {
        if(TokenTools.isNoRepeat(request)) {
            User u = userService.findByUsername(user.getUsername());
            if(u!=null) {throw new SystemException("用户名【"+user.getUsername()+"】已经存在，不可重复添加！");}
            userService.save(user);
        }
        return "redirect:/admin/user/list";
    }

    @Token(flag=Token.READY)
    @AdminAuth(name="修改用户", orderNum=3)
    @RequestMapping(value="update/{id}", method=RequestMethod.GET)
    public String update(Model model, @PathVariable Integer id, HttpServletRequest request) {
        User u = userService.findOne(id);
        model.addAttribute("user", u);
        return "admin/basic/user/update";
    }

    @Token(flag=Token.CHECK)
    @RequestMapping(value="update/{id}", method=RequestMethod.POST)
    public String update(Model model, @PathVariable Integer id, User user, HttpServletRequest request) {
        if(TokenTools.isNoRepeat(request)) {
            User u = userService.findOne(id);
            u.setIsAdmin(user.getIsAdmin());
			u.setStatus(user.getStatus());
            u.setNickname(user.getNickname());
            userService.save(u);
        }
        return "redirect:/admin/user/list";
    }

    @AdminAuth(name="删除用户", orderNum=4)
    @RequestMapping(value="delete/{id}", method=RequestMethod.POST)
    public @ResponseBody String delete(@PathVariable Integer id) {
        try {
            userService.delete(id);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }

    @RequestMapping(value = "addOrDelUserRole", method = RequestMethod.POST)
    @AdminAuth(name="为用户分配角色", orderNum=5)
    public @ResponseBody String addOrDelUserRole(Integer userId, Integer roleId) {
        try {
            userRoleServiceImpl.addOrDelete(userId, roleId);
        } catch (Exception e) {
            throw new SystemException("为用户分配角色失败");
        }
        return "1";
    }
}
