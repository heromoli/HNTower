package com.nokia.modules.workflow.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("supervisor")
public class BuildOrderConfirm extends RowModel implements Serializable {


    @ExcelProperty(value = { "序号", "序号" }, index = 0)
    private Long id;

    @TableId
    @ExcelProperty(value = { "需求编号", "需求编号" }, index = 1)
    private String demandNum;

    @ExcelProperty(value = { "电信企业", "电信企业" }, index = 2)
    private String operatorName;

    @ExcelProperty(value = { "分公司", "分公司" }, index = 3)
    private String branchCompany;

    @ExcelProperty(value = { "区县", "区县" }, index = 4)
    private String region;

    @ExcelProperty(value = { "站点名称", "站点名称" }, index = 5)
    private String stationName;

    private String stationNum;

//    @ExcelProperty(value = { "站点级别", "站点级别" }, index = 6)
    private String stationLevel;

//    @ExcelProperty(value = { "场景划分", "场景划分" }, index = 7)
    private String scene;

    @ExcelProperty(value = { "建设方式", "建设方式" }, index = 6)
    private String buildType;

    @ExcelProperty(value = { "运营商原始需求信息", "经度" }, index = 7)
    private String longitude;

    @ExcelProperty(value = { "运营商原始需求信息", "纬度" }, index = 8)
    private String latitude;

    @ExcelProperty(value = { "运营商原始需求信息", "详细地址" }, index = 9)
    private String address;

    @ExcelProperty(value = { "铁塔种类", "铁塔种类" }, index = 10)
    private String towerType;

    @ExcelProperty(value = { "铁塔细分种类", "铁塔细分种类" }, index = 11)
    private String towerTypeDetail;

    @ExcelProperty(value = { "机房类型", "机房类型" }, index = 12)
    private String roomType;

    @ExcelProperty(value = { "产品配置", "产品配置" }, index = 13)
    private String productConfig;

    @ExcelProperty(value = { "系统个数", "系统个数" }, index = 14)
    private String systemNum;

    @ExcelProperty(value = { "机位数", "机位数" }, index = 15)
    private String seatNum;

    @ExcelProperty(value = { "天线数", "天线数" }, index = 16)
    private String antennaNum;

    @ExcelProperty(value = { "天线挂高", "天线挂高" }, index = 17)
    private String antennaHeight;

    @ExcelProperty(value = { "提供客户总功耗", "提供客户总功耗" }, index = 18)
    private String fullCapacity;

    @ExcelProperty(value = { "风压系数", "风压系数" }, index = 19)
    private String windPressure;

    @ExcelProperty(value = { "电力引入方式", "电力引入方式" }, index = 20)
    private String electricType;

    @ExcelProperty(value = { "批次编号", "批次编号" }, index = 21)
    private String batchNum;

    @ExcelProperty(value = { "备注", "备注" }, index = 22)
    private String remarks;

    @ExcelProperty(value = { "工作流编号", "工作流编号" }, index = 23)
    private String actProcInstId;

    @ExcelProperty(value = { "工作流状态", "工作流状态" }, index = 24)
    private String actProcStatus;
}
