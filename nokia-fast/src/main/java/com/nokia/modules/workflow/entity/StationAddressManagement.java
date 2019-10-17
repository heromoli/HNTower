package com.nokia.modules.workflow.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

//站址管理表
@Data
@TableName("STATION_ADDRESS_MANAGEMENT")
public class StationAddressManagement extends RowModel implements Serializable {

    @TableId
    private Long id;

    @ExcelProperty(value = {"所属省"}, index = 0)
    private String province;

    @ExcelProperty(value = {"地市"}, index = 1)
    private String city;

    @ExcelProperty(value = {"区县"}, index = 2)
    private String county;

    @ExcelProperty(value = {"国家行政区县"}, index = 3)
    private String adminCounty;

    @ExcelProperty(value = {"乡镇"}, index = 4)
    private String town;

    @ExcelProperty(value = {"乡镇代码"}, index = 5)
    private String townNum;

    @ExcelProperty(value = {"站址名称"}, index = 6)
    private String stationName;

    @ExcelProperty(value = {"站址编码"}, index = 7)
    private String stationNum;

    @ExcelProperty(value = {"运维ID"}, index = 8)
    private String operandmaintId;

    @ExcelProperty(value = {"经度(小数点后6位)"}, index = 9)
    private String longitude;

    @ExcelProperty(value = {"纬度(小数点后6位)"}, index = 10)
    private String latitude;

    @ExcelProperty(value = {"海拔高度"}, index = 11)
    private String altitude;

    @ExcelProperty(value = {"维护状态"}, index = 12)
    private String maintainStatus;

    @ExcelProperty(value = {"站址状态"}, index = 13)
    private String stationStatus;

    @ExcelProperty(value = {"FSU交维时间"}, index = 14)
    private String fsuDeliveryTime;

    @ExcelProperty(value = {"地址"}, index = 15)
    private String address;

    @ExcelProperty(value = {"站址来源"}, index = 16)
    private String stationSource;

    @ExcelProperty(value = {"运营商共享情况"}, index = 17)
    private String ifOperatorShare;

    @ExcelProperty(value = {"业务场景"}, index = 18)
    private String bizScene;

    @ExcelProperty(value = {"移动公司站型"}, index = 19)
    private String mobileStationType;

    @ExcelProperty(value = {"移动公司站址维护对象"}, index = 20)
    private String mobileStationMaintain;

    @ExcelProperty(value = {"移动公司设备制式"}, index = 21)
    private String mobileDeviceSystem;

    @ExcelProperty(value = {"移动公司远供站上联站点名称"}, index = 22)
    private String mobileAssocStationName;

    @ExcelProperty(value = {"移动公司远供站上联机房是否交接"}, index = 23)
    private String mobileAssocRoomHandover;

    @ExcelProperty(value = {"移动公司内部站址等级"}, index = 24)
    private String mobileInsideStationLv;

    @ExcelProperty(value = {"移动公司认可的免责时段"}, index = 25)
    private String mobileDisclaimerTime;

    @ExcelProperty(value = {"站址别名-移动"}, index = 26)
    private String mobileStationAlias;

    @ExcelProperty(value = {"移动公司维护服务等级"}, index = 27)
    private String mobileMaintainLv;

    @ExcelProperty(value = {"移动维护服务等级（手工）"}, index = 28)
    private String mobileMaintainLvHand;

    @ExcelProperty(value = {"移动公司是否购买发电服务"}, index = 29)
    private String mobileBuyElectricService;

    @ExcelProperty(value = {"联通公司站型"}, index = 30)
    private String unicomStationType;

    @ExcelProperty(value = {"联通公司站址维护对象"}, index = 31)
    private String unicomStationMaintain;

    @ExcelProperty(value = {"联通公司设备制式"}, index = 32)
    private String unicomDeviceSystem;

    @ExcelProperty(value = {"联通公司远供站上联站点名称"}, index = 33)
    private String unicomAssocStationName;

    @ExcelProperty(value = {"联通公司远供站上联机房是否交接"}, index = 34)
    private String unicomAssocRoomHandover;

    @ExcelProperty(value = {"联通公司内部基站等级"}, index = 35)
    private String unicomInsideStationLv;

    @ExcelProperty(value = {"联通公司认可的免责时段"}, index = 36)
    private String unicomDisclaimerTime;

    @ExcelProperty(value = {"站址别名-联通"}, index = 37)
    private String unicomStationAlias;

    @ExcelProperty(value = {"联通公司维护服务等级"}, index = 38)
    private String unicomMaintainLv;

    @ExcelProperty(value = {"联通维护服务等级（手工）"}, index = 39)
    private String unicomMaintainLvHand;

    @ExcelProperty(value = {"联通公司是否购买发电服务"}, index = 40)
    private String unicomBuyElectricService;

    @ExcelProperty(value = {"电信公司站型"}, index = 41)
    private String telecomStationType;

    @ExcelProperty(value = {"电信公司站址维护对象"}, index = 42)
    private String telecomStationMaintain;

    @ExcelProperty(value = {"电信公司设备制式"}, index = 43)
    private String telecomDeviceSystem;

    @ExcelProperty(value = {"电信公司远供站上联站点名称"}, index = 44)
    private String telecomAssocStationName;

    @ExcelProperty(value = {"电信公司远供站上联机房是否交接"}, index = 45)
    private String telecomAssocRoomHandover;

    @ExcelProperty(value = {"电信公司内部基站等级"}, index = 46)
    private String telecomInsideStationLv;

    @ExcelProperty(value = {"电信公司认可的免责时段"}, index = 47)
    private String telecomDisclaimerTime;

    @ExcelProperty(value = {"站址别名-电信"}, index = 48)
    private String telecomStationAlias;

    @ExcelProperty(value = {"电信公司维护服务等级"}, index = 49)
    private String telecomMaintainLv;

    @ExcelProperty(value = {"电信维护服务等级（手工）"}, index = 50)
    private String telecomMaintainLvHand;

    @ExcelProperty(value = {"电信公司是否购买发电服务"}, index = 51)
    private String telecomBuyElectricService;

    @ExcelProperty(value = {"所属管理区域"}, index = 52)
    private String managementArea;

    @ExcelProperty(value = {"区域经理"}, index = 53)
    private String regionalManager;

    @ExcelProperty(value = {"区域经理联系电话"}, index = 54)
    private String regionalManagerPhoneNum;

    @ExcelProperty(value = {"机房(动环)维护人员"}, index = 55)
    private String roomMaintainer;

    @ExcelProperty(value = {"机房(动环)维护人员联系电话"}, index = 56)
    private String roomMaintainerPhoneNum;

    @ExcelProperty(value = {"铁塔维护人员"}, index = 57)
    private String towerMaintainer;

    @ExcelProperty(value = {"铁塔维护人员联系电话"}, index = 58)
    private String towerMaintainerPhoneNum;

    @ExcelProperty(value = {"运营商名称(*)"}, index = 59)
    private String operatorName;

    @ExcelProperty(value = {"移动维护人员"}, index = 60)
    private String mobileMaintainer;

    @ExcelProperty(value = {"联通维护人员"}, index = 61)
    private String unicomMaintainer;

    @ExcelProperty(value = {"电信维护人员"}, index = 62)
    private String telecomMaintainer;

    @ExcelProperty(value = {"物业属性"}, index = 63)
    private String propertyType;

    @ExcelProperty(value = {"上站难易程度"}, index = 64)
    private String shangzhanDifficlLv;

    @ExcelProperty(value = {"上站联系人"}, index = 65)
    private String shangzhanContact;

    @ExcelProperty(value = {"上站联系电话"}, index = 66)
    private String shangzhanContactPhone;

    @ExcelProperty(value = {"0-6点是否可以上站"}, index = 67)
    private String if06Shangzhan;

    @ExcelProperty(value = {"上站困难原型(详细说明)"}, index = 68)
    private String shangzhanDifficlExplan;

    @ExcelProperty(value = {"供电方式（一级）"}, index = 69)
    private String electricType1;

    @ExcelProperty(value = {"供电方式（二级）"}, index = 70)
    private String electricType2;

    @ExcelProperty(value = {"电表位置"}, index = 71)
    private String ammeterPosition;

    @ExcelProperty(value = {"所属电力公变名称"}, index = 72)
    private String elecTransformerName;

    @ExcelProperty(value = {"所属电力线路名称"}, index = 73)
    private String elecCircuitName;

    @ExcelProperty(value = {"供电部门联系电话"}, index = 74)
    private String elecDepartPhone;

    @ExcelProperty(value = {"机房是否交接"}, index = 75)
    private String ifRoomHandover;

    @ExcelProperty(value = {"是否安装运营商主设备"}, index = 76)
    private String ifInstallOperatorEquip;

    @ExcelProperty(value = {"产权性质"}, index = 77)
    private String propertyNature;

    @ExcelProperty(value = {"门锁类型"}, index = 78)
    private String lockType;

    @ExcelProperty(value = {"是否具备动环监控安装条件"}, index = 79)
    private String ifMonitorInstall;

    @ExcelProperty(value = {"FSU厂家"}, index = 80)
    private String fsuVender;

    @ExcelProperty(value = {"是否具备发电条件"}, index = 81)
    private String ifCanElec;

    @ExcelProperty(value = {"机房类型"}, index = 82)
    private String roomType;

    @ExcelProperty(value = {"从驻点上站时长(分钟)"}, index = 83)
    private String pointToStationDuration;

    @ExcelProperty(value = {"0-6点是否上站发电"}, index = 84)
    private String if06GeneElec;

    @ExcelProperty(value = {"发电所需油机功率(KW)"}, index = 85)
    private String elecGeneratorPower;

    @ExcelProperty(value = {"蓄电池基础保障时间(小时)"}, index = 86)
    private String batterySafeDuration;

    @ExcelProperty(value = {"蓄电池保障额外保障时间(小时)"}, index = 87)
    private String batteryExtraSafeDuration;

    @ExcelProperty(value = {"发电电压门限值"}, index = 88)
    private String geneElecVoltage;

    @ExcelProperty(value = {"蓄电池续航时间(分钟)"}, index = 89)
    private String batteryUseDuration;

    @ExcelProperty(value = {"发电等待时长"}, index = 90)
    private String waitForGeneElecDuration;

    @ExcelProperty(value = {"发电接口位置"}, index = 91)
    private String geneElecPortSite;

    @ExcelProperty(value = {"是否固定油机"}, index = 92)
    private String ifFixGasMachine;

    @ExcelProperty(value = {"具体无法发电原因"}, index = 93)
    private String noGeneElecReason;

    @ExcelProperty(value = {"无法发电其他原因说明"}, index = 94)
    private String otherNoGeneElecReason;

    @ExcelProperty(value = {"是否为免责站址"}, index = 95)
    private String ifNoResponStation;

    @ExcelProperty(value = {"站点全免责维护环境恶劣因素"}, index = 96)
    private String stationEnvirFactor;

    @ExcelProperty(value = {"是否夜间免责站点"}, index = 97)
    private String ifNightNoResponStation;

    @ExcelProperty(value = {"夜间免责时间段"}, index = 98)
    private String NightNoResponTime;

    @ExcelProperty(value = {"供应商名称"}, index = 99)
    private String supplierName;

    @ExcelProperty(value = {"对方主体编号"}, index = 100)
    private String subjectNum;

    @ExcelProperty(value = {"地理系数(陕西必填)"}, index = 101)
    private String geographyCoef;

    @ExcelProperty(value = {"特殊区域类型(广西必填)"}, index = 102)
    private String specialAreaType;

    @ExcelProperty(value = {"分摊比例-移动(山西必填)"}, index = 103)
    private String mobileAmortRatio;

    @ExcelProperty(value = {"分摊比例-联通(山西必填)"}, index = 104)
    private String unicomAmortRatio;

    @ExcelProperty(value = {"分摊比例-电信(山西必填)"}, index = 105)
    private String telecomAmortRatio;

    @ExcelProperty(value = {"是否安装多个FSU"}, index = 106)
    private String ifInstallMultiFsu;

    @ExcelProperty(value = {"站型"}, index = 107)
    private String stationType;

    @ExcelProperty(value = {"点位编码"}, index = 108)
    private String pointLocationNum;

    @ExcelProperty(value = {"验收交付日期"}, index = 109)
    private String deliveryTime;

    @ExcelProperty(value = {"站址起租时间(移动)"}, index = 110)
    private String mobileStartHireTime;

    @ExcelProperty(value = {"站址起租时间(联通)"}, index = 111)
    private String unicomStartHireTime;

    @ExcelProperty(value = {"站址起租时间(电信)"}, index = 112)
    private String telecomStartHireTime;

    @ExcelProperty(value = {"油机发电收费方式"}, index = 113)
    private String gasMachineChargeTime;

    @ExcelProperty(value = {"FSU建设方式"}, index = 114)
    private String fsuBuildType;

    @ExcelProperty(value = {"后备电源组数"}, index = 115)
    private String backUpPowerNum;

    @ExcelProperty(value = {"室分站点RRU数量"}, index = 116)
    private String rruNum;

    @ExcelProperty(value = {"行业外客户"}, index = 117)
    private String customer;

    @ExcelProperty(value = {"室分站址规模"}, index = 118)
    private String roomStationScale;

    @ExcelProperty(value = {"一次下电时长"}, index = 119)
    private String offElecDuration;

    @ExcelProperty(value = {"站址保障等级"}, index = 120)
    private String stationSafeLv;

    @ExcelProperty(value = {"备注"}, index = 121)
    private String remarks;

}
