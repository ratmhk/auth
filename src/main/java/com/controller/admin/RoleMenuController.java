package com.controller.admin;

import com.controller.BaseController;
import com.entity.ResultMsg;
import com.entity.ResultStatusCode;
import com.entity.RoleMenu;
import com.service.RoleMenuService;
import com.utils.CheckParamUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
@RequestMapping("/roleMenu")
public class RoleMenuController extends BaseController<RoleMenu,String> {
    private final static Logger log = LoggerFactory.getLogger(RoleMenuController.class);

    @Autowired
    private RoleMenuService roleMenuService;


    /**
     * 分配角色的菜单
     * @param request
     * @param map
     * @return
     */
    @RequiresRoles(value = {"onlyEdit","超级管理员"},logical = Logical.OR)
    @RequestMapping("/allocateRoleMenu")
    @ResponseBody
    public ResultMsg allocateRoleMenu(HttpServletRequest request, @RequestBody Map<String, Object> map) {
        try {
            Map<String, Object> body = new HashMap<String, Object>();
            List<Integer> ids = (List<Integer>) CheckParamUtil.checkMap(map, "ids", true);
            String roleId = (String) CheckParamUtil.checkMap(map, "roleId", true);
            String cid = request.getHeader("id");
            if (StringUtils.isEmpty(cid)) cid = "system";
            roleMenuService.allocateRoleMenu(ids, roleId, cid);
            return new ResultMsg(0, "success", body);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), ResultStatusCode.SYSTEM_ERR.getErrorMsg() , null);
        }


    }
}
