package com.mapper;

import com.entity.Menu;
import com.entity.MenuDto;

import java.util.List;
import java.util.Map;

public interface MenuMapper extends BaseMapper<Menu,String> {

//    List<Map<String, Object>> getMenuTree(Map<String,Object> map );
    List<MenuDto> getMenuTree(Map<String,Object> map );

    List<MenuDto> getIndexMenuByRole(Map<String,Object> map );

    void updateStatus(Map<String, Object> map);

    List<Map<String,Object>> getRoleMenu(Map<String, Object> map);



}
