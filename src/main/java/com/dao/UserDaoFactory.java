package com.dao;

import com.connection.AwsConnectionMaker;
import com.connection.LocalConnectionMaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDaoFactory {
    // 조립을 해준다.
    @Bean
    public UserDao awsUserDao() { // 다형성 (날개 5개 선풍기)
        AwsConnectionMaker awsConnectionMaker = new AwsConnectionMaker();
        UserDao userDao = new UserDao(awsConnectionMaker);
        return userDao;
    }

    @Bean
    public UserDao localUserDao() { // 다형성 (날개 5개 선풍기)
        UserDao userDao = new UserDao(new LocalConnectionMaker());
        return userDao;
    }
}
