package com.nokia.modules.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.workflow.entity.OrigBuildDemandCollection;
import com.nokia.utils.PageUtils;

import java.util.Map;

public interface OrigBuildDemandCollectionService extends IService<OrigBuildDemandCollection> {

    PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams);
}
