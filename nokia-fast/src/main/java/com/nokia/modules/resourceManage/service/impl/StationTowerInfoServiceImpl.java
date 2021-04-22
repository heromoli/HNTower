package com.nokia.modules.resourceManage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.resourceManage.dao.StationTowerInfoDao;
import com.nokia.modules.resourceManage.entity.StationTowerInfo;
import com.nokia.modules.resourceManage.service.StationTowerInfoService;
import com.nokia.utils.PageUtils;
import com.nokia.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("StationTowerInfoService")
public class StationTowerInfoServiceImpl extends ServiceImpl<StationTowerInfoDao, StationTowerInfo> implements StationTowerInfoService {

    @Override
    public PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams) {
        QueryWrapper queryWrapper = new QueryWrapper<StationTowerInfo>();
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
        IPage<StationTowerInfo> page = this.page(new Query<StationTowerInfo>().getPage(pageParams), queryWrapper);
        return new PageUtils(page);
    }

}
