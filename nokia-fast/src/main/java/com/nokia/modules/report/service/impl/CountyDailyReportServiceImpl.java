package com.nokia.modules.report.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.report.dao.BranchDailyReportDao;
import com.nokia.modules.report.dao.CountyDailyReportDao;
import com.nokia.modules.report.entity.BranchDailyReport;
import com.nokia.modules.report.entity.CountyDailyReport;
import com.nokia.modules.report.service.BranchDailyReportService;
import com.nokia.modules.report.service.CountyDailyReportService;
import com.nokia.utils.PageUtils;
import com.nokia.utils.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

import static com.nokia.utils.DateUtils.DATE_PATTERN;
import static com.nokia.utils.DateUtils.stringToDate;

@Service("CountyDailyReportService")
public class CountyDailyReportServiceImpl extends ServiceImpl<CountyDailyReportDao, CountyDailyReport> implements CountyDailyReportService {
    private static final Logger logger = LoggerFactory.getLogger(CountyDailyReportServiceImpl.class);

    @Override
    public PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams) {
        QueryWrapper queryWrapper = new QueryWrapper<BranchDailyReport>();

        if (queryParams.get("countyQueryDate") != null && !queryParams.get("countyQueryDate").equals("") && !queryParams.get("countyQueryDate").equals("null")) {
            Date sdate = stringToDate(queryParams.get("countyQueryDate").toString().substring(0, 10), DATE_PATTERN);
            queryWrapper.eq("sdate", sdate);
        }
//        String queryDate = queryParams.get("countyQueryDate").toString().equals("") ? "" : queryParams.get("countyQueryDate").toString();
//        if(!queryDate.equals("")){
//            Date sdate = stringToDate(queryDate.substring(0,10),DATE_PATTERN);
//            if (!sdate.equals("")) {
//                queryWrapper.eq("sdate", sdate);
//            }
//        }
        queryWrapper.orderByAsc( "branch_Company");
        IPage<CountyDailyReport> page = this.page(new Query<CountyDailyReport>().getPage(pageParams), queryWrapper);
        return new PageUtils(page);
    }
}
