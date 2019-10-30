package com.nokia.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nokia.modules.sys.entity.ProjectWorkflowGroupEntity;
import com.nokia.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by wow on 2019/6/8.
 */
public interface ProjectWorkflowGroupService extends IService<ProjectWorkflowGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存配置信息
     */
    public void saveConfig(ProjectWorkflowGroupEntity config);

    public void saveOrUpdate(Long userId, List<Long> groupIdList);

    /**
     * 更新配置信息
     */
    public void update(ProjectWorkflowGroupEntity config);


    /**
     * 删除配置信息
     */
    public void deleteBatch(Long[] ids);

    public List<ProjectWorkflowGroupEntity> getListByUserId(Long userId);

    public List<String> getStringListByUserId(Long userId);
}
