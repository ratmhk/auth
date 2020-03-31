package com.service;

import com.entity.SysUserLog;

import java.util.Map;

public interface SysUserLogService  {

    int addLog(SysUserLog sysUserLog);

    Map<String, Object> getListByMapPage(Map<String, Object> map, int currentPage, int pageSize);
}

