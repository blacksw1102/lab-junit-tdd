package com.blacksw.spring;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;

public class SimpleAppTest {

    private static final String APPLICATION_CONTEXT_XML_FILE_NAME = "classpath:application-context.xml";

    private ClassPathXmlApplicationContext context;

    private Passenger expectedPassenger;

    @Before
    public void setUp() {
        // application-context.xml 파일을 통해서 컨텍스트 객체를 생성해준다.
        context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML_FILE_NAME);
        expectedPassenger = PassengerUtil.getExpectedPassenger();
    }

    @Test
    public void tearDown() {
        // 컨텍스트에서 passenger 객체를 주입받는다.
        Passenger passenger = (Passenger) context.getBean("passenger");
        assertEquals(expectedPassenger, passenger);
        System.out.println("expectedPassenger : " + expectedPassenger);
        System.out.println("passenger : " + passenger);
    }

}
