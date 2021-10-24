package com.iangeng.entity.pk;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author: gian
 * @date: 2021-10-16 21:23
 */
@Data
public class UserClassPk implements Serializable {
    private static Long serialVersionUID = 1L;
    private String userId;
    private String classId;
}
