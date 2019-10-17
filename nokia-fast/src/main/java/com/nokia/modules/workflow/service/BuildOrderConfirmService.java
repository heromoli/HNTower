package com.nokia.modules.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.sys.entity.ProjectRightConfigEntity;
import com.nokia.modules.workflow.entity.BuildOrderConfirm;
import com.nokia.modules.workflow.entity.Supervisor;
import com.nokia.utils.PageUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BuildOrderConfirmService extends IService<BuildOrderConfirm> {

    public Supervisor selectDataByInsId(String actProcInstId);

    public PageUtils findData(List<ProjectRightConfigEntity> prcList, Set<String> processInstanceId, Map<String, Object> params);

    public Integer findDataCount(List<ProjectRightConfigEntity> prcList, Set<String> processInstanceId, Map<String, Object> params);

    public List<BuildOrderConfirm> findDataAll(List<ProjectRightConfigEntity> prcList, Set<String> processInstanceId, Map<String, Object> params);
}
