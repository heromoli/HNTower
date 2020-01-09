package com.nokia.modules.workflow.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.workflow.dao.TowerDemandExportDao;
import com.nokia.modules.workflow.entity.TowerDemandExport;
import com.nokia.modules.workflow.service.TowerDemandExportService;
import org.springframework.stereotype.Service;

@Service("TowerDemandExportService")
public class TowerDemandExportServiceImpl extends ServiceImpl<TowerDemandExportDao, TowerDemandExport> implements TowerDemandExportService {


}
