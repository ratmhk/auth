package com.mapper;

import com.entity.Role;
import com.entity.UserRole;

import java.util.List;
import java.util.Map;

public interface UserRoleMapper extends BaseMapper<UserRole,String> {

    int deleteByUserId(String userId);

    Role selectByUserId(Map<String, Object> map);

    int insertBatch(List<UserRole> userRoles);

   // List<UserRole> getUserRole(String userId);
    List<Map<String,Object>> getUserRole(String userId);

    List<Map<String,Object>> getUserRoleByAcc(String acc);

}
