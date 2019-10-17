package com.nokia.modules.workflow.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("BUILD_DEMAND_CHANGE_CONFIRM")
public class BuildDemandChangeConfirm extends RowModel implements Serializable {

    @TableId
    private Long id;

    private String actProcInstId;

    private String actProcStatus;

    @TableField(exist=false)
    private String tableId;

    private String demandNum;

    private String operatorName;

    private String branchCompany;

    private String county;

    private String region;

    private String stationName;

    private String stationNum;

    private String scene;

    private String longitude;

    private String latitude;

    private String address;

    private String changeReason;

    private String changeContentAndSolution;

    private String changeCost;

    private String remarks;

}
