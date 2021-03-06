package com.nokia.modules.resourceManage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.resourceManage.entity.StationAddressInfo;
import com.nokia.utils.PageUtils;

import java.util.Map;

public interface StationAddressInfoService extends IService<StationAddressInfo> {

    PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams);

}
