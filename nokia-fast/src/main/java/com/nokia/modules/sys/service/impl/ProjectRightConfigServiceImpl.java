package com.nokia.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.sys.dao.ProjectRightConfigDao;
import com.nokia.modules.sys.entity.ProjectRightConfigEntity;
import com.nokia.modules.sys.service.ProjectRightConfigService;
import com.nokia.utils.PageUtils;
import com.nokia.utils.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("ProjectRightConfigService")
public class ProjectRightConfigServiceImpl extends ServiceImpl<ProjectRightConfigDao, ProjectRightConfigEntity> implements ProjectRightConfigService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String queryValue = (String) params.get("key");

        IPage<ProjectRightConfigEntity> page = this.page(
                new Query<ProjectRightConfigEntity>().getPage(params),
                new QueryWrapper<ProjectRightConfigEntity>()
                        .like(StringUtils.isNotBlank(queryValue), "company", queryValue).orderByAsc("user_id")
        );

        return new PageUtils(page);
    }

    @Override
    public void saveConfig(ProjectRightConfigEntity config) {
        this.save(config);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectRightConfigEntity config) {
        this.updateById(config);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByUserId(int userId) {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("USER_ID",userId);
        this.removeByMap(columnMap);
    }

    @Override
    public List<ProjectRightConfigEntity> getListByUserId(Long userId) {
        return baseMapper.queryListUserId(userId);
    }

    @Override
    public List<ProjectRightConfigEntity> getStatusList() {
        return this.baseMapper.selectList(new QueryWrapper<ProjectRightConfigEntity>().eq("status", "1"));
    }


}
