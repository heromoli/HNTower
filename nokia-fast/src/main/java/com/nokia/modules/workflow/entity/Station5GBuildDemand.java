package com.nokia.modules.workflow.entity;

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

    @TableId
    private Long id;

    private String demandNum;

    private String demandSource;

    private String branchCompany;

    private String county;

    private String region;

    private String stationName;

    private String scene;

    private String stationLevel;

    private String longitude;

    private String latitude;

    private String address;

    private String buildType;

    private String buildingOrGround;

    @JsonProperty("locationFinishTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date locationFinishTime;

    @JsonProperty("intoTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date intoTime;

    @JsonProperty("totalFinishTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date totalFinishTime;

    private String projectStateDescribe;

    private String orderNum;

    private String operatorName;

    private String remarks;

    private Date pmsFinishTime;

    private Date requireDeliveryTime;

    private Date deliveryTime;


}
