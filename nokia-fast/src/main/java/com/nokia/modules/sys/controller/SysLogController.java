package com.nokia.modules.sys.controller;

import com.nokia.modules.sys.service.SysLogService;
import com.nokia.utils.PageUtils;
import com.nokia.utils.RData;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by wow on 2019/6/8.
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    /**
     * 列表
     */
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("sys:log:list")
    public RData list(@RequestParam Map<String, Object> params){
        PageUtils page = sysLogService.queryPage(params);

        return RData.ok().put("page", page);
    }

}
