package com.dao;

import com.connection.AwsConnectionMaker;
import com.connection.ConnectionMaker;
import com.domain.User;

import java.sql.*;
import java.util.Map;

public class UserDao {
    private static ConnectionMaker connectionMaker;

    public UserDao() {
        this.connectionMaker = new AwsConnectionMaker();
    }

    public UserDao(ConnectionMaker connectionMaker) { // Constructor 오버로딩 connectionMaker를 바꿀 수 있게 여지를 준다.
        this.connectionMaker = connectionMaker;
    }

    public void deleteAll() throws SQLException {
        PreparedStatement pstmt = null;

        Connection c = null;
        try {
            c = connectionMaker.makeConnection();
            pstmt = c.prepareStatement("DELETE From users");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                }
            }
//        pstmt.executeUpdate();
//        pstmt.close();
//        c.close();
        }
    }

    public static int getCount() throws SQLException{
        Connection c = connectionMaker.makeConnection();

        PreparedStatement pstmt = c.prepareStatement("SELECT COUNT(*) from users");
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        pstmt.close();
        c.close();

        return count;
    }

    public void add(User user) {
        Map<String, String> env = System.getenv();
        try {
            // DB접속 (ex sql workbeanch실행)
            Connection c =connectionMaker.makeConnection();

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
            c = connectionMaker.makeConnection();

            // Query문 작성
            PreparedStatement pstmt = c.prepareStatement("SELECT * FROM users WHERE id = ?");
            pstmt.setString(1, id);

            // Query문 실행
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            User user = new User(rs.getString("id"), rs.getString("name"),
                    rs.getString("password"));

            rs.close();
            pstmt.close();
            c.close();

            return user;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
//        userDao.add();
        User user = userDao.findById("6");
        System.out.println(user.getName());
    }
}