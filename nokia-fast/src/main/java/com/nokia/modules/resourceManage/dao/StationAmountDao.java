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

    List<StationInfoAmount> selectStatusGroupCity(@Param("planStatus") String planStatus);

    List<StationInfoAmount> selectProjectTypeGroupCity(@Param("projectType") String projectType);

    List<StationInfoAmount> selectByProjectType(@Param("cityBranchCompany") String cityBranchCompany);

    List<StationInfoAmount> selectGhBuildType(@Param("cityBranchCompany") String cityBranchCompany);

    List<StationInfoAmount> selectGhStationType(@Param("cityBranchCompany") String cityBranchCompany);

    List<StationInfoAmount> selectGhOperator(@Param("cityBranchCompany") String cityBranchCompany);

    List<StationInfoAmount> selectGhNetType(@Param("cityBranchCompany") String cityBranchCompany);

    List<StationInfoAmount> selectXqBuildType(@Param("cityBranchCompany") String cityBranchCompany);

    List<StationInfoAmount> selectXqStationType(@Param("cityBranchCompany") String cityBranchCompany);

    List<StationInfoAmount> selectXqOperator(@Param("cityBranchCompany") String cityBranchCompany);

    List<StationInfoAmount> selectXqNetType(@Param("cityBranchCompany") String cityBranchCompany);

    List<StationInfoAmount> selectCityGhCllj();

    List<StationInfoAmount> selectCityGhXjzd();

    List<StationInfoAmount> selectCityGhHzwldw();

    List<StationInfoAmount> selectCityGhWzwldw();

    List<StationInfoAmount> selectCityGhSfwldw();

    List<StationInfoAmount> selectCityGhYddw();

    List<StationInfoAmount> selectCityGhLtdw();

    List<StationInfoAmount> selectCityGhDxdw();

    List<StationInfoAmount> selectCityGh4Gdw();

    List<StationInfoAmount> selectCityGh5Gdw();

    List<StationInfoAmount> selectCityXqCllj();

    List<StationInfoAmount> selectCityXqXjzd();

    List<StationInfoAmount> selectCityXqHzwldw();

    List<StationInfoAmount> selectCityXqWzwldw();

    List<StationInfoAmount> selectCityXqSfwldw();

    List<StationInfoAmount> selectCityXqYddw();

    List<StationInfoAmount> selectCityXqLtdw();

    List<StationInfoAmount> selectCityXqDxdw();

    List<StationInfoAmount> selectCityXq4Gdw();

    List<StationInfoAmount> selectCityXq5Gdw();
}
