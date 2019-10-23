package com.nokia.modules.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.workflow.dao.StationAddressManagementDao;
import com.nokia.modules.workflow.entity.PMS;
import com.nokia.modules.workflow.entity.StationAddressManagement;
import com.nokia.modules.workflow.service.StationAddressManagementService;
import com.nokia.utils.PageUtils;
import com.nokia.utils.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("StationAddressManagementService")
public class StationAddressManagementServiceImpl extends ServiceImpl<StationAddressManagementDao, StationAddressManagement> implements StationAddressManagementService {


    @Override
    public PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams) {
        QueryWrapper queryWrapper = new QueryWrapper<StationAddressManagement>();
        queryWrapper.ne("longitude",0);
        queryWrapper.ne("latitude",0);
        for (String key : queryParams.keySet()) {
            if (key.equals("county")) {
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
        IPage<StationAddressManagement> page = this.page(new Query<StationAddressManagement>().getPage(pageParams), queryWrapper);
        return new PageUtils(page);
    }

    @Override
    public List<String> getStationCounty() {
        return baseMapper.selectCounty();
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        IPage<StationAddressManagement> page = this.page(
                new Query<StationAddressManagement>().getPage(params),
                new QueryWrapper<StationAddressManagement>().like(StringUtils.isNotBlank(key),"station_name", key)
        );

        return new PageUtils(page);
    }

}
