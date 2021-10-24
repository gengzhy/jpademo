package com.iangeng.basic;

import com.iangeng.basic.annotation.SysCore;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author: gian
 * @date: 2021-10-21 20:37
 */
@Slf4j
@Component
@Aspect
public class BusiAspect {

    @Pointcut("execution(public * com.iangeng..*.*(..))&&@annotation(com.iangeng.basic.annotation.SysCore)")
    private void cut() {}

    @Before("cut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class type = signature.getDeclaringType();
        Class[] parameterTypes = signature.getParameterTypes();
        String[] parameterNames = signature.getParameterNames();
        log.info("正在访问方法：{}_{}_{}.{}({})", Modifier.toString(method.getModifiers()), method.getReturnType().getName(),
                type.getName(), method.getName(), buildRequestParams(parameterTypes, parameterNames));
        // 解析注解
        if (method.isAnnotationPresent(SysCore.class)) {
            SysCore sysCore = method.getAnnotation(SysCore.class);
            log.info("注解信息：{}-{}-{}-{}", sysCore.cls().getName(), sysCore.method(), sysCore.params(), sysCore.memo());
        }

    }

    private String buildRequestParams(Class[] paramTypes, String[] paramNames) {
        if (paramTypes == null || paramTypes.length == 0) {
            return "";
        }
        StringBuilder param = new StringBuilder();
        for (int i = 0, len = paramTypes.length; i < len; i++) {
            param.append(paramTypes[i].getName()).append(" ").append(paramNames[i]);
            if (i < len - 1) {
                param.append(", ");
            }
        }
        return param.toString();
    }


}
