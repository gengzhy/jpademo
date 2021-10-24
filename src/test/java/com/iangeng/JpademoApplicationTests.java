package com.iangeng;

import com.iangeng.dao.ClassRepository;
import com.iangeng.dao.RecordRepository;
import com.iangeng.dao.UserRepository;
import com.iangeng.entity.ClassEntity;
import com.iangeng.entity.RecordEntity;
import com.iangeng.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class JpademoApplicationTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private RecordRepository recordRepository;

    @Test
    public void test() {
        UserEntity user = new UserEntity();
        user.setUserName("喵星人");
        user.setCreateTime(new Date());
        userRepository.save(user);
        System.out.println(user);

        ClassEntity clazz = new ClassEntity();
        clazz.setClassName("一年级2班");
        clazz.setCreateTime(new Date());
        classRepository.save(clazz);
        System.out.println(clazz);

        RecordEntity record = new RecordEntity();
        record.setClassId(clazz.getId());
        record.setUserId(user.getId());
        record.setMemo("添加了一个用户及班级！");
        record.setCreateTime(new Date());
        record.setCreator("喵星人");
        recordRepository.save(record);
        System.out.println(record);

    }

}
