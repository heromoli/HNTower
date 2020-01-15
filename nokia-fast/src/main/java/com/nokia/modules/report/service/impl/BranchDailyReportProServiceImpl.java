package com.nokia.modules.report.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.report.dao.BranchDailyReportDao;
import com.nokia.modules.report.dao.BranchDailyReportProDao;
import com.nokia.modules.report.entity.BranchDailyReport;
import com.nokia.modules.report.entity.BranchDailyReportPro;
import com.nokia.modules.report.service.BranchDailyReportProService;
import com.nokia.modules.report.service.BranchDailyReportService;
import com.nokia.utils.PageUtils;
import com.nokia.utils.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

import static com.nokia.utils.DateUtils.DATE_PATTERN;
import static com.nokia.utils.DateUtils.stringToDate;

@Service("BranchDailyReportProService")
public class BranchDailyReportProServiceImpl extends ServiceImpl<BranchDailyReportProDao, BranchDailyReportPro> implements BranchDailyReportProService {
    private static final Logger logger = LoggerFactory.getLogger(BranchDailyReportProServiceImpl.class);

    @Override
    public PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams) {
        QueryWrapper queryWrapper = new QueryWrapper<BranchDailyReportPro>();
        if (queryParams.get("branchQueryDate") != null && !queryParams.get("branchQueryDate").equals("") && !queryParams.get("branchQueryDate").equals("null")) {
            Date sdate = stringToDate(queryParams.get("branchQueryDate").toString().substring(0, 10), DATE_PATTERN);
            queryWrapper.eq("sdate", sdate);
        }
        queryWrapper.orderByAsc("sdate", "branch_company");
        IPage<BranchDailyReportPro> page = this.page(new Query<BranchDailyReportPro>().getPage(pageParams), queryWrapper);
        return new PageUtils(page);
    }
}
