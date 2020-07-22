package com.nokia.modules.resourceManage.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.resourceManage.dao.TowerSolutionSubmittedDao;
import com.nokia.modules.resourceManage.entity.TowerSolutionSubmitted;
import com.nokia.modules.resourceManage.entity.TowerSolutionStatistics;
import com.nokia.modules.resourceManage.service.TowerSolutionSubmittedService;

import com.nokia.utils.PageUtils;
import com.nokia.utils.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("SubmittedDemandService")
public class TowerSolutionSubmittedServiceImpl extends ServiceImpl<TowerSolutionSubmittedDao, TowerSolutionSubmitted> implements TowerSolutionSubmittedService {

    private static final Logger logger = LoggerFactory.getLogger(TowerSolutionSubmittedServiceImpl.class);

    @Override
    public PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams) {

        QueryWrapper queryWrapper = new QueryWrapper<TowerSolutionSubmitted>();
//        for (String key : queryParams.keySet()) {
//            queryWrapper.like(key, queryParams.get(key));
//        }
        queryWrapper.orderByAsc("smonth","branch");
        IPage<TowerSolutionStatistics> page = this.page(new Query<TowerSolutionSubmitted>().getPage(pageParams), queryWrapper);
        return new PageUtils(page);
    }

    @Override
    public boolean deleteAll() {
        QueryWrapper deleteWrapper = new QueryWrapper<TowerSolutionSubmitted>();
        return this.remove(deleteWrapper);
    }
}
