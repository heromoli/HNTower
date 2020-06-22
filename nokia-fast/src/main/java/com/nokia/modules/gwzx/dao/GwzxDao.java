package com.nokia.modules.gwzx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/6/15 0015.
 */
@Mapper
public interface GwzxDao extends BaseMapper {

    List getLoss();

    List getCentreDown();

    List getLeftUp();

    List getLeftCentre();

    List getLeftDown();

    List getRightDown();


}
