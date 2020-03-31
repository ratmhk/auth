package com.config;

import com.entity.PageBean;
import com.entity.SysUserLog;
import com.mapper.SysUserLogMapper;
import com.service.SysUserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("sysUserLogService")
public class SysUserLogServiceImpl implements SysUserLogService {

    @Autowired
    private SysUserLogMapper sysUserLogMapper;

    @Override
    public int addLog(SysUserLog sysUserLog) {
        return sysUserLogMapper.insert(sysUserLog);
    }

    @Override
    public Map<String, Object> getListByMapPage(Map<String, Object> map, int currentPage, int pageSize) {
        Map<String, Object> body = new HashMap<String, Object>();
        List<SysUserLog> list = new ArrayList<SysUserLog>();
        int totalRecord = 0;//总记录数
        PageBean pb = new PageBean(currentPage, pageSize, totalRecord);

        //totalRecord = baseMapper.getListByMap(map).size();
        totalRecord = sysUserLogMapper.getCountByMap(map).intValue();

        if (totalRecord == 0) {  // 当总记录为0时，直接返回
            body.put("totalPage", pb.getTotalPage());
            body.put("totalRecord", totalRecord);
            body.put("list", list);
            return body;
        }

        map.put("startIndex", pb.getStartIndex());
        map.put("pageSize", pageSize);

        list = sysUserLogMapper.getListByMap(map);

        pb = new PageBean(currentPage, pageSize, totalRecord);
        body.put("totalPage", pb.getTotalPage());
        body.put("totalRecord", totalRecord);
        body.put("list", list);
        return body;

    }
}
