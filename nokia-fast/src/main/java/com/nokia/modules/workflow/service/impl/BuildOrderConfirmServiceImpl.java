package com.nokia.modules.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.sys.entity.ProjectRightConfigEntity;
import com.nokia.modules.workflow.dao.BuildOrderConfirmDao;
import com.nokia.modules.workflow.entity.BuildOrderConfirm;
import com.nokia.modules.workflow.service.BuildOrderConfirmService;
import com.nokia.utils.PageUtils;
import com.nokia.utils.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("BuildOrderConfirmService")
public class BuildOrderConfirmServiceImpl extends ServiceImpl<
        BuildOrderConfirmDao, BuildOrderConfirm> implements BuildOrderConfirmService {

    @Override
    public PageUtils findData(List<ProjectRightConfigEntity> prcList, Set<String> processInstanceId, Map<String, Object> params, Map<String, Object> queryParams) {
//        List<BuildOrderConfirm> orderList = new ArrayList<>();
//        int totalCount = 0;
//        int currPage = Integer.parseInt(params.get("page").toString());
//        int pageSize = Integer.parseInt(params.get("limit").toString());

        QueryWrapper<BuildOrderConfirm> queryWrapper = getQueryWrapper(prcList);

        if (prcList != null && prcList.size() > 0 && (processInstanceId != null && processInstanceId.size() > 0)) {
            String demandNum = queryParams.get("demandNum").toString().equals("") ? "" : queryParams.get("demandNum").toString();
            String stationName = queryParams.get("stationName").toString().equals("") ? "" : queryParams.get("stationName").toString();
            if (!demandNum.equals("")) {
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

//            for (List<String> idList : ret) {
//                QueryWrapper queryWrapper = getQueryWrapper(prcList);
//                if (!demandNum.equals("")) {
//                    queryWrapper.like("demand_Num", demandNum);
//                }
//                if (!stationName.equals("")) {
//                    queryWrapper.like("station_Name", stationName);
//                }
//                queryWrapper.in("ACT_PROC_INST_ID", idList);
//                IPage<BuildOrderConfirm> page = this.page(new Query<BuildOrderConfirm>().getPage(params), queryWrapper);
//                orderList.addAll(page.getRecords());
//                totalCount += page.getTotal();
//            }
//            return new PageUtils(orderList, totalCount, pageSize, currPage);

            if (ret.size() == 1) {
                queryWrapper.in("ACT_PROC_INST_ID", ret.get(0));
            } else if (ret.size() == 2) {
                queryWrapper.and(
                        obj1 -> obj1.in("ACT_PROC_INST_ID", ret.get(0))
                                .or(obj2 -> obj2.in("ACT_PROC_INST_ID", ret.get(1)))
                );
            } else if (ret.size() == 3) {
                queryWrapper.and(
                        obj1 -> obj1.in("ACT_PROC_INST_ID", ret.get(0))
                                .or(obj2 -> obj2.in("ACT_PROC_INST_ID", ret.get(1))
                                        .or(obj3 -> obj3.in("ACT_PROC_INST_ID", ret.get(2))))
                );
            } else if (ret.size() == 4) {
                queryWrapper.and(
                        obj1 -> obj1.in("ACT_PROC_INST_ID", ret.get(0))
                                .or(obj2 -> obj2.in("ACT_PROC_INST_ID", ret.get(1))
                                        .or(obj3 -> obj3.in("ACT_PROC_INST_ID", ret.get(2))
                                                .or(obj4 -> obj4.in("ACT_PROC_INST_ID", ret.get(3)))))
                );
            } else if (ret.size() == 5) {
                queryWrapper.and(
                        obj1 -> obj1.in("ACT_PROC_INST_ID", ret.get(0))
                                .or(obj2 -> obj2.in("ACT_PROC_INST_ID", ret.get(1))
                                        .or(obj3 -> obj3.in("ACT_PROC_INST_ID", ret.get(2))
                                                .or(obj4 -> obj4.in("ACT_PROC_INST_ID", ret.get(3))
                                                        .or(obj5 -> obj5.in("ACT_PROC_INST_ID", ret.get(4))))))
                );
            }

            IPage<BuildOrderConfirm> ipage = this.page(new Query<BuildOrderConfirm>().getPage(params), queryWrapper);
            return new PageUtils(ipage);
        }
        return null;
    }

    @Override
    public Integer findDataCount(List<ProjectRightConfigEntity> prcList, Set<String> processInstanceId, Map<String, Object> params) {
        if (prcList != null && prcList.size() > 0 && (processInstanceId != null && processInstanceId.size() > 0)) {
            QueryWrapper queryWrapper = getQueryWrapper(prcList);
            queryWrapper.in("act_proc_inst_id", processInstanceId);
            return this.listMaps(queryWrapper).size();
        }
        return 0;
    }

    @Override
    public List<BuildOrderConfirm> findDataAll(List<ProjectRightConfigEntity> prcList, Set<String> processInstanceId, Map<String, Object> params) {
        if (prcList != null && prcList.size() > 0 && (processInstanceId != null && processInstanceId.size() > 0)) {
            QueryWrapper queryWrapper = getQueryWrapper(prcList);
            queryWrapper.in("act_proc_inst_id", processInstanceId);
            return this.list(queryWrapper);
        }
        return null;
    }

    private QueryWrapper getQueryWrapper(List<ProjectRightConfigEntity> prcList) {
        QueryWrapper queryWrapper = new QueryWrapper<BuildOrderConfirm>();
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
