package com.nokia.modules.resourceManage.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nokia.modules.workflow.entity.RowModel;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("TOWER_SOLUTION_DETAIL")
public class TowerSolutionDetail extends RowModel implements Serializable {

    @ExcelProperty(index = 0, value = {"序号"})
    private Long id;

    @ExcelProperty(index = 1, value = {"项目编号"})
    private String projectNumber;

    @ExcelProperty(index = 2, value = {"项目名称"})
    private String projectName;

    @ExcelProperty(index = 3, value = {"规划站名"})
    private String planningStationName;

    @ExcelProperty(index = 4, value = {"经度"})
    private String longitude;

    @ExcelProperty(index = 5, value = {"纬度"})
    private String latitude;

    @ExcelProperty(index = 6, value = {"网络类型"})
    private String netType;

    @ExcelProperty(index = 7, value = {"需求运营商"})
    private String demandOperator;

    @ExcelProperty(index = 8, value = {"基站类型"})
    private String stationType;

    @ExcelProperty(index = 9, value = {"铁塔类型"})
    private String towerType;

    @ExcelProperty(index = 10, value = {"高度"})
    private String height;

    @ExcelProperty(index = 11, value = {"建设方式"})
    private String buildType;

    @ExcelProperty(index = 12, value = {"移动"})
    private String mobile;

    @ExcelProperty(index = 13, value = {"电信"})
    private String telecom;

    @ExcelProperty(index = 14, value = {"联通"})
    private String unicom;

    @ExcelProperty(index = 15, value = {"备注"})
    private String remarks;
}
