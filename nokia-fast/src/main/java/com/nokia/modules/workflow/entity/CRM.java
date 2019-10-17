package com.nokia.modules.workflow.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("CRM")
public class CRM extends RowModel implements Serializable {

    @TableId
    private Long id;

    @ExcelProperty(value = {"订单状态", ""}, index = 1)
    private String orderStatus;
    @ExcelProperty(value = {"订单号", ""}, index = 2)
    private String orderNum;
    @ExcelProperty(value = {"运营商", ""}, index = 3)
    private String operatorName;
    @ExcelProperty(value = {"省份", ""}, index = 4)
    private String province;
    @ExcelProperty(value = {"地市", ""}, index = 5)
    private String city;
    @ExcelProperty(value = {"区县", ""}, index = 6)
    private String region;
    @ExcelProperty(value = {"是否属于高铁或地铁项目", ""}, index = 7)
    private String ifRailwayProject;
    @ExcelProperty(value = {"运营商站址名称", ""}, index = 9)
    private String operatorStationName;
    @ExcelProperty(value = {"铁塔站址名称", ""}, index = 10)
    private String towerStationName;
    @ExcelProperty(value = {"站址编码", ""}, index = 11)
    private String stationNum;
    @ExcelProperty(value = {"运营商自有物理站址编码", ""}, index = 12)
    private String operatorPhysStanNum;
    @ExcelProperty(value = {"合并需求单号", ""}, index = 13)
    private String mergeDemandNum;
    @ExcelProperty(value = {"订单属性", ""}, index = 14)
    private String orderPro;
    @ExcelProperty(value = {"需求批次", ""}, index = 15)
    private String demandBatch;
    @ExcelProperty(value = {"项目建设状态", ""}, index = 16)
    private String projBuildStatus;
    @ExcelProperty(value = {"场景划分", ""}, index = 17)
    private String scene;
    @ExcelProperty(value = {"建设方式", ""}, index = 18)
    private String buildType;
    @ExcelProperty(value = {"产权属性", ""}, index = 19)
    private String equityPro;
    @ExcelProperty(value = {"(原)产权单位", ""}, index = 20)
    private String equityOperator;
    @ExcelProperty(value = {"维护服务等级", ""}, index = 21)
    private String maintainServiceLv;
    @ExcelProperty(value = {"0-6点是否上站", ""}, index = 22)
    private String if06Up;
    @ExcelProperty(value = {"站点位置情况", "经度"}, index = 23)
    private String longitude;
    @ExcelProperty(value = {"", "纬度"}, index = 24)
    private String latitude;
    @ExcelProperty(value = {"", "详细地址"}, index = 25)
    private String address;
    @ExcelProperty(value = {"共享用户情况", "是否共享已有站址"}, index = 26)
    private String ifShare;
    @ExcelProperty(value = {"", "原有运营商数(户)"}, index = 27)
    private String oldOperatorNum;
    @ExcelProperty(value = {"", "原有具体运营商"}, index = 28)
    private String oldOperator;
    @ExcelProperty(value = {"", "本次新增运营商数(户)"}, index = 29)
    private String newOperatorNum;
    @ExcelProperty(value = {"", "新增具体运营商"}, index = 30)
    private String newOperator;
    @ExcelProperty(value = {"交付时间要求", ""}, index = 31)
    private String deliveryTime;
    @ExcelProperty(value = {"实际交付时间", ""}, index = 32)
    private String realDeliveryTime;
    @ExcelProperty(value = {"交付验收结果", ""}, index = 33)
    private String deliveryResult;
    @ExcelProperty(value = {"铁塔参数", "铁塔编码"}, index = 34)
    private String towerNum;
    @ExcelProperty(value = {"", "铁塔种类"}, index = 35)
    private String towerType;
    @ExcelProperty(value = {"", "铁塔名称"}, index = 36)
    private String towerName;
    @ExcelProperty(value = {"", "塔是否首家"}, index = 37)
    private String towerFirst;
    @ExcelProperty(value = {"", "铁塔高度(米)"}, index = 38)
    private String towerHeight;
    @ExcelProperty(value = {"", "平台或平层数量(个)"}, index = 39)
    private String platformNum;
    @ExcelProperty(value = {"机房参数", "机房编码"}, index = 40)
    private String roomNum;
    @ExcelProperty(value = {"", "机房类型"}, index = 41)
    private String roomType;
    @ExcelProperty(value = {"", "机房名称"}, index = 42)
    private String roomName;
    @ExcelProperty(value = {"", "机房是否首家"}, index = 43)
    private String roomFirst;
    @ExcelProperty(value = {"", "提供机位数(个)"}, index = 44)
    private String seatNum;
    @ExcelProperty(value = {"BBU参数", "系统数(个)"}, index = 45)
    private String systemNum;
    @ExcelProperty(value = {"", "BBU数(个)"}, index = 46)
    private String bbuNum;
    @ExcelProperty(value = {"RRU参数", "RRU数(个)"}, index = 47)
    private String rruNum;
    @ExcelProperty(value = {"", "RRU供电方式"}, index = 48)
    private String rruElectricType;
    @ExcelProperty(value = {"产品配置", ""}, index = 49)
    private String productConfig;
    @ExcelProperty(value = {"提供客户总功耗（KW）", ""}, index = 50)
    private String offerUserFullPower;
    @ExcelProperty(value = {"电力引入方式", ""}, index = 51)
    private String inElectricType;
    @ExcelProperty(value = {"蓄电池额外保障时长（小时）", ""}, index = 52)
    private String batterySaveHour;
    @ExcelProperty(value = {"实际风压系数", ""}, index = 53)
    private String realWindPressure;
    @ExcelProperty(value = {"算费风压系数", ""}, index = 54)
    private String costWindPressure;
    @ExcelProperty(value = {"是否具备油机发电条件", ""}, index = 55)
    private String ifGasElectric;
    @ExcelProperty(value = {"是否选择发电服务", ""}, index = 56)
    private String ifChoiceElectric;
    @ExcelProperty(value = {"铁塔产品单元1", "系统名称"}, index = 57)
    private String systemName;
    @ExcelProperty(value = {"铁塔产品单元1", "天线挂高(米)"}, index = 58)
    private String antennaHeight;
    @ExcelProperty(value = {"铁塔产品单元1", "天线数量(个)"}, index = 59)
    private String antennaNum;
    @ExcelProperty(value = {"铁塔产品单元1", "计费系统数(个)"}, index = 60)
    private String chargeSystemNum;
    @ExcelProperty(value = {"铁塔产品单元1", "BBU是否放在铁塔公司机房"}, index = 61)
    private String ifBbuInTowerRoom;
    @ExcelProperty(value = {"铁塔产品单元1", "RRU是否上塔"}, index = 62)
    private String ifRruOnTower;
    @ExcelProperty(value = {"WLAN天线", "天线挂高(米)"}, index = 75)
    private String wlanAntennaHeight;
    @ExcelProperty(value = {"微波天线", "天线挂高(米)"}, index = 76)
    private String microwaveAntennaHeight;
    @ExcelProperty(value = {"需求提出时间", ""}, index = 77)
    private String demandPropTime;
    @ExcelProperty(value = {"订单确认时间", ""}, index = 78)
    private String orderConfirmTime;
    @ExcelProperty(value = {"立项时间", ""}, index = 79)
    private String setProjectTime;
    @ExcelProperty(value = {"完工/销项时间", ""}, index = 80)
    private String projectCompleteTime;
    @ExcelProperty(value = {"起租时间", ""}, index = 81)
    private String startHireTime;
    @ExcelProperty(value = {"起租操作时间", ""}, index = 82)
    private String realHireTime;
    @ExcelProperty(value = {"截止时间", ""}, index = 83)
    private String finishHireTime;
    @ExcelProperty(value = {"协议编号", ""}, index = 84)
    private String agreementNum;
    @ExcelProperty(value = {"备注", ""}, index = 85)
    private String remark;
    @ExcelProperty(value = {"是否首家", ""}, index = 86)
    private String ifFirst;
    @ExcelProperty(value = {"备注（新）", ""}, index = 87)
    private String newRemark;
    @ExcelProperty(value = {"终止时间", ""}, index = 88)
    private String finishTime;
    @ExcelProperty(value = {"终止操作时间", ""}, index = 89)
    private String operFinishTime;
    @ExcelProperty(value = {"费用信息（单位：元/年）", "产品单元数"}, index = 90)
    private String productUnitNum;
    @ExcelProperty(value = {"", "铁塔共享折扣"}, index = 91)
    private String towerShareDiscount;
    @ExcelProperty(value = {"", "铁塔建造成本"}, index = 92)
    private String towerBuildCost;
    @ExcelProperty(value = {"", "[铁塔成本年标准单价]"}, index = 93)
    private String towerYearlyCost;
    @ExcelProperty(value = {"", "[铁塔建造成本原始值] "}, index = 94)
    private String towerOriginalCost;
    @ExcelProperty(value = {"", "机房共享折扣"}, index = 95)
    private String roomShareDiscount;
    @ExcelProperty(value = {"", "机房建造成本"}, index = 96)
    private String roomBuildCost;
    @ExcelProperty(value = {"", "[机房成本年标准单价]"}, index = 97)
    private String roomYearlyCost;
    @ExcelProperty(value = {"", "[机房建造成本原始值] "}, index = 98)
    private String roomOriginalCost;
    @ExcelProperty(value = {"", "配套共享折扣"}, index = 99)
    private String matingShareDiscount;
    @ExcelProperty(value = {"", "配套成本"}, index = 100)
    private String matingBuildCost;
    @ExcelProperty(value = {"", "[配套年标准单价]"}, index = 101)
    private String matingYearlyCost;
    @ExcelProperty(value = {"", "[配套建造成本原始值] "}, index = 102)
    private String matingOriginalCost;
    @ExcelProperty(value = {"", "维护费共享折扣"}, index = 103)
    private String maintainShareDiscount;
    @ExcelProperty(value = {"", "维护费模式"}, index = 104)
    private String maintainBuildCostType;
    @ExcelProperty(value = {"", "维护费"}, index = 105)
    private String maintainBuildCost;
    @ExcelProperty(value = {"", "[维护费年标准单价]"}, index = 106)
    private String maintainYearlyCost;
    @ExcelProperty(value = {"", "[维护费原始值] "}, index = 107)
    private String maintainOriginalCost;
    @ExcelProperty(value = {"", "BBU安装在铁塔机房费用"}, index = 108)
    private String bbuInTowerCost;
    @ExcelProperty(value = {"", "基准价格"}, index = 109)
    private String benchmarkCost;
    @ExcelProperty(value = {"", "[基准价格年标准单价]"}, index = 110)
    private String benchmarkYearlyCost;
    @ExcelProperty(value = {"", "场地费模式"}, index = 111)
    private String siteFeeType;
    @ExcelProperty(value = {"", "场地费共享折扣"}, index = 112)
    private String siteFeeShareDiscount;
    @ExcelProperty(value = {"", "场地费是否参与共享"}, index = 113)
    private String ifSiteFeeDiscount;
    @ExcelProperty(value = {"", "场地费"}, index = 114)
    private String siteFee;
    @ExcelProperty(value = {"", "[场地费年标准单价]"}, index = 115)
    private String siteFeeYearlyCost;
    @ExcelProperty(value = {"", "[场地费原始值] "}, index = 116)
    private String siteFeeOriginalCost;
    @ExcelProperty(value = {"", "电力引入费模式"}, index = 117)
    private String electricFeeType;
    @ExcelProperty(value = {"", "电力引入费共享折扣"}, index = 118)
    private String electricShareDiscount;
    @ExcelProperty(value = {"", "电力引入费是否参与共享"}, index = 119)
    private String ifElectricDiscount;
    @ExcelProperty(value = {"", "电力引入费"}, index = 120)
    private String electricFee;
    @ExcelProperty(value = {"", "[电力引入费年标准单价]"}, index = 121)
    private String electricYearlyCost;
    @ExcelProperty(value = {"", "[电力引入费原始值] "}, index = 122)
    private String electricOriginalCost;
    @ExcelProperty(value = {"", "产品价格"}, index = 123)
    private String productCost;
    @ExcelProperty(value = {"", "用电服务费模式"}, index = 124)
    private String electricServiceFeeType;
    @ExcelProperty(value = {"", "用电服务费"}, index = 125)
    private String electricServiceFee;
    @ExcelProperty(value = {"", "[用电服务费原始值] "}, index = 126)
    private String electricServiceOriginalFee;
    @ExcelProperty(value = {"", "油机发电费模式"}, index = 127)
    private String gasElectricFeeType;
    @ExcelProperty(value = {"", "油机发电费"}, index = 128)
    private String gasElectricFee;
    @ExcelProperty(value = {"", "[油机发电费原始值] "}, index = 129)
    private String gasElectricOriginalFee;
    @ExcelProperty(value = {"", "蓄电池额外保障费"}, index = 130)
    private String batterySafeFee;
    @ExcelProperty(value = {"", "[蓄电池额外保障费原始值] "}, index = 131)
    private String batterySafeOriginalFee;
    @ExcelProperty(value = {"", "高等级额外服务费"}, index = 132)
    private String HighLvAddServiceFee;
    @ExcelProperty(value = {"", "[高等级额外服务费原始值] "}, index = 133)
    private String HighLvAddServiceOrigFee;
    @ExcelProperty(value = {"", "其他折扣（RRU优惠）"}, index = 134)
    private String otherDiscount;
    @ExcelProperty(value = {"", "其他费用模式"}, index = 135)
    private String otherFeeType;
    @ExcelProperty(value = {"", "其他费用"}, index = 136)
    private String otherFee;
    @ExcelProperty(value = {"", "[其他费用原始值]"}, index = 137)
    private String otherOriginalFee;
    @ExcelProperty(value = {"", "微波"}, index = 138)
    private String microwave;
    @ExcelProperty(value = {"", "[微波原始值] "}, index = 139)
    private String microwaveOriginal;
    @ExcelProperty(value = {"", "WLAN"}, index = 140)
    private String wlan;
    @ExcelProperty(value = {"", "[WLAN原始值]"}, index = 141)
    private String wlanOriginal;
    @ExcelProperty(value = {"", "其他费用-场地费"}, index = 142)
    private String otherFieldFee;
    @ExcelProperty(value = {"", "[其他费用-场地费原始值]"}, index = 143)
    private String otherFieldOriginalFee;
    @ExcelProperty(value = {"", "其他费用-电力引入费"}, index = 144)
    private String otherElectricFee;
    @ExcelProperty(value = {"", "[其他费用-电力引入费原始值]"}, index = 145)
    private String otherElectricOriginalFee;
    @ExcelProperty(value = {"", "其他费用-油机发电费"}, index = 146)
    private String otherGasElectricFee;
    @ExcelProperty(value = {"", "[其他费用-油机发电费原始值]"}, index = 147)
    private String otherGasElectricOrigFee;
    @ExcelProperty(value = {"", "其他费用-用电服务费"}, index = 148)
    private String otherUseElectricFee;
    @ExcelProperty(value = {"", "[其他费用-用电服务费原始值]"}, index = 149)
    private String otherUseElectricOrigFee;
    @ExcelProperty(value = {"", "其他费用-维护费"}, index = 150)
    private String otherMaintainFee;
    @ExcelProperty(value = {"", "其他费用-[其他费用-维护费原始值]"}, index = 151)
    private String otherMaintainOrigFee;
    @ExcelProperty(value = {"", "总费用"}, index = 152)
    private String totalFee;
    @ExcelProperty(value = {"同步信息", "物业系统年场租及财务系统无形资产摊销是否同步"}, index = 153)
    private String ifPropertyFinanceSync;
    @ExcelProperty(value = {"", "财务系统选址费是否同步"}, index = 154)
    private String ifFinanceAddressSync;
    @ExcelProperty(value = {"建造成本模式", ""}, index = 155)
    private String buildCostType;
    @ExcelProperty(value = {"铁塔管理区域", ""}, index = 156)
    private String towerManageArea;
    @ExcelProperty(value = {"运营商项目名称", ""}, index = 157)
    private String operatorProjectName;
    @ExcelProperty(value = {"项目编码", ""}, index = 158)
    private String projectNum;
    @ExcelProperty(value = {"经办状态", ""}, index = 159)
    private String manageStatus;
    @ExcelProperty(value = {"运营商测需求编码", ""}, index = 160)
    private String operatorDemandNum;
    @ExcelProperty(value = {"铁塔原产权方", ""}, index = 161)
    private String towerEquity;
    @ExcelProperty(value = {"铁塔产权性质", ""}, index = 162)
    private String towerEquityNature;
    @ExcelProperty(value = {"机房原产权方", ""}, index = 163)
    private String roomEquity;
    @ExcelProperty(value = {"机房产权性质", ""}, index = 164)
    private String roomEquityNature;
    @ExcelProperty(value = {"套餐名称", ""}, index = 165)
    private String comboName;
    @ExcelProperty(value = {"服务终止原因", ""}, index = 167)
    private String serviceStopReason;
}
