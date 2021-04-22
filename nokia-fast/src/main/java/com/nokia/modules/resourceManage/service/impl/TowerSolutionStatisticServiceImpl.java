package com.nokia.modules.resourceManage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.resourceManage.dao.TowerSolutionStatisticsDao;
import com.nokia.modules.resourceManage.entity.TowerSolutionStatistics;
import com.nokia.modules.resourceManage.service.TowerSolutionStatisticService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("TowerSolutionStatisticService")
public class TowerSolutionStatisticServiceImpl extends ServiceImpl<TowerSolutionStatisticsDao, TowerSolutionStatistics> implements TowerSolutionStatisticService {

    @Override
    public List<TowerSolutionStatistics> selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams) {

        String smonth = queryParams.get("smonth").toString();
        return baseMapper.selectStatistic(smonth);
    }
}
