package com.blacksw;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FlightTest {

    @Test
    public void testFlightCreation() {
        Flight flight = new Flight("AA123", 100);
        assertNotNull(flight);
    }

    @Test
    public void testInvalidFlightNumber() {
        assertThrows(RuntimeException.class, () -> new Flight("AA12", 100));
        assertThrows(RuntimeException.class, () -> new Flight("AA12345", 100));
    }

    @Test
    public void testValidFlightNumber() {
        Flight flight1 = new Flight("AA345", 100);
        assertNotNull(flight1);
        Flight flight2 = new Flight("AA3456", 100);
        assertNotNull(flight2);
    }

    @Test
    public void testAddPassengers() throws IOException {
        Flight flight = FlightBuilderUtil.buildFlightFromCsv();
        assertEquals(50, flight.getPassengersNumber());
        assertThrows(RuntimeException.class, () -> flight.addPassenger(new Passenger("124-56-7890", "Michael Johnson", "US")));
    }

    @Test
    public void testSetInvalidSeats() throws IOException {
        Flight flight = FlightBuilderUtil.buildFlightFromCsv();
        assertEquals(50, flight.getPassengersNumber());
        assertThrows(RuntimeException.class, () -> flight.setSeats(49));
    }

    @Test
    public void testSetValidSeats() throws IOException {
        Flight flight = FlightBuilderUtil.buildFlightFromCsv();
        assertEquals(50, flight.getPassengersNumber());
        flight.setSeats(52);
        assertEquals(52, flight.getSeats());
    }

    @Test
    public void testChangeOrigin() {
        Flight flight = new Flight("AA1234", 50);
        flight.setOrigin("London");
        flight.setDestination("Bucharest");
        flight.takeOff();
        assertTrue(flight.isFlying());
        assertTrue(flight.isTakenOff());
        assertFalse(flight.isLanded());
        assertThrows(RuntimeException.class, () -> flight.setOrigin("Manchester"));
    }

    @Test
    public void testLand() {
        Flight flight = new Flight("AA1234", 50);
        flight.setOrigin("London");
        flight.setDestination("Bucharest");
        flight.takeOff();
        assertTrue(flight.isTakenOff());
        assertFalse(flight.isLanded());
        flight.land();
        assertTrue(flight.isTakenOff());
        assertTrue(flight.isLanded());
        assertFalse(flight.isFlying());
    }

}
