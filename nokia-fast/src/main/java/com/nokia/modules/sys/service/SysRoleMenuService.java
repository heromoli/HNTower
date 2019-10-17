package com.nokia.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.sys.entity.SysRoleMenuEntity;

import java.util.List;

/**
 * Created by wow on 2019/6/8.
 */
public interface SysRoleMenuService  extends IService<SysRoleMenuEntity> {
    void saveOrUpdate(Long roleId, List<Long> menuIdList);

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId);

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);
}
