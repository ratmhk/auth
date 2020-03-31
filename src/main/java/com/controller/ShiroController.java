package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.entity.ResultMsg;
import com.utils.MyEncodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ShiroController {

    @RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
    @ResponseBody
    public String ajaxLogin(/*UserInfo userInfo*/@RequestBody Map<String, Object> map) {
        JSONObject jsonObject = new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        String acc = (String) map.get("acc");
        String pwd = (String) map.get("pwd");
        String shapwd =  MyEncodeUtil.getSHA256StrJava(pwd,"SHA-256");

        System.out.println("acc "+acc+"shapwd  "+shapwd);
        UsernamePasswordToken token = new UsernamePasswordToken(acc,shapwd/*userInfo.getUsername(), userInfo.getPassword()*/);

        try {
            subject.login(token);
            jsonObject.put("token", subject.getSession().getId());
            jsonObject.put("msg", "登录成功");
        } catch (IncorrectCredentialsException e) {
            jsonObject.put("msg", "密码错误");
        } catch (LockedAccountException e) {
            jsonObject.put("msg", "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            jsonObject.put("msg", "该用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }



    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * @return
     */
    @RequestMapping(value = "/unauth")
    @ResponseBody
    public ResultMsg unauth() {
        Map<String, Object> map = new HashMap<String, Object>();
        return new ResultMsg(7003,"无权限",null);
    }

     @RequestMapping(value = "/a")
     @RequiresRoles({"rolea"})
     @ResponseBody
    public Object a() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "0");
        map.put("msg", "lala啦啦AAAAAAAAAA");
        return map;
    }

    @RequestMapping(value = "/b")
    @RequiresRoles({"roleb"})
    @ResponseBody
    public Object b() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "0");
        map.put("msg", "lala啦啦BBBBBBBBBBBB");
        return map;
    }

    @RequestMapping(value = "/c")
    @RequiresRoles(value = {"rolec","超级管理员"},logical = Logical.OR)
    @ResponseBody
    public Object c() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "0");
        map.put("msg", "lala啦啦CCCCCCCCCCCCCCCC");
        return map;
    }


    @RequestMapping(value = "/admin")
    @RequiresRoles({"超级管理员"})
    @ResponseBody
    public Object admin() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "0");
        map.put("admin", "admin权限就能看到");
        return map;
    }




}
