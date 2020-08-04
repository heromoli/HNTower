package com.nokia.modules.workflow.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("STATION5G_DEMAND")
public class Station5GBuildDemand extends RowModel implements Serializable {


    @ExcelProperty(value = {"序号"}, index = 0)
    private Long id;

    @ExcelProperty(value = {"需求编号"}, index = 1)
    private String demandNum;

    @ExcelProperty(value = {""}, index = 2)
    private String demandSource;

    @ExcelProperty(value = {""}, index = 3)
    private String branchCompany;

    @ExcelProperty(value = {""}, index = 4)
    private String county;

    @ExcelProperty(value = {""}, index = 5)
    private String region;

    @ExcelProperty(value = {""}, index = 6)
    private String stationName;

    @ExcelProperty(value = {""}, index = 7)
    private String scene;

    @ExcelProperty(value = {""}, index = 8)
    private String stationLevel;

    @ExcelProperty(value = {""}, index = 9)
    private String longitude;

    @ExcelProperty(value = {""}, index = 10)
    private String latitude;

    @ExcelProperty(value = {""}, index = 11)
    private String address;

    @ExcelProperty(value = {""}, index = 12)
    private String buildType;

    @ExcelProperty(value = {""}, index = 13)
    private String buildingOrGround;

    @ExcelProperty(value = {""}, index = 14)
    @JsonProperty("locationFinishTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date locationFinishTime;

    @ExcelProperty(value = {""}, index = 15)
    @JsonProperty("intoTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date intoTime;

    @ExcelProperty(value = {""}, index = 16)
    @JsonProperty("totalFinishTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date totalFinishTime;

    @ExcelProperty(value = {""}, index = 17)
    private String projectStateDescribe;

    @ExcelProperty(value = {""}, index = 18)
    private String orderNum;

    @ExcelProperty(value = {""}, index = 19)
    private String operatorName;

    @ExcelProperty(value = {""}, index = 20)
    private String remarks;

    @ExcelProperty(value = {""}, index = 21)
    @JsonProperty("totalFinishTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date pmsFinishTime;

    @ExcelProperty(value = {""}, index = 22)
    @JsonProperty("totalFinishTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date requireDeliveryTime;

    @ExcelProperty(value = {""}, index = 23)
    @JsonProperty("totalFinishTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date deliveryTime;


}
