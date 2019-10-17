package com.nokia.modules.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.sys.entity.ProjectRightConfigEntity;
import com.nokia.modules.workflow.dao.StationAddressCheckDao;
import com.nokia.modules.workflow.entity.StationAddressCheck;
import com.nokia.modules.workflow.entity.Supervisor;
import com.nokia.modules.workflow.service.StationAddressCheckService;
import com.nokia.utils.PageUtils;
import com.nokia.utils.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wow on 2019/7/10.
 */
@Service("StationAddressCheckService")
public class StationAddressCheckServiceImpl extends ServiceImpl<StationAddressCheckDao, StationAddressCheck> implements StationAddressCheckService {

    private static final Logger log = LoggerFactory.getLogger(StationAddressCheckServiceImpl.class);

    @Override
    public PageUtils findData(List<ProjectRightConfigEntity> prcList, Set<String> processInstanceId, Map<String, Object> params) {
        if (prcList != null && prcList.size() > 0 && (processInstanceId != null && processInstanceId.size() > 0)) {
            QueryWrapper queryWrapper = getQueryWrapper(prcList);
//            queryWrapper.eq("ACT_PROC_STATUS", "2");
            queryWrapper.in("ACT_PROC_INST_ID", processInstanceId);
            IPage<StationAddressCheck> page = this.page(new Query<StationAddressCheck>().getPage(params), queryWrapper);
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
    public List<StationAddressCheck> findDataAll(List<ProjectRightConfigEntity> prcList, Set<String> processInstanceId, Map<String, Object> params) {
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
