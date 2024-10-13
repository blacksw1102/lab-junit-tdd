package com.blacksw.springboot;

import com.blacksw.springboot.model.Passenger;
import com.blacksw.springboot.registration.PassengerRegistrationEvent;
import com.blacksw.springboot.registration.RegistrationManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@EnableAutoConfiguration
@ImportResource("classpath:application-context.xml")
public class RegistrationTest {

    @Autowired
    private Passenger passenger;

    @Autowired
    private RegistrationManager registrationManager;

    @Test
    void testPersonRegistration() {
        registrationManager.getApplicationContext().publishEvent(new PassengerRegistrationEvent(passenger));
        assertTrue(passenger.isRegistered());
    }

}
