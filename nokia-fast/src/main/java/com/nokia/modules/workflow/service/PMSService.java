package com.nokia.modules.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.workflow.entity.PMS;
import com.nokia.utils.PageUtils;

import java.util.Map;

public interface PMSService extends IService<PMS> {

    public PageUtils selectDataByParam(Map<String, Object> pageParams,Map<String, Object> queryParams);

}
