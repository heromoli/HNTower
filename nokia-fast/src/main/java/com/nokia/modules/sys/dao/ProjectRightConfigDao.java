package com.nokia.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nokia.modules.sys.entity.ProjectRightConfigEntity;
import com.nokia.modules.sys.entity.SysConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wow on 2019/6/8.
 */
@Mapper
public interface ProjectRightConfigDao extends BaseMapper<ProjectRightConfigEntity> {

    List<ProjectRightConfigEntity> queryListUserId(Long parentId);
}
