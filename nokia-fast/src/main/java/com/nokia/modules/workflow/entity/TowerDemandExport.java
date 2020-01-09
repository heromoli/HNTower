package com.nokia.modules.workflow.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("TOWER_DEMAND_EXPORT")
public class TowerDemandExport extends RowModel implements Serializable {

    @ExcelProperty(index = 0, value = {"统计时间"})
    private String countTime;

    @ExcelProperty(index = 1, value = {"移动侧订单号"})
    private String mobileOrderNum;

    @ExcelProperty(index = 2, value = {"铁塔侧订单号"})
    private String towerOrderNum;

    @ExcelProperty(index = 3, value = {"铁塔侧站址名称"})
    private String towerStationName;

    @ExcelProperty(index = 4, value = {"需求站址名称"})
    private String demandStationName;

    @ExcelProperty(index = 5, value = {"站址编码"})
    private String stationNum;

    @ExcelProperty(index = 6, value = {"规划编号"})
    private String planNum;

    @ExcelProperty(index = 7, value = {"省份"})
    private String province;

    @ExcelProperty(index = 8, value = {"地市"})
    private String city;

    @ExcelProperty(index = 9, value = {"区县"})
    private String county;

    @ExcelProperty(index = 10, value = {"移动侧建设类型"})
    private String mobileBuildType;

    @ExcelProperty(index = 11, value = {"交付指标建设类型"})
    private String deliverBuildType;

    @ExcelProperty(index = 12, value = {"起租指标建设类型"})
    private String hireBuildType;

    @ExcelProperty(index = 13, value = {"站址类型"})
    private String stationType;

    @ExcelProperty(index = 14, value = {"原站址来源"})
    private String oldStationSource;

    @ExcelProperty(index = 15, value = {"项目批次"})
    private String projectBatch;

    @ExcelProperty(index = 16, value = {"所处阶段"})
    private String stage;

    @ExcelProperty(index = 17, value = {"所处状态"})
    private String status;

    @ExcelProperty(index = 18, value = {"经度"})
    private double longitude;

    @ExcelProperty(index = 19, value = {"纬度"})
    private double latitude;

    @ExcelProperty(index = 20, value = {"备注"})
    private String remarks;

    @ExcelProperty(index = 21, value = {"期望交付时间"})
    private String expectDeliverTime;

    @ExcelProperty(index = 22, value = {"信源频段"})
    private String signalBand;

    @ExcelProperty(index = 23, value = {"需求提出时间"})
    private String demandProposedTime;

    @ExcelProperty(index = 24, value = {"需求承接时间"})
    private String demandAcceptedTime;

    @ExcelProperty(index = 25, value = {"站址筛查接收时间"})
    private String checkAcceptedTime;

    @ExcelProperty(index = 26, value = {"站址筛查确认时间"})
    private String checkConfirmTime;

    @ExcelProperty(index = 27, value = {"订单接收时间"})
    private String orderAcceptedTime;

    @ExcelProperty(index = 28, value = {"订单确认时间"})
    private String orderConfirmTime;

    @ExcelProperty(index = 29, value = {"约定交付时间"})
    private String agreeDeliverTime;

    @ExcelProperty(index = 30, value = {"项目启动时间"})
    private String projectStartTime;

    @ExcelProperty(index = 31, value = {"交付接收时间"})
    private String deliverAcceptedTime;

    @ExcelProperty(index = 32, value = {"交付确认时间"})
    private String deliverConfirmTime;

    @ExcelProperty(index = 33, value = {"起租接收时间"})
    private String hireAcceptedTime;

    @ExcelProperty(index = 34, value = {"起租确认时间"})
    private String hireConfirmTime;

    @ExcelProperty(index = 35, value = {"归档时间"})
    private String fileTime;

    @ExcelProperty(index = 36, value = {"需求经度"})
    private double demandLongitude;

    @ExcelProperty(index = 37, value = {"需求纬度"})
    private double demandLatitude;

    @ExcelProperty(index = 38, value = {"经纬度偏移距离(米)"})
    private String migrateDistance;

    @ExcelProperty(index = 39, value = {"项目工程名称"})
    private String projectName;

    @ExcelProperty(index = 40, value = {"原站址编码"})
    private String oldStationNum;

}
