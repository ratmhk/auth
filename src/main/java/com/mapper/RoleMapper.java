package com.mapper;

import com.entity.Role;

import java.util.Map;

public interface RoleMapper extends BaseMapper<Role, String> {

    int updateStatus(Map<String, Object> map);

}
