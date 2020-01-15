package com.nokia.modules.report.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.report.entity.BranchDailyReportPro;
import com.nokia.utils.PageUtils;

import java.util.Map;

public interface BranchDailyReportProService extends IService<BranchDailyReportPro> {

    PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams);
}
