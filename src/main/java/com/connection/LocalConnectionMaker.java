package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LocalConnectionMaker implements ConnectionMaker{
    @Override
    public Connection makeConnection() throws SQLException {
            // DB접속 (ex sql workbeanch실행)
            Connection l = DriverManager.getConnection(
                    "jdbc:mysql://ec2-18-217-118-83.us-east-2.compute.amazonaws.com/likelion-db",
                    "root",
                    "password");
            return l; //return Connection
    }
}
