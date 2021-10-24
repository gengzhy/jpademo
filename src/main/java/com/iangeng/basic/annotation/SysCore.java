package com.iangeng.basic.annotation;

import java.lang.annotation.*;

/**
 * 记录信息
 * @author gian
 * @date 2021-10-21 20:44
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SysCore {
    Class<?> cls() default Object.class;
    String method() default "";
    String[] params() default "";
    String memo() default "";
}
