package com.blacksw.ch17springbootbeans;

import com.blacksw.ch17springbootbeans.beans.TestBeans;
import com.blacksw.ch17springbootbeans.model.Passenger;
import com.blacksw.ch17springbootbeans.registration.PassengerRegistrationEvent;
import com.blacksw.ch17springbootbeans.registration.RegistrationManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Import(TestBeans.class)
public class RegistrationTest {

    @Autowired
    private Passenger passenger;

    @Autowired
    private RegistrationManager registrationManager;

    @Test
    void testPersonRegistration() {
        registrationManager.getApplicationContext().publishEvent(new PassengerRegistrationEvent(passenger));
        System.out.println("After registering: " + passenger);
        assertTrue(passenger.isRegistered());
    }

}
