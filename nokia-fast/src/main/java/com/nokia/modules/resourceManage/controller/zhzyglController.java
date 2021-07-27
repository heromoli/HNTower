package com.nokia.modules.resourceManage.controller;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.nokia.common.exception.RRException;
import com.nokia.modules.resourceManage.entity.*;
import com.nokia.modules.resourceManage.service.*;
import com.nokia.modules.sys.controller.BaseController;
import com.nokia.modules.sys.service.SftpFileService;
import com.nokia.utils.Gps;
import com.nokia.utils.PageUtils;
import com.nokia.utils.RData;
import com.nokia.utils.excel.BeanCopyUtils;
import com.nokia.utils.excel.ExcelUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nokia.utils.PositionUtils.gps84_To_Gcj02;

@RestController
@RequestMapping("/api/zhzygl")
public class zhzyglController extends BaseController {
    private static final Logger logger = Logger.getLogger(zhzyglController.class);

    @Autowired
    private StationAddressManagementService stationManageService;

    @Autowired
    private StationAddressInfoService stationInfoService;

    @Autowired
    private StationAddressInfoNewService stationInfoNewService;

    @Autowired
    private StationTowerInfoService stationTowerService;

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
        logger.info("文件后缀：" + suffix);
        long startTime = System.currentTimeMillis();
        List<StationAddressManagement> list = ExcelUtil.readExcel(file, StationAddressManagement.class, 1, 1);
        logger.info("文件条数：" + list.size());
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
                                     @RequestParam("address") String biz_scene, @RequestParam("longitude") String longitude, @RequestParam("latitude") String latitude,
                                     @RequestParam("rangeValue") String rangeValue,
                                     HttpServletResponse response) {
        Map<String, Object> queryParams = new HashMap<>();
        Map<String, Object> pageParams = new HashMap<>();
        pageParams.put("page", "1");
        pageParams.put("limit", "10000");

        queryParams.put("county", county);
        queryParams.put("station_name", station_name);
        queryParams.put("address", address);
        queryParams.put("biz_scene", biz_scene);
        queryParams.put("longitude", longitude);
        queryParams.put("latitude", latitude);
        queryParams.put("rangeValue", rangeValue);

        PageUtils page = stationManageService.selectDataByParam(pageParams, queryParams);
        List<StationAddressManagement> checkList = BeanCopyUtils.convert(page.getList(), StationAddressManagement.class);
        ExcelUtil.writeExcel(response, checkList, "stationManagement_export", "sheet1", ExcelTypeEnum.XLSX, StationAddressManagement.class);
    }


    @PostMapping("/stationInfoUpload")
    public RData stationInfoUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        long startTime = System.currentTimeMillis();
        List<StationAddressInfo> list = ExcelUtil.readExcel(file, StationAddressInfo.class, 1, 1);
        try {
            for (StationAddressInfo stationAddressInfo : list) {
                stationInfoService.save(stationAddressInfo);
            }
//            stationInfoService.saveBatch(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long haoshi = (endTime - startTime) / 1000;
        logger.info("耗时" + haoshi + "秒。");
        return RData.ok("操作成功,耗时" + haoshi + "秒。");
    }

    @PostMapping("/stationInfoNewUpload")
    public RData stationInfoNewUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        //上传文件
//        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        long startTime = System.currentTimeMillis();
        List<StationAddressInfoNew> list = ExcelUtil.readExcel(file, StationAddressInfoNew.class, 1, 1);
        try {
            for (StationAddressInfoNew stationAddressInfo : list) {
                stationInfoNewService.save(stationAddressInfo);
            }
//            stationInfoService.saveBatch(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long haoshi = (endTime - startTime) / 1000;
        logger.info("耗时" + haoshi + "秒。");
        return RData.ok("操作成功,耗时" + haoshi + "秒。");
    }

    @PostMapping("/towerInfoUpload")
    public RData towerInfoUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        long startTime = System.currentTimeMillis();
        List<StationTowerInfo> list = ExcelUtil.readExcel(file, StationTowerInfo.class, 1, 1);
        try {
//            for (StationAddressInfo stationAddressInfo : list) {
//                stationInfoService.save(stationAddressInfo);
//            }
            stationTowerService.saveBatch(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long haoshi = (endTime - startTime) / 1000;
        logger.info("耗时" + haoshi + "秒。");
        return RData.ok("操作成功,耗时" + haoshi + "秒。");
    }

    @Autowired
    private QueryStationAddressService queryStationAddressService;

    @GetMapping("/queryStationAddressManagement")
    public RData queryStationAddressManagement(@RequestParam Map<String, Object> params) {
        String queryParamString = params.get("queryParam").toString();
        Map<String, Object> queryParams = JSON.parseObject(queryParamString, Map.class);
        List<QueryStationAddress> dataList = queryStationAddressService.selectDataByParam(queryParams);
        List<QueryStationAddress> resultList = new ArrayList<>();

        double longitude = Double.valueOf(queryParams.get("longitude").toString().equals("") ? "0" : queryParams.get("longitude").toString());
        double latitude = Double.valueOf(queryParams.get("latitude").toString().equals("") ? "0" : queryParams.get("latitude").toString());

        Gps queryGps = gps84_To_Gcj02(latitude, longitude);

        for (QueryStationAddress address : dataList) {
            Gps gps = gps84_To_Gcj02(address.getLatitude(), address.getLongitude());
            address.setGcjLatitude(gps.getWgLat());
            address.setGcjLongitude(gps.getWgLon());
            resultList.add(address);
        }

        RData rData = new RData().ok();
        rData.put("queryLat", queryGps.getWgLat());
        rData.put("queryLon", queryGps.getWgLon());
        rData.put("resultList", resultList);
        return rData;
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
        List<StationInfoAmount> dataList = stationAmountService.selectStationAmountByYear();
        List<String> yearList = new ArrayList<>();

        for (StationInfoAmount stationInfoAmount : dataList) {
            yearList.add(stationInfoAmount.getName());
        }

        RData rData = new RData().ok();
        rData.put("amountList", dataList);
        rData.put("nameList", yearList);

        return rData;
    }

    @GetMapping("/getYearlyStationIncrease")
    public RData getYearlyStationIncrease() {
        List<StationInfoAmount> dataList = stationAmountService.selectStationIncreaseByYear();
        List<String> yearList = new ArrayList<>();

        for (StationInfoAmount stationInfoAmount : dataList) {
            yearList.add(stationInfoAmount.getName());
        }

        RData rData = new RData().ok();
        rData.put("amountList", dataList);
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
        String[] regionNameList = new String[amountList.size()];
        for (int i = 0; i < amountList.size(); i++) {
            regionNameList[i] = amountList.get(i).getName();
        }
        RData rData = new RData().ok();
        rData.put("amountList", amountList);
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

        List<TowerSolutionDetail> resultList = new ArrayList<>();
        for (TowerSolutionDetail address : (List<TowerSolutionDetail>) page.getList()) {

            Gps gps = gps84_To_Gcj02(Double.valueOf(address.getLatitude()), Double.valueOf(address.getLongitude()));
            address.setGcjLatitude(gps.getWgLat());
            address.setGcjLongitude(gps.getWgLon());
            resultList.add(address);
        }
        page.setList(resultList);
        return RData.ok().put("page", page);
    }

    @GetMapping("/queryTowerSolutionDetailList")
    public RData queryTowerSolutionDetailList(@RequestParam Map<String, Object> params) {
        String queryParamString = params.get("queryParam").toString();
        Map<String, Object> queryParams = JSON.parseObject(queryParamString, Map.class);
        List<TowerSolutionDetail> towerSolutionDetailList = towerSolutionDetailService.selectDataByParam(queryParams);

        List<TowerSolutionDetail> resultList = new ArrayList<>();
        for (TowerSolutionDetail address : towerSolutionDetailList) {

            Gps gps = gps84_To_Gcj02(Double.valueOf(address.getLatitude()), Double.valueOf(address.getLongitude()));
            address.setGcjLatitude(gps.getWgLat());
            address.setGcjLongitude(gps.getWgLon());
            resultList.add(address);
        }
        return RData.ok().put("list", resultList);
    }

    @GetMapping("/downloadFile")
    public void downloadFile(@RequestParam("county") String county, @RequestParam("solution_name") String solution_name, @RequestParam("plan_form_time") String plan_form_time, HttpServletResponse response) {
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

    @GetMapping("/getStatusGroupCity")
    public RData getStatusGroupCity() {
        List<StationInfoAmount> amountListWhf = stationAmountService.selectStatusGroupCity("未回复");
        List<StationInfoAmount> amountListYxxq = stationAmountService.selectStatusGroupCity("已下需求");
        List<StationInfoAmount> amountListDts = stationAmountService.selectStatusGroupCity("待推送");
        List<StationInfoAmount> amountListDxxq = stationAmountService.selectStatusGroupCity("待下需求");

        RData rData = new RData().ok();
        rData.put("amountListWhf", amountListWhf);
        rData.put("amountListYxxq", amountListYxxq);
        rData.put("amountListDts", amountListDts);
        rData.put("amountListDxxq", amountListDxxq);

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

    @GetMapping("/getProjectTypeGroupCity")
    public RData getProjectTypeGroupCity() {
        List<StationInfoAmount> amountListYys = stationAmountService.selectProjectTypeGroupCity("运营商需求");
        List<StationInfoAmount> amountListYysqz = stationAmountService.selectProjectTypeGroupCity("运营商潜在需求");
        List<StationInfoAmount> amountListWy = stationAmountService.selectProjectTypeGroupCity("物业需求");
        List<StationInfoAmount> amountListZf = stationAmountService.selectProjectTypeGroupCity("政府需求");

        RData rData = new RData().ok();
        rData.put("amountListYys", amountListYys);
        rData.put("amountListYysqz", amountListYysqz);
        rData.put("amountListWy", amountListWy);
        rData.put("amountListZf", amountListZf);

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

    @GetMapping("/getCityGuihuaByBuildType")
    public RData getCityGuihuaByBuildType() {
        List<StationInfoAmount> amountListCllj = stationAmountService.selectCityGhCllj();
        List<StationInfoAmount> amountListXjzd = stationAmountService.selectCityGhXjzd();

        RData rData = new RData().ok();
        rData.put("amountListCllj", amountListCllj);
        rData.put("amountListXjzd", amountListXjzd);
        return rData;
    }

    @GetMapping("/getCityGuihuaByStationType")
    public RData getCityGuihuaByStationType() {
        List<StationInfoAmount> amountListHz = stationAmountService.selectCityGhHzwldw();
        List<StationInfoAmount> amountListWz = stationAmountService.selectCityGhWzwldw();
        List<StationInfoAmount> amountListSf = stationAmountService.selectCityGhSfwldw();

        RData rData = new RData().ok();
        rData.put("amountListHz", amountListHz);
        rData.put("amountListWz", amountListWz);
        rData.put("amountListSf", amountListSf);
        return rData;
    }

    @GetMapping("/getCityGuihuaByOperator")
    public RData getCityGuihuaByOperator() {
        List<StationInfoAmount> amountListYd = stationAmountService.selectCityGhYddw();
        List<StationInfoAmount> amountListLt = stationAmountService.selectCityGhLtdw();
        List<StationInfoAmount> amountListDx = stationAmountService.selectCityGhDxdw();

        RData rData = new RData().ok();
        rData.put("amountListYd", amountListYd);
        rData.put("amountListLt", amountListLt);
        rData.put("amountListDx", amountListDx);
        return rData;
    }

    @GetMapping("/getCityGuihuaByNetType")
    public RData getCityGuihuaByNetType() {
        List<StationInfoAmount> amountList4G = stationAmountService.selectCityGh4Gdw();
        List<StationInfoAmount> amountList5G = stationAmountService.selectCityGh5Gdw();

        RData rData = new RData().ok();
        rData.put("amountList4G", amountList4G);
        rData.put("amountList5G", amountList5G);
        return rData;
    }

    @GetMapping("/getCityXuqiuByBuildType")
    public RData getCityXuqiuByBuildType() {
        List<StationInfoAmount> amountListCllj = stationAmountService.selectCityXqCllj();
        List<StationInfoAmount> amountListXjzd = stationAmountService.selectCityXqXjzd();

        RData rData = new RData().ok();
        rData.put("amountListCllj", amountListCllj);
        rData.put("amountListXjzd", amountListXjzd);
        return rData;
    }

    @GetMapping("/getCityXuqiuByStationType")
    public RData getCityXuqiuByStationType() {
        List<StationInfoAmount> amountListHz = stationAmountService.selectCityXqHzwldw();
        List<StationInfoAmount> amountListWz = stationAmountService.selectCityXqWzwldw();
        List<StationInfoAmount> amountListSf = stationAmountService.selectCityXqSfwldw();

        RData rData = new RData().ok();
        rData.put("amountListHz", amountListHz);
        rData.put("amountListWz", amountListWz);
        rData.put("amountListSf", amountListSf);
        return rData;
    }

    @GetMapping("/getCityXuqiuByOperator")
    public RData getCityXuqiuByOperator() {
        List<StationInfoAmount> amountListYd = stationAmountService.selectCityXqYddw();
        List<StationInfoAmount> amountListLt = stationAmountService.selectCityXqLtdw();
        List<StationInfoAmount> amountListDx = stationAmountService.selectCityXqDxdw();

        RData rData = new RData().ok();
        rData.put("amountListYd", amountListYd);
        rData.put("amountListLt", amountListLt);
        rData.put("amountListDx", amountListDx);
        return rData;
    }

    @GetMapping("/getCityXuqiuByNetType")
    public RData getCityXuqiuByNetType() {
        List<StationInfoAmount> amountList4G = stationAmountService.selectCityXq4Gdw();
        List<StationInfoAmount> amountList5G = stationAmountService.selectCityXq5Gdw();

        RData rData = new RData().ok();
        rData.put("amountList4G", amountList4G);
        rData.put("amountList5G", amountList5G);
        return rData;
    }

    @Autowired
    private BaseStationInfoService baseStationInfoService;

    @PostMapping("/baseStationInfoUpload")
    public RData supervisionUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }

        //上传文件
//        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        StringBuilder message = new StringBuilder();
        long startTime = System.currentTimeMillis();
        List<BaseStationInfo> list = ExcelUtil.readExcel(file, BaseStationInfo.class, 2, 1);

        for (BaseStationInfo info : list) {
            try {
                baseStationInfoService.saveOrUpdate(info);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }

        long endTime = System.currentTimeMillis();
        long haoshi = (endTime - startTime) / 1000;
        logger.info("操作成功,耗时" + haoshi + "秒。");
        return RData.ok("操作成功,耗时" + haoshi + "秒。其中异常数据：{" + message + "}");
    }
}
