package com.iangeng.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: gian
 * @date: 2021-10-24 15:37
 */
@Slf4j
@Configuration
public class WebConfigure implements WebMvcConfigurer {

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("Initialized Customer HandlerInterceptor: {}, with filter Paths: /**", WebRequestHandler.class.getName());
        registry.addInterceptor(new WebRequestHandler()).addPathPatterns("/**");
    }
}
