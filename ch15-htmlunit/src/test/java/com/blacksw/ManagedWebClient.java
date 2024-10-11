package com.blacksw;

import com.gargoylesoftware.htmlunit.WebClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class ManagedWebClient {
    // HtmlUnit 테스트의 주요 시작점.
    protected WebClient webClient;

    // 각 테스트를 실행하기 전에 WebClient 객체를 초기화한다.
    // WebClient 객체는 웹 브라우저를 시물레이션하고 테스트하기 위해 사용한다.
    @BeforeEach
    public void setUp() {
        webClient = new WebClient();
    }

    // 테스트를 종료하고 나면 웹 클라이언트 자원을 해제한다.
    @AfterEach
    public void tearDown() {
        webClient.close();
    }
}
