package com.nokia.modules.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.workflow.entity.StationAddressInfo;
import com.nokia.utils.PageUtils;

import java.util.Map;

public interface StationAddressInfoService extends IService<StationAddressInfo> {

    public PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams);

}
