package com.blacksw;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * JUnit으로 Cucumber 테스트를 실행하기 위한 진입점 역할을 하는 클래스
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = "classpath:features")
public class CucumberTest {

    /**
     *  CucumberTest 클래스는 비워 두고, 스텝 정의는 별도의 클래스에서 구현
     */

}