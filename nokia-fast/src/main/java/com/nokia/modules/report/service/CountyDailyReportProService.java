package com.nokia.modules.report.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.report.entity.CountyDailyReport;
import com.nokia.modules.report.entity.CountyDailyReportPro;
import com.nokia.utils.PageUtils;

import java.util.Map;

public interface CountyDailyReportProService extends IService<CountyDailyReportPro> {

    PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams);
}
