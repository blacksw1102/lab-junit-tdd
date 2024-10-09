package com.blacksw.web;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWebClientFail {
    @Test
    public void testGetContentOk() {
        MockInputStream mockStream = new MockInputStream();
        mockStream.setBuffer("It works");

        MockConnectionFactory mockConnectionFactory = new MockConnectionFactory();
        mockConnectionFactory.setData(mockStream);

        WebClient2 clinet = new WebClient2();
        String workingContent = clinet.getContent(mockConnectionFactory);

        assertEquals("It works", workingContent);
        mockStream.verify();
    }
}
