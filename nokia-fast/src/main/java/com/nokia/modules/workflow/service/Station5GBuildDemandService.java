package com.nokia.modules.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.workflow.entity.Station5GBuildDemand;
import com.nokia.utils.PageUtils;

import java.util.Map;

public interface Station5GBuildDemandService extends IService<Station5GBuildDemand> {

    PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams);

}
