package com.nokia.modules.report.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nokia.modules.workflow.entity.RowModel;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("TIETA_CHUZHANG_XD")
public class   TietaChuzhangXd extends RowModel implements Serializable {

    @ExcelProperty(index = 0, value = {""})
    private String smonth;

    @ExcelProperty(index = 1, value = {""})
    private String demandNum;

    @ExcelProperty(index = 2, value = {""})
    private double tietaCost;

    @ExcelProperty(index = 3, value = {""})
    private double roomCost;

    @ExcelProperty(index = 4, value = {""})
    private double otherCost;

    @ExcelProperty(index = 5, value = {""})
    private double sumCost;



}
