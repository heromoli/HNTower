package com.nokia.common.annotation;

import java.lang.annotation.*;

/**
 * Created by wow on 2019/6/6.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
}
