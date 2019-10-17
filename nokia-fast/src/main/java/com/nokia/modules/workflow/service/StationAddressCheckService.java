package com.nokia.modules.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.sys.entity.ProjectRightConfigEntity;
import com.nokia.modules.workflow.entity.StationAddressCheck;
import com.nokia.utils.PageUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface StationAddressCheckService extends IService<StationAddressCheck> {

    public PageUtils findData(List<ProjectRightConfigEntity> prcList, Set<String> processInstanceId, Map<String, Object> params);

    public Integer findDataCount(List<ProjectRightConfigEntity> prcList, Set<String> processInstanceId, Map<String, Object> params);

    public List<StationAddressCheck> findDataAll(List<ProjectRightConfigEntity> prcList, Set<String> processInstanceId, Map<String, Object> params);
}
