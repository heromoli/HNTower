package com.nokia.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.sys.entity.ProjectRightConfigEntity;
import com.nokia.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by wow on 2019/6/8.
 */
public interface ProjectRightConfigService extends IService<ProjectRightConfigEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存配置信息
     */
    public void saveConfig(ProjectRightConfigEntity config);

    /**
     * 更新配置信息
     */
    public void update(ProjectRightConfigEntity config);



    /**
     * 删除配置信息
     */
    public void deleteById(Long id);

    public List<ProjectRightConfigEntity> getListByUserId(Long userId);

    public List<ProjectRightConfigEntity> getStatusList();
}
