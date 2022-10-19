package com.dao;

import com.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)

class UserDaoTest {

    @Autowired
    ApplicationContext context;

    @Test
    void addAndSelect() {
        UserDao userDao = context.getBean("awsUserDao", UserDao.class);
        String id = "23";
        userDao.add(new User(id, "Kim", "11232"));
        User selectedUser = userDao.findById(id);
        Assertions.assertEquals("Kim", selectedUser.getName());
    }
}