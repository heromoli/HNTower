package com.nokia.modules.resourceManage.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;

import com.nokia.modules.workflow.entity.RowModel;
import lombok.Data;

import java.io.Serializable;

//站址信息表
@Data
@TableName("ZJ_BASE_STATION_INFO")
public class BaseStationInfo extends RowModel implements Serializable {

    @ExcelProperty(value = {"序号"}, index = 0)
    private String id;

    @ExcelProperty(value = {"地市"}, index = 1)
    private String city;

    @ExcelProperty(value = {"项目名称"}, index = 2)
    private String projectName;

    @ExcelProperty(value = {"客户名称"}, index = 3)
    private String customerName;

    @ExcelProperty(value = {"合同编号"}, index = 4)
    private String contractSerialNum;

    @ExcelProperty(value = {"业务类型"}, index = 5)
    private String bizType;

    @ExcelProperty(value = {"站址编号"}, index = 6)
    private String stationNum;

    @ExcelProperty(value = {"需交维内容"}, index = 7)
    private String maintainContent;

    @ExcelProperty(value = {"当前维护单位"}, index = 8)
    private String maintainInstitution;

    @ExcelProperty(value = {"经度"}, index = 9)
    private double longitude;

    @ExcelProperty(value = {"纬度"}, index = 10)
    private double latitude;
}

