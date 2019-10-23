package com.nokia.modules.resourceManage.controller;

import com.nokia.modules.resourceManage.entity.StationInfoAmount;
import com.nokia.modules.resourceManage.entity.StationInfoRate;
import com.nokia.modules.resourceManage.entity.VennEntity;
import com.nokia.modules.resourceManage.service.StationAmountService;
import com.nokia.modules.resourceManage.service.StationInfoRateService;
import com.nokia.modules.sys.controller.BaseController;
import com.nokia.utils.RData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/zhzygl")
public class zhzyglController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(zhzyglController.class);

    @Autowired
    private StationAmountService stationAmountService;

    @Autowired
    private StationInfoRateService stationRateService;

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
            if(dataList.get(i).getName().equals("海口市")){
                regionNameList[0] = dataList.get(i).getName();
                amountList.add(dataList.get(i));
            }else if (dataList.get(i).getName().equals("三亚市")){
                regionNameList[1] = dataList.get(i).getName();
                amountList.add(dataList.get(i));
            }else if (dataList.get(i).getName().equals("儋州市")){
                regionNameList[2] = dataList.get(i).getName();
                amountList.add(dataList.get(i));
            }else if (dataList.get(i).getName().equals("文昌市")){
                regionNameList[3] = dataList.get(i).getName();
                amountList.add(dataList.get(i));
            }else if (dataList.get(i).getName().equals("琼海市")){
                regionNameList[4] = dataList.get(i).getName();
                amountList.add(dataList.get(i));
            }else if (dataList.get(i).getName().equals("万宁市")){
                regionNameList[5] = dataList.get(i).getName();
                amountList.add(dataList.get(i));
            }
        }
        RData rData = new RData().ok();
        rData.put("amountList", amountList);
        rData.put("regionNameList", regionNameList);
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
        String[] yearList = new String[8];
        for (long i = 1L; i <= 8L; i++) {
            LocalDate localDate = LocalDate.now().minusYears(i);
            String localYear = localDate.toString().substring(0, 4);
            yearList[(int) i - 1] = localYear;
        }

        for (StationInfoAmount stationInfoAmount : dataList) {
            if (stationInfoAmount.getName().equals(yearList[0])) {
                amountList.add(stationInfoAmount);
            }else if (stationInfoAmount.getName().equals(yearList[1])) {
                amountList.add(stationInfoAmount);
            }else if (stationInfoAmount.getName().equals(yearList[2])) {
                amountList.add(stationInfoAmount);
            }else if (stationInfoAmount.getName().equals(yearList[3])) {
                amountList.add(stationInfoAmount);
            }else if (stationInfoAmount.getName().equals(yearList[4])) {
                amountList.add(stationInfoAmount);
            }else if (stationInfoAmount.getName().equals(yearList[5])) {
                amountList.add(stationInfoAmount);
            }else if (stationInfoAmount.getName().equals(yearList[6])) {
                amountList.add(stationInfoAmount);
            }else if (stationInfoAmount.getName().equals(yearList[7])) {
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
            }else if (stationInfoAmount.getName().equals(monthList[1])) {
                amountList.add(stationInfoAmount);
            }else if (stationInfoAmount.getName().equals(monthList[2])) {
                amountList.add(stationInfoAmount);
            }else if (stationInfoAmount.getName().equals(monthList[3])) {
                amountList.add(stationInfoAmount);
            }else if (stationInfoAmount.getName().equals(monthList[4])) {
                amountList.add(stationInfoAmount);
            }else if (stationInfoAmount.getName().equals(monthList[5])) {
                amountList.add(stationInfoAmount);
            }else if (stationInfoAmount.getName().equals(monthList[6])) {
                amountList.add(stationInfoAmount);
            }else if (stationInfoAmount.getName().equals(monthList[7])) {
                amountList.add(stationInfoAmount);
            }else if (stationInfoAmount.getName().equals(monthList[8])) {
                amountList.add(stationInfoAmount);
            }else if (stationInfoAmount.getName().equals(monthList[9])) {
                amountList.add(stationInfoAmount);
            }else if (stationInfoAmount.getName().equals(monthList[10])) {
                amountList.add(stationInfoAmount);
            }else if (stationInfoAmount.getName().equals(monthList[11])) {
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
//        logger.info("size:" + amountList.size());
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

}
