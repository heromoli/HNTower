package com.nokia.modules.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.sys.entity.ProjectRightConfigEntity;
import com.nokia.modules.workflow.dao.SupervisorDao;
import com.nokia.modules.workflow.entity.Supervisor;
import com.nokia.modules.workflow.service.SupervisorService;
import com.nokia.utils.PageUtils;
import com.nokia.utils.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("SupervisorService")
public class SupervisorServiceImpl extends ServiceImpl<SupervisorDao, Supervisor> implements SupervisorService {
    private static final Logger logger = LoggerFactory.getLogger(SupervisorServiceImpl.class);

    @Override
    public Supervisor selectDataById(String id) {
        QueryWrapper queryWrapper = new QueryWrapper<Supervisor>();
        queryWrapper.eq("id", id);
        return this.getOne(queryWrapper);
    }

    @Override
    public Supervisor selectDataByInsId(String processInstanceId) {
        QueryWrapper queryWrapper = new QueryWrapper<Supervisor>();
        queryWrapper.eq("act_proc_inst_id", processInstanceId);
        return this.getOne(queryWrapper);
    }

    @Override
    public Supervisor selectDataByDemandNum(String demandNum) {
        QueryWrapper queryWrapper = new QueryWrapper<Supervisor>();
        queryWrapper.eq("demand_num", demandNum);
        return this.getOne(queryWrapper);
    }

    @Override
    public List<Supervisor> selectDataByInsIdSet(Set<String> processInstanceId) {
        QueryWrapper queryWrapper = new QueryWrapper<Supervisor>();
        queryWrapper.in("act_proc_inst_id", processInstanceId);
        return this.list(queryWrapper);

    }

    @Override
    public PageUtils selectDataByParam(Map<String, Object> params) {
        QueryWrapper queryWrapper = new QueryWrapper<Supervisor>();
        String branchCompany = (String) params.get("branchCompany");
        queryWrapper.like("BRANCH_COMPANY",branchCompany);
        String stationName = (String) params.get("stationName");
        queryWrapper.like("STATION_NAME",stationName);
        String address = (String) params.get("address");
        queryWrapper.like("ADDRESS",address);
        IPage<Supervisor> page = this.page(new Query<Supervisor>().getPage(params), queryWrapper);
        return new PageUtils(page);
    }

    @Override
    public PageUtils selectDataByQueryParam(Map<String, Object> pageParams, Map<String, Object> queryParams) {
        QueryWrapper queryWrapper = new QueryWrapper<Supervisor>();
        String branchCompany = queryParams.get("branchCompany").toString().equals("") ? "" : queryParams.get("branchCompany").toString();
        String demandNum = queryParams.get("demandNum").toString().equals("") ? "" : queryParams.get("demandNum").toString();
        String stationName = queryParams.get("stationName").toString().equals("") ? "" : queryParams.get("stationName").toString();
        String address = queryParams.get("address").toString().equals("") ? "" : queryParams.get("address").toString();
        String specialStation = queryParams.get("specialStation").toString().equals("") ? "" : queryParams.get("specialStation").toString();
        if (!branchCompany.equals("")) {
            queryWrapper.like("branch_Company", branchCompany);
        }

        if (!demandNum.equals("")) {
            queryWrapper.like("demand_Num", demandNum);
        }

        if (!stationName.equals("")) {
            queryWrapper.like("station_Name", stationName);
        }

        if (!address.equals("")) {
            queryWrapper.like("address", address);
        }

        if (!specialStation.equals("")) {
            queryWrapper.like("special_Station", specialStation);
        }

        IPage<Supervisor> page = this.page(new Query<Supervisor>().getPage(pageParams), queryWrapper);
        return new PageUtils(page);
    }

    @Override
    public PageUtils selectByQueryParamAndPrc(Map<String, Object> pageParams, Map<String, Object> queryParams, List<ProjectRightConfigEntity> prcList) {
        QueryWrapper queryWrapper = getQueryWrapper(prcList);
        String branchCompany = queryParams.get("branchCompany").toString().equals("") ? "" : queryParams.get("branchCompany").toString();
        String demandNum = queryParams.get("demandNum").toString().equals("") ? "" : queryParams.get("demandNum").toString();
        String stationName = queryParams.get("stationName").toString().equals("") ? "" : queryParams.get("stationName").toString();
        String address = queryParams.get("address").toString().equals("") ? "" : queryParams.get("address").toString();
        String specialStation = queryParams.get("specialStation").toString().equals("") ? "" : queryParams.get("specialStation").toString();
        if (!branchCompany.equals("")) {
            queryWrapper.like("branch_Company", branchCompany);
        }

        if (!demandNum.equals("")) {
            queryWrapper.like("demand_Num", demandNum);
        }

        if (!stationName.equals("")) {
            queryWrapper.like("station_Name", stationName);
        }

        if (!address.equals("")) {
            queryWrapper.like("address", address);
        }

        if (!specialStation.equals("")) {
            queryWrapper.like("special_Station", specialStation);
        }

        IPage<Supervisor> page = this.page(new Query<Supervisor>().getPage(pageParams), queryWrapper);
        return new PageUtils(page);
    }

    @Override
    public PageUtils selectDataByParam(Map<String, Object> pageParams, Set<String> processInstanceId) {
        QueryWrapper queryWrapper = new QueryWrapper<Supervisor>();
        queryWrapper.in("act_proc_inst_id", processInstanceId);
        IPage<Supervisor> page = this.page(new Query<Supervisor>().getPage(pageParams), queryWrapper);
        return new PageUtils(page);
    }

    @Override
    public PageUtils findData(List<ProjectRightConfigEntity> prcList, Set<String> processInstanceId, Map<String, Object> params) {
        if (prcList != null && prcList.size() > 0 && (processInstanceId != null && processInstanceId.size() > 0)) {
            QueryWrapper queryWrapper = getQueryWrapper(prcList);
            queryWrapper.eq("ACT_PROC_STATUS", "1");
            IPage<Supervisor> page = this.page(new Query<Supervisor>().getPage(params), queryWrapper);
            return new PageUtils(page);
        }
        return null;
    }

    @Override
    public Integer findDataCount(List<ProjectRightConfigEntity> prcList, Set<String> processInstanceId, Map<String, Object> params) {
        if (prcList != null && prcList.size() > 0 && (processInstanceId != null && processInstanceId.size() > 0)) {
            List<String> actProcStatus = (List<String>) params.get("actProcStatus");
            Set<String> set = new HashSet<>(actProcStatus);
            QueryWrapper queryWrapper = getQueryWrapper(prcList);
//            queryWrapper.in("ACT_PROC_STATUS", set);
            queryWrapper.in("ACT_PROC_INST_ID", processInstanceId);
            return this.listMaps(queryWrapper).size();
        }
        return 0;
    }

    @Override
    public List<Supervisor> findDataAll(List<ProjectRightConfigEntity> prcList, Set<String> processInstanceId, Map<String, Object> params) {
        if (prcList != null && prcList.size() > 0 && (processInstanceId != null && processInstanceId.size() > 0)) {
            String actProcStatus = (String) params.get("actProcStatus");
            QueryWrapper queryWrapper = getQueryWrapper(prcList);
            queryWrapper.eq("ACT_PROC_STATUS", actProcStatus);
            queryWrapper.in("ACT_PROC_INST_ID",processInstanceId);    //根据字段给出查询参数
            return this.list(queryWrapper);
        }
        return null;
    }

    @Override
    public String queryYDSequence() {
        String seq = baseMapper.queryYDSequence();
        return autoGenericCode(seq, 4);
    }

    @Override
    public String queryLTSequence() {
        String seq = baseMapper.queryLTSequence();
        return autoGenericCode(seq, 4);
    }

    @Override
    public String queryDXSequence() {
        String seq = baseMapper.queryDXSequence();
        return autoGenericCode(seq, 4);
    }

    @Override
    public String countCityNum(String branchCompany) {
        return baseMapper.countCityNum(branchCompany);
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
                queryWrapper.in("branch_company", company);
            }
            if (operator != null) {
                queryWrapper.in("operator_Name", operator);
            }
        }
        return queryWrapper;
    }

    private static String autoGenericCode(String code, int num) {
        String result = "";
        result = String.format("%0" + num + "d", Integer.parseInt(code));
        return result;
    }

}
