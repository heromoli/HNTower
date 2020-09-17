package com.nokia.modules.resourceManage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.resourceManage.entity.BaseStationInfo;
import com.nokia.utils.PageUtils;


import java.util.Map;

public interface BaseStationInfoService extends IService<BaseStationInfo> {

    PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams);

}
