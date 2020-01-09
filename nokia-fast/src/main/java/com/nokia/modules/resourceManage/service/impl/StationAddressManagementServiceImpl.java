package com.nokia.modules.resourceManage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.resourceManage.dao.StationAddressManagementDao;
import com.nokia.modules.resourceManage.entity.StationAddressManagement;
import com.nokia.modules.resourceManage.service.StationAddressManagementService;
import com.nokia.utils.PageUtils;
import com.nokia.utils.Query;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.nokia.utils.LatLonUtils.GetAround;

@Service("StationAddressManagementService")
public class StationAddressManagementServiceImpl extends ServiceImpl<StationAddressManagementDao, StationAddressManagement> implements StationAddressManagementService {
    private static final Logger logger = LoggerFactory.getLogger(StationAddressManagementServiceImpl.class);

    @Override
    public PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams) {
        QueryWrapper queryWrapper = new QueryWrapper<StationAddressManagement>();
        queryWrapper.ne("longitude", 0);
        queryWrapper.ne("latitude", 0);

        String county = queryParams.get("county").toString().equals("") ? "" : queryParams.get("county").toString();
        String bizScene = queryParams.get("biz_scene").toString().equals("") ? "" : queryParams.get("biz_scene").toString();
        String address = queryParams.get("address").toString().equals("") ? "" : queryParams.get("address").toString();
        String station_name = queryParams.get("station_name").toString().equals("") ? "" : queryParams.get("station_name").toString();
        double longitude = Double.valueOf(queryParams.get("longitude").toString().equals("") ? "0" : queryParams.get("longitude").toString());
        double latitude = Double.valueOf(queryParams.get("latitude").toString().equals("") ? "0" : queryParams.get("latitude").toString());
        int rangeValue = queryParams.get("rangeValue").toString().equals("") ? 300 : Integer.valueOf(queryParams.get("rangeValue").toString()) ;
        double[] resultAround;

        if (!county.equals("")) {
            queryWrapper.eq("county", county);
        }

        if (!bizScene.equals("")) {
            queryWrapper.eq("biz_scene", bizScene);
        }

        if (!address.equals("")) {
            queryWrapper.like("address", address);
        }

        if (!station_name.equals("")) {
            queryWrapper.like("station_name", station_name);
        }

        if (longitude > 0 && latitude > 0) {
            resultAround = GetAround(longitude, latitude, rangeValue);
            queryWrapper.le("longitude", resultAround[0]);
            queryWrapper.ge("longitude", resultAround[1]);
            queryWrapper.le("latitude", resultAround[2]);
            queryWrapper.ge("latitude", resultAround[3]);
        }

        IPage<StationAddressManagement> page = this.page(new Query<StationAddressManagement>().getPage(pageParams), queryWrapper);
        return new PageUtils(page);
    }

    @Override
    public List<String> getStationCounty() {
        return baseMapper.selectCounty();
    }

    @Override
    public List<String> getBizScene() {
        return baseMapper.selectBizScene();
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        IPage<StationAddressManagement> page = this.page(
                new Query<StationAddressManagement>().getPage(params),
                new QueryWrapper<StationAddressManagement>().like(StringUtils.isNotBlank(key), "station_name", key)
        );

        return new PageUtils(page);
    }

}
