package com.nokia.modules.resourceManage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nokia.modules.resourceManage.entity.TowerSolutionCollection;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TowerSolutionCollectionDao extends BaseMapper<TowerSolutionCollection> {

    List<String> selectProjectName();
}
