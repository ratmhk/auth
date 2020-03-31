package com.controller.admin;

import com.controller.BaseController;
import com.entity.Menu;
import com.entity.ResultMsg;
import com.entity.ResultStatusCode;
import com.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 菜单管理
 */

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController<Menu,String> {
    private final  static Logger log = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService ;

    @RequestMapping("/getTreeMenu")
    @ResponseBody
    public Object getTreeMenu(HttpServletRequest request, @RequestBody(required = false) Map<String,Object> map){
        Map<String, Object> body = new HashMap<String, Object>();
        if (null == map) map = new HashMap<>();
        body = menuService.getTreeMenu(map);

        return  body.get("list");
    }

    /**
     * 更新状态
     * @param request
     * @param map
     * @return
     */
    @RequestMapping("/updateStatus")
    @ResponseBody
    public ResultMsg updateStatus(HttpServletRequest request, @RequestBody Map<String,Object> map){
        Map<String, Object> body = new HashMap<String, Object>();
        String menuId = (String) map.get("menuId");
        String status = (String) map.get("status");
        if (StringUtils.isEmpty(menuId) || StringUtils.isEmpty(status))
            return new ResultMsg(ResultStatusCode.PARAM_ERR.getErrorCode(),ResultStatusCode.PARAM_ERR.getErrorMsg(),null);
        try {
            menuService.updateStatus(map);
        }catch (Exception e){
            return new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(),ResultStatusCode.SYSTEM_ERR.getErrorMsg(),null);
        }

        return   new ResultMsg(0,"success",body);
    }


    /**
     * 获取角色的菜单
     * @param request
     * @param map
     * @return
     */
    @RequestMapping("/getRoleMenu")
    @ResponseBody
    public ResultMsg getRoleMenu(HttpServletRequest request, @RequestBody Map<String,Object> map){
        Map<String, Object> body = new HashMap<String, Object>();
        String roleId = (String) map.get("roleId");

        if (StringUtils.isEmpty(roleId) )
            return new ResultMsg(ResultStatusCode.PARAM_ERR.getErrorCode(),ResultStatusCode.PARAM_ERR.getErrorMsg(),null);
        try {
            body = menuService.getRoleMenu(map);
        }catch (Exception e){
            return new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(),ResultStatusCode.SYSTEM_ERR.getErrorMsg(),null);
        }

        return   new ResultMsg(0,"success",body);
    }


}
