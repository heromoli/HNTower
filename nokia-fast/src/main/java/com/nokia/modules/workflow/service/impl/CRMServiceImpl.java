package com.nokia.modules.workflow.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.workflow.dao.CRMDao;

import com.nokia.modules.workflow.entity.CRM;
import com.nokia.modules.workflow.service.CRMService;
import com.nokia.utils.PageUtils;
import com.nokia.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("CRMService")
public class CRMServiceImpl extends ServiceImpl<CRMDao, CRM> implements CRMService {

    @Override
    public PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams) {
        QueryWrapper queryWrapper = new QueryWrapper<CRM>();
        for (String key : queryParams.keySet()) {
            queryWrapper.like(key, queryParams.get(key));
        }
        IPage<CRM> page = this.page(new Query<CRM>().getPage(pageParams), queryWrapper);
        return new PageUtils(page);
    }
}
