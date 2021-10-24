package com.iangeng.basic.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 限流注解
 * @author: gian
 * @date: 2021-10-24 18:08
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Limit {

    /**
     * 限流key，唯一；不同的接口有不同的限流策略
     * 没设置值，则取再整执行的类名+方法名
     *
     * @return
     */
    String key() default "";

    /**
     * 每秒存入令牌桶的令牌数（QPS）
     */
    double permitsPerSecond() default 5.0;

    /**
     * 从令牌桶中获得令牌的最大等待时间（默认100毫秒-timeunit单位默认的情况）
     */
    long timeout() default 200L;

    /**
     * 从令牌桶中获得令牌的最大等待时间单位（默认：毫秒）
     */
    TimeUnit timeunit() default TimeUnit.MILLISECONDS;

    /**
     * 未拿到令牌超时提示消息
     */
    String msg() default "系统繁忙，请稍后再试.";
}
