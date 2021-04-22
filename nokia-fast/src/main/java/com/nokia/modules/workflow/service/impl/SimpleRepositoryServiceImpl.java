package com.nokia.modules.workflow.service.impl;

import com.nokia.modules.workflow.service.SimpleRepositoryService;
import com.nokia.utils.RData;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("simpleRepositoryService")
public class SimpleRepositoryServiceImpl implements SimpleRepositoryService {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(SimpleRepositoryServiceImpl.class);

    @Autowired
    private RepositoryService repositoryService;

    public RData repositoryProcesses(String name, String bpmnPath, String pngPath) {
        Deployment deployment = repositoryService.createDeployment().name("工程管理流程")
                .addClasspathResource("processes/tt_wf_changeCheck.bpmn")
                .deploy();
        logger.info("deployment name:[{" + deployment.getName() + "}],id:[{" + deployment.getId() + "}]");
        return RData.ok();
    }
}
