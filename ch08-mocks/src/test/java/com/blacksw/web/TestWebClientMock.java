package com.blacksw.web;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWebClientMock {
    @Test
    public void testGetContentOk() throws MalformedURLException {
        MockHttpURLConnection mockConnection = new MockHttpURLConnection();
        mockConnection.setExpectedInputStream(new ByteArrayInputStream("It works".getBytes()));

        TestableWebClient client = new TestableWebClient();
        client.setHttpURLConnection(mockConnection);

        String result = client.getContent(new URL("http://localhost"));

        assertEquals("It works", result);
    }

    private class TestableWebClient extends WebClient1 {
        private HttpURLConnection connection;

        public void setHttpURLConnection(HttpURLConnection connection) {
            this.connection = connection;
        }

        public HttpURLConnection createHttpURLConnection(URL url) throws IOException {
            return this.connection;
        }
    }

}
