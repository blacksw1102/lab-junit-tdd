package com.blacksw.browse;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MultiBrowserSeleniumTest {

    private WebDriver driver;

    public static Collection<WebDriver> getBrowserVersions() {
        return Arrays.asList(new WebDriver[] {
            new ChromeDriver(),
            new FirefoxDriver(),
            new EdgeDriver()
        });
    }

    // 파라미터별로 브라우저를 열어서 manning.com 접근 테스트 수행
    @ParameterizedTest
    @MethodSource("getBrowserVersions")
    void testManningAccess(WebDriver driver) {
        this.driver = driver;
        driver.get("https://www.manning.com");
        assertThat(driver.getTitle(), is("Manning"));
    }

    // 파라미터별로 브라우저를 열어서 google.com 접근 테스트 수행
    @ParameterizedTest
    @MethodSource("getBrowserVersions")
    void testGoogleAccess(WebDriver driver) {
        this.driver = driver;
        driver.get("https://www.google.com");
        assertThat(driver.getTitle(), is("Google"));
    }

    @AfterEach
    void tearDown() {
        driver.close();
    }

}
