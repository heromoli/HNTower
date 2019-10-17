package com.nokia.modules.workflow.service;

import com.nokia.utils.RData;

/**
 * Created by wow on 2019/6/26.
 */
public interface SimpleRepositoryService {
    public RData repositoryProcesses(String name, String bpmnPath, String pngPath );
}
