package com.service.impl;

import com.entity.LogAnnotation;
import com.entity.Menu;
import com.entity.MenuDto;
import com.mapper.MenuMapper;
import com.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu,String> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;



    @Override
    public Map<String, Object> getTreeMenu(Map<String, Object> map) {
        Map<String,Object> res = new HashMap<>();
//        List<Map<String,Object>>  list = menuMapper.getMenuTree(map);
        List<MenuDto>  list = menuMapper.getMenuTree(map);

        if (null == map.get("add")){
            MenuDto menuDto = new MenuDto();
            menuDto.setParentId(0);
            menuDto.setId(1);
            menuDto.setName("请选择");
            list.add(menuDto);
     }

        res.put("list",list);
        return res;

    }

    @LogAnnotation(moduleName = "菜单管理", operate = "修改菜单")
    @Override
    @Transactional
    public void updateStatus(Map<String, Object> map) {
        menuMapper.updateStatus(map);
    }

    @Override
    public Map<String,Object> getRoleMenu(Map<String, Object> map) {

        List<Map<String, Object>> roleMenus = menuMapper.getRoleMenu(map);
        Map<String,Object> res = new HashMap<>();
        res.put("list",roleMenus);
        return res ;
    }
}
