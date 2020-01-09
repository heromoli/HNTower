package com.nokia.modules.resourceManage.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.resourceManage.dao.TowerSolutionDetailDao;
import com.nokia.modules.resourceManage.entity.TowerSolutionDetail;
import com.nokia.modules.resourceManage.service.TowerSolutionDetailService;
import com.nokia.utils.PageUtils;
import com.nokia.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("TowerSolutionDetailService")
public class TowerSolutionDetailServiceImpl extends ServiceImpl<TowerSolutionDetailDao, TowerSolutionDetail> implements TowerSolutionDetailService {

    @Override
    public PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams) {

        QueryWrapper queryWrapper = new QueryWrapper<TowerSolutionDetail>();

        for (String key : queryParams.keySet()) {
            queryWrapper.like(key, queryParams.get(key));
        }
//        String projectName = queryParams.get("project_name").toString();
//        queryWrapper.eq("project_Name",projectName);
        IPage<TowerSolutionDetail> page = this.page(new Query<TowerSolutionDetail>().getPage(pageParams), queryWrapper);
        return new PageUtils(page);
    }
}
