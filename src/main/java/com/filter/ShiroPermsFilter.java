package com.filter;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShiroPermsFilter extends PermissionsAuthorizationFilter {
    /**同样扩展PermissionsAuthorizationFilter，用于ajax访问接口登录但是资源操作认证不通过的处理
     * shiro认证perms资源失败后回调方法
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws IOException
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String requestedWith = httpServletRequest.getHeader("X-Requested-With");
        if (StringUtils.isNotEmpty(requestedWith) &&
                StringUtils.equals(requestedWith, "XMLHttpRequest")) {//如果是ajax返回指定格式数据
            /*ResponseHeader responseHeader = new ResponseHeader();
            responseHeader.setResponse(ResponseHeader.SC_FORBIDDEN, null);*/
            httpServletResponse.setStatus(403);
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json");
            JSONObject jo = new JSONObject();
            try {
                jo.put("response",httpServletResponse.toString());
            }catch (Exception e){
                e.printStackTrace();
            }
            httpServletResponse.getWriter().write(jo.toString());
        } else {//如果是普通请求进行重定向
            httpServletResponse.sendRedirect("/403");
        }
        return false;
    }
}
