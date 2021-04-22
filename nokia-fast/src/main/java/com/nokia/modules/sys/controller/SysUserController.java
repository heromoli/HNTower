package com.nokia.modules.sys.controller;

import com.nokia.common.annotation.SysLog;
import com.nokia.modules.sys.entity.PasswordForm;
import com.nokia.modules.sys.entity.SysUserEntity;
import com.nokia.modules.sys.service.ProjectRightConfigService;
import com.nokia.modules.sys.service.ProjectWorkflowGroupService;
import com.nokia.modules.sys.service.SysUserRoleService;
import com.nokia.modules.sys.service.SysUserService;
import com.nokia.utils.Constant;
import com.nokia.utils.PageUtils;
import com.nokia.utils.RData;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private ProjectWorkflowGroupService pwfgService;

    @Autowired
    private ProjectRightConfigService projectRightConfigService;


    /**
     * 所有用户列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:user:list")
    public RData list(@RequestParam Map<String, Object> params) {
        //只有超级管理员，才能查看所有管理员列表
        if (getUserId() != Constant.SUPER_ADMIN) {
            params.put("createUserId", getUserId());
        }
        PageUtils page = sysUserService.queryPage(params);

        return RData.ok().put("page", page);
    }

    /**
     * 获取登录的用户信息
     */
    @GetMapping("/info")
    public RData info() {
        return RData.ok().put("user", getUser());
    }

    /**
     * 修改登录用户密码
     */
    @SysLog("修改密码")
    @PostMapping("/password")
    public RData password(@RequestBody PasswordForm form) {
//		Assert.isBlank(form.getNewPassword(), "新密码不为能空");

        //sha256加密
        String password = new Sha256Hash(form.getPassword(), getUser().getSalt()).toHex();
        //sha256加密
        String newPassword = new Sha256Hash(form.getNewPassword(), getUser().getSalt()).toHex();

        //更新密码
        boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
        if (!flag) {
            return RData.error("原密码不正确");
        }

        return RData.ok();
    }

    /**
     * 用户信息
     */
    @GetMapping("/info/{userId}")
    @RequiresPermissions("sys:user:info")
    public RData info(@PathVariable("userId") Long userId) {
        SysUserEntity user = sysUserService.getById(userId);

        //获取用户所属的角色列表
        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);

        List<String> groupIdList = pwfgService.getStringListByUserId(userId);
        List<Long> longList = new ArrayList<>();
        for(String str : groupIdList) {
            long l = Long.parseLong(str);
            longList.add(l);
        }

        user.setGroupIdList(longList);

        return RData.ok().put("user", user);
    }

    /**
     * 保存用户
     */
    @SysLog("保存用户")
    @PostMapping("/save")
    @RequiresPermissions("sys:user:save")
    public RData save(@RequestBody SysUserEntity user) {
//		ValidatorUtils.validateEntity(user, AddGroup.class);

        user.setCreateUserId(getUserId());
        sysUserService.saveUser(user);

        return RData.ok();
    }

    /**
     * 修改用户
     */
    @SysLog("修改用户")
    @PostMapping("/update")
    @RequiresPermissions("sys:user:update")
    public RData update(@RequestBody SysUserEntity user) {
//		ValidatorUtils.validateEntity(user, UpdateGroup.class);

        user.setCreateUserId(getUserId());
        sysUserService.update(user);

        return RData.ok();
    }

    /**
     * 删除用户
     */
    @SysLog("删除用户")
    @PostMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public RData delete(@RequestBody Long[] userIds) {
        if (ArrayUtils.contains(userIds, 1L)) {
            return RData.error("系统管理员不能删除");
        }
        if (ArrayUtils.contains(userIds, getUserId())) {
            return RData.error("当前用户不能删除");
        }
        sysUserService.deleteBatch(userIds);
        for (long userId:userIds){
            projectRightConfigService.deleteByUserId((int)userId);
        }

        return RData.ok();
    }
}
