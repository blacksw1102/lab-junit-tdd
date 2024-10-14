package com.blacksw.ch18rest.exceptions;

public class PassengerNotFoundException extends RuntimeException {

    public PassengerNotFoundException(Long id) {
        super("Passenger is not found : " + id);
    }

}
