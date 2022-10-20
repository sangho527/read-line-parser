package com.dao;

import com.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class) //Junit에서 Spring ApplicationContext를 쓸 수 있게 해주는 기능
@ContextConfiguration(classes = UserDaoFactory.class) //Junit5 Test코드를 실행 할 때 ApplicationContext에 들어갈 설정정보(관계 설정)를 불러오게 해주는 기능

class UserDaoTest {

    @Autowired
    ApplicationContext context;
    UserDao userDao;
    User user1;
    User user2;
    User user3;

    @BeforeEach
    void Setup() {
        userDao = context.getBean("awsUserDao", UserDao.class);
        user1 = new User("1" , "Sang", "1323");
        user2 = new User("2" , "ho", "13233");
        user3 = new User("3" , "Sing", "1323");
    }

    @Test
    void addAndSelect() {
        UserDao userDao = context.getBean("awsUserDao", UserDao.class);
        UserDao userDao2 = context.getBean("awsUserDao", UserDao.class);

        System.out.println(userDao);
        System.out.println(userDao2);

        String id = "27";
        userDao.add(new User(id, "Kim", "11232"));

        User selectedUser = userDao.findById(id);
        assertEquals("Kim", selectedUser.getName());
    }

    @Test
    void count() throws SQLException {

        User user1 = new User("1", "geonwoo", "1131");
        User user2 = new User("2", "joon", "0628");
        User user3 = new User("3", "hyunmeen", "0312");

        UserDao userDao = context.getBean("awsUserDao", UserDao.class);
        userDao.deleteAll();
        assertEquals(0, userDao.getCount());

        userDao.add(user1);
        assertEquals(1, UserDao.getCount());
        userDao.add(user2);
        assertEquals(2, UserDao.getCount());
        userDao.add(user3);
        assertEquals(3, UserDao.getCount());

    }

    @Test
    void addAndGet() throws SQLException {
        UserDao userDao = context.getBean("awsUserDao", UserDao.class);
        userDao.deleteAll();
        assertEquals(0, userDao.getCount());

        String id = "1";
        userDao.add(new User(id, "Kim", "1234"));
        assertEquals(1, userDao.getCount());
        User user = userDao.findById(id);

        assertEquals("Kim", user.getName());
        assertEquals("1234", user.getPassword());
    }
}