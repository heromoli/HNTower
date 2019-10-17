package com.nokia.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.sys.entity.SysLogEntity;
import com.nokia.utils.PageUtils;

import java.util.Map;

/**
 * Created by wow on 2019/6/6.
 */
public interface SysLogService extends IService<SysLogEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
