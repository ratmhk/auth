package com.service;

import com.entity.RoleMenu;

import java.util.List;

public interface RoleMenuService extends BaseService<RoleMenu,String >{
    int allocateRoleMenu(List<Integer> ids, String roleId, String cid);
}
