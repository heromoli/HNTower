package com.nokia.modules.workflow.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("PMS")
public class PMS extends RowModel implements Serializable {

    @TableId
    private Long id;

    @ExcelProperty(value = {"订单号", "订单号"}, index = 0)
    private String orderNum;

    @ExcelProperty(value = {"建设单位", "建设单位"}, index = 1)
    private String buildDepartment;

    @ExcelProperty(value = {"行政区域", "行政区域"}, index = 2)
    private String province;

    @ExcelProperty(value = {"所属组织", "所属组织"}, index = 3)
    private String city;

    @ExcelProperty(value = {"所属区域", "所属区域"}, index = 4)
    private String region;

    @ExcelProperty(value = {"移动公司项目批次", "移动公司项目批次"}, index = 5)
    private String mobileBatch;

    @ExcelProperty(value = {"电信公司项目批次", "电信公司项目批次"}, index = 6)
    private String telecomBatch;

    @ExcelProperty(value = {"联通公司项目批次", "联通公司项目批次"}, index = 7)
    private String unicomBatch;

    @ExcelProperty(value = {"铁塔公司项目批次", "铁塔公司项目批次"}, index = 8)
    private String towerBatch;

    @ExcelProperty(value = {"是否属于高铁或地铁项目", "是否属于高铁或地铁项目"}, index = 9)
    private String ifRailwayProject;

    @ExcelProperty(value = {"所属高铁或地铁项目名称", "所属高铁或地铁项目名称"}, index = 10)
    private String railwayProjectName;

    @ExcelProperty(value = {"站点名称", "站点名称"}, index = 11)
    private String stationName;

    @ExcelProperty(value = {"站点编号", "站点编号"}, index = 12)
    private String stationNum;

    @ExcelProperty(value = {"场景划分", "场景划分"}, index = 13)
    private String scene;

    @ExcelProperty(value = {"建设方式", "建设方式"}, index = 14)
    private String buildType;

    @ExcelProperty(value = {"建设服务等级", "建设服务等级"}, index = 15)
    private String buildServeLevel;

    @ExcelProperty(value = {"维护服务等级", "维护服务等级"}, index = 16)
    private String maintainServeLevel;

    @ExcelProperty(value = {"站点位置情况", "经度"}, index = 17)
    private String longitude;

    @ExcelProperty(value = {"站点位置情况", "纬度"}, index = 18)
    private String latitude;

    @ExcelProperty(value = {"站点位置情况", "详细地址"}, index = 19)
    private String address;

    @ExcelProperty(value = {"共享用户情况", "是否共享已有站址"}, index = 20)
    private String ifShare;

    @ExcelProperty(value = {"共享用户情况", "原有运营商数(户)"}, index = 21)
    private String oldOperatorNum;

    @ExcelProperty(value = {"共享用户情况", "原有具体运营商"}, index = 22)
    private String oldOperator;

    @ExcelProperty(value = {"共享用户情况", "本次新增运营商数(户)"}, index = 23)
    private String newOperatorNum;

    @ExcelProperty(value = {"共享用户情况", "新增具体运营商"}, index = 24)
    private String newOperator;

    @ExcelProperty(value = {"交付时间要求", "交付时间要求"}, index = 25)
    @JsonProperty("deliveryTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String deliveryTime;

    @ExcelProperty(value = {"通信设备安装调测时限", "通信设备安装调测时限"}, index = 26)
    @JsonProperty("debuggingTimeLimit")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String debuggingTimeLimit;

    @ExcelProperty(value = {"已安装系统数（个）", "已安装系统数（个）"}, index = 27)
    private String systemNum;

    @ExcelProperty(value = {"已安装RRU数（个）", "已安装RRU数（个）"}, index = 28)
    private String rruNum;

    @ExcelProperty(value = {"提供设备总功耗（KW）", "提供设备总功耗（KW）"}, index = 29)
    private String equitFullPower;

    @ExcelProperty(value = {"可安装机位总数（个）", "可安装机位总数（个）"}, index = 30)
    private String seatNum;

    @ExcelProperty(value = {"电力引入方式", "电力引入方式"}, index = 31)
    private String electricType;

    @ExcelProperty(value = {"预估年度租金（元）", "移动"}, index = 32)
    private String mobileEstimateYearlyRental;

    @ExcelProperty(value = {"预估年度租金（元）", "联通"}, index = 33)
    private String unicomEstimateYearlyRental;

    @ExcelProperty(value = {"预估年度租金（元）", "电信"}, index = 34)
    private String telecomEstimateYearlyRental;

    @ExcelProperty(value = {"预估年度租金（元）", "合计"}, index = 35)
    private String totalEstimateYearlyRental;

    @ExcelProperty(value = {"单站投资收益率", "单站投资收益率"}, index = 36)
    private String roi;

    @ExcelProperty(value = {"风压系数", "风压系数"}, index = 37)
    private String windPressure;

    @ExcelProperty(value = {"平台或平层数量(个)", "平台或平层数量(个)"}, index = 38)
    private String platformNum;

    @ExcelProperty(value = {"铁塔产权性质", "铁塔产权性质"}, index = 39)
    private String towerEquity;

    @ExcelProperty(value = {"铁塔种类", "铁塔种类"}, index = 40)
    private String towerType;

//    @ExcelProperty(value = {"", ""}, index = 1)
//    private String towerTypeDetail;

    @ExcelProperty(value = {"铁塔投资估算(元)", "费用名称"}, index = 41)
    private String towerInvestName;

    @ExcelProperty(value = {"铁塔投资估算(元)", "规格类型"}, index = 42)
    private String towerInvestType;

    @ExcelProperty(value = {"铁塔投资估算(元)", "单位"}, index = 43)
    private String towerInvestUnit;

    @ExcelProperty(value = {"铁塔投资估算(元)", "单价"}, index = 44)
    private String towerInvestPrice;

    @ExcelProperty(value = {"铁塔投资估算(元)", "数量"}, index = 45)
    private String towerInvestNum;

    @ExcelProperty(value = {"铁塔投资估算(元)", "合价"}, index = 46)
    private String towerInvestTotalPrice;

    @ExcelProperty(value = {"铁塔投资估算(元)", "备注"}, index = 47)
    private String towerInvestRemark;

    @ExcelProperty(value = {"塔基投资估算(元)", "费用名称"}, index = 48)
    private String baseInvestName;

    @ExcelProperty(value = {"塔基投资估算(元)", "规格类型"}, index = 49)
    private String baseInvestType;

    @ExcelProperty(value = {"塔基投资估算(元)", "单位"}, index = 50)
    private String baseInvestUnit;

    @ExcelProperty(value = {"塔基投资估算(元)", "单价"}, index = 51)
    private String baseInvestPrice;

    @ExcelProperty(value = {"塔基投资估算(元)", "数量"}, index = 52)
    private String baseInvestNum;

    @ExcelProperty(value = {"塔基投资估算(元)", "合价"}, index = 53)
    private String baseInvestTotalPrice;

    @ExcelProperty(value = {"地网投资估算(元)", "费用名称"}, index = 54)
    private String netInvestName;

    @ExcelProperty(value = {"地网投资估算(元)", "规格类型"}, index = 55)
    private String netInvestType;

    @ExcelProperty(value = {"地网投资估算(元)", "单位"}, index = 56)
    private String netInvestUnit;

    @ExcelProperty(value = {"地网投资估算(元)", "单价"}, index = 57)
    private String netInvestPrice;

    @ExcelProperty(value = {"地网投资估算(元)", "数量"}, index = 58)
    private String netInvestNum;

    @ExcelProperty(value = {"地网投资估算(元)", "合价"}, index = 59)
    private String netInvestTotalPrice;

    @ExcelProperty(value = {"机房投资估算(元)", "费用名称"}, index = 60)
    private String roomInvestName;

    @ExcelProperty(value = {"机房投资估算(元)", "规格类型"}, index = 61)
    private String roomInvestType;

    @ExcelProperty(value = {"机房投资估算(元)", "单位"}, index = 62)
    private String roomInvestUnit;

    @ExcelProperty(value = {"机房投资估算(元)", "单价"}, index = 63)
    private String roomInvestPrice;

    @ExcelProperty(value = {"机房投资估算(元)", "数量"}, index = 64)
    private String roomInvestNum;

    @ExcelProperty(value = {"机房投资估算(元)", "合价"}, index = 65)
    private String roomInvestTotalPrice;

    @ExcelProperty(value = {"交流配电屏/箱投资估算(元)", "费用名称"}, index = 66)
    private String electricInvestName;

    @ExcelProperty(value = {"交流配电屏/箱投资估算(元)", "规格类型"}, index = 67)
    private String electricInvestType;

    @ExcelProperty(value = {"交流配电屏/箱投资估算(元)", "单位"}, index = 68)
    private String electricInvestUnit;

    @ExcelProperty(value = {"交流配电屏/箱投资估算(元)", "单价"}, index = 69)
    private String electricInvestPrice;

    @ExcelProperty(value = {"交流配电屏/箱投资估算(元)", "数量"}, index = 70)
    private String electricInvestNum;

    @ExcelProperty(value = {"交流配电屏/箱投资估算(元)", "合价"}, index = 71)
    private String electricInvestTotalPrice;

    @ExcelProperty(value = {"开关电源（含整流模块）投资估算(元)", "费用名称"}, index = 72)
    private String switchInvestName;

    @ExcelProperty(value = {"开关电源（含整流模块）投资估算(元)", "规格类型"}, index = 73)
    private String switchInvestType;

    @ExcelProperty(value = {"开关电源（含整流模块）投资估算(元)", "单位"}, index = 74)
    private String switchInvestUnit;

    @ExcelProperty(value = {"开关电源（含整流模块）投资估算(元)", "单价"}, index = 75)
    private String switchInvestPrice;

    @ExcelProperty(value = {"开关电源（含整流模块）投资估算(元)", "数量"}, index = 76)
    private String switchInvestNum;

    @ExcelProperty(value = {"开关电源（含整流模块）投资估算(元)", "合价"}, index = 77)
    private String switchInvestTotalPrice;

    @ExcelProperty(value = {"蓄电池投资估算(元)", "费用名称"}, index = 78)
    private String batteryInvestName;

    @ExcelProperty(value = {"蓄电池投资估算(元)", "规格类型"}, index = 79)
    private String batteryInvestType;

    @ExcelProperty(value = {"蓄电池投资估算(元)", "单位"}, index = 80)
    private String batteryInvestUnit;

    @ExcelProperty(value = {"蓄电池投资估算(元)", "单价"}, index = 81)
    private String batteryInvestPrice;

    @ExcelProperty(value = {"蓄电池投资估算(元)", "数量"}, index = 82)
    private String batteryInvestNum;

    @ExcelProperty(value = {"蓄电池投资估算(元)", "合价"}, index = 83)
    private String batteryInvestTotalPrice;

    @ExcelProperty(value = {"空调投资估算(元)", "费用名称"}, index = 84)
    private String airInvestName;

    @ExcelProperty(value = {"空调投资估算(元)", "规格类型"}, index = 85)
    private String airInvestType;

    @ExcelProperty(value = {"空调投资估算(元)", "单位"}, index = 86)
    private String airInvestUnit;

    @ExcelProperty(value = {"空调投资估算(元)", "单价"}, index = 87)
    private String airInvestPrice;

    @ExcelProperty(value = {"空调投资估算(元)", "数量"}, index = 88)
    private String airInvestNum;

    @ExcelProperty(value = {"空调投资估算(元)", "合价"}, index = 89)
    private String airInvestTotalPrice;

    @ExcelProperty(value = {"动环监控投资估算(元)", "费用名称"}, index = 90)
    private String monitoringInvestName;

    @ExcelProperty(value = {"动环监控投资估算(元)", "规格类型"}, index = 91)
    private String monitoringInvestType;

    @ExcelProperty(value = {"动环监控投资估算(元)", "单位"}, index = 92)
    private String monitoringInvestUnit;

    @ExcelProperty(value = {"动环监控投资估算(元)", "单价"}, index = 93)
    private String monitoringInvestPrice;

    @ExcelProperty(value = {"动环监控投资估算(元)", "数量"}, index = 94)
    private String monitoringInvestNum;

    @ExcelProperty(value = {"动环监控投资估算(元)", "合价"}, index = 95)
    private String monitoringInvestTotalPrice;

    @ExcelProperty(value = {"施工费(元)", "施工费(元)"}, index = 96)
    private String constructionFee;

    @ExcelProperty(value = {"选址费（元）", "选址费（元）"}, index = 97)
    private String siteSelectionFee;

    @ExcelProperty(value = {"建设用地及综合赔补费", "建设用地及综合赔补费"}, index = 98)
    private String constructionLandFee;

    @ExcelProperty(value = {"工程建设其他费(元)", "工程建设其他费(元)"}, index = 99)
    private String buildOtherFee;

    @ExcelProperty(value = {"电力引入总费用（元）", "电力引入总费用（元）"}, index = 100)
    private String powerLeadFee;

    @ExcelProperty(value = {"其他投资(元)", "其他投资(元)"}, index = 101)
    private String otherInvestFee;

    @ExcelProperty(value = {"投资总计(元)", "投资总计(元)"}, index = 102)
    private String totalInvestFee;

    @ExcelProperty(value = {"场地年租金（元）", "场地年租金（元）"}, index = 103)
    private String siteYearlyRental;

    @ExcelProperty(value = {"备注", "备注"}, index = 104)
    private String remark;

    @ExcelProperty(value = {"项目名称", "项目名称"}, index = 105)
    private String projectName;

    @ExcelProperty(value = {"项目年份", "项目年份"}, index = 106)
    private String projectYear;

    @ExcelProperty(value = {"项目编码", "项目编码"}, index = 107)
    private String projectNum;

    @ExcelProperty(value = {"国投项目", "国投项目"}, index = 108)
    private String govInvestProject;

    @ExcelProperty(value = {"项目批复人", "项目批复人"}, index = 109)
    private String projectPrincipal;

    @ExcelProperty(value = {"项目批复时间", "项目批复时间"}, index = 110)
    @JsonProperty("projectApproveTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String projectApproveTime;

    @ExcelProperty(value = {"项目批复文号", "项目批复文号"}, index = 111)
    private String projectApproveNum;

    @ExcelProperty(value = {"客户经理（领取人）", "客户经理（领取人）"}, index = 112)
    private String userManager;

    @ExcelProperty(value = {"方案造价管理岗（领取人）", "方案造价管理岗（领取人）"}, index = 113)
    private String costManager;

    @ExcelProperty(value = {"设计阶段", "设计投资金额（元）"}, index = 114)
    private String designInvestAmount;

    @ExcelProperty(value = {"设计阶段", "设计批复人"}, index = 115)
    private String designApprover;

    @ExcelProperty(value = {"设计阶段", "设计批复文号"}, index = 116)
    private String designApproveNum;

    @ExcelProperty(value = {"设计阶段", "设计批复时间"}, index = 117)
    @JsonProperty("designApproveTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String designApproveTime;

    @ExcelProperty(value = {"设计阶段", "设计规模"}, index = 118)
    private String designScale;

    @ExcelProperty(value = {"设计阶段", "项目管理岗"}, index = 119)
    private String designManager;

    @ExcelProperty(value = {"设计阶段", "开工时间"}, index = 120)
    @JsonProperty("startBuildTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String startBuildTime;

    @ExcelProperty(value = {"设计阶段", "完工时间"}, index = 121)
    @JsonProperty("finishBuildTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String finishBuildTime;

    @ExcelProperty(value = {"设计阶段", "内验交维时限"}, index = 122)
    @JsonProperty("acceptanceMaintainTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String acceptanceMaintainTime;

    @ExcelProperty(value = {"设计阶段", "内部验收时间"}, index = 123)
    @JsonProperty("internalAcceptanceTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String internalAcceptanceTime;

    @ExcelProperty(value = {"设计阶段", "验收交付时间"}, index = 124)
    @JsonProperty("acceptanceDeliveryTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String acceptanceDeliveryTime;

    @ExcelProperty(value = {"设计阶段", "通讯设备调测完成时间"}, index = 125)
    @JsonProperty("equipmentDebugTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String equipmentDebugTime;

    @ExcelProperty(value = {"设计阶段", "地勘服务商"}, index = 126)
    private String explorationEnterprise;

    @ExcelProperty(value = {"设计阶段", "通信服务商"}, index = 127)
    private String communicationEnterprise;

    @ExcelProperty(value = {"设计阶段", "外市电服务商"}, index = 128)
    private String electricEnterprise;

    @ExcelProperty(value = {"施工阶段", "实际工时间"}, index = 129)
    @JsonProperty("realityStartTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String realityStartTime;

    @ExcelProperty(value = {"施工阶段", "实际完工时间"}, index = 130)
    @JsonProperty("realityFinishTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String realityFinishTime;

    @ExcelProperty(value = {"施工阶段", "监理单位"}, index = 131)
    private String supervisorCompany;

    @ExcelProperty(value = {"施工阶段", "施工单位"}, index = 132)
    private String constructionCompany;

    @ExcelProperty(value = {"验收阶段", "内部验收开始时间"}, index = 133)
    @JsonProperty("startInAcceptanceTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String startInAcceptTime;

    @ExcelProperty(value = {"验收阶段", "内部验收结束时间"}, index = 134)
    @JsonProperty("finishInAcceptanceTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String finishInAcceptTime;

    @ExcelProperty(value = {"验收阶段", "安装调测时间"}, index = 135)
    @JsonProperty("installDebugTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String installDebugTime;

    @ExcelProperty(value = {"验收阶段", "交付验收开始时间"}, index = 136)
    @JsonProperty("startDeliveryAcceptTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String startDeliveryAcceptTime;

    @ExcelProperty(value = {"验收阶段", "交付验收结束时间"}, index = 137)
    @JsonProperty("finishDeliveryAcceptTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String finishDeliveryAcceptTime;

    @ExcelProperty(value = {"验收阶段", "交维时间"}, index = 138)
    @JsonProperty("deliveryMaintenanceTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String deliveryMaintenanceTime;

    @ExcelProperty(value = {"决算时间", "初步竣工决算时间"}, index = 139)
    @JsonProperty("initProjectCompleteTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String initProjectCompleteTime;

    @ExcelProperty(value = {"决算时间", "试运行开始时间"}, index = 140)
    @JsonProperty("pilotRunStartTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String pilotRunStartTime;

    @ExcelProperty(value = {"决算时间", "试运行结束时间"}, index = 141)
    @JsonProperty("pilotRunFinishTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String pilotRunFinishTime;

    @ExcelProperty(value = {"决算时间", "竣工决算时间"}, index = 142)
    @JsonProperty("projectCompleteTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String projectCompleteTime;

    @ExcelProperty(value = {"决算时间", "项目终验时间"}, index = 143)
    @JsonProperty("projectFinalAcceptTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String projectFinalAcceptTime;

    @ExcelProperty(value = {"决算时间", "项目送审时间"}, index = 144)
    @JsonProperty("projectSubmitTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String projectSubmitTime;

    @ExcelProperty(value = {"决算时间", "项目审定时间"}, index = 145)
    @JsonProperty("projectApprovedTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String projectApprovedTime;

    @ExcelProperty(value = {"决算时间", "工程转资时间"}, index = 146)
    @JsonProperty("projectTransAccountTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String projectTransAccountTime;

    @ExcelProperty(value = {"决算时间", "工程转资金额"}, index = 147)
    private String tentativeInvestTotal;

    @ExcelProperty(value = {"决算时间", "初步决算投资合计"}, index = 148)
    private String initInvestTotal;

    @ExcelProperty(value = {"决算时间", "决算审计投资合计"}, index = 149)
    private String investTotal;

    @ExcelProperty(value = {"决算时间", "项目转资投资合计"}, index = 150)
    private String projectInvestTotal;

    @ExcelProperty(value = {"CRM订单信息", "移动订单号"}, index = 151)
    private String mobileOrderNum;

    @ExcelProperty(value = {"CRM订单信息", "移动订单名称"}, index = 152)
    private String mobileOrderName;

    @ExcelProperty(value = {"CRM订单信息", "移动订单交付时间"}, index = 153)
    @JsonProperty("mobileOrderDeliveryTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String mobileOrderDeliveryTime;

    @ExcelProperty(value = {"CRM订单信息", "联通订单号"}, index = 154)
    private String unicomOrderNum;

    @ExcelProperty(value = {"CRM订单信息", "联通订单名称"}, index = 155)
    private String unicomOrderName;

    @ExcelProperty(value = {"CRM订单信息", "联通订单交付时间"}, index = 156)
    @JsonProperty("unicomOrderDeliveryTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String unicomOrderDeliveryTime;

    @ExcelProperty(value = {"CRM订单信息", "电信订单号"}, index = 157)
    private String telecomOrderNum;

    @ExcelProperty(value = {"CRM订单信息", "电信订单名称"}, index = 158)
    private String telecomOrderName;

    @ExcelProperty(value = {"CRM订单信息", "电信订单交付时间"}, index = 159)
    @JsonProperty("telecomOrderDeliveryTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String telecomOrderDeliveryTime;

    @ExcelProperty(value = {"是否新业务", "是否新业务"}, index = 160)
    private String ifNewBusiness;

    @ExcelProperty(value = {"新业务订单信息", "订单号"}, index = 161)
    private String newBizOrderNum;

    @ExcelProperty(value = {"新业务订单信息", "订单名称"}, index = 162)
    private String newBizOrderName;

    @ExcelProperty(value = {"新业务订单信息", "订单交付时间"}, index = 163)
    @JsonProperty("newBIZOrderDeliveryTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String newBizOrderDeliveryTime;

    @ExcelProperty(value = {"项目状态", "项目状态"}, index = 164)
    private String projectStatus;

    @ExcelProperty(value = {"所属专项", "所属专项"}, index = 165)
    private String belongTo;

    @ExcelProperty(value = {"销项批复时间", "销项批复时间"}, index = 166)
    @JsonProperty("destroyProjectReplyTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String destroyProjectReplyTime;

    @ExcelProperty(value = {"国开行专项名称", "国开行专项名称"}, index = 167)
    private String gkhProjectName;

    @ExcelProperty(value = {"已入账金额", "已入账金额"}, index = 168)
    private String recordedAmount;

    @ExcelProperty(value = {"预计折损金额", "预计折损金额"}, index = 169)
    private String breakageAmout;

    @ExcelProperty(value = {"是否分摊设计费", "是否分摊设计费"}, index = 170)
    private String ifShareDesignFee;

    @ExcelProperty(value = {"是否分摊监理费", "是否分摊监理费"}, index = 171)
    private String ifShareSupervisorFee;

    @ExcelProperty(value = {"需求单领取时间", "需求单领取时间"}, index = 172)
    @JsonProperty("orderGetTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String orderGetTime;

}

