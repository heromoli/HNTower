package com.nokia.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nokia.modules.sys.entity.ProjectWorkflowGroupEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by wow on 2019/7/8.
 */
@Mapper
public interface ProjectWorkflowGroupDao extends BaseMapper<ProjectWorkflowGroupEntity> {

    List<ProjectWorkflowGroupEntity> queryListUserId(Long parentId);
}
