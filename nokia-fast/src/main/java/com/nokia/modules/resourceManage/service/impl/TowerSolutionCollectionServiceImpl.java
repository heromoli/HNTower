package com.nokia.modules.resourceManage.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.resourceManage.dao.TowerSolutionCollectionDao;
import com.nokia.modules.resourceManage.entity.TowerSolutionCollection;
import com.nokia.modules.workflow.entity.PlanningTask;
import com.nokia.modules.resourceManage.service.TowerSolutionCollectionService;
import com.nokia.utils.PageUtils;
import com.nokia.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("TowerSolutionCollectionService")
public class TowerSolutionCollectionServiceImpl extends ServiceImpl<TowerSolutionCollectionDao, TowerSolutionCollection> implements TowerSolutionCollectionService {

    @Override
    public List<String> selectProjectName() {
        return baseMapper.selectProjectName();
    }

    @Override
    public PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams) {
        QueryWrapper queryWrapper = new QueryWrapper<TowerSolutionCollection>();
        for (String key : queryParams.keySet()) {
            queryWrapper.like(key, queryParams.get(key));
        }
        IPage<TowerSolutionCollection> page = this.page(new Query<TowerSolutionCollection>().getPage(pageParams), queryWrapper);
        return new PageUtils(page);
    }
}
