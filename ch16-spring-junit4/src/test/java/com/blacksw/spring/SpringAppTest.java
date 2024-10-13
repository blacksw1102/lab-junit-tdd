package com.blacksw.spring;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

// 어노테이션을 통해서 스프링 컨텍스트를 자동으로 초기화한다.
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class SpringAppTest {

    // 컨텍스트에서 passenger 빈을 자동 주입해준다.
    @Autowired
    private Passenger passenger;
    private Passenger expectedPassenger;

    @Before
    public void setUp() {
        expectedPassenger = PassengerUtil.getExpectedPassenger();
    }

    @Test
    public void tetInitPassenger() {
        assertEquals(expectedPassenger, passenger);
        System.out.println("expectedPassenger : " + expectedPassenger);
        System.out.println("passenger : " + passenger);
    }
}
