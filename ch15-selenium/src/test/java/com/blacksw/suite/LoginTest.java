package com.blacksw.suite;

import com.blacksw.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.Collection;

public class LoginTest {

    private HomePage homePage;
    private WebDriver webDriver;

    public static Collection<WebDriver> getBrowserVersions() {
        return Arrays.asList(new WebDriver[]{
                new FirefoxDriver(),
                new ChromeDriver(),
                new EdgeDriver()
        });
    }

    @ParameterizedTest
    @MethodSource("getBrowserVersions")
    public void loginWithValidCredentials(WebDriver webDriver) {
        this.webDriver = webDriver;
        homePage = new HomePage(webDriver);
        homePage.openFormAuthentication() // 로그인 페이지로 이동
                .loginWith("tomsmith", "SuperSecretPassword!") // 로그인 정보 입력 후 로그인 시도
                .thenLoginSuccessful(); // 로그인 성공 검증
    }

    @ParameterizedTest
    @MethodSource("getBrowserVersions")
    public void loginWithInvalidCredentials(WebDriver webDriver) {
        this.webDriver = webDriver;
        homePage = new HomePage(webDriver);
        homePage.openFormAuthentication() // 로그인 페이지로 이동
                .loginWith("tomsmith", "SuperSecretPassword") // 로그인 정보 입력 후 로그인 시도
                .thenLoginUnsuccessful(); // 로그인 실패 검증
    }

    @AfterEach
    public void tearDown() {
        this.webDriver.close();
    }

}
