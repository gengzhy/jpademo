package com.iangeng.basic.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: gian
 * @date: 2021-10-24 21:16
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResultVO<T> {
    private int code;
    private String msg;
    private T data;
}
