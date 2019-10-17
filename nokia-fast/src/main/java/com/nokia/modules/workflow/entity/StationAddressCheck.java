package com.nokia.modules.workflow.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@TableName("supervisor")
public class StationAddressCheck extends RowModel implements Serializable {

    @TableId
    @ExcelProperty(value = { "序号", "序号" }, index = 0)
    private Long id;

    @ExcelProperty(value = { "工作流编号", "工作流编号" }, index = 23)
    private String actProcInstId;

    @ExcelProperty(value = { "当前状态", "当前状态" }, index = 24)
    private String actProcStatus;

    @ExcelProperty(value = { "电信企业", "电信企业" }, index = 1)
    private String operatorName;

    @ExcelProperty(value = { "需求编号", "需求编号" }, index = 4)
    private String demandNum;

    @ExcelProperty(value = { "地市", "地市" }, index = 2)
    private String branchCompany;

    private String county;

    @ExcelProperty(value = { "区县", "区县" }, index = 3)
    private String region;

    @ExcelProperty(value = { "站点名称", "站点名称" }, index = 5)
    private String stationName;

    @ExcelProperty(value = { "站点级别", "站点级别" }, index = 6)
    private String stationLevel;

    @ExcelProperty(value = { "场景划分", "场景划分" }, index = 7)
    private String scene;

    @ExcelProperty(value = { "运营商原始需求信息", "经度" }, index = 8)
    private String longitude;

    @ExcelProperty(value = { "运营商原始需求信息", "纬度" }, index = 9)
    private String latitude;

    @ExcelProperty(value = { "运营商原始需求信息", "详细地址" }, index = 10)
    private String address;

    @ExcelProperty(value = { "能否形成订单", "能否形成订单" }, index = 11)
    @TableField(exist=false)
    private String ifOrder;

    @ExcelProperty(value = { "是否共享已有站址", "是否共享已有站址" }, index = 12)
    private String ifShare;

    @ExcelProperty(value = { "共享存量站址产权归属方", "共享存量站址产权归属方" }, index = 13)
    private String shareStationBelong;

    @ExcelProperty(value = { "原有运营商数(户)", "原有运营商数(户)" }, index = 14)
    private String oldOperatorNum;

    @ExcelProperty(value = { "原有具体运营商", "原有具体运营商" }, index = 15)
    private String oldOperator;

    @ExcelProperty(value = { "本次新增运营商数(户)", "本次新增运营商数(户)" }, index = 16)
    private String newOperatorNum;

    @ExcelProperty(value = { "新增具体运营商", "新增具体运营商" }, index = 17)
    private String newOperator;

    @ExcelProperty(value = { "天线挂高范围", "天线挂高范围" }, index = 18)
    private String antennaHeight;

    @ExcelProperty(value = { "铁塔种类", "铁塔种类" }, index = 19)
    private String towerType;

    @ExcelProperty(value = { "铁塔细分种类", "铁塔细分种类" }, index = 20)
    private String towerTypeDetail;

    @ExcelProperty(value = { "机房类型", "机房类型" }, index = 21)
    private String roomType;

    @ExcelProperty(value = { "建设方式", "建设方式" }, index = 22)
    private String buildType;

    @ExcelProperty(value = { "备注", "备注" }, index = 25)
    private String remarks;

}
