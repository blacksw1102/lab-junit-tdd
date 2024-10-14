package com.blacksw.dao;

import com.blacksw.ConnectionManager;
import com.blacksw.model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDao {
    private static final String GET_ALL_COUNTRIES_SQL = "select * from country";
    private static final String GET_COUNTRIES_BY_NAME_SQL = "select * from country where name like ?";

    public List<Country> getCountryList() {
        List<Country> countryList = new ArrayList<>();

        try {
            Connection connection = ConnectionManager.openConnection();
            PreparedStatement statement = connection.prepareStatement(GET_ALL_COUNTRIES_SQL);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString(2);
                String codeName = resultSet.getString(3);
                countryList.add(new Country(name, codeName));
            }

            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.closeConnection();
        }

        return countryList;
    }

    public List<Country> getCountryListStartWith(String name) {
        List<Country> countryList = new ArrayList<>();

        try {
            Connection connection = ConnectionManager.openConnection();
            PreparedStatement statement = connection.prepareStatement(GET_COUNTRIES_BY_NAME_SQL);
            statement.setString(1, name + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                countryList.add(new Country(resultSet.getString(2), resultSet.getString(3)));
            }

            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.closeConnection();
        }

        return countryList;
    }

}
