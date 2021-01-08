package com.nokia.modules.disaster.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nokia.modules.workflow.entity.RowModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
//@TableName("SHOUZHAI_MESSAGE")
public class ZjwaterTyphoonInfo extends RowModel implements Serializable {

    @ExcelProperty(index = 0, value = {"编号"})
    private String tfid;

    @ExcelProperty(index = 1, value = {"英文名称"})
    private String enname;

    @ExcelProperty(index = 2, value = {"中文名称"})
    private String name;

    @ExcelProperty(index = 3, value = {"开始时间"})
    private String starttime;

    @ExcelProperty(index = 4, value = {"结束时间"})
    private String endtime;

    @ExcelProperty(index = 5, value = {"状态"})
    private String isactive;

    @ExcelProperty(index = 6, value = {"警报级别"})
    private String warnlevel;

}
