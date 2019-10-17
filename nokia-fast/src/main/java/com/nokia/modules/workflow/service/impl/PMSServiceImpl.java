package com.nokia.modules.workflow.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.workflow.dao.PMSDao;
import com.nokia.modules.workflow.entity.PMS;
import com.nokia.modules.workflow.service.PMSService;
import com.nokia.utils.PageUtils;
import com.nokia.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("PMSService")
public class PMSServiceImpl extends ServiceImpl<PMSDao, PMS> implements PMSService {

    @Override
    public PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams) {
        QueryWrapper queryWrapper = new QueryWrapper<PMS>();
        for (String key : queryParams.keySet()) {
            queryWrapper.like(key, queryParams.get(key));
        }
        IPage<PMS> page = this.page(new Query<PMS>().getPage(pageParams), queryWrapper);
        return new PageUtils(page);
    }
}
