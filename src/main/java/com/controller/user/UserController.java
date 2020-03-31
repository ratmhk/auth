package com.controller.user;

import com.controller.BaseController;
import com.entity.ResultMsg;
import com.entity.ResultStatusCode;
import com.entity.User;
import com.service.UserService;
import com.utils.CheckParamUtil;
import com.utils.MyEncodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User, String> {

    private static  final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;



    @RequestMapping(value = "/regist", method = RequestMethod.POST  )
    @ResponseBody
    @RequiresRoles(value = {"onlyInsert","超级管理员"},logical = Logical.OR)
    public ResultMsg userRegist(HttpServletRequest request,
                                @RequestBody User user){
        Map<String, Object> body = new HashMap<String, Object>();
        log.info("提交的数据  "+user.toString());
        int res = userService.addUser(user);
        if (res == 1){
            return   new ResultMsg(0,"OK,SUCCESS",null);
        }
        log.info("插入新用户失败" );
        body.put("msg","新增用户失败");
        return   new ResultMsg(-1,"新增用户失败",body);

    }


    @RequestMapping(value = "/checkUserAcc", method = RequestMethod.POST  )
    @ResponseBody
    public ResultMsg checkUserAcc(HttpServletRequest request,
                                  @RequestBody Map<String,Object> map){
        Map<String, Object> body = new HashMap<String, Object>();
        String acc = (String) map.get("acc");
        body =  userService.checkUserAcc(acc);
        return   new ResultMsg(0,"",body);

    }




    @RequestMapping(value = "/login", method = RequestMethod.POST  )
    @ResponseBody
    public ResultMsg userLogin(HttpServletRequest request,
                               @RequestBody Map<String,Object> map){
        Map<String, Object> body = new HashMap<String, Object>();
        String acc = (String) map.get("acc");
        String password = (String) map.get("password");

        body =  userService.userLogin(acc, password);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(acc, MyEncodeUtil.getSHA256StrJava(password,"SHA256") );

        try {

            subject.login(token);

        }catch (AuthenticationException e){
            log.error("认证失败，账密错误");
            e.printStackTrace();
        }
        body.put("token", subject.getSession().getId());
        boolean falg = (Boolean) body.get("flag");
        if (falg){
            return   new ResultMsg(0,"账号密码正确",body);// map.put("userInfo", user);
        }
        body.put("msg","登录失败");
        return   new ResultMsg(-1,"账号或密码不正确",body);

    }


    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST  )
    @ResponseBody
    @RequiresRoles(value = {"onlyEdit","超级管理员"},logical = Logical.OR)
    public ResultMsg updateUser(HttpServletRequest request,
                               @RequestBody User user){
        Map<String, Object> body = new HashMap<String, Object>();

        try {
            body = userService.updateUser(user, "system");
        }catch (Exception e){
            new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), ResultStatusCode.SYSTEM_ERR.getErrorMsg(),null);
        }
        return   new ResultMsg(0, ResultStatusCode.SUCCESS.getErrorMsg(),body);
    }




    @RequestMapping(value = "/changeImg", method = RequestMethod.POST  )
    @ResponseBody
    public ResultMsg changeImg(HttpServletRequest request,
                                    @RequestBody Map<String,Object> map){
        Map<String, Object> body = new HashMap<String, Object>();
        String onlineId = request.getHeader("id");

        try {
            String img = (String) CheckParamUtil.checkMap(map,"img",true);
            String userId = (String) CheckParamUtil.checkMap(map,"userId",true);

            userService.updateImg(onlineId,userId,img);

        }catch (Exception e){
            e.printStackTrace();
            return new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), e.getStackTrace().toString(),null);
        }
        return   new ResultMsg(0, ResultStatusCode.SUCCESS.getErrorMsg(),null);
    }


    @RequestMapping(value = "/test"  )
    @ResponseBody
    public Map<String, Object> test(HttpServletRequest request     ){
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("key","val值");

        return body;
    }






}
