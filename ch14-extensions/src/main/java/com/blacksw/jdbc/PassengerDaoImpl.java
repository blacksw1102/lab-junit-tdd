package com.blacksw.jdbc;

import com.blacksw.Passenger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PassengerDaoImpl implements PassengerDao {

    private Connection connection;

    // 쿼리를 실행한 Connection을 의존 주입받는다.
    public PassengerDaoImpl(Connection connection) {
        this.connection = connection;
    }

    // 신규 Passenger 정보를 DB에 저장한다.
    // 기 Passenger 정보가 존재할 경우 PassengerExistsException 예외 발생.
    @Override
    public void insert(Passenger passenger) throws PassengerExistsException {
        String sql = "INSERT INTO PASSENGERS (ID, NAME) VALUES (?, ?)";

        if (null != getById(passenger.getIdentifier())) {
            throw new PassengerExistsException(passenger, passenger.toString());
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, passenger.getIdentifier());
            statement.setString(2, passenger.getName());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    // 기 Passenger 수정 정보를 DB에 반영한다.
    @Override
    public void update(String id, String name) {
        String sql = "UPDATE PASSENGERS SET NAME = ? WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    // 기 Passenger 정보를 DB에서 제거한다.
    @Override
    public void delete(Passenger passenger) {
        String sql = "DELETE FROM PASSENGERS WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, passenger.getIdentifier());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    // 동일한 id를 가지는 Passenger를 조회한다.
    @Override
    public Passenger getById(String id) {
        String sql = "SELECT * FROM PASSENGERS WHERE ID = ?";
        Passenger passenger = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                passenger = new Passenger(resultSet.getString(1), resultSet.getString(2));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return passenger;
    }
}
