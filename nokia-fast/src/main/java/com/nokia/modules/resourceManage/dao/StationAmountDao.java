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

    List<StationInfoAmount> selectStationAloneAmount();

    List<StationInfoAmount> selectBizSceneAmount();

    List<StationInfoAmount> selectStationAmountByYear();

    List<StationInfoAmount> selectStationIncreaseByYear();

    List<StationInfoAmount> selectStationAmountByMonth();

    List<StationInfoAmount> selectStationSourceAmount();

    List<StationInfoAmount> calculateShareRate(@Param("name") String name);

    List<StationInfoAmount> selectOperatorStationAmount();

    List<StationInfoAmount> selectCountyStation5GAmount();

    List<StationInfoAmount> selectMonthlyStation5GAmount();

    List<StationInfoAmount> selectProvinceTuisong();

    List<StationInfoAmount> selectHaikouTuisong();

    List<StationInfoAmount> selectSanyaTuisong();

    List<StationInfoAmount> selectQionghaiTuisong();

    List<StationInfoAmount> selectDanzhouTuisong();

    List<StationInfoAmount> selectByProjectType(@Param("cityBranchCompany") String cityBranchCompany);

    List<StationInfoAmount> selectGhBuildType(@Param("cityBranchCompany") String cityBranchCompany);

    List<StationInfoAmount> selectGhStationType(@Param("cityBranchCompany") String cityBranchCompany);

    List<StationInfoAmount> selectGhOperator(@Param("cityBranchCompany") String cityBranchCompany);

    List<StationInfoAmount> selectGhNetType(@Param("cityBranchCompany") String cityBranchCompany);

    List<StationInfoAmount> selectXqBuildType(@Param("cityBranchCompany") String cityBranchCompany);

    List<StationInfoAmount> selectXqStationType(@Param("cityBranchCompany") String cityBranchCompany);

    List<StationInfoAmount> selectXqOperator(@Param("cityBranchCompany") String cityBranchCompany);

    List<StationInfoAmount> selectXqNetType(@Param("cityBranchCompany") String cityBranchCompany);



}
