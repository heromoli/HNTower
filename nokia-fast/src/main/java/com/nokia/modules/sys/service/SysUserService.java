package com.nokia.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.sys.entity.SysUserEntity;
import com.nokia.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by wow on 2019/6/8.
 */
public interface SysUserService extends IService<SysUserEntity> {
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据用户名，查询系统用户
     */
    SysUserEntity queryByUserName(String username);

    /**
     * 根据用户ID，查询系统用户
     */
    SysUserEntity queryByUserId(Long userid);

    /**
     * 根据用户名，模糊查询系统用户
     */
    List<SysUserEntity> queryListByUserName(String username);

    /**
     * 保存用户
     */
    void saveUser(SysUserEntity user);

    /**
     * 修改用户
     */
    void update(SysUserEntity user);

    /**
     * 删除用户
     */
    void deleteBatch(Long[] userIds);

    /**
     * 修改密码
     * @param userId       用户ID
     * @param password     原密码
     * @param newPassword  新密码
     */
    boolean updatePassword(Long userId, String password, String newPassword);
}
