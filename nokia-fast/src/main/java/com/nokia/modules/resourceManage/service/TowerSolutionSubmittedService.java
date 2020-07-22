package com.nokia.modules.resourceManage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.resourceManage.entity.TowerSolutionSubmitted;
import com.nokia.utils.PageUtils;

import java.util.Map;

public interface TowerSolutionSubmittedService extends IService<TowerSolutionSubmitted> {

    PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams);

    boolean deleteAll();

}
