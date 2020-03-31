package com.config;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyGloExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(MyGloExceptionHandler.class);

    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public Map<String, Object> ErrorHandler(AuthorizationException e) {
        log.error("没有通过权限验证！", e);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("errorCode", "101");
        map.put("errorMsg", "没有通过权限验证!");
        return map;
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public Map<String, Object> unauthorizedExceptionHandler(AuthorizationException e) {
        log.error("没有通过权限验证！", e);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("errorCode", 7003);
        map.put("errorMsg", "没有权限!");
        return map;
    }


    @ExceptionHandler(IncorrectCredentialsException.class)
    @ResponseBody
    public Map<String, Object> exceptionHandler() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("errorCode", "101");
        map.put("errorMsg", "密码不正确!");
        log.info("密码不正确");
        return map;
    }

    @ExceptionHandler(UnknownAccountException.class)
    @ResponseBody
    public Map<String, Object> exceptionHandler2() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("errorCode", "101");
        map.put("errorMsg", "账号不存在!");
        log.info("账号不存在");
        return map;
    }


}
