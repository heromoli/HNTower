package com.nokia.modules.resourceManage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.resourceManage.dao.StationAmountDao;
import com.nokia.modules.resourceManage.entity.StationInfoAmount;
import com.nokia.modules.resourceManage.entity.VennEntity;
import com.nokia.modules.resourceManage.service.StationAmountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Service("StationAmountService")
public class StationAmountServiceImpl extends ServiceImpl<StationAmountDao, StationInfoAmount> implements StationAmountService {

    private static final Logger logger = LoggerFactory.getLogger(StationAmountServiceImpl.class);

    @Override
    public List<StationInfoAmount> selectStationAmountData() {
        List<StationInfoAmount> stationInfoAmountList = new ArrayList<>();
        stationInfoAmountList.addAll(baseMapper.selectHaikouAmount());
        stationInfoAmountList.addAll(baseMapper.selectSanyaAmount());
        stationInfoAmountList.addAll(baseMapper.selectHainanAmount());
        stationInfoAmountList.addAll(baseMapper.selectQundaoAmount());
        return stationInfoAmountList;
    }

    @Override
    public List<StationInfoAmount> selectCountyAmountData() {
        return baseMapper.selectCountyAmount();
    }

    @Override
    public List<StationInfoAmount> selectChanquanAmount() {
        return baseMapper.selectChanquanAmount();
    }

    @Override
    public List<StationInfoAmount> selectCityStationAmount() {
        return baseMapper.selectCityStationAmount();
    }

    @Override
    public int[] selectStationShareAmount() {
        List<StationInfoAmount> stationShareAmountList = baseMapper.selectStationShareAmount();

        int[] stationShareAmount = new int[7];
        for (StationInfoAmount stationInfoAmount : stationShareAmountList) {
            if (stationInfoAmount.getName().equals("移动")) {
                stationShareAmount[0] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("联通")) {
                stationShareAmount[1] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("电信")) {
                stationShareAmount[2] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("移动，联通")) {
                stationShareAmount[3] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("移动，电信")) {
                stationShareAmount[4] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("联通，电信")) {
                stationShareAmount[5] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("移动，联通，电信")) {
                stationShareAmount[6] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("无")) {
//                stationShareAmount[7] = stationInfoAmount.getValue();
            }
        }


        return stationShareAmount;
    }

    @Override
    public VennEntity[] selectStationShareAmountVenn() {
        List<StationInfoAmount> stationShareAmountList = baseMapper.selectStationShareAmount();

        VennEntity[] vennList = new VennEntity[8];

        for (int i = 0; i < stationShareAmountList.size(); i++) {
            VennEntity vennEntity = new VennEntity();
            vennEntity.setName(stationShareAmountList.get(i).getName());
            vennEntity.setValue(stationShareAmountList.get(i).getValue());
            vennEntity.setSets(stationShareAmountList.get(i).getName().split("，"));
            vennList[i] = vennEntity;
        }
        return vennList;
    }

    @Override
    public int[] selectBizSceneAmount() {
        int[] bizSceneAmount = new int[16];
        List<StationInfoAmount> stationInfoAmountList = baseMapper.selectBizSceneAmount();
        for (StationInfoAmount stationInfoAmount : stationInfoAmountList) {
            if (stationInfoAmount.getName().equals("一般市区")) {
                bizSceneAmount[0] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("密集市区")) {
                bizSceneAmount[1] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("县城")) {
                bizSceneAmount[2] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("乡镇")) {
                bizSceneAmount[3] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("农村")) {
                bizSceneAmount[4] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("住宅小区")) {
                bizSceneAmount[5] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("3A以上景区")) {
                bizSceneAmount[6] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("其他景区")) {
                bizSceneAmount[7] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("校园")) {
                bizSceneAmount[8] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("工业园")) {
                bizSceneAmount[9] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("室分")) {
                bizSceneAmount[10] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("商业市场")) {
                bizSceneAmount[11] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("高速")) {
                bizSceneAmount[12] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("高铁线")) {
                bizSceneAmount[13] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("国道")) {
                bizSceneAmount[14] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("其他交通干线")) {
                bizSceneAmount[15] = stationInfoAmount.getValue();
            }
        }
        return bizSceneAmount;
    }

    @Override
    public List<StationInfoAmount> selectStationAloneAmount() {
        return baseMapper.selectStationAloneAmount();
    }

    @Override
    public List<StationInfoAmount> selectStationAmountByYear() {
        List<StationInfoAmount> stationInfoAmountList = baseMapper.selectStationAmountByYear();
        return stationInfoAmountList;
    }

    @Override
    public List<StationInfoAmount> selectStationIncreaseByYear() {
        List<StationInfoAmount> stationInfoAmountList = baseMapper.selectStationIncreaseByYear();
        return stationInfoAmountList;
    }

    @Override
    public List<StationInfoAmount> selectStationAmountByMonth() {
        return baseMapper.selectStationAmountByMonth();
    }

    @Override
    public List<StationInfoAmount> selectStationSourceAmount() {
        return baseMapper.selectStationSourceAmount();
    }

    @Override
    public List<StationInfoAmount> calculateShareRate() {
        List<StationInfoAmount> yidongList = baseMapper.calculateShareRate("移动");

        return yidongList;
    }

    @Override
    public List<StationInfoAmount> selectOperatorStationAmount() {
        return baseMapper.selectOperatorStationAmount();
    }

    @Override
    public List<StationInfoAmount> selectCountyStation5GAmount() {
        return baseMapper.selectCountyStation5GAmount();
    }

    @Override
    public List<StationInfoAmount> selectStation5gAmountByMonth() {
        return baseMapper.selectMonthlyStation5GAmount();
    }

    @Override
    public List<StationInfoAmount> selectProvinceTuisong() {
        return baseMapper.selectProvinceTuisong();
    }

    @Override
    public List<StationInfoAmount> selectStatusGroupCity(String planStatus) {
        return baseMapper.selectStatusGroupCity(planStatus);
    }

    @Override
    public List<StationInfoAmount> selectByProjectType(String cityBranchCompany) {
        return baseMapper.selectByProjectType(cityBranchCompany);
    }

    @Override
    public List<StationInfoAmount> selectProjectTypeGroupCity(String projectType) {
        return baseMapper.selectProjectTypeGroupCity(projectType);
    }

    @Override
    public List<StationInfoAmount> selectGhBuildType(String cityBranchCompany) {
        return baseMapper.selectGhBuildType(cityBranchCompany);
    }

    @Override
    public List<StationInfoAmount> selectGhStationType(String cityBranchCompany) {
        return baseMapper.selectGhStationType(cityBranchCompany);
    }

    @Override
    public List<StationInfoAmount> selectGhOperator(String cityBranchCompany) {
        return baseMapper.selectGhOperator(cityBranchCompany);
    }

    @Override
    public List<StationInfoAmount> selectGhNetType(String cityBranchCompany) {
        return baseMapper.selectGhNetType(cityBranchCompany);
    }

    @Override
    public List<StationInfoAmount> selectXqBuildType(String cityBranchCompany) {
        return baseMapper.selectXqBuildType(cityBranchCompany);
    }

    @Override
    public List<StationInfoAmount> selectXqStationType(String cityBranchCompany) {
        return baseMapper.selectXqStationType(cityBranchCompany);
    }

    @Override
    public List<StationInfoAmount> selectXqOperator(String cityBranchCompany) {
        return baseMapper.selectXqOperator(cityBranchCompany);
    }

    @Override
    public List<StationInfoAmount> selectXqNetType(String cityBranchCompany) {
        return baseMapper.selectXqNetType(cityBranchCompany);
    }

    @Override
    public List<StationInfoAmount> selectCityGhCllj() {
        return baseMapper.selectCityGhCllj();
    }

    @Override
    public List<StationInfoAmount> selectCityGhXjzd() {
        return baseMapper.selectCityGhXjzd();
    }

    @Override
    public List<StationInfoAmount> selectCityGhHzwldw() {
        return baseMapper.selectCityGhHzwldw();
    }

    @Override
    public List<StationInfoAmount> selectCityGhWzwldw() {
        return baseMapper.selectCityGhWzwldw();
    }

    @Override
    public List<StationInfoAmount> selectCityGhSfwldw() {
        return baseMapper.selectCityGhSfwldw();
    }

    @Override
    public List<StationInfoAmount> selectCityGhYddw() {
        return baseMapper.selectCityGhYddw();
    }

    @Override
    public List<StationInfoAmount> selectCityGhLtdw() {
        return baseMapper.selectCityGhLtdw();
    }

    @Override
    public List<StationInfoAmount> selectCityGhDxdw() {
        return baseMapper.selectCityGhDxdw();
    }

    @Override
    public List<StationInfoAmount> selectCityGh4Gdw() {
        return baseMapper.selectCityGh4Gdw();
    }

    @Override
    public List<StationInfoAmount> selectCityGh5Gdw() {
        return baseMapper.selectCityGh5Gdw();
    }

    @Override
    public List<StationInfoAmount> selectCityXqCllj() {
        return baseMapper.selectCityXqCllj();
    }

    @Override
    public List<StationInfoAmount> selectCityXqXjzd() {
        return baseMapper.selectCityXqXjzd();
    }

    @Override
    public List<StationInfoAmount> selectCityXqHzwldw() {
        return baseMapper.selectCityXqHzwldw();
    }

    @Override
    public List<StationInfoAmount> selectCityXqWzwldw() {
        return baseMapper.selectCityXqWzwldw();
    }

    @Override
    public List<StationInfoAmount> selectCityXqSfwldw() {
        return baseMapper.selectCityXqSfwldw();
    }

    @Override
    public List<StationInfoAmount> selectCityXqYddw() {
        return baseMapper.selectCityXqYddw();
    }

    @Override
    public List<StationInfoAmount> selectCityXqLtdw() {
        return baseMapper.selectCityXqLtdw();
    }

    @Override
    public List<StationInfoAmount> selectCityXqDxdw() {
        return baseMapper.selectCityXqDxdw();
    }

    @Override
    public List<StationInfoAmount> selectCityXq4Gdw() {
        return baseMapper.selectCityXq4Gdw();
    }

    @Override
    public List<StationInfoAmount> selectCityXq5Gdw() {
        return baseMapper.selectCityXq5Gdw();
    }

}
