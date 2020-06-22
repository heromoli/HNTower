package com.nokia.modules.disaster.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.disaster.dao.DisasterSufferDataDao;
import com.nokia.modules.disaster.entity.DisasterSufferData;
import com.nokia.modules.disaster.service.DisasterSufferDataService;
import org.springframework.stereotype.Service;

@Service("disasterSufferDataService")
public class DisasterSufferDataServiceImpl extends ServiceImpl<DisasterSufferDataDao, DisasterSufferData> implements DisasterSufferDataService {

}
