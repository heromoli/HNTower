package com.nokia.modules.resourceManage.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nokia.modules.workflow.entity.RowModel;
import lombok.Data;

import java.io.Serializable;

//站址管理表
@Data
@TableName("STATION_ADDRESS_MANAGEMENT")
public class QueryStationAddress extends RowModel implements Serializable {

    @TableId
    private Long id;

    @ExcelProperty(value = {"所属省"}, index = 0)
    private String province;

    @ExcelProperty(value = {"地市"}, index = 1)
    private String city;

    @ExcelProperty(value = {"区县"}, index = 2)
    private String county;

    @ExcelProperty(value = {"站址名称"}, index = 6)
    private String stationName;

    @ExcelProperty(value = {"站址编码"}, index = 7)
    private String stationNum;

    @ExcelProperty(value = {"经度(小数点后6位)"}, index = 9)
    private double longitude;

    @ExcelProperty(value = {"纬度(小数点后6位)"}, index = 10)
    private double latitude;

    @ExcelProperty(value = {"维护状态"}, index = 12)
    private String maintainStatus;

    @ExcelProperty(value = {"站址状态"}, index = 13)
    private String stationStatus;

    @ExcelProperty(value = {"地址"}, index = 15)
    private String address;

    @ExcelProperty(value = {"站址来源"}, index = 16)
    private String stationSource;

    @ExcelProperty(value = {"运营商共享情况"}, index = 17)
    private String ifOperatorShare;

    @ExcelProperty(value = {"业务场景"}, index = 18)
    private String bizScene;

    @ExcelProperty(value = {"站址保障等级"}, index = 120)
    private String stationSafeLv;

    @ExcelProperty(value = {"备注"}, index = 121)
    private String remarks;

    @TableField(exist = false)
    private double gcjLongitude;

    @TableField(exist = false)
    private double gcjLatitude;

}
