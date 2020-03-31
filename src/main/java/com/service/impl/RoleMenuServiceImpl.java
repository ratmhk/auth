package com.service.impl;

import com.entity.RoleMenu;
import com.mapper.RoleMenuMapper;
import com.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenu,String>  implements RoleMenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    @Transactional
    public int allocateRoleMenu(List<Integer> ids, String roleId, String cid) {
       /* roleMenuMapper.selectByRole(roleId);*/
        roleMenuMapper.deleteByRole(roleId);

        List<RoleMenu> roleMenus = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setIdForInsert(cid);
            roleMenu.setMenuId(ids.get(i));
            roleMenu.setRoleId(roleId);
            roleMenus.add(roleMenu);
        }

        if (roleMenus.size() > 0 )
        return roleMenuMapper.insertBatch(roleMenus);
        return 0;
    }
}
