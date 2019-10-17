package com.nokia.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.sys.entity.SysUserTokenEntity;
import com.nokia.utils.RData;

/**
 * Created by wow on 2019/6/8.
 */
public interface SysUserTokenService extends IService<SysUserTokenEntity> {
    /**
     * 生成token
     * @param userId  用户ID
     */
    RData createToken(long userId);

    /**
     * 退出，修改token值
     * @param userId  用户ID
     */
    void logout(long userId);
}
