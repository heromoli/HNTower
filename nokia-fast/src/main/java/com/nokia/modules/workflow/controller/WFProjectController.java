package com.nokia.modules.workflow.controller;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.nokia.common.exception.RRException;
import com.nokia.modules.sys.controller.BaseController;
import com.nokia.modules.sys.entity.ProjectRightConfigEntity;
import com.nokia.modules.sys.entity.SysUserEntity;
import com.nokia.modules.sys.service.ProjectRightConfigService;
import com.nokia.modules.sys.service.ProjectWorkflowGroupService;
import com.nokia.modules.workflow.entity.*;
import com.nokia.modules.workflow.service.*;
import com.nokia.modules.workflow.service.StationAddressManagementService;
import com.nokia.utils.PageUtils;
import com.nokia.utils.RData;
import com.nokia.utils.excel.BeanCopyUtils;
import com.nokia.utils.excel.ExcelUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.nokia.utils.DateUtils.getMonthLastDay;

/**
 * Created by wow on 2019/6/26.
 * 工程管理控制器
 */
@RestController
@RequestMapping("/api/wf")
public class WFProjectController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(WFProjectController.class);

    @Autowired
    private SimpleTaskService simpleTaskService;

    @Autowired
    private SimpleRepositoryService simpleRepositoryService;

    @Autowired
    private SimpleRuntimeService simpleRuntimeService;

    @Autowired
    private SimpleIdentityService simpleIdentityService;

    @Autowired
    private ProjectWorkflowGroupService projectWorkflowGroupService;

    @Autowired
    private SimpleHistoryService simpleHistoryService;

    @Autowired
    private ProjectRightConfigService projectRightConfigService;

    @Autowired
    private CustomerDemandCollectionService cncService;

    @Autowired
    private StationAddressCheckService sacService;

    @Autowired
    private BuildOrderConfirmService bocService;

    @Autowired
    private SupervisorService supervisorService;

    @Autowired
    private BuildDemandChangeConfirmService bnccService;

    @Autowired
    private PMSService pmsService;

    @Autowired
    private CRMService crmService;

    @Autowired
    private StationAddressInfoService stationAddressInfoService;

    @Autowired
    private StationAddressManagementService stationManageService;

    @Value("${workflow_act_process_definition_id}")
    private String actProcessDefinitionId;
    //上传文件并注册

    //发起单个需求工程流程
    @PostMapping("/startProcesses")
    public RData startProcesses(@RequestBody CustomerDemandCollection params) {
        SysUserEntity sysUserEntity = getUser();
        if (sysUserEntity == null) {
            return RData.error(500, "无法获取用户信息");
        }
        Map variables = RData.getMap("user", sysUserEntity);

        String processDefinitionId = params.getActProcessDefinitionId();

        if (StringUtils.isEmpty(processDefinitionId)) {
            processDefinitionId = actProcessDefinitionId;
        }
        RData rData = simpleRuntimeService.startProcess(processDefinitionId, variables);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String operatorName = params.getOperatorName();

        if ("移动".equals(operatorName)) {
            String seq_num = supervisorService.queryYDSequence();
            String monthLastDay = getMonthLastDay();
            params.setDemandNum("YDB" + monthLastDay + seq_num);
        } else if ("联通".equals(operatorName)) {
            String seq_num = supervisorService.queryLTSequence();
            params.setDemandNum("LTB" + dateFormat.format(new Date()) + seq_num);
        } else if ("电信".equals(operatorName)) {
            String seq_num = supervisorService.queryDXSequence();
            params.setDemandNum("DXB" + dateFormat.format(new Date()) + seq_num);
        }

        params.setDemandProposeTime(new Date());
        params.setActProcInstId((String) rData.get("processInstanceId"));
        params.setActProcStatus("1");

        cncService.save(params);
        return rData;
    }

    //发起多个流程


    //详情页面审查通过流程
    @PostMapping("/checkProcesses")
    public RData checkProcesses(@RequestBody Supervisor params) {
        SysUserEntity sysUserEntity = getUser();
        if (sysUserEntity == null) {
            return RData.error(500, "无法获取用户信息");
        }

        String processInstanceId = params.getActProcInstId();
        String comment = params.getRemarks();
        String actProcStatus = params.getActProcStatus();
        Supervisor supervisor = supervisorService.selectDataByInsId(processInstanceId);

        Map<String, Object> var = new HashMap<>();
        var.put("user", sysUserEntity);
        if ("2".equals(actProcStatus) || "4".equals(actProcStatus) || "6".equals(actProcStatus)) {
            var.put("approve", "1");
        } else if ("3".equals(actProcStatus) || "5".equals(actProcStatus) || "7".equals(actProcStatus)) {
            var.put("approve", "0");
            BuildDemandChangeConfirm bncc = convertToChangeConfirm(params);
            bnccService.saveOrUpdate(bncc);
        }

        boolean result = supervisorService.saveOrUpdate(params);
        if (!result) {
            return RData.error(500, "保存失败，请检查数据格式。");
        } else if (result && !actProcStatus.equals(supervisor.getActProcStatus())) {  //审批状态不改变，流程不往前走
            return simpleTaskService.complete(processInstanceId, comment, var);
        } else
            return RData.ok();
    }

    //列表界面提交流程
    @PostMapping("/complete")
    public RData complete(@RequestBody Supervisor params) {
        SysUserEntity sysUserEntity = getUser();
        if (sysUserEntity == null) {
            return RData.error(500, "无法获取用户信息");
        }

        String processInstanceId = params.getActProcInstId();
        String comment = params.getRemarks();
        String groupId = params.getGroupId();
        String approve = params.getApprove();
        Map<String, Object> var = new HashMap<>();
        var.put("user", sysUserEntity);
        var.put("approve", approve);

        if ("1".equals(approve)) {
            if ("3".equals(groupId)) {
                params.setActProcStatus("2");
            } else if ("4".equals(groupId)) {
                params.setActProcStatus("4");
            } else if ("5".equals(groupId)) {
                params.setActProcStatus("6");
            }
        } else if ("0".equals(approve)) {
            if ("3".equals(groupId)) {
                params.setActProcStatus("3");
            } else if ("4".equals(groupId)) {
                params.setActProcStatus("5");
            } else if ("5".equals(groupId)) {
                params.setActProcStatus("7");
            }
        }

        boolean result = supervisorService.saveOrUpdate(params);
        if (result) {
            return simpleTaskService.complete(processInstanceId, comment, var);
        } else
            return RData.error(500, "数据保存失败。");
    }


    private BuildDemandChangeConfirm convertToChangeConfirm(Supervisor params) {
        BuildDemandChangeConfirm buildDemandChangeConfirm = new BuildDemandChangeConfirm();
        buildDemandChangeConfirm.setActProcInstId(params.getActProcInstId());
        buildDemandChangeConfirm.setActProcStatus(params.getActProcStatus());
        buildDemandChangeConfirm.setDemandNum(params.getDemandNum());
        buildDemandChangeConfirm.setOperatorName(params.getOperatorName());
        buildDemandChangeConfirm.setBranchCompany(params.getBranchCompany());
        buildDemandChangeConfirm.setRegion(params.getRegion());
        buildDemandChangeConfirm.setStationName(params.getStationName());
        buildDemandChangeConfirm.setScene(params.getScene());
        buildDemandChangeConfirm.setLongitude(params.getLongitude());
        buildDemandChangeConfirm.setLatitude(params.getLatitude());
        buildDemandChangeConfirm.setAddress(params.getAddress());
        buildDemandChangeConfirm.setTableId(params.getGroupId());
        buildDemandChangeConfirm.setRemarks(params.getRemarks());

        return buildDemandChangeConfirm;
    }

    private CustomerDemandCollection convertToSupervisor(BuildDemandChangeConfirm changeConfirm) {
        CustomerDemandCollection supervisor = new CustomerDemandCollection();
        supervisor.setDemandNum(changeConfirm.getDemandNum());
        supervisor.setOperatorName(changeConfirm.getOperatorName());
        supervisor.setBranchCompany(changeConfirm.getBranchCompany());
        supervisor.setCounty(changeConfirm.getCounty());
        supervisor.setRegion(changeConfirm.getRegion());
        supervisor.setStationName(changeConfirm.getStationName());
        supervisor.setScene(changeConfirm.getScene());
        supervisor.setLongitude(changeConfirm.getLongitude());
        supervisor.setLatitude(changeConfirm.getLatitude());
        supervisor.setAddress(changeConfirm.getAddress());
        supervisor.setRemarks(changeConfirm.getRemarks());

        return supervisor;
    }

    //需求变更流程
    @PostMapping("/demandChange")
    public RData demandChange(@RequestBody BuildDemandChangeConfirm params) {
        SysUserEntity sysUserEntity = getUser();
        if (sysUserEntity == null) {
            return RData.error(500, "无法获取用户信息");
        }

        String processInstanceId = params.getActProcInstId();
        Supervisor supervisor = supervisorService.selectDataByInsId(processInstanceId);

        String currentStatus = params.getActProcStatus();
        if ("1".equals(currentStatus)) {
            supervisor.setActProcStatus("3");
        } else if ("4".equals(currentStatus)) {
            supervisor.setActProcStatus("5");
        } else if ("6".equals(currentStatus)) {
            supervisor.setActProcStatus("7");
        }

        String remarks = params.getChangeReason() + "。" + params.getRemarks();
        supervisor.setRemarks(remarks);

        boolean supervisorResult = supervisorService.saveOrUpdate(supervisor);
        boolean result = bnccService.saveOrUpdate(params);

        if (result && supervisorResult) {
            Map<String, Object> var = new HashMap<>();
            var.put("user", sysUserEntity);
            var.put("approve", "0");
            return simpleTaskService.complete(processInstanceId, remarks, var);
        }
        return RData.ok("建站需求已变更。");
    }

    //重启变更流程
    @PostMapping("/redemand")
    public RData redemand(@RequestBody BuildDemandChangeConfirm params) {
        SysUserEntity sysUserEntity = getUser();
        if (sysUserEntity == null) {
            return RData.error(500, "无法获取用户信息");
        }

        String processInstanceId = params.getActProcInstId();
        String reason = params.getChangeReason() + "。" + params.getRemarks();

        simpleRuntimeService.cancel(processInstanceId, reason);
        CustomerDemandCollection customerDemandCollection = convertToSupervisor(params);

        RData rData = startProcesses(customerDemandCollection);

        return rData;
    }

    //手动结束流程
    @PostMapping("/shutDownProcess")
    public RData shutDownProcess(@RequestBody Supervisor params) {
        SysUserEntity sysUserEntity = getUser();
        if (sysUserEntity == null) {
            return RData.error(500, "无法获取用户信息");
        }
        String processInstanceId = params.getActProcInstId();
        params.setActProcStatus("3");

        return simpleRuntimeService.cancel(processInstanceId, "站址筛查不通过");
    }

    //手动结束流程
    @PostMapping("/cancelProcess")
    public RData cancelProcess(@RequestBody BuildDemandChangeConfirm params) {
        SysUserEntity sysUserEntity = getUser();
        if (sysUserEntity == null) {
            return RData.error(500, "无法获取用户信息");
        }

        String processInstanceId = params.getActProcInstId();
        String reason = params.getChangeReason() + "。" + params.getRemarks();

        return simpleRuntimeService.cancel(processInstanceId, reason);
    }

    //获取当前人员待办新工单信息
    @GetMapping("/findStartProcessByGroup")
    public RData findStartProcessByGroup(@RequestParam Map<String, Object> params) {
        SysUserEntity sysUserEntity = getUser();
        if (sysUserEntity == null) {
            return RData.error(500, "无法获取用户信息");
        }
        long user_id = sysUserEntity.getUserId();

        //查询当前用户属于哪些group
        List<String> groupList = projectWorkflowGroupService.getStringListByUserId(user_id);
        String groupId = (String) params.get("groupId");
        String key = (String) params.get("key");
        if (groupList.contains(groupId)) {
            //当前用户有权查看的工单集合processInstanceId Set
            RData rData = new RData();
            if (!"".equals(key)) {
                rData = simpleRuntimeService.findProcessByUserOrGroupAndKey(String.valueOf(user_id), groupId, key);
            } else {
                rData = simpleRuntimeService.findProcessByUserOrGroup(String.valueOf(user_id), groupId);
            }

            if ("0".equals(String.valueOf(rData.get("code")))) {
                List<ProjectRightConfigEntity> prcList = projectRightConfigService.getListByUserId(user_id);
                Set<String> processInstanceId = (Set) rData.get("processInstanceId");
                PageUtils page = cncService.findData(prcList, processInstanceId, params);
                return RData.ok().put("page", page);
            }
        }
        return RData.error("无权限");
    }

    //获取当前人员待办审核工单信息
    @GetMapping("/findCheckProcessByGroup")
    public RData findCheckProcessByGroup(@RequestParam Map<String, Object> params) {
        SysUserEntity sysUserEntity = getUser();
        if (sysUserEntity == null) {
            return RData.error(500, "无法获取用户信息");
        }
        long user_id = sysUserEntity.getUserId();
        List<String> groupList = projectWorkflowGroupService.getStringListByUserId(user_id);
        String groupId = (String) params.get("groupId");
        String key = (String) params.get("key");
        if (groupList.contains(groupId)) {
            //当前用户有权查看的工单集合processInstanceId Set
            RData rData = new RData();
            if (!"".equals(key)) {
                rData = simpleRuntimeService.findProcessByUserOrGroupAndKey(String.valueOf(user_id), groupId, key);
            } else {
                rData = simpleRuntimeService.findProcessByUserOrGroup(String.valueOf(user_id), groupId);
            }

            if ("0".equals(String.valueOf(rData.get("code")))) {
                List<ProjectRightConfigEntity> prcList = projectRightConfigService.getListByUserId(user_id);
                Set<String> processInstanceId = (Set) rData.get("processInstanceId");
                PageUtils page = sacService.findData(prcList, processInstanceId, params);
                return RData.ok().put("page", page);
            }
        }
        return RData.error("无权限");
    }

    //获取当前人员待办审核工单信息
    @GetMapping("/findConfirmProcessByGroup")
    public RData findConfirmProcessByGroup(@RequestParam Map<String, Object> params) {
        SysUserEntity sysUserEntity = getUser();
        if (sysUserEntity == null) {
            return RData.error(500, "无法获取用户信息");
        }
        long user_id = sysUserEntity.getUserId();
        List<String> groupList = projectWorkflowGroupService.getStringListByUserId(user_id);
        String groupId = (String) params.get("groupId");

        String key = (String) params.get("key");
        if (groupList.contains(groupId)) {
            //当前用户有权查看的工单集合processInstanceId Set
            RData rData = new RData();
            if (!"".equals(key)) {
                rData = simpleRuntimeService.findProcessByUserOrGroupAndKey(String.valueOf(user_id), groupId, key);
            } else {
                rData = simpleRuntimeService.findProcessByUserOrGroup(String.valueOf(user_id), groupId);
            }

            if ("0".equals(String.valueOf(rData.get("code")))) {
                List<ProjectRightConfigEntity> prcList = projectRightConfigService.getListByUserId(user_id);
                Set<String> processInstanceId = (Set) rData.get("processInstanceId");
                PageUtils page = bocService.findData(prcList, processInstanceId, params);
                return RData.ok().put("page", page);
            }
        }
        return RData.error("无权限");
    }

    //获取当前人员变更工单信息
    @GetMapping("/findDemandChangeDataByGroup")
    public RData findDemandChangeDataByGroup(@RequestParam Map<String, Object> params) {
        SysUserEntity sysUserEntity = getUser();
        if (sysUserEntity == null) {
            return RData.error(500, "无法获取用户信息");
        }
        long user_id = sysUserEntity.getUserId();

        //查询当前用户属于哪些group
        List<String> groupList = projectWorkflowGroupService.getStringListByUserId(user_id);
        String groupId = (String) params.get("groupId");
        String key = (String) params.get("key");
//        params.put("actProcstatus", "1");
        if (groupList.contains(groupId)) {
            RData rData = new RData();
            if (!"".equals(key)) {
                rData = simpleRuntimeService.findProcessByUserOrGroupAndKey(String.valueOf(user_id), groupId, key);
            } else {
                rData = simpleRuntimeService.findProcessByUserOrGroup(String.valueOf(user_id), groupId);
            }
            if ("0".equals(String.valueOf(rData.get("code")))) {
                List<ProjectRightConfigEntity> prcList = projectRightConfigService.getListByUserId(user_id);
                Set<String> processInstanceId = (Set) rData.get("processInstanceId");
                PageUtils page = bnccService.findData(prcList, processInstanceId, params);
                return RData.ok().put("page", page);
            }
        }
        return RData.error("无权限");
    }


    @GetMapping("/queryPMSData")
    public RData queryPMSData(@RequestParam Map<String, Object> params) {
        SysUserEntity sysUserEntity = getUser();
        if (sysUserEntity == null) {
            return RData.error(500, "无法获取用户信息");
        }
        long user_id = sysUserEntity.getUserId();

        Map<String, Object> queryparams = new HashMap<>();

        queryparams.put("STATION_NAME", params.get("queryParam"));
        PageUtils page = pmsService.selectDataByParam(params, queryparams);

        return RData.ok().put("page", page);
    }

    @GetMapping("/queryCRMData")
    public RData queryCRMData(@RequestParam Map<String, Object> params) {
        SysUserEntity sysUserEntity = getUser();
        if (sysUserEntity == null) {
            return RData.error(500, "无法获取用户信息");
        }
//        long user_id = sysUserEntity.getUserId();

        Map<String, Object> queryparams = new HashMap<>();

        queryparams.put("TOWER_STATION_NAME", params.get("queryParam"));
        PageUtils page = crmService.selectDataByParam(params, queryparams);

        return RData.ok().put("page", page);
    }


    //获取表单填充数据
    @GetMapping("/getSupervisorList")
    public RData getSupervisorList(@RequestParam Map<String, Object> params) {
        String actProcInstId = (String) params.get("processInstanceId");
        List<Supervisor> supervisorList = supervisorService.list();
        Supervisor supervisor = supervisorService.selectDataByInsId(actProcInstId);
        return RData.ok().put("returnData", supervisor);
    }


    //获取表单填充数据
    @GetMapping("/fillSupervisorForm")
    public RData fillSupervisorForm(@RequestParam Map<String, Object> params) {
        String actProcInstId = (String) params.get("processInstanceId");
        Supervisor supervisor = supervisorService.selectDataByInsId(actProcInstId);
        return RData.ok().put("returnData", supervisor);
    }

    //获取表单填充数据
    @GetMapping("/fillBNCCForm")
    public RData fillBNCCForm(@RequestParam Map<String, Object> params) {
        String actProcInstId = (String) params.get("processInstanceId");
        BuildDemandChangeConfirm bncc = bnccService.selectDataByInsId(actProcInstId);
        return RData.ok().put("returnData", bncc);
    }

    //获取当前人员待办信息数量
    @GetMapping("/findProcessCount")
    public RData findProcessCount(@RequestParam Map<String, Object> params) {
        SysUserEntity sysUserEntity = getUser();
        if (sysUserEntity == null) {
            return RData.error(500, "无法获取用户信息");
        }
        long user_id = sysUserEntity.getUserId();
        List<String> list = projectWorkflowGroupService.getStringListByUserId(user_id);
        String groupId = (String) params.get("groupId");
        String key = "";
        List<String> processInstanceStatusList = new ArrayList<>();
        if ("3".equals(groupId)) {
            key = "usertask1";
            processInstanceStatusList.add("1");
        } else if ("4".equals(groupId)) {
            key = "usertask2";
            processInstanceStatusList.add("2");
        } else if ("5".equals(groupId)) {
            key = "usertask3";
            processInstanceStatusList.add("4");
        } else if ("6".equals(groupId)) {
            key = "usertask4";
            processInstanceStatusList.add("3");
            processInstanceStatusList.add("5");
            processInstanceStatusList.add("7");
        }
        params.put("actProcStatus", processInstanceStatusList);
        if (list.contains(groupId)) {
            RData rData = simpleRuntimeService.findProcessByUserOrGroupAndKey(String.valueOf(user_id), groupId, key);
            if ("0".equals(String.valueOf(rData.get("code")))) {
                List<ProjectRightConfigEntity> prcList = projectRightConfigService.getListByUserId(user_id);
                Set<String> processInstanceId = (Set) rData.get("processInstanceId");
                Integer resultCount = supervisorService.findDataCount(prcList, processInstanceId, params);
                return RData.ok().put("count", resultCount);
            }

        }
        return RData.ok("0");
    }

    @GetMapping("/findProcessHistoryById")
    public RData findProcessHistoryById(@RequestParam Map<String, Object> params) {
        String processInstanceId = (String) params.get("processInstanceId");
        return RData.ok().put("history", simpleHistoryService.queryHistoryTaskByActProcInstId(processInstanceId));
    }

    @GetMapping("/findProcessHistoryAll")
    public RData findProcessHistoryAll(@RequestParam Map<String, Object> params) {
        int currPage = Integer.parseInt(params.get("page").toString());
        int pageSize = Integer.parseInt(params.get("limit").toString());
        String processInstanceId = (String) params.get("processInstanceId");
        return RData.ok().put("history", simpleHistoryService.queryHistoryTaskByActProcInstId(processInstanceId));
    }

    @GetMapping("/findProcessHistoryByUser")
    public RData findProcessHistoryByUser(@RequestParam Map<String, Object> params) {
        SysUserEntity sysUserEntity = getUser();
        if (sysUserEntity == null) {
            return RData.error(500, "无法获取用户信息");
        }
        long user_id = sysUserEntity.getUserId();
        String userId = String.valueOf(user_id);

        List<String> procInstIdList = simpleHistoryService.queryHistoryFinishedProcByStartUserId(userId);

        Set<String> procInstIdSet = new HashSet<>(procInstIdList);

        PageUtils page = supervisorService.selectDataByParam(params,procInstIdSet);

        return RData.ok().put("page", page);
    }

    @GetMapping("/processImg")
    public void processImg(@RequestParam String processInstanceId, HttpServletResponse response) {
        try {
            if (StringUtils.isEmpty(processInstanceId)) {
                logger.error("processInstanceId is null");
                return;
            }
            response.setHeader("Cache-Control", "no-store, no-cache");
            response.setContentType("image/jpeg");
            InputStream inputStream = simpleHistoryService.getFlowImgByInstanceId(processInstanceId);
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(ImageIO.read(inputStream), "png", out);
            IOUtils.closeQuietly(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //批量提交流程
    @PostMapping("/upload")
    public RData upload(@RequestParam("file") MultipartFile file, @RequestParam("groupId") String groupId) {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        logger.info(suffix);

        List<String> xqbh = new ArrayList<>();
        try {
            if ("3".equals(groupId)) {
                List<CustomerDemandCollection> list = ExcelUtil.readExcel(file, CustomerDemandCollection.class, 1, 2);
                for (CustomerDemandCollection customerDemand : list) {
                    RData rData = startProcesses(customerDemand);
                    if ("0".equals(String.valueOf(rData.get("code"))) && StringUtils.isNotEmpty(String.valueOf(rData.get("processInstanceId")))) {
                        logger.info("建站需求流程ID[{}] 创建成功", rData.get("processInstanceId"));
                    }
                }
            } else if ("4".equals(groupId)) {
                List<StationAddressCheck> list = ExcelUtil.readExcel(file, StationAddressCheck.class, 1, 2);
                List<Supervisor> supervisorList = BeanCopyUtils.convert(list, Supervisor.class);
                for (Supervisor addressCheck : supervisorList) {

                    if ("2".equals(addressCheck.getActProcStatus())) {
                        addressCheck.setApprove("1");
                    } else if ("3".equals(addressCheck.getActProcStatus())) {
                        addressCheck.setApprove("0");
                    }
                    RData rData = checkProcesses(addressCheck);
                    if ("0".equals(String.valueOf(rData.get("code"))) && StringUtils.isNotEmpty(String.valueOf(rData.get("processInstanceId")))) {
                        logger.info("审批工单ID[{}] 成功", rData.get("processInstanceId"));
                    }
                }
            } else if ("5".equals(groupId)) {
                List<BuildOrderConfirm> list = ExcelUtil.readExcel(file, BuildOrderConfirm.class, 1, 2);
                List<Supervisor> supervisorList = BeanCopyUtils.convert(list, Supervisor.class);
                for (Supervisor orderConfirm : supervisorList) {
                    if ("4".equals(orderConfirm.getActProcStatus())) {
                        orderConfirm.setApprove("1");
                    } else if ("5".equals(orderConfirm.getActProcStatus())) {
                        orderConfirm.setApprove("0");
                    }
                    RData rData = checkProcesses(orderConfirm);
                    if ("0".equals(String.valueOf(rData.get("code"))) && StringUtils.isNotEmpty(String.valueOf(rData.get("processInstanceId")))) {
                        logger.info("建站需求确认ID[{}] 工单成功", rData.get("processInstanceId"));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (xqbh.size() > 0) {
            return RData.error(401, "部分流程创建失败").put("list", xqbh);
        }
        return RData.ok();
    }

    @GetMapping("/export")
    public void export(@RequestParam("groupId") String groupId, HttpServletResponse response) {
        SysUserEntity sysUserEntity = getUser();
        if (sysUserEntity == null) {
            return;
        }
        Map<String, Object> params = new HashMap<>();
        long user_id = sysUserEntity.getUserId();
        List<String> groupList = projectWorkflowGroupService.getStringListByUserId(user_id);   //查看该用户属于哪些用户组

        if (groupList.contains(groupId)) {
            RData rData = simpleRuntimeService.findProcessByUserOrGroup(String.valueOf(user_id), groupId);
            if ("0".equals(String.valueOf(rData.get("code")))) {
                if (groupId.equals("3")) {
                    params.put("actProcStatus", "1");
                    List<ProjectRightConfigEntity> prcList = projectRightConfigService.getListByUserId(user_id);
                    Set<String> processInstanceId = (Set) rData.get("processInstanceId");
                    List<Supervisor> supervisorList = supervisorService.findDataAll(prcList, processInstanceId, params);

                    List<StationAddressCheck> checkList = BeanCopyUtils.convert(supervisorList, StationAddressCheck.class);
                    ExcelUtil.writeExcel(response, checkList, "table3_export", "sheet1", ExcelTypeEnum.XLSX, StationAddressCheck.class);
                } else if (groupId.equals("4")) {
                    params.put("actProcStatus", "2");
                    List<ProjectRightConfigEntity> prcList = projectRightConfigService.getListByUserId(user_id);
                    Set<String> processInstanceId = (Set) rData.get("processInstanceId");
                    List<Supervisor> supervisorList = supervisorService.findDataAll(prcList, processInstanceId, params);
                    List<BuildOrderConfirm> checkList = BeanCopyUtils.convert(supervisorList, BuildOrderConfirm.class);
                    ExcelUtil.writeExcel(response, checkList, "table4_export", "sheet1", ExcelTypeEnum.XLSX, BuildOrderConfirm.class);
                } else if (groupId.equals("5")) {
                    params.put("actProcStatus", "4");
                    List<ProjectRightConfigEntity> prcList = projectRightConfigService.getListByUserId(user_id);
                    Set<String> processInstanceId = (Set) rData.get("processInstanceId");
                    List<Supervisor> supervisorList = supervisorService.findDataAll(prcList, processInstanceId, params);

                    List<BuildOrderConfirm> checkList = BeanCopyUtils.convert(supervisorList, BuildOrderConfirm.class);
                    ExcelUtil.writeExcel(response, checkList, "table5_export", "sheet1", ExcelTypeEnum.XLSX, BuildOrderConfirm.class);

                }

            } else {
                throw new RRException("无导出权限");
            }


        } else {
            throw new RRException("无导出权限");
        }
    }

    //批量提交PMS
    @PostMapping("/pmsUpload")
    public RData pmsUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        logger.info(suffix);

        try {
            List<PMS> list = ExcelUtil.readExcel(file, PMS.class, 1, 2);
            for (PMS pms : list) {
                pmsService.save(pms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RData.ok();
    }

    //批量提交流程
    @PostMapping("/crmUpload")
    public RData crmUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        logger.info(suffix);

        try {
            List<CRM> list = ExcelUtil.readExcel(file, CRM.class, 1, 2);
            for (CRM crm : list) {
                crmService.save(crm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RData.ok();
    }

    //站址信息表上传
    @PostMapping("/stationInfoUpload")
    public RData stationInfoUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        long startTime = System.currentTimeMillis();
        try {
            List<StationAddressInfo> list = ExcelUtil.readExcel(file, StationAddressInfo.class, 1, 1);

            for (StationAddressInfo stationInfo : list) {
                stationAddressInfoService.save(stationInfo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long haoshi = (endTime - startTime) / 1000;
        logger.info("耗时" + haoshi + "秒。");
        return RData.ok("操作成功,耗时" + haoshi + "秒。");
    }

    //站址管理表上传
    @PostMapping("/stationManageUpload")
    public RData stationManageUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        long startTime = System.currentTimeMillis();
        try {
            List<StationAddressManagement> list = ExcelUtil.readExcel(file, StationAddressManagement.class, 1, 2);

            for (StationAddressManagement stationManagement : list) {
                stationManageService.save(stationManagement);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long haoshi = (endTime - startTime) / 1000;
        logger.info("耗时" + haoshi + "秒。");
        return RData.ok("操作成功,耗时" + haoshi + "秒。");
    }

    @GetMapping("/chartTaskStatusCount")
    public RData chartTaskStatusCount() {
        String[] definitionKey = {"usertask1", "usertask2", "usertask3", "usertask4", "usertask5"};

        Set<Map> resultSet = new HashSet<>();
        for (String key : definitionKey) {
            Map resultMap = new HashMap();
            int count = simpleRuntimeService.countTaskByDefKey(key);
            if (key.equals("usertask1")) {
                resultMap.put("name", "需求收集");
                resultMap.put("value", count);
            } else if (key.equals("usertask2")) {
                resultMap.put("name", "站址筛查");
                resultMap.put("value", count);
            }
            if (key.equals("usertask3")) {
                resultMap.put("name", "建站确认");
                resultMap.put("value", count);
            }
            if (key.equals("usertask5")) {
                resultMap.put("name", "需求变更");
                resultMap.put("value", count);
            }
            resultSet.add(resultMap);
        }
        RData result = RData.ok();
        result.put("resultData", resultSet);
        return result;
    }

    @GetMapping("/chartBranchCompanyCount")
    public RData chartBranchCompanyCount() {
        String[] branchCompany = {"海口", "三亚", "琼海", "儋州"};

        Set<Map> resultSet = new HashSet<>();
        for (String cityName : branchCompany) {
            Map resultMap = new HashMap();
            String count = supervisorService.countCityNum(cityName);
            resultMap.put("name", cityName);
            resultMap.put("value", Integer.parseInt(count));
            resultSet.add(resultMap);
        }
        RData result = RData.ok();
        result.put("resultData", resultSet);
        return result;
    }

    @GetMapping("/queryStationAddressInfo")
    public RData queryStationAddressInfo(@RequestParam Map<String, Object> params) {
        String queryParamString = params.get("queryParam").toString();
        Map<String, Object> queryParams = JSON.parseObject(queryParamString, Map.class);

        PageUtils page = stationAddressInfoService.selectDataByParam(params, queryParams);

        return RData.ok().put("page", page);

    }

    @GetMapping("/queryStationAddressManagement")
    public RData queryStationAddressManagement(@RequestParam Map<String, Object> params) {
        String queryParamString = params.get("queryParam").toString();
        Map<String, Object> queryParams = JSON.parseObject(queryParamString, Map.class);
        PageUtils page = stationManageService.selectDataByParam(params, queryParams);
        return RData.ok().put("page", page);
    }

    @GetMapping("/stationAddressManagementList")
    public RData stationAddressManagementList(@RequestParam Map<String, Object> params) {
        PageUtils page = stationManageService.queryPage(params);
        return RData.ok().put("page", page);
    }

    @GetMapping("/getStationCounty")
    public RData getStationCounty() {
        List<String> countyList = stationManageService.getStationCounty();
        return RData.ok().put("countyList", countyList);
    }

}
