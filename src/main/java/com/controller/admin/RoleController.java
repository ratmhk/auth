package com.controller.admin;

import com.controller.BaseController;
import com.entity.ResultMsg;
import com.entity.ResultStatusCode;
import com.entity.Role;
import com.exception.ParamException;
import com.service.RoleService;
import com.utils.CheckParamUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController<Role, String> {

    private static final Logger log = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;


    /**
     * 更新状态
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST  )
    @ResponseBody
    @RequiresRoles(value = {"onlyEdit","超级管理员"},logical = Logical.OR)
    public ResultMsg updateStatus(HttpServletRequest request, @RequestBody Map<String,Object> map){
        Map<String, Object> body = new HashMap<String, Object>();
        try {
            String onlineId = request.getHeader("id");
            String noticeId = (String) CheckParamUtil.checkMap(map, "roleId", true);
            String status = (String)CheckParamUtil.checkMap(map, "status", true);
            map.put("updateUser","admin");
            map.put("updateDate",new Date());
            int num = roleService.updateStatus(onlineId,map);
            if (num == 1)  return new ResultMsg(ResultStatusCode.SUCCESS.getErrorCode(), ResultStatusCode.SUCCESS.getErrorMsg(),null);
            else  return   new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), ResultStatusCode.SYSTEM_ERR.getErrorMsg(),null);
        }catch (ParamException e){
            e.printStackTrace();
            return   new ResultMsg(ResultStatusCode.PARAM_ERR.getErrorCode(), ResultStatusCode.PARAM_ERR.getErrorMsg(),null);
        }catch (Exception e){
            e.printStackTrace();
            return   new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), ResultStatusCode.SYSTEM_ERR.getErrorMsg(),null);
        }

    }


    /**
     * 更新用户角色
     * @param request
     * @param map
     * @return
     */

    @RequestMapping(value = "/updateUserRole", method = RequestMethod.POST  )
    @ResponseBody
    @RequiresRoles(value = {"onlyEdit","超级管理员"},logical = Logical.OR)
    public ResultMsg updateUserRole(HttpServletRequest request, @RequestBody Map<String,Object> map){
        Map<String, Object> body = new HashMap<String, Object>();
        try {
            String onlineId = request.getHeader("id");
            List<String> roleIds = (List<String>) CheckParamUtil.checkMap(map, "roleIds", true);
            String userId = (String)CheckParamUtil.checkMap(map, "userId", true);

            roleService.updateUserRole(onlineId, userId, roleIds);
            return new ResultMsg(ResultStatusCode.SUCCESS.getErrorCode(), ResultStatusCode.SUCCESS.getErrorMsg(),null);
        }catch (ParamException e){
            e.printStackTrace();
            return   new ResultMsg(ResultStatusCode.PARAM_ERR.getErrorCode(), ResultStatusCode.PARAM_ERR.getErrorMsg(),null);
        }catch (Exception e){
            e.printStackTrace();
            return   new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), ResultStatusCode.SYSTEM_ERR.getErrorMsg(),null);
        }

    }


    /**
     * 获取用户角色
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value = "/getUserRole", method = RequestMethod.POST  )
    @ResponseBody
    public ResultMsg getUserRole(HttpServletRequest request, @RequestBody Map<String,Object> map){
        Map<String, Object> body = new HashMap<String, Object>();
        try {
            String onlineId = request.getHeader("id");
            String userId = (String)CheckParamUtil.checkMap(map, "userId", true);

            body = roleService.getUserRole(onlineId, userId);
            return new ResultMsg(ResultStatusCode.SUCCESS.getErrorCode(), ResultStatusCode.SUCCESS.getErrorMsg(),body);
        }catch (ParamException e){
            e.printStackTrace();
            return   new ResultMsg(ResultStatusCode.PARAM_ERR.getErrorCode(), ResultStatusCode.PARAM_ERR.getErrorMsg(),null);
        }catch (Exception e){
            e.printStackTrace();
            return   new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), ResultStatusCode.SYSTEM_ERR.getErrorMsg(),null);
        }

    }


    /**
     * 获取用户的角色
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value = "/getRolePermission", method = RequestMethod.POST  )
    @ResponseBody
    public ResultMsg getRolePermission(HttpServletRequest request, @RequestBody Map<String,Object> map){
        Map<String, Object> body = new HashMap<String, Object>();
        try {
            String onlineId = request.getHeader("id");
            String userId = (String)CheckParamUtil.checkMap(map, "userId", true);

            body = roleService.getUserRole(onlineId, userId);
            return new ResultMsg(ResultStatusCode.SUCCESS.getErrorCode(), ResultStatusCode.SUCCESS.getErrorMsg(),body);
        }catch (ParamException e){
            e.printStackTrace();
            return   new ResultMsg(ResultStatusCode.PARAM_ERR.getErrorCode(), ResultStatusCode.PARAM_ERR.getErrorMsg(),null);
        }catch (Exception e){
            e.printStackTrace();
            return   new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), ResultStatusCode.SYSTEM_ERR.getErrorMsg(),null);
        }

    }



}
