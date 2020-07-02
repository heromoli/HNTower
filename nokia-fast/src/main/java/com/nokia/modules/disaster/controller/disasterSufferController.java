package com.nokia.modules.disaster.controller;

import com.alibaba.fastjson.JSON;
import com.nokia.common.exception.RRException;
import com.nokia.modules.disaster.entity.AlarmInfoAmount;
import com.nokia.modules.disaster.entity.AlarmMessageManagement;
import com.nokia.modules.disaster.entity.DisasterSufferMessage;
import com.nokia.modules.disaster.service.AlarmInfoAmountService;
import com.nokia.modules.disaster.service.AlarmMessageManagementService;
import com.nokia.modules.disaster.service.DisasterSufferDataService;
import com.nokia.modules.disaster.service.DisasterSufferMessageService;
import com.nokia.modules.sys.controller.BaseController;
import com.nokia.utils.PageUtils;
import com.nokia.utils.RData;
import com.nokia.utils.excel.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/disaster")
public class disasterSufferController extends BaseController {

    @Autowired
    private DisasterSufferDataService dsdService;

    @Autowired
    private DisasterSufferMessageService messageService;

    @Autowired
    private AlarmMessageManagementService ammService;

    @Autowired
    private AlarmInfoAmountService aiaService;

    @GetMapping("/queryDisasterSufferMessage")
    public RData queryDisasterSufferMessage(@RequestParam Map<String, Object> params) {
        String queryParamString = params.get("queryParam").toString();
        Map<String, Object> queryParams = JSON.parseObject(queryParamString, Map.class);
        PageUtils page = messageService.selectDataByParam(params, queryParams);
        return RData.ok().put("page", page);
    }

    @GetMapping("/queryAmm")
    public RData queryAmm(@RequestParam Map<String, Object> params) {
        String queryParamString = params.get("queryParam").toString();
        Map<String, Object> queryParams = JSON.parseObject(queryParamString, Map.class);
        List<AlarmMessageManagement>  list = ammService.selectDataByParam(queryParams);
        return RData.ok().put("list", list);
    }

    @GetMapping("/queryAmmPage")
    public RData queryAmmPage(@RequestParam Map<String, Object> params) {
        String queryParamString = params.get("queryParam").toString();
        Map<String, Object> queryParams = JSON.parseObject(queryParamString, Map.class);
        PageUtils page = ammService.selectDataByParam(params, queryParams);
        return RData.ok().put("page", page);
    }

    @PostMapping("/fileUpload")
    public RData fileUpload(@RequestParam("file") MultipartFile file) {
        long startTime = System.currentTimeMillis();
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        logger.info(suffix);

        try {
            List<DisasterSufferMessage> list = ExcelUtil.readExcel(file, DisasterSufferMessage.class, 1, 1);
            for (DisasterSufferMessage shouzaiMessage : list) {
                messageService.save(shouzaiMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long haoshi = (endTime - startTime) / 1000;
        logger.info("耗时" + haoshi + "秒。");
        return RData.ok("操作成功,耗时" + haoshi + "秒。");
    }

    @PostMapping("/alarmFileUpload")
    public RData alarmFileUpload(@RequestParam("file") MultipartFile file) {
        long startTime = System.currentTimeMillis();
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        logger.info(suffix);

        try {
            List<AlarmMessageManagement> list = ExcelUtil.readExcel(file, AlarmMessageManagement.class, 1, 1);
            for (AlarmMessageManagement tietaChuzhang : list) {
                ammService.save(tietaChuzhang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long haoshi = (endTime - startTime) / 1000;
        logger.info("耗时" + haoshi + "秒。");
        return RData.ok("操作成功,耗时" + haoshi + "秒。");
    }

    @GetMapping("/selectAlarmTypeAmount")
    public RData selectAlarmTypeAmount(@RequestParam Map<String, Object> params) {
        String queryParamString = params.get("queryParam").toString();
        Map<String, Object> queryParams = JSON.parseObject(queryParamString, Map.class);
        AlarmMessageManagement queryInfo = new AlarmMessageManagement();
        queryInfo.setStationSecurityLevel(queryParams.get("level_value").toString());
        queryInfo.setBelongOperator(queryParams.get("operator").toString());
        queryInfo.setBizScene(queryParams.get("biz_scene").toString());
        List<AlarmInfoAmount> amountList = aiaService.selectAlarmTypeAmount(queryInfo);
        return RData.ok().put("amountList", amountList);
    }
}
