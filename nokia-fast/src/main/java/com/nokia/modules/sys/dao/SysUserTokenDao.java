package com.nokia.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nokia.modules.sys.entity.SysUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by wow on 2019/6/6.
 */
@Mapper
public interface SysUserTokenDao extends BaseMapper<SysUserTokenEntity> {
    SysUserTokenEntity queryByToken(String token);
}
