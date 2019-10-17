package com.nokia.modules.workflow.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nokia.modules.workflow.entity.StationAddressInfo;
import com.nokia.modules.workflow.entity.StationAddressManagement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StationAddressManagementDao extends BaseMapper<StationAddressManagement> {
    List<String> selectCounty();

}
