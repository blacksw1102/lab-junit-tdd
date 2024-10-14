package com.blacksw.ch17springbootnewfeature.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Flight {
    private String flightNumber;
    private int seats;
    private Set<Passenger> passengers = new HashSet<>();

    public Flight(String flightNumber, int seats) {
        this.flightNumber = flightNumber;
        this.seats = seats;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public int getSeats() {
        return seats;
    }

    public Set<Passenger> getPassengers() {
        return Collections.unmodifiableSet(passengers);
    }

    // 항공편 승객 추가
    public boolean addPassenger(Passenger passenger) {
        if (passengers.size() >= seats) {
            throw new RuntimeException("항공편의 좌석 수보다 더 많은 승객을 추가할 수 없습니다.");
        }
        return passengers.add(passenger);
    }

    // 항공편 승객 제거
    public boolean removePassenger(Passenger passenger) {
        return passengers.remove(passenger);
    }

    @Override
    public String toString() {
        return "Flight " + getFlightNumber();
    }

}