package com.nokia.modules.resourceManage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.resourceManage.dao.QueryStationAddressDao;
import com.nokia.modules.resourceManage.entity.QueryStationAddress;
import com.nokia.modules.resourceManage.service.QueryStationAddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.nokia.utils.LatLonUtils.GetAround;

@Service("QueryStationAddressService")
public class QueryStationAddressServiceImpl extends ServiceImpl<QueryStationAddressDao, QueryStationAddress> implements QueryStationAddressService {

    @Override
    public List<QueryStationAddress> selectDataByParam(Map<String, Object> queryParams) {
        QueryWrapper queryWrapper = new QueryWrapper<QueryStationAddress>();
        queryWrapper.select("city", "county", "biz_scene", "address", "station_name", "station_num", "longitude", "latitude",
                "station_Status", "if_Operator_Share","station_Safe_Lv","remarks");
        queryWrapper.ne("longitude", 0);
        queryWrapper.ne("latitude", 0);

        String county = queryParams.get("county").toString().equals("") ? "" : queryParams.get("county").toString();
        String bizScene = queryParams.get("biz_scene").toString().equals("") ? "" : queryParams.get("biz_scene").toString();
        String address = queryParams.get("address").toString().equals("") ? "" : queryParams.get("address").toString();
        String station_name = queryParams.get("station_name").toString().equals("") ? "" : queryParams.get("station_name").toString();
        double longitude = Double.valueOf(queryParams.get("longitude").toString().equals("") ? "0" : queryParams.get("longitude").toString());
        double latitude = Double.valueOf(queryParams.get("latitude").toString().equals("") ? "0" : queryParams.get("latitude").toString());
        int rangeValue = queryParams.get("rangeValue").toString().equals("") ? 300 : Integer.valueOf(queryParams.get("rangeValue").toString());
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
        } else {
            queryWrapper.le("longitude", 111.2);
            queryWrapper.ge("longitude", 108.5);
            queryWrapper.le("latitude", 20.2);
            queryWrapper.ge("latitude", 18);
        }
        return this.list(queryWrapper);
    }

}
