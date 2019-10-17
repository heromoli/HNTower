package com.nokia.modules.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.workflow.entity.CRM;
import com.nokia.utils.PageUtils;

import java.util.Map;

public interface CRMService extends IService<CRM> {

    public PageUtils selectDataByParam(Map<String, Object> pageParams, Map<String, Object> queryParams);

}
