package com.blacksw.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// 일반적으로 객체나 리소스를 관리하는 클래스를 Manager라고 부른다.
// ConnectionManager는 DB와 맺어져 있는 Connection들을 관리하는 클래스다.
public class ConnectionManager {

    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    //
    public static Connection openConnection() {
        try {
            // H2 데이터베이스 드라이버인 org.h2.Drvier 클래스를 로드한다.
            Class.forName("org.h2.Driver");
            // DB와의 커넥션을 맺는다.
            connection = DriverManager.getConnection("jdbc:h2:~/passenger", "sa", "");
            // 커넥션 리턴.
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 커넥션이 맺어져 있는 상태면 끊어버린다.
    public static void closeConnection() {
        if (null != connection) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
