package com.nokia.modules.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.sys.entity.ProjectRightConfigEntity;
import com.nokia.modules.workflow.dao.BuildDemandChangeConfirmDao;
import com.nokia.modules.workflow.entity.BuildDemandChangeConfirm;
import com.nokia.modules.workflow.entity.Supervisor;
import com.nokia.modules.workflow.service.BuildDemandChangeConfirmService;
import com.nokia.utils.PageUtils;
import com.nokia.utils.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("BuildDemandChangeConfirmService")
public class BuildDemandChangeConfirmServiceImpl extends ServiceImpl<BuildDemandChangeConfirmDao, BuildDemandChangeConfirm> implements BuildDemandChangeConfirmService {


    @Override
    public BuildDemandChangeConfirm selectDataByInsId(String actProcInstId) {
        QueryWrapper queryWrapper = new QueryWrapper<Supervisor>();
        queryWrapper.eq("act_proc_inst_id", actProcInstId);
        return this.getOne(queryWrapper);
    }

    @Override
    public PageUtils findData(List<ProjectRightConfigEntity> prcList, Set<String> processInstanceId, Map<String, Object> params) {
        if (prcList != null && prcList.size() > 0 && (processInstanceId != null && processInstanceId.size() > 0)) {
            QueryWrapper queryWrapper = new QueryWrapper<Supervisor>();
//            queryWrapper.in("ACT_PROC_STATUS", "3","5","7");
            String queryParam = (String) params.get("param");
            queryWrapper.like("STATION_NAME",queryParam);
            IPage<BuildDemandChangeConfirm> page = this.page(new Query<BuildDemandChangeConfirm>().getPage(params), queryWrapper);
            return new PageUtils(page);
        }
        return null;
    }

    @Override
    public Integer findDataCount(List<ProjectRightConfigEntity> prcList, Set<String> processInstanceId, Map<String, Object> params) {
        if (prcList != null && prcList.size() > 0 && (processInstanceId != null && processInstanceId.size() > 0)) {
            QueryWrapper queryWrapper = getQueryWrapper(prcList);
            queryWrapper.in("ACT_PROC_INST_ID", processInstanceId);
            return this.listMaps(queryWrapper).size();
        }
        return 0;
    }

    @Override
    public List<BuildDemandChangeConfirm> findDataAll(List<ProjectRightConfigEntity> prcList, Set<String> processInstanceId, Map<String, Object> params) {
        if (prcList != null && prcList.size() > 0 && (processInstanceId != null && processInstanceId.size() > 0)) {
            QueryWrapper queryWrapper = getQueryWrapper(prcList);
            queryWrapper.in("ACT_PROC_INST_ID", processInstanceId);
            return this.list(queryWrapper);
        }
        return null;
    }

    private QueryWrapper getQueryWrapper(List<ProjectRightConfigEntity> prcList) {
        QueryWrapper queryWrapper = new QueryWrapper<Supervisor>();
        if (prcList != null && prcList.size() > 0) {
            List<String> company = new ArrayList<>();
            List<String> operator = new ArrayList<>();
            for (ProjectRightConfigEntity projectRightConfigEntity : prcList) {
                if (company != null)
                    if ("全部".equals(projectRightConfigEntity.getCompany())) {
                        company = null;
                    } else {
                        company.add(projectRightConfigEntity.getCompany());
                    }
                if (operator != null)
                    if ("全部".equals(projectRightConfigEntity.getOperator())) {
                        operator = null;
                    } else {
                        operator.add(projectRightConfigEntity.getOperator());
                    }
            }

            if (company != null) {
                queryWrapper.in("branchCompany", company);
            }
            if (operator != null) {
                queryWrapper.in("operatorName", operator);
            }
        }
        return queryWrapper;
    }
}
