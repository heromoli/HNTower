package com.nokia.modules.disaster.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nokia.modules.disaster.entity.AlarmInfoAmount;
import com.nokia.modules.disaster.entity.AlarmMessageManagement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AlarmMessageManagementDao extends BaseMapper<AlarmMessageManagement> {

    List<AlarmInfoAmount> selectAlarmTypeAmount();

}
