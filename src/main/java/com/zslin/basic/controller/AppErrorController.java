package com.zslin.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 钟述林 393156105@qq.com on 2016/10/27 9:37.
 */
@Controller
public class AppErrorController {

    @RequestMapping(value = "404")
    public String error404(Model model, HttpServletRequest request) {
        return "admin/basic/errors/404";
    }

    @RequestMapping(value = "400")
    public String error400(Model model, HttpServletRequest request) {
        return "admin/basic/errors/400";
    }

    @RequestMapping(value = "500")
    public String error500(Model model, HttpServletRequest request) {
        return "admin/basic/errors/500";
    }
}
