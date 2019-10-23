package com.nokia.modules.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.workflow.entity.StationAddressManagement;
import com.nokia.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface StationAddressManagementService extends IService<StationAddressManagement> {

    PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams);

    List<String> getStationCounty();

    PageUtils queryPage(Map<String, Object> params);

}
