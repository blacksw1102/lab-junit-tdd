package com.blacksw.browse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WikipediaAccessTest {

    private RemoteWebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new FirefoxDriver();
    }

    @Test
    void testWikipediaAccess() {
        // 페이지 타이틀 검증
        driver.get("https://en.wikipedia.org/");
        assertThat(driver.getTitle(), is("Wikipedia, the free encyclopedia"));

        // "Talk"라는 텍스트의 링크를 가진 Element를 찾아서 클릭 => 페이지 이동
        WebElement contents = driver.findElement(By.linkText("Talk"));
        assertTrue(contents.isDisplayed());
        contents.click();

        // 이동한 페이지 타이틀 검증
        assertThat(driver.getTitle(), is("Talk:Main Page - Wikipedia"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}