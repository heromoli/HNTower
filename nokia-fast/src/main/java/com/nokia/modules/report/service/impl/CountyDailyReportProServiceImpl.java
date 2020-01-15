package com.nokia.modules.report.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.report.dao.CountyDailyReportDao;
import com.nokia.modules.report.dao.CountyDailyReportProDao;
import com.nokia.modules.report.entity.BranchDailyReport;
import com.nokia.modules.report.entity.CountyDailyReport;
import com.nokia.modules.report.entity.CountyDailyReportPro;
import com.nokia.modules.report.service.CountyDailyReportProService;
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

@Service("CountyDailyReportProService")
public class CountyDailyReportProServiceImpl extends ServiceImpl<CountyDailyReportProDao, CountyDailyReportPro> implements CountyDailyReportProService {
    private static final Logger logger = LoggerFactory.getLogger(CountyDailyReportProServiceImpl.class);

    @Override
    public PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams) {
        QueryWrapper queryWrapper = new QueryWrapper<CountyDailyReportPro>();

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
        queryWrapper.orderByAsc("sdate", "county");
        IPage<CountyDailyReportPro> page = this.page(new Query<CountyDailyReportPro>().getPage(pageParams), queryWrapper);
        return new PageUtils(page);
    }
}
