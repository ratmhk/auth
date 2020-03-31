package com.entity;


import java.lang.annotation.*;

/**
 * 自定义日志注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface LogAnnotation {

    /**模块名称*/
    String moduleName() default "";
    /**操作内容*/
    String operate() default "";
}
