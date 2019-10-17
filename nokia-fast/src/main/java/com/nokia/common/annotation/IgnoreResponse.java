package com.nokia.common.annotation;

import java.lang.annotation.*;

/**
 * Created by wow on 2019/6/6.
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreResponse {
    String value() default "";
}
