package com.nokia.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by wow on 2019/6/26.
 */
@Data
@TableName("project_right_config")
public class ProjectRightConfigEntity {
    @TableId
    private Long id;
    @NotBlank(message="用户不能为空")
    private int userId;
    @NotBlank(message="类型不能为空")
    private String company;
    @NotBlank(message="值不能为空")
    private String operator;

    @TableField(exist=false)
    private String userName;
}
