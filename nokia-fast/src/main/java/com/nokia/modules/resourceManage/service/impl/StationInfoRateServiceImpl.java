package com.nokia.modules.resourceManage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.resourceManage.dao.StationAmountDao;
import com.nokia.modules.resourceManage.dao.StationInfoRateDao;
import com.nokia.modules.resourceManage.entity.StationInfoAmount;
import com.nokia.modules.resourceManage.entity.StationInfoRate;
import com.nokia.modules.resourceManage.service.StationAmountService;
import com.nokia.modules.resourceManage.service.StationInfoRateService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("StationInfoRateService")
public class StationInfoRateServiceImpl extends ServiceImpl<StationInfoRateDao, StationInfoRate> implements StationInfoRateService {

    @Override
    public double[] calculateShareRate() {
        double[] stationRate = new double[4];
        List<StationInfoRate> rateList = baseMapper.calculateShareRate();

        for (StationInfoRate stationInfoRate : rateList) {
            if (stationInfoRate.getName().equals("新建")) {
                stationRate[0] = stationInfoRate.getValue();
            } else if (stationInfoRate.getName().equals("联通")) {
                stationRate[1] = stationInfoRate.getValue();
            } else if (stationInfoRate.getName().equals("电信")) {
                stationRate[2] = stationInfoRate.getValue();
            } else if (stationInfoRate.getName().equals("移动")) {
                stationRate[3] = stationInfoRate.getValue();
            }
        }
        return stationRate;
    }

}
