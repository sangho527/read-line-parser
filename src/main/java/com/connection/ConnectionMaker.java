package com.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
    Connection makeConnection() throws SQLException; // 커넥션을 만들다가 발생할 수 있는 오류들이 SQLException인데 그것을 예외처리해줌

}
