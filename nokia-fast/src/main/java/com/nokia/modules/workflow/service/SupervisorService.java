package com.nokia.modules.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.sys.entity.ProjectRightConfigEntity;
import com.nokia.modules.workflow.entity.Supervisor;
import com.nokia.utils.PageUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SupervisorService extends IService<Supervisor> {

    public Supervisor selectDataByInsId(String processInstanceId);

    public List<Supervisor> selectDataByInsIdSet(Set<String> processInstanceId);

    public PageUtils selectDataByParam(Map<String, Object> params);

    public PageUtils findData(List<ProjectRightConfigEntity> prcList, Set<String> processInstanceId, Map<String, Object> params);

    public Integer findDataCount(List<ProjectRightConfigEntity> prcList, Set<String> processInstanceId, Map<String, Object> params);

    public List<Supervisor> findDataAll(List<ProjectRightConfigEntity> prcList, Set<String> processInstanceId, Map<String, Object> params);

    public String queryYDSequence();

    public String queryLTSequence();

    public String queryDXSequence();

    String countCityNum(String branchCompany);
}
