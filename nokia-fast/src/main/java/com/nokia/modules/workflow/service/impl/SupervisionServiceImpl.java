package com.nokia.modules.workflow.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.workflow.dao.PMSDao;
import com.nokia.modules.workflow.dao.SupervisionDao;
import com.nokia.modules.workflow.entity.PMS;
import com.nokia.modules.workflow.entity.Supervision;
import com.nokia.modules.workflow.service.PMSService;
import com.nokia.modules.workflow.service.SupervisionService;
import com.nokia.utils.PageUtils;
import com.nokia.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("SupervisionService")
public class SupervisionServiceImpl extends ServiceImpl<SupervisionDao, Supervision> implements SupervisionService {


}
