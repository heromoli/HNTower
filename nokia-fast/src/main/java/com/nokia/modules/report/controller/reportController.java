package com.nokia.modules.report.controller;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.nokia.common.exception.RRException;
import com.nokia.modules.report.entity.*;

import com.nokia.modules.report.service.*;
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

    @Autowired
    private OperatorDailyReportService operatorDailyReportService;

    @Autowired
    private BranchDailyReportProService branchDailyReportProService;

    @Autowired
    private CountyDailyReportProService countyDailyReportProService;

    @Autowired
    private OperatorDailyReportProService operatorDailyReportProService;

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

    @GetMapping("/queryOperatorDailyReport")
    public RData queryOperatorDailyReport(@RequestParam Map<String, Object> params) {
        String queryParamString = params.get("queryParam").toString();
        Map<String, Object> queryParams = JSON.parseObject(queryParamString, Map.class);
        PageUtils page = operatorDailyReportService.selectDataByParam(params, queryParams);
        return RData.ok().put("page", page);
    }

    @GetMapping("/queryBranchDailyReportPro")
    public RData queryBranchDailyReportPro(@RequestParam Map<String, Object> params) {
        String queryParamString = params.get("queryParam").toString();
        Map<String, Object> queryParams = JSON.parseObject(queryParamString, Map.class);
        PageUtils page = branchDailyReportProService.selectDataByParam(params, queryParams);
        return RData.ok().put("page", page);
    }

    @GetMapping("/queryCountyDailyReportPro")
    public RData queryCountyDailyReportPro(@RequestParam Map<String, Object> params) {
        String queryParamString = params.get("queryParam").toString();
        Map<String, Object> queryParams = JSON.parseObject(queryParamString, Map.class);
        PageUtils page = countyDailyReportProService.selectDataByParam(params, queryParams);
        return RData.ok().put("page", page);
    }

    @GetMapping("/queryOperatorDailyReportPro")
    public RData queryOperatorDailyReportPro(@RequestParam Map<String, Object> params) {
        String queryParamString = params.get("queryParam").toString();
        Map<String, Object> queryParams = JSON.parseObject(queryParamString, Map.class);
        PageUtils page = operatorDailyReportProService.selectDataByParam(params, queryParams);
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
        ExcelUtil.writeExcel(response, checkList, "branchDaily5GReport_export", "sheet1", ExcelTypeEnum.XLSX, BranchDailyReport.class);
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
        ExcelUtil.writeExcel(response, checkList, "countyDaily5GReport_export", "sheet1", ExcelTypeEnum.XLSX, CountyDailyReport.class);
    }

    @GetMapping("/exportOperatorReport")
    public void exportOperatorReport(@RequestParam("operatorQueryDate") String operatorQueryDate,
                                   HttpServletResponse response) {
        Map<String, Object> queryParams = new HashMap<>();
        Map<String, Object> pageParams = new HashMap<>();
        pageParams.put("page", "1");
        pageParams.put("limit", "10000");

        queryParams.put("operatorQueryDate", operatorQueryDate);

        PageUtils page = operatorDailyReportService.selectDataByParam(pageParams, queryParams);
        List<OperatorDailyReport> checkList = BeanCopyUtils.convert(page.getList(), OperatorDailyReport.class);
        ExcelUtil.writeExcel(response, checkList, "operatorDaily5GReport_export", "sheet1", ExcelTypeEnum.XLSX, OperatorDailyReport.class);
    }

    @GetMapping("/exportBranchReportPro")
    public void exportBranchReportPro(@RequestParam("branchQueryDate") String branchQueryDate,
                                   HttpServletResponse response) {
        Map<String, Object> queryParams = new HashMap<>();
        Map<String, Object> pageParams = new HashMap<>();
        pageParams.put("page", "1");
        pageParams.put("limit", "10000");

        queryParams.put("branchQueryDate", branchQueryDate);

        PageUtils page = branchDailyReportProService.selectDataByParam(pageParams, queryParams);
        List<BranchDailyReportPro> checkList = BeanCopyUtils.convert(page.getList(), BranchDailyReportPro.class);
        ExcelUtil.writeExcel(response, checkList, "BranchDailyReportPro_export", "sheet1", ExcelTypeEnum.XLSX, BranchDailyReportPro.class);
    }

    @GetMapping("/exportCountyReportPro")
    public void exportCountyReportPro(@RequestParam("countyQueryDate") String countyQueryDate,
                                   HttpServletResponse response) {
        Map<String, Object> queryParams = new HashMap<>();
        Map<String, Object> pageParams = new HashMap<>();
        pageParams.put("page", "1");
        pageParams.put("limit", "10000");

        queryParams.put("countyQueryDate", countyQueryDate);

        PageUtils page = countyDailyReportProService.selectDataByParam(pageParams, queryParams);
        List<CountyDailyReportPro> checkList = BeanCopyUtils.convert(page.getList(), CountyDailyReportPro.class);
        ExcelUtil.writeExcel(response, checkList, "CountyDailyReportPro_export", "sheet1", ExcelTypeEnum.XLSX, CountyDailyReportPro.class);
    }

    @GetMapping("/exportOperatorReportPro")
    public void exportOperatorReportPro(@RequestParam("operatorQueryDate") String operatorQueryDate,
                                      HttpServletResponse response) {
        Map<String, Object> queryParams = new HashMap<>();
        Map<String, Object> pageParams = new HashMap<>();
        pageParams.put("page", "1");
        pageParams.put("limit", "10000");

        queryParams.put("operatorQueryDate", operatorQueryDate);

        PageUtils page = operatorDailyReportProService.selectDataByParam(pageParams, queryParams);
        List<OperatorDailyReportPro> checkList = BeanCopyUtils.convert(page.getList(), OperatorDailyReportPro.class);
        ExcelUtil.writeExcel(response, checkList, "OperatorDailyReportPro_export", "sheet1", ExcelTypeEnum.XLSX, OperatorDailyReportPro.class);
    }

}
