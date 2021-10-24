package com.iangeng.basic;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import com.iangeng.basic.annotation.Limit;
import com.iangeng.basic.data.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 接口限流切面
 *
 * @author: gian
 * @date: 2021-10-24 18:19
 */
@Slf4j
@Aspect
@Component
public class LimitAspect {
    /**
     * 限流接口（不同的接口有各自的限流策略）
     */
    private final Map<String, RateLimiter> limiterMap = Maps.newConcurrentMap();

    /**
     * 定义切面
     */
    @Pointcut("@annotation(com.iangeng.basic.annotation.Limit)")
    public void cut(){}

    @Around("cut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.isAnnotationPresent(Limit.class)) {
            Limit limit = method.getAnnotation(Limit.class);
            // 添加令牌桶
            String key = ObjectUtils.isEmpty(limit.key())
                    ? String.join("_", joinPoint.getTarget().getClass().getSimpleName(), method.getName())
                    : limit.key();
            key = "token_bucket_" + key;
            if (limiterMap.get(key) == null) {
                limiterMap.put(key, RateLimiter.create(limit.permitsPerSecond()));
                log.info("初始化令牌桶={}, 每秒存入令牌桶的令牌数={}", key, limit.permitsPerSecond());
            }

            // 拿到令牌
            if (!limiterMap.get(key).tryAcquire(limit.timeout(), limit.timeunit())) {
                log.warn("从令牌桶={}获取令牌失败.", key);
                this.responseFail(limit.msg());
                return null;
            }
        }
        return joinPoint.proceed();
    }

    private void responseFail(String msg) throws IOException {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String json = JSONObject.toJSONString(ResultUtils.fail(msg));
        log.error(json);
        response.getWriter().write(json);
    }
}
