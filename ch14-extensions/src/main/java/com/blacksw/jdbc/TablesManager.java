package com.blacksw.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TablesManager {

    public static void createTable(Connection connection) {
        String sql = "CREATE TABLE IF NOT EXISTS PASSENGERS (ID VARCHAR(50), NAME VARCHAR(50));";
        executeStatement(connection, sql);
    }

    public static void dropTable(Connection connection) {
        String sql = "DROP TABLE IF EXISTS PASSENGERS;";
        executeStatement(connection, sql);
    }

    private static void executeStatement(Connection connection, String sql) {
        // try-with-resources 구문으로 PreparedStatement를 할당하고 블록 종료 시 자동으로 자원을 해제한다.
        // PreparedStatement를 통해서 sql 구문을 실행한다.
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
