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
@TableName("supervisor")
public class Supervision extends RowModel implements Serializable {

    @TableId
    private Long id;

    @ExcelProperty(index = 1, value = {"需求编号"})
    private String demandNum;

    @ExcelProperty(index = 2, value = {"需求来源"})
    private String demandSource;

    @ExcelProperty(index = 3, value = {"分公司"})
    private String branchCompany;

    @ExcelProperty(index = 4, value = {"市县"})
    private String county;

    @ExcelProperty(index = 5, value = {"区域"})
    private String region;

    @ExcelProperty(index = 6, value = {"站点名称"})
    private String stationName;

    @ExcelProperty(index = 7, value = {"站址编号"})
    private String stationNum;

    @ExcelProperty(index = 8, value = {"机房编号"})
    private String roomNum;

    @ExcelProperty(index = 9, value = {"订单编号"})
    private String orderNum;

    @ExcelProperty(index = 10, value = {"运营商站点名称"})
    private String operatorStationName;

    @ExcelProperty(index = 13, value = {"运营商编号"})
    private String OperatorNum;

    @ExcelProperty(index = 14, value = {"需求提出时间"})
    @JsonProperty("demandProposeTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date demandProposeTime;

    @ExcelProperty(index = 15, value = {"交付时间"})
    @JsonProperty("deliveryTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deliveryTime;

    @ExcelProperty(index = 16, value = {"场景划分"})
    private String scene;

    @ExcelProperty(index = 17, value = {"经度"})
    private double longitude;

    @ExcelProperty(index = 18, value = {"纬度"})
    private double latitude;

    @ExcelProperty(index = 19, value = {"详细地址"})
    private String address;

    @ExcelProperty(index = 20, value = {"建设方式"})
    private String buildType;

    @ExcelProperty(index = 21, value = {"楼面地面"})
    private String buildingOrGround;

    @ExcelProperty(index = 22, value = {"共享模式"})
    private String ifShare;

    @ExcelProperty(index = 23, value = {"移动建设模式"})
    private String mobileBuildType;

    @ExcelProperty(index = 24, value = {"征址完成时间"})
    @JsonProperty("locationFinishTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date locationFinishTime;

    @ExcelProperty(index = 25, value = {"进场时间"})
    @JsonProperty("intoTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date intoTime;

    @ExcelProperty(index = 26, value = {"土建完成时间"})
    @JsonProperty("buildFinishTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date buildFinishTime;

    @ExcelProperty(index = 27, value = {"铁塔完成时间"})
    @JsonProperty("towerFinishTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date towerFinishTime;

    @ExcelProperty(index = 28, value = {"引电完成时间"})
    @JsonProperty("electricFinishTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date electricFinishTime;

    @ExcelProperty(index = 29, value = {"配套完成时间"})
    @JsonProperty("matchingFinishTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date matchingFinishTime;

    @ExcelProperty(index = 30, value = {"总体完工时间"})
    @JsonProperty("totalFinishTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date totalFinishTime;

    @ExcelProperty(index = 31, value = {"内验完成时间"})
    @JsonProperty("checkFinishTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date checkFinishTime;

    @ExcelProperty(index = 33, value = {"起租时间"})
    @JsonProperty("startHireTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startHireTime;

    @ExcelProperty(index = 34, value = {"工程状态分类"})
    private String projectState;

    @ExcelProperty(index = 35, value = {"工程状态描述"})
    private String projectStateDescribe;

    @ExcelProperty(index = 36, value = {"任务年份"})
    private String projectYear;

    @ExcelProperty(index = 37, value = {"是否政府协调"})
    private String ifGovernment;

    private String importantStation;

    private String oldOperatorNum;

    private String newOperatorNum;

    @JsonProperty("buildingTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date buildingTime;

    private String crmStationNum;

    private String crmRoomNum;

    private String crmOrderNum;
}
