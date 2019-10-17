package com.nokia.common.exception;

import com.nokia.utils.RData;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created by wow on 2019/6/6.
 */
@RestControllerAdvice
public class RRExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(RRException.class)
    public RData handleRRException(RRException e){
        RData r = new RData();
        r.put("code", e.getCode());
        r.put("msg", e.getMessage());

        return r;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public RData handlerNoFoundException(Exception e) {
        logger.error(e.getMessage(), e);
        return RData.error(404, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public RData handleDuplicateKeyException(DuplicateKeyException e){
        logger.error(e.getMessage(), e);
        return RData.error("数据库中已存在该记录");
    }

    @ExceptionHandler(AuthorizationException.class)
    public RData handleAuthorizationException(AuthorizationException e){
        logger.error(e.getMessage(), e);
        return RData.error("没有权限，请联系管理员授权");
    }

    @ExceptionHandler(Exception.class)
    public RData handleException(Exception e){
        logger.error(e.getMessage(), e);
        return RData.error();
    }
}
