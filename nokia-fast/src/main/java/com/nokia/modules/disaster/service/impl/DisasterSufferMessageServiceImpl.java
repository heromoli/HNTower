package com.nokia.modules.disaster.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.disaster.dao.DisasterSufferMessageDao;
import com.nokia.modules.disaster.entity.DisasterSufferMessage;
import com.nokia.modules.disaster.service.DisasterSufferMessageService;
import com.nokia.utils.PageUtils;
import com.nokia.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

import static com.nokia.utils.DateUtils.DATE_PATTERN;
import static com.nokia.utils.DateUtils.stringToDate;

@Service("disasterSufferMessageService")
public class DisasterSufferMessageServiceImpl extends ServiceImpl<DisasterSufferMessageDao, DisasterSufferMessage> implements DisasterSufferMessageService {


    @Override
    public PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        String msgId = queryParams.get("msg_id").toString().equals("") ? "" : queryParams.get("msg_id").toString();
        String msg = queryParams.get("msg").toString().equals("") ? "" : queryParams.get("msg").toString();

        if (!msgId.equals("")) {
            queryWrapper.eq("msg_id", msgId);
        }

        if (!msg.equals("")) {
            queryWrapper.like("msg", msg);
        }

        if (queryParams.get("sdate") != null && !queryParams.get("sdate").equals("") && !queryParams.get("sdate").equals("null")) {
            Date sdate = stringToDate(queryParams.get("sdate").toString().substring(0, 10), DATE_PATTERN);
            queryWrapper.eq("sdate", sdate);
        }

        queryWrapper.orderByAsc( "sdate");
        IPage<DisasterSufferMessage> page = this.page(new Query<DisasterSufferMessage>().getPage(pageParams), queryWrapper);
        return new PageUtils(page);
    }
}
