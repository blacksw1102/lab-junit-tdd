package com.blacksw.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:application-context.xml")
public class RegistrationTest {

    @Autowired
    private Passenger passenger;

    @Autowired
    private RegistrationManager registrationManager;

    @Test
    public void testPersonRegistration() {
        registrationManager.getApplicationContext().publishEvent(new PassengerRegistrationEvent(passenger));
        assertTrue(passenger.isRegistered());

        System.out.println(System.getProperty("java.version"));
    }

}
