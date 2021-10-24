package com.iangeng.controller;

import com.iangeng.basic.annotation.Limit;
import com.iangeng.basic.annotation.SysCore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author: gian
 * @date: 2021-10-21 21:12
 */
@RestController
public class TestController {

    @Limit(timeout = 100L)
    @SysCore(cls = TestController.class, method = "hello")
    @RequestMapping("/hello")
    public String hello(String name, String id, Map<String, Object> map,
                        HttpServletRequest request, HttpServletResponse response) {
        return "hello";
    }
}
