package com.nokia.modules.resourceManage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nokia.modules.resourceManage.entity.QueryStationAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QueryStationAddressDao extends BaseMapper<QueryStationAddress> {
    List<String> selectCounty();

    List<String> selectBizScene();
}
