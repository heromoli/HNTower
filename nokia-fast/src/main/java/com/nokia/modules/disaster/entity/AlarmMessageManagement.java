package com.nokia.modules.disaster.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nokia.modules.workflow.entity.RowModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("V_ALARM_MESSAGE_MANAGEMENT")
public class AlarmMessageManagement extends RowModel implements Serializable {

    @ExcelProperty(index = 0, value = {"故障单编号"})
    private String alarmTicketNumber;

    @ExcelProperty(index = 1, value = {"告警等级"})
    private String alarmLevel;

    @ExcelProperty(index = 2, value = {"省"})
    private String province;

    @ExcelProperty(index = 3, value = {"市"})
    private String city;

    @ExcelProperty(index = 4, value = {"区县"})
    private String county;

//    @ExcelProperty(index = 5, value = {"国家行政区县"})
//    private String adminCounty;
//
//    @ExcelProperty(index = 6, value = {"乡镇代码"})
//    private String townCode;
//
//    @ExcelProperty(index = 7, value = {"站型"})
//    private String stationType;
//
//    @ExcelProperty(index = 8, value = {"移动"})
//    private String mobile;
//
//    @ExcelProperty(index = 9, value = {"联通"})
//    private String unicom;
//
//    @ExcelProperty(index = 10, value = {"电信"})
//    private String telecom;

    @ExcelProperty(index = 11, value = {"站址保障等级"})
    private String stationSecurityLevel;

    @ExcelProperty(index = 12, value = {"站址名称"})
    private String stationName;

    @ExcelProperty(index = 13, value = {"站址状态"})
    private String stationStatus;

//    @ExcelProperty(index = 14, value = {"站址运维ID"})
//    private String stationOperationId;

    @ExcelProperty(index = 15, value = {"站址编码"})
    private String stationCode;

    @ExcelProperty(index = 16, value = {"告警名称"})
    private String alarmName;

//    @ExcelProperty(index = 17, value = {"FSU名称"})
//    private String fsuName;
//
//    @ExcelProperty(index = 18, value = {"FSU运维ID"})
//    private String fsuOperationId;
//
//    @ExcelProperty(index = 19, value = {"FSU厂家"})
//    private String fsuManufacturer;
//
//    @ExcelProperty(index = 20, value = {"子设备名称"})
//    private String subsetName;
//
//    @ExcelProperty(index = 21, value = {"信号量ID"})
//    private String signalId;

    @ExcelProperty(index = 22, value = {"告警详情"})
    private String alarmDetail;

    @ExcelProperty(index = 23, value = {"告警发生时间"})
    @JsonProperty("alarmOccurTime")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date alarmOccurTime;

    @ExcelProperty(index = 24, value = {"告警历时(分钟)"})
    private String alarmDurationTime;

    @ExcelProperty(index = 25, value = {"是否超时"})
    private String ifTimeout;

    @ExcelProperty(index = 26, value = {"设备告警开始时间"})
    @JsonProperty("alarmStartTime")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date alarmStartTime;

    @ExcelProperty(index = 27, value = {"设备告警结束时间"})
    @JsonProperty("alarmEndTime")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date alarmEndTime;

//    @ExcelProperty(index = 28, value = {"告警次数"})
//    private String alarmNum;
//
//    @ExcelProperty(index = 29, value = {"告警来源"})
//    private String alarmSource;
//
//    @ExcelProperty(index = 30, value = {"告警类别"})
//    private String alarmType;
//
//    @ExcelProperty(index = 31, value = {"告警对象类型"})
//    private String alarmObjectType;
//
//    @ExcelProperty(index = 32, value = {"告警原因"})
//    private String alarmReason;
//
//    @ExcelProperty(index = 33, value = {"告警摘要"})
//    private String alarmAbstract;
//
//    @ExcelProperty(index = 34, value = {"告警附加信息"})
//    private String alarmAdditionalInfo;
//
//    @ExcelProperty(index = 35, value = {"告警入库时间"})
//    @JsonProperty("alarmInstorageTime")
//    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
//    private Date alarmInstorageTime;
//
//    @ExcelProperty(index = 36, value = {"是否已确认"})
//    private String ifConfirm;
//
//    @ExcelProperty(index = 37, value = {"确认时间"})
//    @JsonProperty("confirmTime")
//    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
//    private Date confirmTime;
//
//    @ExcelProperty(index = 38, value = {"确认人"})
//    private String confirmPerson;
//
//    @ExcelProperty(index = 39, value = {"确认原因"})
//    private String confirmReason;
//
//    @ExcelProperty(index = 40, value = {"用户备注信息"})
//    private String userNoteInfo;
//
//    @ExcelProperty(index = 41, value = {"是否已恢复"})
//    private String ifAlarmRecover;
//
//    @ExcelProperty(index = 42, value = {"是否转故障"})
//    private String ifAlarmToFault;
//
//    @ExcelProperty(index = 43, value = {"告警说明"})
//    private String alarmExplain;
//
//    @ExcelProperty(index = 44, value = {"故障现象描述"})
//    private String faultDescribe;
//
//    @ExcelProperty(index = 45, value = {"站址来源"})
//    private String stationSource;
//
//    @ExcelProperty(index = 46, value = {"代维公司"})
//    private String maintainCompany;
//
//    @ExcelProperty(index = 47, value = {"告警流水号ID"})
//    private String alarmSerialNumber;
//
    @ExcelProperty(index = 48, value = {"所属运营商"})
    private String belongOperator;
//
//    @ExcelProperty(index = 49, value = {"移动站址名称"})
//    private String mobileStationName;
//
//    @ExcelProperty(index = 50, value = {"移动站址编码"})
//    private String mobileStationCode;
//
//    @ExcelProperty(index = 51, value = {"联通站址名称"})
//    private String uincomStationName;
//
//    @ExcelProperty(index = 52, value = {"联通站址编码"})
//    private String uincomStationCode;
//
//    @ExcelProperty(index = 53, value = {"电信站址名称"})
//    private String telecomStationName;
//
//    @ExcelProperty(index = 54, value = {"电信站址编码"})
//    private String telecomStationCode;
//
//    @ExcelProperty(index = 55, value = {"告警站址蓄电池续航时间"})
//    private String batteryDurationTime;

//    @ExcelProperty(index = 56, value = {"经度"})
    private double longitude;

//    @ExcelProperty(index = 57, value = {"纬度"})
    private double latitude;

//    @ExcelProperty(index = 58, value = {"场景"})
    private String bizScene;

}
