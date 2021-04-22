package com.nokia.modules.workflow.service.impl;

import com.nokia.modules.sys.entity.SysUserEntity;
import com.nokia.modules.workflow.service.SimpleRuntimeService;
import com.nokia.utils.RData;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service("simpleRuntimeService")
public class SimpleRuntimeServiceImpl implements SimpleRuntimeService {

    private static final Logger logger = Logger.getLogger(SimpleRuntimeServiceImpl.class);

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private TaskService taskService;

    @Override
    public RData startProcess(String processDefinitionId, Map<String, Object> variables) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        if (processDefinition == null) {
            List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionId).list();
            if (list != null && list.size() > 0) {
                processDefinition = list.get(list.size() - 1);
            }
        }
        if (processDefinition == null) {
            logger.error("process definition {" + processDefinitionId + "} is not exists");
            return RData.error("流程定义" + processDefinitionId + "不存在");
        }
        SysUserEntity sysUserEntity = (SysUserEntity) variables.get("user");
//        identityService.setAuthenticatedUserId(String.valueOf(sysUserEntity.getUserId()));
        Authentication.setAuthenticatedUserId(String.valueOf(sysUserEntity.getUserId()));
        ProcessInstance processInstance;
        if (!CollectionUtils.isEmpty(variables)) {
//            processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(), variables);
            processInstance = runtimeService.startProcessInstanceByKey(processDefinition.getKey(), variables);
        } else {
            processInstance = runtimeService.startProcessInstanceByKey(processDefinition.getKey());
        }

        RData result = RData.ok();
        result.put("processInstanceId", processInstance.getId());
        return result;
    }

    @Override
    public RData findProcessByUserOrGroup(String userId, List<String> groupId) {
        if (StringUtils.isEmpty(userId) && (groupId == null || groupId.size() == 0)) {
            return RData.error("查询参数错误");
        }

        Set<String> processInstanceIdSet = new HashSet<>();

        if (StringUtils.isNotEmpty(userId)) {
            //TODO 查询待优化  taskService.createTaskQuery().taskDefinitionKey(String key)
            List<Task> taskList1 = taskService.createTaskQuery().taskOwner(userId).list();    //userID创建的工单
            List<Task> taskList2 = taskService.createTaskQuery().taskAssignee(userId).list();  //userID被分配到工单
            List<Task> taskList3 = taskService.createTaskQuery().taskCandidateUser(userId).list();  //userID被分配到，作为候补执行人的工单
            for (Task task : taskList1) {
                processInstanceIdSet.add(task.getProcessInstanceId());
            }
            for (Task task : taskList2) {
                processInstanceIdSet.add(task.getProcessInstanceId());
            }
            for (Task task : taskList3) {
                processInstanceIdSet.add(task.getProcessInstanceId());
            }

        }
        if (groupId != null && groupId.size() > 0) {
            List<Task> taskList4 = taskService.createTaskQuery().taskCandidateGroupIn(groupId).list();    //userID所处的工作组，被分配到的工单
            for (Task task : taskList4) {
                processInstanceIdSet.add(task.getProcessInstanceId());
            }
        }

        RData result = RData.ok();
        result.put("processInstanceId", processInstanceIdSet);

        return result;
    }

    @Override
    public RData findProcessByUserOrGroupAndKey(String userId, List<String> groupId, String key) {
//        logger.debug("--------key:" + key);
        if (StringUtils.isEmpty(userId) && (groupId == null || groupId.size() == 0)) {
            return RData.error("查询参数错误");
        }
        Set<String> processInstanceIdSet = new HashSet<>();
        if (StringUtils.isNotEmpty(userId)) {

            List<Task> taskList1 = taskService.createTaskQuery().taskOwner(userId).taskDefinitionKey(key).list();    //userID创建的工单
            List<Task> taskList2 = taskService.createTaskQuery().taskAssignee(userId).taskDefinitionKey(key).list();  //userID被分配到工单
            List<Task> taskList3 = taskService.createTaskQuery().taskCandidateUser(userId).taskDefinitionKey(key).list();  //userID被分配到，作为候补执行人的工单
            for (Task task : taskList1) {
                processInstanceIdSet.add(task.getProcessInstanceId());
            }
            for (Task task : taskList2) {
                processInstanceIdSet.add(task.getProcessInstanceId());
            }
            for (Task task : taskList3) {
                processInstanceIdSet.add(task.getProcessInstanceId());
            }

        }
        if (groupId != null && groupId.size() > 0) {
            List<Task> taskList4 = taskService.createTaskQuery().taskCandidateGroupIn(groupId).taskDefinitionKey(key).list();    //userID所处的工作组，被分配到的工单
            for (Task task : taskList4) {
                processInstanceIdSet.add(task.getProcessInstanceId());
            }
        }
//        logger.debug("--------processInstanceIdSet:" + processInstanceIdSet);
        RData result = RData.ok();
        result.put("processInstanceId", processInstanceIdSet);

        return result;
    }

    @Override
    public RData findProcessByUserOrGroup(String userId, String groupId) {
        List<String> list = null;
        if (StringUtils.isNotEmpty(groupId)) {
            list = new ArrayList<>();
            list.add(groupId);
        }
        return findProcessByUserOrGroup(userId, list);
    }

    @Override
    public RData findProcessByUserOrGroupAndKey(String userId, String groupId, String key) {
        List<String> list = null;
        if (StringUtils.isNotEmpty(groupId)) {
            list = new ArrayList<>();
            list.add(groupId);
        }
        return findProcessByUserOrGroupAndKey(userId, list, key);
    }

    @Override
    public int countTaskByDefKey(String key) {
        List<Task> taskList = taskService.createTaskQuery().taskDefinitionKey(key).list();
        return taskList.size();
    }

    public RData cancel(String processInstanceId, String reason) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (processInstance == null) {
            logger.error("process instance {" + processInstanceId + "} is not exists");
            return RData.error("流程实例不存在");
        }

        runtimeService.deleteProcessInstance(processInstanceId, reason);
        return RData.ok();
    }
}
