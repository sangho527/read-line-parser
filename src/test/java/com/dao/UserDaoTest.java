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

        UserDao userDao = new UserDaoFactory().awsUserDao();
        String id = "22";
        userDao.add(new User(id, "NuNu", "11232"));
//        User user = new User(id, "Eternity", "1123");
//        userDao.add(user);

        User selectedUser = userDao.findById(id);
        Assertions.assertEquals("NuNu", selectedUser.getName());
        //Assertions.assertEquals("1123", selectedUser.getPassword());
    }
}