package com.service;

import com.entity.Menu;

import java.util.Map;

public interface MenuService extends BaseService<Menu,String> {


    Map<String, Object> getTreeMenu(Map<String, Object> map);

    void updateStatus(Map<String, Object> map);

    Map<String,Object> getRoleMenu(Map<String, Object> map);
}
