package com.mapper;

import com.entity.RolePermission;

import java.util.List;

public interface RolePermissionMapper extends BaseMapper<RolePermission, String>{

   int deleteByRole(String roleId);

   int insertBatch(List<RolePermission> rolePermission);
}
