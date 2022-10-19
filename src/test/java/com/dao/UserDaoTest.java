package com.dao;

import com.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class) //Junit에서 Spring ApplicationContext를 쓸 수 있게 해주는 기능
@ContextConfiguration(classes = UserDaoFactory.class) //Junit5 Test코드를 실행 할 때 ApplicationContext에 들어갈 설정정보(관계 설정)를 불러오게 해주는 기능

class UserDaoTest {

    @Autowired
    ApplicationContext context;

    @Test
    void addAndSelect() {
        UserDao userDao = context.getBean("awsUserDao", UserDao.class);
        UserDao userDao2 = context.getBean("awsUserDao", UserDao.class);

        System.out.println(userDao);
        System.out.println(userDao2);

        String id = "24";
        userDao.add(new User(id, "Kim", "11232"));

        User selectedUser = userDao.findById(id);
        Assertions.assertEquals("Kim", selectedUser.getName());
    }
}