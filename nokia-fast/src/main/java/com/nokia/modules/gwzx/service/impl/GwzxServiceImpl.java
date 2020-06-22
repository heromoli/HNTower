package com.nokia.modules.gwzx.service.impl;

import com.nokia.modules.gwzx.dao.GwzxDao;
import com.nokia.modules.gwzx.service.GwzxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/6/15 0015.
 */
@Service
public class GwzxServiceImpl implements GwzxService {

    @Autowired
    private GwzxDao gwzxDao;


    @Override
    public List getLoss() {
        return gwzxDao.getLoss();
    }

    @Override
    public List getCentreDown() {
        return gwzxDao.getCentreDown();
    }

    @Override
    public List getLeftUp() {
        return gwzxDao.getLeftUp();
    }

    @Override
    public List getLeftCentre() {
        return gwzxDao.getLeftCentre();
    }

    @Override
    public List getLeftDown() {
        return gwzxDao.getLeftDown();
    }

    @Override
    public List getRightDown() {
        return gwzxDao.getRightDown();
    }
}
