package com.mapper;

import com.entity.RoleMenu;

import java.util.List;

public interface RoleMenuMapper extends BaseMapper<RoleMenu,String> {

    int insertBatch(List<RoleMenu> list);

    int deleteByRole(String roleId);

    List<RoleMenu> selectByRole(String roleId);
}
