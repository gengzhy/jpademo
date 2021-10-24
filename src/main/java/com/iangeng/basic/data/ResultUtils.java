package com.iangeng.basic.data;

/**
 * @author: gian
 * @date: 2021-10-24 21:18
 */
public class ResultUtils {

    public static ResultVO fail() {
        return vo(401, "处理失败.");
    }

    public static <T> ResultVO fail(T data) {
        return vo(401, "处理失败.", data);
    }

    public static ResultVO fail(String msg) {
        return vo(401, msg);
    }

    public static ResultVO success() {
        return vo(200, "处理成功.");
    }

    public static <T> ResultVO success(T data) {
        return vo(200, "处理成功.", data);
    }

    public static ResultVO success(String msg) {
        return vo(200, msg);
    }

    public static ResultVO vo(int code, String msg) {
        return vo(code, msg, null);
    }

    public static <T> ResultVO vo(int code, String msg, T data) {
        return new ResultVO(code, msg, data);
    }
}
