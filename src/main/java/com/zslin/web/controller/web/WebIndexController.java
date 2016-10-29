package com.zslin.web.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 钟述林 393156105@qq.com on 2016/10/29 22:19.
 */
@Controller
public class WebIndexController {

    @RequestMapping(value = {"", "/", "index"}, method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {

        return "/web/index";
    }
}
