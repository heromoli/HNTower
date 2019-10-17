package com.nokia.processes;

import com.nokia.NokiaFastApplication;
import com.nokia.modules.sys.entity.SysRoleEntity;
import com.nokia.modules.sys.entity.SysUserEntity;
import com.nokia.modules.sys.service.SysRoleService;
import com.nokia.modules.sys.service.SysUserService;
import com.nokia.modules.workflow.service.SimpleHistoryService;
import com.nokia.modules.workflow.service.SimpleRuntimeService;
import com.nokia.modules.workflow.service.SimpleTaskService;
import com.nokia.utils.RData;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wow on 2019/6/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NokiaFastApplication.class)
public class ProcessesTest {
    private static final Logger logger = LoggerFactory.getLogger(ProcessesTest.class);

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private SimpleRuntimeService simplerRuntimeService ;

    @Autowired
    private SimpleTaskService simpleTaskService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SimpleHistoryService simpleHistoryService;



    //注册流程
    @Test
    public void repositoryProcesses(){
        Deployment deployment = repositoryService.createDeployment().name("工程管理流程")
                .addClasspathResource("processes/tt_gcb_xxspV2.bpmn")
                .deploy();
        logger.info("deployment:{}",deployment.getName());
    }

    //发起流程
    @Test
    public void startProcesses(){
        Map map = new HashMap<>();
        SysUserEntity sysUserEntity = sysUserService.getById("2");
        map.put("user",sysUserEntity);
        RData pi = simplerRuntimeService.startProcess("tt_gcb_xxsp",map);
        logger.info("RData",pi);
    }

    @Test
    public void queryProcesses(){
        RData rData = simplerRuntimeService.findProcessByUserOrGroup("","3");
        logger.info("rData:{}",rData.get("processInstanceId"));
        String processInstanceId = String.valueOf(rData.get("processInstanceId"));
        String[] processInstanceIds = processInstanceId.split(",");
        for(String id:processInstanceIds){
            if(StringUtils.isNotEmpty(id)){
                Map map = new HashMap();
                Map m = new HashMap();
                m.put("roleId","1");
//                SysRoleEntity sysRoleEntity = new SysRoleEntity();
//                sysRoleEntity.setRoleId(1L);
                map.put("role",m);
                completeProcesses(id,"申请1",map);
            }
        }
    }
    //提交流程
    public void completeProcesses(String id,String comment,Map<String, Object> var){
        simpleTaskService.complete(id,comment,var);
    }

    //查看流程

    //
    @Test
    public void deleteProcess(){

        String deploymentId = "25001";
        /**不带级联的删除：只能删除没有启动的流程，如果流程启动，就会抛出异常*/
//     repositoryService.deleteDeployment(deploymentId);

        /**级联删除：不管流程是否启动，都能可以删除（emmm大概是一锅端）*/
        repositoryService.deleteDeployment(deploymentId, true);
        System.out.println("删除成功！");
    }

    @Test
    public void history(){
        String proinstid = "127501";
        List a = simpleHistoryService.queryHistoryTaskByActProcInstId(proinstid);
        System.out.println(a);
    }
}
