package com.nokia.modules.resourceManage.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.resourceManage.dao.StationAddressInfoNewDao;
import com.nokia.modules.resourceManage.entity.StationAddressInfoNew;
import com.nokia.modules.resourceManage.service.StationAddressInfoNewService;
import com.nokia.utils.PageUtils;
import com.nokia.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("StationAddressInfoNewService")
public class StationAddressInfoNewServiceImpl extends ServiceImpl<StationAddressInfoNewDao, StationAddressInfoNew> implements StationAddressInfoNewService {

    @Override
    public PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams) {
        QueryWrapper queryWrapper = new QueryWrapper<StationAddressInfoNew>();
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
        IPage<StationAddressInfoNew> page = this.page(new Query<StationAddressInfoNew>().getPage(pageParams), queryWrapper);
        return new PageUtils(page);
    }


}
