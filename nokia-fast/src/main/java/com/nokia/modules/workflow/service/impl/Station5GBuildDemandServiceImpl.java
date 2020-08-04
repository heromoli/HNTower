package com.nokia.modules.workflow.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.workflow.dao.Station5GBuildDemandDao;
import com.nokia.modules.workflow.entity.Station5GBuildDemand;
import com.nokia.modules.workflow.service.Station5GBuildDemandService;
import com.nokia.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("Station5GBuildDemandService")
public class Station5GBuildDemandServiceImpl extends ServiceImpl<Station5GBuildDemandDao, Station5GBuildDemand> implements Station5GBuildDemandService {

    @Override
    public PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams) {
        return null;
    }
}
