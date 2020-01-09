package com.nokia.modules.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.sys.entity.ProjectRightConfigEntity;
import com.nokia.modules.workflow.entity.Supervisor;
import com.nokia.utils.PageUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SupervisorService extends IService<Supervisor> {

    Supervisor selectDataById(String id);

    Supervisor selectDataByInsId(String processInstanceId);

    List<Supervisor> selectDataByInsIdSet(Set<String> processInstanceId);

    PageUtils selectDataByParam(Map<String, Object> params);

    PageUtils selectDataByQueryParam(Map<String, Object> pageParams, Map<String, Object> queryParams);

    PageUtils selectDataByParam(Map<String, Object> pageParams, Set<String> processInstanceId);

    PageUtils findData(List<ProjectRightConfigEntity> prcList, Set<String> processInstanceId, Map<String, Object> params);

    Integer findDataCount(List<ProjectRightConfigEntity> prcList, Set<String> processInstanceId, Map<String, Object> params);

    List<Supervisor> findDataAll(List<ProjectRightConfigEntity> prcList, Set<String> processInstanceId, Map<String, Object> params);

    String queryYDSequence();

    String queryLTSequence();

    String queryDXSequence();

    String countCityNum(String branchCompany);
}
