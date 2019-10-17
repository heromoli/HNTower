package com.nokia.modules.resourceManage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nokia.modules.resourceManage.entity.StationInfoAmount;
import com.nokia.modules.resourceManage.entity.StationInfoRate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StationInfoRateDao extends BaseMapper<StationInfoRate> {

    List<StationInfoRate> calculateShareRate();

}
