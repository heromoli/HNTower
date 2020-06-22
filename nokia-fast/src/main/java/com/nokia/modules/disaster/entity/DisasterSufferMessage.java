package com.nokia.modules.disaster.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nokia.modules.workflow.entity.RowModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("SHOUZHAI_MESSAGE")
public class DisasterSufferMessage extends RowModel implements Serializable {

//    @TableId
    @ExcelProperty(index = 0, value = {"序号"})
    private Long id;

    @ExcelProperty(index = 1, value = {"简报时间"})
    @JsonProperty("sdate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date sdate;

    @ExcelProperty(index = 2, value = {"简报类型"})
    private int msgId;

    @ExcelProperty(index = 3, value = {"内容"})
    private String msg;

    @ExcelProperty(index = 4, value = {"是否发送"})
    private int sendStatus;

    @ExcelProperty(index = 5, value = {"发送时间"})
    private Date sendDate;

}
