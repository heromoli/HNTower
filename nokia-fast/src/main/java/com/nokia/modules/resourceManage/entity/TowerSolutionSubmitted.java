package com.nokia.modules.resourceManage.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nokia.modules.workflow.entity.RowModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//站址信息表
@Data
@TableName("TOWER_SOLUTION_SUBMITTED")
public class TowerSolutionSubmitted extends RowModel implements Serializable {

    @ExcelProperty(value = {"序号"}, index = 0)
    private Long id;

    @ExcelProperty(value = {"地市级分公司名称"}, index = 1)
    private String cityBranchCompany;

    @ExcelProperty(value = {"综合解决方案名称"}, index = 2)
    private String solutionName;

    @ExcelProperty(value = {"站址类型"}, index = 3)
    private String stationType;

    @ExcelProperty(value = {"电信企业"}, index = 4)
    private String operatorName;

    @ExcelProperty(value = {"市县"}, index = 5)
    private String city;

    @ExcelProperty(value = {"需求名称"}, index = 6)
    private String demandName;

    @ExcelProperty(value = {"经度"}, index = 7)
    private String longitude;

    @ExcelProperty(value = {"纬度"}, index = 8)
    private String latitude;

    @ExcelProperty(value = {"建设分类"}, index = 9)
    private String buildClassify;

    @ExcelProperty(value = {"下需求时间"}, index = 10)
    @JsonProperty("demandSubmitTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date demandSubmitTime;

    @ExcelProperty(value = {"需求编码"}, index = 11)
    private String demandCode;

    @ExcelProperty(value = {"建设方式"}, index = 12)
    private String buildType;

    @ExcelProperty(value = {"网络"}, index = 13)
    private String netType;

}

