package com.controller;

import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletContext;


@Controller
public class CommonController {
    @Resource
    ServletContext servletContext;
    @PostConstruct
    public void init() {
        String webPath = servletContext.getContextPath();
        System.out.println("设置  webPath " +webPath);
        servletContext.setAttribute("webPath", webPath);
    }
}