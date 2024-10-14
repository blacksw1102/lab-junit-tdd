package com.blacksw.ch18rest;

import com.blacksw.ch18rest.beans.FlightBuilder;
import com.blacksw.ch18rest.model.Flight;
import com.blacksw.ch18rest.model.Passenger;
import com.blacksw.ch18rest.registration.PassengerRegistrationEvent;
import com.blacksw.ch18rest.registration.RegistrationManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Import(FlightBuilder.class)
public class FlightTest {

    @Autowired
    private Flight flight;

    @Autowired
    private RegistrationManager registrationManager;

    @Test
    void testFlightPassengersRegistration() {
        for (Passenger passenger : flight.getPassengers()) {
            assertFalse(passenger.isRegistered());
            registrationManager.getApplicationContext().publishEvent(new PassengerRegistrationEvent(passenger));
        }

        for (Passenger passenger : flight.getPassengers()) {
            assertTrue(passenger.isRegistered());
        }
    }
}