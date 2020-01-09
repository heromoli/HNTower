package com.nokia.modules.resourceManage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.nokia.modules.workflow.entity.RowModel;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("ZHJJFA_MON_REPORT")
public class TowerSolutionStatistics extends RowModel implements Serializable {

    private String smonth;

    private String branch;

    private String county;

    private int soluAll;

    private int soluCurMon;

    private int sumPlanAll;

    private int sumPlanCurMon;

    private int sumPlanAllWz;

    private int sumPlanCurMonWz;

    private int sumWxWzdwShzy;

    private int sumWxWzdwShzyMon;

    private int sumPlanType;

    private int sumPlanTypeMon;

    private int sumPlanTypeHz;

    private int sumPlanTypeHzMon;

    private int sumPlanTypeWz;

    private int sumPlanTypeWzMon;

    private int sumPlanOpeYd;

    private int sumPlanOpeYdMon;

    private int sumPlanOpeLt;

    private int sumPlanOpeLtMon;

    private int sumPlanOpeDx;

    private int sumPlanOpeDxMon;


}
