package com.nokia.modules.resourceManage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.resourceManage.entity.StationTowerInfo;
import com.nokia.utils.PageUtils;

import java.util.Map;

public interface StationTowerInfoService extends IService<StationTowerInfo> {

    PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams);

}
