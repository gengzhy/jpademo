package com.iangeng.dao;

import com.iangeng.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: gian
 * @date: 2021-10-16 20:40
 */
public interface UserRepository extends JpaRepository<UserEntity, String> {
}
