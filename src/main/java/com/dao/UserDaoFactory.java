package com.dao;

public class UserDaoFactory {
    // 조립을 해준다.
    public UserDao awsUserDao() {
        AwsConnectionMaker awsConnectionMaker = new AwsConnectionMaker();
        UserDao userDao = new UserDao(awsConnectionMaker);
        return userDao;
    }

    public UserDao localUserDao() {
        //LocalConnectionMaker localConnectionMaker = new LocalConnectionMaker();
        UserDao userDao = new UserDao(new LocalConnectionMaker());
        return userDao;
    }
}
