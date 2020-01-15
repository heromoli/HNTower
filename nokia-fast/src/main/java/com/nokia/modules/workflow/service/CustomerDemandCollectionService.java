package com.nokia.modules.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.sys.entity.ProjectRightConfigEntity;
import com.nokia.modules.workflow.entity.CustomerDemandCollection;
import com.nokia.utils.PageUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CustomerDemandCollectionService extends IService<CustomerDemandCollection> {
    PageUtils findData(List<ProjectRightConfigEntity> prcList, Set<String> processInstanceId, Map<String, Object> params);

    Integer findDataCount(List<ProjectRightConfigEntity> prcList, Set<String> processInstanceId, Map<String, Object> params);

    List<CustomerDemandCollection> findDataAll(List<ProjectRightConfigEntity> prcList, Set<String> processInstanceId, Map<String, Object> params);

}
