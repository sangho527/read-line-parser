package com.dao;

import com.domain.User;

import java.sql.*;
import java.util.Map;

public abstract class UserDaoAbstract {

    public abstract Connection makeConnection() throws SQLException;

//    private Connection makeConnection() throws SQLException { // 접근제어자 private
//        Map<String, String> env = System.getenv();
//            // DB접속 (ex sql workbeanch실행)
//            Connection c = DriverManager.getConnection(env.get("DB_HOST"),
//                    env.get("DB_USER"), env.get("DB_PASSWORD"));
//            return c; //return Connection
//    }

    public void add(User user) {
        Map<String, String> env = System.getenv();
        try {
            // DB접속 (ex sql workbeanch실행)
            Connection c =makeConnection();

            // Query문 작성
            PreparedStatement pstmt = c.prepareStatement("INSERT INTO users(id, name, password) VALUES(?,?,?);");
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPassword());

            // Query문 실행
            pstmt.executeUpdate();

            pstmt.close();
            c.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findById(String id) { //select
        Map<String, String> env = System.getenv();
        Connection c; // 여기에도 connection 적용
        try {
            // DB접속 (ex sql workbeanch실행)
            c = makeConnection();

            // Query문 작성
            PreparedStatement pstmt = c.prepareStatement("SELECT * FROM users WHERE id = ?");
            pstmt.setString(1, id);

            // Query문 실행
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            User user = new User(rs.getString("id"), rs.getString("name"),
                    rs.getString("name"));

            rs.close();
            pstmt.close();
            c.close();

            return user;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



//    public static void main(String[] args) {
//        UserDaoAbstract userDao = new UserDaoAbstract();
////        userDao.add();
//        User user = userDao.findById("6");
//        System.out.println(user.getName());
//    }
}