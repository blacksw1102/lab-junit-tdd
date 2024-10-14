package com.blacksw.dao;

import com.blacksw.model.Country;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryRowMapper implements RowMapper<Country> {
    public static final String NAME = "name";
    public static final String CODE_NAME = "code_name";

    @Override
    public Country mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Country(resultSet.getString(NAME), resultSet.getString(CODE_NAME));
    }
}
