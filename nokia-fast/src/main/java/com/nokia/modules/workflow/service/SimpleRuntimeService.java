package com.nokia.modules.workflow.service;

import com.nokia.utils.RData;

import java.util.List;
import java.util.Map;

/**
 * Created by wow on 2019/6/26.
 */
public interface SimpleRuntimeService {

    public RData startProcess(String processDefinitionId, Map<String, Object> variables);

    public RData findProcessByUserOrGroup(String userId, List<String> groupId);

    public RData findProcessByUserOrGroupAndKey(String userId, List<String> groupId, String key);

    public RData findProcessByUserOrGroup(String userId, String groupId);

    public RData findProcessByUserOrGroupAndKey(String userId, String groupId, String key);

    public int countTaskByDefKey(String key);

    public RData cancel(String processInstanceId, String reason);
}
