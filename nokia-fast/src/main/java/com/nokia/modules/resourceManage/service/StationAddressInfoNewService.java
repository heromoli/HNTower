package com.nokia.modules.resourceManage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.resourceManage.entity.StationAddressInfoNew;
import com.nokia.utils.PageUtils;

import java.util.Map;

public interface StationAddressInfoNewService extends IService<StationAddressInfoNew> {

    PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams);

}
