package com.nokia.modules.resourceManage.controller;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.nokia.common.exception.RRException;
import com.nokia.modules.resourceManage.entity.*;
import com.nokia.modules.resourceManage.service.*;
import com.nokia.modules.sys.controller.BaseController;
import com.nokia.modules.sys.service.SftpFileService;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/zhzygl")
public class zhzyglController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(zhzyglController.class);

    @Autowired
    private StationAddressManagementService stationManageService;

    @Autowired
    private StationAmountService stationAmountService;

    @Autowired
    private StationInfoRateService stationRateService;

    @Autowired
    private TowerSolutionCollectionService towerSolutionCollectionService;

    @Autowired
    private TowerSolutionDetailService towerSolutionDetailService;

    @Autowired
    private TowerSolutionSubmittedService sdService;

    @Autowired
    private TowerSolutionStatisticService statisticService;

    @Autowired
    private SftpFileService sftpFileService;

    //站址管理表上传
    @PostMapping("/stationManageUpload")
    public RData stationManageUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        long startTime = System.currentTimeMillis();
        List<StationAddressManagement> list = ExcelUtil.readExcel(file, StationAddressManagement.class, 1, 2);
        try {
            for (StationAddressManagement stationManagement : list) {
                stationManageService.save(stationManagement);
            }
//            stationManageService.saveBatch(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long haoshi = (endTime - startTime) / 1000;
        logger.info("耗时" + haoshi + "秒。");
        return RData.ok("操作成功,耗时" + haoshi + "秒。");
    }

    @GetMapping("/exportStationAddress")
    public void exportStationAddress(@RequestParam("county") String county, @RequestParam("station_name") String station_name, @RequestParam("address") String address,
                                     @RequestParam("longitude") String longitude, @RequestParam("latitude") String latitude, @RequestParam("rangeValue") String rangeValue,
                                     HttpServletResponse response) {
        Map<String, Object> queryParams = new HashMap<>();
        Map<String, Object> pageParams = new HashMap<>();
        pageParams.put("page", "1");
        pageParams.put("limit", "1000");

        queryParams.put("county", county);
        queryParams.put("station_name", station_name);
        queryParams.put("address", address);
        queryParams.put("longitude", longitude);
        queryParams.put("latitude", latitude);
        queryParams.put("rangeValue", rangeValue);

        PageUtils page = stationManageService.selectDataByParam(pageParams, queryParams);
        List<StationAddressManagement> checkList = BeanCopyUtils.convert(page.getList(), StationAddressManagement.class);
        ExcelUtil.writeExcel(response, checkList, "stationManagement_export", "sheet1", ExcelTypeEnum.XLSX, StationAddressManagement.class);
    }

    @GetMapping("/queryStationAddressManagement")
    public RData queryStationAddressManagement(@RequestParam Map<String, Object> params) {
        String queryParamString = params.get("queryParam").toString();
        Map<String, Object> queryParams = JSON.parseObject(queryParamString, Map.class);
        PageUtils page = stationManageService.selectDataByParam(params, queryParams);
        return RData.ok().put("page", page);
    }

    @GetMapping("/stationAddressManagementList")
    public RData stationAddressManagementList(@RequestParam Map<String, Object> params) {
        PageUtils page = stationManageService.queryPage(params);
        return RData.ok().put("page", page);
    }

    @GetMapping("/getStationCounty")
    public RData getStationCounty() {
        List<String> countyList = stationManageService.getStationCounty();
        return RData.ok().put("countyList", countyList);
    }

    @GetMapping("/getBizScene")
    public RData getBizScene() {
        List<String> countyList = stationManageService.getBizScene();
        return RData.ok().put("sceneList", countyList);
    }

    @GetMapping("/getStationAmount")
    public RData getStationAmount() {
        List<StationInfoAmount> amountList = stationAmountService.selectStationAmountData();
        String[] regionNameList = new String[amountList.size()];
        for (int i = 0; i < amountList.size(); i++) {
            regionNameList[i] = amountList.get(i).getName();
        }
        RData rData = new RData().ok();
        rData.put("amountList", amountList);
        rData.put("regionNameList", regionNameList);
        return rData;
    }

    @GetMapping("/getStationAmount6")
    public RData getStationAmountTop6() {
        List<StationInfoAmount> dataList = stationAmountService.selectStationAmountData();
        List<StationInfoAmount> amountList = new ArrayList<>();
        String[] regionNameList = new String[6];
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).getName().equals("海口市")) {
                regionNameList[0] = dataList.get(i).getName();
                amountList.add(dataList.get(i));
            } else if (dataList.get(i).getName().equals("三亚市")) {
                regionNameList[1] = dataList.get(i).getName();
                amountList.add(dataList.get(i));
            } else if (dataList.get(i).getName().equals("儋州市")) {
                regionNameList[2] = dataList.get(i).getName();
                amountList.add(dataList.get(i));
            } else if (dataList.get(i).getName().equals("文昌市")) {
                regionNameList[3] = dataList.get(i).getName();
                amountList.add(dataList.get(i));
            } else if (dataList.get(i).getName().equals("琼海市")) {
                regionNameList[4] = dataList.get(i).getName();
                amountList.add(dataList.get(i));
            } else if (dataList.get(i).getName().equals("万宁市")) {
                regionNameList[5] = dataList.get(i).getName();
                amountList.add(dataList.get(i));
            }
        }
        RData rData = new RData().ok();
        rData.put("amountList", amountList);
        rData.put("regionNameList", regionNameList);
        rData.put("dataList", dataList);

        return rData;
    }

    @GetMapping("/getCountyAmount")
    public RData getCountyAmount() {
        List<StationInfoAmount> amountList = stationAmountService.selectCountyAmountData();
        String[] regionNameList = new String[amountList.size()];
        for (int i = 0; i < amountList.size(); i++) {
            regionNameList[i] = amountList.get(i).getName();
        }
        RData rData = new RData().ok();
        rData.put("amountList", amountList);
        rData.put("regionNameList", regionNameList);
        return rData;
    }

    @GetMapping("/getChanquanAmount")
    public RData getChanquanAmount() {
        List<StationInfoAmount> amountList = stationAmountService.selectChanquanAmount();
        return RData.ok().put("amountList", amountList);
    }

    @GetMapping("/getCityStationAmount")
    public RData getCityStationAmount() {
        List<StationInfoAmount> amountList = stationAmountService.selectCityStationAmount();
        return RData.ok().put("amountList", amountList);
    }

    @GetMapping("/getStationShareAmount")
    public RData getStationShareAmount() {
        int[] amountList = stationAmountService.selectStationShareAmount();
        return RData.ok().put("amountList", amountList);
    }

    @GetMapping("/getStationAloneAmount")
    public RData getStationAloneAmount() {
        List<StationInfoAmount> amountList = stationAmountService.selectStationAloneAmount();
        return RData.ok().put("amountList", amountList);
    }

    @GetMapping("/getStationShareAmountVenn")
    public RData getStationShareAmountVenn() {
        VennEntity[] amountList = stationAmountService.selectStationShareAmountVenn();
        return RData.ok().put("amountList", amountList);
    }

    @GetMapping("/getBizSceneAmount")
    public RData getBizSceneAmount() {
        int[] amountList = stationAmountService.selectBizSceneAmount();
        return RData.ok().put("amountList", amountList);
    }

    @GetMapping("/getYearlyStationAmount")
    public RData getYearlyStationAmount() {
        List<StationInfoAmount> amountList = new ArrayList<>();
        List<StationInfoAmount> dataList = stationAmountService.selectStationAmountByYear();
        String[] yearList = new String[10];
        for (long i = 1L; i <= 10L; i++) {
            LocalDate localDate = LocalDate.now().minusYears(i);
            String localYear = localDate.toString().substring(0, 4);
            yearList[(int) i - 1] = localYear;
        }

        for (StationInfoAmount stationInfoAmount : dataList) {
            if (stationInfoAmount.getName().equals(yearList[0])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(yearList[1])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(yearList[2])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(yearList[3])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(yearList[4])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(yearList[5])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(yearList[6])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(yearList[7])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(yearList[8])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(yearList[9])) {
                amountList.add(stationInfoAmount);
            }
        }

        RData rData = new RData().ok();
        rData.put("amountList", amountList);
        rData.put("nameList", yearList);

        return rData;
    }

    @GetMapping("/getYearlyStationIncrease")
    public RData getYearlyStationIncrease() {
        List<StationInfoAmount> amountList = new ArrayList<>();
        List<StationInfoAmount> dataList = stationAmountService.selectStationIncreaseByYear();
        String[] yearList = new String[10];
        for (long i = 1L; i <= 10L; i++) {
            LocalDate localDate = LocalDate.now().minusYears(i);
            String localYear = localDate.toString().substring(0, 4);
            yearList[(int) i - 1] = localYear;
        }

        for (StationInfoAmount stationInfoAmount : dataList) {
            if (stationInfoAmount.getName().equals(yearList[0])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(yearList[1])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(yearList[2])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(yearList[3])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(yearList[4])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(yearList[5])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(yearList[6])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(yearList[7])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(yearList[8])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(yearList[9])) {
                amountList.add(stationInfoAmount);
            }
        }

        RData rData = new RData().ok();
        rData.put("amountList", amountList);
        rData.put("nameList", yearList);

        return rData;
    }

    @GetMapping("/getMonthlyStationAmount")
    public RData getMonthlyStationAmount() {
        List<StationInfoAmount> amountList = new ArrayList<>();
        List<StationInfoAmount> dataList = stationAmountService.selectStationAmountByMonth();
        String[] monthList = new String[12];

        for (long i = 1L; i <= 12L; i++) {
            LocalDate localDate = LocalDate.now().minusMonths(i);
            String localMonth = localDate.toString().substring(0, 7);
            monthList[(int) i - 1] = localMonth;
        }

        for (StationInfoAmount stationInfoAmount : dataList) {
            if (stationInfoAmount.getName().equals(monthList[0])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(monthList[1])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(monthList[2])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(monthList[3])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(monthList[4])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(monthList[5])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(monthList[6])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(monthList[7])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(monthList[8])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(monthList[9])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(monthList[10])) {
                amountList.add(stationInfoAmount);
            } else if (stationInfoAmount.getName().equals(monthList[11])) {
                amountList.add(stationInfoAmount);
            }
        }

        RData rData = new RData().ok();
        rData.put("amountList", amountList);
        rData.put("nameList", monthList);
        return rData;
    }

    @GetMapping("/getStationSourceAmount")
    public RData getStationSourceAmount() {
        List<StationInfoAmount> amountList = stationAmountService.selectStationSourceAmount();
        return RData.ok().put("amountList", amountList);
    }

    @GetMapping("/getCalculateShareRate")
    public RData calculateShareRate() {
        double[] amountList = stationRateService.calculateShareRate();
        return RData.ok().put("amountList", amountList);
    }

    @GetMapping("/getOperatorStationAmount")
    public RData getOperatorStationAmount() {
        List<StationInfoAmount> amountList = stationAmountService.selectOperatorStationAmount();
        return RData.ok().put("amountList", amountList);
    }

    @GetMapping("/getStation5GAmount")
    public RData getStation5GAmount() {
        List<StationInfoAmount> amountList = stationAmountService.selectCountyStation5GAmount();
        String[] regionNameList = new String[amountList.size()];
        for (int i = 0; i < amountList.size(); i++) {
            regionNameList[i] = amountList.get(i).getName();
        }
        RData rData = new RData().ok();
        rData.put("amountList", amountList);
        rData.put("countyList", regionNameList);
        return rData;
    }

    @GetMapping("/getMonthlyStation5GAmount")
    public RData getMonthlyStation5GAmount() {
        List<StationInfoAmount> amountList = stationAmountService.selectStation5gAmountByMonth();
        String[] regionNameList = new String[amountList.size() - 5];
        for (int i = 5; i < amountList.size(); i++) {
            regionNameList[i - 5] = amountList.get(i).getName();
        }
        RData rData = new RData().ok();
        rData.put("amountList", amountList.subList(5, amountList.size()));
        rData.put("nameList", regionNameList);
        return rData;
    }

    @PostMapping("/towerSolutionCollectionUpload")
    public RData towerSolutionCollectionUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        //上传文件
        List<TowerSolutionCollection> list = ExcelUtil.readExcel(file, TowerSolutionCollection.class, 4, 8);
        if (list.size() > 0) {
            towerSolutionCollectionService.deleteAll();
        }
        for (TowerSolutionCollection collection : list) {
            try {
                towerSolutionCollectionService.save(collection);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
        return RData.ok();
    }

    @PostMapping("/towerSolutionDetailUpload")
    public RData towerSolutionDetailUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        //上传文件
        List<TowerSolutionDetail> list = ExcelUtil.readExcel(file, TowerSolutionDetail.class, 5, 0);
        if (list.size() > 0) {
            towerSolutionDetailService.deleteAll();
        }
        for (TowerSolutionDetail detail : list) {
            try {
                towerSolutionDetailService.save(detail);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
        return RData.ok();
    }

    @PostMapping("/submittedDemandUpload")
    public RData submittedDemandUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        //上传文件
        List<TowerSolutionSubmitted> list = ExcelUtil.readExcel(file, TowerSolutionSubmitted.class, 7, 1);
//        if (list.size() > 0) {
//            sdService.deleteAll();
//        }
        for (TowerSolutionSubmitted collection : list) {
            try {
                sdService.save(collection);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
        return RData.ok();
    }

    @GetMapping("/selectProjectName")
    public RData selectProjectName() {
        List<String> projectName = towerSolutionCollectionService.selectProjectName();
        return RData.ok().put("projectNameList", projectName);
    }

    @GetMapping("/queryTowerSolution")
    public RData queryTowerSolution(@RequestParam Map<String, Object> params) {
        String queryParamString = params.get("queryParam").toString();
        Map<String, Object> queryParams = JSON.parseObject(queryParamString, Map.class);
        PageUtils page = towerSolutionCollectionService.selectDataByParam(params, queryParams);
        return RData.ok().put("page", page);
    }

    @GetMapping("/queryTowerSolutionDetailPage")
    public RData queryTowerSolutionDetailPage(@RequestParam Map<String, Object> params) {
        String queryParamString = params.get("queryParam").toString();
        Map<String, Object> queryParams = JSON.parseObject(queryParamString, Map.class);
        PageUtils page = towerSolutionDetailService.selectDataByParam(params, queryParams);
        return RData.ok().put("page", page);
    }

    @GetMapping("/queryTowerSolutionDetailList")
    public RData queryTowerSolutionDetailList(@RequestParam Map<String, Object> params) {
        String queryParamString = params.get("queryParam").toString();
        Map<String, Object> queryParams = JSON.parseObject(queryParamString, Map.class);
        List<TowerSolutionDetail> towerSolutionDetailList = towerSolutionDetailService.selectDataByParam(queryParams);
        return RData.ok().put("list", towerSolutionDetailList);
    }

    @GetMapping("/downloadFile")
    public void downloadFile(@RequestParam("county") String county, @RequestParam("solution_name") String solution_name, @RequestParam("plan_form_time") String plan_form_time, HttpServletResponse response) {
        logger.info(county + " " + solution_name + " " + plan_form_time);
        sftpFileService.downloadFile(county, solution_name, plan_form_time, response);

    }

    @GetMapping("/queryTowerSolutionStatistic")
    public RData queryTowerSolutionStatistic(@RequestParam Map<String, Object> params) {
        String queryParamString = params.get("queryParam").toString();
        Map<String, Object> queryParams = JSON.parseObject(queryParamString, Map.class);
        List<TowerSolutionStatistics> statisticsList = statisticService.selectDataByParam(params, queryParams);
        return RData.ok().put("amountList", statisticsList);
    }

    @GetMapping("/getProvinceTuisongStatus")
    public RData getProvinceTuisongStatus() {
        List<StationInfoAmount> amountList = stationAmountService.selectProvinceTuisong();
        String[] statusNameList = new String[amountList.size()];
        for (int i = 0; i < amountList.size(); i++) {
            statusNameList[i] = amountList.get(i).getName();
        }
        RData rData = new RData().ok();
        rData.put("amountList", amountList);
        rData.put("nameList", statusNameList);
        return rData;
    }

    @GetMapping("/getProjectType")
    public RData getProjectType(@RequestParam String cityBranchCompany) {
        List<StationInfoAmount> amountList = stationAmountService.selectByProjectType(cityBranchCompany);
        String[] nameList = new String[amountList.size()];
        for (int i = 0; i < amountList.size(); i++) {
            nameList[i] = amountList.get(i).getName();
        }
        RData rData = new RData().ok();

        rData.put("nameList", nameList);
        rData.put("amountList", amountList);

        return rData;
    }

    @GetMapping("/getGuihuaByBuildType")
    public RData getGuihuaByBuildType(@RequestParam String cityBranchCompany) {
        List<StationInfoAmount> amountList = stationAmountService.selectGhBuildType(cityBranchCompany);
        String[] nameList = new String[amountList.size()];
        int totalData = 0;
        for (int i = 0; i < amountList.size(); i++) {
            nameList[i] = amountList.get(i).getName();
            totalData += amountList.get(i).getValue();
        }
        RData rData = new RData().ok();

        rData.put("nameList", nameList);
        rData.put("amountList", amountList);
        rData.put("totalData", totalData);

        return rData;
    }

    @GetMapping("/getGuihuaByStationType")
    public RData getGuihuaByStationType(@RequestParam String cityBranchCompany) {
        List<StationInfoAmount> amountList = stationAmountService.selectGhStationType(cityBranchCompany);
        String[] nameList = new String[amountList.size()];
        int totalData = 0;
        for (int i = 0; i < amountList.size(); i++) {
            nameList[i] = amountList.get(i).getName();
            totalData += amountList.get(i).getValue();
        }
        RData rData = new RData().ok();

        rData.put("nameList", nameList);
        rData.put("amountList", amountList);
        rData.put("totalData", totalData);

        return rData;
    }

    @GetMapping("/getGuihuaByOperator")
    public RData getGuihuaByOperator(@RequestParam String cityBranchCompany) {
        List<StationInfoAmount> amountList = stationAmountService.selectGhOperator(cityBranchCompany);
        String[] nameList = new String[amountList.size()];
        int totalData = 0;
        for (int i = 0; i < amountList.size(); i++) {
            nameList[i] = amountList.get(i).getName();
            totalData += amountList.get(i).getValue();
        }
        RData rData = new RData().ok();

        rData.put("nameList", nameList);
        rData.put("amountList", amountList);
        rData.put("totalData", totalData);

        return rData;
    }

    @GetMapping("/getGuihuaByNetType")
    public RData getGuihuaByNetType(@RequestParam String cityBranchCompany) {
        List<StationInfoAmount> amountList = stationAmountService.selectGhNetType(cityBranchCompany);
        String[] nameList = new String[amountList.size()];
        int totalData = 0;
        for (int i = 0; i < amountList.size(); i++) {
            nameList[i] = amountList.get(i).getName();
            totalData += amountList.get(i).getValue();
        }
        RData rData = new RData().ok();

        rData.put("nameList", nameList);
        rData.put("amountList", amountList);
        rData.put("totalData", totalData);
        return rData;
    }

    @GetMapping("/getXuqiuByBuildType")
    public RData getXuqiuByBuildType(@RequestParam String cityBranchCompany) {
        List<StationInfoAmount> amountList = stationAmountService.selectXqBuildType(cityBranchCompany);
        String[] nameList = new String[amountList.size()];
        int totalData = 0;
        for (int i = 0; i < amountList.size(); i++) {
            nameList[i] = amountList.get(i).getName();
            totalData += amountList.get(i).getValue();
        }
        RData rData = new RData().ok();

        rData.put("nameList", nameList);
        rData.put("amountList", amountList);
        rData.put("totalData", totalData);

        return rData;
    }


    @GetMapping("/getXuqiuByStationType")
    public RData getXuqiuByStationType(@RequestParam String cityBranchCompany) {
        List<StationInfoAmount> amountList = stationAmountService.selectXqStationType(cityBranchCompany);
        String[] nameList = new String[amountList.size()];
        int totalData = 0;
        for (int i = 0; i < amountList.size(); i++) {
            nameList[i] = amountList.get(i).getName();
            totalData += amountList.get(i).getValue();
        }
        RData rData = new RData().ok();

        rData.put("nameList", nameList);
        rData.put("amountList", amountList);
        rData.put("totalData", totalData);

        return rData;
    }

    @GetMapping("/getXuqiuByOperator")
    public RData getXuqiuByOperator(@RequestParam String cityBranchCompany) {
        List<StationInfoAmount> amountList = stationAmountService.selectXqOperator(cityBranchCompany);
        String[] nameList = new String[amountList.size()];
        int totalData = 0;
        for (int i = 0; i < amountList.size(); i++) {
            nameList[i] = amountList.get(i).getName();
            totalData += amountList.get(i).getValue();
        }
        RData rData = new RData().ok();

        rData.put("nameList", nameList);
        rData.put("amountList", amountList);
        rData.put("totalData", totalData);

        return rData;
    }

    @GetMapping("/getXuqiuByNetType")
    public RData getXuqiuByNetType(@RequestParam String cityBranchCompany) {
        List<StationInfoAmount> amountList = stationAmountService.selectXqNetType(cityBranchCompany);
        String[] nameList = new String[amountList.size()];
        int totalData = 0;
        for (int i = 0; i < amountList.size(); i++) {
            nameList[i] = amountList.get(i).getName();
            totalData += amountList.get(i).getValue();
        }
        RData rData = new RData().ok();

        rData.put("nameList", nameList);
        rData.put("amountList", amountList);
        rData.put("totalData", totalData);
        return rData;
    }
}
