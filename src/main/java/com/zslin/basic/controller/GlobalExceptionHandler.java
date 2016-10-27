package com.zslin.basic.controller;

import com.zslin.basic.exception.ErrorInfo;
import com.zslin.basic.exception.SystemException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zsl-pc on 2016/9/8.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = SystemException.class)
    public String systemExceptionHandler(Model model, HttpServletRequest req, SystemException e) {
        ErrorInfo<String> er = new ErrorInfo<>();
        er.setCode(ErrorInfo.ERROR);
        er.setMessage(e.getMessage());
        er.setUrl(req.getRequestURL().toString());
        er.setParams(req.getQueryString());
        er.setDatas("发生系统异常，无法继续进行！");
        model.addAttribute("errorInfo", er);
//        e.printStackTrace();
        return "admin/basic/errors/system";
    }

    @ExceptionHandler(value = Exception.class)
    public String defaultExceptionHandler(Model model, HttpServletRequest req, Exception e) {
        ErrorInfo<String> er = new ErrorInfo<>();
        er.setCode(ErrorInfo.ERROR);
        er.setMessage(e.getMessage());
        er.setUrl(req.getRequestURL().toString());
        er.setParams(req.getQueryString());
        er.setDatas("发生异常，无法继续进行！");
        model.addAttribute("errorInfo", er);
//        e.printStackTrace();
        return "admin/basic/errors/default";
    }
}
