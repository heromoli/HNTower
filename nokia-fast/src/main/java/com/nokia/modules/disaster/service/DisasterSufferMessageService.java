package com.nokia.modules.disaster.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.disaster.entity.DisasterSufferMessage;
import com.nokia.utils.PageUtils;

import java.util.Map;

public interface DisasterSufferMessageService extends IService<DisasterSufferMessage> {

    PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams);
}
