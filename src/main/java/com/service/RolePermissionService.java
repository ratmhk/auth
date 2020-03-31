package com.service;

import com.entity.RolePermission;

import java.util.List;

public interface RolePermissionService extends BaseService<RolePermission,  String>{

    int updateRolePermission(String onlineId, String roleId, List<String> ids);
}
