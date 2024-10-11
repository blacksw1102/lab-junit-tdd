package com.blacksw;

import com.gargoylesoftware.htmlunit.CollectingAlertHandler;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.MockWebConnection;
import com.gargoylesoftware.htmlunit.WebAssert;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class WindowConfirmTest extends ManagedWebClient {

    @Test
    public void testWindowConfirm() throws FailingHttpStatusCodeException, IOException {
        // 모의 html 커넥션 바인딩 설정
        String html = "<html><head><title>Hello</title></head><body onload='confirm(\"Confirm Message\")'></body></html>";
        URL testUrl = new URL("http://Page1/");
        MockWebConnection mockConnection = new MockWebConnection();
        mockConnection.setResponse(testUrl, html);
        webClient.setWebConnection(mockConnection);

        // 컨펌 창이 떴을 경우 컨펌 메시지를 리스트에 추가한다.
        List<String> confirmMessages = new ArrayList<>();
        webClient.setConfirmHandler((page, message) -> {
            confirmMessages.add(message);
            return true;
        });

        // 타이틀 검증
        HtmlPage firstPage = webClient.getPage(testUrl);
        WebAssert.assertTitleEquals(firstPage, "Hello");
        
        // 컨펌 메시지 검증
        assertArrayEquals(new String[]{"Confirm Message"}, confirmMessages.toArray());
    }

    @Test
    public void testWindowConfirmAndAlert() throws FailingHttpStatusCodeException, IOException {
        // 모의 html 커넥션 바인딩 설정
        String html = "<html><head><title>Hello</title><script>function go(){alert(confirm('Confirm Message'))}</script>" +
                "</head><body onload='go()'></body></html>";
        URL testUrl = new URL("http://Page1/");
        MockWebConnection mockConnection = new MockWebConnection();
        mockConnection.setResponse(testUrl, html);
        webClient.setWebConnection(mockConnection);

        // 컨펌 창이 떴을 경우 컨펌 메시지를 리스트에 추가한다.
        List<String> confirmMessages = new ArrayList<>();
        webClient.setAlertHandler(new CollectingAlertHandler());
        webClient.setConfirmHandler((page, message) -> {
            confirmMessages.add(message);
            return true;
        });

        // 페이지 타이틀 검증
        HtmlPage firstPage = webClient.getPage(testUrl);
        WebAssert.assertTitleEquals(firstPage, "Hello");

        // 컨펌 메시지 검증
        assertArrayEquals(new String[]{"Confirm Message"}, confirmMessages.toArray());

        // 경고 메시지 검증
        assertArrayEquals(new String[]{"true"}, ((CollectingAlertHandler) webClient.getAlertHandler()).getCollectedAlerts().toArray());
    }

}
