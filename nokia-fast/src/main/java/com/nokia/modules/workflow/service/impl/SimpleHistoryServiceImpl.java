package com.nokia.modules.workflow.service.impl;

import com.nokia.modules.sys.entity.SysUserEntity;
import com.nokia.modules.sys.service.SysUserService;
import com.nokia.modules.workflow.entity.HistoryTask;
import com.nokia.modules.workflow.service.SimpleHistoryService;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.*;
import org.activiti.engine.identity.User;
import org.activiti.engine.task.Comment;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wow on 2019/6/26.
 */
@Service("simpleHistoryService")
public class SimpleHistoryServiceImpl implements SimpleHistoryService {

    private static final Logger logger = LoggerFactory.getLogger(SimpleHistoryServiceImpl.class);

    @Autowired
    private org.activiti.engine.HistoryService historyService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private SpringProcessEngineConfiguration springProcessEngineConfiguration;

    @Override
    public List queryHistoryTaskByStartUserId(String startUserId) {
        List<HistoricProcessInstance> historicProcessInstanceList
                = historyService.createHistoricProcessInstanceQuery().startedBy(startUserId).list();
        List<HistoryTask> historyTaskList = new ArrayList<>();
        for (HistoricProcessInstance historicProcessInstance : historicProcessInstanceList) {
            List<HistoricTaskInstance> historicTaskInstance
                    = historyService.createHistoricTaskInstanceQuery().processInstanceId(historicProcessInstance.getId()).list();
            List<HistoryTask> temp = this.generateHistoryTask(historicTaskInstance);
            historyTaskList.addAll(temp);
        }
        return historyTaskList;
    }

    @Override
    public List queryHistoryTaskByActProcInstId(String actProcInstId) {
        List<HistoricTaskInstance> historicTaskInstance = historyService.createHistoricTaskInstanceQuery().processInstanceId(actProcInstId).list();
        List<HistoryTask> historyTaskList = this.generateHistoryTask(historicTaskInstance);
        historyTaskList.add(getStartHistoryTask(actProcInstId));
        return historyTaskList;
    }

    @Override
    public List<String> queryHistoryFinishedProcByStartUserId(String startUserId) {
        HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery();
        List<HistoricProcessInstance> historicProcessInstanceList = historicProcessInstanceQuery.startedBy(startUserId).finished().list();
        List<String> historyInsIdList = new ArrayList<>();
        for (HistoricProcessInstance historicProcessInstance : historicProcessInstanceList) {
//            logger.info("已完工的工程ProcessInstanceID："+historicProcessInstance.getId());
            historyInsIdList.add(historicProcessInstance.getId());
        }
        return historyInsIdList;
    }

    private List<HistoryTask> generateHistoryTask(List<HistoricTaskInstance> historicTaskInstance) {
        List<HistoryTask> historyTaskList = new ArrayList<>();
        for (HistoricTaskInstance taskInstance : historicTaskInstance) {
            if (taskInstance.getEndTime() == null) {
                continue;
            }
            HistoryTask historyTask = new HistoryTask();
            historyTask.setId(taskInstance.getId());
            historyTask.setName(taskInstance.getName());
            historyTask.setActProcInstId(taskInstance.getProcessInstanceId());
            historyTask.setActReProcdefId(taskInstance.getProcessDefinitionId());
            historyTask.setStartTime(taskInstance.getStartTime());
            historyTask.setEndTime(taskInstance.getEndTime());
            historyTask.setDeleteReason(taskInstance.getDeleteReason());
            //处理者
            String user_id = taskInstance.getAssignee();
            if (StringUtils.isNotEmpty(user_id)) {
                SysUserEntity sysUserEntity = sysUserService.getById(Long.valueOf(user_id));
                if (sysUserEntity != null)
                    historyTask.setAssignee(sysUserEntity.getUsername());
            }

            if (StringUtils.isNotEmpty(taskInstance.getOwner())) {
                User user = identityService.createUserQuery().userId(taskInstance.getOwner()).singleResult();
                historyTask.setOwner(user.getFirstName());
            }
            List<Comment> comment = taskService.getTaskComments(taskInstance.getId());
            if (!CollectionUtils.isEmpty(comment)) {
                historyTask.setComment(comment.get(0).getFullMessage());
            }

            historyTaskList.add(historyTask);
        }

        return historyTaskList;
    }

    private HistoryTask getStartHistoryTask(String actProcInstId) {
        HistoricProcessInstance historicActivityInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(actProcInstId).singleResult();
        HistoryTask historyTask = new HistoryTask();
        if (historicActivityInstance != null) {
            historyTask.setId(historicActivityInstance.getId());
            historyTask.setName(historicActivityInstance.getName());
            historyTask.setActProcInstId(actProcInstId);
            historyTask.setActReProcdefId(historicActivityInstance.getProcessDefinitionId());
            historyTask.setStartTime(historicActivityInstance.getStartTime());
            historyTask.setEndTime(historicActivityInstance.getEndTime());
            historyTask.setDeleteReason(historicActivityInstance.getDeleteReason());
            String user_id = historicActivityInstance.getStartUserId();
            if (StringUtils.isNotEmpty(user_id)) {
                SysUserEntity sysUserEntity = sysUserService.getById(Long.valueOf(user_id));
                if (sysUserEntity != null)
                    historyTask.setAssignee(sysUserEntity.getUsername());
            }
            historyTask.setComment("发起流程");
        }
        return historyTask;
    }

    public InputStream getFlowImgByInstanceId(String processInstanceId) {
        if (StringUtils.isEmpty(processInstanceId)) {
            logger.error("processInstanceId is null");
            return null;
        }
        // 获取历史流程实例
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        // 获取流程中已经执行的节点，按照执行先后顺序排序
        List<HistoricActivityInstance> historicActivityInstances = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId)
                .orderByHistoricActivityInstanceId().asc().list();

        List<String> highLightedActivitiIds = new ArrayList<>();
        for (HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
            highLightedActivitiIds.add(historicActivityInstance.getActivityId());
        }

        List<HistoricProcessInstance> historicFinishedProcessInstances = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).finished()
                .list();
        ProcessDiagramGenerator processDiagramGenerator = null;
        // 如果还没完成，流程图高亮颜色为绿色，如果已经完成为红色
        if (!CollectionUtils.isEmpty(historicFinishedProcessInstances)) {
            // 如果不为空，说明已经完成
            processDiagramGenerator = springProcessEngineConfiguration.getProcessDiagramGenerator();
        } else {
//            processDiagramGenerator = springProcessEngineConfiguration.getProcessDiagramGenerator();
            processDiagramGenerator = new CustomProcessDiagramGenerator();
        }
//        CustomProcessDiagramGenerator processDiagramGenerator = new CustomProcessDiagramGenerator();

        BpmnModel bpmnModel = repositoryService.getBpmnModel(historicProcessInstance.getProcessDefinitionId());
        // 高亮流程已发生流转的线id集合
        List<String> highLightedFlowIds = getHighLightedFlows(bpmnModel, historicActivityInstances);

        // 使用默认配置获得流程图表生成器，并生成追踪图片字符流
        InputStream imageStream = processDiagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivitiIds, highLightedFlowIds, "黑体", "黑体", "微软雅黑", null, 2.0);

        return imageStream;
    }

    // 高亮已经执行流程节点ID集合
    private static List<String> getHighLightedFlows(BpmnModel bpmnModel, List<HistoricActivityInstance> historicActivityInstances) {
        // 高亮流程已发生流转的线id集合
        List<String> highLightedFlowIds = new ArrayList<>();
        // 全部活动节点
        List<FlowNode> historicActivityNodes = new ArrayList<>();
        // 已完成的历史活动节点
        List<HistoricActivityInstance> finishedActivityInstances = new ArrayList<>();

        for (HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
            FlowNode flowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(historicActivityInstance.getActivityId(), true);
            historicActivityNodes.add(flowNode);
            if (historicActivityInstance.getEndTime() != null) {
                finishedActivityInstances.add(historicActivityInstance);
            }
        }

        FlowNode currentFlowNode = null;
        FlowNode targetFlowNode = null;
        // 遍历已完成的活动实例，从每个实例的outgoingFlows中找到已执行的
        for (HistoricActivityInstance currentActivityInstance : finishedActivityInstances) {
            // 获得当前活动对应的节点信息及outgoingFlows信息
            currentFlowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(currentActivityInstance.getActivityId(), true);
            List<SequenceFlow> sequenceFlows = currentFlowNode.getOutgoingFlows();

            /**
             * 遍历outgoingFlows并找到已已流转的 满足如下条件认为已已流转： 1.当前节点是并行网关或兼容网关，则通过outgoingFlows能够在历史活动中找到的全部节点均为已流转 2.当前节点是以上两种类型之外的，通过outgoingFlows查找到的时间最早的流转节点视为有效流转
             */
            if ("parallelGateway".equals(currentActivityInstance.getActivityType()) || "inclusiveGateway".equals(currentActivityInstance.getActivityType())) {
                // 遍历历史活动节点，找到匹配流程目标节点的
                for (SequenceFlow sequenceFlow : sequenceFlows) {
                    targetFlowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(sequenceFlow.getTargetRef(), true);
                    if (historicActivityNodes.contains(targetFlowNode)) {
                        highLightedFlowIds.add(targetFlowNode.getId());
                    }
                }
            } else {
                List<Map<String, Object>> tempMapList = new ArrayList<>();
                for (SequenceFlow sequenceFlow : sequenceFlows) {
                    for (HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
                        if (historicActivityInstance.getActivityId().equals(sequenceFlow.getTargetRef())) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("highLightedFlowId", sequenceFlow.getId());
                            map.put("highLightedFlowStartTime", historicActivityInstance.getStartTime().getTime());
                            tempMapList.add(map);
                        }
                    }
                }

                if (!CollectionUtils.isEmpty(tempMapList)) {
                    // 遍历匹配的集合，取得开始时间最早的一个
                    long earliestStamp = 0L;
                    String highLightedFlowId = null;
                    for (Map<String, Object> map : tempMapList) {
                        long highLightedFlowStartTime = Long.valueOf(map.get("highLightedFlowStartTime").toString());
                        if (earliestStamp == 0 || earliestStamp >= highLightedFlowStartTime) {
                            highLightedFlowId = map.get("highLightedFlowId").toString();
                            earliestStamp = highLightedFlowStartTime;
                        }
                    }

                    highLightedFlowIds.add(highLightedFlowId);
                }

            }

        }
        return highLightedFlowIds;
    }
}
