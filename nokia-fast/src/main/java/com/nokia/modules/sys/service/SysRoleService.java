package com.nokia.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.sys.entity.SysRoleEntity;
import com.nokia.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by wow on 2019/6/8.
 */
public interface SysRoleService extends IService<SysRoleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveRole(SysRoleEntity role);

    void update(SysRoleEntity role);

    void deleteBatch(Long[] roleIds);


    /**
     * 查询用户创建的角色ID列表
     */
    List<Long> queryRoleIdList(Long createUserId);
}
