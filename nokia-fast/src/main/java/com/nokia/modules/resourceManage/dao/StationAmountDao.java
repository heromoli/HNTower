package com.nokia.modules.resourceManage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nokia.modules.resourceManage.entity.StationInfoAmount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StationAmountDao extends BaseMapper<StationInfoAmount> {
    List<StationInfoAmount> selectHaikouAmount();

    List<StationInfoAmount> selectSanyaAmount();

    List<StationInfoAmount> selectQundaoAmount();

    List<StationInfoAmount> selectHainanAmount();

    List<StationInfoAmount> selectCountyAmount();

    List<StationInfoAmount> selectChanquanAmount();

    List<StationInfoAmount> selectCityStationAmount();

    List<StationInfoAmount> selectStationShareAmount();

    List<StationInfoAmount> selectBizSceneAmount();

    List<StationInfoAmount> selectStationAmountByYear();

    List<StationInfoAmount> selectStationAmountByMonth();

    List<StationInfoAmount> selectStationSourceAmount();

    List<StationInfoAmount> calculateShareRate(@Param("name") String name);

    List<StationInfoAmount> selectOperatorStationAmount();

    List<StationInfoAmount> selectCountyStation5GAmount();

    List<StationInfoAmount> selectMonthlyStation5GAmount();

}
