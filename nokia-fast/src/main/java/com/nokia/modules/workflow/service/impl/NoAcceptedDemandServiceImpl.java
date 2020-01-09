package com.nokia.modules.workflow.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.workflow.dao.NoAcceptedDemandDao;
import com.nokia.modules.workflow.entity.NoAcceptedDemand;
import com.nokia.modules.workflow.service.NoAcceptedDemandService;
import org.springframework.stereotype.Service;

@Service("NoAcceptedDemandService")
public class NoAcceptedDemandServiceImpl extends ServiceImpl<NoAcceptedDemandDao, NoAcceptedDemand> implements NoAcceptedDemandService {


}
