package com.nokia.modules.report.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.report.dao.OperatorDailyReportDao;
import com.nokia.modules.report.entity.OperatorDailyReport;
import com.nokia.modules.report.service.OperatorDailyReportService;
import com.nokia.utils.PageUtils;
import com.nokia.utils.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

import static com.nokia.utils.DateUtils.DATE_PATTERN;
import static com.nokia.utils.DateUtils.stringToDate;

@Service("OperatorDailyReportService")
public class OperatorDailyReportServiceImpl extends ServiceImpl<OperatorDailyReportDao, OperatorDailyReport> implements OperatorDailyReportService {
    private static final Logger logger = LoggerFactory.getLogger(OperatorDailyReportServiceImpl.class);

    @Override
    public PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams) {
        QueryWrapper queryWrapper = new QueryWrapper<OperatorDailyReport>();

        if (queryParams.get("operatorQueryDate") != null && !queryParams.get("operatorQueryDate").equals("") && !queryParams.get("operatorQueryDate").equals("null")) {
            Date sdate = stringToDate(queryParams.get("operatorQueryDate").toString().substring(0, 10), DATE_PATTERN);
            queryWrapper.eq("sdate", sdate);
        }
//        String queryDate = queryParams.get("countyQueryDate").toString().equals("") ? "" : queryParams.get("countyQueryDate").toString();
//        if(!queryDate.equals("")){
//            Date sdate = stringToDate(queryDate.substring(0,10),DATE_PATTERN);
//            if (!sdate.equals("")) {
//                queryWrapper.eq("sdate", sdate);
//            }
//        }
        queryWrapper.orderByAsc("operator_Name");
        IPage<OperatorDailyReport> page = this.page(new Query<OperatorDailyReport>().getPage(pageParams), queryWrapper);
        return new PageUtils(page);
    }
}
