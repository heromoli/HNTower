package com.nokia.modules.workflow.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nokia.modules.workflow.entity.Supervisor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SupervisorDao extends BaseMapper<Supervisor> {

    String queryYDSequence();

    String queryLTSequence();

    String queryDXSequence();

    String countCityNum(String branchCompany);
}
