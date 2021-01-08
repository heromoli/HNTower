package com.nokia.modules.disaster.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.nokia.modules.workflow.entity.RowModel;
import lombok.Data;

import java.io.Serializable;

@Data
//@TableName("SHOUZHAI_MESSAGE")
public class ZjwaterLandInfo extends RowModel implements Serializable {

    @ExcelProperty(index = 0, value = {"登陆信息"})
    private String info;

    @ExcelProperty(index = 1, value = {"登陆位置"})
    private String landaddress;

    @ExcelProperty(index = 2, value = {"登陆时间"})
    private String landtime;

    @ExcelProperty(index = 3, value = {"纬度"})
    private String lat;

    @ExcelProperty(index = 4, value = {"经度"})
    private String lng;

    @ExcelProperty(index = 5, value = {"强度"})
    private String strong;

}
