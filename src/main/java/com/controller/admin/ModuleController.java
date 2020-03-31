package com.controller.admin;

import com.controller.BaseController;
import com.entity.Module;
import com.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 模块管理
 */
@Controller
@RequestMapping("/module")
public class ModuleController extends BaseController<Module,String> {

    @Autowired
    private ModuleService moduleService;

}
