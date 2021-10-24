package com.iangeng.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: gian
 * @date: 2021-10-16 20:24
 */
@Data
@Entity
@Table(name = "t_class")
public class ClassEntity implements Serializable {
    private static Long serialVersionUID = 1L;

    @Id
    @Column(name = "class_id", length = 32)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "class_name", length = 255, nullable = false)
    private String className;

    @Column(name = "create_time" , length = 19, nullable = false)
    private Date createTime;

}
