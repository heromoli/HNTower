package com.nokia.modules.workflow.service;

import com.nokia.modules.workflow.entity.HistoryTask;
import org.activiti.engine.history.HistoricTaskInstance;

import java.io.InputStream;
import java.util.List;

/**
 * Created by wow on 2019/6/26.
 */
public interface SimpleHistoryService {
    public List queryHistoryTaskByStartUserId(String startUserId);
    public List queryHistoryTaskByActProcInstId(String actProcInstId);
    public List queryHistoryFinishedProcByStartUserId(String actProcInstId);
    public InputStream getFlowImgByInstanceId(String processInstanceId);
}
