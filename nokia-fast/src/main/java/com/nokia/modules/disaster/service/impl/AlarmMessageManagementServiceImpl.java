package com.nokia.modules.disaster.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.disaster.dao.AlarmMessageManagementDao;
import com.nokia.modules.disaster.entity.AlarmMessageManagement;
import com.nokia.modules.disaster.service.AlarmMessageManagementService;
import com.nokia.utils.PageUtils;
import com.nokia.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.nokia.utils.DateUtils.DATE_PATTERN;
import static com.nokia.utils.DateUtils.stringToDate;

@Service("alarmMessageManagementService")
public class AlarmMessageManagementServiceImpl extends ServiceImpl<AlarmMessageManagementDao, AlarmMessageManagement> implements AlarmMessageManagementService {

    @Override
    public List<AlarmMessageManagement> selectDataByParam(Map<String, Object> queryParams) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        String level_value = queryParams.get("level_value").toString().equals("") ? "" : queryParams.get("level_value").toString();
        String operator = queryParams.get("operator").toString().equals("") ? "" : queryParams.get("operator").toString();
        String bizScene = queryParams.get("biz_scene").toString().equals("") ? "" : queryParams.get("biz_scene").toString();

        if (!level_value.equals("")) {
            queryWrapper.like("STATION_SECURITY_LEVEL", level_value);
        }
        if (!operator.equals("")) {
            queryWrapper.like("BELONG_OPERATOR", operator);
        }
        if (!bizScene.equals("")) {
            queryWrapper.eq("BIZ_SCENE", bizScene);
        }
        queryWrapper.in("alarm_name","温度过高","交流输入停电告警","烟雾告警","水浸告警","FSU离线","一级低压脱离告警");

        return this.list(queryWrapper);

    }

    @Override
    public PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        String level_value = queryParams.get("level_value").toString().equals("") ? "" : queryParams.get("level_value").toString();
        String operator = queryParams.get("operator").toString().equals("") ? "" : queryParams.get("operator").toString();
        String bizScene = queryParams.get("biz_scene").toString().equals("") ? "" : queryParams.get("biz_scene").toString();

//        if (!level_value.equals("")) {
//            queryWrapper.like("STATION_SECURITY_LEVEL", level_value);
//        }
//        if (!operator.equals("")) {
//            queryWrapper.like("BELONG_OPERATOR", operator);
//        }
//        if (!bizScene.equals("")) {
//            queryWrapper.eq("BIZ_SCENE", bizScene);
//        }
        queryWrapper.in("alarm_name","温度过高","交流输入停电告警","烟雾告警","水浸告警","FSU离线","一级低压脱离告警");


        queryWrapper.orderByDesc("alarm_start_time");
        IPage<AlarmMessageManagement> page = this.page(new Query<AlarmMessageManagement>().getPage(pageParams), queryWrapper);
        return new PageUtils(page);
    }
}
