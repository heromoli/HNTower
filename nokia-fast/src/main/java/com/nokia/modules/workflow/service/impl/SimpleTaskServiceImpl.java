package com.nokia.modules.workflow.service.impl;

import com.nokia.modules.sys.entity.SysUserEntity;
import com.nokia.modules.workflow.service.SimpleTaskService;
import com.nokia.utils.RData;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("simpleTaskService")
public class SimpleTaskServiceImpl implements SimpleTaskService {

    private static final Logger logger = Logger.getLogger(SimpleTaskServiceImpl.class);

    @Autowired
    private TaskService taskService;

    public RData complete(String processInstanceId, String comment, Map<String, Object> var) {

        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        if (task == null) {
            logger.error("process {" + processInstanceId + "} task is not exists");
            return RData.error("流程任务不存在");
        }
        if (StringUtils.isEmpty(comment)) {
            comment = "同意";
        }
        //添加审批者
        SysUserEntity sysUserEntity = (SysUserEntity) var.get("user");

        Authentication.setAuthenticatedUserId(String.valueOf(sysUserEntity.getUserId()));
        taskService.addComment(task.getId(), processInstanceId, comment);

        taskService.setAssignee(task.getId(), String.valueOf(sysUserEntity.getUserId()));
        if (var == null) {
            taskService.complete(task.getId());
        } else {
            taskService.complete(task.getId(), var);
        }

        task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();

        RData result = RData.ok();
        result.put("isFinished", task == null);
        return result;
    }
}
