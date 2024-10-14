package com.blacksw.ch17springbootnewfeature;

import com.blacksw.ch17springbootnewfeature.beans.TestBeans;
import com.blacksw.ch17springbootnewfeature.model.Passenger;
import com.blacksw.ch17springbootnewfeature.registration.PassengerRegistrationEvent;
import com.blacksw.ch17springbootnewfeature.registration.RegistrationManager;
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
        assertTrue(passenger.isRegistered());
    }

}
