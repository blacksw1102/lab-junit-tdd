package com.blacksw.configurations;

public class DefaultConfiguration implements Configuration {

    public DefaultConfiguration(String configurationName) {

    }

    @Override
    public String getSQL(String sqlString) {
        return null;
    }
}
