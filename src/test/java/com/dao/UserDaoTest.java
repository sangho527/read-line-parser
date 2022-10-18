package com.dao;

import com.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserDaoTest {

    @Test
    void addAndSelect() {
//        AWSUserDaoImpl userDao = new AWSUserDaoImpl(); // 리스코프치환의 법칙을지킴
//        String id = "19";
//        userDao.add(new User(id, "Lina", "12345"));
//        User user = userDao.findById(id);
//        Assertions.assertEquals("Lina", user.getName());

        UserDao userDao = new UserDao();
        String id = "21";
        User user = new User(id, "Eternity", "1123");
        userDao.add(user);

        User selectedUser = userDao.findById(id);
        Assertions.assertEquals("Eternity", selectedUser.getName());
        Assertions.assertEquals("1123", selectedUser.getPassword());
    }
}