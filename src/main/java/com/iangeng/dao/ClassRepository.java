package com.iangeng.dao;

import com.iangeng.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: gian
 * @date: 2021-10-16 20:40
 */
public interface ClassRepository extends JpaRepository<ClassEntity, String> {
}
