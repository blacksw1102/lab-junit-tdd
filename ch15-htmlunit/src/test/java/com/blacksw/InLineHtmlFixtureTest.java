package com.blacksw;

import com.gargoylesoftware.htmlunit.MockWebConnection;
import com.gargoylesoftware.htmlunit.WebAssert;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

public class InLineHtmlFixtureTest extends ManagedWebClient {

    @Test
    public void testInLineHtmlFixture() throws IOException {
        // 모의 HTTP 연결 설정.
        String expectedTitle = "Hello 1!";
        String html = "<html><head><title>" + expectedTitle + "</title></head></html>";
        MockWebConnection connection = new MockWebConnection();
        connection.setDefaultResponse(html);
        webClient.setWebConnection(connection);

        // 페이지 타이틀 검증 수행.
        HtmlPage page = webClient.getPage("http://page");
        WebAssert.assertTitleEquals(page, expectedTitle);
    }

    @Test
    public void testInLineHtmlFixtures() throws IOException {
        final URL page1Url = new URL("http://Page1/");
        final URL page2Url = new URL("http://Page2/");
        final URL page3Url = new URL("http://Page3/");

        // 모의 HTTP 연결 설정.
        MockWebConnection connection = new MockWebConnection();
        connection.setResponse(page1Url, "<html><head><title>Hello 1!</title></head></html>");
        connection.setResponse(page2Url, "<html><head><title>Hello 2!</title></head></html>");
        connection.setResponse(page3Url, "<html><head><title>Hello 3!</title></head></html>");
        webClient.setWebConnection(connection);

        // 페이지별 타이틀 검증 수행.
        HtmlPage page1 = webClient.getPage(page1Url);
        WebAssert.assertTitleEquals(page1, "Hello 1!");

        HtmlPage page2 = webClient.getPage(page2Url);
        WebAssert.assertTitleEquals(page2, "Hello 2!");

        HtmlPage page3 = webClient.getPage(page3Url);
        WebAssert.assertTitleEquals(page3, "Hello 3!");
    }
}