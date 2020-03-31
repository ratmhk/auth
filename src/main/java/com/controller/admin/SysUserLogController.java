package com.controller.admin;

import com.entity.PageBean;
import com.entity.ResultMsg;
import com.entity.ResultStatusCode;
import com.service.SysUserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统用户操作的日志
 */
@Controller
@RequestMapping("/sysLog")
public class SysUserLogController {

    @Autowired
    private SysUserLogService sysUserLogService;


    /**
     * 条件获取
     * @param request
     * @param map
     * @return
     */
    @RequestMapping("/getListByMapPage")
    @ResponseBody
    public ResultMsg getListByMapPage(HttpServletRequest request, @RequestBody Map<String,Object> map){
        try {
            Map<String, Object> body = new HashMap<String, Object>();
            int currentPage = PageBean.getCurrentPage(map);//当前页为第几页
            int pageSize = PageBean.getPageSize(map);//每页显示的记录数
            body = sysUserLogService.getListByMapPage(map, currentPage, pageSize);

            return   new ResultMsg(0,"success",body);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), e.getStackTrace().toString(),null);
        }


    }


}
