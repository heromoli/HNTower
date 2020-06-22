package com.nokia.modules.disaster.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.disaster.entity.AlarmMessageManagement;
import com.nokia.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface AlarmMessageManagementService extends IService<AlarmMessageManagement> {

    List<AlarmMessageManagement> selectDataByParam(Map<String, Object> queryParams);

    PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams);
}
