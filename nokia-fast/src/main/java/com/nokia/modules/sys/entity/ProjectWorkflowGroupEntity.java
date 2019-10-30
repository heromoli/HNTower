package com.nokia.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by wow on 2019/7/8.
 */
@Data
@TableName("project_workflow_group")
public class ProjectWorkflowGroupEntity {
    @TableId
    private Long id;
    @NotBlank(message="用户不能为空")
    private Long userId;
    @NotBlank(message="分组ID")
    private Long groupId;
    private String groupName;

    @TableField(exist=false)
    private String userName;
}
