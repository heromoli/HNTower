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
@TableName("BRANCH_DAILY_REPORT")
public class BranchDailyReport extends RowModel implements Serializable {

    @ExcelProperty(index = 0, value = {"时间","时间"})
    @JsonProperty("sdate")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date sdate;

    @ExcelProperty(index = 1, value = {"分公司","分公司"})
    private String branch;

    @ExcelProperty(index =2, value = {"任务","总量"})
    private double task;

    @ExcelProperty(index = 3, value = {"任务","增量"})
    private double taskIncrement;

    @ExcelProperty(index = 4, value = {"征址","总量"})
    private double locationNeg;

    @ExcelProperty(index = 5, value = {"征址","增量"})
    private double locationIncrement;

    @ExcelProperty(index = 6, value = {"开工","总量"})
    private double projectStart;

    @ExcelProperty(index = 7, value = {"开工","增量"})
    private double startIncrement;

    @ExcelProperty(index = 8, value = {"完工","总量"})
    private double complete;

    @ExcelProperty(index = 9, value = {"完工","增量"})
    private double completeIncrement;

    @ExcelProperty(index = 10, value = {"征址率","征址率"})
    private String ratioLocationNeg;

    @ExcelProperty(index = 11, value = {"完工率","完工率"})
    private String ratioComplete;



}
