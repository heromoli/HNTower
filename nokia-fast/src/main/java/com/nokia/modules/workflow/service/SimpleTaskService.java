package com.nokia.modules.workflow.service;

import com.nokia.utils.RData;

import java.util.Map;

/**
 * Created by wow on 2019/6/26.
 */
public interface SimpleTaskService {
    public RData complete(String processInstanceId, String comment, Map<String, Object> var);
}
