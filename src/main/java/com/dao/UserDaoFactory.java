package com.dao;

import com.connection.AwsConnectionMaker;
//import com.connection.LocalConnectionMaker;
import com.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;

@Configuration
public class UserDaoFactory {
    @Bean
    public UserDao awsUserDao(){
        return new UserDao(awsDataSource());
    }

    public UserDao localUserDao(){
        return new UserDao(localDataSource());
    }

    @Bean
    public DataSource awsDataSource(){
        Map<String, String> env=System.getenv();
        SimpleDriverDataSource dataSource=new SimpleDriverDataSource();
        dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
        dataSource.setUrl(env.get("DB_HOST"));
        dataSource.setUsername(env.get("DB_USER"));
        dataSource.setPassword(env.get("DB_PASSWORD"));

        return dataSource;
    }
    @Bean
    public DataSource localDataSource(){
        Map<String, String> env=System.getenv();
        SimpleDriverDataSource dataSource=new SimpleDriverDataSource();
        dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
        dataSource.setUrl(env.get("localhost"));
        dataSource.setUsername(env.get("root"));
        dataSource.setPassword(env.get("password"));

        return dataSource;
    }
}