package com.nokia.modules.resourceManage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.resourceManage.entity.TowerSolutionCollection;
import com.nokia.modules.workflow.entity.PlanningTask;
import com.nokia.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface TowerSolutionCollectionService extends IService<TowerSolutionCollection> {

    List<String> selectProjectName();

    PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams);

    boolean deleteAll();

}
