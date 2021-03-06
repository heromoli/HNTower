package com.nokia.modules.resourceManage.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nokia.modules.workflow.entity.RowModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("TOWER_SOLUTION_COLLECTION")
public class TowerSolutionCollection extends RowModel implements Serializable {

    //	(type = IdType.INPUT)
    private Long id;

    @ExcelProperty(index = 1, value = {"序号"})
    private String serialNumber;

    @ExcelProperty(index = 2, value = {"省级分公司简称"})
    private String provinceBranchCompany;

    @ExcelProperty(index = 3, value = {"市级分公司简称"})
    private String cityBranchCompany;

    @ExcelProperty(index = 4, value = {"区县"})
    private String county;

    @ExcelProperty(index = 5, value = {"综合解决方案名称"})
    private String solutionName;

    @ExcelProperty(index = 6, value = {"共建方式"})
    private String constructionType;

    @ExcelProperty(index = 7, value = {"场景划分"})
    private String sceneDivide;

    @ExcelProperty(index = 8, value = {"子网格划分"})
    private String gridDivide;

    @ExcelProperty(index = 9, value = {"覆盖面积"})
    private String coverageArea;

    @ExcelProperty(index = 10, value = {"中心经度"})
    private String longitude;

    @ExcelProperty(index = 11, value = {"中心纬度"})
    private String latitude;

    @ExcelProperty(index = 12, value = {"方案类型"})
    private String projectType;

    //现状
    //塔类宏站
    //存量站址数
    @ExcelProperty(index = 13, value = {""})
    private int xzHzClzzs;

    //电信企业存量站址情况
    //其中移动站址数
    @ExcelProperty(index = 14, value = {""})
    private int xzHzYdzzs;

    @ExcelProperty(index = 15, value = {""})
    private int xzHzLtzzs;

    @ExcelProperty(index = 16, value = {""})
    private int xzHzDxzzs;

    //宏站站址类型
    @ExcelProperty(index = 17, value = {""})
    private int xzHzDmts;

    @ExcelProperty(index = 18, value = {""})
    private int xzHzJyts;

    @ExcelProperty(index = 19, value = {""})
    private int xzHzLmts;

    @ExcelProperty(index = 20, value = {""})
    private int xzHzShzyts;

    //塔类微站
    //存量微站点位数
    @ExcelProperty(index = 21, value = {""})
    private int xzWzClzzs;

    //电信企业存量站址情况
    @ExcelProperty(index = 22, value = {""})
    private int xzWzYdzzs;

    @ExcelProperty(index = 23, value = {""})
    private int xzWzLtzzs;

    @ExcelProperty(index = 24, value = {""})
    private int xzWzDxzzs;

    //微站站址类型
    @ExcelProperty(index = 25, value = {""})
    private int xzWzLmdws;

    @ExcelProperty(index = 26, value = {""})
    private int xzWzGtdws;

    @ExcelProperty(index = 27, value = {""})
    private int xzWzShzydws;

    //无线部分
    //塔类宏站
    //规划数
    //规划点位
    @ExcelProperty(index = 28, value = {""})
    private int wxHzWlghdw;

    @ExcelProperty(index = 29, value = {""})
    private int wxHzYdghdw;

    @ExcelProperty(index = 30, value = {""})
    private int wxHzLtghdw;

    @ExcelProperty(index = 31, value = {""})
    private int wxHzDxghdw;

    @ExcelProperty(index = 32, value = {""})
    private int wxHzGdghdw;

    //需求数
    //已下需求点位
    @ExcelProperty(index = 33, value = {""})
    private int wxHzWldwxq;

    @ExcelProperty(index = 34, value = {""})
    private int wxHzYdxq;

    @ExcelProperty(index = 35, value = {""})
    private int wxHzLtxq;

    @ExcelProperty(index = 36, value = {""})
    private int wxHzDxxq;

    @ExcelProperty(index = 37, value = {""})
    private int wxHzGdxq;

    //站址数
    //存量自改规模
    @ExcelProperty(index = 38, value = {""})
    private int wxHzZzsClzggs;

    @ExcelProperty(index = 39, value = {""})
    private int wxHzZzsClzgydgs;

    @ExcelProperty(index = 40, value = {""})
    private int wxHzZzsClzgltgs;

    @ExcelProperty(index = 41, value = {""})
    private int wxHzZzsClzgdxgs;

    //存量利旧规模
    @ExcelProperty(index = 42, value = {""})
    private int wxHzZzsClljgs;

    @ExcelProperty(index = 43, value = {""})
    private int wxHzZzsClljydgs;

    @ExcelProperty(index = 44, value = {""})
    private int wxHzZzsClljltgs;

    @ExcelProperty(index = 45, value = {""})
    private int wxHzZzsClljdxgs;

    @ExcelProperty(index = 46, value = {""})
    private int wxHzZzsClljgdgs;

    //新建站规模
    @ExcelProperty(index = 47, value = {""})
    private int wxHzZzsXjzgs;

    @ExcelProperty(index = 48, value = {""})
    private int wxHzZzsXjzydgs;

    @ExcelProperty(index = 49, value = {""})
    private int wxHzZzsXjzltgs;

    @ExcelProperty(index = 50, value = {""})
    private int wxHzZzsXjzdxgs;

    @ExcelProperty(index = 51, value = {""})
    private int wxHzZzsXjz2jgxgs;

    @ExcelProperty(index = 52, value = {""})
    private int wxHzZzsXjz3jgxgs;

    @ExcelProperty(index = 53, value = {""})
    private int wxHzZzsXjzgdgs;

    //新建站站址类型
    @ExcelProperty(index = 54, value = {""})
    private int wxHzZzsXjzdmtgs;

    @ExcelProperty(index = 55, value = {""})
    private int wxHzZzsXjzjytgs;

    @ExcelProperty(index = 56, value = {""})
    private int wxHzZzsXjzlmtgs;

    @ExcelProperty(index = 57, value = {""})
    private int wxHzZzsXjzshzytgs;

    //投资
    //存量自改投资
    @ExcelProperty(index = 58, value = {""})
    private double wxHzCltzWsdgz;

    @ExcelProperty(index = 59, value = {""})
    private double wxHzCltzPzgz;

    @ExcelProperty(index = 60, value = {""})
    private double wxHzCltzTwgz;

    @ExcelProperty(index = 61, value = {""})
    private double wxHzCltzZjgz;

    //利旧存量投资
    @ExcelProperty(index = 62, value = {""})
    private double wxHzLjtzWsdgz;

    @ExcelProperty(index = 63, value = {""})
    private double wxHzLjtzPzgz;

    @ExcelProperty(index = 64, value = {""})
    private double wxHzLjtzTwgz;

    @ExcelProperty(index = 65, value = {""})
    private double wxHzLjtzZjgz;

    //新建站站址类型分类投资
    @ExcelProperty(index = 66, value = {""})
    private double wxHzXjztzDmt;

    @ExcelProperty(index = 67, value = {""})
    private double wxHzXjztzJyt;

    @ExcelProperty(index = 68, value = {""})
    private double wxHzXjztzLmt;

    @ExcelProperty(index = 69, value = {""})
    private double wxHzXjztzShzyt;

    //宏站总投资
    @ExcelProperty(index = 70, value = {""})
    private double wxHzHzztz;

    //无线
    //塔类微站
    //微站规划点位
    @ExcelProperty(index = 71, value = {""})
    private int wxWzghWldw;

    @ExcelProperty(index = 72, value = {""})
    private int wxWzghYddw;

    @ExcelProperty(index = 73, value = {""})
    private int wxWzghLtdw;

    @ExcelProperty(index = 74, value = {""})
    private int wxWzghDxdw;

    @ExcelProperty(index = 75, value = {""})
    private int wxWzghGddw;

    //微站已下需求点位
    @ExcelProperty(index = 76, value = {""})
    private int wxWzxqWldw;

    @ExcelProperty(index = 77, value = {""})
    private int wxWzxqYdxq;

    @ExcelProperty(index = 78, value = {""})
    private int wxWzxqLtxq;

    @ExcelProperty(index = 79, value = {""})
    private int wxWzxqDxxq;

    @ExcelProperty(index = 80, value = {""})
    private int wxWzxqGdxq;

    //微站点位
    @ExcelProperty(index = 81, value = {""})
    private int wxWzdwzs;

    @ExcelProperty(index = 82, value = {""})
    private int wxWzdwLmz;

    @ExcelProperty(index = 83, value = {""})
    private int wxWzdwZjtg;

    @ExcelProperty(index = 84, value = {""})
    private int wxWzdwShzy;

    //投资
    @ExcelProperty(index = 85, value = {""})
    private double wxWztzLmz;

    @ExcelProperty(index = 86, value = {""})
    private double wxWztzZjtg;

    @ExcelProperty(index = 87, value = {""})
    private double wxWztzShzy;

    @ExcelProperty(index = 88, value = {""})
    private double wxWztzzs;

    //无线总投资
    @ExcelProperty(index = 89, value = {""})
    private double wxZtz;

    //传输投资
    @ExcelProperty(index = 90, value = {""})
    private double csZtz;

    //其他投资
    @ExcelProperty(index = 91, value = {""})
    private double qtZtz;

    //总投资
    @ExcelProperty(index = 92, value = {""})
    private double zyZtz;

    @ExcelProperty(index = 93, value = {"建设年份"})
    private String buildYear;

    @JsonProperty("planFormTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(index = 94, value = {"计划编制时间"})
    private Date planFormTime;

    @ExcelProperty(index = 95, value = {"网络类型"})
    private String netType;

    @ExcelProperty(index = 96, value = {"方案类型"})
    private String planType;

    @ExcelProperty(index = 97, value = {"方案状态"})
    private String planStatus;

    //方案状态跟进
    //运营商方案推送情况
    //移动推送跟踪
    @ExcelProperty(index = 98, value = {"", ""})
    private String mobilePlanProgress;

    @JsonProperty("mobileCameTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(index = 99, value = {"", ""})
    private Date mobileCameTime;

    @JsonProperty("mobilePlanConfirmTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(index = 100, value = {"", ""})
    private Date mobilePlanConfirmTime;

    @JsonProperty("mobileDemandTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(index = 101, value = {"", ""})
    private Date mobileDemandTime;

    //联通推送跟踪
    @ExcelProperty(index = 102, value = {"", ""})
    private String unicomPlanProgress;

    @JsonProperty("unicomCameTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(index = 103, value = {"", ""})
    private Date unicomCameTime;

    @JsonProperty("unicomPlanConfirmTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(index = 104, value = {"", ""})
    private Date unicomPlanConfirmTime;

    @JsonProperty("unicomDemandTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(index = 105, value = {"", ""})
    private Date unicomDemandTime;

    //电信推送跟踪
    @ExcelProperty(index = 106, value = {"", ""})
    private String telecomPlanProgress;

    @JsonProperty("telecomCameTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(index = 107, value = {"", ""})
    private Date telecomCameTime;

    @JsonProperty("telecomPlanConfirmTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(index = 108, value = {"", ""})
    private Date telecomPlanConfirmTime;

    @JsonProperty("telecomDemandTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(index = 109, value = {"", ""})
    private Date telecomDemandTime;

    //广电推送跟踪
    @ExcelProperty(index = 110, value = {"", ""})
    private String guangdianPlanProgress;

    @JsonProperty("guangdianCameTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(index = 111, value = {"", ""})
    private Date guangdianCameTime;

    @JsonProperty("guangdianPlanConfirmTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(index = 112, value = {"", ""})
    private Date guangdianPlanConfirmTime;

    @JsonProperty("guangdianDemandTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(index = 113, value = {"", ""})
    private Date guangdianDemandTime;
}
