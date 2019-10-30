package com.nokia.modules.workflow.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("supervisor")
public class StationAddressCheck extends RowModel implements Serializable {
    //表4
    @TableId
    @ExcelProperty(value = {"序号", "序号"}, index = 0)
    private Long id;

    @ExcelProperty(value = {"电信企业", "电信企业"}, index = 1)
    private String operatorName;

    @ExcelProperty(value = {"地市", "地市"}, index = 2)
    private String branchCompany;

    private String county;

    @ExcelProperty(value = {"市县", "市县"}, index = 3)
    private String region;

    @ExcelProperty(value = {"站点名称", "站点名称"}, index = 4)
    private String stationName;

    @ExcelProperty(value = {"需求编号", "需求编号"}, index = 5)
    private String demandNum;

    @ExcelProperty(value = {"场景划分", "场景划分"}, index = 6)
    private String scene;

    @ExcelProperty(value = {"运营商原始需求信息", "经度"}, index = 7)
    private String longitude;

    @ExcelProperty(value = {"运营商原始需求信息", "纬度"}, index = 8)
    private String latitude;

    //    @ExcelProperty(value = { "运营商原始需求信息", "详细地址" }, index = 10)
    private String address;

    @ExcelProperty(value = {"运营商原始需求信息", "允许偏离半径(米)"}, index = 9)
    private String deviateRadius;

    @ExcelProperty(value = {"能否形成订单", "能否形成订单"}, index = 10)
    private String ifCanOrder;

    @ExcelProperty(value = {"不能形成订单原因", "不能形成订单原因"}, index = 11)
    private String cantOrderReason;

    @ExcelProperty(value = {"候选站点1", "站点名称"}, index = 12)
    private String hx1StationName;

    @ExcelProperty(value = {"候选站点1", "站点编号"}, index = 13)
        private String hx1StationNum;

    @ExcelProperty(value = {"候选站点1", "经度"}, index = 14)
    private String hx1Longitude;

    @ExcelProperty(value = {"候选站点1", "纬度"}, index = 15)
    private String hx1Latitude;

    @ExcelProperty(value = {"候选站点1", "详细地址"}, index = 16)
    private String hx1Address;

    @ExcelProperty(value = {"候选站点1", "是否共享已有站址"}, index = 17)
    private String hx1IfShare;

    @ExcelProperty(value = {"候选站点1", "共享存量站址产权归属方"}, index = 18)
    private String hx1ShareStationBelong;

    @ExcelProperty(value = {"候选站点1", "原有运营商数(户)"}, index = 19)
    private String hx1OldOperatorNum;

    @ExcelProperty(value = {"候选站点1", "原有具体运营商"}, index = 20)
    private String hx1OldOperator;

    @ExcelProperty(value = {"候选站点1", "本次新增运营商数(户)"}, index = 21)
    private String hx1NewOperatorNum;

    @ExcelProperty(value = {"候选站点1", "新增具体运营商"}, index = 22)
    private String hx1NewOperator;

    @ExcelProperty(value = {"候选站点1", "天线挂高范围"}, index = 23)
    private String hx1AntennaHeight;

    @ExcelProperty(value = {"候选站点1", "铁塔类型"}, index = 24)
    private String hx1TowerType;

    @ExcelProperty(value = {"候选站点1", "铁塔细分种类"}, index = 25)
    private String hx1TowerTypeDetail;

    @ExcelProperty(value = {"候选站点1", "机房类型"}, index = 26)
    private String hx1RoomType;

    @ExcelProperty(value = {"候选站点1", "建设方式"}, index = 27)
    private String hx1BuildType;

    @ExcelProperty(value = {"候选站点1", "共建方1名称"}, index = 28)
    private String hx1Gjf1Name;

    @ExcelProperty(value = {"候选站点1", "共建方1需求站点名称"}, index = 29)
    private String hx1Gjf1StationName;

    @ExcelProperty(value = {"候选站点1", "共建方1需求站点编号"}, index = 30)
    private String hx1Gjf1StationNum;

    @ExcelProperty(value = {"候选站点1", "共建方1需求来源批次"}, index = 31)
    private String hx1Gjf1SourceBatch;

    @ExcelProperty(value = {"候选站点1", "共建方2名称"}, index = 32)
    private String hx1Gjf2Name;

    @ExcelProperty(value = {"候选站点1", "共建方2需求站点名称"}, index = 33)
    private String hx1Gjf2StationName;

    @ExcelProperty(value = {"候选站点1", "共建方2需求站点编号"}, index = 34)
    private String hx1Gjf2StationNum;

    @ExcelProperty(value = {"候选站点1", "共建方2需求来源批次"}, index = 35)
    private String hx1Gjf2SourceBatch;

    @ExcelProperty(value = {"候选站点2", "站点名称"}, index = 36)
    private String hx2StationName;

    @ExcelProperty(value = {"候选站点2", "站点编号"}, index = 37)
    private String hx2StationNum;

    @ExcelProperty(value = {"候选站点2", "经度"}, index = 38)
    private String hx2Longitude;

    @ExcelProperty(value = {"候选站点2", "纬度"}, index = 39)
    private String hx2Latitude;

    @ExcelProperty(value = {"候选站点2", "详细地址"}, index = 40)
    private String hx2Address;

    @ExcelProperty(value = {"候选站点2", "是否共享已有站址"}, index = 41)
    private String hx2IfShare;

    @ExcelProperty(value = {"候选站点2", "共享存量站址产权归属方"}, index = 42)
    private String hx2ShareStationBelong;

    @ExcelProperty(value = {"候选站点2", "原有运营商数(户)"}, index = 43)
    private String hx2OldOperatorNum;

    @ExcelProperty(value = {"候选站点2", "原有具体运营商"}, index = 44)
    private String hx2OldOperator;

    @ExcelProperty(value = {"候选站点2", "本次新增运营商数(户)"}, index = 45)
    private String hx2NewOperatorNum;

    @ExcelProperty(value = {"候选站点2", "新增具体运营商"}, index = 46)
    private String hx2NewOperator;

    @ExcelProperty(value = {"候选站点2", "天线挂高范围"}, index = 47)
    private String hx2AntennaHeight;

    @ExcelProperty(value = {"候选站点2", "铁塔类型"}, index = 48)
    private String hx2TowerType;

    @ExcelProperty(value = {"候选站点2", "铁塔细分种类"}, index = 49)
    private String hx2TowerTypeDetail;

    @ExcelProperty(value = {"候选站点2", "机房类型"}, index = 50)
    private String hx2RoomType;

    @ExcelProperty(value = {"候选站点2", "建设方式"}, index = 51)
    private String hx2BuildType;

    @ExcelProperty(value = {"候选站点2", "共建方1名称"}, index = 52)
    private String hx2Gjf1Name;

    @ExcelProperty(value = {"候选站点2", "共建方1需求站点名称"}, index = 53)
    private String hx2Gjf1StationName;

    @ExcelProperty(value = {"候选站点2", "共建方1需求站点编号"}, index = 54)
    private String hx2Gjf1StationNum;

    @ExcelProperty(value = {"候选站点2", "共建方1需求来源批次"}, index = 55)
    private String hx2Gjf1SourceBatch;

    @ExcelProperty(value = {"候选站点2", "共建方2名称"}, index = 56)
    private String hx2Gjf2Name;

    @ExcelProperty(value = {"候选站点2", "共建方2需求站点名称"}, index = 57)
    private String hx2Gjf2StationName;

    @ExcelProperty(value = {"候选站点2", "共建方2需求站点编号"}, index = 58)
    private String hx2Gjf2StationNum;

    @ExcelProperty(value = {"候选站点2", "共建方2需求来源批次"}, index = 59)
    private String hx2Gjf2SourceBatch;

    @ExcelProperty(value = {"站点级别", "站点级别"}, index = 60)
    private String stationLevel;

    @ExcelProperty(value = {"共享用户数", "共享用户数"}, index = 61)
    private String shareUserNum;

    @ExcelProperty(value = {"更新日期", "更新日期"}, index = 62)
    private Date updateTime;

    @ExcelProperty(value = {"工作流编号", "工作流编号"}, index = 63)
    private String actProcInstId;

    @ExcelProperty(value = {"当前状态", "当前状态"}, index = 64)
    private String actProcStatus;

    @ExcelProperty(value = {"备注", "备注"}, index = 65)
    private String remarks;

}
