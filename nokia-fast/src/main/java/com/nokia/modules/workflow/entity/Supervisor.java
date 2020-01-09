package com.nokia.modules.workflow.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("supervisor")
public class Supervisor extends RowModel implements Serializable {

    @ExcelProperty(index = 0, value = {"序号"})
    private Long id;

    private String actProcInstId;

    private String actProcStatus;

    private String operatorName;

    private String batchNum;

    @TableId
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

    @ExcelProperty(index = 11, value = {"专项站点"})
    private String specialStation;

    @ExcelProperty(index = 12, value = {"运营商编号"})
    private String operatorNum;

    @ExcelProperty(index = 13, value = {"运营商修正编号"})
    private String operatorNumCorrect;

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

    private String stationLevel;

    private String coverRangeDescribe;

    private String deviateRadius;

    private String towerType;

    private String towerTypeDetail;

    @ExcelProperty(index = 20, value = {"建设方式"})
    private String buildType;

    @ExcelProperty(index = 21, value = {"楼面地面"})
    private String buildingOrGround;

    @ExcelProperty(index = 22, value = {"共享共建模式"})
    private String ifShare;

    @ExcelProperty(index = 23, value = {"移动建设模式"})
    private String mobileBuildType;

    private String shareStationBelong;

    private String oldOperatorNum;

    private String oldOperator;

    private String newOperatorNum;

    private String newOperator;

    private String shareType;

    private String debuggingTimeLimit;

    private String productConfig;

    private String towerHeight;

    private String platformNum;

    private String roomType;

    private String roomSize;

    private String seatNum;

    private String systemNum;

    private String bbuNum;

    private String bbuSize;

    private String bbuWeight;

    private String bbuPower;

    private String rruNum;

    private String rruSize;

    private String rruWeight;

    private String rruSupplyType;

    private String rruPower;

    private String ifBbu;

    private String windPressure;   //风压系数

    private String electricType;   //电力引入方式

    private String fullCapacity;

    private String thisCapacity;

    private String batteryCapacity;

    private String batteryHour;

    private String transEquitSize;

    private String transEquitWeight;

    private String transEquitFullPower;

    private String antennaHeight;

    private String antennaAngle;

    private String antennaNum;

    private String antennaSize;

    private String antennaWeight;

    private String serviceLevel;

    private String payType;

    private String ifCanOrder;

    private String cantOrderReason;

    private String hx1StationName;

    private String hx1StationNum;

    private String hx1Longitude;

    private String hx1Latitude;

    private String hx1Address;

    private String hx1IfShare;

    private String hx1ShareStationBelong;

    private String hx1OldOperatorNum;

    private String hx1OldOperator;

    private String hx1NewOperatorNum;

    private String hx1NewOperator;

    private String hx1AntennaHeight;

    private String hx1TowerType;

    private String hx1TowerTypeDetail;

    private String hx1RoomType;

    private String hx1BuildType;

    private String hx1Gjf1Name;

    private String hx1Gjf1StationName;

    private String hx1Gjf1StationNum;

    private String hx1Gjf1SourceBatch;

    private String hx1Gjf2Name;

    private String hx1Gjf2StationName;

    private String hx1Gjf2StationNum;

    private String hx1Gjf2SourceBatch;

    private String hx2StationName;

    private String hx2StationNum;

    private String hx2Longitude;

    private String hx2Latitude;

    private String hx2Address;

    private String hx2IfShare;

    private String hx2ShareStationBelong;

    private String hx2OldOperatorNum;

    private String hx2OldOperator;

    private String hx2NewOperatorNum;

    private String hx2NewOperator;

    private String hx2AntennaHeight;

    private String hx2TowerType;

    private String hx2TowerTypeDetail;

    private String hx2RoomType;

    private String hx2BuildType;

    private String hx2Gjf1Name;

    private String hx2Gjf1StationName;

    private String hx2Gjf1StationNum;

    private String hx2Gjf1SourceBatch;

    private String hx2Gjf2Name;

    private String hx2Gjf2StationName;

    private String hx2Gjf2StationNum;

    private String hx2Gjf2SourceBatch;

    private String shareUserNum;

    private Date updateTime;

    //    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
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

    @ExcelProperty(index = 32, value = {"交付完成时间"})
    @JsonProperty("deliverFinishTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deliverFinishTime;

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

    @ExcelProperty(index = 37, value = {"是否报政府协调"})
    private String ifGovernment;

    private String contact;

    private String remarks;

    @TableField(exist = false)
    private String approve;

    @TableField(exist = false)
    private String groupId;


}
