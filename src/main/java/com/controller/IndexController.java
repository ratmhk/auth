package com.controller;

import com.entity.ResultMsg;
import com.entity.ResultStatusCode;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Controller
public class IndexController {

    @ResponseBody
    @RequestMapping(value = "/index",  produces = "application/json")
    public String index(){
        return  "this is index 默认首页！！！";
    }

    @ResponseBody
    @RequestMapping(value = "/unauth2" )
    public ResultMsg unauth(){
        Map<String,Object> map = new HashMap<>();
        map.put("msg","认证错误");
        return  new ResultMsg(ResultStatusCode.INVALID_LOGIN_PASSWORD.getErrorCode(),
                "未认证",
                map);
    }


    @ResponseBody
    @RequestMapping(value = "/uuid",  produces = "application/json")
    public String testUuid(){
        String id = UUID.randomUUID().toString().replace("-", "");
        return  "id: "+id+"   len"+id.length();
    }

    @ResponseBody
    @RequestMapping(value = "/testMsg",  produces = "application/json")//method = RequestMethod.POST,
    public ResultMsg testMsg(){
         Map<String, Object> body = new HashMap<String, Object>();
         body.put("msg", "test is OK ,测试通过");
         return   new ResultMsg(0,"OK,SUCCESS",body);
    }



    @ResponseBody
    @RequiresRoles(value = {"rolea"})
    @RequestMapping(value = "/rolea",  produces = "application/json")//method = RequestMethod.POST,
    public ResultMsg rolea(HttpServletRequest request){
        System.out.println(request.getHeader("Authorization"));
         Map<String, Object> body = new HashMap<String, Object>();
         body.put("rolea", "test is OK ,测试通过");
         return   new ResultMsg(0,"OK,SUCCESS",body);
    }

    @RequestMapping(value = "/roleb" )//method = RequestMethod.POST,
    @RequiresRoles(value = {"roleb"})
    @ResponseBody
    public ResultMsg roleb(HttpServletRequest request){
        System.out.println(request.getHeader("Authorization"));
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("roleb", "test is OK ,测试通过");
        return   new ResultMsg(0,"OK,SUCCESS",body);
    }


    @RequestMapping(value = "/onlyEdit" )//method = RequestMethod.POST,
    @RequiresRoles(value = {"onlyEdit","admin"},logical = Logical.OR)
    @ResponseBody
    public ResultMsg onlyEdit(HttpServletRequest request){
        System.out.println(request.getHeader("Authorization"));
         Map<String, Object> body = new HashMap<String, Object>();
         body.put("msg", "test is OK ,测试通过");
         return   new ResultMsg(0,"OK,SUCCESS",body);
    }


    @RequestMapping(value = "/onlyDelete" )//method = RequestMethod.POST,
    @RequiresRoles(value = {"onlyDelete","admin"},logical = Logical.OR)
    @ResponseBody
    public ResultMsg onlyDelete(HttpServletRequest request){
        System.out.println(request.getHeader("Authorization"));
         Map<String, Object> body = new HashMap<String, Object>();
         body.put("msg", "test is OK ,测试通过");
         return   new ResultMsg(0,"OK,SUCCESS",body);
    }

    @RequiresRoles(value = {"onlyInsert","admin"},logical = Logical.OR)
    @ResponseBody
    @RequestMapping(value = "/onlyInsert" )//method = RequestMethod.POST,
    public ResultMsg onlyInsert(HttpServletRequest request){
        System.out.println(request.getHeader("Authorization"));
         Map<String, Object> body = new HashMap<String, Object>();
         body.put("msg", "test is OK ,测试通过");
         return   new ResultMsg(0,"OK,SUCCESS",body);
    }


    @RequestMapping(value = "/onlysee",  produces = "application/json")//method = RequestMethod.POST,
    @RequiresRoles(value = {"onlysee","admin"},logical = Logical.OR)
    @ResponseBody
    public ResultMsg onlysee(HttpServletRequest request){
        System.out.println(request.getHeader("Authorization"));
         Map<String, Object> body = new HashMap<String, Object>();
         body.put("msg", "test is OK ,测试通过");
         return   new ResultMsg(0,"OK,SUCCESS",body);
    }

    @RequestMapping(value = "/admin",  produces = "application/json")//method = RequestMethod.POST,
    @ResponseBody
    @RequiresRoles(value = {"admin"} )
    public ResultMsg admin(HttpServletRequest request){
        System.out.println(request.getHeader("Authorization"));
         Map<String, Object> body = new HashMap<String, Object>();
         body.put("msg", "test is OK ,测试通过");
         return   new ResultMsg(0,"OK,SUCCESS",body);
    }







}
