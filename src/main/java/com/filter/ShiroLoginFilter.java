package com.filter;

import com.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.AdviceFilter;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShiroLoginFilter extends AdviceFilter {

    /**
     * 在访问controller前判断是否登录，返回json，不进行重定向。
     * @param request
     * @param response
     * @return true-继续往下执行，false-该filter过滤器已经处理，不继续执行其他过滤器
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        User sysUser = (User) httpServletRequest.getSession().getAttribute("user");
        if (null == sysUser && !StringUtils.contains(httpServletRequest.getRequestURI(), "/login")) {
            String requestedWith = httpServletRequest.getHeader("X-Requested-With");
            if (StringUtils.isNotEmpty(requestedWith) && StringUtils.equals(requestedWith, "XMLHttpRequest")) {//如果是ajax返回指定数据
                /*ResponseHeader responseHeader = new ResponseHeader();
                responseHeader.setResponse(ResponseHeader.SC_MOVED_TEMPORARILY, null);*/
                httpServletResponse.setStatus(301);
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.setContentType("application/json");
                JSONObject jo = new JSONObject();
                jo.put("response",httpServletResponse);
                httpServletResponse.getWriter().write( jo.toString());
                return false;
            } else {//不是ajax进行重定向处理
                httpServletResponse.sendRedirect("/login/local");
                return false;
            }
        }
        return true;
    }

}
