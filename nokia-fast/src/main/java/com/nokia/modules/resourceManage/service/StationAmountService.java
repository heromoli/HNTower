package com.nokia.modules.resourceManage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.resourceManage.entity.StationInfoAmount;
import com.nokia.modules.resourceManage.entity.VennEntity;

import java.util.List;

public interface StationAmountService extends IService<StationInfoAmount> {

    List<StationInfoAmount> selectStationAmountData();

    List<StationInfoAmount> selectCountyAmountData();

    List<StationInfoAmount> selectChanquanAmount();

    List<StationInfoAmount> selectCityStationAmount();

    int[] selectStationShareAmount();

    VennEntity[] selectStationShareAmountVenn();

    int[] selectBizSceneAmount();

    List<StationInfoAmount> selectStationAloneAmount();

    List<StationInfoAmount> selectStationAmountByYear();

    List<StationInfoAmount> selectStationIncreaseByYear();

    List<StationInfoAmount> selectStationAmountByMonth();

    List<StationInfoAmount> selectStationSourceAmount();

    List<StationInfoAmount> calculateShareRate();

    List<StationInfoAmount> selectOperatorStationAmount();

    List<StationInfoAmount> selectCountyStation5GAmount();

    List<StationInfoAmount> selectStation5gAmountByMonth();

    List<StationInfoAmount> selectProvinceTuisong();

    List<StationInfoAmount> selectByProjectType(String cityBranchCompany);

    List<StationInfoAmount> selectGhBuildType(String cityBranchCompany);

    List<StationInfoAmount> selectGhStationType(String cityBranchCompany);

    List<StationInfoAmount> selectGhOperator(String cityBranchCompany);

    List<StationInfoAmount> selectGhNetType(String cityBranchCompany);

    List<StationInfoAmount> selectXqBuildType(String cityBranchCompany);

    List<StationInfoAmount> selectXqStationType(String cityBranchCompany);

    List<StationInfoAmount> selectXqOperator(String cityBranchCompany);

    List<StationInfoAmount> selectXqNetType(String cityBranchCompany);

}
