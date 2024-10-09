package com.blacksw.web;

import java.io.InputStream;

public class MockConnectionFactory implements ConnectionFactory {
    private InputStream inputStream;

    public void setData(InputStream stream) {
        this.inputStream = stream;
    }

    @Override
    public InputStream getData() throws Exception {
        return inputStream;
    }
}
