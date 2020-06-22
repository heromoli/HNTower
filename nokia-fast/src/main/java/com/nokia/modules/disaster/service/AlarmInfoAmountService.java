package com.nokia.modules.disaster.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.disaster.entity.AlarmInfoAmount;
import com.nokia.modules.disaster.entity.AlarmMessageManagement;
import com.nokia.modules.disaster.entity.DisasterSufferMessage;
import com.nokia.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface AlarmInfoAmountService extends IService<AlarmInfoAmount> {

    List<AlarmInfoAmount> selectAlarmTypeAmount(AlarmMessageManagement queryParams);

}
