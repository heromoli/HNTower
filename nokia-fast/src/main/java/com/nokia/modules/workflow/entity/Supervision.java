package com.nokia.modules.workflow.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uetty.common.excel.anno.ColumnWidth;
import com.uetty.common.excel.anno.ExplicitConstraint;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("supervisor")
public class Supervision extends RowModel implements Serializable {

    @ExcelProperty(index = 0, value = {"序号"})
    private Long id;

    @TableId(type = IdType.INPUT)
    @ExcelProperty(index = 1, value = {"需求编号"})
    private String demandNum;

    @ExcelProperty(index = 2, value = {"需求来源"})
    private String demandSource;

    @ExcelProperty(index = 3, value = {"分公司"})
    @ExplicitConstraint(source = {"海口", "三亚", "琼海", "儋州"})
    @ColumnWidth(width = 100)
    private String branchCompany;

    @ExcelProperty(index = 4, value = {"市县"})
    private String county;

    @ExcelProperty(index = 5, value = {"区域"})
    private String region;

    @ExcelProperty(index = 6, value = {"站点名称"})
    private String stationName;

    @ExcelProperty(index = 7, value = {"站址编号"})
    private String stationNum;

    @ExcelProperty(index = 8, value = {"机房编号"})
    private String roomNum;

    @ExcelProperty(index = 9, value = {"订单编号"})
    private String orderNum;

    @ExcelProperty(index = 10, value = {"运营商站点名称"})
    private String operatorStationName;

    @ExcelProperty(index = 11, value = {"专项站点"})
    private String specialStation;

    @ExcelProperty(index = 12, value = {"运营商编号"})
    private String operatorNum;

    @ExcelProperty(index = 13, value = {"运营商修正编号"})
    private String operatorNumCorrect;

    @ExcelProperty(index = 14, value = {"需求提出时间"})
    @JsonProperty("demandProposeTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date demandProposeTime;

    @ExcelProperty(index = 15, value = {"交付时间"})
    @JsonProperty("deliveryTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deliveryTime;

    @ExcelProperty(index = 16, value = {"场景划分"})
    @ExplicitConstraint(source = {"密集市区", "一般市区", "县城", "乡镇", "农村"})
    private String scene;

    @ExcelProperty(index = 17, value = {"经度"})
    private double longitude;

    @ExcelProperty(index = 18, value = {"纬度"})
    private double latitude;

    @ExcelProperty(index = 19, value = {"详细地址"})
    private String address;

    @ExcelProperty(index = 20, value = {"建设方式"})
    @ExplicitConstraint(source = {"新建", "改造", "存量直接满足"})
    private String buildType;

    @ExcelProperty(index = 21, value = {"楼面地面"})
    @ExplicitConstraint(source = {"楼面站", "地面站"})
    private String buildingOrGround;

    @ExcelProperty(index = 22, value = {"共享共建模式"})
    @ExplicitConstraint(source = {"新建独享", "新建共享", "存量共享"})
    private String ifShare;

    @ExcelProperty(index = 23, value = {"移动建设模式"})
    @ExplicitConstraint(source = {"新址独享", "新建共享", "共址独享", "共址共享"})
    private String mobileBuildType;

    @ExcelProperty(index = 24, value = {"征址完成时间"})
    @JsonProperty("locationFinishTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date locationFinishTime;

    @ExcelProperty(index = 25, value = {"进场时间"})
    @JsonProperty("intoTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date intoTime;

    @ExcelProperty(index = 26, value = {"土建完成时间"})
    @JsonProperty("buildFinishTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date buildFinishTime;

    @ExcelProperty(index = 27, value = {"铁塔完成时间"})
    @JsonProperty("towerFinishTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date towerFinishTime;

    @ExcelProperty(index = 28, value = {"引电完成时间"})
    @JsonProperty("electricFinishTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date electricFinishTime;

    @ExcelProperty(index = 29, value = {"配套完成时间"})
    @JsonProperty("matchingFinishTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date matchingFinishTime;

//    @ExcelProperty(index = 28, value = {"动环安装时间"})
//    @JsonProperty("electricFinishTime")
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    private Date dhInstallFinishTime;

    @ExcelProperty(index = 30, value = {"总体完工时间"})
    @JsonProperty("totalFinishTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date totalFinishTime;

    @ExcelProperty(index = 31, value = {"内验完成时间"})
    @JsonProperty("checkFinishTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date checkFinishTime;

    @ExcelProperty(index = 32, value = {"交付完成时间"})
    @JsonProperty("deliverFinishTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deliverFinishTime;

    @ExcelProperty(index = 33, value = {"起租时间"})
    @JsonProperty("startHireTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startHireTime;

    @ExcelProperty(index = 34, value = {"工程状态分类"})
    private String projectState;

    @ExcelProperty(index = 35, value = {"工程状态描述"})
    private String projectStateDescribe;

    @ExcelProperty(index = 36, value = {"任务年份"})
    private String projectYear;

    @ExcelProperty(index = 37, value = {"是否报政府协调"})
    private String ifGovernment;

    @JsonProperty("updateTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
