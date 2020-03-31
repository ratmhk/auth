package com.service.impl;

import com.entity.Role;
import com.entity.UserRole;
import com.mapper.RoleMapper;
import com.mapper.UserRoleMapper;
import com.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, String> implements RoleService {


    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;


    @Override
    @Transactional
    public int updateStatus(String onlineId, Map<String, Object> map) {
        return roleMapper.updateStatus(map);
    }


    @Override
    @Transactional
    public int updateUserRole(String onlineId, String userId, List<String> roleIds) {

            List<UserRole> list = new ArrayList<>();
            for (int i = 0; i < roleIds.size(); i++) {
                UserRole userRole = new UserRole();
                userRole.setIdForInsert(onlineId);
                userRole.setRoleId(roleIds.get(i));
                userRole.setUserId(userId);
                list.add(userRole);
            }

            if (list.size() > 0) {
                userRoleMapper.deleteByUserId(userId);
                return userRoleMapper.insertBatch(list);
            }
            else return  0 ;

    }


    @Override
    public Map<String, Object> getUserRole(String onlineId, String userId) {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        list = userRoleMapper.getUserRole(userId);
        map.put("list",list);
        return map;
    }
}
