package com.nokia.modules.sys.controller;

import com.nokia.modules.sys.entity.SysUserEntity;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.slf4j.LoggerFactory;

/**
 * Created by wow on 2019/6/8.
 */

public class BaseController {
    protected SysUserEntity getUser() {
        return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }
}
