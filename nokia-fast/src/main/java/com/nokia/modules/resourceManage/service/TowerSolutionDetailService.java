package com.nokia.modules.resourceManage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.resourceManage.entity.TowerSolutionDetail;
import com.nokia.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface TowerSolutionDetailService extends IService<TowerSolutionDetail> {

    PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams);

    List<TowerSolutionDetail> selectDataByParam(Map<String, Object> queryParams);

    boolean deleteAll();

}
