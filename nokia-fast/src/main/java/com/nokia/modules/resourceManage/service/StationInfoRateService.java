package com.nokia.modules.resourceManage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.resourceManage.entity.StationInfoAmount;
import com.nokia.modules.resourceManage.entity.StationInfoRate;

import java.util.List;

public interface StationInfoRateService extends IService<StationInfoRate> {

    double[] calculateShareRate();

}
