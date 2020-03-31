package com.service.impl;

import com.entity.RolePermission;
import com.mapper.RolePermissionMapper;
import com.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermission, String> implements RolePermissionService {


    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    @Transactional
    public int updateRolePermission(String onlineId, String roleId, List<String> ids) {
        rolePermissionMapper.deleteByRole(roleId);
        if (ids.size() >0 ){
            List<RolePermission> list = new ArrayList<>();

            for (int i = 0; i < ids.size(); i++) {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setPermisionId(ids.get(i));
                rolePermission.setRoleId(roleId);
                list.add(rolePermission);
            }

            return rolePermissionMapper.insertBatch(list);
        }

        return 0;
    }



}
