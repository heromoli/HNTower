package com.nokia.modules.workflow.entity;

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

    private String demandNum;

    private String demandSource;

    private String branchCompany;

    private String county;

    private String stationName;

    private String operatorStationName;

    private String scene;

    private String longitude;

    private String latitude;

    private String address;

    private String buildType;

    private String buildingOrGround;

    private String ifShare;

    private String mobileBuildType;

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

    private String projectState;

    private String projectStateDescribe;

    @JsonProperty("buildingTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date buildingTime;

    private String crmStationNum;

    private String crmRoomNum;

    private String crmOrderNum;

    private String projectYear;

    private String importantStation;

    private String oldOperatorNum;

    private String newOperatorNum;

    @JsonProperty("demandProposeTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date demandProposeTime;

    @JsonProperty("testFinishTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date testFinishTime;

    @JsonProperty("deliveryTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date deliveryTime;

    @JsonProperty("startHireTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startHireTime;

    private String ifGovernment;

}
