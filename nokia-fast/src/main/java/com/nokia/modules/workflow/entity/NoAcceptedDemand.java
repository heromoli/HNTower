package com.nokia.modules.workflow.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("NO_ACCEPTED_DEMAND")
public class NoAcceptedDemand extends RowModel implements Serializable {

    @ExcelProperty(index = 0 , value =  {"需求单号"})
    private String demandNum;

    @ExcelProperty(index = 1 , value =  {"需求名称"})
    private String demandName;

    @ExcelProperty(index = 2 , value =  {"运营商客户"})
    private String operatorName;

    @ExcelProperty(index = 3 , value =  {"需求类型"})
    private String demandType;

    @ExcelProperty(index = 4 , value =  {"需求状态"})
    private String demandStatus;

    @ExcelProperty(index = 5 , value =  {"经度"})
    private String longitude;

    @ExcelProperty(index = 6 , value =  {"纬度"})
    private String latitude;

    @ExcelProperty(index = 7 , value =  {"处理人"})
    private String handler;

    @JsonProperty("demandProposeTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    @ExcelProperty(index = 8 , value =  {"处理时间"})
    private String handleTime;

    @JsonProperty("demandProposeTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    @ExcelProperty(index = 9 , value =  {"承接时间"})
    private String acceptTime;

    @ExcelProperty(index = 10 , value =  {"天线数"})
    private String antennaNum;

    @ExcelProperty(index = 11 , value =  {"系统数"})
    private String systemNum;

    @ExcelProperty(index = 12 , value =  {"系统类型及频段"})
    private String systemType;

    @ExcelProperty(index = 13 , value =  {"推送人员"})
    private String pusher;

    @ExcelProperty(index = 14 , value =  {"覆盖范围描述"})
    private String coverRangeDescribe;


}
