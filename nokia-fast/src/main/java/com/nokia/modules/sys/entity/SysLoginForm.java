package com.nokia.modules.sys.entity;

import lombok.Data;

/**
 * Created by wow on 2019/6/8.
 */
@Data
public class SysLoginForm {
    private String username;
    private String password;
    private String captcha;
    private String uuid;
}
