package com.nokia.datasource.annotation;

import java.lang.annotation.*;

/**
 * Created by wow on 2019/6/7.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {
    String value() default "";
}
