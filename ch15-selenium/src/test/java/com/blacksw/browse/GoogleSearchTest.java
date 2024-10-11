package com.blacksw.browse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleSearchTest {

    private RemoteWebDriver driver;

    public static Collection<RemoteWebDriver> getBrowserVersions() {
        return Arrays.asList(new RemoteWebDriver[]{new ChromeDriver(), new FirefoxDriver(), new EdgeDriver()});
    }

    @ParameterizedTest
    @MethodSource("getBrowserVersions")
    void testGoogleSearch(RemoteWebDriver driver) {
        this.driver = driver;

        // Google에서 Wikipedia를 검색한다.
        driver.get("http://www.google.com");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("en.wikipedia.org");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

        // 1000 ms 이내에 "result-stats" id를 지닌 element가 렌더링 되는지 검증.
        WebElement myDynamicElement  = (new WebDriverWait(driver, Duration.ofMillis(1000)))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("result-stats")));

        // Google 페이지 첫번째 검색 결과의 url과 타이틀을 검증한다.
        List<WebElement> findElements = driver.findElements(By.xpath("//*[@id='rso']//a/h3"));
        findElements.get(0).click();
        assertEquals("https://en.wikipedia.org/wiki/Main_Page", driver.getCurrentUrl());
        assertThat(driver.getTitle(), is("Wikipedia, the free encyclopedia"));

        // Wikipedia 페이지에 "Talk" 링크가 화면에 표시되고 있는지 검증한다.
        WebElement contents = driver.findElement(By.linkText("Talk"));
        assertTrue(contents.isDisplayed());

        // Wikipedia > Talk:Main Page 페이지의 타이틀을 검증한다.
        contents.click();
        assertThat(driver.getTitle(), is("Talk:Main Page - Wikipedia"));
    }

    @AfterEach
    public void tearDown() {
        // 브라우저 자원 해제
        driver.close();
    }

}
