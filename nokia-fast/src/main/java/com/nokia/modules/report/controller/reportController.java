package com.nokia.modules.report.controller;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.nokia.common.exception.RRException;
import com.nokia.modules.report.entity.BranchDailyReport;
import com.nokia.modules.report.entity.CountyDailyReport;
import com.nokia.modules.report.entity.TietaChuzhangXd;

import com.nokia.modules.report.service.CountyDailyReportService;
import com.nokia.modules.report.service.TietaChuzhangXdService;
import com.nokia.modules.report.service.BranchDailyReportService;
import com.nokia.modules.sys.controller.BaseController;
import com.nokia.utils.PageUtils;
import com.nokia.utils.RData;
import com.nokia.utils.excel.BeanCopyUtils;
import com.nokia.utils.excel.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/report")
public class reportController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(reportController.class);

    @Autowired
    private TietaChuzhangXdService tietaChuzhangXdService;


    @Autowired
    private BranchDailyReportService branchDailyReportService;

    @Autowired
    private CountyDailyReportService countyDailyReportService;

    @PostMapping("/tietaChuzhangUpload")
    public RData tietaChuzhangUpload(@RequestParam("file") MultipartFile file) {
        long startTime = System.currentTimeMillis();
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        logger.info(suffix);

        try {
            List<TietaChuzhangXd> list = ExcelUtil.readExcel(file, TietaChuzhangXd.class, 1, 2);
            for (TietaChuzhangXd tietaChuzhang : list) {
                tietaChuzhangXdService.save(tietaChuzhang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long haoshi = (endTime - startTime) / 1000;
        logger.info("耗时" + haoshi + "秒。");
        return RData.ok("操作成功,耗时" + haoshi + "秒。");
    }

    @GetMapping("/queryBranchDailyReport")
    public RData queryBranchDailyReport(@RequestParam Map<String, Object> params) {
        String queryParamString = params.get("queryParam").toString();
        Map<String, Object> queryParams = JSON.parseObject(queryParamString, Map.class);
        PageUtils page = branchDailyReportService.selectDataByParam(params, queryParams);
        return RData.ok().put("page", page);
    }

    @GetMapping("/queryCountyDailyReport")
    public RData queryCountyDailyReport(@RequestParam Map<String, Object> params) {
        String queryParamString = params.get("queryParam").toString();
        Map<String, Object> queryParams = JSON.parseObject(queryParamString, Map.class);
        PageUtils page = countyDailyReportService.selectDataByParam(params, queryParams);
        return RData.ok().put("page", page);
    }

    @GetMapping("/exportBranchReport")
    public void exportBranchReport(@RequestParam("branchQueryDate") String branchQueryDate,
                                 HttpServletResponse response) {
        Map<String, Object> queryParams = new HashMap<>();
        Map<String, Object> pageParams = new HashMap<>();
        pageParams.put("page", "1");
        pageParams.put("limit", "10000");

        queryParams.put("branchQueryDate", branchQueryDate);

        PageUtils page = branchDailyReportService.selectDataByParam(pageParams, queryParams);
        List<BranchDailyReport> checkList = BeanCopyUtils.convert(page.getList(), BranchDailyReport.class);
        ExcelUtil.writeExcel(response, checkList, "branchDailyReport_export", "sheet1", ExcelTypeEnum.XLSX, BranchDailyReport.class);
    }

    @GetMapping("/exportCountyReport")
    public void exportCountyReport(@RequestParam("countyQueryDate") String countyQueryDate,
                                   HttpServletResponse response) {
        Map<String, Object> queryParams = new HashMap<>();
        Map<String, Object> pageParams = new HashMap<>();
        pageParams.put("page", "1");
        pageParams.put("limit", "10000");

        queryParams.put("countyQueryDate", countyQueryDate);

        PageUtils page = countyDailyReportService.selectDataByParam(pageParams, queryParams);
        List<CountyDailyReport> checkList = BeanCopyUtils.convert(page.getList(), CountyDailyReport.class);
        ExcelUtil.writeExcel(response, checkList, "countyDailyReport_export", "sheet1", ExcelTypeEnum.XLSX, CountyDailyReport.class);
    }

}
