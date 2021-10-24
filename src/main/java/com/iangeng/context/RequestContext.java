package com.iangeng.context;

import java.util.Map;

/**
 * @author: gian
 * @date: 2021-10-24 15:24
 */
public class RequestContext {
    private final static ThreadLocal<Object> request_context = new ThreadLocal<>();

    public static void set(Object obj) {
        request_context.set(obj);
    }

    public static Object get() {
        return request_context.get();
    }

    public static void release() {
        request_context.remove();
    }
}
