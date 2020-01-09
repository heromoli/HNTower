package com.nokia.modules.report.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nokia.modules.report.entity.BranchDailyReport;
import com.nokia.modules.report.entity.CountyDailyReport;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CountyDailyReportDao extends BaseMapper<CountyDailyReport> {


}
