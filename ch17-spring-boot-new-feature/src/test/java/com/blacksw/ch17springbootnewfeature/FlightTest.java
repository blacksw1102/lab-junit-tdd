package com.blacksw.ch17springbootnewfeature;

import com.blacksw.ch17springbootnewfeature.beans.FlightBuilder;
import com.blacksw.ch17springbootnewfeature.model.Flight;
import com.blacksw.ch17springbootnewfeature.model.Passenger;
import com.blacksw.ch17springbootnewfeature.registration.PassengerRegistrationEvent;
import com.blacksw.ch17springbootnewfeature.registration.RegistrationManager;
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
        // 항공편의 모든 승객이 미등록 상태인지를 확인
        // 모든 승객을 등록
        for (Passenger passenger : flight.getPassengers()) {
            assertFalse(passenger.isRegistered());
            registrationManager.getApplicationContext().publishEvent(new PassengerRegistrationEvent(passenger));
        }

        // 항공편의 모든 승객이 등록되었는지 확인
        for (Passenger passenger : flight.getPassengers()) {
            assertTrue(passenger.isRegistered());
        }
    }

}
