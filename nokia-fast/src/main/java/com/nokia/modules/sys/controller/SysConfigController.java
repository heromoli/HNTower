package com.nokia.modules.sys.controller;

import com.nokia.common.annotation.SysLog;
import com.nokia.modules.sys.entity.SysConfigEntity;
import com.nokia.modules.sys.service.SysConfigService;
import com.nokia.utils.PageUtils;
import com.nokia.utils.RData;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by wow on 2019/6/8.
 */
@RestController
@RequestMapping("/sys/config")
public class SysConfigController extends BaseController {

    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 所有配置列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:config:list")
    public RData list(@RequestParam Map<String, Object> params){
        PageUtils page = sysConfigService.queryPage(params);

        return RData.ok().put("page", page);
    }


    /**
     * 配置信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:config:info")
    public RData info(@PathVariable("id") Long id){
        SysConfigEntity config = sysConfigService.getById(id);

        return RData.ok().put("config", config);
    }

    /**
     * 保存配置
     */
    @SysLog("保存配置")
    @PostMapping("/save")
    @RequiresPermissions("sys:config:save")
    public RData save(@RequestBody SysConfigEntity config){
//        ValidatorUtils.validateEntity(config);
        sysConfigService.saveConfig(config);
        return RData.ok();
    }

    /**
     * 修改配置
     */
    @SysLog("修改配置")
    @PostMapping("/update")
    @RequiresPermissions("sys:config:update")
    public RData update(@RequestBody SysConfigEntity config){
//        ValidatorUtils.validateEntity(config);

        sysConfigService.update(config);

        return RData.ok();
    }

    /**
     * 删除配置
     */
    @SysLog("删除配置")
    @PostMapping("/delete")
    @RequiresPermissions("sys:config:delete")
    public RData delete(@RequestBody Long[] ids){
        sysConfigService.deleteBatch(ids);
        return RData.ok();
    }

}
