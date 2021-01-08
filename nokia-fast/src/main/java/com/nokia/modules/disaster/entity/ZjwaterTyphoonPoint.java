package com.nokia.modules.disaster.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.nokia.modules.workflow.entity.RowModel;
import lombok.Data;

import java.io.Serializable;

@Data
//@TableName("SHOUZHAI_MESSAGE")
public class ZjwaterTyphoonPoint extends RowModel implements Serializable {

    @ExcelProperty(index = 0, value = {"时间"})
    private String time;

    @ExcelProperty(index = 1, value = {"纬度"})
    private String lat;

    @ExcelProperty(index = 2, value = {"经度"})
    private String lng;

    @ExcelProperty(index = 3, value = {"移向"})
    private String movedirection;

    @ExcelProperty(index = 4, value = {"移速"})
    private String movespeed;

    @ExcelProperty(index = 5, value = {"风力"})
    private String power;

    @ExcelProperty(index = 6, value = {"中心气压"})
    private String pressure;

    @ExcelProperty(index = 7, value = {"风速"})
    private String speed;

    @ExcelProperty(index = 8, value = {"台风强度"})
    private String strong;

    @ExcelProperty(index = 9, value = {"备注"})
    private String ckposition;

}
