package com.nokia.modules.disaster.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.disaster.dao.AlarmInfoAmountDao;
import com.nokia.modules.disaster.entity.AlarmInfoAmount;
import com.nokia.modules.disaster.entity.AlarmMessageManagement;
import com.nokia.modules.disaster.service.AlarmInfoAmountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("alarmInfoAmountService")
public class AlarmInfoAmountServiceImpl extends ServiceImpl<AlarmInfoAmountDao, AlarmInfoAmount> implements AlarmInfoAmountService {
    @Override
    public List<AlarmInfoAmount> selectAlarmTypeAmount(AlarmMessageManagement queryParams) {
        return baseMapper.selectAlarmTypeAmountRate(queryParams);
    }
}
