package com.nokia.modules.resourceManage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nokia.modules.resourceManage.entity.TowerSolutionStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TowerSolutionStatisticsDao extends BaseMapper<TowerSolutionStatistics> {

    List<TowerSolutionStatistics> selectStatistic(String smonth);

}
