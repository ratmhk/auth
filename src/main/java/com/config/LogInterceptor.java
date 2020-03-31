package com.config;

import com.alibaba.fastjson.JSON;
import com.entity.LogAnnotation;
import com.entity.SysUserLog;
import com.service.SysUserLogService;
import com.utils.HttpUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Aspect
@Component
public class LogInterceptor {

    private static Map<String,String> map = new HashMap<>();

    static {
        map.put("/menu/updateStatus","菜单模块");
    }

    @Pointcut("execution(* com.service..*.*(..))")
    public void logRecord(){}

    @AfterReturning(value = "logRecord()",returning = "returnObject" )
    public void afterReturning(JoinPoint point, Object returnObject) {

        try {
           /* Subject subject = null ;
            try {
                subject = CarfiUserUtil.getSubject();
            } catch (Exception e) {
                e.printStackTrace();
                //发生异常则不进行
                return ;
            }*/
            SysUserLog userLog = new SysUserLog();
            String targetName = point.getTarget().getClass().getSimpleName();//.getName();
            Class targetClass = Class.forName(point.getTarget().getClass().getName());
            String methodName = point.getSignature().getName();
            Method[] method = targetClass.getMethods();
            Object[] params = point.getArgs(); // 获得参数列表
            for (Method m : method) {
                if (m.getName().equals(methodName)) {
                    Class[] tmpCs = m.getParameterTypes();
                    if (tmpCs.length == params.length) {
                        // 获取注解内容
                        LogAnnotation logAnnotation = m.getAnnotation(LogAnnotation.class);
                        if(logAnnotation != null){
                            //写入参数
                            if(params.length>0){
                                String jsonstr = JSON.toJSONString(params);

                                // 使用json转换工具 将参数转为json串，以便存入数据库
                                String jsonStr = jsonstr;//JsonUtil.toJSONStr(params);
                                if (jsonStr.length()>127) jsonStr = jsonstr.substring(1,127);
                                userLog.setParams(jsonStr);
                            }
                            //获取模块名称
                            String moduleName = logAnnotation.moduleName();
                            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

                            if ("".equals(moduleName)) moduleName = getModuleName(request.getServletPath().split("/")[1]);
                            //获取操作名称
                            String operate = logAnnotation.operate();
                            userLog.setModuleName(moduleName);
                            userLog.setOperate(operate);
                            userLog.setClassName(targetName);
                            userLog.setMethodName(methodName);

                            String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();

//                            userLog.setLogId(uuid);//CommonUtil.generatePrimaryKey()
                            userLog.setTime(new Date());
                           // HttpServletRequest request = (HttpServletRequest)((WebSubject)subject).getServletRequest();
                            userLog.setIp(HttpUtil.getIpAddr(request));//CommonUtil.getIpAddr(request)
                            userLog.setUserId(request.getHeader("opter"));//CarfiUserUtil.getSysUser().getUserId()
                            SysUserLogService userLogService = (SysUserLogService) SpringUtil.getBean ("sysUserLogService");

                            userLogService.addLog(userLog);

                            break;
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public String getModuleName(String str){
        if (str.contains("roleMenu")) return "分配菜单";
        else if(str.contains("menu")) return "菜单模块";
        else if(str.contains("permission")) return "权限模块";
        else if(str.contains("acc")) return "账号模块";
        else if(str.contains("Acc")) return "账号模块";
        else if(str.contains("user")) return "账号模块";
        else if(str.contains("module")) return "资源模块";
        else if(str.contains("role")) return "角色模块";
        else  return "";

    }

   /* public String getOperateName(String str){
        if (str.contains("update")) return "更新";
        else if(str.contains("insert")) return "添加";
        else if(str.contains("delete")) return "删除";
        else if(str.contains("list")) return "查询列表";
        else if(str.contains("get")) return "查询";
        else if(str.contains("login")) return "登陆";
        else  return "";

    }*/


}
