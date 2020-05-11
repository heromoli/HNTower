package com.nokia.modules.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.sys.entity.ProjectRightConfigEntity;
import com.nokia.modules.workflow.dao.CustomerDemandCollectionDao;
import com.nokia.modules.workflow.entity.CustomerDemandCollection;
import com.nokia.modules.workflow.entity.Supervisor;
import com.nokia.modules.workflow.service.CustomerDemandCollectionService;
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
@Service("CustomerDemandCollectionService")
public class CustomerDemandCollectionServiceImpl extends ServiceImpl<
        CustomerDemandCollectionDao, CustomerDemandCollection> implements CustomerDemandCollectionService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerDemandCollectionServiceImpl.class);

    @Override
    public PageUtils findData(List<ProjectRightConfigEntity> prcList, Set<String> processInstanceId, Map<String, Object> params, Map<String, Object> queryParams) {
        if (prcList != null && prcList.size() > 0 && (processInstanceId != null && processInstanceId.size() > 0)) {
            QueryWrapper queryWrapper = getQueryWrapper(prcList);
//            queryWrapper.eq("ACT_PROC_STATUS", "1");

            String demandNum = queryParams.get("demandNum").toString().equals("") ? "" : queryParams.get("demandNum").toString();
            String stationName = queryParams.get("stationName").toString().equals("") ? "" : queryParams.get("stationName").toString();
            if (!demandNum.equals("")) {
//                queryWrapper.and(wrapper -> wrapper.like("station_Name", stationName));
                queryWrapper.like("demand_Num", demandNum);
            }

            if (!stationName.equals("")) {
                queryWrapper.like("station_Name", stationName);
            }

            List<String> processInstanceIdList = new ArrayList<>(processInstanceId);
            int size = processInstanceIdList.size();
            List<List<String>> ret = new ArrayList<>();
            if (size > 1000) {
                int pre = size / 1000;
                int last = size % 1000;
                // 前面pre个集合，每个大小都是 subListLength 个元素
                for (int i = 0; i < pre; i++) {
                    List<String> itemList = new ArrayList<>();
                    for (int j = 0; j < 1000; j++) {
                        itemList.add(processInstanceIdList.get(i * 1000 + j));
                    }
                    ret.add(itemList);
                }
                // last的进行处理
                if (last > 0) {
                    List<String> itemList = new ArrayList<>();
                    for (int i = 0; i < last; i++) {
                        itemList.add(processInstanceIdList.get(pre * 1000 + i));
                    }
                    ret.add(itemList);
                }

            } else {
                ret.add(processInstanceIdList);
            }
            for (List<String> idList : ret) {
                queryWrapper.in("ACT_PROC_INST_ID", idList);
                queryWrapper.or();
            }

//            queryWrapper.in("ACT_PROC_INST_ID", processInstanceId);

            IPage<CustomerDemandCollection> page = this.page(new Query<CustomerDemandCollection>().getPage(params), queryWrapper);
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
    public List<CustomerDemandCollection> findDataAll(List<ProjectRightConfigEntity> prcList, Set<String> processInstanceId, Map<String, Object> params) {
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
                queryWrapper.in("branch_Company", company);
            }
            if (operator != null) {
                queryWrapper.in("operator_Name", operator);
            }
        }
        return queryWrapper;
    }
}
