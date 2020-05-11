package com.nokia.modules.workflow.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("ORIG_BUILD_DEMAND_COLLECTION")
public class OrigBuildDemandCollection extends RowModel implements Serializable {
//表3
    @TableId
    private Long id;

    @ExcelProperty(index = 1, value = {"需求编号","需求编号"})
    private String demandNum;

    @ExcelProperty(index = 2 , value =  {"电信企业","电信企业"})
    private String operatorName;

    @ExcelProperty(index = 3 , value =  {"分公司","分公司"})
    private String branchCompany;

    private String county;

    @ExcelProperty(index = 4 , value =  {"区县","区县"})
    private String region;

    @ExcelProperty(index = 5 , value =  {"站点名称","站点名称"})
    private String stationName;

    @ExcelProperty(index = 6 , value =  {"场景划分","场景划分"})
    private String scene;

    @ExcelProperty(index = 7 , value =  {"站点级别","站点级别"})
    private String stationLevel;

    @ExcelProperty(index = 8 , value =  {"站点位置及要求","经度"})
    private String longitude;

    @ExcelProperty(index = 9 , value =  {"站点位置及要求","纬度"})
    private String latitude;

    @ExcelProperty(index = 10 , value =  {"站点位置及要求","详细地址"})
    private String address;

    @ExcelProperty(index = 11 , value =  {"站点位置及要求","覆盖范围描述"})
    private String coverRangeDescribe;

    @ExcelProperty(index = 12 , value =  {"站点位置及要求","允许偏离半径(米)"})
    private String deviateRadius;

    @ExcelProperty(index = 13 , value =  {"交付时间要求(月)","交付时间要求(月)"})
    @JsonProperty("deliveryTime")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date deliveryTime;

    @ExcelProperty(index = 14 , value =  {"BBU参数","系统数"})
    private String systemNum;

    @ExcelProperty(index = 15 , value =  {"BBU参数","BBU数(个)"})
    private String bbuNum;

    @ExcelProperty(index = 16 , value =  {"RRU参数","RRU数(个)"})
    private String rruNum;

    @ExcelProperty(index = 17 , value =  {"天线参数","天线挂高范围(m)"})
    private String antennaHeight;

    @ExcelProperty(index = 18 , value =  {"天线参数","天线方向角"})
    private String antennaAngle;

    @ExcelProperty(index = 19 , value =  {"天线参数","天线数(个)"})
    private String antennaNum;

    @ExcelProperty(index = 20 , value =  {"需求的具体联系人","需求的具体联系人"})
    private String contact;

    @ExcelProperty(index = 21 , value =  {"备注","备注"})
    private String remarks;

}
