package com.nokia.modules.resourceManage.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nokia.modules.workflow.entity.RowModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//站址信息表
@Data
@TableName("STATION_ADDRESS_INFO_NEW")
public class StationAddressInfoNew extends RowModel implements Serializable {

    @ExcelProperty(value = {"站址名称"}, index = 0)
    private String stationName;

    @ExcelProperty(value = {"站址编码"}, index = 1)
    private String stationNum;

    @ExcelProperty(value = {"省"}, index = 2)
    private String province;

    @ExcelProperty(value = {"市"}, index = 3)
    private String city;

    @ExcelProperty(value = {"所属区域"}, index = 4)
    private String region;

    @ExcelProperty(value = {"行政区域"}, index = 5)
    private String adminRegion;

    @ExcelProperty(value = {"所属组织"}, index = 6)
    private String organization;

    @ExcelProperty(value = {"站址类型"}, index = 7)
    private String stationType;

    @ExcelProperty(value = {"经度"}, index = 8)
    private String longitude;

    @ExcelProperty(value = {"纬度"}, index = 9)
    private String latitude;

    @ExcelProperty(value = {"所在地址"}, index = 10)
    private String address;

    @ExcelProperty(value = {"站址地形"}, index = 11)
    private String stationTerrain;

    @ExcelProperty(value = {"入网日期"}, index = 12)
    private Date inNetDate;

    @ExcelProperty(value = {"原产权单位"}, index = 13)
    private String oldEquityUnit;

    @ExcelProperty(value = {"产权性质"}, index = 14)
    private float equityProperty;

    @ExcelProperty(value = {"站址状态"}, index = 15)
    private String addressStatus;

    @ExcelProperty(value = {"备注"}, index = 16)
    private String remarks;

    @ExcelProperty(value = {"修改时间"}, index = 17)
    private String modifyTime;

    @ExcelProperty(value = {"录入人员"}, index = 18)
    private String entryPerson;

    @ExcelProperty(value = {"录入时间"}, index = 19)
    private String entryTime;

    @ExcelProperty(value = {"修改人"}, index = 20)
    private String modifyPerson;

    @ExcelProperty(value = {"是否封锁"}, index = 21)
    private String ifLocked;

    @ExcelProperty(value = {"站址来源"}, index = 22)
    private String stationSource;

    @ExcelProperty(value = {"来源行业"}, index = 23)
    private String sourceIndustry;

    @ExcelProperty(value = {"站址合并记录"}, index = 24)
    private String stationMergeRecord;

    @ExcelProperty(value = {"站址接收标记"}, index = 25)
    private String stationReceptionSign;

    @ExcelProperty(value = {"删除时间"}, index = 26)
    private String deletedTime;

    @ExcelProperty(value = {"原移动站址名称"}, index = 27)
    private String oldMobileName;

    @ExcelProperty(value = {"原联通站址名称"}, index = 28)
    private String oldUnicomeName;

    @ExcelProperty(value = {"原电信站址名称"}, index = 29)
    private String oldTelecomName;

    @ExcelProperty(value = {"是否交塔"}, index = 30)
    private String ifDeliveryTower;

    @ExcelProperty(value = {"是否交房"}, index = 31)
    private String ifDeliveryRoom;

    @ExcelProperty(value = {"注销原因"}, index = 32)
    private String cancelReason;

    @ExcelProperty(value = {"锚定业务"}, index = 33)
    private String anchorBiz;

    @ExcelProperty(value = {"一级业务场景"}, index = 34)
    private String lv1BizScene;

    @ExcelProperty(value = {"二级业务场景"}, index = 35)
    private String lv2BizScene;

    @ExcelProperty(value = {"业务子类"}, index = 36)
    private String bizSubtype;

    @ExcelProperty(value = {"共享单位"}, index = 37)
    private String sharedUnit;

    @ExcelProperty(value = {"基站类别"}, index = 38)
    private String baseStationType;

    @ExcelProperty(value = {"业务类型"}, index = 39)
    private String bizType;

    @ExcelProperty(value = {"实际拆除日期"}, index = 40)
    private Date demolitionTime;


//    @ExcelProperty(value = {"维护等级"}, index = 11)
//    private String maintainLv;
//
//    @ExcelProperty(value = {"维护单位"}, index = 14)
//    private String maintainUnit;
//
//    @ExcelProperty(value = {"业务场景"}, index = 15)
//    private String bizScene;
//
//    @ExcelProperty(value = {"场景划分"}, index = 17)
//    private String sceneDivide;
//
//    @ExcelProperty(value = {"是否共享"}, index = 20)
//    private String ifOperatorShare;
//
//    @ExcelProperty(value = {"使用单位"}, index = 21)
//    private String useUnit;
//
//    @ExcelProperty(value = {"物理站址编码"}, index = 22)
//    private String physicsStationNum;
//
//    @ExcelProperty(value = {"是否起租"}, index = 23)
//    private String ifHired;
//
//    @ExcelProperty(value = {"站址是否具备发电条件"}, index = 30)
//    private String ifStationCanElec;
//
//    @ExcelProperty(value = {"室分站点RRU数量"}, index = 31)
//    private String stationRruNum;
//
//    @ExcelProperty(value = {"要求夜间上站"}, index = 32)
//    private String ifNightUpStation;
//
//    @ExcelProperty(value = {"发电等待时长"}, index = 33)
//    private String waitForElecDuration;
//
//    @ExcelProperty(value = {"发电电压门限值"}, index = 34)
//    private String voltageThreshold;
//
//    @ExcelProperty(value = {"基本备电约定"}, index = 35)
//    private String baseBackupElec;
//
//    @ExcelProperty(value = {"固定油机配备"}, index = 36)
//    private String fixedGasMachine;
//
//    @ExcelProperty(value = {"蓄电池额外保障服务"}, index = 37)
//    private String batteryExtraSafe;
//
//    @ExcelProperty(value = {"是否有动环监控"}, index = 38)
//    private String ifEnvironmentMonitor;
//
//    @ExcelProperty(value = {"是否公共库位"}, index = 39)
//    private String ifPublicSite;
//
//    @ExcelProperty(value = {"站址标签"}, index = 40)
//    private String stationTag;
//
//    @ExcelProperty(value = {"是否能源站"}, index = 41)
//    private String ifEnergyStation;
}

