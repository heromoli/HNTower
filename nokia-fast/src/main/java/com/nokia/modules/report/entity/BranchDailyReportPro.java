package com.nokia.modules.report.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nokia.modules.workflow.entity.RowModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("TIETA_PRO_DAILY_REPORT")
public class BranchDailyReportPro extends RowModel implements Serializable {

    @ExcelProperty(index = 0, value = {"时间","时间"})
    @JsonProperty("sdate")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date sdate;

    @ExcelProperty(index = 1, value = {"分公司","分公司"})
    private String branchCompany;

    @ExcelProperty(index =2, value = {"任务","总量"})
    private double taskNumber;

    @ExcelProperty(index = 3, value = {"任务","增量"})
    private double taskIncrement;

    @ExcelProperty(index = 4, value = {"征址","总量"})
    private double locationNegNumber;

    @ExcelProperty(index = 5, value = {"征址","增量"})
    private double locatoinIncrement;

    @ExcelProperty(index = 6, value = {"开工","总量"})
    private double projectStartNumber;

    @ExcelProperty(index = 7, value = {"开工","增量"})
    private double startIncrement;

    @ExcelProperty(index = 8, value = {"完工","总量"})
    private double completeNumber;

    @ExcelProperty(index = 9, value = {"完工","增量"})
    private double completeIncrement;

    @ExcelProperty(index = 10, value = {"交付","总量"})
    private double deliverNumber;

    @ExcelProperty(index = 11, value = {"交付","增量"})
    private double deliverIncrement;

    @ExcelProperty(index = 12, value = {"起租","总量"})
    private double hireNumber;

    @ExcelProperty(index = 13, value = {"起租","增量"})
    private double hireIncrement;



}
