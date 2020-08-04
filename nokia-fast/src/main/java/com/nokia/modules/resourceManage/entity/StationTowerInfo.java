package com.nokia.modules.resourceManage.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nokia.modules.workflow.entity.RowModel;
import lombok.Data;

import java.io.Serializable;

//站址信息表
@Data
@TableName("STATION_TOWER_INFO")
public class StationTowerInfo extends RowModel implements Serializable {

    @TableId
    @ExcelProperty(value = {"资源ID"}, index = 0)
    private Long sourceId;

    @ExcelProperty(value = {"铁塔名称"}, index = 1)
    private String towerName;

    @ExcelProperty(value = {"站址编码"}, index = 1)
    private String stationNum;

    @ExcelProperty(value = {"站址名称"}, index = 0)
    private String stationName;

    @ExcelProperty(value = {"省"}, index = 2)
    private String province;

    @ExcelProperty(value = {"市"}, index = 3)
    private String city;

    @ExcelProperty(value = {"所属区域"}, index = 4)
    private String region;

    @ExcelProperty(value = {"铁塔细分类型"}, index = 5)
    private String towerTypeDetail;

    @ExcelProperty(value = {"塔身高度(m)"}, index = 6)
    private String towerHeight;

    @ExcelProperty(value = {"最低平台距地高度(m)"}, index = 7)
    private String groundClearance;

    @ExcelProperty(value = {"平台平均间距(m)"}, index = 8)
    private String platformSpacing;

    @ExcelProperty(value = {"海拔高度(m)"}, index = 11)
    private double altitude;

    @ExcelProperty(value = {"是否有避雷装置"}, index = 9)
    private String ifLightningRod;

    @ExcelProperty(value = {"风压系数"}, index = 10)
    private String windPressure;

    @ExcelProperty(value = {"塔基高度(m)"}, index = 11)
    private String towerBaseHeight;

    @ExcelProperty(value = {"生产厂家"}, index = 12)
    private String productVender;

    @ExcelProperty(value = {"原产权单位"}, index = 19)
    private String oldEquityUnit;

    @ExcelProperty(value = {"使用单位"}, index = 19)
    private String usedUnit;

    @ExcelProperty(value = {"设计天线数量"}, index = 21)
    private String designAntennaAmount;

    @ExcelProperty(value = {"实际承载重量(Kg)"}, index = 13)
    private String weightCapacity;

    @ExcelProperty(value = {"产权性质"}, index = 18)
    private float equityProperty;

    @ExcelProperty(value = {"业务状态"}, index = 15)
    private String bizStatus;

    @ExcelProperty(value = {"是否封锁"}, index = 14)
    private String ifLocked;

    @ExcelProperty(value = {"美化罩类型"}, index = 16)
    private String shieldType;

    @ExcelProperty(value = {"维护状态"}, index = 17)
    private String maintainStatus;

    @ExcelProperty(value = {"创建人员"}, index = 24)
    private String entryPerson;

    @JsonProperty("entryTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    @ExcelProperty(value = {"创建时间"}, index = 25)
    private String entryTime;

    @ExcelProperty(value = {"修改人"}, index = 26)
    private String modifyPerson;

    @JsonProperty("modifyTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    @ExcelProperty(value = {"修改时间"}, index = 27)
    private String modifyTime;

    @ExcelProperty(value = {"资产接收类型"}, index = 20)
    private String assetsReceiveType;

    @ExcelProperty(value = {"入网时间"}, index = 22)
    @JsonProperty("inNetTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    private String inNetTime;

    @ExcelProperty(value = {"铁塔接地"}, index = 22)
    private String towerInGround;

    @ExcelProperty(value = {"备注"}, index = 29)
    private String remarks;

    @ExcelProperty(value = {"资产编号"}, index = 30)
    private String assetsNum;

    @ExcelProperty(value = {"资产来源"}, index = 31)
    private String assetsSource;

    @ExcelProperty(value = {"平台总数"}, index = 32)
    private String platformNum;

    @ExcelProperty(value = {"占用平台数"}, index = 33)
    private String usedPlatformNum;

}

