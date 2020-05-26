package com.nokia.modules.report.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.report.entity.OperatorDailyReport;
import com.nokia.utils.PageUtils;

import java.util.Map;

public interface OperatorDailyReportService extends IService<OperatorDailyReport> {

    PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams);
}
