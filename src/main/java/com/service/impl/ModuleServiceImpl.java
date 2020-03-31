package com.service.impl;

import com.entity.Module;
import com.mapper.ModuleMapper;
import com.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImpl extends BaseServiceImpl<Module,String> implements ModuleService {

    @Autowired
    private ModuleMapper moduleMapper;


}
