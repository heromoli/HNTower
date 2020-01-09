package com.nokia.modules.resourceManage.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.resourceManage.controller.zhzyglController;
import com.nokia.modules.resourceManage.dao.TowerSolutionStatisticsDao;
import com.nokia.modules.resourceManage.entity.TowerSolutionStatistics;
import com.nokia.modules.resourceManage.service.TowerSolutionStatisticService;
import com.nokia.utils.PageUtils;
import com.nokia.utils.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("TowerSolutionStatisticService")
public class TowerSolutionStatisticServiceImpl extends ServiceImpl<TowerSolutionStatisticsDao, TowerSolutionStatistics> implements TowerSolutionStatisticService {

    private static final Logger logger = LoggerFactory.getLogger(TowerSolutionStatisticServiceImpl.class);

    @Override
    public List<TowerSolutionStatistics> selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams) {

//        QueryWrapper queryWrapper = new QueryWrapper<TowerSolutionStatistics>();
//        for (String key : queryParams.keySet()) {
//            queryWrapper.like(key, queryParams.get(key));
//        }
//        queryWrapper.orderByAsc("smonth","branch");
//        IPage<TowerSolutionStatistics> page = this.page(new Query<TowerSolutionStatistics>().getPage(pageParams), queryWrapper);
//        return new PageUtils(page);

        String smonth = queryParams.get("smonth").toString();
        if (smonth.length() > 6) {
            smonth = smonth.substring(0, 4) + smonth.substring(5, 7);
        }
        logger.info(smonth);

        return baseMapper.selectStatistic(smonth);
    }
}
