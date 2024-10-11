package com.blacksw.jdbc;

import com.blacksw.Passenger;

// DB에 동일한 Passenger가 존재할 경우 발생하는 Exception.
public class PassengerExistsException extends Exception {
    private Passenger passenger;

    public PassengerExistsException(Passenger passenger, String message) {
        super(message);
        this.passenger = passenger;
    }
}
