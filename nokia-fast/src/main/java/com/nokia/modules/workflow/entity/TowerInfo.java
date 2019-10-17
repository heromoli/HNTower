package com.nokia.modules.workflow.entity;

import lombok.Data;

import java.io.Serializable;

//铁塔表
@Data
public class TowerInfo extends RowModel implements Serializable {
    private Long id;

    private String sourceId;

    private String towerName;

    private String stationNum;

    private String stationName;

    private String province;

    private String city;

    private String region;

    private String towerTypeDetail;

    private String towerHeight;

    private String platformMinHeight;

    private String platformSpacing;

    private float altitude;

    private String lightningRod;

    private String windPressure;

    private String towerBaseHeight;

    private String manufacturer;

    private String oldPropertyUnit;

    private String useUnit;

    private String antennaNum;

    private String realBearing;

    private float propertyNature;

    private String bizStatus;

    private String ifLocked;

    private String beautifyShieldType;

    private String maintainStatus;

    private String createPerson;

    private String createTime;

    private String modifyPerson;

    private String modifyTime;

    private String propertyAcceptType;

    private String inNetTime;

    private String towerConnectGround;

    private String remarks;

    private String propertyNum;

    private String propertySource;

    private String platformNum;

    private String usedPlatformNum;

}
