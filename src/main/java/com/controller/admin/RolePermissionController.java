package com.controller.admin;

import com.controller.BaseController;
import com.entity.ResultMsg;
import com.entity.ResultStatusCode;
import com.entity.RolePermission;
import com.service.RolePermissionService;
import com.utils.CheckParamUtil;
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
@RequestMapping("/rolePermisson")
public class RolePermissionController extends BaseController<RolePermission, String> {

    @Autowired
    private RolePermissionService rolePermissionService;


    @RequestMapping("/updateRolePermission")
    @ResponseBody
    public ResultMsg updateRolePermission(HttpServletRequest request, @RequestBody Map<String,Object> map){
        try {
            Map<String, Object> body = new HashMap<String, Object>();
            String onlineId = request.getHeader("id");
            String roleId = (String) CheckParamUtil.checkMap(map, "roleId", true);
            List<String> ids = (List<String>) CheckParamUtil.checkMap(map, "ids", true);
            rolePermissionService.updateRolePermission(onlineId, roleId, ids);

            return   new ResultMsg(0,"success",body);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), e.getStackTrace().toString(),null);
        }


    }


}
