package com.controller.admin;

import com.entity.PageBean;
import com.entity.ResultMsg;
import com.entity.ResultStatusCode;
import com.service.UserService;
import com.utils.CheckParamUtil;
import com.utils.MyEncodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/adminAcc")
public class AdminAccController {
    private static final Logger log = LoggerFactory.getLogger(AdminAccController.class);

    @Autowired
    private UserService userService;

    /**
     * 条件筛选，获取账号列表
     * @param request
     * @param map
     * @return
     */
    @RequestMapping("/getAccByMap")
    @ResponseBody
    public ResultMsg getAccByMap(HttpServletRequest request, @RequestBody Map<String,Object> map){
        Map<String, Object> body = new HashMap<String, Object>();
        int currentPage = PageBean.getCurrentPage(map);
        int pageSize = PageBean.getPageSize(map);

        body = userService.getAccByMap(map,currentPage, pageSize);

        return   new ResultMsg(0,"success",body);
    }




    /** 冻结账号
     * 状态1正常 0冻结
     * @param request
     * @param map
     * @return
     */
    @RequiresRoles(value = {"onlyEdit","超级管理员"},logical = Logical.OR)
    @RequestMapping("/changeAccStatus")
    @ResponseBody
    public ResultMsg changeAccStatus(HttpServletRequest request, @RequestBody Map<String,Object> map){
        try {
            Map<String, Object> body = new HashMap<String, Object>();
            //String onlineId = request.getHeader("id");
            List<String> ids = (List<String>) CheckParamUtil.checkMap(map, "ids", true);
            String status = (String) CheckParamUtil.checkMap(map, "status", true);
            userService.changeAccStatus(ids, status);
            return   new ResultMsg(0,"success",body);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), e.getStackTrace().toString(),null);
        }


    }


    /**
     * 登陆
     * @param request
     * @param map
     * @return
     */
    @RequestMapping("/adminLogin")
    @ResponseBody
    public ResultMsg adminLogin(HttpServletRequest request, @RequestBody Map<String,Object> map){
        Map<String, Object> body = new HashMap<String, Object>();
        try {
            String acc = (String) CheckParamUtil.checkMap(map, "acc", true);
            String pwd = (String) CheckParamUtil.checkMap(map, "pwd", true);
            Subject subject = SecurityUtils.getSubject();
            String shapwd =  MyEncodeUtil.getSHA256StrJava(pwd,"SHA-256");
            UsernamePasswordToken token = new UsernamePasswordToken(acc, shapwd);

            subject.login(token);
            body = userService.getLoginSuccessInfo(acc, pwd);
            body.put("token", subject.getSession().getId());
            return   new ResultMsg(0,"success",body);
        }catch(IncorrectCredentialsException e){
            return new ResultMsg(7003, "密码错误"  ,null);
        }catch (AuthenticationException e){
            return new ResultMsg(7003, "登录认证错误"  ,null);
        } catch (Exception e){
            log.error( "账密错误,登录失败");
            e.printStackTrace();
            body.put("flag",false);
            return new ResultMsg(ResultStatusCode.INVALID_LOGIN_PASSWORD.getErrorCode(),
                    ResultStatusCode.INVALID_LOGIN_PASSWORD.getErrorMsg()  ,body);
        }


    }


}
