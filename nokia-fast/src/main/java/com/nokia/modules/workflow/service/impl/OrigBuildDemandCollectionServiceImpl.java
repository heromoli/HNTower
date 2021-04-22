package com.nokia.modules.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.workflow.dao.OrigBuildDemandCollectionDao;
import com.nokia.modules.workflow.entity.OrigBuildDemandCollection;
import com.nokia.modules.workflow.service.OrigBuildDemandCollectionService;
import com.nokia.utils.PageUtils;
import com.nokia.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("OrigBuildDemandCollectionService")
public class OrigBuildDemandCollectionServiceImpl extends ServiceImpl<OrigBuildDemandCollectionDao, OrigBuildDemandCollection> implements OrigBuildDemandCollectionService {

    @Override
    public PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams) {
        QueryWrapper queryWrapper = new QueryWrapper<OrigBuildDemandCollection>();
        for (String key : queryParams.keySet()) {
            queryWrapper.like(key, queryParams.get(key));
        }
        IPage<OrigBuildDemandCollection> page = this.page(new Query<OrigBuildDemandCollection>().getPage(pageParams), queryWrapper);
        return new PageUtils(page);
    }
}
