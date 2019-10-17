package com.nokia.modules.workflow.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.workflow.dao.StationAddressInfoDao;
import com.nokia.modules.workflow.entity.PMS;
import com.nokia.modules.workflow.entity.StationAddressInfo;
import com.nokia.modules.workflow.service.StationAddressInfoService;
import com.nokia.utils.PageUtils;
import com.nokia.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("StationAddressInfoService")
public class StationAddressInfoServiceImpl extends ServiceImpl<StationAddressInfoDao, StationAddressInfo> implements StationAddressInfoService {

    @Override
    public PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams) {
        QueryWrapper queryWrapper = new QueryWrapper<StationAddressInfo>();
        for (String key : queryParams.keySet()) {
            if (key.equals("city")) {
                queryWrapper.eq(key, queryParams.get(key));
            } else if (key.equals("address") || key.equals("station_name")) {
                queryWrapper.like(key, queryParams.get(key));
            } else if (key.equals("longitude") || key.equals("latitude")) {
                String value = (String) queryParams.get(key);
                if (value.length() > 0) {
                    value = value.substring(0, value.length() - 3);
                    queryWrapper.like(key, value);
                }
            }
        }
        IPage<PMS> page = this.page(new Query<StationAddressInfo>().getPage(pageParams), queryWrapper);
        return new PageUtils(page);
    }


}
