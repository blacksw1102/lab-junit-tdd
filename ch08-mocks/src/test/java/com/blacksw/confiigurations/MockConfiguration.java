package com.blacksw.confiigurations;

import com.blacksw.configurations.Configuration;

public class MockConfiguration implements Configuration {

    public void setSQL(String sqlString) {

    }

    @Override
    public String getSQL(String sqlString) {
        return null;
    }
}
