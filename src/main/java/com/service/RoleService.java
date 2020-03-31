package com.service;

import com.entity.Role;

import java.util.List;
import java.util.Map;

public interface RoleService extends BaseService<Role, String> {


    int updateStatus(String onlineId, Map<String, Object> map);

    int updateUserRole(String onlineId, String userId, List<String> roleIds);

    Map<String, Object> getUserRole(String onlineId, String userId);
}
