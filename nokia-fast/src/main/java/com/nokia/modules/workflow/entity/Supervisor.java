package com.nokia.modules.workflow.entity;

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

    @TableId
    private Long id;

    private String actProcInstId;

    private String actProcStatus;

    private String operatorName;

    private String operatorNum;

    private String batchNum;

    private String demandNum;

    private String demandSource;

    private String orderNum;

    private String branchCompany;

    private String county;

    private String region;

    private String stationName;

    private String stationNum;

    private String scene;

    private String stationLevel;

    private String roomNum;

    private String operatorStationName;

    private String specialStation;

    @JsonProperty("demandProposeTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date demandProposeTime;

    private String longitude;

    private String latitude;

    private String address;

    private String coverRangeDescribe;

    private String deviateRadius;

    private String towerType;

    private String towerTypeDetail;

    private String buildType;

    private String buildingOrGround;

    @JsonProperty("deliveryTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date deliveryTime;

    private String ifShare;

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
    @JsonProperty("locationFinishTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date locationFinishTime;

    @JsonProperty("intoTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date intoTime;

    @JsonProperty("buildFinishTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date buildFinishTime;

    @JsonProperty("towerFinishTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date towerFinishTime;

    @JsonProperty("electricFinishTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date electricFinishTime;

    @JsonProperty("matchingFinishTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date matchingFinishTime;

    @JsonProperty("totalFinishTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date totalFinishTime;

    @JsonProperty("checkFinishTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date checkFinishTime;

    @JsonProperty("deliverFinishTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date deliverFinishTime;

    @JsonProperty("startHireTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startHireTime;

    private String projectState;

    private String projectStateDescribe;

    private String contact;

    private String remarks;

    @TableField(exist=false)
    private String approve;

    @TableField(exist=false)
    private String groupId;


}
