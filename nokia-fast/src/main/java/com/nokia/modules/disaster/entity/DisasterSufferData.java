package com.nokia.modules.disaster.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nokia.modules.workflow.entity.RowModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("")
public class DisasterSufferData extends RowModel implements Serializable {

    @ExcelProperty(index = 0, value = {"时间", "时间"})
    @JsonProperty("sdate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date sdate;

    @ExcelProperty(index = 1, value = {"分公司", "分公司"})
    private String city;

    @ExcelProperty(index = 2, value = {"运营商", "运营商"})
    private String operator;

    @ExcelProperty(index = 3, value = {"基站", "基站总数"})
    private int siteNumber;

    @ExcelProperty(index = 4, value = {"基站", "退服基站（当前）"})
    private int outageCurrent;

    @ExcelProperty(index = 5, value = {"基站", "退服率（%）"})
    private double outageCurrentRate;

    @ExcelProperty(index = 6, value = {"基站", "退服基站（累计）"})
    private int outageTotal;

    @ExcelProperty(index = 7, value = {"基站", "修复数（累计）"})
    private int repairTotal;

    @ExcelProperty(index = 8, value = {"基站", "减少退服基站"})
    private int repairCurrent;

    @ExcelProperty(index = 9, value = {"基站", "修复率（%）"})
    private double repairRate;

    @ExcelProperty(index = 10, value = {"基站", "产权基站"})
    private int owner;

    @ExcelProperty(index = 11, value = {"基站", "产权停电（当前）"})
    private int ownerCut;

    @ExcelProperty(index = 12, value = {"基站", "产权停电率（%）"})
    private double ownerCutRate;

    @ExcelProperty(index = 13, value = {"基站", "产权停电退服（当前）"})
    private int ownerCutOutageCurrent;

    @ExcelProperty(index = 14, value = {"基站", "产权停电退服率（当前）"})
    private int ownerCutOutageCurrentRate;

    @ExcelProperty(index = 15, value = {"光缆", "中断光缆数(累计)"})
    private int breakOptTotal;

    @ExcelProperty(index = 16, value = {"光缆", "修复数（累计）"})
    private int repairOptTotal;

    @ExcelProperty(index = 17, value = {"光缆", "修复率（%）"})
    private double repairOptRate;

}
