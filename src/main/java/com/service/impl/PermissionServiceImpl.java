package com.service.impl;

import com.entity.Permission;
import com.mapper.PermissionMapper;
import com.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission, String> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;


}
