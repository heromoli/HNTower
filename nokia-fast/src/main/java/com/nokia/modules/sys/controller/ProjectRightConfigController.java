package com.nokia.modules.sys.controller;

import com.nokia.common.annotation.SysLog;
import com.nokia.modules.sys.entity.ProjectRightConfigEntity;
import com.nokia.modules.sys.entity.SysUserEntity;
import com.nokia.modules.sys.service.ProjectRightConfigService;
import com.nokia.modules.sys.service.SysUserService;
import com.nokia.utils.PageUtils;
import com.nokia.utils.RData;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wow on 2019/6/26.
 */
@RestController
@RequestMapping("/sys/project/config")
public class ProjectRightConfigController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ProjectRightConfigController.class);

    @Autowired
    private ProjectRightConfigService projectRightConfigService;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 所有配置列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:project:config:list")
    public RData list(@RequestParam Map<String, Object> params) {
        PageUtils page = projectRightConfigService.queryPage(params);
        List<ProjectRightConfigEntity> list = (List<ProjectRightConfigEntity>) page.getList();
        for (ProjectRightConfigEntity projectRightConfigEntity : list) {
            projectRightConfigEntity.setUserName(sysUserService.getById(projectRightConfigEntity.getUserId()).getUsername());
        }
        return RData.ok().put("page", page);
    }

    /**
     * 配置信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:project:config:info")
    public RData info(@PathVariable("id") Long id) {
        ProjectRightConfigEntity config = projectRightConfigService.getById(id);

        return RData.ok().put("config", config);
    }

    /**
     * 保存配置
     */
    @SysLog("保存工程权限配置")
    @PostMapping("/save")
    @RequiresPermissions("sys:project:config:save")
    public RData save(@RequestBody ProjectRightConfigEntity config) {
//        ValidatorUtils.validateEntity(config);
        projectRightConfigService.saveConfig(config);
        return RData.ok();
    }

    /**
     * 修改配置
     */
    @SysLog("修改工程权限配置")
    @PostMapping("/update")
    @RequiresPermissions("sys:project:config:update")
    public RData update(@RequestBody ProjectRightConfigEntity config) {
//        ValidatorUtils.validateEntity(config);

        projectRightConfigService.update(config);

        return RData.ok();
    }

    /**
     * 删除配置
     */
    @SysLog("删除工程权限配置")
    @PostMapping("/delete")
    @RequiresPermissions("sys:project:config:delete")
    public RData delete(@RequestBody Long id) {
        projectRightConfigService.deleteById(id);
        return RData.ok();
    }

    @GetMapping("/userList")
    public RData getProjectUserList() {
        List<SysUserEntity> list = sysUserService.list();
//        List<ProjectRightConfigEntity> projectRightConfigEntityList = projectRightConfigService.getStatusList();
        List<SysUserEntity> rList = new ArrayList<>();
        for (SysUserEntity sysUserEntity : list) {
//            if(!isContainUser(projectRightConfigEntityList,sysUserEntity)){   //未被配置过权限的用户
            rList.add(sysUserEntity);    //全部用户
//            }
        }
        return RData.ok().put("userData", rList);
    }

    @GetMapping("/queryUser")
    public RData queryProjectUser(@RequestParam Map<String, Object> params) {
        String userName = params.get("userName").toString();
        List<SysUserEntity> list = sysUserService.queryListByUserName(userName);
//        List<ProjectRightConfigEntity> projectRightConfigEntityList = projectRightConfigService.getStatusList();
        List<SysUserEntity> rList = new ArrayList<>();
        for (SysUserEntity sysUserEntity : list) {
//            if(!isContainUser(projectRightConfigEntityList,sysUserEntity)){   //未被配置过权限的用户
            rList.add(sysUserEntity);    //全部用户
//            }
        }
        return RData.ok().put("userList", rList);
    }

    private boolean isContainUser(List<ProjectRightConfigEntity> projectRightConfigEntityList, SysUserEntity sysUserEntity) {
        for (ProjectRightConfigEntity projectRightConfigEntity : projectRightConfigEntityList) {
            if (String.valueOf(projectRightConfigEntity.getUserId()).equals(String.valueOf(sysUserEntity.getUserId()))) {
                return true;
            }
        }
        return false;
    }
}
