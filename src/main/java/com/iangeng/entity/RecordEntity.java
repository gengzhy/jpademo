package com.iangeng.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iangeng.entity.pk.UserClassPk;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: gian
 * @date: 2021-10-16 21:20
 */
@Data
@Entity
@Table(name = "t_record")
@IdClass(UserClassPk.class)
public class RecordEntity implements Serializable {
    private static Long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id", length = 32)
    private String userId;

    @Id
    @Column(name = "class_id", length = 32)
    private String classId;

    @Column(name = "memo", length = 64)
    private String memo;

    @Column(name = "create_time", nullable = false, length = 19)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy:MM:dd HH:mm:ss")
    private Date createTime;

    @Value("${cust.user-name}")
    @Column(name = "creator", length = 32)
    private String creator;

}
