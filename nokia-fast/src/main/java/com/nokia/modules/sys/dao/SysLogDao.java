package com.nokia.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nokia.modules.sys.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by wow on 2019/6/8.
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {
}
