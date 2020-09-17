package com.nokia.modules.resourceManage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.resourceManage.entity.QueryStationAddress;

import java.util.List;
import java.util.Map;

public interface QueryStationAddressService extends IService<QueryStationAddress> {

    List<QueryStationAddress> selectDataByParam(Map<String, Object> queryParams);
}
