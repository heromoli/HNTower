package com.nokia.modules.resourceManage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nokia.modules.resourceManage.entity.StationAddressManagement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StationAddressManagementDao extends BaseMapper<StationAddressManagement> {
    List<String> selectCounty();

    List<String> selectBizScene();
}
