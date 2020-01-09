package com.nokia.modules.resourceManage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.resourceManage.entity.TowerSolutionStatistics;

import java.util.List;
import java.util.Map;

public interface TowerSolutionStatisticService extends IService<TowerSolutionStatistics> {

    List<TowerSolutionStatistics> selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams);

}
