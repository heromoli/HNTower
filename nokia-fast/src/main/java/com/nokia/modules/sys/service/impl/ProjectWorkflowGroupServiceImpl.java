package com.nokia.modules.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.sys.dao.ProjectWorkflowGroupDao;
import com.nokia.modules.sys.entity.ProjectWorkflowGroupEntity;
import com.nokia.modules.sys.service.ProjectWorkflowGroupService;
import com.nokia.utils.PageUtils;
import com.nokia.utils.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by wow on 2019/6/8.
 */
@Service("ProjectWorkflowGroupService")
public class ProjectWorkflowGroupServiceImpl extends ServiceImpl<ProjectWorkflowGroupDao, ProjectWorkflowGroupEntity> implements ProjectWorkflowGroupService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String queryValue = (String)params.get("queryValue");

        IPage<ProjectWorkflowGroupEntity> page = this.page(
                new Query<ProjectWorkflowGroupEntity>().getPage(params),
                new QueryWrapper<ProjectWorkflowGroupEntity>()
//                        .like(StringUtils.isNotBlank(queryValue),"value", queryValue)
        );

        return new PageUtils(page);
    }

    @Override
    public void saveConfig(ProjectWorkflowGroupEntity config) {
        this.save(config);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectWorkflowGroupEntity config) {
        this.updateById(config);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] ids) {
        this.removeByIds(Arrays.asList(ids));
    }

    @Override
    public List<ProjectWorkflowGroupEntity> getListByUserId(Long userId) {
        return baseMapper.queryListUserId(userId);
    }

    @Override
    public List<String> getStringListByUserId(Long userId) {
        List<ProjectWorkflowGroupEntity> list = getListByUserId(userId);
        List<String> l = new ArrayList<>();
        if(list != null){
            for(ProjectWorkflowGroupEntity projectWorkflowGroupEntity:list){
                l.add(projectWorkflowGroupEntity.getGroupId());
            }
        }
        return l;
    }

}
