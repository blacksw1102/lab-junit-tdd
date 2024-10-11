package com.blacksw.browse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FirefoxSeleniumTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        // 파이어 폭스 드라이버 초기화
        driver = new FirefoxDriver();
    }

    @Test
    void testFirefoxManning() {
        // 브라우저를 열여서 페이지의 타이틀 검증
        driver.get("https://www.manning.com/");
        assertThat(driver.getTitle(), is("Manning"));
    }

    @Test
    void testFirefoxGoogle() {
        // 브라우저를 열여서 페이지의 타이틀 검증
        driver.get("https://www.google.com");
        assertThat(driver.getTitle(), is("Google"));
    }

    @AfterEach
    void tearDown() {
        // 테스트 종료 후 브라우저 할당 자원 해제
        driver.close();
    }

}
