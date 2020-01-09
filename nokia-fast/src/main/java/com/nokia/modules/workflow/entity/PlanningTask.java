package com.nokia.modules.workflow.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("PLANNING_TASK")
public class PlanningTask extends RowModel implements Serializable {

	@TableId
	private Long id;

	@ExcelProperty(index = 1, value = {"分公司"})
	private String branchCompany;

	@ExcelProperty(index = 2, value = {"区县"})
	private String county;

	@ExcelProperty(index = 3, value = {"项目名称"})
	private String projectName;

	@JsonProperty("planFormTime")
	@JsonFormat(pattern="yyyy-MM-dd")
	@ExcelProperty(index = 4,value = {"", ""})
	private Date planFormTime;

	@ExcelProperty(index = 5,value = {"", ""})
	private String type;

	@ExcelProperty(index = 6,value = {"", ""})
	private String planDemandType;

	@ExcelProperty(index = 7,value = {"", ""})
	private String coverSceneType;

	@ExcelProperty(index = 8,value = {"", ""})
	private String coverScene;

	@ExcelProperty(index = 9,value = {"", ""})
	private String planPointTotal;

	@ExcelProperty(index = 10,value = {"", ""})
	private String macroPoint;

	@ExcelProperty(index = 11,value = {"", ""})
	private String microPoint;

	@ExcelProperty(index = 12,value = {"", ""})
	private String roomPoint;

	@ExcelProperty(index = 13,value = {"", ""})
	private String planDemandTotal;

	@ExcelProperty(index = 14,value = {"", ""})
	private String macroDemand;

	@ExcelProperty(index = 15,value = {"", ""})
	private String microDemand;

	@ExcelProperty(index = 16,value = {"", ""})
	private String roomDemand;

	@ExcelProperty(index = 17,value = {"", ""})
	private String socialTowerPointNum;

	@ExcelProperty(index = 18,value = {"", ""})
	private String roofResourcePointNum;

	@ExcelProperty(index = 19,value = {"", ""})
	private String stockTowerPointNum;

	@ExcelProperty(index = 20,value = {"", ""})
	private String microUseSocialPoint;

	@ExcelProperty(index = 21,value = {"", ""})
	private String microUseRoofPoint;

	@ExcelProperty(index = 22,value = {"", ""})
	private String microUseStock;

	@ExcelProperty(index = 23,value = {"", ""})
	private String macroUseSocialPoint;

	@ExcelProperty(index = 24,value = {"", ""})
	private String macroUseRoofPoint;

	@ExcelProperty(index = 25,value = {"", ""})
	private String macroUseStock;

	@ExcelProperty(index = 26,value = {"", ""})
	private String operatorAcceptMacroPoint;

	@ExcelProperty(index = 27,value = {"", ""})
	private String operatorAcceptMicroPoint;

	@ExcelProperty(index = 28,value = {"", ""})
	private String operatorAcceptRoomPoint;

	@ExcelProperty(index = 29,value = {"", ""})
	private String operatorAMicroUseSocial;

	@ExcelProperty(index = 30,value = {"", ""})
	private String operatorAMicroUseRoof;

	@ExcelProperty(index = 31,value = {"", ""})
	private String operatorAMicroUseStock;

	@ExcelProperty(index = 32,value = {"", ""})
	private String operatorAMacroUseSocial;

	@ExcelProperty(index = 33,value = {"", ""})
	private String operatorAMacroUseRoof;

	@ExcelProperty(index = 34,value = {"", ""})
	private String operatorAMacroUseStock;

	@ExcelProperty(index = 35,value = {"", ""})
	private String mobileDemandTotal;

	@ExcelProperty(index = 36,value = {"", ""})
	private String mobileDemandMacro;

	@ExcelProperty(index = 37,value = {"", ""})
	private String mobileDemandMicro;

	@ExcelProperty(index = 38,value = {"", ""})
	private String mobileDemandRoom;

	@ExcelProperty(index = 39,value = {"", ""})
	private String uincomDemandTotal;

	@ExcelProperty(index = 40,value = {"", ""})
	private String uincomDemandMacro;

	@ExcelProperty(index = 41,value = {"", ""})
	private String uincomDemandMicro;

	@ExcelProperty(index = 42,value = {"", ""})
	private String uincomDemandRoom;

	@ExcelProperty(index = 43,value = {"", ""})
	private String telecomDemandTotal;

	@ExcelProperty(index = 44,value = {"", ""})
	private String telecomDemandMacro;

	@ExcelProperty(index = 45,value = {"", ""})
	private String telecomDemandMicro;

	@ExcelProperty(index = 46,value = {"", ""})
	private String telecomDemandRoom;

	@ExcelProperty(index = 47,value = {"", ""})
	private String operatorDemandPointTotal;

	@ExcelProperty(index = 48,value = {"", ""})
	private String operatorDemandPointMacro;

	@ExcelProperty(index = 49,value = {"", ""})
	private String operatorDemandPointMicro;

	@ExcelProperty(index = 50,value = {"", ""})
	private String operatorDemandPointRoom;

	@ExcelProperty(index = 51,value = {"", ""})
	private String operatorDMicroUseSocial;

	@ExcelProperty(index = 52,value = {"", ""})
	private String operatorDMicroUseRoof;

	@ExcelProperty(index = 53,value = {"", ""})
	private String operatorDMicroUseStock;

	@ExcelProperty(index = 54,value = {"", ""})
	private String operatorDMacroUseSocial;

	@ExcelProperty(index = 55,value = {"", ""})
	private String operatorDMacroUseRoof;

	@ExcelProperty(index = 56,value = {"", ""})
	private String operatorDMacroUseStock;

	@ExcelProperty(index = 57,value = {"", ""})
	private String planDetail;

	@ExcelProperty(index = 58,value = {"", ""})
	private String mobilePlanProgress;

	@JsonProperty("mobileCameTime")
	@JsonFormat(pattern="yyyy-MM-dd")
	@ExcelProperty(index = 59,value = {"", ""})
	private Date mobileCameTime;

	@JsonProperty("mobilePlanConfirmTime")
	@JsonFormat(pattern="yyyy-MM-dd")
	@ExcelProperty(index = 60,value = {"", ""})
	private Date mobilePlanConfirmTime;

	@JsonProperty("mobileDemandTime")
	@JsonFormat(pattern="yyyy-MM-dd")
	@ExcelProperty(index = 61,value = {"", ""})
	private Date mobileDemandTime;

	@ExcelProperty(index = 62,value = {"", ""})
	private String unicomPlanProgress;

	@JsonProperty("unicomCameTime")
	@JsonFormat(pattern="yyyy-MM-dd")
	@ExcelProperty(index = 63,value = {"", ""})
	private Date unicomCameTime;

	@JsonProperty("unicomPlanConfirmTime")
	@JsonFormat(pattern="yyyy-MM-dd")
	@ExcelProperty(index = 64,value = {"", ""})
	private Date unicomPlanConfirmTime;

	@JsonProperty("unicomDemandTime")
	@JsonFormat(pattern="yyyy-MM-dd")
	@ExcelProperty(index = 65,value = {"", ""})
	private Date unicomDemandTime;

	@ExcelProperty(index = 66,value = {"", ""})
	private String telecomPlanProgress;

	@JsonProperty("telecomCameTime")
	@JsonFormat(pattern="yyyy-MM-dd")
	@ExcelProperty(index = 67,value = {"", ""})
	private Date telecomCameTime;

	@JsonProperty("telecomPlanConfirmTime")
	@JsonFormat(pattern="yyyy-MM-dd")
	@ExcelProperty(index = 68,value = {"", ""})
	private Date telecomPlanConfirmTime;

	@JsonProperty("telecomDemandTime")
	@JsonFormat(pattern="yyyy-MM-dd")
	@ExcelProperty(index = 69,value = {"", ""})
	private Date telecomDemandTime;
}
